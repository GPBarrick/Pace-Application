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
import com.example.pace.clientuser.ClientDataDailyList;
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

        holder.dateText.setText(determineDateFormat(ListHolder.getInstance().outputMonthlyDataList.get(position).getMonth()));
        holder.monthIcon.setImageResource(getMonthIcons(ListHolder.getInstance().outputMonthlyDataList.get(position).getMonth()));
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

    public String determineDateFormat(int monthIndex) {
        String[] monthArr = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        return monthArr[monthIndex - 1];
    }

    public int getMonthIcons(int monthIndex) {
        Integer[] monthIconArr = {
                R.drawable.snowflake_icon, // January 0
                R.drawable.heart_icon, // February 1
                R.drawable.clover_icon, // March 2
                R.drawable.umbrella_icon, // April 3
                R.drawable.flowers_icon, // May 4
                R.drawable.beach_ball_icon, // June 5
                R.drawable.fireworks_icon, // July 6
                R.drawable.campfire_icon, // August 7
                R.drawable.books_icon, // September 8
                R.drawable.pumpkin_icon, // October 9
                R.drawable.maple_leaf_icon, // November 10
                R.drawable.christmas_tree_icon, // December 11
                R.drawable.blank_month_icon // Blank 12
        };
        return monthIconArr[monthIndex];
    }
}
