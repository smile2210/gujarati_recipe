package com.example.gujaratirecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.rd.PageIndicatorView;

import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Database database;
    AutoScrollViewPager viewPager;
    PageIndicatorView indicator;

    int[] img = new int[]{R.drawable.nasta,R.drawable.mithai,R.drawable.icecremsarbat,R.drawable.festival,R.drawable.child};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        menu = findViewById(R.id.menu);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.mainviewpager);
        indicator = findViewById(R.id.indicator);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.favourites:
                        break;

                    case R.id.info:
                        break;
                }
                return false;
            }
        });

        indicator.setViewPager(viewPager);
        viewPager.startAutoScroll(250);
        ViewPageAdapter adapter1 = new ViewPageAdapter(MainActivity.this,img);
        viewPager.setAdapter(adapter1);

        RecyclerView.LayoutManager manager = new GridLayoutManager(MainActivity.this,2,RecyclerView.VERTICAL,false);
        MainAdapter adapter = new MainAdapter(MainActivity.this,database);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}