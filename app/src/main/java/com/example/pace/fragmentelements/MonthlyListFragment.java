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
import com.example.pace.adapters.MonthlyListAdapter;
import com.example.pace.config.ListHolder;

public class MonthlyListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_list, container, false);

        initViews(view);

        initMonthlyListAdapter();

        return view;
    }

    public RecyclerView recyclerView;
    private void initViews(View view) {
        this.recyclerView = view.findViewById(R.id.monthly_list_recyclerView);
    }

    private void initMonthlyListAdapter() {
        ListHolder.getInstance().monthlyListAdapter = new MonthlyListAdapter();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(ListHolder.getInstance().monthlyListAdapter);
    }
}