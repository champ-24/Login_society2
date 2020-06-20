package com.example.login_society;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Associates extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView associate_list;
    DatabaseReference reference;
    FirebaseDatabase root;
    FirebaseRecyclerOptions<associate_list_helper> associate_options;
    FirebaseRecyclerAdapter<associate_list_helper,AssociateViewHolder> associate_adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associates2);

       Initialize();
       AssociateHandler();

    }

    private void AssociateHandler() {

        reference= FirebaseDatabase.getInstance().getReference().child("Society").child("Associateas");
        associate_options= new FirebaseRecyclerOptions.Builder<associate_list_helper>().setQuery(reference,associate_list_helper.class).build();
        associate_adapter= new FirebaseRecyclerAdapter<associate_list_helper, AssociateViewHolder>(associate_options) {
            @Override
            protected void onBindViewHolder(@NonNull AssociateViewHolder holder, int position, @NonNull associate_list_helper model) {

                holder.ass_name.setText(model.getAss_name());
                holder.ass_work.setText(model.getAss_work());

            }


            @NonNull
            @Override
            public AssociateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ass_list_card,parent,false);
                return new AssociateViewHolder(view);
            }
        };

        associate_adapter.startListening();
        associate_list.setAdapter(associate_adapter);
    }

    private void Initialize() {

        fab=findViewById(R.id.floatingActionButton);

        associate_list=findViewById(R.id.associate_list);
        associate_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        associate_list.setHasFixedSize(true);
    }

    public void clickHandler(View view) {

        Intent intent=new Intent(this,Add_Associate.class);
        startActivity(intent);
    }

}
