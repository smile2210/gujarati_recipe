package com.gujaratirecipe.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gujaratirecipe.Adapter.MainAdapter;
import com.gujaratirecipe.Adapter.SecondAdapter;
import com.gujaratirecipe.Model.Model;
import com.gujaratirecipe.Model.SecondModel;
import com.gujaratirecipe.R;
import com.gujaratirecipe.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity {

    ListView list;
    ImageView back;
    TinyDB tinyDB;
    ArrayList<SecondModel> secondModelList = new ArrayList<>();
    ArrayList<Model> modellist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        list = findViewById(R.id.list);
        back = findViewById(R.id.back);

        tinyDB = new TinyDB(FavActivity.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FavActivity.this,ThirdActivity.class);
                intent.putExtra("image2",secondModelList.get(i));
                intent.putExtra("name",modellist.get(i).getName());
                intent.putExtra("sahitya",modellist.get(i).getSahitya());
                intent.putExtra("kruti",modellist.get(i).getKruti());
                intent.putExtra("type_id",modellist.get(i).getType_id());
                intent.putExtra("row_id",modellist.get(i).getRow_id());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        modellist = new ArrayList<>();
        secondModelList = new ArrayList<>();

        modellist = (ArrayList<Model>) tinyDB.getListModel("modellist",Model.class);
        secondModelList = (ArrayList<SecondModel>) tinyDB.getListModel("secondmodellist",SecondModel.class);

        SecondAdapter secondAdapter = new SecondAdapter(FavActivity.this, secondModelList, modellist);
        list.setAdapter(secondAdapter);
    }
}