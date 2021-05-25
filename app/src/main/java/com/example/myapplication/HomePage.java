package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button bFish,bAmphi,bRept,bMammal,bBird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bFish=findViewById(R.id.fish);
        bAmphi=findViewById(R.id.amphibian);
        bRept=findViewById(R.id.reptile);
        bMammal=findViewById(R.id.mammal);
        bBird=findViewById(R.id.bird);

        bFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FishesActivity.class));
            }
        });


        bAmphi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AmphibianActivity.class));
            }
        });


        bRept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ReptileActivity.class));
            }
        });

        bMammal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MammalActivity.class));
            }
        });

        bBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BirdActivity.class));
            }
        });

    }
}