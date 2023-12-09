package com.example.pace.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pace.R;
import com.example.pace.config.ListHolder;

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.ViewHolder> {

    private Context applicationContext;
    public HomeCardAdapter(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public HomeCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardAdapter.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(applicationContext, R.anim.recycler_view_animation_1);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return ListHolder.getInstance().outputMonthlyDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateText;
        public TextView mpgValue;
        public TextView gasPriceValue;
        public TextView distanceValue;
        public TextView incomeValue;
        public ImageView monthIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dateText = itemView.findViewById(R.id.home_card_month_text);
            this.mpgValue = itemView.findViewById(R.id.home_card_mpg_value);
            this.gasPriceValue = itemView.findViewById(R.id.home_card_gas_price_value);
            this.distanceValue = itemView.findViewById(R.id.home_card_distance_text);
            this.incomeValue = itemView.findViewById(R.id.home_card_income_value);
            this.monthIcon = itemView.findViewById(R.id.home_card_monthIcon);
        }
    }
}
