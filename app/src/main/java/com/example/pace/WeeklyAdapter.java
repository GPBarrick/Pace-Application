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
