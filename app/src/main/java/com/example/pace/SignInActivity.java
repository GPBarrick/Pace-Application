package com.example.pace;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity {
    SignInButton GoogleSignBnt;
    FirebaseAuth mAuth;
    Button signUpBnt;
    Button singInBnt;
    String userEmail, userPassWord;
    SignInClient oneTapClient;
    BeginSignInRequest signUpRequest;

    protected void onStart(){
        super.onStart();
        FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (CurrentUser != null) {
            Intent singInIntent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(singInIntent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        GoogleSignBnt = findViewById(R.id.google_signIn_btn);
        mAuth = FirebaseAuth.getInstance();
        singInBnt = findViewById(R.id.Sign_in_btn);
        signUpBnt = findViewById(R.id.Sign_up_btn);

        //Bnt lister send the user to the create account activity.
        signUpBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SingUpIntent = new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(SingUpIntent);

            }
        });

        //Email and password sign in Bnt listener
        singInBnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText userPassWordIn = findViewById(R.id.password_edit_text);
                EditText emailInput = findViewById(R.id.UserName_edit_text);
                userEmail = String.valueOf(emailInput.getText());
                userPassWord = String.valueOf(userPassWordIn.getText());

                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(SignInActivity.this,"Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(userPassWord)){
                    Toast.makeText(SignInActivity.this,"Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                signInMethod();


            }
        });
//-----------------------Google SignIn---------------------------------------------------
        ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if(result.getResultCode() == Activity.RESULT_OK){
                            try {
                                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                                String idToken = credential.getGoogleIdToken();
                                if (idToken !=  null) {
                                    AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                                    GoogleSingInCredentials(firebaseCredential);
                                }
                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        oneTapClient = Identity.getSignInClient(this);
        signUpRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.webId))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();


        GoogleSignBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleActivityHandler(activityResultLauncher);
            }
        });

        //-----------------------Google SingIn----------------------------------------------------------------------------------

    }

    //If user made an account with email and password this handler the request and sign the user in.
    void signInMethod(){

        mAuth.signInWithEmailAndPassword(userEmail, userPassWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Login Successful.",
                                    Toast.LENGTH_SHORT).show();
                            Intent returnHomeIntent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(returnHomeIntent);
                            finish();
                        } else {

                            Toast.makeText(SignInActivity.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
    //part of google sing in. This save the user credentials into firebase database
    //help keep user logged in if they use google sign in.
    void GoogleSingInCredentials(AuthCredential firebaseCredential){
        mAuth.signInWithCredential(firebaseCredential).addOnCompleteListener(
                SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent singInIntent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(singInIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                        }
                    }
                });

    }

    //handler the google sing in request and look for google accounts on the device.
    void GoogleActivityHandler(ActivityResultLauncher activityResultLauncher){
        oneTapClient.beginSignIn(signUpRequest)
                .addOnSuccessListener(SignInActivity.this, new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult result) {
                        IntentSenderRequest intentSenderRequest =
                                new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                        activityResultLauncher.launch(intentSenderRequest);

                    }
                })
                .addOnFailureListener(SignInActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // No Google Accounts found. Just continue presenting the signed-out UI.
                        Log.d("TAG", e.getLocalizedMessage());
                        Toast.makeText(SignInActivity.this,"No Google Account", Toast.LENGTH_LONG).show();
                    }
                });

    }
}
