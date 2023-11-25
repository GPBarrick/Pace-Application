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
import com.example.pace.clientuser.ClientData;

import java.util.ArrayList;

public class DailyListFragment extends Fragment {

    public ArrayList<ClientData> clientDataList;
    public DailyListFragment(ArrayList<ClientData> clientDataList) {
        this.clientDataList = clientDataList;
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

    public DailyListAdapter dailyListAdapter;
    public void initListAdapter() {

        this.clientDataList.add(new ClientData());
        this.clientDataList.add(new ClientData());
        this.clientDataList.add(new ClientData());
        this.clientDataList.add(new ClientData());

        this.dailyListAdapter = new DailyListAdapter(this.clientDataList);
        this.dailyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.dailyList.setAdapter(this.dailyListAdapter);
    }
}