package com.example.pace.fragmentinputlayouts;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pace.R;
import com.example.pace.databinding.FragmentClientInputBinding;

public class InputDistanceFragment extends Fragment {

    public FragmentClientInputBinding fragmentClientInputBinding;
    public InputDistanceFragment(FragmentClientInputBinding fragmentClientInputBinding) {
        this.fragmentClientInputBinding = fragmentClientInputBinding;
        this.errorBool = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.distanceInputText.getText().clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_distance, container, false);

        initViews(view);

        initListeners();

        return view;
    }

    public ImageButton forwardButton, backButton;
    public ImageView errorIcon;
    public EditText distanceInputText;
    private void initViews(View view) {
        this.forwardButton = view.findViewById(R.id.input_distance_forwardButton);
        this.backButton = view.findViewById(R.id.input_distance_backButton);
        this.errorIcon = view.findViewById(R.id.input_distance_errorIcon);
        this.distanceInputText = view.findViewById(R.id.input_distance_editText);

        this.errorIcon.setVisibility(View.INVISIBLE);

    }

    private void initListeners() {

        this.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (distanceInputText.getText().length() > 0) {
                    fragmentClientInputBinding.getInputDataBinding().setDistance(Float.parseFloat(String.valueOf(distanceInputText.getText())));
                    fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.distanceText.setText(
                            distanceInputText.getText() + " mi"
                    );
                    errorIcon.setVisibility(View.INVISIBLE);
                    errorBool = false;

                    fragmentClientInputBinding.clientInputViewPager.setCurrentItem(3);
                } else {
                    errorIcon.setVisibility(View.VISIBLE);
                    errorBool = true;
                }
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentClientInputBinding.clientInputViewPager.setCurrentItem(1);
            }
        });
        this.distanceInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (distanceInputText.getText().length() > 0) {
                        fragmentClientInputBinding.getInputDataBinding().setDistance(Float.parseFloat(String.valueOf(distanceInputText.getText())));
                        fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.distanceText.setText(
                                distanceInputText.getText() + " mi"
                        );
                        errorIcon.setVisibility(View.INVISIBLE);
                        errorBool = false;

                        fragmentClientInputBinding.clientInputViewPager.setCurrentItem(3);
                    } else {
                        errorIcon.setVisibility(View.VISIBLE);
                        errorBool = true;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public boolean errorBool;
    public boolean getErrorBool() {
        return errorBool;
    }
}