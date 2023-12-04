package com.example.pace.config;

import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.adapters.DailyListAdapter;
import com.example.pace.adapters.MonthlyListAdapter;
import com.example.pace.adapters.WeeklyListAdapter;
import com.example.pace.clientuser.ClientData;
import com.example.pace.clientuser.ClientDataDailyList;
import com.example.pace.clientuser.ClientDataMonthlyList;
import com.example.pace.clientuser.ClientDataWeeklyList;

import java.util.ArrayList;

public class ListHolder {

    public ListHolder() {}
    public static ListHolder listHolderInstance;
    public static ListHolder getInstance() {
        if (listHolderInstance == null) {
            synchronized (ListHolder.class) {
                if (listHolderInstance == null) {
                    listHolderInstance = new ListHolder();
                }
            }
        }
        return listHolderInstance;
    }

    public ArrayList<ClientData> clientDataList;
    public ArrayList<ClientDataDailyList> outputDailyDataList = new ArrayList<>();
    public DailyListAdapter dailyListAdapter;
    public ArrayList<ClientDataWeeklyList> outputWeeklyDataList = new ArrayList<>();
    public WeeklyListAdapter weeklyListAdapter;
    public ArrayList<ClientDataMonthlyList> outputMonthlyDataList = new ArrayList<>();
    public MonthlyListAdapter monthlyListAdapter;
    public ViewPager2 mainActivityPager;
}
