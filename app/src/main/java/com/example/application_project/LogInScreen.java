package com.example.application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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





        Email = (EditText) findViewById(R.id.emailTxt);
        Password = (EditText) findViewById(R.id.passwordTxt);

        registerBtn = (Button) findViewById(R.id.RegisterBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = Email.getText().toString();
                String Password = Password.getText().toString();


                if(Email.equals("")||Password.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkEmail = db.checkEmail(Email);
                    if(checkEmail == true){
                        Boolean insert = db.insert(Email, Password);
                        if(insert == true){
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