package com.example.login_society;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ComplaintViewHolder extends RecyclerView.ViewHolder {

    TextView description,date;
    public ComplaintViewHolder(@NonNull View itemView) {
        super(itemView);
        description=itemView.findViewById(R.id.complaint_description);
        date=itemView.findViewById(R.id.complaint_date);
    }
}
