package com.example.pace;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeViews();
    }

    /* 11/5/2023 Set the xml elements to their public member objects */
    public RecyclerView dayList, averagesList;
    private void InitializeViews() {
        this.averagesList = findViewById(R.id.activity_main_averages_list);
        this.dayList = findViewById(R.id.activity_main_daily_list);
    }
}