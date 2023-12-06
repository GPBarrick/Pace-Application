package com.example.pace.graphutil.graphutilfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.R;
import com.example.pace.adapters.DailyListGraphAdapter;
import com.example.pace.config.ListHolder;
import com.example.pace.fragmentlayouts.MainFragment;

import java.util.ArrayList;

public class DailyListItemFragment extends Fragment {

    public DailyListItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_list_item, container, false);

        initViews(view);

        initFragments();

        initListener();

        return view;
    }

    public ViewPager2 graphFragmentPager;
    public ImageButton returnButton;
    public TextView dateText;
    private void initViews(View view) {
        this.graphFragmentPager = view.findViewById(R.id.daily_list_item_viewPager);
        this.returnButton = view.findViewById(R.id.daily_list_item_returnButton);
        this.dateText = view.findViewById(R.id.daily_list_item_mainDateText);

        initDateText();
    }

    private void initDateText() {
        this.dateText.setText(ListHolder.getInstance().outputDailyDataList.get(
                ListHolder.getInstance().outputDailyDataListIndex
        ).getFormattedDate());
    }

    public DailyMpgGraphFragment mpgGraphFragment;
    public DailyGasPriceGraphFragment gasPriceGraphFragment;
    public DailyDistanceGraphFragment distanceGraphFragment;
    public DailyIncomeGraphFragment incomeGraphFragment;
    public ArrayList<Fragment> fragmentList;
    public DailyListGraphAdapter graphAdapter;
    private void initFragments() {
        this.fragmentList = new ArrayList<>();
        this.mpgGraphFragment = new DailyMpgGraphFragment();
        this.fragmentList.add(this.mpgGraphFragment);
        this.gasPriceGraphFragment = new DailyGasPriceGraphFragment();
        this.fragmentList.add(this.gasPriceGraphFragment);
        this.distanceGraphFragment = new DailyDistanceGraphFragment();
        this.fragmentList.add(this.distanceGraphFragment);
        this.incomeGraphFragment = new DailyIncomeGraphFragment();
        this.fragmentList.add(this.incomeGraphFragment);

        this.graphAdapter = new DailyListGraphAdapter(ListHolder.getInstance().fragmentManager, getLifecycle());
        for (int i = 0; i < this.fragmentList.size(); ++i) {
            this.graphAdapter.addFragment(this.fragmentList.get(i));
        }

        this.graphFragmentPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        this.graphFragmentPager.setAdapter(this.graphAdapter);
    }

    private void initListener() {
        this.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = ListHolder.getInstance().fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_mainFrame, new MainFragment());
                fragmentTransaction.commit();
            }
        });
    }
}