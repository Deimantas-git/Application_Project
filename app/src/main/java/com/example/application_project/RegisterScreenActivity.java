package com.example.application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreenActivity extends AppCompatActivity {


    EditText name, password, repassword;
    Button register;
    DatabaseSetUp db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);


        db = new DatabaseSetUp(this);
        name = (EditText) findViewById(R.id.newEmailTxt);
        password = (EditText) findViewById(R.id.newPasswordTxt);
        repassword = (EditText) findViewById(R.id.newRePasswordTxt);


        register = (Button) findViewById(R.id.registerBtn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = name.getText().toString();
                String password_text = password.getText().toString();
                String rePassword_text = repassword.getText().toString();

                if(email_text.equals("")||password_text.equals("")||rePassword_text.equals("")){
                    Toast.makeText(getApplicationContext(), "Data tables empty", Toast.LENGTH_SHORT).show();
                }else {
                    if (password_text.equals(rePassword_text)) {
                        Boolean checkEmail = db.checkEmail(email_text);
                        if (checkEmail == false) {
                            Boolean insert = db.insert(email_text, password_text);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Account registerd", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Account not registerd", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}