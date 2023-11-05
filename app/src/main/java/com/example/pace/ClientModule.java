package com.example.pace;
public class ClientModule {

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
}

/* 11/5/2023 Create the class members that will represent the statistics we will be manipulating
* Must have getters and setters, as well as constructor initialization. Check the data you are using
* by comparing it to null as a safety feature. */