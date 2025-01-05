package com.example.aidflow;


import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.List;

class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {

    private final List<DonationHistory> historyList;
    private Context context;

    public HistoryListAdapter(List<DonationHistory> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_donation_history_recycleview, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        DonationHistory history = historyList.get(position);

        Log.d("HistoryListAdapter", "Binding history: " + history.getPIC() + "");

        // Bind data to views
        holder.donationName.setText(history.getDonationTitle());
        holder.projectName.setText(history.getCategory());
        holder.organizername.setText(history.getPIC());
        holder.amount.setText(String.format("RM %.2f", history.getAmount()));
        holder.paymentMethod.setText(history.getPaymentMethod());

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView donationName, projectName, organizername, amount,paymentMethod;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            donationName = itemView.findViewById(R.id.donationName_history);
            projectName = itemView.findViewById(R.id.category_history);
            organizername = itemView.findViewById(R.id.PIC_history);
            amount = itemView.findViewById(R.id.amount_history);
            paymentMethod = itemView.findViewById(R.id.paymentMethod_history);
        }
    }
}

