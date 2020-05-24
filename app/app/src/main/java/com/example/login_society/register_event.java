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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class register_event extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    EditText name,description,date,time,host;
    Button save_btn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);

        date = findViewById(R.id.date);
        host = findViewById(R.id.host);
        time = findViewById(R.id.time);
        name = findViewById(R.id.event_name);
        description = findViewById(R.id.desc);
        save_btn = findViewById(R.id.save_button);


        //firebase database reference get the instance of the databse of our
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Event");

        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(register_event.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        time.setInputType(InputType.TYPE_NULL);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(register_event.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String event_name = name.getText().toString().trim();
                String desc = description.getText().toString().trim();
                String event_date = date.getText().toString().trim();
                String event_time = time.getText().toString().trim();
                String eventhost = host.getText().toString().trim();



                if (event_name.isEmpty()) {
                    name.setError(getString(R.string.input_error_event_name));
                    name.requestFocus();
                    return;
                }

                if (desc.isEmpty()) {
                    description.setError(getString(R.string.input_error_event_name));
                    description.requestFocus();
                    return;
                }

                Events events = new Events(event_name,desc,event_date,event_time,eventhost);

                databaseReference.push().setValue((events));

                    Toast.makeText(register_event.this, "Data Insertion Sucessful", Toast.LENGTH_SHORT).show();
                    finish();
                


            }
        });
    }
}
