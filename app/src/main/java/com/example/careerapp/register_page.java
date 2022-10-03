package com.example.careerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register_page extends AppCompatActivity {

    private EditText user_name,pass_word,phone_no,gender,stud_id,fullname;
    private Button register,backtologin;
    Newprofile newprofile;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        fullname = findViewById(R.id.fullname);
        user_name = findViewById(R.id.user);
        stud_id = findViewById(R.id.studentid);
        pass_word = findViewById(R.id.pass);
        phone_no = findViewById(R.id.phoneno);
        gender = findViewById(R.id.gend);
        register = findViewById(R.id.regbut);
        backtologin = findViewById(R.id.backtologbut);

        //firebaseDatabase = FirebaseDatabase.getInstance("https://careerapp-39b2a-default-rtdb.firebaseio.com/");
        firebaseDatabase = FirebaseDatabase.getInstance("https://ishitaproj-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user_name.getText().toString();
                String password = pass_word.getText().toString();
                String studentid = stud_id.getText().toString();
                String gend = gender.getText().toString();
                String phoneno = phone_no.getText().toString();
                String name = fullname.getText().toString();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(studentid) || TextUtils.isEmpty(gend) || TextUtils.isEmpty(phoneno))
                {
                    Toast.makeText(register_page.this,"Please Ensure that all fields are filled",Toast.LENGTH_SHORT).show();
                }
                else {

                    newprofile = new Newprofile(name,studentid,username,password,gend,phoneno);
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(studentid)){
                                Toast.makeText(register_page.this, "Student ID is already Registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("Users").child(studentid).setValue(newprofile);
                                Toast.makeText(register_page.this, "You are Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}