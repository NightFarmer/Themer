package com.nightfarmer.themer.sample;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nightfarmer.themer.Themer;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle("主题示例");

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_theme: {
                        startActivity(new Intent(MainActivity.this, ThemeChooseActivity.class));
                    }
                    default: {

                    }
                }
                item.setChecked(true);
                return true;
            }
        });

        ((ImageView) navigation_view.getHeaderView(0).findViewById(R.id.iv_logo)).setColorFilter(Color.WHITE);

        Bundle cachedData = getIntent().getBundleExtra(Themer.INSTANCE.getCachedDataKey());
        if (cachedData != null) {
            Log.i("chachDataTest", "data: " + cachedData.getString("cachedStr"));
        }
    }

    public void changeThem1(View v) {
        Bundle cacheData = new Bundle();
        cacheData.putString("cachedStr", "hello theme !");
        Themer.INSTANCE.setTheme(this, R.style.Theme_Blue, cacheData);
    }

    public void changeThem2(View v) {
        Themer.INSTANCE.setThemeSoft(this, R.style.Theme_Red, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation_view.getMenu().getItem(0).setChecked(true);
    }
}
