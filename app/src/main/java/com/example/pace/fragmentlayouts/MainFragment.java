package com.example.pace.fragmentlayouts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.pace.R;
import com.example.pace.adapters.MainFragmentAdapter;
import com.example.pace.clientuser.ClientData;
import com.example.pace.config.ListHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        initFragments();
        initTabLayout();
        initKeyboardSoftInput();

        return view;
    }

    public ViewPager2 mainViewPager;
    public TabLayout mainTabLayout;
    private void initViews(View view) {
        this.mainViewPager = view.findViewById(R.id.main_viewPager);
        this.mainTabLayout = view.findViewById(R.id.main_tabLayout);
    }
    public ArrayList<Fragment> mainFragmentList;
    public HomeFragment homeFragment;
    public ClientInputFragment clientInputFragment;
    private void initFragments() {
        ListHolder.getInstance().mainActivityPager = this.mainViewPager;

        this.homeFragment = new HomeFragment();
        this.clientInputFragment = new ClientInputFragment();

        this.mainFragmentList = new ArrayList<>();
        this.mainFragmentList.add(this.homeFragment);
        this.mainFragmentList.add(this.clientInputFragment);

        MainFragmentAdapter fragmentAdapter = new MainFragmentAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
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

    private void initKeyboardSoftInput() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}