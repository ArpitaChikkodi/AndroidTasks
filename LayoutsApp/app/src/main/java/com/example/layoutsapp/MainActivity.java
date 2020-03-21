package com.example.layoutsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

/*
    This application demonstrates 4 types of layouts => Relative Layout,Frame Layout,Constraint Layout and TabLayout
    It has a ViewPager which will change dynamically the activity/layout when clicked on tabitems
    It also has a floating button which takes user to new activity!
 */

public class MainActivity extends AppCompatActivity {

    TextView info;
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.tv_info);

        info.setText("Click on the tabitems for different layouts and floating button to move to different activity!");
        fbutton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("RelativeLayout"));
        tabLayout.addTab(tabLayout.newTab().setText("FrameLayout"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
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

        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,NewActivity.class);
                startActivity(intent1);
            }
        });


    }
}
