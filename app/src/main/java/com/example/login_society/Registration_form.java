package com.example.login_society;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_form extends AppCompatActivity {


    TextInputEditText fullname,phoneno,email,officeno,societyname,societyaddr,passcode,confopasscode;
    Button reg_btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        getSupportActionBar().setTitle("RegistrationForm");


            fullname=(TextInputEditText) findViewById(R.id.et_fullnm);
            phoneno=(TextInputEditText) findViewById(R.id.ph_no);
            email=(TextInputEditText)findViewById(R.id.et_email);
            officeno=(TextInputEditText)findViewById(R.id.off_no);
            societyaddr=(TextInputEditText)findViewById(R.id.soc_addr);
            societyname=(TextInputEditText)findViewById(R.id.soc_name);
            passcode=(TextInputEditText)findViewById(R.id.passcode);
            confopasscode=(TextInputEditText)findViewById(R.id.confo_passcode);
            reg_btn=(Button) findViewById(R.id.reg_btn);

            reg_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseDatabase=FirebaseDatabase.getInstance();
                    databaseReference=firebaseDatabase.getReference("Society");
                    reg_user();
                }
            });

    }

    private void reg_user() {

        final String name = fullname.getText().toString().trim();
        final String semail =email.getText().toString().trim();
        final String ph_no=phoneno.getText().toString().trim();
        final String off_no=officeno.getText().toString().trim();
        final String soc_nm=societyname.getText().toString().trim();
        final String soc_addr=societyaddr.getText().toString().trim();
        String pass_code=passcode.getText().toString().trim();
        String confo_code=confopasscode.getText().toString().trim();


        if (name.isEmpty()) {
            fullname.setError(getString(R.string.input_error_name));
            fullname.requestFocus();
            return;
        }

        if (semail.isEmpty()) {
            email.setError(getString(R.string.input_error_email));
            email.requestFocus();
            return;
        }
        if (ph_no.isEmpty()) {
            phoneno.setError(getString(R.string.input_error_phone));
            phoneno.requestFocus();
            return;
        }

        if (ph_no.length() != 10) {
            phoneno.setError(getString(R.string.input_error_phone_invalid));
            phoneno.requestFocus();
            return;
        }

        if (off_no.isEmpty()) {
            officeno.setError(getString(R.string.input_error_name));
            officeno.requestFocus();
            return;
        }
        if (soc_nm.isEmpty()) {
            societyname.setError(getString(R.string.input_error_name));
            societyname.requestFocus();
            return;
        }
        if (soc_addr.isEmpty()) {
            societyaddr.setError(getString(R.string.input_error_name));
            societyaddr.requestFocus();
            return;
        }

        if (pass_code.isEmpty()) {
            passcode.setError(getString(R.string.input_error_password));
            passcode.requestFocus();
            return;
        }

        if (pass_code.length() < 6) {
            passcode.setError(getString(R.string.input_error_password_length));
            passcode.requestFocus();
            return;
        }
        if(!confo_code.equals(pass_code))
        {
            confopasscode.setError((getString((R.string.input_confocode_msg))));
            confopasscode.requestFocus();
            passcode.setText("");
            confopasscode.setText("");
            return;
        }

        DbHelperClass helperClass=new DbHelperClass(name,soc_nm,soc_addr,ph_no,off_no,semail);
        databaseReference.child(soc_nm).child(name).setValue(helperClass);

    }
}

