package com.example.pace;
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

import java.util.ArrayList;

public class AddClientModule extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_module);

        InitializeViews();

        InitializeOpenCalendarButton();

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

        this.isCalendarOpen = false;
    }

    public String mpg, date, gasPrice, tank, dist, income;
    private void SetTextValues() {
        this.mpg = String.valueOf(this.mpgInput.getText());
        this.date = String.valueOf(this.dateInput.getText());
        this.gasPrice = String.valueOf(this.gasPriceInput.getText());
        this.tank = String.valueOf(this.tankInput.getText());
        this.dist = String.valueOf(this.distanceInput.getText());
        this.income = String.valueOf(this.incomeInput.getText());
    }

    private void SetListenerToButton() {
        this.addButton.setOnClickListener(this);
    }

    boolean isCalendarOpen;
    private void InitializeOpenCalendarButton() {
        this.openCalendar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                
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

        if (calendarData.size() > 0) {
            // Find common date to store new ClientModule within the ArrayList<CalendarData>
            for (int i = 0; i < calendarData.size(); ++i) {
                //if (calendarData.get(i).getMonth())
            }
        }

        /* 11/7/2023 Testing received data */ Log.v("CALENDAR_DATA_SIZE", ""+calendarData.size());

        Intent setintent = new Intent(AddClientModule.this, MainActivity.class);
        //startActivity(setIntent);
    }
}