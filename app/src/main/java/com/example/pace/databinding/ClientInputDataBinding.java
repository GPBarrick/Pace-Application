package com.example.pace.databinding;

import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.databinding.BaseObservable;

import com.example.pace.fragmentelements.ClientInputCardFragment;

import java.util.Calendar;

public class ClientInputDataBinding extends BaseObservable {

    private boolean isCalendarVisible;
    private CalendarView calendarView;
    private EditText calendarDate;
    public ClientInputDataBinding(CalendarView calendarView, EditText calendarDate) {
        this.calendarView = calendarView;
        this.calendarView.setVisibility(CalendarView.INVISIBLE);
        this.isCalendarVisible = false;
        this.calendarDate = calendarDate;
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
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.YEAR, year);
        this.calendarDate.setText(calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR));
        this.clientInputCardFragment.dateText.setText(String.valueOf(this.calendarDate.getText()));
    }

    public ClientInputCardFragment clientInputCardFragment;
    public void setCardFragment(ClientInputCardFragment cardFragment) {
        this.clientInputCardFragment = cardFragment;
    }
}
