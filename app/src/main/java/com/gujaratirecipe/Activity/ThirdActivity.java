package com.gujaratirecipe.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.gujaratirecipe.Adapter.FragmentAdapter;
import com.gujaratirecipe.Model.Model;
import com.gujaratirecipe.Model.SecondModel;
import com.gujaratirecipe.R;
import com.gujaratirecipe.TinyDB;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    ImageView back,image,image2;
    TextView recepi;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppCompatButton like;
    public static String samgri;
    public static String rit;
    int type_id,row_id;
    TinyDB tinydb;
    ArrayList<Model> modellist = new ArrayList<>();
    ArrayList<SecondModel> secondmodellist = new ArrayList<>();

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

        SecondModel secondModel = new SecondModel();
        secondModel = (SecondModel) getIntent().getExtras().get("image2");

        Glide.with(ThirdActivity.this).load(secondModel.getPic2()).into(image2);
        recepi.setText(getIntent().getStringExtra("name"));
        type_id = getIntent().getIntExtra("type_id",0);
        row_id = getIntent().getIntExtra("row_id",0);

        for (Model model : modellist){
            if(model.getType_id() == type_id && model.getRow_id() == row_id){
                like.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                like.setEnabled(false);
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
                like.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                like.setEnabled(false);

                Model model = new Model();
                model.setName(recepi.getText().toString());
                model.setType_id(type_id);
                model.setRow_id(row_id);
                model.setSahitya(samgri);
                model.setKruti(rit);

                modellist.add(model);

                SecondModel secondModel = new SecondModel();
                secondModel.setPic2(getIntent().getIntExtra("image2",0));

                secondmodellist.add(secondModel);

                tinydb.putListModel("modellist",modellist);
                tinydb.putListModel("secondmodellist",secondmodellist);

            }
        });

        samgri = (getIntent().getStringExtra("sahitya"));
        rit = (getIntent().getStringExtra("kruti"));

    }
}