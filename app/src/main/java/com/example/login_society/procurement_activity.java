package com.example.login_society;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class procurement_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView activity_name;

    Button grossary,stationary,electronics,dairy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.procurement_activity);

        Initialization();

        NavigationSupport();
    }

    private void Initialization() {

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_drawer);
        toolbar=findViewById(R.id.toolbar);
        activity_name=findViewById(R.id.activity_name);

        grossary=findViewById(R.id.btn_grossary);
        grossary.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                grossary_call();
            }

        });
        electronics=findViewById(R.id.btn_electronics);
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(procurement_activity.this, Electronics.class);
                startActivity(intent);
            }
        });
        stationary=findViewById(R.id.btn_stationary);
        stationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(procurement_activity.this, Stationary.class);
                startActivity(intent);
            }
        });
        dairy=findViewById(R.id.btn_dairy);
        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(procurement_activity.this, Dairy.class);
                startActivity(intent);
            }
        });
    }

    private void grossary_call() {
        Intent i = new Intent(this,Grossary.class);
        startActivity(i);
    }

    private void NavigationSupport() {

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.my_nav);
        bottomNavigationView.setSelectedItemId(R.id.proc);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), home_activity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.transaction:
                        startActivity(new Intent(getApplicationContext(), transaction_activity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.proc:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profile_activity.class));
                        overridePendingTransition(0, 0);
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
                startActivity(new Intent(this, DisplayEvents.class));
                break;

            case R.id.nav_associates:
                startActivity(new Intent(this,Associates.class));
                break;

            case R.id.nav_complain:
                startActivity(new Intent(this,user_complaint.class));
                break;

            case R.id.nav_notice:
                startActivity(new Intent(this,user_notice.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Hardware(View view) {
        Intent intent=new Intent(this,Hardware.class);
        startActivity(intent);
    }
}
