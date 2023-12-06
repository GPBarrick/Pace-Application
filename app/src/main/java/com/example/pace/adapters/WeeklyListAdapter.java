package com.example.pace.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pace.R;
import com.example.pace.clientuser.ClientDataDailyList;
import com.example.pace.config.ListHolder;

public class WeeklyListAdapter extends RecyclerView.Adapter<WeeklyListAdapter.ViewHolder> {

    public WeeklyListAdapter() {
    }

    @NonNull
    @Override
    public WeeklyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyListAdapter.ViewHolder holder, int position) {
        String dateFormat = "nullDate";
        int startMonth = ListHolder.getInstance().outputWeeklyDataList.get(position).getStartMonth();
        int endMonth = ListHolder.getInstance().outputWeeklyDataList.get(position).getEndMonth();
        int startDay = ListHolder.getInstance().outputWeeklyDataList.get(position).getStartDay();
        int endDay = ListHolder.getInstance().outputWeeklyDataList.get(position).getEndDay();
        int startYear = ListHolder.getInstance().outputWeeklyDataList.get(position).getStartYear();
        int endYear = ListHolder.getInstance().outputWeeklyDataList.get(position).getEndYear();
        if (startMonth == endMonth) {
            dateFormat = determineMonthFormat(startMonth) + " " + startDay + " to " + endDay + ", " + startYear;
        } else {
            dateFormat = determineMonthFormat(startMonth) + " " + startDay + " to " + determineMonthFormat(endMonth) + " " + endDay;
            if (startYear == endYear) {
                dateFormat = dateFormat + ", " + startYear;
            } else {
                int startYearFormat = startYear % 100;
                int endYearFormat = endYear % 100;
                dateFormat = determineMonthFormat(startMonth) + " " + startDay + ", " + startYearFormat
                        + " to " + determineMonthFormat(endMonth) + " " + endDay + ", " + endYearFormat;
            }
        }
        holder.dateText.setText(dateFormat);
        holder.routesText.setText("Routes: ");
        holder.routesNum.setText(String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(position).getClientDataList().size()));
        holder.expenditureText.setText("$ "+String.format("%.2f",ListHolder.getInstance().outputWeeklyDataList.get(position).getExpenditure()));
    }

    @Override
    public int getItemCount() {
        return ListHolder.getInstance().outputWeeklyDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateText;
        public TextView routesText;
        public TextView routesNum;
        public TextView expenditureText;
        public TextView relatedPercentage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dateText = itemView.findViewById(R.id.layout_list_dateText);
            this.routesText = itemView.findViewById(R.id.layout_list_routesText);
            this.routesNum = itemView.findViewById(R.id.layout_list_routesNum);
            this.expenditureText = itemView.findViewById(R.id.layout_list_expenditureText);
            this.relatedPercentage = itemView.findViewById(R.id.layout_list_expenditurePercentageText);
        }
    }

    public String determineMonthFormat(int dateIndex) {
        String[] monthArr = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        return monthArr[dateIndex - 1];
    }
}
