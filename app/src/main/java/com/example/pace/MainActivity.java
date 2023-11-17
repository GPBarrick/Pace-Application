package com.example.pace;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeTest();

        InitializeViews();

        InitializeDayListAdapter();

//-------------------------------------testing sign out delete after complete---------------------
        Button singOut = findViewById(R.id.Sign_out_btn);//THIS IS THE ID THAT IS ATTACHED TO THE LOGOUT.
        singOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
                    Intent singInIntent = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(singInIntent);
                    finish();

            }
        });
        //-------------------------------------testing sign out delete after complete---------------------
    }
   @Override
   protected void onStart(){
       super.onStart();
       FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
       if(CurrentUser == null && GoogleAuthProvider.PROVIDER_ID.isEmpty()){
           Intent singInIntent = new Intent(MainActivity.this, SignInActivity.class);
           startActivity(singInIntent);
           finish();

       }

   }

    /* 11/5/2023 Initialize Test object member */
    public Test t;
    private void InitializeTest() { this.t = new Test(); }


    /* 11/5/2023 Set the xml elements to their public member objects */
    public RecyclerView dayList, averagesList;
    private void InitializeViews() {
        this.averagesList = findViewById(R.id.activity_main_averages_list);
        this.dayList = findViewById(R.id.activity_main_daily_list);
    }

    /* 11/5/2023 Initialize the DayListAdapter and LinearLayoutManager for this.dayList */
    public DayListAdapter dayListAdapter;
    private void InitializeDayListAdapter() {
        this.dayListAdapter = new DayListAdapter(t.populateClientModuleList());
        this.dayList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        this.dayList.setAdapter(this.dayListAdapter);
    }
}