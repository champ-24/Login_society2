package com.example.login_society;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Associate extends AppCompatActivity {

    Button register;
    EditText ass_name,ass_contact,ass_address,ass_work,ass_salary;
    RadioGroup ass_gender;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__associate);

        ass_name=findViewById(R.id.ass_name);
        ass_contact=findViewById(R.id.ass_contact);
        ass_address=findViewById(R.id.ass_address);
        ass_gender=findViewById(R.id.ass_gender);
        ass_work=findViewById(R.id.ass_work);
        ass_salary=findViewById(R.id.ass_salary);
        register=findViewById(R.id.register);
    }
    public void clickRegister(View view){
        if (ass_name.getText().toString()==null||ass_contact.getText().toString()==null||ass_address.getText().toString()==null||ass_work.getText().toString()==null||ass_salary.getText().toString()==null){
            Toast.makeText(this,"Please Enter all Fields",Toast.LENGTH_LONG).show();
        }
        else {
            rootNode=FirebaseDatabase.getInstance();
            reference=rootNode.getReference().child("Society").child("Associate");

            String name=ass_name.getText().toString();
            String contact=ass_contact.getText().toString();
            String address=ass_address.getText().toString();
            int selectedId=ass_gender.getCheckedRadioButtonId();
            RadioButton ass_gender_button = (RadioButton) findViewById(selectedId);
            String gender= (String) ass_gender_button.getText();
            String work=ass_work.getText().toString();
            String salary=ass_salary.getText().toString();

            Associate_Helper helper=new Associate_Helper(name,contact,address,gender,work,salary);

            reference.push().setValue(helper);

            Toast.makeText(this, "Associate Registered", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
