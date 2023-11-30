package com.example.pace.fragmentelements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pace.R;
import com.example.pace.adapters.DailyListAdapter;
import com.example.pace.config.ListHolder;

public class DailyListFragment extends Fragment {

    public DailyListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_list, container, false);

        initViews(view);

        initListAdapter();

        return view;
    }

    public RecyclerView dailyList;
    private void initViews(View view) {
        this.dailyList = view.findViewById(R.id.daily_list_recyclerView);
    }

    public void initListAdapter() {

        //ListConfig.getInstance().setFormattedDailyListDate(this.clientDataList);
        //ListConfig.getInstance().calculatePercentages(this.clientDataList);

        ListHolder.getInstance().dailyListAdapter = new DailyListAdapter(ListHolder.getInstance().clientDataList);
        this.dailyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.dailyList.setAdapter(ListHolder.getInstance().dailyListAdapter);
    }
}