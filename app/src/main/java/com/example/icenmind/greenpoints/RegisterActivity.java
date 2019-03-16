package com.example.icenmind.greenpoints;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    private static final int REQ_CODE = 123;

    private  String FirstName="";
    private  String LastName="";
    private  String gender="";
    private  String Birthday="";
/*------------------------------------     ยังไม่ได้ดัก    --------------------------------*/
    private  String photo="";
/*--------------------------------------------------------------------------------------*/
    private  String tel="";
    private  String email="";
    private  String username="";
    private  String password="";
    private  String confirmpassword="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



/*------------------------------------------------------------------------------------------------------------------*/
        Button btnSignup = (Button) findViewById(R.id.btn_Signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText FirstNames = findViewById(R.id.txtFirstName);
                FirstName = FirstNames.getText().toString();

                EditText LNames = findViewById(R.id.txtLastName);
                LastName = LNames.getText().toString();

                RadioButton genderRa1 = findViewById(R.id.m);
                RadioButton genderRa2 = findViewById(R.id.fm);
                //intent.putExtra("Choice", Ra3.getText().toString());

                EditText bd = findViewById(R.id.txtBirthday);
                Birthday = bd.getText().toString();

                EditText tell = findViewById(R.id.txttel);
                tel = tell.getText().toString();

                EditText emails = findViewById(R.id.txtmail);
                email = emails.getText().toString();

                EditText usernames = findViewById(R.id.txtusername);
                username = usernames.getText().toString();

                EditText passwords = findViewById(R.id.txtpassword);
                password = passwords.getText().toString();

                EditText confirmpasswords = findViewById(R.id.txtconfirmpass);
                confirmpassword = confirmpasswords.getText().toString();

                if("".equals(FirstName) ){
                    ShowAlert(1);
                }
                else if("".equals(LastName) ){
                    ShowAlert(2);
                }
                else if(!genderRa1.isChecked() && !genderRa2.isChecked()){
                    ShowAlert(3);
                }

                else if("".equals(Birthday) ){
                    ShowAlert(4);
                }
                else if(tel.length()!= 10){
                    ShowAlert(5);
                }

                else if("".equals(email) ){
                    ShowAlert(6);
                }

/*              --------------------------------------   เชค รูป ---------------------------------------------------
                else if("".equals(LastName) ){
                    ShowAlert(7);
                }
*/
                else if("".equals(username) ){
                    ShowAlert(8);
                }
                else if("".equals(password) ){
                    ShowAlert(9);
                }
                else if("".equals(confirmpassword) ){
                    ShowAlert(10);
                }
                else{
                    ShowAlert(11);

                    Intent intent = new Intent(RegisterActivity.this,Login.class);
                    startActivity(intent);
                }


                Log.e("test", "login isSuccessful");
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
                DatabaseReference user = database.getReference("Users");
                DatabaseReference id = user.child("10000001");
                id.child("Password").setValue(""+password);
                id.child("userType").setValue("1");
                id.child("Username").setValue(""+username);

                FirebaseDatabase databasea = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
                DatabaseReference tourist = databasea.getReference("Tourist");
                DatabaseReference tid = tourist.child("10000001");
                tid.child("Birthday").setValue(""+Birthday);
                tid.child("Email").setValue(""+email);
                String genders = null;
                if(genderRa1.isChecked()){ genders = genderRa1.getText().toString();}
                    else if(genderRa2.isChecked()){ genders = genderRa2.getText().toString();}
                tid.child("Gender").setValue(""+genders);
                tid.child("FirstName").setValue(""+FirstName);
                tid.child("LastName").setValue(""+LastName);

                //Photo Upload

                //end Photo Upload

                tid.child("Point").setValue("0");
                tid.child("Tel").setValue(""+tel);

            }

        });
/*------------------------------------------------------------------------------------------------------------------------*/

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
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(RegisterActivity.this);
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


    public void ShowAlert(int check){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.attention);
        builder.setCancelable(false);
        builder.setTitle(" ----- Alert ----- ");

        if(check == 1){
            builder.setMessage("Please input FirstName");
        }
        else if(check == 2){
            builder.setMessage("Please input LastName");
        }
        else if(check == 3){
            builder.setMessage(" Please Select Gender ");
        }
        else if(check == 4){
            builder.setMessage("please input Birthday ");
        }
        else if(check == 5){
            builder.setMessage("please input Telephone number 10 digits ");
        }
        else if(check == 6){
            builder.setMessage("please input E-Mail ");
        }
        else if(check == 7){
            builder.setMessage("please Add a Photo ");
        }
        else if(check == 8){
             builder.setMessage("please input Username ");
        }
        else if(check == 9){
            builder.setMessage("please input Password ");
        }
        else if(check == 10){
            builder.setMessage("please Confirm Password ");
        }
        else{
            builder.setIcon(R.drawable.correct);
            builder.setMessage(" Successfully ");
        }

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }


    public void btnTakePhoto(View view){
        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(picIntent, REQ_CODE);
    }

    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if (reqCode == REQ_CODE && resCode == RESULT_OK) {
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ImageView img = (ImageView) findViewById(R.id.img_user);
            img.setVisibility(View.VISIBLE);
            img.setImageBitmap(bmp);
            Toast.makeText(this, "path"+img.toString(), Toast.LENGTH_LONG).show();
            Log.e("","");
        }
    }

//  public void OnClick_sign_up(View view){
//      EditText FirstNames = findViewById(R.id.txtFirstName);
//      FirstName = FirstNames.getText().toString();
//
//      EditText LNames = findViewById(R.id.txtLastName);
//      LastName = LNames.getText().toString();
//
//      RadioGroup gender = findViewById(R.id.gender);
//      RadioButton genderRa1 = findViewById(R.id.m);
//      RadioButton genderRa2 = findViewById(R.id.fm);
//      //intent.putExtra("Choice", Ra3.getText().toString());
//
//      EditText bd = findViewById(R.id.txtBirthday);
//      Birthday = bd.getText().toString();
//
//      EditText tell = findViewById(R.id.txttel);
//      tel = tell.getText().toString();
//
//      EditText emails = findViewById(R.id.txtmail);
//      email = emails.getText().toString();
//
//      EditText usernames = findViewById(R.id.txtusername);
//      username = usernames.getText().toString();
//
//      EditText passwords = findViewById(R.id.txtpassword);
//      password = passwords.getText().toString();
//
//      EditText confirmpasswords = findViewById(R.id.txtconfirmpass);
//      confirmpassword = confirmpasswords.getText().toString();
//
//      Log.e("test", "login isSuccessful");
//      FirebaseDatabase database = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
//      DatabaseReference user = database.getReference("Users");
//      DatabaseReference id = user.child("10000001");
//      id.child("Password").setValue(""+password);
//      id.child("userType").setValue("1");
//      id.child("Username").setValue(""+username);
//
//      DatabaseReference tourist = database.getReference("Users");
//      DatabaseReference tid = tourist.child("10000001");
//      tid.child("Birthday").setValue(""+Birthday);
//      tid.child("Email").setValue(""+email);
//      tid.child("gender").setValue("");
//
////      stu1.child("Point").setValue("");
//        Intent intent = new Intent(RegisterActivity.this,Login.class);
//        startActivity(intent);
//    }


}
