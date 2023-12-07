package com.example.pace.config;

import com.example.pace.R;
import com.example.pace.clientuser.ClientData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class DevFactory {
    public DevFactory() {}
    public static DevFactory devFactoryInstance;
    public static DevFactory getInstance() {
        if (devFactoryInstance == null) {
            synchronized (DevFactory.class) {
                if (devFactoryInstance == null) {
                    devFactoryInstance = new DevFactory();
                }
            }
        }
        return devFactoryInstance;
    }
    public ClientData createClientData() {
        Random rand = new Random();
        int randDay = rand.nextInt((20 - 5) + 1) + 5;
        int randMonth = rand.nextInt((11 - 2) + 1) + 2;
        float randMpg = 15.0f + rand.nextFloat() * (40.0f - 15.0f);
        float randGasPrice = 2.00f + rand.nextFloat() * (4.00f - 2.00f);
        float randDistance = 5.0f + rand.nextFloat() * (25.0f - 5.0f);
        float randIncome = 6.0f + rand.nextFloat() * (25.0f - 6.0f);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, randMonth);
        calendar.set(Calendar.DAY_OF_MONTH, randDay);
        calendar.set(Calendar.YEAR, 2023);
        int wof = calendar.get(Calendar.WEEK_OF_YEAR);
        return new ClientData(randMonth, randDay, calendar.get(Calendar.YEAR), randMpg, randGasPrice, randDistance, randIncome, wof);
    }
    public void addClientData(int dataAmount) {
        for (int i = 0; i < dataAmount; ++i) {
            ClientData clientData = createClientData();
            if (ListHolder.getInstance().clientDataList == null) {
                ListHolder.getInstance().clientDataList = new ArrayList<>();
            }
            ListHolder.getInstance().clientDataList.add(clientData);
            ListOrganizer.getInstance().setExpenditure(clientData);
            ListOrganizer.getInstance().initDailyListData(clientData);
            ListOrganizer.getInstance().organizeDailyList(ListHolder.getInstance().outputDailyDataList);
            ListOrganizer.getInstance().initWeeklyListData(clientData);
            ListOrganizer.getInstance().organizeWeeklyList(ListHolder.getInstance().outputWeeklyDataList);
            ListOrganizer.getInstance().initMonthlyListData(clientData);
            ListOrganizer.getInstance().organizeMonthlyList(ListHolder.getInstance().outputMonthlyDataList);
            if (ListHolder.getInstance().homeCardAdapter != null) {
                ListHolder.getInstance().homeCardAdapter.notifyDataSetChanged();
            }
        }
    }
}
