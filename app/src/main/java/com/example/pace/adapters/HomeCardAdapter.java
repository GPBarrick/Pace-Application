package com.example.pace.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class HomeCardAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public HomeCardAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return this.fragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        this.fragmentList.add(fragment);
    }
}