package com.gujaratirecipe.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.gujaratirecipe.Adapter.MainAdapter;
import com.gujaratirecipe.BaseActivity;
import com.gujaratirecipe.R;
import com.gujaratirecipe.Adapter.SecondAdapter;
import com.gujaratirecipe.Model.SecondModel;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends BaseActivity {

    ImageView back, image;
    TextView recipe;
    ListView list;
    int selectedPosition = -1;
    List<SecondModel> secondModelList = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        loadAd();

        progressDialog = new ProgressDialog(SecondActivity.this);
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        back = findViewById(R.id.back);
        image = findViewById(R.id.image);
        recipe = findViewById(R.id.recipe);
        list = findViewById(R.id.list);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        image.setImageResource(getIntent().getExtras().getInt("image"));
        recipe.setText(getIntent().getStringExtra("recipe"));

        final int[] pic2 = getIntent().getExtras().getIntArray("pic2");


        for (int j : pic2) {
            SecondModel secondModel = new SecondModel();
            secondModel.setPic2(j);
            secondModelList.add(secondModel);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                SecondAdapter secondAdapter = new SecondAdapter(SecondActivity.this, secondModelList, MainAdapter.modelList);
                list.setAdapter(secondAdapter);
            }
        },2500);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedPosition = i;
                showInterstitial(SecondActivity.this);

                if (selectedPosition >= 0){
                    Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                    intent.putExtra("image2",secondModelList.get(selectedPosition));
                    intent.putExtra("name",MainAdapter.modelList.get(selectedPosition).getName());
                    intent.putExtra("sahitya",MainAdapter.modelList.get(selectedPosition).getSahitya());
                    intent.putExtra("kruti",MainAdapter.modelList.get(selectedPosition).getKruti());
                    intent.putExtra("type_id",MainAdapter.modelList.get(selectedPosition).getType_id());
                    intent.putExtra("row_id",MainAdapter.modelList.get(selectedPosition).getRow_id());
                    startActivity(intent);
                }
            }
        });

    }


    public void adClosed() {


    }


}