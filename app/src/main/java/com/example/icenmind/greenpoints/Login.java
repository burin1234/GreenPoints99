package com.example.icenmind.greenpoints;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.icenmind.greenpoints.ClassAll.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    List<Users> user = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e("","55555");
    }

    public void OnClick_Create_new_account(View view){
        Intent intent = new Intent(Login.this,RegisterActivity.class);
        startActivity(intent);
    }

    public void OnClick_sign_in(View view){
        Intent intent = new Intent(Login.this,Index.class);
        startActivity(intent);
//        for(Users u : user){
//            if(u.getUsername().equals(""+username.getText())){
//                if(u.getPassword().equals(""+password.getText())){
//                    Intent intent = new Intent(Login.this,Index.class);
//                    Log.e("IDUSER ",""+u.getUserID());
//                    Bundle ePzl= new Bundle();
//                    ePzl.putString("iduser", ""+u.getUserID());
//                    intent.putExtra("id",u.getUserID());
//                    startActivity(intent);
//                }
//                else{
//
//                    Intent intent = new Intent(Login.this,Login.class);
//                    startActivity(intent);
//                }
//            } else{
//
//                Intent intent = new Intent(Login.this,Login.class);
//                startActivity(intent);
//            }}
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(Login.this, Login.class);
        startActivity(mainIntent); finish();

    }
}
