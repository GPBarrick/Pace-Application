package com.example.pace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeTest();

        InitializeToolbarProperties();

        InitializeViews();

        InitializeCalendarData();

        DetermineIntentFunctionality();

        AddClientButton(this.calendarDataList, this.t);

        InitializeDayListAdapter();

        InitializeAveragesListAdapter();
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

        //ArrayList<CalendarData> calData = this.t.populateCalendarDataList();

        SetCalculatedPercentage(this.calendarDataList);
        this.dayListAdapter = new DayListAdapter(this.calendarDataList, getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        this.dayList.setLayoutManager(layoutManager);
        this.dayList.setAdapter(this.dayListAdapter);
    }

    /* 11/7/2023 Determine where the getIntent() is coming from */
    private void DetermineIntentFunctionality() {
        Intent getIntent = getIntent();
        int retCode = getIntent.getIntExtra("ret_code", 402);
        Log.v("ret_code", ""+retCode);
        if (retCode == 10) {
            this.calendarDataList = (ArrayList<CalendarData>)getIntent.getSerializableExtra("calendar_data_list");

            //InitializeDayListAdapter();
        }
    }

    /* 11/8/2023 Create a function that calculates a percentage and populates members with that value from the ArrayList<CalendarData> */
    private void SetCalculatedPercentage(ArrayList<CalendarData> calendarDataList) {

        for (int i = 0; i < calendarDataList.size(); ++i) {

            if (i + 1 < calendarDataList.size()) {
                float originalExpenditure = calendarDataList.get(i).CalculateClientExpenditure();
                float newExpenditure = calendarDataList.get(i + 1).CalculateClientExpenditure();

                if (originalExpenditure != 0) {
                    float percentageCalculation = calendarDataList.get(i).calculatePercentage(originalExpenditure, newExpenditure);

                    calendarDataList.get(i).setPercentageCalculation(percentageCalculation);
                } else {
                    calendarDataList.get(i).setPercentageCalculation(0);
                }
            } else {
                calendarDataList.get(i).setPercentageCalculation(100.0f);
            }
        }
    }

    /* 11/8/2023 Initialize the AveragesListAdapter */
    public AveragesListAdapter averagesListAdapter;
    private void InitializeAveragesListAdapter() {
        // The SnapHelper will create snap the item on the screen to the center
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(this.averagesList);
        averagesListAdapter = new AveragesListAdapter(this.calendarDataList, getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        this.averagesList.setLayoutManager(layoutManager);
        this.averagesList.setAdapter(this.averagesListAdapter);
    }
}










