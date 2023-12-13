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
    ArrayList<ClientData> clienList = new ArrayList<>();

    public void FirebaseSetUp(ArrayList<ClientData>data){

        String userId = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(!data.isEmpty()){
            userRef = mDatabase.child("user: ").child(userId);
            userRef.push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        DatabaseException e = null;
                        e.printStackTrace();
                    }
                }
            });
        }

        FirebaseReadData();

    }


    public void FirebaseReadData() {
        String userId = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userRef = mDatabase.child("user: ").child(userId);

        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (DataSnapshot dataSnap: snapshot.getChildren()) {
                    clientData = dataSnap.getValue(ClientData.class);
                    clienList.add(clientData);
                    ListOrganizer.getInstance().initDailyListData(clientData);
                    // THE LIST ORGANIZER CLASS IS THE ONE THAT MAKE THIS SHIT APPEAR ON THE APP
                    // NOW I NEED TO FOGURED HOW TO MAKE THE DATA TO BE ORGANIZED WITHIN THE APP, MONTH TO MONTH
                    // WEEK TO WEEK AN DAYS.

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
