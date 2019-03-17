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
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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
    String mdownloaduri = null;

    private Button btnChoose;
    private ImageView imageView;

    private  String id="0";
    private  int idregis=0;

    private Uri filePath;
    String image = "";

    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("ilux1122@gmail.com", "ilux1122").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                }
            }
        });
/*----------------------------------------------------------------------------------------------------------------*/
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
        DatabaseReference user = database.getReference("Users");
        Query query1 = user.orderByKey();
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    id= ds.getKey();
                }

                Log.e("q : ",id);

                Log.e("ID Regis",""+((Integer.parseInt(id.toString()))+1));

                idregis = ((Integer.parseInt(id.toString()))+1);
                if(idregis == 1){
                    idregis = 10000001;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        btnChoose = (Button) findViewById(R.id.btnChoose);
        imageView = (ImageView) findViewById(R.id.imgView);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
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
                Log.e("ID Register",""+idregis);
                DatabaseReference Username = user.child(""+username);
                Username.child("Password").setValue(""+password);
                Username.child("userType").setValue("1");
                Username.child("Id").setValue(""+idregis);

                FirebaseDatabase databasea = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
                DatabaseReference tourist = databasea.getReference("Tourist");
                DatabaseReference tid = tourist.child(""+idregis);
                tid.child("Birthday").setValue(""+Birthday);
                tid.child("Email").setValue(""+email);
                String genders = null;
                if(genderRa1.isChecked()){ genders = genderRa1.getText().toString();}
                    else if(genderRa2.isChecked()){ genders = genderRa2.getText().toString();}
                tid.child("Gender").setValue(""+genders);
                tid.child("FirstName").setValue(""+FirstName);
                tid.child("LastName").setValue(""+LastName);

                //Photo Upload
                uploadImage();
                Log.e("Photo : " ,""+image);
                //end Photo Upload

                tid.child("Point").setValue("0");
                tid.child("Tel").setValue(""+tel);

            }

        });
    }
/*------------------------------------------------------------------------------------------------------------------------*/
        private void uploadImage() {

            Task<Uri> urltask = null;
            if(filePath != null){
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
                UploadTask uploadTask = ref.putFile(filePath);
                urltask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw task.getException();
                        }
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            mdownloaduri = task.getResult().toString();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                            /**/
                            FirebaseDatabase databasea = FirebaseDatabase.getInstance("https://greenpoints-it411.firebaseio.com/");
                            DatabaseReference tourist = databasea.getReference("Tourist");

                            DatabaseReference tid = tourist.child(""+idregis);

                            tid.child("Photo").setValue(""+mdownloaduri);
                            /**/
                        }
                    }
                });
//                ref.putFile(filePath)
//                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                progressDialog.dismiss();
//                                Toast.makeText(RegisterActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                                image = taskSnapshot.getDownloadUrl().toString();
//                                Log.e("Photossss : ",image);
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                progressDialog.dismiss();
//                                Toast.makeText(RegisterActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
//                                        .getTotalByteCount());
//                                progressDialog.setMessage("Uploaded "+(int)progress+"%");
//                            }
//                        });

                Log.e("Photo : " ,""+image);
            }
        }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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


//    public void btnTakePhoto(View view){
//        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(picIntent, REQ_CODE);
//    }
//
//    protected void onActivityResult(int reqCode, int resCode, Intent data) {
//        if (reqCode == REQ_CODE && resCode == RESULT_OK) {
//            Bitmap bmp = (Bitmap) data.getExtras().get("data");
//            ImageView img = (ImageView) findViewById(R.id.img_user);
//            img.setVisibility(View.VISIBLE);
//            img.setImageBitmap(bmp);
//            Toast.makeText(this, "path"+img.toString(), Toast.LENGTH_LONG).show();
//            Log.e("","");
//        }
//    }

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
