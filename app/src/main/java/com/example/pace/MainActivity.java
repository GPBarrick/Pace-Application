package com.example.pace;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeTest();

        InitializeViews();

        InitializeDayListAdapter();

    }
   @Override
   protected void onStart(){
       super.onStart();
       Intent singInIntent = new Intent(MainActivity.this, SignInActivity.class);
       startActivity(singInIntent);
       finish();
   }

    /* 11/5/2023 Initialize Test object member */
    public Test t;
    private void InitializeTest() { this.t = new Test(); }


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