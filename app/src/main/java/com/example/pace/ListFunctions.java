package com.example.pace;

import android.util.Log;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class ListFunctions {

    // Calculate the percentage increase or decrease
    public float calculatePercentage(float original, float newNumber) {
        float calculation = original - newNumber;
        calculation = (calculation / newNumber) * 100.0f;
        return calculation;
    }

    // Organize CalendarData into list of WeeklyData
    public ArrayList<WeeklyData> organizeCalendarData(@NonNull ArrayList<CalendarData> calendarDataList) {
        ArrayList<WeeklyData> weeklyDataList = new ArrayList<>();
        ArrayList<CalendarData> calendarDataCopy = new ArrayList<>();

        // Deep copy
        for (CalendarData obj : calendarDataList) {
            calendarDataCopy.add(new CalendarData(obj));
        }

        for (int calendarNdx = 0; calendarNdx < calendarDataCopy.size(); ++calendarNdx) {
            int year = calendarDataCopy.get(calendarNdx).getYear();
            int month = calendarDataCopy.get(calendarNdx).getMonth();
            int day = calendarDataCopy.get(calendarNdx).getDay();

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.setMinimalDaysInFirstWeek(4);
            int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);

            if (weeklyDataList.size() > 0) {
                boolean weekNumFound = false;
                for (int weeklyNdx = 0; weeklyNdx < weeklyDataList.size(); ++weeklyNdx) {

                    if (weeklyDataList.get(weeklyNdx).getWeekNumber() == weekNumber) {
                        weeklyDataList.get(weeklyNdx).getClientModuleList().addAll(calendarDataCopy.get(calendarNdx).getClientModuleList());
                        weekNumFound = true;
                        break;
                    }
                }
                if (!weekNumFound) {
                    WeeklyData weeklyDataModule = new WeeklyData(weekNumber);
                    weeklyDataModule.setClientModuleList(calendarDataCopy.get(calendarNdx).getClientModuleList());
                    weeklyDataList.add(weeklyDataModule);
                }

            } else {
                WeeklyData weeklyDataModule = new WeeklyData(weekNumber);
                weeklyDataModule.setClientModuleList(calendarDataCopy.get(calendarNdx).getClientModuleList());
                weeklyDataList.add(weeklyDataModule);
            }
        }
        return weeklyDataList;
    }

    public void setWeeklyDataExpenditure(@NonNull ArrayList<WeeklyData> weeklyDataList) {
        for (int weeklyNdx = 0; weeklyNdx < weeklyDataList.size(); ++weeklyNdx) {
            weeklyDataList.get(weeklyNdx).setTotalExpenditure(0.0f);
            for (int clientNdx = 0; clientNdx < weeklyDataList.get(weeklyNdx).getClientModuleList().size(); ++clientNdx) {
                weeklyDataList.get(weeklyNdx).setTotalExpenditure(
                        weeklyDataList.get(weeklyNdx).getTotalExpenditure() +
                        weeklyDataList.get(weeklyNdx).getClientModuleList().get(clientNdx).CalculateExpenditure()
                );
            }
        }
    }

    public void setWeekDateRange(@NonNull ArrayList<WeeklyData> weeklyDataList) {
        for (int i = 0; i < weeklyDataList.size(); ++i) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, 1);

            while (calendar.get(Calendar.WEEK_OF_YEAR) != 1) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            calendar.add(Calendar.WEEK_OF_YEAR, weeklyDataList.get(i).getWeekNumber() - 1);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            // TODO: SET MONTH INDEX TO   ->   DetermineMonthName();
            String startDate = String.format("%02d/%02d", calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            calendar.add(Calendar.DAY_OF_WEEK, 6);
            String endDate = String.format("%02d/%02d", calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            weeklyDataList.get(i).setDateFormat(startDate + " to " + endDate);

            calendar.clear();
        }
    }

    public String DetermineMonthName(int monthIndex) {
        String monthName = "";
        switch (monthIndex) {
            case (1):
                monthName = "Jan";
                break;
            case (2):
                monthName = "Feb";
                break;
            case (3):
                monthName = "Mar";
                break;
            case (4):
                monthName = "Apr";
                break;
            case (5):
                monthName = "May";
                break;
            case (6):
                monthName = "Jun";
                break;
            case (7):
                monthName = "Jul";
                break;
            case (8):
                monthName = "Aug";
                break;
            case (9):
                monthName = "Sep";
                break;
            case (10):
                monthName = "Oct";
                break;
            case (11):
                monthName = "Nov";
                break;
            case (12):
                monthName = "Dec";
                break;
        }
        return monthName;
    }

    public void SetCalculatedPercentage(@NonNull ArrayList<CalendarData> calendarDataList) {
        for (int i = 0; i < calendarDataList.size(); ++i) {

            if (i + 1 < calendarDataList.size()) {
                float originalExpenditure = calendarDataList.get(i).CalculateClientExpenditure();
                float newExpenditure = calendarDataList.get(i + 1).CalculateClientExpenditure();

                if (originalExpenditure != 0) {
                    float percentageCalculation = calculatePercentage(originalExpenditure, newExpenditure);

                    calendarDataList.get(i).setPercentageCalculation(percentageCalculation);
                } else {
                    calendarDataList.get(i).setPercentageCalculation(0);
                }
            } else {
                calendarDataList.get(i).setPercentageCalculation(100.0f);
            }
        }
    }

    public void setWeeklyPercentages(@NonNull ArrayList<WeeklyData> weeklyDataList) {
        for (int i = 0; i < weeklyDataList.size(); ++i) {

            if (i + 1 < weeklyDataList.size()) {
                float originalExpenditure = weeklyDataList.get(i).getTotalExpenditure();
                float newExpenditure = weeklyDataList.get(i + 1).getTotalExpenditure();

                if (originalExpenditure != 0) {
                    float percentageCalculation = calculatePercentage(originalExpenditure, newExpenditure);
                    weeklyDataList.get(i).setPercentageRate(percentageCalculation);
                } else {
                    weeklyDataList.get(i).setPercentageRate(0);
                }
            } else {
                weeklyDataList.get(i).setPercentageRate(100.0f);
            }
        }
    }
}
