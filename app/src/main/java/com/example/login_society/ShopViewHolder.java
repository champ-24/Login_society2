package com.example.login_society;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ShopViewHolder extends RecyclerView.ViewHolder{

    TextView shop_name,shop_contact;

    public ShopViewHolder(@NonNull View itemView) {
        super(itemView);

        shop_name=itemView.findViewById(R.id.shop_name);
        shop_contact=itemView.findViewById(R.id.shop_contact);
    }
}
