package com.example.pace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUpActivity extends AppCompatActivity {

    String Email;
    String PassWord;
    Button signUp;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText userEmail, userPassword;
        userEmail = findViewById(R.id.Email_edit_text);
        userPassword = findViewById(R.id.password_edit_text);
        signUp = findViewById(R.id.Create_user_btn2);
        mAuth = FirebaseAuth.getInstance();
        
        //checks that the user enter something in the editText and send them to the
        //user account creation method down below.
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = String.valueOf(userEmail.getText());
                PassWord = String.valueOf(userPassword.getText());


                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(signUpActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(PassWord)){
                    Toast.makeText(signUpActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                createUser();

            }
        });

        //if the user hit the cancel Bnt it will send them back into the logIn activity.
        Button cancel = findViewById(R.id.Cancel_Bnt);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //It checks if the user was created successfully or the authentication failed.
private void createUser(){
    mAuth.createUserWithEmailAndPassword(Email, PassWord)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(signUpActivity.this, "Account Created",
                                Toast.LENGTH_SHORT).show();
                        Intent backHome = new Intent(signUpActivity.this, MainActivity.class);
                        startActivity(backHome);
                        finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(signUpActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }

                }
            });

}
    private void createUser(){
        mAuth.createUserWithEmailAndPassword(Email, PassWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signUpActivity.this, "Account Created",
                                    Toast.LENGTH_SHORT).show();
                            Intent backHome = new Intent(signUpActivity.this, MainActivity.class);
                            startActivity(backHome);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(signUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }


}