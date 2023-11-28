package com.example.application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

public class LogInScreen extends AppCompatActivity {


    Button registerBtn;

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





        registerBtn = (Button) findViewById(R.id.RegisterBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LogInScreen.this, RegisterScreenActivity.class);
                startActivity(i);

                String Email = emailTxt.getText().toString();
                String Password = passwordTxt.getText().toString();


                if(Email.equals("")||Password.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkEmail = db.checkEmail(Email);
                    if(checkEmail == true){
                        Boolean checkPassword = db.insert(Email, Password);
                        if(checkPassword == true){
                            Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}