package com.example.gujaratirecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class ThirdActivity extends AppCompatActivity {

    ImageView back,image,image2;
    TextView recepi;
    TabLayout tabLayout;
    ViewPager viewPager;
    public static String samgri;
    public static String rit;

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image2.setImageResource(getIntent().getExtras().getInt("image2"));
        recepi.setText(getIntent().getStringExtra("name"));

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

        samgri = (getIntent().getStringExtra("sahitya"));
        rit = (getIntent().getStringExtra("kruti"));

    }
}