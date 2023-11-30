package com.example.pace;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.adapters.MainFragmentAdapter;
import com.example.pace.clientuser.ClientData;
import com.example.pace.config.ListHolder;
import com.example.pace.fragmentlayouts.ClientInputFragment;
import com.example.pace.fragmentlayouts.HomeFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments();
        initTabLayout();
    }

    ArrayList<ClientData> clientDataList = new ArrayList<>();
    public void setClientDataList(ArrayList<ClientData> clientDataList) { this.clientDataList = clientDataList; }
    public ArrayList<ClientData> getClientDataList() { return this.clientDataList; }

    public ViewPager2 mainViewPager;
    public TabLayout mainTabLayout;
    private void initViews() {
        this.mainViewPager = findViewById(R.id.activity_main_viewPager);
        this.mainTabLayout = findViewById(R.id.activity_main_tabLayout);
    }
    public ArrayList<Fragment> mainFragmentList;
    public HomeFragment homeFragment;
    public ClientInputFragment clientInputFragment;
    private void initFragments() {
        ListHolder.getInstance().clientDataList = this.clientDataList;
        ListHolder.getInstance().mainActivityPager = this.mainViewPager;

        this.homeFragment = new HomeFragment();
        this.clientInputFragment = new ClientInputFragment();

        this.mainFragmentList = new ArrayList<>();
        this.mainFragmentList.add(this.homeFragment);
        this.mainFragmentList.add(this.clientInputFragment);

        MainFragmentAdapter fragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), getLifecycle());
        for (int index = 0; index < this.mainFragmentList.size(); ++index) {
            fragmentAdapter.addFragment(this.mainFragmentList.get(index));
        }

        initViewPager(fragmentAdapter);
    }
    private void initViewPager(MainFragmentAdapter fragmentAdapter) {
        this.mainViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        this.mainViewPager.setAdapter(fragmentAdapter);
    }
    private void initTabLayout() {
        ArrayList<String> tabLayoutText = new ArrayList<>();
        tabLayoutText.add("Home");
        tabLayoutText.add("Add");
        new TabLayoutMediator(this.mainTabLayout, this.mainViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabLayoutText.get(position));
            }
        }).attach();
    }
}