package com.example.login_society;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_notice extends AppCompatActivity {

    EditText description,name;
    Button save_btn;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_notice);

        name = findViewById(R.id.name);
        description = findViewById(R.id.desc);
        save_btn = findViewById(R.id.save_button);

        //firebase database reference get the instance of the databse of our
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Society").child("Notice");



        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desc = description.getText().toString().trim();
                String user_name = name.getText().toString().trim();


                if (desc.isEmpty()) {
                    description.setError(getString(R.string.input_error_event_name));
                    description.requestFocus();
                    return;
                }

                notice complaint = new notice(desc,user_name);

                databaseReference.push().setValue((complaint));

                Toast.makeText(register_notice.this, "Data Insertion Sucessful", Toast.LENGTH_SHORT).show();
                finish();



            }
        });




    }
}
