package com.example.login_society;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Joining_form extends AppCompatActivity {

    TextInputEditText fullname,phoneno,email,societyname,societyaddr,password,confopasscode;
    Button reg_btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joining_form);


        fullname=(TextInputEditText) findViewById(R.id.et_fullnm);
        phoneno=(TextInputEditText) findViewById(R.id.ph_no);
        email=(TextInputEditText)findViewById(R.id.et_email);
        societyaddr=(TextInputEditText)findViewById(R.id.soc_addr);
        societyname=(TextInputEditText)findViewById(R.id.soc_name);
        password=(TextInputEditText)findViewById(R.id.password);
        confopasscode=(TextInputEditText)findViewById(R.id.confo_passcode);
        reg_btn=(Button) findViewById(R.id.reg_btn);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Society");
        firebaseAuth= FirebaseAuth.getInstance();

    }

}
