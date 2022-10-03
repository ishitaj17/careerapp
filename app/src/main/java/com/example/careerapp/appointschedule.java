package com.example.careerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class appointschedule extends AppCompatActivity {
    EditText appointmonth;
    Button pickmon;
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointschedule);

        appointmonth = findViewById(R.id.monthappoint);
        pickmon = findViewById(R.id.pickmonth);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        pickmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(appointschedule.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, dayofmonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        pickmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(appointschedule.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String dat = (month + 1) + "/" + year;
                        appointmonth.setText(dat);
                    }
                },year,month,dayofmonth);
                datePickerDialog.show();
            }
        });

    }

}