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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeTest();

        InitializeToolbarProperties();

        AddClientButton();

        InitializeViews();

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
    private void AddClientButton() {
        this.toolbarAddClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddClientModule.class);
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

    /* 11/5/2023 Initialize the DayListAdapter and LinearLayoutManager for this.dayList */
    public DayListAdapter dayListAdapter;
    private void InitializeDayListAdapter() {
        this.dayListAdapter = new DayListAdapter(t.populateClientModuleList());
        this.dayList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        this.dayList.setAdapter(this.dayListAdapter);
    }
}