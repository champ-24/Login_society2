package com.example.login_society;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class user_complaint extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    complaint_set user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint);



        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("Society").child("Complaint");

        user = new complaint_set();
        list= new ArrayList<>();

        listView=findViewById(R.id.lv_complaint);
        floatingActionButton=findViewById(R.id.floatingActionButton4);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(user_complaint.this, register_complaint.class);
                startActivity(i);
            }
        });


        adapter = new ArrayAdapter<>(this, R.layout.eventhold, R.id.event_info,list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    user = ds.getValue(complaint_set.class);
                    list.add(user.getDescription().toString()+"\n "+user.getDate().toString());

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
