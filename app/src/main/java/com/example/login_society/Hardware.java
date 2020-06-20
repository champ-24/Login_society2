package com.example.login_society;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Hardware extends AppCompatActivity {
    TextView activity_name;
    RecyclerView rec_hardware;
    FloatingActionButton add_shop;

    DatabaseReference reference;
    FirebaseRecyclerOptions<ShopCardHelper> hardware_options;
    FirebaseRecyclerAdapter<ShopCardHelper,ShopViewHolder> hardware_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware);

        Initialization();

        HardwareHandler();
    }

    private void HardwareHandler() {
        reference= FirebaseDatabase.getInstance().getReference().child("Society").child("shop_List").child("Hardware");
        hardware_options=new FirebaseRecyclerOptions.Builder<ShopCardHelper>().setQuery(reference,ShopCardHelper.class).build();
        hardware_adapter=new FirebaseRecyclerAdapter<ShopCardHelper, ShopViewHolder>(hardware_options) {
            @Override
            protected void onBindViewHolder(@NonNull ShopViewHolder holder, int position, @NonNull ShopCardHelper model) {
                holder.shop_name.setText(model.getShop_name());
                holder.shop_contact.setText(model.getShop_contact());
            }

            @NonNull
            @Override
            public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_card,parent,false);
                return new ShopViewHolder(view);
            }
        };
        hardware_adapter.startListening();
        rec_hardware.setAdapter(hardware_adapter);
    }

    private void Initialization() {
        activity_name=findViewById(R.id.activity_name);

        rec_hardware=findViewById(R.id.rec_hardware);
        rec_hardware.setHasFixedSize(true);
        rec_hardware.setLayoutManager(new LinearLayoutManager(this));

        add_shop=findViewById(R.id.add_shop);
    }

    public void clickHandler(View view) {
        Intent intent=new Intent(this,AddShopHardware.class);
        startActivity(intent);
    }
}
