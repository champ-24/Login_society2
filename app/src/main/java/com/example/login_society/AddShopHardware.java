package com.example.login_society;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddShopHardware extends AppCompatActivity {

    EditText shop_name,shop_owner_name,shop_contact,shop_address;

    Button add_shop;

    TextView activity_name;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_hardware);

        Initialization();
    }

    private void Initialization() {
        activity_name=findViewById(R.id.activity_name);
        add_shop=findViewById(R.id.add_shop);

        shop_name=findViewById(R.id.hardware_shop_name);
        shop_owner_name=findViewById(R.id.hardware_shop_owner_name);
        shop_contact=findViewById(R.id.hardware_shop_contact);
        shop_address=findViewById(R.id.hardware_shop_address);
    }

    public void AddShop(View view) {
        if (shop_name!=null && shop_owner_name!=null && shop_contact!=null && shop_address!=null){

            String name,owner_name,contact,address;

            name=shop_name.getText().toString();
            owner_name=shop_owner_name.getText().toString();
            contact=shop_contact.getText().toString();
            address=shop_address.getText().toString();

            ShopHelper shopHelper=new ShopHelper(name,owner_name,contact,address);


            reference= FirebaseDatabase.getInstance().getReference().child("Society").child("shop_List").child("Hardware");

            reference.push().setValue(shopHelper);

            Toast.makeText(this,"Hardware Shop added",Toast.LENGTH_LONG).show();
            finish();

        }else {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            shop_name.requestFocus();
        }
    }
}
