package com.example.pace;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    //private List<ClientModule> clientModuleList;

    public DayListAdapter(/*List<ClientModule> clientModuleList*/) {
        /*this.clientModuleList = clientModuleList*/
    }

    /*public void setClientModuleList(List<ClientModule> newClientModuleList) { this.clientModuleList = newClientModuleList; }
    *public List<ClientModule> getClientModuleList() { return this.clientModuleList; }*/

    @NonNull
    @Override
    public DayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

/* 11/5/2023 Link elements from within blueprint xml file to the objects that will
* be manipulated by the structural class ClientModule */