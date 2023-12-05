package com.example.pace.fragmentinputlayouts;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pace.R;
import com.example.pace.clientuser.ClientData;
import com.example.pace.clientuser.ClientDataDailyList;
import com.example.pace.clientuser.ClientDataMonthlyList;
import com.example.pace.clientuser.ClientDataWeeklyList;
import com.example.pace.config.ListHolder;
import com.example.pace.databinding.FragmentClientInputBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class InputIncomeFragment extends Fragment {

    public FragmentClientInputBinding fragmentClientInputBinding;
    public InputIncomeFragment(FragmentClientInputBinding fragmentClientInputBinding) {
        this.fragmentClientInputBinding = fragmentClientInputBinding;
        this.errorBool = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.incomeInputText.getText().clear();
    }

    private ArrayList<Fragment> fragmentList;
    public void setFragmentList(ArrayList<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_income, container, false);

        initViews(view);

        initListeners();

        return view;
    }

    public ImageButton submitButton, backButton;
    public EditText incomeInputText;
    public ImageView errorIcon;
    private void initViews(View view) {
        this.submitButton = view.findViewById(R.id.input_income_submitButton);
        this.backButton = view.findViewById(R.id.input_income_backButton);
        this.incomeInputText = view.findViewById(R.id.input_income_editText);
        this.errorIcon = view.findViewById(R.id.input_income_errorIcon);

        this.errorIcon.setVisibility(View.INVISIBLE);

    }

    private void initListeners() {

        this.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (incomeInputText.getText().length() > 0) {
                    fragmentClientInputBinding.getInputDataBinding().setIncome(Float.parseFloat(String.valueOf(incomeInputText.getText())));
                    fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.incomeText.setText(
                            "$" + incomeInputText.getText()
                    );

                    checkCalendarBinding();

                    errorIcon.setVisibility(View.INVISIBLE);
                    errorBool = false;

                    addClientDataToStructure();
                } else {

                    checkCalendarBinding();

                    errorIcon.setVisibility(View.VISIBLE);
                    errorBool = true;
                }
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentClientInputBinding.clientInputViewPager.setCurrentItem(2);
            }
        });
        this.incomeInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (incomeInputText.getText().length() > 0) {
                        fragmentClientInputBinding.getInputDataBinding().setIncome(Float.parseFloat(String.valueOf(incomeInputText.getText())));
                        fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.incomeText.setText(
                                "$" + incomeInputText.getText()
                        );

                        checkCalendarBinding();

                        errorIcon.setVisibility(View.INVISIBLE);
                        errorBool = false;

                        addClientDataToStructure();

                    } else {

                        checkCalendarBinding();

                        errorIcon.setVisibility(View.VISIBLE);
                        errorBool = true;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public boolean errorBool;
    public boolean getErrorBool() {
        return errorBool;
    }

    public void checkCalendarBinding() {

        if(fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.getDateValid()) {
            fragmentClientInputBinding.clientInputErrorIcon.setVisibility(View.INVISIBLE);
        } else {
            fragmentClientInputBinding.clientInputErrorIcon.setVisibility(View.VISIBLE);
        }
    }

    public void addClientDataToStructure() {
        if (fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.getDateValid()) {
            ClientData clientData = new ClientData(
                    fragmentClientInputBinding.getInputDataBinding().getMonth(),
                    fragmentClientInputBinding.getInputDataBinding().getDay(),
                    fragmentClientInputBinding.getInputDataBinding().getYear(),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getMpg()),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getGasPrice()),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getDistance()),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getIncome()),
                    fragmentClientInputBinding.getInputDataBinding().getWeekOfYear()
            );
            ListHolder.getInstance().clientDataList.add(clientData);

            setExpenditure(clientData);

            initDailyListData(clientData);
            initWeeklyListData(clientData);
            initMonthlyListData(clientData);

            fragmentClientInputBinding.clientInputViewPager.setCurrentItem(0);
            fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.resetAllCalendarCardValues();

            for (int i = 0; i < this.fragmentList.size(); ++i) {
                fragmentList.get(i).onDestroy();
            }

            fragmentClientInputBinding.getInputDataBinding().getCalendarDate().getText().clear();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ListHolder.getInstance().mainActivityPager.setCurrentItem(0);
                }
            }, 400);
        }
    }

    public void setExpenditure(ClientData clientData) {
        clientData.setExpenditureCalculation(((clientData.getDistance() / clientData.getMpg()) * clientData.getGasPrice()) - clientData.getIncome());
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

                ListHolder.getInstance().dailyListAdapter.notifyItemInserted(ListHolder.getInstance().outputDailyDataList.size());
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
                    ListHolder.getInstance().weeklyListAdapter.notifyDataSetChanged();
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

                ListHolder.getInstance().weeklyListAdapter.notifyItemInserted(ListHolder.getInstance().outputWeeklyDataList.size());
            }
        }
        debugDateDetails();
    }

    private void debugDateDetails() {
        for (int i = 0; i < ListHolder.getInstance().outputWeeklyDataList.size(); ++i) {
            Log.v(
                    "START.END.DATE.DETAILS",
                    "sd:"+ String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(i).getStartDay())
                            +" ed:"+ String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(i).getEndDay())
                            +" sm:"+ String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(i).getStartMonth())
                            +" em:"+ String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(i).getEndMonth())
                            +" sy:"+ String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(i).getStartYear())
                            +" ey:"+ String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(i).getEndYear())
                            +" wof:"+ String.valueOf(ListHolder.getInstance().outputWeeklyDataList.get(i).getWeekOfYear())
            );
        }
    }

    private void initMonthlyListData(ClientData clientData) {
        if (ListHolder.getInstance().outputMonthlyDataList.size() == 0) {
            ArrayList<ClientData> clientDataList = new ArrayList<>();
            clientDataList.add(clientData);
            ListHolder.getInstance().outputMonthlyDataList.add(new ClientDataMonthlyList(clientDataList));
            ListHolder.getInstance().outputMonthlyDataList.get(0).setMonth(clientData.getMonth());
            ListHolder.getInstance().outputMonthlyDataList.get(0).setYear(clientData.getYear());
        } else {
            boolean wasFound = false;
            for (int i = 0; i < ListHolder.getInstance().outputMonthlyDataList.size(); ++i) {
                if (ListHolder.getInstance().outputMonthlyDataList.get(i).getMonth() == clientData.getMonth()) {
                    ListHolder.getInstance().outputMonthlyDataList.get(i).getClientDataList().add(clientData);
                    ListHolder.getInstance().outputMonthlyDataList.get(i).setMonth(clientData.getMonth());
                    ListHolder.getInstance().outputMonthlyDataList.get(i).setYear(clientData.getYear());
                    ListHolder.getInstance().monthlyListAdapter.notifyDataSetChanged();
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
                ListHolder.getInstance().monthlyListAdapter.notifyItemInserted(ListHolder.getInstance().outputMonthlyDataList.size());
            }
        }
    }
}