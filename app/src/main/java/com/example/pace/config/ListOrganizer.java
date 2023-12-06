package com.example.pace.config;

import com.example.pace.clientuser.ClientData;
import com.example.pace.clientuser.ClientDataDailyList;
import com.example.pace.clientuser.ClientDataMonthlyList;
import com.example.pace.clientuser.ClientDataWeeklyList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class ListOrganizer {

    public ListOrganizer() {}
    public static ListOrganizer listOrganizer;
    public static ListOrganizer getInstance() {
        if (listOrganizer == null) {
            synchronized (ListOrganizer.class) {
                if (listOrganizer == null) {
                    listOrganizer = new ListOrganizer();
                }
            }
        }
        return listOrganizer;
    }

    public void initDailyListData(ClientData clientData) {

        if (ListHolder.getInstance().outputDailyDataList.size() == 0 && ListHolder.getInstance().clientDataList.size() > 0) {
            ArrayList<ClientData> clientDataList = new ArrayList<>();
            clientDataList.add(clientData);
            ListHolder.getInstance().outputDailyDataList.add(new ClientDataDailyList(clientDataList));

            ListHolder.getInstance().outputDailyDataList.get(0).setMonth(clientData.getMonth());
            ListHolder.getInstance().outputDailyDataList.get(0).setDay(clientData.getDay());
            ListHolder.getInstance().outputDailyDataList.get(0).setYear(clientData.getYear());

            ListHolder.getInstance().outputDailyDataList.get(0).setExpenditure(clientData.getExpenditureCalculation());
            ListHolder.getInstance().outputDailyDataList.get(0).setAverageMpg(clientData.getMpg());
            ListHolder.getInstance().outputDailyDataList.get(0).setAverageGasPrice(clientData.getGasPrice());
            ListHolder.getInstance().outputDailyDataList.get(0).setAverageDistance(clientData.getDistance());
            ListHolder.getInstance().outputDailyDataList.get(0).setAverageIncome(clientData.getIncome());

            ListHolder.getInstance().dailyListAdapter.notifyItemInserted(ListHolder.getInstance().outputDailyDataList.size());

        } else {
            boolean wasFound = false;
            for (int i = 0; i < ListHolder.getInstance().outputDailyDataList.size(); ++i) {
                if (
                        ListHolder.getInstance().outputDailyDataList.get(i).getMonth() == clientData.getMonth()
                                && ListHolder.getInstance().outputDailyDataList.get(i).getDay() == clientData.getDay()
                                && ListHolder.getInstance().outputDailyDataList.get(i).getYear() == clientData.getYear()
                ) {
                    ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().add(clientData);
                    ListHolder.getInstance().outputDailyDataList.get(i).setExpenditure(
                            ListHolder.getInstance().outputDailyDataList.get(i).getExpenditure() + clientData.getExpenditureCalculation()
                    );

                    float averageMpgTotal = 0.0f;
                    for (int t = 0; t < ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size(); ++t) {
                        averageMpgTotal = averageMpgTotal + ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().get(t).getMpg();
                    }
                    if (ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size() > 0) {
                        averageMpgTotal = averageMpgTotal / ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size();
                    }
                    ListHolder.getInstance().outputDailyDataList.get(i).setAverageMpg(averageMpgTotal);

                    float averageGasPriceTotal = 0.2f;
                    for (int t = 0; t < ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size(); ++t) {
                        averageGasPriceTotal = averageGasPriceTotal + ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().get(t).getGasPrice();
                    }
                    if (ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size() > 0) {
                        averageGasPriceTotal = averageGasPriceTotal / ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size();
                    }
                    ListHolder.getInstance().outputDailyDataList.get(i).setAverageGasPrice(averageGasPriceTotal);

                    float averageDistanceTotal = 0.0f;
                    for (int t = 0; t < ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size(); ++t) {
                        averageDistanceTotal = averageDistanceTotal + ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().get(t).getDistance();
                    }
                    if (ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size() > 0) {
                        averageDistanceTotal = averageDistanceTotal / ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size();
                    }
                    ListHolder.getInstance().outputDailyDataList.get(i).setAverageDistance(averageDistanceTotal);

                    float averageIncomeTotal = 0.0f;
                    for (int t = 0; t < ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size(); ++t) {
                        averageIncomeTotal = averageIncomeTotal + ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().get(t).getIncome();
                    }
                    if (ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size() > 0) {
                        averageIncomeTotal = averageIncomeTotal / ListHolder.getInstance().outputDailyDataList.get(i).getClientDataList().size();
                    }
                    ListHolder.getInstance().outputDailyDataList.get(i).setAverageIncome(averageIncomeTotal);

                    ListHolder.getInstance().dailyListAdapter.notifyDataSetChanged();
                    wasFound = true;
                    break;
                }
            }
            if (!wasFound) {
                ArrayList<ClientData> clientDataList = new ArrayList<>();
                clientDataList.add(clientData);
                ListHolder.getInstance().outputDailyDataList.add(new ClientDataDailyList(clientDataList));

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setMonth(clientData.getMonth());

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setDay(clientData.getDay());

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setYear(clientData.getYear());

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setExpenditure(clientData.getExpenditureCalculation());

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setAverageMpg(clientData.getMpg());

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setAverageGasPrice(clientData.getGasPrice());

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setAverageDistance(clientData.getDistance());

                ListHolder.getInstance().outputDailyDataList.get(
                        ListHolder.getInstance().outputDailyDataList.size() - 1
                ).setAverageIncome(clientData.getIncome());

                ListHolder.getInstance().dailyListAdapter.notifyItemInserted(ListHolder.getInstance().outputDailyDataList.size());
            }
        }
    }
    public void organizeDailyList(ArrayList<ClientDataDailyList> clientDailyDataList) {
        Collections.sort(clientDailyDataList, new Comparator<ClientDataDailyList>() {
            @Override
            public int compare(ClientDataDailyList d1, ClientDataDailyList d2) {
                if (d1.getYear() != d2.getYear()) {
                    return Integer.compare(d2.getYear(), d1.getYear());
                }
                if (d1.getMonth() != d2.getMonth()) {
                    return Integer.compare(d2.getMonth(), d1.getMonth());
                }
                return Integer.compare(d2.getDay(), d1.getDay());
            }
        });
        calculateDailyListPercentages();
        if (ListHolder.getInstance().dailyListAdapter != null) {
            ListHolder.getInstance().dailyListAdapter.notifyDataSetChanged();
        }
    }
    private void calculateDailyListPercentages() {
        /* Percentage Difference = ((New Value - Old Value) / |Old Value|) * 100 */
        float percentageDifference = 0.0f;
        for (int i = 0; i < ListHolder.getInstance().outputDailyDataList.size(); ++i) {
            if (i + 1 == ListHolder.getInstance().outputDailyDataList.size()) {
                ListHolder.getInstance().outputDailyDataList.get(i).setPercentageDifference(0.0f);
            } else {
                percentageDifference =
                        (ListHolder.getInstance().outputDailyDataList.get(i).getPercentageDifference()
                        - ListHolder.getInstance().outputDailyDataList.get(i + 1).getPercentageDifference())
                        / ListHolder.getInstance().outputDailyDataList.get(i + 1).getPercentageDifference()
                        * 100.0f;
                ListHolder.getInstance().outputDailyDataList.get(i).setPercentageDifference(percentageDifference);
            }
        }
    }
    public void initWeeklyListData(ClientData clientData) {

        if (ListHolder.getInstance().outputWeeklyDataList.size() == 0){

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, clientData.getYear());
            calendar.set(Calendar.WEEK_OF_YEAR, clientData.getWeekOfYear());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            int startDay = calendar.get(Calendar.DAY_OF_MONTH);
            int startMonth = calendar.get(Calendar.MONTH) + 1;
            int startYear = calendar.get(Calendar.YEAR);

            calendar.add(Calendar.DAY_OF_WEEK, 6);
            int endDay = calendar.get(Calendar.DAY_OF_MONTH);
            int endMonth = calendar.get(Calendar.MONTH) + 1;
            int endYear = calendar.get(Calendar.YEAR);
            calendar.clear();

            ArrayList<ClientData> clientDataList = new ArrayList<>();
            clientDataList.add(clientData);
            ListHolder.getInstance().outputWeeklyDataList.add(new ClientDataWeeklyList(clientDataList));
            ListHolder.getInstance().outputWeeklyDataList.get(0).setStartDay(startDay);
            ListHolder.getInstance().outputWeeklyDataList.get(0).setEndDay(endDay);
            ListHolder.getInstance().outputWeeklyDataList.get(0).setStartMonth(startMonth);
            ListHolder.getInstance().outputWeeklyDataList.get(0).setEndMonth(endMonth);
            ListHolder.getInstance().outputWeeklyDataList.get(0).setStartYear(startYear);
            ListHolder.getInstance().outputWeeklyDataList.get(0).setEndYear(endYear);
            ListHolder.getInstance().outputWeeklyDataList.get(0).setWeekOfYear(clientData.getWeekOfYear());
            ListHolder.getInstance().outputWeeklyDataList.get(0).setExpenditure(clientData.getExpenditureCalculation());

        } else {
            boolean wasFound = false;
            for (int r = 0; r < ListHolder.getInstance().outputWeeklyDataList.size(); ++r) {
                if (ListHolder.getInstance().outputWeeklyDataList.get(r).getWeekOfYear() == clientData.getWeekOfYear()) {
                    ListHolder.getInstance().outputWeeklyDataList.get(r).getClientDataList().add(clientData);
                    ListHolder.getInstance().outputWeeklyDataList.get(r).setExpenditure(
                            ListHolder.getInstance().outputWeeklyDataList.get(r).getExpenditure()
                                    + clientData.getExpenditureCalculation()
                    );
                    if (ListHolder.getInstance().weeklyListAdapter != null) {
                        ListHolder.getInstance().weeklyListAdapter.notifyDataSetChanged();
                    }
                    wasFound = true;
                    break;
                }
            }
            if (!wasFound) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, clientData.getYear());
                calendar.set(Calendar.WEEK_OF_YEAR, clientData.getWeekOfYear());
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

                int startDay = calendar.get(Calendar.DAY_OF_MONTH);
                int startMonth = calendar.get(Calendar.MONTH) + 1;
                int startYear = calendar.get(Calendar.YEAR);

                calendar.add(Calendar.DAY_OF_WEEK, 6);
                int endDay = calendar.get(Calendar.DAY_OF_MONTH);
                int endMonth = calendar.get(Calendar.MONTH) + 1;
                int endYear = calendar.get(Calendar.YEAR);
                calendar.clear();

                ArrayList<ClientData> clientDataList = new ArrayList<>();
                clientDataList.add(clientData);
                ListHolder.getInstance().outputWeeklyDataList.add(new ClientDataWeeklyList(clientDataList));
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setStartDay(startDay);
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setEndDay(endDay);
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setStartMonth(startMonth);
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setEndMonth(endMonth);
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setStartYear(startYear);
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setEndYear(endYear);
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setWeekOfYear(clientData.getWeekOfYear());
                ListHolder.getInstance().outputWeeklyDataList.get(
                        ListHolder.getInstance().outputWeeklyDataList.size() - 1
                ).setExpenditure(clientData.getExpenditureCalculation());

                //ListHolder.getInstance().weeklyListAdapter.notifyItemInserted(ListHolder.getInstance().outputWeeklyDataList.size() - 1);
            }
        }
    }
    public void organizeWeeklyList(ArrayList<ClientDataWeeklyList> clientDataWeeklyList) {

    }
    private void calculateWeeklyListPercentages() {

    }
    public void initMonthlyListData(ClientData clientData) {
        if (ListHolder.getInstance().outputMonthlyDataList.size() == 0) {
            ArrayList<ClientData> clientDataList = new ArrayList<>();
            clientDataList.add(clientData);
            ListHolder.getInstance().outputMonthlyDataList.add(new ClientDataMonthlyList(clientDataList));
            ListHolder.getInstance().outputMonthlyDataList.get(0).setMonth(clientData.getMonth());
            ListHolder.getInstance().outputMonthlyDataList.get(0).setYear(clientData.getYear());
            ListHolder.getInstance().outputMonthlyDataList.get(0).setExpenditure(clientData.getExpenditureCalculation());
        } else {
            boolean wasFound = false;
            for (int i = 0; i < ListHolder.getInstance().outputMonthlyDataList.size(); ++i) {
                if (ListHolder.getInstance().outputMonthlyDataList.get(i).getMonth() == clientData.getMonth()) {
                    ListHolder.getInstance().outputMonthlyDataList.get(i).getClientDataList().add(clientData);
                    ListHolder.getInstance().outputMonthlyDataList.get(i).setExpenditure(
                            ListHolder.getInstance().outputMonthlyDataList.get(i).getExpenditure()
                                    + clientData.getExpenditureCalculation()
                    );
                    ListHolder.getInstance().outputMonthlyDataList.get(i).setMonth(clientData.getMonth());
                    ListHolder.getInstance().outputMonthlyDataList.get(i).setYear(clientData.getYear());
                    if (ListHolder.getInstance().monthlyListAdapter != null) {
                        ListHolder.getInstance().monthlyListAdapter.notifyDataSetChanged();
                    }
                    wasFound = true;
                    break;
                }
            }
            if (!wasFound) {
                ArrayList<ClientData> clientDataList = new ArrayList<>();
                clientDataList.add(clientData);
                ListHolder.getInstance().outputMonthlyDataList.add(new ClientDataMonthlyList(clientDataList));
                ListHolder.getInstance().outputMonthlyDataList.get(
                        ListHolder.getInstance().outputMonthlyDataList.size() - 1
                ).setMonth(clientData.getMonth());
                ListHolder.getInstance().outputMonthlyDataList.get(
                        ListHolder.getInstance().outputMonthlyDataList.size() - 1
                ).setYear(clientData.getYear());
                ListHolder.getInstance().outputMonthlyDataList.get(
                        ListHolder.getInstance().outputMonthlyDataList.size() - 1
                ).setExpenditure(clientData.getExpenditureCalculation());
                ListHolder.getInstance().monthlyListAdapter.notifyItemInserted(ListHolder.getInstance().outputMonthlyDataList.size());
            }
        }
    }
    public void organizeMonthlyList(ArrayList<ClientDataMonthlyList> clientDataMonthlyList) {

    }
    private void calculateMonthlyListPercentages() {

    }
}
