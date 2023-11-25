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

public class InputGasPriceFragment extends Fragment {

    public FragmentClientInputBinding fragmentClientInputBinding;
    public InputGasPriceFragment(FragmentClientInputBinding fragmentClientInputBinding) {
        this.fragmentClientInputBinding = fragmentClientInputBinding;
        this.errorBool = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.gasPriceInputText.getText().clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_gas_price, container, false);

        initViews(view);

        initListeners();

        return view;
    }

    public ImageButton forwardButton, backButton;
    public EditText gasPriceInputText;
    public ImageView errorIcon;
    private void initViews(View view) {
        this.forwardButton = view.findViewById(R.id.input_gasPrice_forwardButton);
        this.backButton = view.findViewById(R.id.input_gasPrice_backButton);
        this.gasPriceInputText = view.findViewById(R.id.input_gasPrice_editText);
        this.errorIcon = view.findViewById(R.id.input_gasPrice_errorIcon);

        this.errorIcon.setVisibility(View.INVISIBLE);
    }

    private void initListeners() {

        this.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gasPriceInputText.getText().length() > 0) {
                    fragmentClientInputBinding.getInputDataBinding().setGasPrice(Float.parseFloat(String.valueOf(gasPriceInputText.getText())));
                    fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.gasPriceText.setText(
                            "$" + gasPriceInputText.getText()
                    );
                    errorIcon.setVisibility(View.INVISIBLE);
                    errorBool = false;


                    fragmentClientInputBinding.clientInputViewPager.setCurrentItem(2);
                } else {
                    errorIcon.setVisibility(View.VISIBLE);
                    errorBool = true;
                }
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentClientInputBinding.clientInputViewPager.setCurrentItem(0);
            }
        });
        this.gasPriceInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (gasPriceInputText.getText().length() > 0) {
                        fragmentClientInputBinding.getInputDataBinding().setGasPrice(Float.parseFloat(String.valueOf(gasPriceInputText.getText())));
                        fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.gasPriceText.setText(
                                "$" + gasPriceInputText.getText()
                        );
                        errorIcon.setVisibility(View.INVISIBLE);
                        errorBool = false;

                        fragmentClientInputBinding.clientInputViewPager.setCurrentItem(2);
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