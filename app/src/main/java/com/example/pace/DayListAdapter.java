package com.example.pace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    private List<ClientModule> clientModuleList;

    public DayListAdapter(List<ClientModule> clientModuleList) {
        this.clientModuleList = clientModuleList;
    }

    public void setClientModuleList(List<ClientModule> newClientModuleList) { this.clientModuleList = newClientModuleList; }
    public List<ClientModule> getClientModuleList() { return this.clientModuleList; }

    @NonNull
    @Override
    public DayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.day_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.ViewHolder holder, int position) {
        holder.statusImage.setImageResource(this.clientModuleList.get(position).getImageResource());
        holder.dateText.setText(this.clientModuleList.get(position).getDateText());
    }

    @Override
    public int getItemCount() {
        return this.clientModuleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView statusImage;
        public TextView dateText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.statusImage = itemView.findViewById(R.id.day_element_status_image);
            this.dateText = itemView.findViewById(R.id.day_element_date_text);
        }
    }
}

/* 11/5/2023 Link elements from within blueprint xml file to the objects that will
* be manipulated by the structural class ClientModule */