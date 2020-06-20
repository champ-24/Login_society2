package com.example.login_society;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class EventViewHolder extends RecyclerView.ViewHolder {

    TextView event_name,host,date;
    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        event_name=itemView.findViewById(R.id.event_name);
        host=itemView.findViewById(R.id.host);
        date=itemView.findViewById(R.id.date);
    }
}
