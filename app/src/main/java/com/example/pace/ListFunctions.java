package com.example.pace;

import java.util.ArrayList;

public class ListFunctions {

    // Calculate the percentage increase or decrease
    public float calculatePercentage(float original, float newNumber) {
        float calculation = original - newNumber;
        calculation = (calculation / newNumber) * 100.0f;
        return calculation;
    }

    // Organize CalendarData into list of WeeklyData
    public ArrayList<WeeklyData> organizeCalendarData(ArrayList<CalendarData> calendarDataList) {
        ArrayList<WeeklyData> weeklyDataList = new ArrayList<>();

        for (int i = 0; i < calendarDataList.size(); ++i) {

            // TODO: organize here

        }

        return weeklyDataList;
    }
}
