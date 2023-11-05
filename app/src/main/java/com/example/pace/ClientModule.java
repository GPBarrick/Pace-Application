package com.example.pace;
public class ClientModule {
private String DateText;
private int ImageResource;

//constructor for variable.
public void ClientModule(String DateText, int ImageResource){
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
}

/* 11/5/2023 Create the class members that will represent the statistics we will be manipulating
* Must have getters and setters, as well as constructor initialization. Check the data you are using
* by comparing it to null as a safety feature. */