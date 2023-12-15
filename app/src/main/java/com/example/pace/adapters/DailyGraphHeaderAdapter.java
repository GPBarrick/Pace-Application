package com.example.pace.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pace.R;
import com.example.pace.config.ListHolder;

public class DailyGraphHeaderAdapter extends RecyclerView.Adapter<DailyGraphHeaderAdapter.ViewHolder> {

    private int dataSet;
    public void setDataSet(int dataSet) { this.dataSet = dataSet; }
    public int getDataSet() { return this.dataSet; }

    private int structureIndex;
    public void setStructureIndex(int structureIndex) { this.structureIndex = structureIndex; }
    public int getStructureIndex() { return this.structureIndex; }

    public DailyGraphHeaderAdapter(int dataSet, int structureIndex) {
        this.dataSet = dataSet;
        this.structureIndex = structureIndex;
    }

    @NonNull
    @Override
    public DailyGraphHeaderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_header_element, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyGraphHeaderAdapter.ViewHolder holder, int position) {
        holder.indexValue.setText(1 + position + " )");
        switch (dataSet) {
            case 0:
                holder.sampleText.setText("MPG");
                holder.sampleValue.setText(
                        String.format("%.2f", ""+ListHolder.getInstance().outputDailyDataList.get(structureIndex).getClientDataList().get(position).getMpg())
                );
                break;
            case 1:
                holder.sampleText.setText("Gas Price");
                holder.sampleValue.setText(
                        String.format("%.2f", ""+ListHolder.getInstance().outputDailyDataList.get(structureIndex).getClientDataList().get(position).getGasPrice())
                );
                break;
            case 2:
                holder.sampleText.setText("Distance");
                holder.sampleValue.setText(
                        String.format("%.2f", ""+ListHolder.getInstance().outputDailyDataList.get(structureIndex).getClientDataList().get(position).getDistance())
                );
                break;
            case 3:
                holder.sampleText.setText("Income");
                holder.sampleValue.setText(
                        String.format("%.2f", ""+ListHolder.getInstance().outputDailyDataList.get(structureIndex).getClientDataList().get(position).getIncome())
                );
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ListHolder.getInstance().outputDailyDataList.get(structureIndex).getClientDataList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView indexValue;
        public TextView sampleText;
        public TextView sampleValue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.indexValue = itemView.findViewById(R.id.graph_header_element_index);
            this.sampleText = itemView.findViewById(R.id.graph_header_element_sample);
            this.sampleValue = itemView.findViewById(R.id.graph_header_element_value);
        }
    }
}
