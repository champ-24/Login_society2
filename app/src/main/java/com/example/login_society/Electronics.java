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

public class Electronics extends AppCompatActivity {

    TextView activity_name;
    RecyclerView rec_electronics;
    FloatingActionButton add_shop;

    DatabaseReference reference;
    FirebaseRecyclerOptions<ShopCardHelper> electronics_options;
    FirebaseRecyclerAdapter<ShopCardHelper,ShopViewHolder> electronics_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);

        Initialization();

        ElectronicsHandler();

    }

    private void ElectronicsHandler() {

        reference= FirebaseDatabase.getInstance().getReference().child("Society").child("shop_List").child("Electronics");
        electronics_options=new FirebaseRecyclerOptions.Builder<ShopCardHelper>().setQuery(reference,ShopCardHelper.class).build();
        electronics_adapter=new FirebaseRecyclerAdapter<ShopCardHelper, ShopViewHolder>(electronics_options) {
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
        electronics_adapter.startListening();
        rec_electronics.setAdapter(electronics_adapter);
    }

    private void Initialization() {
        rec_electronics=findViewById(R.id.rec_electronics);
        rec_electronics.setHasFixedSize(true);
        rec_electronics.setLayoutManager(new LinearLayoutManager(this));

        activity_name=findViewById(R.id.activity_name);
        add_shop=findViewById(R.id.add_shop);
    }

    public void clickHandler(View view) {
        Intent intent=new Intent(this,AddShopElectronics.class);
        startActivity(intent);
    }
}
