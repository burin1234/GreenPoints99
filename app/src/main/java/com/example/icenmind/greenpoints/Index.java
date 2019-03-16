package com.example.icenmind.greenpoints;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity {

    private List<String> ListRe = new ArrayList();
    List<String> listEX = new ArrayList<>();
    List<String> moneyEX = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Button b1 = findViewById(R.id.homes);
        b1.setBackgroundResource(R.drawable.menu_home2);

        listEX.add("ประเภท");
        moneyEX.add("ขีด : แต้ม");

        listEX.add("ขวดพลาสติก");
        moneyEX.add("3");

        listEX.add("ขวดแก้ว");
        moneyEX.add("6");

        listEX.add("อื่นๆ");
        moneyEX.add("1");


        ListView listView = findViewById(R.id.list_rate_garbage);
        layout_rate_garbage Name_unit = new layout_rate_garbage(getApplicationContext(),listEX,moneyEX);
        listView.setAdapter( Name_unit);


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
        Intent mainIntent = new Intent(Index.this, About.class);
        startActivity(mainIntent);
    }
    public void btncontactus(View v){
        Intent mainIntent = new Intent(Index.this, ContactUS.class);
        startActivity(mainIntent);
    }
    public void btnhowto(View v){
        Intent mainIntent = new Intent(Index.this, HowTo.class);
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
