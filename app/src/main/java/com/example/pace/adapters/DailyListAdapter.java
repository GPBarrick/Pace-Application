package com.example.pace.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pace.R;
import com.example.pace.clientuser.ClientData;
import com.example.pace.clientuser.ClientDataDailyList;
import com.example.pace.config.ListHolder;

import java.util.ArrayList;

public class DailyListAdapter extends RecyclerView.Adapter<DailyListAdapter.ViewHolder> {

    public DailyListAdapter() {
    }

    @NonNull
    @Override
    public DailyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyListAdapter.ViewHolder holder, int position) {

        holder.dateText.setText(determineDateFormat(ListHolder.getInstance().outputDailyDataList.get(position)));
        holder.routesText.setText("Routes: ");
        holder.routesNum.setText(""+ListHolder.getInstance().outputDailyDataList.get(position).getClientDataList().size());
        holder.expenditureText.setText("$ "+String.format("%.2f",ListHolder.getInstance().outputDailyDataList.get(position).getExpenditure()));
        holder.relatedPercentage.setText("% 0.00");
    }

    @Override
    public int getItemCount() {
        return ListHolder.getInstance().outputDailyDataList.size();
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

    public String determineDateFormat(ClientDataDailyList clientData) {
        String[] monthArr = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        return monthArr[clientData.getMonth() - 1] + " " + clientData.getDay() + ", " + clientData.getYear();
    }
}
