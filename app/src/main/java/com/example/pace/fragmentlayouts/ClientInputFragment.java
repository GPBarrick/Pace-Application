package com.example.pace.fragmentlayouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pace.R;
import com.example.pace.adapters.ClientInputAdapter;
import com.example.pace.databinding.ClientInputDataBinding;
import com.example.pace.databinding.FragmentClientInputBinding;
import com.example.pace.fragmentelements.ClientInputCardFragment;
import com.example.pace.fragmentinputlayouts.InputDistanceFragment;
import com.example.pace.fragmentinputlayouts.InputGasPriceFragment;
import com.example.pace.fragmentinputlayouts.InputIncomeFragment;
import com.example.pace.fragmentinputlayouts.InputMpgFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ClientInputFragment extends Fragment {
    public ClientInputFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentClientInputBinding fragmentClientInputBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_client_input, container, false);
        View view = fragmentClientInputBinding.getRoot();

        initCardFragment();

        initBindingData(fragmentClientInputBinding);

        initInputFragments(fragmentClientInputBinding);

        initTabLayout(fragmentClientInputBinding);

        return view;
    }

    public ClientInputCardFragment cardFragment;
    private void initCardFragment() {

        this.cardFragment = new ClientInputCardFragment();
        initCardFrameLayout();
    }

    public ClientInputDataBinding clientInputDataBinding;
    private void initBindingData(FragmentClientInputBinding fragmentClientInputBinding) {

        this.clientInputDataBinding = new ClientInputDataBinding(
                fragmentClientInputBinding.clientInputCalendarView,
                fragmentClientInputBinding.clientInputCalendarEditText,
                fragmentClientInputBinding.clientInputErrorIcon
        );
        fragmentClientInputBinding.setInputDataBinding(this.clientInputDataBinding);
        this.clientInputDataBinding.setCardFragment(this.cardFragment);
    }

    private void initCardFrameLayout() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.client_input_frameLayout, this.cardFragment);
        fragmentTransaction.commit();
    }

    public InputMpgFragment inputMpgFragment;
    public InputGasPriceFragment inputGasPriceFragment;
    public InputDistanceFragment inputDistanceFragment;
    public InputIncomeFragment inputIncomeFragment;
    public ArrayList<Fragment> inputFragments;
    public ClientInputAdapter clientInputAdapter;
    private void initInputFragments(FragmentClientInputBinding fragmentClientInputBinding) {

        this.inputFragments = new ArrayList<>();
        this.inputMpgFragment = new InputMpgFragment(fragmentClientInputBinding);
        this.inputFragments.add(this.inputMpgFragment);

        this.inputGasPriceFragment = new InputGasPriceFragment(fragmentClientInputBinding);
        this.inputFragments.add(this.inputGasPriceFragment);

        this.inputDistanceFragment = new InputDistanceFragment(fragmentClientInputBinding);
        this.inputFragments.add(this.inputDistanceFragment);

        this.inputIncomeFragment = new InputIncomeFragment(fragmentClientInputBinding);
        this.inputFragments.add(this.inputIncomeFragment);

        this.inputIncomeFragment.setFragmentList(this.inputFragments);

        this.clientInputAdapter = new ClientInputAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        for (int index = 0; index < this.inputFragments.size(); ++index) {
            this.clientInputAdapter.addFragment(this.inputFragments.get(index));
        }

        fragmentClientInputBinding.clientInputViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        fragmentClientInputBinding.clientInputViewPager.setAdapter(this.clientInputAdapter);
    }

    private void initTabLayout(FragmentClientInputBinding fragmentClientInputBinding) {

        ArrayList<String> tabLayoutText = new ArrayList<>();
        tabLayoutText.add("MPG");
        tabLayoutText.add("Gas Price");
        tabLayoutText.add("Distance");
        tabLayoutText.add("Income");
        new TabLayoutMediator(
                fragmentClientInputBinding.clientInputTabLayout,
                fragmentClientInputBinding.clientInputViewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabLayoutText.get(position));
            }
        }).attach();
    }
}