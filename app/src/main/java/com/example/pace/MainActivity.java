package com.example.pace;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeTest();

        InitializeToolbarProperties();

        InitializeViews();

        InitializeCalendarData();

        AddClientButton(this.calendarDataList, this.t);

        InitializeDayListAdapter();
    }
    /* 11/5/2023 Initialize Test object member */
    public Test t;
    private void InitializeTest() { this.t = new Test(); }

    /* 11/6/2023 Initialize the Toolbar */
    public Toolbar toolbar;
    public ImageButton toolbarAddClientButton;
    private void InitializeToolbarProperties() {
        this.toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(this.toolbar);
        this.toolbarAddClientButton = findViewById(R.id.app_toolbar_add_button);
    }

    /* 11/6/2023 Initialize the Toolbar ImageButton */
    private void AddClientButton(ArrayList<CalendarData> calendarDataList, Test t) {
        this.toolbarAddClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddClientModule.class);
                /* 11/7/2023 Populate the passed serializable ArrayList<CalendarData> to test receiving end (AddClientModule.class)
                *calendarDataList.add(new CalendarData(t.populateClientModuleListDataSet2(), 11, 2, 2023));*/
                intent.putExtra("calendar_data_list", calendarDataList);
                startActivity(intent);
            }
        });
    }

    /* 11/5/2023 Set the xml elements to their public member objects */
    public RecyclerView dayList, averagesList;
    private void InitializeViews() {
        this.averagesList = findViewById(R.id.activity_main_averages_list);
        this.dayList = findViewById(R.id.activity_main_daily_list);
    }

    /* 11/7/2023 Create the ArrayList<CalendarData> to represent your day to day view*/
    public ArrayList<CalendarData> calendarDataList;
    private void InitializeCalendarData() {
        this.calendarDataList = new ArrayList<>();
    }

    /* 11/5/2023 Initialize the DayListAdapter and LinearLayoutManager for this.dayList */
    public DayListAdapter dayListAdapter;
    private void InitializeDayListAdapter() {
        this.dayListAdapter = new DayListAdapter(this.calendarDataList, getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(false);
        this.dayList.setLayoutManager(layoutManager);
        this.dayList.setAdapter(this.dayListAdapter);
    }
}