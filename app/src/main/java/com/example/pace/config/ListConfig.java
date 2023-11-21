package com.example.pace.config;

import com.example.pace.clientuser.*;

import java.util.ArrayList;

public class ListConfig implements ListFunctions {

    public ListConfig() {}
    public static ListConfig listConfigInstance;
    public static ListConfig getInstance() {
        if (listConfigInstance == null) {
            synchronized (ListConfig.class) {
                if (listConfigInstance == null) {
                    listConfigInstance = new ListConfig();
                }
            }
        }
        return listConfigInstance;
    }

    @Override
    public void organizeDescending(ArrayList<ClientData> clientDataList) {

    }

    @Override
    public void calculatePercentages(ArrayList<ClientData> clientDataList) {

    }

    @Override
    public void setFormattedCardDate(ArrayList<ClientData> clientDataList) {

    }

    @Override
    public void setFormattedListDate(ArrayList<ClientData> clientDataList) {

    }
}
