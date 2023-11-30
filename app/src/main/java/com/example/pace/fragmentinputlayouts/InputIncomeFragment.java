package com.example.pace.fragmentinputlayouts;

import android.os.Bundle;
import android.os.Handler;
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
import com.example.pace.clientuser.ClientData;
import com.example.pace.config.ListHolder;
import com.example.pace.databinding.FragmentClientInputBinding;

import java.util.ArrayList;

public class InputIncomeFragment extends Fragment {

    public FragmentClientInputBinding fragmentClientInputBinding;
    public InputIncomeFragment(FragmentClientInputBinding fragmentClientInputBinding) {
        this.fragmentClientInputBinding = fragmentClientInputBinding;
        this.errorBool = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.incomeInputText.getText().clear();
    }

    private ArrayList<Fragment> fragmentList;
    public void setFragmentList(ArrayList<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_income, container, false);

        initViews(view);

        initListeners();

        return view;
    }

    public ImageButton submitButton, backButton;
    public EditText incomeInputText;
    public ImageView errorIcon;
    private void initViews(View view) {
        this.submitButton = view.findViewById(R.id.input_income_submitButton);
        this.backButton = view.findViewById(R.id.input_income_backButton);
        this.incomeInputText = view.findViewById(R.id.input_income_editText);
        this.errorIcon = view.findViewById(R.id.input_income_errorIcon);

        this.errorIcon.setVisibility(View.INVISIBLE);

    }

    private void initListeners() {

        this.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (incomeInputText.getText().length() > 0) {
                    fragmentClientInputBinding.getInputDataBinding().setIncome(Float.parseFloat(String.valueOf(incomeInputText.getText())));
                    fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.incomeText.setText(
                            "$" + incomeInputText.getText()
                    );

                    checkCalendarBinding();

                    errorIcon.setVisibility(View.INVISIBLE);
                    errorBool = false;

                    addClientDataToStructure();
                } else {

                    checkCalendarBinding();

                    errorIcon.setVisibility(View.VISIBLE);
                    errorBool = true;
                }
            }
        });
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentClientInputBinding.clientInputViewPager.setCurrentItem(2);
            }
        });
        this.incomeInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (incomeInputText.getText().length() > 0) {
                        fragmentClientInputBinding.getInputDataBinding().setIncome(Float.parseFloat(String.valueOf(incomeInputText.getText())));
                        fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.incomeText.setText(
                                "$" + incomeInputText.getText()
                        );

                        checkCalendarBinding();

                        errorIcon.setVisibility(View.INVISIBLE);
                        errorBool = false;

                        addClientDataToStructure();

                    } else {

                        checkCalendarBinding();

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

    public void checkCalendarBinding() {

        if(fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.getDateValid()) {
            fragmentClientInputBinding.clientInputErrorIcon.setVisibility(View.INVISIBLE);
        } else {
            fragmentClientInputBinding.clientInputErrorIcon.setVisibility(View.VISIBLE);
        }
    }

    public void addClientDataToStructure() {
        if (fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.getDateValid()) {
            ListHolder.getInstance().clientDataList.add(new ClientData(
                    fragmentClientInputBinding.getInputDataBinding().getMonth(),
                    fragmentClientInputBinding.getInputDataBinding().getDay(),
                    fragmentClientInputBinding.getInputDataBinding().getYear(),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getMpg()),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getGasPrice()),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getDistance()),
                    Float.parseFloat(""+fragmentClientInputBinding.getInputDataBinding().getIncome())
            ));
            ListHolder.getInstance().dailyListAdapter.notifyItemInserted(ListHolder.getInstance().clientDataList.size());
            fragmentClientInputBinding.clientInputViewPager.setCurrentItem(0);

            fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.resetAllCalendarCardValues();

            for (int i = 0; i < this.fragmentList.size(); ++i) {
                fragmentList.get(i).onDestroy();
            }

            fragmentClientInputBinding.getInputDataBinding().getCalendarDate().getText().clear();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ListHolder.getInstance().mainActivityPager.setCurrentItem(0);
                }
            }, 400);
        }
    }
}