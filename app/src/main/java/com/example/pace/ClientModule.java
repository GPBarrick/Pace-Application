package com.example.pace;
public class ClientModule {
<<<<<<< HEAD

private String dateText;
private int imageResource;

    //constructor for variable.
    public ClientModule(String dateText, int imageResource){
        this.dateText = dateText;
        this.imageResource = imageResource;
    }

    //getters
    public String getDateText() { return dateText; }
    public int getImageResource() { return imageResource; }

    //Setters
    public void setDateText(String dateText) { this.dateText = dateText; }
    public void setImageResource(int imageResource) { this.imageResource = imageResource; }
=======
    private String DateText;
    private int ImageResource;

    //constructor for variable.
    public ClientModule(String DateText, int ImageResource){
        this.DateText = DateText;
        this.ImageResource = ImageResource;
    }

    //getters
    public String getDateText() {
        return DateText;
    }
    public int getImageResource() {
        return ImageResource;
    }

    //Setters
    public void setDateText(String dateText) {
        DateText = dateText;
    }
    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }
>>>>>>> saul
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