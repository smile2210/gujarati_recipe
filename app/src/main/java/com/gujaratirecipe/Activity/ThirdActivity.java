package com.gujaratirecipe.Activity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

    ImageView back,image,image2;
    TextView recepi;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppCompatButton like,shareAsPdf;
    public static String samgri;
    public static String rit;
    int type_id,row_id;
    TinyDB tinydb;
    ArrayList<Model> modellist = new ArrayList<>();
    ArrayList<SecondModel> secondmodellist = new ArrayList<>();
    boolean liked = false;
    SecondModel secondModel = new SecondModel();

    int pageHeight = 1120;
    int pagewidth = 792;

    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        back = findViewById(R.id.back);
        image2 = findViewById(R.id.image2);
        image = findViewById(R.id.image);
        recepi = findViewById(R.id.recipe);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        like = findViewById(R.id.like);
        shareAsPdf = findViewById(R.id.shareAsPdf);

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

        modellist = (ArrayList<Model>) tinydb.getListModel("modellist",Model.class);
        secondmodellist = (ArrayList<SecondModel>) tinydb.getListModel("secondmodellist",SecondModel.class);


        secondModel = (SecondModel) getIntent().getExtras().get("image2");

        Glide.with(ThirdActivity.this).load(secondModel.getPic2()).into(image2);
        recepi.setText(getIntent().getStringExtra("name"));
        type_id = getIntent().getIntExtra("type_id",0);
        row_id = getIntent().getIntExtra("row_id",0);

        for (Model model : modellist){
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



                if(!liked){
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

                }else {
                    like.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite_border));
                    liked = false;

                    for (Model model : modellist){
                        if(model.getType_id() == type_id && model.getRow_id() == row_id){
                            modellist.remove(model);
                            break;
                        }
                    }

                    for (SecondModel secondModel1 : secondmodellist){
                        if(secondModel1.getPic2() == secondModel.getPic2()){
                            secondmodellist.remove(secondModel1);
                            break;
                        }
                    }

                }

                tinydb.putListModel("modellist",modellist);
                tinydb.putListModel("secondmodellist",secondmodellist);



            }
        });

        samgri = (getIntent().getStringExtra("sahitya"));
        rit = (getIntent().getStringExtra("kruti"));

        shareAsPdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    Toast.makeText(ThirdActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    shareAsPdffile();
                } else {
                    requestPermission();
                }
            }
        });

    }

    private void shareAsPdffile() {

        LinearLayout linearLayout = findViewById(R.id.mainLinear);

        View screenView = linearLayout.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);

        store(bitmap,"recipe.jpg");
    }

    public static void store(Bitmap bm, String fileName){
        final String dirPath = Environment.getExternalStorageDirectory() + "/Gujarati_recipe_Screenshots";
        File dir = new File(dirPath);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
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

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}