package com.example.pace.fragmentlayouts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pace.DataBase.DataBase;
import com.example.pace.R;
import com.example.pace.adapters.MainFragmentAdapter;
import com.example.pace.clientuser.ClientData;
import com.example.pace.config.ListHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    DataBase database = new DataBase();
    ArrayList<ClientData> clientDataList = new ArrayList<>();
    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        initFragments();
        initTabLayout();
        initKeyboardSoftInput();
        initSignOut();

        return view;
    }

    public ViewPager2 mainViewPager;
    public TabLayout mainTabLayout;

    public ImageButton signOutButton;
    private void initViews(View view) {
        this.mainViewPager = view.findViewById(R.id.main_viewPager);
        this.mainTabLayout = view.findViewById(R.id.main_tabLayout);
        this.signOutButton = view.findViewById(R.id.main_notification_button);
    }
    public ArrayList<Fragment> mainFragmentList;
    public HomeFragment homeFragment;
    public ClientInputFragment clientInputFragment;
    private void initFragments() {
        ListHolder.getInstance().mainActivityPager = this.mainViewPager;

        this.homeFragment = new HomeFragment();
        this.clientInputFragment = new ClientInputFragment();

        this.mainFragmentList = new ArrayList<>();
        this.mainFragmentList.add(this.homeFragment);
        this.mainFragmentList.add(this.clientInputFragment);

        MainFragmentAdapter fragmentAdapter = new MainFragmentAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        for (int index = 0; index < this.mainFragmentList.size(); ++index) {
            fragmentAdapter.addFragment(this.mainFragmentList.get(index));
        }

        initViewPager(fragmentAdapter);
    }
    private void initViewPager(MainFragmentAdapter fragmentAdapter) {
        this.mainViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        this.mainViewPager.setAdapter(fragmentAdapter);
    }
    private void initTabLayout() {
        ArrayList<String> tabLayoutText = new ArrayList<>();
        tabLayoutText.add("Home");
        tabLayoutText.add("Add");
        new TabLayoutMediator(this.mainTabLayout, this.mainViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabLayoutText.get(position));
            }
        }).attach();
//***************************************check that the app return to home and add to the database***********************
        mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // defaulted to 0 since the app action is to return to home.
                //after the user finish inputtind the data the data is send to the database.
                if (position == 0) {
                    database.FirebaseSetUp(ListHolder.getInstance().clientDataList);
                }
            }
        });
//***************************************END OF THE BLOCK THAT CALL THE DATABASE*****************************************
    }

    private void initKeyboardSoftInput() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    private void initSignOut() {
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(),"You are signed out", Toast.LENGTH_LONG).show();
            }
        });
    }






}