package com.example.icenmind.greenpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuother extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuother);
        Button b5 = findViewById(R.id.howto);
        b5.setBackgroundResource(R.drawable.menu_hamber2);


    }

    public void OnClick_editprofile(View view){
        Intent intent = new Intent(menuother.this,Edit_profile.class);
        startActivity(intent);
    }


    public void btnhome(View v){
        Intent mainIntent = new Intent(menuother.this, Index.class);
        startActivity(mainIntent); finish();
    }
    public void btnpark(View v){
        Intent mainIntent = new Intent(menuother.this, Park.class);
        startActivity(mainIntent); finish();
    }
    public void btnabout(View v){
        Intent mainIntent = new Intent(menuother.this, history_exchange_garbage.class);
        startActivity(mainIntent); finish();
    }
    public void btncontactus(View v){
        Intent mainIntent = new Intent(menuother.this, history_usepoint.class);
        startActivity(mainIntent); finish();
    }
    public void btnhowto(View v){
        Intent mainIntent = new Intent(menuother.this, menuother.class);
        startActivity(mainIntent); finish();
    }
    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(menuother.this, Index.class);
        startActivity(mainIntent); finish();

    }
}
