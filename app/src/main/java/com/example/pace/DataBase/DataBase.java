package com.example.pace.DataBase;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pace.clientuser.ClientData;
import com.example.pace.config.ListHolder;
import com.example.pace.config.ListOrganizer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataBase {
    private DatabaseReference mDatabase;
    DatabaseReference userRef;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ClientData clientData;
    ArrayList<ClientData> data = new ArrayList<>();
    ArrayList<ClientData> clienList = new ArrayList<>();
    ArrayList<ClientData> compareData = new ArrayList<>();

    public void FirebaseSetUp(){

        String userId = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        data = ListHolder.getInstance().clientDataList;

        if(data.size() >= 1){
            userRef = mDatabase.child("user: ").child(userId);
            userRef.push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Exception e = task.getException();
                        if (e != null) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            data.clear();
        }
        else{
            OrganizeFireBaseData();
        }


    }
    public void OrganizeFireBaseData(){
        FirebaseReadData();
        for(int i=0; i<clienList.size(); i++){
            if(compareData.get(i) == clienList.get(i)){
                if(compareData.get(i).getMonth() == clienList.get(i).getMonth()){
                    ListOrganizer.getInstance().initMonthlyListData(clienList.get(i));
                }
                if(compareData.get(i).getWeekOfYear() == clienList.get(i).getWeekOfYear()){
                    ListOrganizer.getInstance().initWeeklyListData(clienList.get(i));
                }
                if(compareData.get(i).getDay() == clienList.get(i).getDay()){
                    ListOrganizer.getInstance().initDailyListData(clienList.get(i));
                }

            }
            else{
                return;
            }


        }
        clienList.clear();
    }
    private void FirebaseReadData() {
        String userId = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userRef = mDatabase.child("user: ").child(userId);

        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                clientData = null;
                for (DataSnapshot dataSnap: snapshot.getChildren()) {
                    clientData = dataSnap.getValue(ClientData.class);
                    clienList.add(clientData);
                    compareData.add(clientData);

                }

            }



            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        userRef.addChildEventListener(childEventListener);
    }

}
