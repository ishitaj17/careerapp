package com.example.careerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class admindash extends AppCompatActivity {

    EditText searchtxt;
    Button searchbut, logoutbt;
    RecyclerView rvusers;
    MyAdapter myAdapter;
    List<bookingprof> bookingList = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindash);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Appointment");

        rvusers = findViewById(R.id.rv);
        rvusers.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                bookingList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    bookingprof b = dataSnapshot.getValue(bookingprof.class);
                    bookingList.add(b);
                }
//                bookingList.add(new bookingprof("Mana","1234567890","12","male","12-12-2000"));
                myAdapter = new MyAdapter(bookingList,admindash.this);
                rvusers.setAdapter(myAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchtxt = findViewById(R.id.rsearch);
        searchbut = findViewById(R.id.searchad);
        logoutbt = findViewById(R.id.logoutad);


        //setdata();
        //preparerecyclerview();
    }
}/*
    public void  setdata(){

        firebaseDatabase = FirebaseDatabase.getInstance("https://careerapp-39b2a-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference().child("Appointment");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    fetchclass fetchpr = dataSnapshot1.getValue(fetchclass.class);
                    fetchclassList.add(fetchpr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
/*
        fetchclassList.add(new fetchclass("17-9-2011","Pratik","1234567890","120"));
        fetchclassList.add(new fetchclass("17-11-2010","mNASA","1234qweqe90","20"));
        fetchclassList.add(new fetchclass("7-4-2001","Pasafsdik","1234qwe890","10"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));
        fetchclassList.add(new fetchclass("1-12-2021","Pfdsabjsd","12eqwe7890","12"));

*/
    /*
    public void preparerecyclerview(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        preadapter();
    }

    public void preadapter(){
        adapterclass = new Adapterclass(fetchclassList,this);
        recyclerView.setAdapter(adapterclass);
    }
}*/