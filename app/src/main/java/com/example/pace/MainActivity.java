package com.example.pace;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.config.MainFragmentAdapter;
import com.example.pace.fragmentlayouts.ClientInputFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initViewPager();
    }

    public ViewPager2 mainViewPager;
    private void initViews() {
        this.mainViewPager = findViewById(R.id.activity_main_viewPager);
    }
    private void initViewPager() {
        MainFragmentAdapter fragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), getLifecycle());
        fragmentAdapter.addFragment(new ClientInputFragment());
        this.mainViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        this.mainViewPager.setAdapter(fragmentAdapter);
    }

}