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

public class MonthlyListAdapter extends RecyclerView.Adapter<MonthlyListAdapter.ViewHolder> {

    public MonthlyListAdapter() {
    }

    @NonNull
    @Override
    public MonthlyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyListAdapter.ViewHolder holder, int position) {
        holder.dateText.setText(determineDateFormat(ListHolder.getInstance().outputMonthlyDataList.get(position).getMonth()));
        holder.routesText.setText("Routes: ");
        holder.routesNum.setText(""+ListHolder.getInstance().outputMonthlyDataList.get(position).getClientDataList().size());
    }

    @Override
    public int getItemCount() {
        return ListHolder.getInstance().outputMonthlyDataList.size();
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

    public String determineDateFormat(int monthIndex) {
        String[] monthArr = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        return monthArr[monthIndex - 1];
    }
}
