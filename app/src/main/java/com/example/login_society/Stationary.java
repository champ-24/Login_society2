package com.example.login_society;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.LayoutInflater.from;

public class Stationary extends AppCompatActivity {

    TextView activity_name;
    RecyclerView rec_stationary;
    FloatingActionButton add_shop;

    DatabaseReference reference;
    FirebaseRecyclerOptions<ShopCardHelper> stationary_options;
    FirebaseRecyclerAdapter<ShopCardHelper,ShopViewHolder> stationary_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stationary);

        Initialization();

        StationaryHandler();
    }

    private void StationaryHandler() {

        reference= FirebaseDatabase.getInstance().getReference().child("Society").child("shop_List").child("Stationary");
        stationary_options=new FirebaseRecyclerOptions.Builder<ShopCardHelper>().setQuery(reference,ShopCardHelper.class).build();
        stationary_adapter=new FirebaseRecyclerAdapter<ShopCardHelper, ShopViewHolder>(stationary_options) {
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
        stationary_adapter.startListening();
        rec_stationary.setAdapter(stationary_adapter);
    }

    private void Initialization() {
        activity_name=findViewById(R.id.activity_name);
        add_shop=findViewById(R.id.add_shop);

        rec_stationary=findViewById(R.id.rec_stationary);
        rec_stationary.setHasFixedSize(true);
        rec_stationary.setLayoutManager(new LinearLayoutManager(this));
    }

    public void clickHandler(View view) {
        Intent intent=new Intent(this,AddShopStationary.class);
        startActivity(intent);
    }
}
