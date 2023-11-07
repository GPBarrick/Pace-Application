package com.example.pace;
import java.util.ArrayList;
public class Test {

    /* 11/5/2023 Function responsible for populating data structure*/
    public ArrayList<ClientModule> populateClientModuleListDataSet1() {
        ArrayList<ClientModule> clientModuleList = new ArrayList<>();
        clientModuleList.add(new ClientModule(5, 29, 3.98f, 0));
        clientModuleList.add(new ClientModule(2, 28, 3.98f, 0));
        clientModuleList.add(new ClientModule(6, 32, 2.98f, 0));
        return clientModuleList;
    }

    public ArrayList<ClientModule> populateClientModuleListDataSet2() {
        ArrayList<ClientModule> clientModuleList = new ArrayList<>();
        clientModuleList.add(new ClientModule(5, 29, 3.98f, 0));
        clientModuleList.add(new ClientModule(2, 10, 0.98f, 0));
        clientModuleList.add(new ClientModule(10, 32, 2.98f, 0));
        return clientModuleList;
    }

    public ArrayList<ClientModule> populateClientModuleListDataSet3() {
        ArrayList<ClientModule> clientModuleList = new ArrayList<>();
        clientModuleList.add(new ClientModule(3, 29, 50.98f, 0));
        clientModuleList.add(new ClientModule(0, 28, 10.98f, 0));
        clientModuleList.add(new ClientModule(1, 32, 1.98f, 0));
        return clientModuleList;
    }

    /* 11/6/2023 Function responsible for populating an ArrayList<CalendarData> */
    public ArrayList<CalendarData> populateCalendarDataList() {
        ArrayList<CalendarData> calendarDataList = new ArrayList<>();
        calendarDataList.add(new CalendarData(populateClientModuleListDataSet1(), 11, 3, 2023));
        calendarDataList.add(new CalendarData(populateClientModuleListDataSet1(), 10, 3, 2023));
        calendarDataList.add(new CalendarData(populateClientModuleListDataSet3(), 9, 3, 2023));
        calendarDataList.add(new CalendarData(populateClientModuleListDataSet2(), 8, 3, 2023));
        calendarDataList.add(new CalendarData(populateClientModuleListDataSet3(), 7, 3, 2023));
        calendarDataList.add(new CalendarData(populateClientModuleListDataSet1(), 6, 3, 2023));
        calendarDataList.add(new CalendarData(populateClientModuleListDataSet2(), 5, 3, 2023));
        return calendarDataList;
    }
}
