package com.example.pace.config;

import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.adapters.DailyListAdapter;
import com.example.pace.clientuser.ClientData;

import java.util.ArrayList;

public class ListHolder {

    public ListHolder() {}
    public static ListHolder listHolderInstance;
    public static ListHolder getInstance() {
        if (listHolderInstance == null) {
            synchronized (ListConfig.class) {
                if (listHolderInstance == null) {
                    listHolderInstance = new ListHolder();
                }
            }
        }
        return listHolderInstance;
    }

    public ArrayList<ClientData> clientDataList;
    public DailyListAdapter dailyListAdapter;
    public ViewPager2 mainActivityPager;
}
