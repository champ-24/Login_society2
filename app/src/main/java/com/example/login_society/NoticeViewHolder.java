package com.example.login_society;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NoticeViewHolder extends RecyclerView.ViewHolder {

    TextView description,host;
    public NoticeViewHolder(@NonNull View itemView) {
        super(itemView);

        description=itemView.findViewById(R.id.notice_description);
        host=itemView.findViewById(R.id.notice_name);
    }
}
