package com.example.pace;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DailyFragment extends Fragment {

    // Set the CalendarData
    private ArrayList<CalendarData> calendarDataList;
    public void SetCalendarData(ArrayList<CalendarData> calendarData) { this.calendarDataList = calendarData; }

    // Set the WeeklyData
    private ArrayList<WeeklyData> weeklyDataList;
    public void SetWeeklyData(ArrayList<WeeklyData> weeklyData) { this.weeklyDataList = weeklyData; }

    private Context applicationContext;
    private String fragmentName;
    private int fragmentType;

    public DailyFragment(Context applicationContext, String fragmentName, int fragmentType) {
        this.applicationContext = applicationContext;
        this.fragmentName = fragmentName;
        this.fragmentType = fragmentType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        RecyclerView list = view.findViewById(R.id.fragment_daily_recyclerView_list);

        if (this.fragmentType == 1) {
            DayListAdapter dayListAdapter = new DayListAdapter(this.calendarDataList, this.applicationContext);
            list.setLayoutManager(new LinearLayoutManager(this.applicationContext));
            list.setAdapter(dayListAdapter);

        } else if (this.fragmentType == 2) {
            WeeklyAdapter weeklyListAdapter = new WeeklyAdapter(this.weeklyDataList, this.applicationContext);
            list.setLayoutManager(new LinearLayoutManager(this.applicationContext));
            list.setAdapter(weeklyListAdapter);

        } else if (this.fragmentType == 3) {
            // monthly adapter <MonthlyData>
        }
        return view;
    }

    public ArrayList<CalendarData> getCalendarDataList() {
        return this.calendarDataList;
    }
    public ArrayList<WeeklyData> getWeeklyDataList() {
        return this.weeklyDataList;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public int getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(int fragmentType) {
        this.fragmentType = fragmentType;
    }
}