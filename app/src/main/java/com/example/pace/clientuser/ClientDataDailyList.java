package com.example.pace.clientuser;

import java.util.ArrayList;

public class ClientDataDailyList {

    private int month;
    public void setMonth(int month) { this.month = month; }
    public int getMonth() { return this.month; }

    private int day;
    public void setDay(int day) { this.day = day; }
    public int getDay() { return this.day; }

    private int year;
    public void setYear(int year) { this.year = year; }
    public int getYear() { return this.year; }

    private float expenditure;
    public void setExpenditure(float expenditure) { this.expenditure = expenditure; }
    public float getExpenditure() { return this.expenditure; }

    private float averageMpg;
    public void setAverageMpg(float averageMpg) { this.averageMpg = averageMpg; }
    public float getAverageMpg() { return this.averageMpg; }

    private float averageGasPrice;
    public void setAverageGasPrice(float averageGasPrice) { this.averageGasPrice = averageGasPrice; }
    public float getAverageGasPrice() { return this.averageGasPrice; }

    private float averageDistance;
    public void setAverageDistance(float averageDistance) { this.averageDistance = averageDistance; }
    public float getAverageDistance() { return this.averageDistance; }

    private float averageIncome;
    public void setAverageIncome(float averageIncome) { this.averageIncome = averageIncome; }
    public float getAverageIncome() { return this.averageIncome; }

    private String formattedDate;
    public void setFormattedDate(String formattedDate) { this.formattedDate = formattedDate; }
    public String getFormattedDate() { return this.formattedDate; }

    private float percentageDifference;
    public void setPercentageDifference(float percentageDifference) { this.percentageDifference = percentageDifference; }
    public float getPercentageDifference() { return this.percentageDifference; }

    private ArrayList<ClientData> clientDataList;
    public void setClientDataList(ArrayList<ClientData> clientDataList) { this.clientDataList = clientDataList; }
    public ArrayList<ClientData> getClientDataList() { return this.clientDataList; }

    public ClientDataDailyList() { /* default constructor */ }
    public ClientDataDailyList(ArrayList<ClientData> clientDataList) {
        this.clientDataList = clientDataList;
    }
}