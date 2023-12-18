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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.DataBase.DataBase;
import com.example.pace.adapters.MainFragmentAdapter;
import com.example.pace.clientuser.ClientData;
import com.example.pace.config.ListHolder;
import com.example.pace.fragmentlayouts.ClientInputFragment;
import com.example.pace.fragmentlayouts.HomeFragment;
import com.example.pace.fragmentlayouts.MainFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    public ListFunctions listFunctions = new ListFunctions();

    /* Set the Fragments */
    public AveragesFragment monthlyAverages, yearlyAverages;
    public DailyFragment dailyList, weeklyList, monthlyList;
    public ArrayList<DailyFragment> dailyFragments;
    private void InitFragments() {
        // Fragment instantiation
        this.monthlyAverages = new AveragesFragment();
        this.yearlyAverages = new AveragesFragment();

        // dailyList initialization (fragmentType == 1 ? daily)
        this.dailyList = new DailyFragment(getApplicationContext(), "Daily", 1);
        // Calculate percentages from dailyList members
        this.listFunctions.SetCalculatedPercentage(this.calendarDataList);
        // Set the calendarDataList to the fragment
        this.dailyList.SetCalendarData(this.calendarDataList);

        // weeklyList initialization (fragmentType == 2 ? weekly)
        this.weeklyList = new DailyFragment(getApplicationContext(), "Weekly", 2);
        // Set the weekly list to the fragment member
        ArrayList<WeeklyData> organizedWeeklyList = this.listFunctions.organizeCalendarData(this.calendarDataList);
        this.listFunctions.setWeeklyDataExpenditure(organizedWeeklyList);
        this.listFunctions.setWeekDateRange(organizedWeeklyList);
        this.listFunctions.setWeeklyPercentages(organizedWeeklyList);
        this.weeklyList.SetWeeklyData(organizedWeeklyList);



        this.monthlyList = new DailyFragment(getApplicationContext(), "Monthly", 3);


        // TabLayout list
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










        initLayout();
    }

    ArrayList<ClientData> clientDataList = new ArrayList<>();
    public void setClientDataList(ArrayList<ClientData> clientDataList) { this.clientDataList = clientDataList; }
    public ArrayList<ClientData> getClientDataList() { return this.clientDataList; }

    public FrameLayout frameLayout;
    public MainFragment mainFragment;
    private void initLayout() {
        mainFragment = new MainFragment();
        this.frameLayout = findViewById(R.id.activity_main_mainFrame);

        ListHolder.getInstance().clientDataList = this.clientDataList;
        ListHolder.getInstance().mainFragment = this.mainFragment;

        initFragmentData();
    }

    private void initFragmentData() {
        ListHolder.getInstance().fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = ListHolder.getInstance().fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_mainFrame, this.mainFragment);
        fragmentTransaction.commit();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Intent mainActivity = new Intent(DailyListItem.this, MainActivity.class);
//            startActivity(mainActivity);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}

