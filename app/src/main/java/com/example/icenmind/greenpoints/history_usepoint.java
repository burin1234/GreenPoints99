package com.example.icenmind.greenpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class history_usepoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_usepoint);
        Button b4 = findViewById(R.id.contactus);
        b4.setBackgroundResource(R.drawable.menu_histroly_usepoint2);
    }
    public void btnhome(View v){
        Intent mainIntent = new Intent(history_usepoint.this, Index.class);
        startActivity(mainIntent); finish();
    }
    public void btnpark(View v){
        Intent mainIntent = new Intent(history_usepoint.this, Park.class);
        startActivity(mainIntent); finish();
    }
    public void btnabout(View v){
        Intent mainIntent = new Intent(history_usepoint.this, history_exchange_garbage.class);
        startActivity(mainIntent); finish();
    }
    public void btncontactus(View v){
        Intent mainIntent = new Intent(history_usepoint.this, history_usepoint.class);
        startActivity(mainIntent); finish();
    }
    public void btnhowto(View v){
        Intent mainIntent = new Intent(history_usepoint.this, menuother.class);
        startActivity(mainIntent); finish();
    }
    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(history_usepoint.this, Index.class);
        startActivity(mainIntent); finish();

    }
}
