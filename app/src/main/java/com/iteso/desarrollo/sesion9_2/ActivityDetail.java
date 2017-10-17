package com.iteso.desarrollo.sesion9_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import beans.ItemProduct;

public class ActivityDetail extends AppCompatActivity {
    private ItemProduct product;

    protected ImageView image;
    protected EditText title;
    protected EditText store;
    protected EditText location;
    protected Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = (ImageView) findViewById(R.id.activity_detail_image);
        title = (EditText) findViewById(R.id.activity_detail_title);
        store = (EditText) findViewById(R.id.activity_detail_store);
        location = (EditText) findViewById(R.id.activity_detail_location);
        save = (Button) findViewById(R.id.activity_detail_save);

        product = getIntent().getParcelableExtra("ITEM");

        store.setText(product.getStore().getName());
        title.setText(product.getTitle());
        location.setText(product.getStore().getCity().getName());

        switch (product.getImage()){
            case 0:
                image.setImageResource(R.drawable.mac);
                break;
            case 1:
                image.setImageResource(R.drawable.alienware);
                break;
        }
    }

    public void onClick(View view){
        if(view.getId() == R.id.activity_detail_save){
            product.setTitle(title.getText().toString());
            product.getStore().setName(store.getText().toString());
            product.getStore().getCity().setName(location.getText().toString());

            Intent intent = new Intent();
            intent.putExtra("ITEM", product);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
