package com.example.pace;

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
import android.os.Bundle;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }
    //***********************THE DATABASE CLASS NEEDS TO BE CALLED EVERYTIME THE USER CICKS ADD BUTTON*********************
    //*******FIGURED OUT HOW EVENT LISTENERS TO SET UP THE DATABASE CLASS IN THE ADD BUTTON LISTENER
    //-----------------------DELETE THIS IS JUST TO TEST THE DATA BASE------------------------------
   // protected void onDestroy() {
    DataBase testing = new DataBase();
      //  super.onDestroy();
        //testing database
     //   DataBase database = new DataBase();
     //   database.FirebaseSetUp(clientDataList);
        //end of testing.
   // }
    //-------------------END OF THE TESTING AREA----------------------------------------------------
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