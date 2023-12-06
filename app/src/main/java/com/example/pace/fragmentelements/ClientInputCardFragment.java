package com.example.pace.fragmentelements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pace.R;

import java.util.ArrayList;

public class ClientInputCardFragment extends Fragment {
    public ClientInputCardFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_input_card, container, false);

        initViews(view);

        return view;
    }

    public TextView dateText;
    public TextView mpgText;
    public TextView gasPriceText;
    public TextView distanceText;
    public TextView incomeText;
    public ImageView monthIcon;
    private void initViews(View view) {
        this.dateText = view.findViewById(R.id.input_card_header);
        this.mpgText = view.findViewById(R.id.input_card_mpgText);
        this.gasPriceText = view.findViewById(R.id.input_card_gasPriceText);
        this.distanceText = view.findViewById(R.id.input_card_distanceText);
        this.incomeText = view.findViewById(R.id.input_card_incomeText);
        this.monthIcon = view.findViewById(R.id.input_card_monthIcon);
    }

    boolean dateValid = false;
    public void setDateValid(boolean dateValid) { this.dateValid = dateValid; }
    public boolean getDateValid() { return this.dateValid; }

    public void resetAllCalendarCardValues() {
        this.dateText.setText("Date");
        this.dateValid = false;
        this.mpgText.setText("");
        this.gasPriceText.setText("");
        this.distanceText.setText("");
        this.incomeText.setText("");
        this.monthIcon.setImageResource(getMonthIcons(6));
    }

    public int getMonthIcons(int monthIndex) {
        Integer[] monthIconArr = {
                R.drawable.snowflake_icon, // January 0
                R.drawable.heart_icon, // February 1
                R.drawable.clover_icon, // March 2
                R.drawable.showers_icon, // April 3
                R.drawable.flowers_icon, // May 4
                R.drawable.sun_icon, // June 5
                R.drawable.blank_month_icon // Blank 6
        };
        return monthIconArr[monthIndex];
    }
}