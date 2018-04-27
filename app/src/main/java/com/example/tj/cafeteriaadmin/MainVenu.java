package com.example.tj.cafeteriaadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainVenu extends AppCompatActivity{
    ImageView addImageView, deleteImageView, updateImageView, menuImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_venu);

        addImageView =(ImageView)findViewById(R.id.imageView1);
        deleteImageView =(ImageView)findViewById(R.id.imageView2);
        updateImageView =(ImageView)findViewById(R.id.imageView3);
        menuImageView =(ImageView)findViewById(R.id.imageView4);

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainVenu.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainVenu.this,DeleteActivity.class);
                startActivity(intent);
            }
        });

    }


}
