package com.example.icenmind.greenpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OnClick_Create_new_account(View view){
        Intent intent = new Intent(Login.this,RegisterActivity.class);
        startActivity(intent);
    }

    public void OnClick_sign_in(View view){
        Intent intent = new Intent(Login.this,Index.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(Login.this, Index.class);
        startActivity(mainIntent); finish();

    }
}
