package com.example.careerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import kotlin.jvm.internal.Lambda;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdaptervh> {

    public List<bookingprof> bookingmodel = new ArrayList<>();
    public Context context;

    public MyAdapter(List<bookingprof> usermodel, Context context1){
        this.bookingmodel = usermodel;
        this.context = context1;
    }


    @NonNull
    @Override
    public MyAdaptervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardholder,parent,false);

        return new MyAdaptervh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptervh holder, int position) {
    bookingprof bookingprof1 = bookingmodel.get(position);
    String date_time = bookingprof1.getDate_timeofapppoint();
    String name1 = bookingprof1.getFullname();
    String gende = bookingprof1.getGender();
    String numph = bookingprof1.getPhoneno();
    String ids = bookingprof1.getStudentid();

    holder.dat_time.setText(date_time);
    holder.name.setText(name1);
    holder.gend.setText(gende);
    holder.numphone.setText(numph);
    holder.idstude.setText(ids);

    holder.delBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            DatabaseReference db = FirebaseDatabase.getInstance().getReference();
            db.child("Appointment").child(date_time).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {

                        notifyDataSetChanged();
                    }
                }
            });

        }
    });

    }

    @Override
    public int getItemCount() {
        return bookingmodel.size();
    }

    public static class MyAdaptervh extends RecyclerView.ViewHolder{

        private TextView dat_time,name,gend,idstude,numphone;
        private ImageView delBtn;
        public MyAdaptervh(@NonNull View itemView) {
            super(itemView);
            dat_time = itemView.findViewById(R.id.appointdat_time);
            name = itemView.findViewById(R.id.fullname);
            gend = itemView.findViewById(R.id.gen_der);
            idstude = itemView.findViewById(R.id.idstud);
            numphone = itemView.findViewById(R.id.phoneno);
            delBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
