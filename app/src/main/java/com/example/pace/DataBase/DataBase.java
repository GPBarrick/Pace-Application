package com.example.pace.DataBase;

import com.example.pace.clientuser.ClientData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBase {
   // private ClientData dataToSend;
    //having issues with the client data being passed into the  firebase setup function.
    //go to main activity and pass the right index of the arraylist into the fire base set up
    //test the write data and then keep going.
    public void FirebaseSetUp(ClientData data){


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("testing database");

        myRef.setValue(data);
    }
}
