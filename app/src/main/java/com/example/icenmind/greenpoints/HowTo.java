package com.example.icenmind.greenpoints;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowTo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        Button b5 = findViewById(R.id.howto);
        b5.setBackgroundResource(R.drawable.menu_hamber2);
    }
    public void btnhome(View v){
        Intent mainIntent = new Intent(HowTo.this, Index.class);
        startActivity(mainIntent); finish();
    }
    public void btnpark(View v){
        Intent mainIntent = new Intent(HowTo.this, Park.class);
        startActivity(mainIntent); finish();
    }
    public void btnabout(View v){
        Intent mainIntent = new Intent(HowTo.this, About.class);
        startActivity(mainIntent); finish();
    }
    public void btncontactus(View v){
        Intent mainIntent = new Intent(HowTo.this, ContactUS.class);
        startActivity(mainIntent); finish();
    }
    public void btnhowto(View v){
        Intent mainIntent = new Intent(HowTo.this, HowTo.class);
        startActivity(mainIntent); finish();
    }
    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(HowTo.this, Index.class);
        startActivity(mainIntent); finish();

    }
}
