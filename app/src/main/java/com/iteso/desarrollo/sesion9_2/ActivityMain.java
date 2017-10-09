package com.iteso.desarrollo.sesion9_2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import adapters.SectionsPagerAdapter;
import static Commons.Commons.*;

public class ActivityMain extends AppCompatActivity {
    protected Toolbar toolbar;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    private SectionsPagerAdapter mSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        mSection = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(mSection);

        tabLayout = (TabLayout) findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == DETAIL_SUBACTIVITY){
            if(resultCode == RESULT_OK){
                mSection.fragmentTechnology.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.activity_main_logout:
                clearPreferences();
                break;

            case R.id.activity_main_privacy_policy:
                intent = new Intent(this, ActivityPrivacyPolicy.class);
                startActivity(intent);
                break;

            case R.id.activity_main_EULA:
                intent = new Intent(this, ActivityEULA.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.desarrollo.sesion9_2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();

        Intent intent = new Intent(this, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        finish();
    }
}



