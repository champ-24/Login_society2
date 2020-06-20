package com.example.login_society;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class AssociateViewHolder extends RecyclerView.ViewHolder {

    TextView ass_name,ass_work;

    public AssociateViewHolder(@NonNull View itemView) {
        super(itemView);

        ass_name=itemView.findViewById(R.id.ass_list_name);
        ass_work=itemView.findViewById(R.id.ass_list_work);
    }
}
