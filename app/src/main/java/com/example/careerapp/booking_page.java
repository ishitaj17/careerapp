package com.example.careerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class booking_page extends AppCompatActivity {

    TextView txtdate, appointdat;
    TextView appointtxt, appointtime;
    Button timebut, datbut, bookslot,nextnavig;
    EditText fullname,phone_no,studentid,gen_der;
    DatePickerDialog.OnDateSetListener setListener;
    bookingprof newbooking_profile;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        txtdate = findViewById(R.id.datetext);
        datbut = findViewById(R.id.datebutton);
        appointdat = findViewById(R.id.appointdate);
        appointtxt = findViewById(R.id.appointtxt);
        appointtime = findViewById(R.id.appointtime);
        timebut = findViewById(R.id.timebutton);
        bookslot = findViewById(R.id.bookslot);
      //  nextnavig = findViewById(R.id.nextnav);
        fullname = findViewById(R.id.nameetext);
        phone_no = findViewById(R.id.phonetext);
        studentid = findViewById(R.id.studid);
        gen_der = findViewById(R.id.gender);

        //firebaseDatabase = FirebaseDatabase.getInstance("https://careerapp-39b2a-default-rtdb.firebaseio.com/");
        firebaseDatabase = FirebaseDatabase.getInstance("https://ishitaproj-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference();

        String full_name = getIntent().getStringExtra("full_name");
        String phoneno = getIntent().getStringExtra("phone");
        String stud_id = getIntent().getStringExtra("stud-id");
        String gender = getIntent().getStringExtra("gend");

        fullname.setText(full_name);
        phone_no.setText(phoneno);
        studentid.setText(stud_id);
        gen_der.setText(gender);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        final int[] t1hour = new int[1];
        final int[] t1minute = new int[1];

        datbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(booking_page.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, dayofmonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        timebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(booking_page.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1hour[0] = hourOfDay;
                                t1minute[0] = minute;
                                String time = t1hour[0] + ":" + t1minute[0];
                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                    appointtime.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }

                        }, 12, 0, false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }

        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                String curdat = day + "-" + month + "-" + year;
                appointdat.setText(curdat);
            }
        };


        datbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(booking_page.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String dat = day + "-" + (month + 1) + "-" + year;
                        appointdat.setText(dat);
                    }
                }, year, month, dayofmonth);
                datePickerDialog.show();
            }
        });

        bookslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateofappoint = appointdat.getText().toString();
                String timeofappoint = appointtime.getText().toString();
                if (dateofappoint.length() == 0 && timeofappoint.length() == 0) {
                    Toast.makeText(booking_page.this, "Please Select Date and Time of Appointment", Toast.LENGTH_SHORT).show();
                } else if (dateofappoint.length() != 0 && timeofappoint.length() == 0) {
                    Toast.makeText(booking_page.this, "Please Select Time of Appointment", Toast.LENGTH_SHORT).show();
                } else if (dateofappoint.length() == 0 && timeofappoint.length() != 0) {
                    Toast.makeText(booking_page.this, "Please Select Date of Appointment", Toast.LENGTH_SHORT).show();
                } else {

                    String date_timeofappoint = dateofappoint + " @ " + timeofappoint;
                    databaseReference.child("Appointment").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(date_timeofappoint)){
                                Toast.makeText(booking_page.this, "This Time Slot has Already been Taken , Please try for another time slot", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                newbooking_profile = new bookingprof(full_name,phoneno,stud_id,gender,date_timeofappoint);
                                databaseReference.child("Appointment").child(date_timeofappoint).setValue(newbooking_profile);

                                String appointment = "Your Appointment is scheduled on " + dateofappoint + " at " + timeofappoint;
                                Toast.makeText(booking_page.this, appointment, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }


}