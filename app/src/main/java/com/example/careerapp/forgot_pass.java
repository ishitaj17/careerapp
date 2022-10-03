package com.example.careerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.util.Map;

public class forgot_pass extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button changepass,backtologin;
    EditText studid,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        studid = findViewById(R.id.studentid);
        password = findViewById(R.id.newpass);
        changepass = findViewById(R.id.changebut);
        backtologin = findViewById(R.id.backtologbut);
       // firebaseDatabase = FirebaseDatabase.getInstance("https://careerapp-39b2a-default-rtdb.firebaseio.com/");
        firebaseDatabase = FirebaseDatabase.getInstance("https://ishitaproj-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference();

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stud_id = studid.getText().toString();
                String pass_word = password.getText().toString();

                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(stud_id))
                        {
                            databaseReference.child("Users").child(stud_id).child("password").setValue(pass_word);

                            Toast.makeText(forgot_pass.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(forgot_pass.this, "Entered Student ID is Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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