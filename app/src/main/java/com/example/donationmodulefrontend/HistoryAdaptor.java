package com.example.donationmodulefrontend;


import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.List;

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<History> historyList;

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recycleview, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = historyList.get(position);

        // Bind data to views

        holder.donationDate.setText(history.getTransactionDate());
        holder.donationName.setText(history.getDonationName());
        holder.projectName.setText(history.getProjectName());
        holder.donatorName.setText(history.getDonatorName());
        holder.amount.setText("RM"+history.getAmount());
        holder.paymentMethod.setText(history.getPaymentMethod());

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView donationDate,donationName, projectName, donatorName, amount,paymentMethod;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            donationDate = itemView.findViewById(R.id.donationDate_history);
            donationName = itemView.findViewById(R.id.donationName_history);
            projectName = itemView.findViewById(R.id.projectName_history);
            donatorName = itemView.findViewById(R.id.donatorName_history);
            amount = itemView.findViewById(R.id.amount_history);
            paymentMethod = itemView.findViewById(R.id.paymentMethod_history);
        }
    }
}

