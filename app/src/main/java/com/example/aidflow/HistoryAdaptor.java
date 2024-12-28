package com.example.aidflow;


import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.List;

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<DonationHistory> historyList;
    private Context context;

    public HistoryAdapter(List<DonationHistory> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_donation_history_recycleview, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        DonationHistory history = historyList.get(position);

        // Bind data to views
        holder.donationDate.setText(history.getTransactionDate());
        holder.donationName.setText(history.getDonationName());
        holder.projectName.setText(history.getProjectName());
        holder.organizername.setText(history.getDonatorName());
        holder.amount.setText("RM" + history.getAmount());
        holder.paymentMethod.setText(history.getPaymentMethod());

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView donationDate,donationName, projectName, organizername, amount,paymentMethod;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            donationDate = itemView.findViewById(R.id.donationDate_history);
            donationName = itemView.findViewById(R.id.donationName_history);
            projectName = itemView.findViewById(R.id.projectName_history);
            organizername = itemView.findViewById(R.id.organizerName_history);
            amount = itemView.findViewById(R.id.amount_history);
            paymentMethod = itemView.findViewById(R.id.paymentMethod_history);
        }
    }
}

