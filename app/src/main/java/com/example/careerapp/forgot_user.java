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

public class forgot_user extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button changeuser,backtologin;
    EditText studid,username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_user);

        studid = findViewById(R.id.studentid);
        username = findViewById(R.id.newuser);
        changeuser = findViewById(R.id.changeuser);
        backtologin = findViewById(R.id.backlog);
        //firebaseDatabase = FirebaseDatabase.getInstance("https://careerapp-39b2a-default-rtdb.firebaseio.com/");
        firebaseDatabase = FirebaseDatabase.getInstance("https://ishitaproj-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference();

        changeuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stud_id = studid.getText().toString();
                String user_name = username.getText().toString();

                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(stud_id))
                        {
                            databaseReference.child("Users").child(stud_id).child("user_name").setValue(user_name);

                            Toast.makeText(forgot_user.this, "User Name Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(forgot_user.this, "Entered Student ID is Incorrect", Toast.LENGTH_SHORT).show();
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