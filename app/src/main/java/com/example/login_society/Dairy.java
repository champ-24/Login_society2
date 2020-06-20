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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class Dairy extends AppCompatActivity {

    TextView activity_name;
    RecyclerView rec_dairy;
    FloatingActionButton add_shop;

    DatabaseReference reference;
    FirebaseRecyclerOptions<ShopCardHelper> dairy_options;
    FirebaseRecyclerAdapter<ShopCardHelper,ShopViewHolder> dairy_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy);

        Initialization();

        DairyHandler();
    }

    private void DairyHandler() {
        reference= FirebaseDatabase.getInstance().getReference().child("Society").child("shop_List").child("Dairy");
        dairy_options=new FirebaseRecyclerOptions.Builder<ShopCardHelper>().setQuery(reference,ShopCardHelper.class).build();
        dairy_adapter= new FirebaseRecyclerAdapter<ShopCardHelper, ShopViewHolder>(dairy_options) {
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

        dairy_adapter.startListening();
        rec_dairy.setAdapter(dairy_adapter);
    }

    private void Initialization() {

        activity_name=findViewById(R.id.activity_name);
        add_shop=findViewById(R.id.add_shop);

        rec_dairy=findViewById(R.id.rec_dairy);
        rec_dairy.setHasFixedSize(true);
        rec_dairy.setLayoutManager(new LinearLayoutManager(this));

    }

    public void clickHandler(View view) {
        Intent intent=new Intent(this,AddShopDairy.class);
        startActivity(intent);

    }
}
