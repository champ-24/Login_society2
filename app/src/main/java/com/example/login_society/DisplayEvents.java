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

public class DisplayEvents extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_events);

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("Event");

        user = new User();
        list= new ArrayList<>();

        listView=findViewById(R.id.lv_events);
        floatingActionButton=findViewById(R.id.floatingActionButton4);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisplayEvents.this,register_event.class);
                startActivity(i);
            }
        });



        adapter = new ArrayAdapter<>(this,R.layout.eventhold,R.id.event_info,list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    user = ds.getValue(User.class);
                    list.add(user.getHost().toString()+"\n"+user.getEvent_name().toString()+"\n"+user.getDescription().toString()+"\n "+user.getDate().toString()+" \n"+user.getTime().toString());

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
