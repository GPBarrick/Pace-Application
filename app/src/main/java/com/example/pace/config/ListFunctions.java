package com.example.pace.config;

import com.example.pace.clientuser.*;

import java.util.ArrayList;

public interface ListFunctions {
    void organizeDailyDescending(ArrayList<ClientData> clientDataList);
    void organizeWeeklyDescending(ArrayList<ClientData> clientDataList);
    void organizeMonthlyDescending(ArrayList<ClientData> clientDataList);
    void calculatePercentages(ArrayList<ClientData> clientDataList);
    void setFormattedCardDate(ArrayList<ClientData> clientDataList);
    void setFormattedDailyListDate(ArrayList<ClientData> clientDataList);
    void setFormattedWeeklyListDate(ArrayList<ClientData> clientDataList);
    void setFormattedMonthlyListDate(ArrayList<ClientData> clientDataList);

    String findMonthString(ClientData clientData);
}
