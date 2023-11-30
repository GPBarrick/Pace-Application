package com.example.pace.fragmentlayouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.R;
import com.example.pace.adapters.HomeCardAdapter;
import com.example.pace.adapters.HomeListAdapter;
import com.example.pace.fragmentelements.DailyListFragment;
import com.example.pace.fragmentelements.HomeCardFragment;
import com.example.pace.fragmentelements.MonthlyListFragment;
import com.example.pace.fragmentelements.WeeklyListFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        initCardViewPager();
        initListViewPager();
        initTabLayout();
        return view;
    }

    public ViewPager2 listViewPager;
    public ViewPager2 cardViewPager;
    public TabLayout listTabLayout;
    private void initViews(View view) {
        this.listViewPager = view.findViewById(R.id.home_list_viewPager);
        this.cardViewPager = view.findViewById(R.id.home_card_viewPager);
        this.listTabLayout = view.findViewById(R.id.home_tabLayout);
    }
    private void initListViewPager() {
        HomeListAdapter homeListAdapter = new HomeListAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        homeListAdapter.addFragment(new DailyListFragment());
        homeListAdapter.addFragment(new WeeklyListFragment());
        homeListAdapter.addFragment(new MonthlyListFragment());
        this.listViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        this.listViewPager.setAdapter(homeListAdapter);
    }
    private void initCardViewPager() {
        HomeCardAdapter homeCardAdapter = new HomeCardAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        homeCardAdapter.addFragment(new HomeCardFragment());
        homeCardAdapter.addFragment(new HomeCardFragment());
        this.cardViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        this.cardViewPager.setAdapter(homeCardAdapter);
    }
    private void initTabLayout() {
        ArrayList<String> tabLayoutText = new ArrayList<>();
        tabLayoutText.add("Daily");
        tabLayoutText.add("Weekly");
        tabLayoutText.add("Monthly");
        new TabLayoutMediator(this.listTabLayout, this.listViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabLayoutText.get(position));
            }
        }).attach();
    }
}