package com.example.pace.databinding;

import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;

import com.example.pace.fragmentelements.ClientInputCardFragment;

import java.util.Calendar;

public class ClientInputDataBinding extends BaseObservable {

    private boolean isCalendarVisible;
    private CalendarView calendarView;
    public CalendarView getCalendarView() { return this.calendarView; }
    private EditText calendarDate;
    public EditText getCalendarDate() { return this.calendarDate; }
    private ImageView errorIcon;
    public ImageView getErrorIcon() { return this.errorIcon; }
    public ClientInputDataBinding(CalendarView calendarView, EditText calendarDate, ImageView errorIcon) {
        this.calendarView = calendarView;
        this.calendarView.setVisibility(CalendarView.INVISIBLE);

        this.isCalendarVisible = false;
        this.calendarDate = calendarDate;

        this.errorIcon = errorIcon;
        //this.clientInputCardFragment.setDateValid(false);
        this.errorIcon.setVisibility(View.INVISIBLE);

    }

    public void onCalendarButtonClick(View view) {
        if (this.isCalendarVisible) {
            this.calendarView.setVisibility(CalendarView.INVISIBLE);
            this.isCalendarVisible = false;
        } else {
            this.calendarView.setVisibility(CalendarView.VISIBLE);
            this.isCalendarVisible = true;
        }
    }

    public void getCalendarData(CalendarView view, int year, int month, int dayOfMonth) {
        this.isCalendarVisible = false;
        this.calendarView.setVisibility(CalendarView.INVISIBLE);
        Calendar calendar = Calendar.getInstance();
        this.month = month;
        this.day = dayOfMonth;
        this.year = year;
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.YEAR, year);
        this.calendarDate.setText(calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR));
        this.clientInputCardFragment.dateText.setText(String.valueOf(this.calendarDate.getText()));
        this.clientInputCardFragment.setDateValid(true);
        this.errorIcon.setVisibility(View.INVISIBLE);
    }

    public ClientInputCardFragment clientInputCardFragment;
    public void setCardFragment(ClientInputCardFragment cardFragment) {
        this.clientInputCardFragment = cardFragment;
    }

    private int month;
    public int getMonth() { return this.month; }
    public void setMonth(int month) { this.month = month; }

    private int day;
    public int getDay() { return this.day; }
    public void setDay(int day) { this.day = day; }

    private int year;
    public int getYear() { return this.year; }
    public void setYear(int year) { this.year = year; }

    private float mpg;
    public float getMpg() { return this.mpg; }
    public void setMpg(float mpg) { this.mpg = mpg; }

    private float gasPrice;
    public float getGasPrice() { return this.gasPrice; }
    public void setGasPrice(float gasPrice) { this.gasPrice = gasPrice; }

    private float distance;
    public void setDistance(float distance) { this.distance = distance; }
    public float getDistance() { return this.distance; }

    private float income;
    public void setIncome(float income) { this.income = income; }
    public float getIncome() { return this.income; }



}
