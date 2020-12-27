package com.example.gujaratirecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ImageView back, image;
    TextView recipe;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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

        final List<SecondModel> secondModelList = new ArrayList<>();
        for (int i = 0; i < pic2.length; i++) {
            SecondModel secondModel = new SecondModel();
            secondModel.setPic2(pic2[i]);
            secondModelList.add(secondModel);
        }


        SecondAdapter secondAdapter = new SecondAdapter(SecondActivity.this, secondModelList,MainAdapter.modelList);
        list.setAdapter(secondAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("image2",pic2[i]);
                intent.putExtra("name",MainAdapter.modelList.get(i).getName());
                intent.putExtra("sahitya",MainAdapter.modelList.get(i).getSahitya());
                intent.putExtra("kruti",MainAdapter.modelList.get(i).getKruti());
                startActivity(intent);
            }
        });

    }
}