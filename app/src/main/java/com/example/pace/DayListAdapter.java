package com.example.pace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    private List<CalendarData> calendarData;

    public DayListAdapter(List<CalendarData> calendarData) {
        this.calendarData = calendarData;
    }

    public void getCalendarData (List<CalendarData> newClientModuleList) { this.calendarData = newClientModuleList; }
    public List<CalendarData> setCalendarData() { return this.calendarData; }

    @NonNull
    @Override
    public DayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.day_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.ViewHolder holder, int position) {
        //holder.statusImage.setImageResource(this.calendarData.get(position).getClientModule(position).getImageResource());
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
                holder.percentageStatus.setText(R.string.percent_sign);
            }
            holder.percentageText.setText(String.format("%.2f", percentageCalculation));
        }
        else {
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