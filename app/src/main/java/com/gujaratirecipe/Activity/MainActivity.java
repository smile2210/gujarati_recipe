package com.gujaratirecipe.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.gujaratirecipe.BuildConfig;
import com.gujaratirecipe.Database;
import com.gujaratirecipe.Adapter.MainAdapter;
import com.gujaratirecipe.R;
import com.gujaratirecipe.Adapter.ViewPageAdapter;
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
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(MainActivity.this,FavActivity.class));
                        break;

                    case R.id.share:
                        Intent intent =new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Android Studio Pro");
                        intent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                        intent.setType("text/plain");
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        indicator.setViewPager(viewPager);
        ViewPageAdapter adapter1 = new ViewPageAdapter(MainActivity.this,img);
        viewPager.setAdapter(adapter1);
        viewPager.startAutoScroll();

        RecyclerView.LayoutManager manager = new GridLayoutManager(MainActivity.this,2,RecyclerView.VERTICAL,false);
        MainAdapter adapter = new MainAdapter(MainActivity.this,database);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}