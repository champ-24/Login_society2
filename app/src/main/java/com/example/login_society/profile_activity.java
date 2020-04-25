package com.example.login_society;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class profile_activity extends AppCompatActivity {

    Button signout;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //from xml
    TextView tname,temail,tphone,tsociety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        getSupportActionBar().setTitle("Profile");





//init fire base for dispplay
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Society");

        //init views

        tname=findViewById(R.id.name);
        temail=findViewById(R.id.email);
        tphone=findViewById(R.id.ph_no);
        tsociety=findViewById(R.id.soc_name);

        /*We need to get the current signed in user we can do this as follows*/

        //By using orderbychild query we will show the detail of the nnode and whose key name is same as email of the signed in user

        Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //check the data until we can get it

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String name = ""+ds.child("full_name").getValue();
                    String email = ""+ds.child("email").getValue();
                    String phone = ""+ds.child("phoneno").getValue();
                    String soc_name = ""+ds.child("society_name").getValue();

                    tname.setText(name);
                    temail.setText(email);
                    tphone.setText(phone);
                    tsociety.setText(soc_name);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });













        signout=(Button)findViewById(R.id.signout);

        BottomNavigationView bottomNavigationView=findViewById(R.id.my_nav);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),home_activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.transaction:
                        startActivity(new Intent(getApplicationContext(), transaction_activity.class));
                        overridePendingTransition(0, 0);
                        return true ;

                    case R.id.proc:
                        startActivity(new Intent(getApplicationContext(), procurment_activity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
        Signout();



    }

    private void Signout() {

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent=new Intent(profile_activity.this,login_form.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}
