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

public class InputMpgFragment extends Fragment {

    public FragmentClientInputBinding fragmentClientInputBinding;
    public InputMpgFragment(FragmentClientInputBinding fragmentClientInputBinding) {
        this.fragmentClientInputBinding = fragmentClientInputBinding;
        this.errorBool = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_mpg, container, false);

        initViews(view);

        initListeners();

        return view;
    }

    public ImageButton forwardButton;
    public EditText mpgInputText;
    public ImageView errorIcon;
    private void initViews(View view) {
        this.forwardButton = view.findViewById(R.id.input_mpg_forwardButton);
        this.mpgInputText = view.findViewById(R.id.input_mpg_editText);
        this.errorIcon = view.findViewById(R.id.input_mpg_errorIcon);

        this.errorIcon.setVisibility(View.INVISIBLE);
    }

    private void initListeners() {
        this.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mpgInputText.getText().length() > 0) {
                    fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.mpgText.setText(
                            String.valueOf(mpgInputText.getText())
                    );
                    fragmentClientInputBinding.clientInputViewPager.setCurrentItem(1);
                    errorIcon.setVisibility(View.INVISIBLE);
                    errorBool = false;
                } else {
                    errorIcon.setVisibility(View.VISIBLE);
                    errorBool = true;
                }
            }
        });
        this.mpgInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (mpgInputText.getText().length() > 0) {
                        fragmentClientInputBinding.getInputDataBinding().clientInputCardFragment.mpgText.setText(
                                String.valueOf(mpgInputText.getText())
                        );
                        fragmentClientInputBinding.clientInputViewPager.setCurrentItem(1);
                        errorIcon.setVisibility(View.INVISIBLE);
                        errorBool = false;
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