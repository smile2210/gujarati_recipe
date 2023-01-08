package com.gujaratirecipe.Activity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.tabs.TabLayout;
import com.gujaratirecipe.Adapter.FragmentAdapter;
import com.gujaratirecipe.Model.Model;
import com.gujaratirecipe.Model.SecondModel;
import com.gujaratirecipe.R;
import com.gujaratirecipe.TinyDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    ImageView back, image, image2;
    TextView recepi;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppCompatButton like, shareAsPdf;
    public static String samgri;
    public static String rit;
    int type_id, row_id;
    TinyDB tinydb;
    ArrayList<Model> modellist = new ArrayList<>();
    ArrayList<SecondModel> secondmodellist = new ArrayList<>();
    boolean liked = false;
    SecondModel secondModel = new SecondModel();


    LinearLayout linearLayout;
    TextView title, samgritext, rittext;
    ImageView recipe_image;
    Bitmap bitmap;

    int pageHeight = 1520;
    int pagewidth = 792;

    private static final int PERMISSION_REQUEST_CODE = 200;

    private InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-5224318517283869/7628232091";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        loadAd();

        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }

        init();

        tinydb = new TinyDB(ThirdActivity.this);
        like.setEnabled(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        modellist = new ArrayList<>();
        secondmodellist = new ArrayList<>();

        modellist = (ArrayList<Model>) tinydb.getListModel("modellist", Model.class);
        secondmodellist = (ArrayList<SecondModel>) tinydb.getListModel("secondmodellist", SecondModel.class);

        secondModel = (SecondModel) getIntent().getExtras().get("image2");

        Glide.with(ThirdActivity.this).load(secondModel.getPic2()).into(image2);
        recepi.setText(getIntent().getStringExtra("name"));
        type_id = getIntent().getIntExtra("type_id", 0);
        row_id = getIntent().getIntExtra("row_id", 0);

        for (Model model : modellist) {
            if(model.getType_id() == type_id && model.getRow_id() == row_id){
                like.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                liked = true;
                break;
            }
        }

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.samgri).setText("સામગ્રી"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.rit).setText("રીત"));

        FragmentPagerAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(! liked){
                    like.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                    liked = true;

                    Model model = new Model();
                    model.setName(recepi.getText().toString());
                    model.setType_id(type_id);
                    model.setRow_id(row_id);
                    model.setSahitya(samgri);
                    model.setKruti(rit);

                    modellist.add(model);
                    secondmodellist.add(secondModel);
                    Toast.makeText(ThirdActivity.this, "Add to Favourite", Toast.LENGTH_SHORT).show();

                }
                else{
                    like.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite_border));
                    liked = false;

                    for (Model model : modellist) {
                        if(model.getType_id() == type_id && model.getRow_id() == row_id){
                            modellist.remove(model);
                            break;
                        }
                    }

                    for (SecondModel secondModel1 : secondmodellist) {
                        if(secondModel1.getPic2() == secondModel.getPic2()){
                            secondmodellist.remove(secondModel1);
                            break;
                        }
                    }

                    Toast.makeText(ThirdActivity.this, "Remove from Favourite", Toast.LENGTH_SHORT).show();

                }

                tinydb.putListModel("modellist", modellist);
                tinydb.putListModel("secondmodellist", secondmodellist);


            }
        });

        samgri = (getIntent().getStringExtra("sahitya"));
        rit = (getIntent().getStringExtra("kruti"));


        title.setText(recepi.getText().toString());
        Glide.with(ThirdActivity.this).load(secondModel.getPic2()).into(recipe_image);
        samgritext.setText(samgri);
        rittext.setText(rit);

        shareAsPdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if(checkPermission()){
                    generatePDF(recepi.getText().toString(), secondModel.getPic2(), samgri, rit);
                }else{
                    requestPermission();
                }
            }
        });

    }

    private void init() {
        back = findViewById(R.id.back);
        image2 = findViewById(R.id.image2);
        image = findViewById(R.id.image);
        recepi = findViewById(R.id.recipe);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        like = findViewById(R.id.like);
        shareAsPdf = findViewById(R.id.shareAsPdf);
        linearLayout = findViewById(R.id.sharepdflayout);
        title = findViewById(R.id.title);
        samgritext = findViewById(R.id.samgritext);
        rittext = findViewById(R.id.rittext);
        recipe_image = findViewById(R.id.recipe_image);
    }

    public void loadAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(AD_UNIT_ID);

        interstitialAd.setAdListener(
                new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        showInterstitial();
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
//                        String error = String.format("domain: %s, code: %d, message: %s", loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
//                        Toast.makeText(ThirdActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdClosed() {

                    }
                });
    }

    private void showInterstitial() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
//            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void generatePDF(String s, int pic2, String samgri, String rit) {

        PdfDocument pdfDocument = new PdfDocument();

        Paint paint = new Paint();
        Paint title = new Paint();

        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
        Canvas canvas = myPage.getCanvas();

        //Title
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextAlign(Paint.Align.CENTER);
        title.setTextSize(40);
        title.setColor(ContextCompat.getColor(this, android.R.color.black));
        canvas.drawText(s, pagewidth / 2, 50, title);

        // pic
        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), pic2), pagewidth/3, 200, true);
        canvas.drawBitmap(bitmap, pagewidth / 3, 100, paint);

        // samgri

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setColor(ContextCompat.getColor(this, android.R.color.black));
        title.setTextSize(40);
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("સામગ્રી : ", pagewidth/10, 350, title);

        TextPaint mTextPaint = new TextPaint();
        mTextPaint.setTextSize(23);
        mTextPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTextPaint.setColor(ContextCompat.getColor(this, android.R.color.black));
        StaticLayout mTextLayout = new StaticLayout( samgri ,mTextPaint, canvas.getWidth() - 100, Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, false);
        canvas.save();
        int textX = pagewidth/17;
        int textY = 400;
        canvas.translate(textX, textY);
        mTextLayout.draw(canvas);
        canvas.restore();

        pdfDocument.finishPage(myPage);

        //Create 2nd page

        PdfDocument.PageInfo mypageInfo1 = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 2).create();

        PdfDocument.Page myPage1 = pdfDocument.startPage(mypageInfo1);
        Canvas canvas1 = myPage1.getCanvas();

        // rit

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setColor(ContextCompat.getColor(this, android.R.color.black));
        title.setTextSize(40);
        title.setTextAlign(Paint.Align.CENTER);
        canvas1.drawText("રીત : ", pagewidth/10, 100, title);


        TextPaint mTextPaint1 = new TextPaint();
        mTextPaint1.setTextSize(23);
        mTextPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTextPaint.setColor(ContextCompat.getColor(this, android.R.color.black));
        StaticLayout mTextLayout1 = new StaticLayout( rit ,mTextPaint1, canvas1.getWidth() - 100, Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, false);
        canvas1.save();
        int textX1 = pagewidth/17;
        int textY1 = 150;
        canvas1.translate(textX1, textY1);

        mTextLayout1.draw(canvas1);
        canvas1.restore();

        pdfDocument.finishPage(myPage1);


        // below line is used to set the name of
        // our PDF file and its path.

//        String path = Environment.getExternalStorageDirectory() + "/Gujarati_recipe";
//        File dir = new File(path);
//        if(! dir.exists())
//            dir.mkdirs();

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if(! path.exists())
            path.mkdirs();

        File filePath = new File(path,  s + ".pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(filePath));
            shareFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        pdfDocument.close();

    }

    public void shareFile(File myFile){
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        Uri uri = FileProvider.getUriForFile(ThirdActivity.this, "com.example.homefolder.example.provider", myFile);

        if(myFile.exists()) {
            intentShareFile.setType("application/pdf");
            intentShareFile.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentShareFile.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentShareFile.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intentShareFile.putExtra(Intent.EXTRA_STREAM, uri);
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File from Gujarati Recipe...");
            startActivity(Intent.createChooser(intentShareFile, "Share File Gujarati Recipe"));
        }
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0){

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if(writeStorage && readStorage){
                    generatePDF(recepi.getText().toString(), secondModel.getPic2(), samgri, rit);
                }else{
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onResume() {
        // Start or resume the game.
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}