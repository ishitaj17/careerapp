package com.example.careerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText username;
   private EditText password;
   private EditText studeid;
   private Button loginbutton,registerbut,forgotbut,forgotuser,adminbutton;
   // DatePickerDialog.OnDateSetListener setListener;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    userprofile userprof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        registerbut = findViewById(R.id.regbut);
        loginbutton = findViewById(R.id.loginbut);
        forgotuser = findViewById(R.id.forgotuserbut);
        forgotbut = findViewById(R.id.forgotbut);
        studeid = findViewById(R.id.studid);
        adminbutton = findViewById(R.id.adminnbut);
        //firebaseDatabase = FirebaseDatabase.getInstance("https://careerapp-39b2a-default-rtdb.firebaseio.com/");
        firebaseDatabase = FirebaseDatabase.getInstance("https://ishitaproj-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference();


        adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stud_id = studeid.getText().toString();
                String user_name = username.getText().toString();
                String pass_word= password.getText().toString();
                String adminid = "0",adiminusername = "admin",adminpass = "admin@123";

                if(stud_id.equals(adminid) && user_name.equals(adiminusername) && pass_word.equals(adminpass)){
                    Intent intent = new Intent(MainActivity.this, admindash.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Admin Credentials Don't Match", Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, register_page.class);
                startActivity(intent);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stud_id = studeid.getText().toString().trim();
                String user_name = username.getText().toString().trim();
                String pass_word= password.getText().toString().trim();



                        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(stud_id)){
                            String getpassowrd = snapshot.child(stud_id).child("password").getValue(String.class).trim();
                            String getusername = snapshot.child(stud_id).child("user_name").getValue(String.class).trim();
                            String fullname = snapshot.child(stud_id).child("fullname").getValue(String.class).trim();
                            String phoneno = snapshot.child(stud_id).child("phoneno").getValue(String.class).trim();
                            String gen = snapshot.child(stud_id).child("gender").getValue(String.class).trim();

//                            Toast.makeText(MainActivity.this, getpassowrd + " " + getusername, Toast.LENGTH_SHORT).show();
//                            Log.d("TAG123", getpassowrd + " " + pass_word + " " + String.valueOf(getpassowrd.equals(pass_word)));

                            if(getusername.equals(user_name) && getpassowrd.equals(pass_word)){
                                Toast.makeText(MainActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(MainActivity.this,booking_page.class);
                                in.putExtra("full_name",fullname);
                                in.putExtra("phone",phoneno);
                                in.putExtra("stud-id",stud_id);
                                in.putExtra("gend",gen);
                                startActivity(in);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Please Ensure that the User Name and Password are Correct", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "The STUDENT ID entered is Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        forgotbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,forgot_pass.class);
                startActivity(in);
            }
        });

        forgotuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,forgot_user.class);
                startActivity(in);
            }
        });

    }

}