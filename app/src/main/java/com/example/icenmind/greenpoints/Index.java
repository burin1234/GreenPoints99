package com.example.icenmind.greenpoints;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.icenmind.greenpoints.ClassAll.Rate_exchange;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity {

    private List<String> ListRe = new ArrayList();
    List<String> listEXhead = new ArrayList<>();
    List<String> moneyEXhead = new ArrayList<>();
    List<String> listEX = new ArrayList<>();
    List<String> moneyEX = new ArrayList<>();
    List<Rate_exchange> rate = new ArrayList<>();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        //header
        Intent intent = getIntent();

        /*-------------------------------------------------------------*/


        FirebaseApp.initializeApp(this);

        Button b1 = findViewById(R.id.homes);
        b1.setBackgroundResource(R.drawable.menu_home2);

        listEXhead.add("ประเภท");
        moneyEXhead.add("ขีด : แต้ม");

//        Log.e("test", "login isSuccessful");
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
//        DatabaseReference myRef = database.getReference("RateExchange");
//        for(int i = 0 ; i < listEX.size() ; i++){
//            DatabaseReference stu1 = myRef.child(""+(i+1));
//            stu1.child("Type").setValue(""+listEX.get(i));
//            stu1.child("Point").setValue(""+moneyEX.get(i));}

        Log.e("test", "Get Rate Exchange isSuccessful");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
        DatabaseReference myRef = database.getReference("RateExchange");
        Query query1 = myRef.orderByKey();
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){listEX.add(ds.child("Type").getValue().toString()+"");
                    moneyEX.add(Double.parseDouble(ds.child("Point").getValue().toString())+"");
                }
                ListView listView = findViewById(R.id.list_rate_garbage);
                layout_rate_garbage Name_unit = new layout_rate_garbage(getApplicationContext(),listEX,moneyEX);
                listView.setAdapter( Name_unit);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        Log.e("size ",rate.size()+"");

        ListView listViewhead = findViewById(R.id.list_rate_garbage_head);
        layout_rate_garbage Name_unithead = new layout_rate_garbage(getApplicationContext(),listEXhead,moneyEXhead);
        listViewhead.setAdapter( Name_unithead);


    }

    public void btnhome(View v){
        Intent mainIntent = new Intent(Index.this, Index.class);
        startActivity(mainIntent);
    }
    public void btnpark(View v){
        Intent mainIntent = new Intent(Index.this, Park.class);
        startActivity(mainIntent);
    }
    public void btnabout(View v){
        Intent mainIntent = new Intent(Index.this, history_exchange_garbage.class);
        startActivity(mainIntent);
    }
    public void btncontactus(View v){
        Intent mainIntent = new Intent(Index.this, history_usepoint.class);
        startActivity(mainIntent);
    }
    public void btnhowto(View v){
        Intent mainIntent = new Intent(Index.this, menuother.class);
        startActivity(mainIntent);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Index.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface dialog, int id) {
                        finishAndRemoveTask();
                        moveTaskToBack(true);
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
