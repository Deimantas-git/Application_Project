package com.example.application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreenPage extends AppCompatActivity {


    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_page);

        nextbtn = (Button) findViewById(R.id.next);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstScreenPage.this, ScheduleApp.class);
                startActivity(i);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////
                                                /*Add your code here*/
        ///////////////////////////////////////////////////////////////////////////////////////////////////
    }
}