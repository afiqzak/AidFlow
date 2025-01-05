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
    private final Map<String, List<DonationHistory>> groupedHistory;
    private final List<String> dateList; // To maintain the order of dates
    private final Context context;

    public DonationHistoryGroupAdaptor(Map<String, List<DonationHistory>> groupedHistory, Context context) {
        this.groupedHistory = groupedHistory;
        this.dateList = new ArrayList<>(groupedHistory.keySet());
        this.context = context;
    }

    @NonNull
    @Override
    public DonationHistoryGroupAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_donation_history_grouped, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DonationHistoryGroupAdaptor.ViewHolder holder, int position) {
        String date = dateList.get(position);
        List<DonationHistory> donations = groupedHistory.get(date);

        String displayDate = getDisplayDate(date); // Get the formatted date

        holder.TVHistoryDate.setText(displayDate);

        // Set up nested RecyclerView for donations under this date
        HistoryListAdapter donationHistoryListAdapter = new HistoryListAdapter(donations, context);
        holder.RVListHistory.setLayoutManager(new LinearLayoutManager(context));
        holder.RVListHistory.setAdapter(donationHistoryListAdapter);
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    private String getDisplayDate(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("d MMM", Locale.getDefault()); // Important to match your date format
        SimpleDateFormat todayFormat = new SimpleDateFormat("d MMM", Locale.getDefault());
        SimpleDateFormat yesterdayFormat = new SimpleDateFormat("d MMM", Locale.getDefault());

        try {
            Date date = inputFormat.parse(dateStr);
            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                Date today = calendar.getTime();
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                Date yesterday = calendar.getTime();

                if (todayFormat.format(date).equals(todayFormat.format(today))) {
                    return "Today";
                } else if (yesterdayFormat.format(date).equals(yesterdayFormat.format(yesterday))) {
                    return "Yesterday";
                } else {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM", Locale.getDefault()); // Or your desired format
                    return outputFormat.format(date);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr; // Return the original string in case of parsing error
    }

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
