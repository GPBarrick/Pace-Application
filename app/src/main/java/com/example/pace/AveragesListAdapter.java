package com.example.pace;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AveragesListAdapter extends RecyclerView.Adapter<AveragesListAdapter.ViewHolder> {

    private List<CalendarData> calendarDataList;
    private Context applicationContext;

    public AveragesListAdapter(List<CalendarData> calendarDataList, Context applicationContext) {
        this.calendarDataList = calendarDataList;
        this.applicationContext = applicationContext;
    }

    public void SetCalendarDataList(List<CalendarData> calendarDataList) { this.calendarDataList = calendarDataList; }
    public List<CalendarData> GetCalendarDataList() { return this.calendarDataList; }

    public void SetApplicationContext(Context applicationContext) { this.applicationContext = applicationContext; }
    public Context GetApplicationContext() { return this.applicationContext; }

    @NonNull
    @Override
    public AveragesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.averages_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AveragesListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.calendarDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

/* 11/5/2023 Link elements from within blueprint xml file to the objects that will
 * be manipulated by the structural class ClientModule */