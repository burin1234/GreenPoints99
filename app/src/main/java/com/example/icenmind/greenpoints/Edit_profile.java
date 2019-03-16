package com.example.icenmind.greenpoints;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Edit_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
    public void onClickDateBirthday(View view) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(   year <= c.get(Calendar.YEAR)&&
                        monthOfYear >=  c.get(Calendar.MONTH) &&
                        dayOfMonth <= c.get(Calendar.DAY_OF_MONTH)){

                    EditText txtDate = findViewById(R.id.txtBirthday);
                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
                else{
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Edit_profile.this);
                    builder.setIcon(R.drawable.attention);
                    builder.setCancelable(false);
                    builder.setTitle(" ----- Alert ----- ");
                    builder.setMessage("Please select a date less than the current.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    android.support.v7.app.AlertDialog alert = builder.create();
                    alert.show();

                }

            }
        }, mYear, mMonth, mDay);
        dpd.show();
    }
}
