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
import com.example.pace.adapters.WeeklyListAdapter;
import com.example.pace.config.ListHolder;

public class WeeklyListFragment extends Fragment {

    public WeeklyListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly_list, container, false);

        initViews(view);

        initListAdapter();

        return view;
    }

    public RecyclerView recyclerView;
    private void initViews(View view) {
        this.recyclerView = view.findViewById(R.id.weekly_list_recyclerView);
    }

    public void initListAdapter() {
        ListHolder.getInstance().weeklyListAdapter = new WeeklyListAdapter();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(ListHolder.getInstance().weeklyListAdapter);
    }
}