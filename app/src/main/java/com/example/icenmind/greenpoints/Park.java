package com.example.icenmind.greenpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Park extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        Button b2 = findViewById(R.id.park);
        b2.setBackgroundResource(R.drawable.menu_park2);
    }
    public void btnhome(View v){
        Intent mainIntent = new Intent(Park.this, Index.class);
        startActivity(mainIntent); finish();
    }
    public void btnpark(View v){
        Intent mainIntent = new Intent(Park.this, Park.class);
        startActivity(mainIntent); finish();
    }
    public void btnabout(View v){
        Intent mainIntent = new Intent(Park.this, history_exchange_garbage.class);
        startActivity(mainIntent); finish();
    }
    public void btncontactus(View v){
        Intent mainIntent = new Intent(Park.this, history_usepoint.class);
        startActivity(mainIntent); finish();
    }
    public void btnhowto(View v){
        Intent mainIntent = new Intent(Park.this, menuother.class);
        startActivity(mainIntent); finish();
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(Park.this, Index.class);
        startActivity(mainIntent); finish();

    }
}
