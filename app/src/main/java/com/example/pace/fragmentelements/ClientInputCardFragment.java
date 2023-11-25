package com.example.pace.fragmentelements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pace.R;

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
    private void initViews(View view) {
        this.dateText = view.findViewById(R.id.input_card_dateText);
        this.mpgText = view.findViewById(R.id.input_card_mpgText);
        this.gasPriceText = view.findViewById(R.id.input_card_gasPriceText);
        this.distanceText = view.findViewById(R.id.input_card_distanceText);
        this.incomeText = view.findViewById(R.id.input_card_incomeText);
    }
}