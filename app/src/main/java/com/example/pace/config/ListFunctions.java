package com.example.pace.config;

import com.example.pace.clientuser.*;

import java.util.ArrayList;

public interface ListFunctions {
    void organizeDescending(ArrayList<ClientData> clientDataList);
    void calculatePercentages(ArrayList<ClientData> clientDataList);
    void setFormattedCardDate(ArrayList<ClientData> clientDataList);
    void setFormattedListDate(ArrayList<ClientData> clientDataList);
}
