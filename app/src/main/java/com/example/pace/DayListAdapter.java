package com.example.pace;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    private List<CalendarData> calendarData;
    private Context applicationContext;

    public DayListAdapter(List<CalendarData> calendarData, Context applicationContext) {
        this.calendarData = calendarData;
        this.applicationContext = applicationContext;
    }

    public void getCalendarData (List<CalendarData> newClientModuleList) { this.calendarData = newClientModuleList; }
    public List<CalendarData> setCalendarData() { return this.calendarData; }

    public Context getApplicationContext() { return applicationContext; }
    public void setApplicationContext(Context applicationContext) { this.applicationContext = applicationContext;}

    @NonNull
    @Override
    public DayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.day_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.ViewHolder holder, int position) {
        String dateFormat = this.calendarData.get(position).getMonth() + "/" + this.calendarData.get(position).getDay() + "/" + this.calendarData.get(position).getYear();
        holder.dateText.setText(dateFormat);
        holder.routesText.setText(String.valueOf(this.calendarData.size() + 1));
        holder.totalGasExpenditure.setText(String.format("%.2f", this.calendarData.get(position).CalculateClientExpenditure()));

        Log.v("NDX", ""+position);

        if (this.calendarData.size() >= 2 && position > 0) {
            float percentageCalculation = this.calendarData.get(position).getPercentageText(
                    this.calendarData.get(position - 1).CalculateClientExpenditure(),
                    this.calendarData.get(position).CalculateClientExpenditure()
            );
            if (percentageCalculation == 0) {
                holder.statusImage.setImageResource(R.drawable.green_circle);
                holder.percentageStatus.setText(R.string.percent_sign);
            }
            else if (percentageCalculation < -0.0f) {
                percentageCalculation = percentageCalculation * -1.0f;
                holder.percentageStatus.setText(R.string.negative_percent_sign);
                holder.statusImage.setImageResource(R.drawable.red_circle);
                int color = ContextCompat.getColor(this.applicationContext, R.color.red_accent_1);
                holder.percentageStatus.setTextColor(color);
                holder.percentageText.setTextColor(color);
            }
            else if (percentageCalculation > 0.0f) {
                holder.percentageStatus.setText(R.string.plus_percent_sign);
                holder.statusImage.setImageResource(R.drawable.green_circle);
                int color = ContextCompat.getColor(this.applicationContext, R.color.green_accent_1);
                holder.percentageStatus.setTextColor(color);
                holder.percentageText.setTextColor(color);
            }
            holder.percentageText.setText(String.format("%.2f", percentageCalculation));
        }
        else {
            int color = ContextCompat.getColor(this.applicationContext, R.color.green_accent_1);
            holder.percentageStatus.setTextColor(color);
            holder.percentageText.setTextColor(color);
            holder.statusImage.setImageResource(R.drawable.green_circle);
            holder.percentageStatus.setText(R.string.plus_percent_sign);
            holder.percentageText.setText("100");
        }
    }

    @Override
    public int getItemCount() {
        return this.calendarData.size();
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
            this.statusImage = itemView.findViewById(R.id.day_element_status_image);
            this.dateText = itemView.findViewById(R.id.day_element_date_text);
            this.routesText = itemView.findViewById(R.id.day_element_routes_input);
            this.totalGasExpenditure = itemView.findViewById(R.id.day_element_calculation_text);
            this.percentageStatus = itemView.findViewById(R.id.day_element_percentage_status);
            this.percentageText = itemView.findViewById(R.id.day_element_percentage_text);
        }
    }
}

/* 11/5/2023 Link elements from within blueprint xml file to the objects that will
* be manipulated by the structural class ClientModule */