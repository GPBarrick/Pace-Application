package com.example.pace.clientuser;

import java.util.ArrayList;

public class ClientDataMonthlyList {
    private int month;
    public void setMonth(int month) { this.month = month; }
    public int getMonth() { return this.month; }

    private int year;
    public void setYear(int year) { this.year = year; }
    public int getYear() { return this.year; }

    private float expenditure;
    public void setExpenditure(float expenditure) { this.expenditure = expenditure; }
    public float getExpenditure() { return this.expenditure; }

    private ArrayList<ClientData> clientDataList;
    public void setClientDataList(ArrayList<ClientData> clientDataList) { this.clientDataList = clientDataList; }
    public ArrayList<ClientData> getClientDataList() { return this.clientDataList; }

    public ClientDataMonthlyList(ArrayList<ClientData> clientDataList) {
        this.clientDataList = clientDataList;
    }
}
