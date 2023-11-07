package com.example.pace;
import java.util.ArrayList;
public class ClientModule {

    private String dateText;
    private int imageResource;

    private float distance;
    private float milesPerGallon;
    private float gasPrice;
    private float income;

    public ClientModule(String DateText){
        this.dateText = DateText;
    }

    public ClientModule(float distance, float milesPerGallon, float gasPrice, float income) {
        this.distance = distance;
        this.milesPerGallon = milesPerGallon;
        this.gasPrice = gasPrice;
        this.income = income;
    }

    public void setDateText(String dateText) { this.dateText = dateText; }
    public String getDateText() { return this.dateText; }

    public void setImageResource(int imageResource) { this.imageResource = imageResource; }
    public int getImageResource() { return this.imageResource; }

    public void setDistance(float distance) { this.distance = distance; }
    public float getDistance() { return this.distance; }

    public void setMilesPerGallon(float milesPerGallon) { this.milesPerGallon = milesPerGallon; }
    public float getMilesPerGallon() { return this.milesPerGallon; }

    public void setGasPrice(float gasPrice) { this.gasPrice = gasPrice; }
    public float getGasPrice() { return this.gasPrice; }

    public void setIncome(float income) { this.income = income; }
    public float getIncome() { return income; }

    public float CalculateExpenditure() {
        return (this.distance / this.milesPerGallon) * this.gasPrice;
    }
    public float CalculateExpenditureFromIncome() {
        return ((this.distance / this.milesPerGallon) * this.gasPrice) - this.income;
    }
}

/* 11/5/2023
 *   Create private members with getters and setters to use when calculating the information below.
 *
 *   Create calculation functions to calculate:
 * - Amount of money spent on gas when driving a certain distance while considering, gas price, and MPG
 * - Averages for routes, distance, money spent on gas, income
 *
 * - The averages will be done by creating a function with parameters of (ArrayList<ClientModule>)
 * and using that parameter to view the list of current client modules we will be using to calculate averages.
 * For example, I will call this function in MainActivity.java and pass in an ArrayList of ClientModules and
 * the function within this class is responsible for calculating the averages based on the list provided */

/* 11/5/2023 Create the class members that will represent the statistics we will be manipulating
 * Must have getters and setters, as well as constructor initialization. Check the data you are using
 * by comparing it to null as a safety feature. */