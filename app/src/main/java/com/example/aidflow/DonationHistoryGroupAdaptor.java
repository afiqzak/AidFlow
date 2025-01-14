package com.example.aidflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DonationHistoryGroupAdaptor extends RecyclerView.Adapter<DonationHistoryGroupAdaptor.ViewHolder> {
    // Map to hold grouped donation history by date
    private final Map<String, List<DonationHistory>> groupedHistory;
    // List to maintain the order of dates
    private final List<String> dateList;
    private final Context context;

    // Constructor to initialize the adapter with grouped history and context
    public DonationHistoryGroupAdaptor(Map<String, List<DonationHistory>> groupedHistory, Context context) {
        this.groupedHistory = groupedHistory;
        this.dateList = new ArrayList<>(groupedHistory.keySet());
        this.context = context;
    }

    @NonNull
    @Override
    public DonationHistoryGroupAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each group item
        View view = LayoutInflater.from(context).inflate(R.layout.single_donation_history_grouped, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationHistoryGroupAdaptor.ViewHolder holder, int position) {
        // Get the date and corresponding donations for the current position
        String date = dateList.get(position);
        List<DonationHistory> donations = groupedHistory.get(date);

        // Get the formatted display date
        String displayDate = getDisplayDate(date);

        // Set the display date in the TextView
        holder.TVHistoryDate.setText(displayDate);

        // Set up the nested RecyclerView for donations under this date
        HistoryListAdapter donationHistoryListAdapter = new HistoryListAdapter(donations, context);
        holder.RVListHistory.setLayoutManager(new LinearLayoutManager(context));
        holder.RVListHistory.setAdapter(donationHistoryListAdapter);
    }

    @Override
    public int getItemCount() {
        // Return the number of date groups
        return dateList.size();
    }

    // Method to get the formatted display date
    private String getDisplayDate(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("d MMM", Locale.getDefault());
        SimpleDateFormat todayFormat = new SimpleDateFormat("d MMM", Locale.getDefault());
        SimpleDateFormat yesterdayFormat = new SimpleDateFormat("d MMM", Locale.getDefault());

        try {
            Date date = inputFormat.parse(dateStr);
            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                Date today = calendar.getTime();
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                Date yesterday = calendar.getTime();

                // Check if the date is today, yesterday, or another date
                if (todayFormat.format(date).equals(todayFormat.format(today))) {
                    return "Today";
                } else if (yesterdayFormat.format(date).equals(yesterdayFormat.format(yesterday))) {
                    return "Yesterday";
                } else {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM", Locale.getDefault());
                    return outputFormat.format(date);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Return the original string in case of parsing error
        return dateStr;
    }

    // ViewHolder class to hold the views for each group item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView TVHistoryDate;
        RecyclerView RVListHistory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVHistoryDate = itemView.findViewById(R.id.TVHistoryDate);
            RVListHistory = itemView.findViewById(R.id.RVListHistory);
        }
    }
}
