package com.example.pace.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pace.R;
import com.example.pace.clientuser.ClientData;

import java.util.ArrayList;

public class DailyListAdapter extends RecyclerView.Adapter<DailyListAdapter.ViewHolder> {

    private ArrayList<ClientData> clientDataList;
    public DailyListAdapter(ArrayList<ClientData> clientDataList) {
        this.clientDataList = clientDataList;
    }

    @NonNull
    @Override
    public DailyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.clientDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
