package com.example.pace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeeklyAdapter extends RecyclerView.Adapter<WeeklyAdapter.ViewHolder> {

    private final List<WeeklyData> weeklyDataList;
    private final Context applicationContext;

    public WeeklyAdapter(List<WeeklyData> weeklyDataList, Context applicationContext) {
        this.weeklyDataList = weeklyDataList;
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public WeeklyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.applicationContext).inflate(R.layout.weekly_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyAdapter.ViewHolder holder, int position) {
        holder.dateText.setText(String.valueOf(this.weeklyDataList.get(position).getDateFormat()));
        holder.routesText.setText(String.valueOf(this.weeklyDataList.get(position).getClientModuleList().size()));
        holder.totalGasExpenditure.setText(String.format("%.2f", this.weeklyDataList.get(position).getTotalExpenditure()));

        if (this.weeklyDataList.get(position).getPercentageRate() <= 0.0f) {
            if (this.weeklyDataList.get(position).getPercentageRate() != 0.0f) {
                this.weeklyDataList.get(position).setPercentageRate(this.weeklyDataList.get(position).getPercentageRate() * -1.0f);
                holder.percentageStatus.setText(R.string.negative_percent_sign);
            } else {
                holder.percentageStatus.setText(R.string.percent_sign);
            }
            int color = ContextCompat.getColor(this.applicationContext, R.color.green_accent_1);
            holder.statusImage.setImageResource(R.drawable.green_circle);
            holder.percentageText.setText(String.format("%.2f", this.weeklyDataList.get(position).getPercentageRate()));
            holder.percentageText.setTextColor(color);
            holder.percentageStatus.setTextColor(color);
        } else if (this.weeklyDataList.get(position).getPercentageRate() > 0.0f) {
            holder.percentageStatus.setText(R.string.plus_percent_sign);
            int color = ContextCompat.getColor(this.applicationContext, R.color.red_accent_1);
            holder.statusImage.setImageResource(R.drawable.red_circle);
            holder.percentageText.setText(String.format("%.2f", this.weeklyDataList.get(position).getPercentageRate()));
            holder.percentageText.setTextColor(color);
            holder.percentageStatus.setTextColor(color);
        }

    }

    @Override
    public int getItemCount() {
        return this.weeklyDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView statusImage;
        public TextView dateText;
        public TextView routesText;
        public TextView totalGasExpenditure;
        public TextView percentageStatus;
        public TextView percentageText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.statusImage = itemView.findViewById(R.id.weekly_element_status_image);
            this.dateText = itemView.findViewById(R.id.weekly_element_date_text);
            this.routesText = itemView.findViewById(R.id.weekly_element_routes_input);
            this.totalGasExpenditure = itemView.findViewById(R.id.weekly_element_calculation_text);
            this.percentageStatus = itemView.findViewById(R.id.weekly_element_percentage_status);
            this.percentageText = itemView.findViewById(R.id.weekly_element_percentage_text);
        }
    }
}
