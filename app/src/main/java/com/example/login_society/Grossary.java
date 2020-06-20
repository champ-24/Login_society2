package com.example.login_society;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class Grossary extends AppCompatActivity {

    RecyclerView grossary_shop_list;
    FloatingActionButton add_shop;

    FirebaseRecyclerOptions<ShopCardHelper> grossary_options;
    FirebaseRecyclerAdapter<ShopCardHelper,ShopViewHolder> grossary_adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grossary);

        Initialization();

        GrossaryHandler();
    }

    private void GrossaryHandler() {
        reference=FirebaseDatabase.getInstance().getReference().child("Society").child("shop_List").child("Grossary");
        grossary_options=new FirebaseRecyclerOptions.Builder<ShopCardHelper>().setQuery(reference,ShopCardHelper.class).build();
        grossary_adapter=new FirebaseRecyclerAdapter<ShopCardHelper, ShopViewHolder>(grossary_options) {
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
        grossary_adapter.startListening();
        grossary_shop_list.setAdapter(grossary_adapter);
    }

    private void Initialization() {
        grossary_shop_list=findViewById(R.id.grossary_shop_list);
        grossary_shop_list.setLayoutManager(new LinearLayoutManager(this));
        grossary_shop_list.setHasFixedSize(true);

        add_shop=findViewById(R.id.add_shop);

    }

    public void AddShop(View view) {
        Intent intent=new Intent(this,AddShopGrossary.class);
        startActivity(intent);
    }
}
