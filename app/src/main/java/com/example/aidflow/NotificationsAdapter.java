package com.example.aidflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aidflow.R;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private final Context context;
    private final List<String> notificationTitles; // list noti text semua silap letak title tapi malas nak tukar

    public NotificationsAdapter(Context context, List<String> notificationTitles) {
        this.context = context;
        this.notificationTitles = notificationTitles;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.noti_card, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        String title = notificationTitles.get(position);
        holder.notiText.setText(title);
    }

    @Override
    public int getItemCount() {
        return notificationTitles.size();
    }

    //utk recycle view so takyah kacau
    public static class NotificationsViewHolder extends RecyclerView.ViewHolder {
        TextView notiText;

        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
            notiText = itemView.findViewById(R.id.noti_text);
        }
    }
}


