package com.example.pace.DataBase;

import androidx.annotation.NonNull;

import com.example.pace.clientuser.ClientData;
import com.example.pace.config.ListHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
   private DatabaseReference mDatabase;
    DatabaseReference userRef;
   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
   //******************KEEP SETTING UP THE DATA BASE TO READ THE DATA THAT IS BEING SEND TO THE DATABASE
    //*****************MAKE SURE THE DATA IS NOT BEING OVERWRITTED EITHER.
    public void FirebaseSetUp(ArrayList<ClientData>data){
        String userName = user.getDisplayName();
        String userId = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //I do not like how this is set up but let's keep it for now
        //DELETE LATER THIS IS UGLY AS HELL.
        if(!data.isEmpty()){
            userRef = mDatabase.child(userId);
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

    public void FirebaseReadData(){
        FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
        String user = userId.getUid();
        ValueEventListener ReadData = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClientData data = new ClientData();
                data = snapshot.getValue(ClientData.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }
}
