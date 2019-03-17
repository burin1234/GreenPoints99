package com.example.icenmind.greenpoints;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        Button b = findViewById(R.id.btn_Sign_in);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("","666");
                EditText username = findViewById(R.id.username);
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
                DatabaseReference user = database.getReference("Users");
                Query query1 = user.orderByKey().equalTo(username.getText().toString());
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        EditText password = findViewById(R.id.password);
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            Log.d("test", ds.getKey() + " => " + ds.getValue());
                            if(ds.child("Password").getValue().equals(password.getText().toString())){
                                Intent intent = new Intent(Login.this,Index.class);
                                intent.putExtra("id",ds.child("Id").getValue().toString());
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(Login.this,Login.class);
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }

    public void OnClick_Create_new_account(View view){
        Intent intent = new Intent(Login.this,RegisterActivity.class);
        startActivity(intent);
    }

    public void OnClick_sign_in(View view){
        Intent intent = new Intent(Login.this,Index.class);
        startActivity(intent);
        Log.d("test", "");
        EditText username = findViewById(R.id.username);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
        DatabaseReference user = database.getReference("Users");
        Query query1 = user.orderByKey().equalTo(username.getText().toString());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Log.d("test", ds.getKey() + " => " + ds.getValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
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
