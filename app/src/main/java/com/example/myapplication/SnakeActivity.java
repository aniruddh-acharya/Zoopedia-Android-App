package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SnakeActivity extends AppCompatActivity {

    ImageView imageView;
    TextView info;

    private FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();
    private DatabaseReference first=databaseReference.child("snake");
    private DatabaseReference txt=databaseReference.child("snakeText");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);

        imageView = findViewById(R.id.img);
        info = findViewById(R.id.snakeInfo);

    }



    @Override
    protected void onStart() {
        super.onStart();
        first.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(String.class)!=null)
                {
                    String textHolder = snapshot.getValue(String.class);
                    info.setText(textHolder);
                    info.setMovementMethod(new ScrollingMovementMethod());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}