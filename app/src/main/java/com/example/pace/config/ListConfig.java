package com.example.pace.config;

import com.example.pace.clientuser.*;

import java.util.ArrayList;

public class ListConfig implements ListFunctions {

    public ListConfig() {}
    public static ListConfig listConfigInstance;
    public static ListConfig getInstance() {
        if (listConfigInstance == null) {
            synchronized (ListConfig.class) {
                if (listConfigInstance == null) {
                    listConfigInstance = new ListConfig();
                }
            }
        }
        return listConfigInstance;
    }

    @Override
    public void organizeDailyDescending(ArrayList<ClientData> clientDataList) {

    }
    @Override
    public void organizeWeeklyDescending(ArrayList<ClientData> clientDataList) {

    }
    @Override
    public void organizeMonthlyDescending(ArrayList<ClientData> clientDataList) {

    }

    @Override
    public void calculatePercentages(ArrayList<ClientData> clientDataList) {

    }

    @Override
    public void setFormattedCardDate(ArrayList<ClientData> clientDataList) {

    }

    @Override
    public void setFormattedDailyListDate(ArrayList<ClientData> clientDataList) {
        for (int index = 0; index < clientDataList.size(); ++index) {
            String month = findMonthString(clientDataList.get(index));
            clientDataList.get(index).setFormattedDateList(month);
        }
    }
    @Override
    public void setFormattedWeeklyListDate(ArrayList<ClientData> clientDataList) {

    }
    @Override
    public void setFormattedMonthlyListDate(ArrayList<ClientData> clientDataList) {

    }

    @Override
    public String findMonthString(ClientData clientData) {
        String[] monthArr = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Oct", "Nov", "Dec" };
        return monthArr[clientData.getMonth()];
    }
}
