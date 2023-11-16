package com.example.pace;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class AddClientModule extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_module);

        InitializeViews();

        InitializeOpenCalendarButton(this.calendar);

        InitializeCalendarListener();

        SetListenerToButton();
    }

    /* Initialize the views */
    public EditText mpgInput, dateInput, gasPriceInput, tankInput, distanceInput, incomeInput;
    public Button addButton;
    public CheckBox openCalendar;
    public CalendarView calendar;
    private void InitializeViews() {
        this.mpgInput = findViewById(R.id.add_client_mpg_edit);
        this.dateInput = findViewById(R.id.add_client_date_edit);
        this.gasPriceInput = findViewById(R.id.add_client_gas_price_edit);
        this.tankInput = findViewById(R.id.add_client_gas_added_edit);
        this.distanceInput = findViewById(R.id.add_client_trip_distance_edit);
        this.incomeInput = findViewById(R.id.add_client_income_edit);
        this.addButton = findViewById(R.id.add_client_calculate_button);
        this.openCalendar = findViewById(R.id.add_client_open_calendar);
        this.calendar = findViewById(R.id.add_client_calendar);
        this.calendar.setVisibility(View.INVISIBLE);
    }

    public String mpg, date, gasPrice, tank, dist, income;
    private void SetTextValues() {
        boolean test = true;

        if (test) {
            Random random = new Random();
            this.mpg = String.valueOf(random.nextInt(50 - 10) + 10);
        } else {
            this.mpg = String.valueOf(this.mpgInput.getText());
        }

        this.date = String.valueOf(this.dateInput.getText());

        if (test) {
            Random random = new Random();
            this.gasPrice = String.valueOf(random.nextInt(4 - 1) + 1);
        } else {
            this.gasPrice = String.valueOf(this.gasPriceInput.getText());
        }

        this.tank = String.valueOf(this.tankInput.getText());

        if (test) {
            Random random = new Random();
            this.dist = String.valueOf(random.nextInt(100 - 1) + 1);
        } else {
            this.dist = String.valueOf(this.distanceInput.getText());
        }

        if (test) {
            Random random = new Random();
            this.income = String.valueOf(random.nextInt(25 - 1) + 1);
        } else {
            this.income = String.valueOf(this.incomeInput.getText());
        }
    }

    private void SetListenerToButton() {
        this.addButton.setOnClickListener(this);
    }

    private void UpdateCalendarState(boolean isOpened) {
        if (isOpened) {
            calendar.setVisibility(View.VISIBLE);
        } else {
            calendar.setVisibility(View.INVISIBLE);
        }
    }
    private void InitializeOpenCalendarButton(CalendarView calendar) {
        this.openCalendar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.v("calendar_open", ""+b);
                UpdateCalendarState(b);
            }
        });
    }

    private int month;
    private int GetMonth() { return this.month; }
    private void SetMonth(int month) { this.month = month; }
    private int day;
    private int GetDay() { return this.day; }
    private void SetDay(int day) { this.day = day; }
    private int year;
    private int GetYear() { return this.year; }
    private void SetYear(int year) { this.year = year; }
    private EditText GetCalendarEditText () { return this.dateInput; }
    private void InitializeCalendarListener() {
        this.calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String calendarDateSelected = String.valueOf(i1+1) + "/" + String.valueOf(i2) + "/" + String.valueOf(i);
                SetMonth(i1+1);
                SetDay(i2);
                SetYear(i);
                UpdateCalendarState(false);
                GetCalendarEditText().setText(calendarDateSelected);
            }
        });
    }

    @Override
    public void onClick(View view) {
        SetTextValues();

        Intent getIntent = getIntent();
        ArrayList<CalendarData> calendarData = (ArrayList<CalendarData>)getIntent.getSerializableExtra("calendar_data_list");

        ClientModule newModule = new ClientModule(
                Float.valueOf(this.dist),
                Float.valueOf(this.mpg),
                Float.valueOf(this.gasPrice),
                Float.valueOf(this.income)
        );

        Log.v("calendar_size", String.valueOf(calendarData.size()));
        if (calendarData.size() > 0) {
            boolean wasFound = false;
            for (int i = 0; i < calendarData.size(); ++i) {
                if (calendarData.get(i).getMonth() == GetMonth() && calendarData.get(i).getDay() == GetDay() && calendarData.get(i).getYear() == GetYear()) {
                    Log.v("calendar_indexing", String.valueOf(GetMonth() + "/" + GetDay() + "/" + GetYear()));
                    calendarData.get(i).getClientModuleList().add(newModule);
                    wasFound = true;
                    break;
                }
            }
            if (!wasFound) {
                ArrayList<ClientModule> clientModuleList = new ArrayList<>();
                clientModuleList.add(newModule);
                calendarData.add(0, new CalendarData(clientModuleList, GetMonth(), GetDay(), GetYear()));
            }
        } else {
            ArrayList<ClientModule> clientModuleList = new ArrayList<>();
            clientModuleList.add(newModule);
            calendarData.add(new CalendarData(clientModuleList, GetMonth(), GetDay(), GetYear()));
        }

        Collections.sort(calendarData, new Comparator<CalendarData>() {
            @Override
            public int compare(CalendarData o1, CalendarData o2) {
                if (o1.getYear() != o2.getYear()) {
                    return o2.getYear() - o1.getYear(); // Descending order for year
                } else if (o1.getMonth() != o2.getMonth()) {
                    return o2.getMonth() - o1.getMonth(); // Descending order for month
                } else {
                    return o2.getDay() - o1.getDay(); // Descending order for day
                }
            }
        });

        /* 11/7/2023 Testing received data */ //Log.v("calendar_data_size", ""+calendarData.size());

        Intent setIntent = new Intent(AddClientModule.this, MainActivity.class);
        setIntent.putExtra("calendar_data_list", calendarData);
        setIntent.putExtra("ret_code", 10);
        startActivity(setIntent);
    }
}