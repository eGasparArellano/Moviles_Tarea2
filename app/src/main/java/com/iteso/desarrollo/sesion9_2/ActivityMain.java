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

import Commons.Constants;
import adapters.SectionsPagerAdapter;
import beans.ItemProduct;

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
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        mSection = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(mSection);

        tabLayout = (TabLayout) findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case Constants.INTENT_PRODUCTS_NOTIFY:
                if(resultCode == RESULT_OK) {
                    if(data != null){
                        ItemProduct itemProduct = data.getParcelableExtra("ITEM");

                        if(itemProduct.getCategory().getName().equalsIgnoreCase("TECHNOLOGY")){
                            mSection.fragmentTechnology.notifyDataSetChanged(itemProduct);
                        }

                        else if(itemProduct.getCategory().getName().equalsIgnoreCase("HOME")){
                            mSection.fragmentHome.notifyDataSetChanged(itemProduct);
                        }

                        else if(itemProduct.getCategory().getName().equalsIgnoreCase("HOME")){
                            mSection.fragmentElectronics.notifyDataSetChanged(itemProduct);
                        }
                    }
                }
                break;

            case Constants.DETAIL_SUBACTIVITY:
                if(resultCode == RESULT_OK){
                    mSection.fragmentTechnology.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }

    // Inflar el XML del menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Qué hacer en caso de presionar los botones del menú
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
            case R.id.action_products:
                Intent products = new Intent(this, ActivityProduct.class);
                startActivityForResult(products, Constants.INTENT_PRODUCTS_NOTIFY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Limpiar las preferencias compartidas para que se pueda hacer el Log Out
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



