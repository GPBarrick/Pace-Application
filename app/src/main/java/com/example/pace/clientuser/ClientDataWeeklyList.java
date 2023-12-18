package com.example.pace.clientuser;

import java.util.ArrayList;

public class ClientDataWeeklyList {
    private int startMonth;
    public void setStartMonth(int month) { this.startMonth = month; }
    public int getStartMonth() { return this.startMonth; }

    private int endMonth;
    public void setEndMonth(int endMonth) { this.endMonth = endMonth; }
    public int getEndMonth() { return this.endMonth; }

    private int startDay;
    public void setStartDay(int startDay) { this.startDay = startDay; }
    public int getStartDay() { return this.startDay; }

    private int endDay;
    public void setEndDay(int endDay) { this.endDay = endDay; }
    public int getEndDay() { return this.endDay; }

    private int startYear;
    public void setStartYear(int startYear) { this.startYear = startYear; }
    public int getStartYear() { return this.startYear; }

    private int endYear;
    public void setEndYear(int endYear) { this.endYear = endYear; }
    public int getEndYear() { return this.endYear; }

    private float expenditure;
    public void setExpenditure(float expenditure) { this.expenditure = expenditure; }
    public float getExpenditure() { return this.expenditure; }

    private int weekOfYear;
    public void setWeekOfYear(int weekOfYear) { this.weekOfYear = weekOfYear; }
    public int getWeekOfYear() { return this.weekOfYear; }

    private ArrayList<ClientData> clientDataList;
    public void setClientDataList(ArrayList<ClientData> clientDataList) { this.clientDataList = clientDataList; }
    public ArrayList<ClientData> getClientDataList() { return this.clientDataList; }

    public ClientDataWeeklyList() { /* default constructor */ }
    public ClientDataWeeklyList(ArrayList<ClientData> clientDataList) {
        this.clientDataList = clientDataList;
    }
}
