package com.example.application_project;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LogInScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button SignOut;
    Button registerBtn;
    Button googleAuth;
    TextView emailTxt;
    TextView passwordTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        DatabaseSetUp db;
        EditText Email, Password;
        Button logInbtn, registerBtn;

        db = new DatabaseSetUp(this);

        emailTxt = (EditText) findViewById(R.id.emailTxt);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt);
        googleAuth = (Button) findViewById(R.id.googleSignIn);
        mAuth = FirebaseAuth.getInstance();

        registerBtn = (Button) findViewById(R.id.RegisterBtn);
        logInbtn = (Button) findViewById(R.id.logInbtn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInScreen.this, RegisterScreenActivity.class);
                startActivity(i);
            }
        });


        logInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = emailTxt.getText().toString();
                String Password = passwordTxt.getText().toString();


                if(Email.equals("")||Password.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkEmail = db.checkEmail(Email);
                    if(checkEmail == true){
                        Boolean checkPassword = db.checkPassword(Email, Password);
                        if(checkPassword == true){
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInScreen.this, FirstScreenPage.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Incorrect email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        googleAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSignInIntent();
            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////////
                                    /*Experimenting Ignore code below:*/
        ///////////////////////////////////////////////////////////////////////////////////////////////////


//        Button sendEmailButton = findViewById(R.id.sendEmailButton);
//        sendEmailButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create an intent to send an email
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//                emailIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
//
//                // Set email recipients (to, cc, bcc)
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@gmail.com"});
//                emailIntent.putExtra(Intent.EXTRA_CC, new String[]{"cc@gmail.com"});
//                emailIntent.putExtra(Intent.EXTRA_BCC, new String[]{"bcc@gmail.com"});
//
//                // Set email subject and body
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email");
//
//                try {
//                    // Show the email app chooser
//                    startActivity(Intent.createChooser(emailIntent, "Send email using..."));
//                } catch (ActivityNotFoundException e) {
//                    // Handle the case where no email app is installed
//                    Toast.makeText(getApplicationContext(), "No email app installed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////
                                    /*Experimenting Ignore code above:*/
        ///////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(

            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );



    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    public void createSignInIntent()
    {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.AnonymousBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }


    public void onSignInResult(FirebaseAuthUIAuthenticationResult result){
        IdpResponse response = result.getIdpResponse();

        if(result.getResultCode() == RESULT_OK){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user.isEmailVerified()) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LogInScreen.this, FirstScreenPage.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "LogIn Denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Log in has failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void signOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        createSignInIntent();
                    }
                });
    }
}