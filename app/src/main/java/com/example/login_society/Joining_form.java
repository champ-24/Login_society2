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

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_user();
            }
        });

    }

    private void reg_user() {

        final String name = fullname.getText().toString().trim();
        final String semail =email.getText().toString().trim();
        final String ph_no=phoneno.getText().toString().trim();
        final String soc_nm=societyname.getText().toString().trim();
        final String soc_addr=societyaddr.getText().toString().trim();
        String pass_code=password.getText().toString().trim();
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
            password.setError(getString(R.string.input_error_password));
            password.requestFocus();
            return;
        }

        if (pass_code.length() < 6) {
            password.setError(getString(R.string.input_error_password_length));
            password.requestFocus();
            return;
        }
        if(!confo_code.equals(pass_code))
        {
            confopasscode.setError((getString((R.string.input_confocode_msg))));
            confopasscode.requestFocus();
            password.setText("");
            confopasscode.setText("");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(semail, pass_code)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            JoiningDBHelperClass helperClass=new JoiningDBHelperClass(name,soc_nm,soc_addr,ph_no,semail);
                            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child(soc_nm).setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(Joining_form.this, "Rigistration Complete", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),home_activity.class));
                                }
                            });

                        } else {

                            Toast.makeText(Joining_form.this, "Some Error Occured", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
}
