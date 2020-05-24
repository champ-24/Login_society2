package com.example.login_society;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class register_complaint extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    EditText description,date;
    Button save_btn;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);


        date = findViewById(R.id.date);
        description = findViewById(R.id.desc);
        save_btn = findViewById(R.id.save_button);


        //firebase database reference get the instance of the databse of our
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Society").child("Complaint");

        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(register_complaint.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desc = description.getText().toString().trim();
                String event_date = date.getText().toString().trim();


                if (desc.isEmpty()) {
                    description.setError(getString(R.string.input_error_event_name));
                    description.requestFocus();
                    return;
                }

                complaint complaint = new complaint(desc,event_date);

                databaseReference.push().setValue((complaint));

                Toast.makeText(register_complaint.this, "Data Insertion Sucessful", Toast.LENGTH_SHORT).show();
                finish();



            }
        });


    }
}
