package com.example.pace;

import java.util.ArrayList;

public class WeeklyData {

    public WeeklyData() {}
    public WeeklyData(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    private ArrayList<ClientModule> clientModuleList;
    public void AddClientModule(ClientModule clientModule) {
        if (this.clientModuleList == null) { this.clientModuleList = new ArrayList<>(); }
        this.clientModuleList.add(clientModule);
    }
    public ClientModule GetClientModule(int index) { return this.clientModuleList.get(index); }
    public ArrayList<ClientModule> getClientModuleList() {
        return clientModuleList;
    }
    public void setClientModuleList(ArrayList<ClientModule> clientModuleList) {
        this.clientModuleList = clientModuleList;
    }

    private float totalExpenditure;
    public float getTotalExpenditure() {
        return totalExpenditure;
    }
    public void setTotalExpenditure(float totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    private int weekNumber;
    public int getWeekNumber() {
        return weekNumber;
    }
    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    private String dateFormat;
    public String getDateFormat() {
        return dateFormat;
    }
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    private float percentageRate;
    public float getPercentageRate() {
        return this.percentageRate;
    }
    public void setPercentageRate(float percentageRate) {
        this.percentageRate = percentageRate;
    }
}
