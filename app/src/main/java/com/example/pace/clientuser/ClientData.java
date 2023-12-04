package com.example.pace.clientuser;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "client_table")
public class ClientData {
    @ColumnInfo(name = "client_key")
    @PrimaryKey(autoGenerate = true)
    private int primaryKey;
    public void setPrimaryKey(int primaryKey) { this.primaryKey = primaryKey; }
    public int getPrimaryKey() { return this.primaryKey; }

    @ColumnInfo(name = "client_month")
    private int month;
    public void setMonth(int month) { this.month = month; }
    public int getMonth() { return this.month; }

    @ColumnInfo(name = "client_day")
    private int day;
    public void setDay(int day) { this.day = day; }
    public int getDay() { return this.day; }

    @ColumnInfo(name = "client_year")
    private int year;
    public void setYear(int year) { this.year = year; }
    public int getYear() { return this.year; }

    @ColumnInfo(name = "client_mpg")
    private float mpg;
    public void setMpg(float mpg) { this.mpg = mpg; }
    public float getMpg() { return this.mpg; }

    @ColumnInfo(name = "client_gasPrice")
    private float gasPrice;
    public void setGasPrice(float gasPrice) { this.gasPrice = gasPrice; }
    public float getGasPrice() { return this.gasPrice; }

    @ColumnInfo(name = "client_distance")
    private float distance;
    public void setDistance(float distance) { this.distance = distance; }
    public float getDistance() { return this.distance; }

    @ColumnInfo(name = "client_income")
    private float income;
    public void setIncome(float income) { this.income = income; }
    public float getIncome() { return this.income; }

    @ColumnInfo(name = "client_percentageCalculation")
    private float percentageCalculation;
    public void setPercentageCalculation(float percentageCalculation) { this.percentageCalculation = percentageCalculation; }
    public float getPercentageCalculation() { return this.percentageCalculation; }

    @ColumnInfo(name = "client_expenditureCalculation")
    private float expenditureCalculation;
    public void setExpenditureCalculation(float expenditureCalculation) { this.expenditureCalculation = expenditureCalculation; }
    public float getExpenditureCalculation() { return this.expenditureCalculation; }

    @ColumnInfo(name = "client_weekOfYear")
    private int weekOfYear;
    public void setWeekOfYear(int weekOfYear) { this.weekOfYear = weekOfYear; }
    public int getWeekOfYear() { return this.weekOfYear; }

    private String formattedDateCard;
    public void setFormattedDateCard(String formattedDateCard) { this.formattedDateCard = formattedDateCard; }
    public String getFormattedDateCard() { return this.formattedDateCard; }

    private String formattedDateList;
    public void setFormattedDateList(String formattedDateList) { this.formattedDateList = formattedDateList; }
    public String getFormattedDateList() { return this.formattedDateList; }

    public ClientData() {/* Default constructor */}
    public ClientData(int month, int day, int year, float mpg, float gasPrice, float distance, float income, int weekOfYear) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.mpg = mpg;
        this.gasPrice = gasPrice;
        this.distance = distance;
        this.income = income;
        this.weekOfYear = weekOfYear;
    }
}
