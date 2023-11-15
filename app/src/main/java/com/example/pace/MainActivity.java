package com.example.pace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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

        InitFragments();

        InitFragmentData();

        InitTabs(this.dailyFragments);
    }
    /* Initialize Test object member */
    public Test t;
    private void InitializeTest() { this.t = new Test(); }

    /* Initialize the Toolbar */
    public Toolbar toolbar;
    public ImageButton toolbarAddClientButton;
    private void InitializeToolbarProperties() {
        this.toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(this.toolbar);
        this.toolbarAddClientButton = findViewById(R.id.app_toolbar_add_button);
    }

    /* Initialize the Toolbar ImageButton */
    private void AddClientButton(ArrayList<CalendarData> calendarDataList, Test t) {
        this.toolbarAddClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddClientModule.class);
                /* Populate the passed serializable ArrayList<CalendarData> to test receiving end (AddClientModule.class)
                *calendarDataList.add(new CalendarData(t.populateClientModuleListDataSet2(), 11, 2, 2023));*/
                intent.putExtra("calendar_data_list", calendarDataList);
                startActivity(intent);
            }
        });
    }

    public ViewPager2 viewPagerAverages, viewPagerDaily;
    public TabLayout tabLayout;
    private void InitializeViews() {
        this.viewPagerAverages = findViewById(R.id.activity_main_top_viewpager);
        this.viewPagerDaily = findViewById(R.id.activity_main_bottom_viewpager);
        this.tabLayout = findViewById(R.id.activity_main_tab_layout);
    }

    public ArrayList<CalendarData> calendarDataList;
    private void InitializeCalendarData() {
        this.calendarDataList = new ArrayList<>();
    }

    private void DetermineIntentFunctionality() {
        Intent getIntent = getIntent();
        int retCode = getIntent.getIntExtra("ret_code", 402);
        Log.v("ret_code", ""+retCode);
        if (retCode == 10) {
            this.calendarDataList = (ArrayList<CalendarData>)getIntent.getSerializableExtra("calendar_data_list");
        }
    }

    /* Create a function that calculates a percentage and populates members with that value from the ArrayList<CalendarData> */
    public ListFunctions listFunctions = new ListFunctions();
    private void SetCalculatedPercentage(ArrayList<CalendarData> calendarDataList) {

        for (int i = 0; i < calendarDataList.size(); ++i) {

            if (i + 1 < calendarDataList.size()) {
                float originalExpenditure = calendarDataList.get(i).CalculateClientExpenditure();
                float newExpenditure = calendarDataList.get(i + 1).CalculateClientExpenditure();

                if (originalExpenditure != 0) {
                    float percentageCalculation = this.listFunctions.calculatePercentage(originalExpenditure, newExpenditure);

                    calendarDataList.get(i).setPercentageCalculation(percentageCalculation);
                } else {
                    calendarDataList.get(i).setPercentageCalculation(0);
                }
            } else {
                calendarDataList.get(i).setPercentageCalculation(100.0f);
            }
        }
    }

    /* Set the Fragments */
    public AveragesFragment monthlyAverages, yearlyAverages;
    public DailyFragment dailyList, weeklyList, monthlyList;
    public ArrayList<DailyFragment> dailyFragments;
    private void InitFragments() {
        // Fragment instantiation
        this.monthlyAverages = new AveragesFragment();
        this.yearlyAverages = new AveragesFragment();

        ArrayList<CalendarData> calendarDataTestList = this.t.populateCalendarDataList();

        // Fragment instantiation
        this.dailyList = new DailyFragment(getApplicationContext(), "Daily", 1);
        this.dailyList.SetCalendarData(calendarDataTestList);

        this.weeklyList = new DailyFragment(getApplicationContext(), "Weekly", 2);
        this.weeklyList.SetWeeklyData(this.listFunctions.organizeCalendarData(calendarDataTestList));

        this.monthlyList = new DailyFragment(getApplicationContext(), "Monthly", 3);


        // TabLayout
        this.dailyFragments = new ArrayList<>();
        this.dailyFragments.add(this.dailyList);
        this.dailyFragments.add(this.weeklyList);
        this.dailyFragments.add(this.monthlyList);
    }

    public AveragesFragmentAdapter averagesAdapter;
    public DailyFragmentAdapter dailyAdapter;
    private void InitFragmentData() {
        // Averages adapter
        this.averagesAdapter = new AveragesFragmentAdapter(getSupportFragmentManager(), getLifecycle());
        this.averagesAdapter.addFragment(monthlyAverages);
        this.averagesAdapter.addFragment(yearlyAverages);

        // Daily adapter
        this.dailyAdapter = new DailyFragmentAdapter(getSupportFragmentManager(), getLifecycle());
        this.dailyAdapter.addFragment(dailyList);
        this.dailyAdapter.addFragment(weeklyList);
        this.dailyAdapter.addFragment(monthlyList);

        // Set adapter
        this.viewPagerAverages.setAdapter(this.averagesAdapter);
        this.viewPagerDaily.setAdapter(this.dailyAdapter);
    }

    private void InitTabs(ArrayList<DailyFragment> dailyFragments) {
        new TabLayoutMediator(this.tabLayout, this.viewPagerDaily, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(dailyFragments.get(position).getFragmentName());
            }
        }).attach();
    }

    /* 11/8/2023 Initialize the AveragesListAdapter */
    //public AveragesListAdapter averagesListAdapter;
    //private void InitializeAveragesListAdapter() {
    //    // The SnapHelper will create snap the item on the screen to the center
    //    SnapHelper snapHelper = new LinearSnapHelper();
    //    snapHelper.attachToRecyclerView(this.averagesList);
    //    averagesListAdapter = new AveragesListAdapter(this.calendarDataList, getApplicationContext());
    //    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
    //    this.averagesList.setLayoutManager(layoutManager);
    //    this.averagesList.setAdapter(this.averagesListAdapter);
    //}

    ///* 11/5/2023 Initialize the DayListAdapter and LinearLayoutManager for this.dayList */
    //public DayListAdapter dayListAdapter;
    //private void InitializeDayListAdapter() {

    //ArrayList<CalendarData> calData = this.t.populateCalendarDataList();

    //SetCalculatedPercentage(this.calendarDataList);
    //this.dayListAdapter = new DayListAdapter(this.calendarDataList, getApplicationContext());
    //LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
    //this.dayList.setLayoutManager(layoutManager);
    //this.dayList.setAdapter(this.dayListAdapter);
    //}
}










