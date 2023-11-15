package com.example.pace;

import java.util.ArrayList;

public class WeeklyData {
    private int startMonth, endMonth;
    private int startDay, endDay;
    private int startYear, endYear;

    private ArrayList<ClientModule> clientModuleList;

    public void AddClientModule(ClientModule clientModule) { this.clientModuleList.add(clientModule); }
    public ClientModule GetClientModule(int index) { return this.clientModuleList.get(index); }

    private float totalExpenditure;

    public WeeklyData(int startMonth, int endMonth, int startDay, int endDay, int startYear, int endYear, ArrayList<ClientModule> clientModuleList) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startYear = startYear;
        this.endYear = endYear;
        this.clientModuleList = clientModuleList;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public ArrayList<ClientModule> getClientModuleList() {
        return clientModuleList;
    }

    public void setClientModuleList(ArrayList<ClientModule> clientModuleList) {
        this.clientModuleList = clientModuleList;
    }

    public float getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(float totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }
}
