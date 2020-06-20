package com.example.login_society;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class home_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView events,notices,complaints,activity_name;
    RecyclerView rec_events,rec_notices,rec_complaints;

    DatabaseReference reference;

    FirebaseRecyclerOptions<Event_helper> options;
    FirebaseRecyclerOptions<notice> notice_options;
    FirebaseRecyclerOptions<complaint> complaint_options;
    FirebaseRecyclerAdapter<complaint,ComplaintViewHolder> complaint_adapter;
    FirebaseRecyclerAdapter<Event_helper,EventViewHolder> event_adapter;
    FirebaseRecyclerAdapter<notice,NoticeViewHolder> notice_adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        Initialization();
        
        activity_name.setText("Home");

        NavigationSupport();

        EventsHandler();

        NoticesHandler();
        
        ComplaintHandler();

    }

    private void ComplaintHandler() {
        reference=FirebaseDatabase.getInstance().getReference().child("Society").child("Complaint");
        complaint_options=new FirebaseRecyclerOptions.Builder<complaint>().setQuery(reference,complaint.class).build();
        complaint_adapter=new FirebaseRecyclerAdapter<complaint, ComplaintViewHolder>(complaint_options) {
            @Override
            protected void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position, @NonNull complaint model) {
                holder.description.setText(model.getDescription());
                holder.date.setText(model.getDate());
            }

            @NonNull
            @Override
            public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_card,parent,false);
                return new ComplaintViewHolder(view);
            }
        };
        complaint_adapter.startListening();
        rec_complaints.setAdapter(complaint_adapter);
    }

    private void Initialization() {
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_drawer);
        toolbar=findViewById(R.id.toolbar);
        activity_name=findViewById(R.id.activity_name);

        events=findViewById(R.id.text_upcoming_events);
        notices=findViewById(R.id.text_notices);
        complaints=findViewById(R.id.text_complaints);

        rec_events=findViewById(R.id.rec_upcoming_events);
        rec_events.setHasFixedSize(true);
        rec_events.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        rec_notices=findViewById(R.id.rec_notices);
        rec_notices.setHasFixedSize(true);
        rec_notices.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        rec_complaints=findViewById(R.id.rec_complaints);
        rec_complaints.setHasFixedSize(true);
        rec_complaints.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }
    public void EventsHandler(){

        reference= FirebaseDatabase.getInstance().getReference().child("Society").child("Event");
        options= new FirebaseRecyclerOptions.Builder<Event_helper>().setQuery(reference,Event_helper.class).build();
        event_adapter= new FirebaseRecyclerAdapter<Event_helper, EventViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Event_helper model) {

                holder.event_name.setText(model.getEvent_name());
                holder.host.setText(model.getHost());
                holder.date.setText(model.getDate());
            }

            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.events_card,parent,false);
                return new EventViewHolder(view);
            }
        };

        event_adapter.startListening();
        rec_events.setAdapter(event_adapter);
    }

    public void NoticesHandler(){

        reference= FirebaseDatabase.getInstance().getReference().child("Society").child("Notice");
        notice_options= new FirebaseRecyclerOptions.Builder<notice>().setQuery(reference,notice.class).build();
        notice_adapter= new FirebaseRecyclerAdapter<notice, NoticeViewHolder>(notice_options) {
            @Override
            protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull notice model) {

                holder.description.setText(model.getDescription());
                holder.host.setText(model.getHost());
            }

            @NonNull
            @Override
            public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_card,parent,false);
                return new NoticeViewHolder(view);
            }
        };

        notice_adapter.startListening();
        rec_notices.setAdapter(notice_adapter);
    }

    public void NavigationSupport(){

        setSupportActionBar(toolbar);

        navigationView.bringToFront();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView=findViewById(R.id.my_nav);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.transaction:
                        startActivity(new Intent(getApplicationContext(), transaction_activity.class));
                        overridePendingTransition(0, 0);
                        return true ;

                    case R.id.proc:
                        startActivity(new Intent(getApplicationContext(), procurement_activity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profile_activity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_events:
                Intent intent = new Intent(this, DisplayEvents.class);
                startActivity(intent);
                break;

            case R.id.nav_associates:
                Intent intent1 = new Intent(this,Associates.class);
                startActivity(intent1);
                break;

            case R.id.nav_complain:
                Intent intent2 = new Intent(this,user_complaint.class);
                startActivity(intent2);
                break;

            case R.id.nav_notice:
                Intent intent3 = new Intent(this,user_notice.class);
                startActivity(intent3);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addEvent(View view) {
        Intent intent= new Intent(this,register_event.class);
        startActivity(intent);
    }

}


