package com.example.application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class FirstScreenPage extends AppCompatActivity {

    Button carePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_page);

        Button carePlan = (Button) findViewById(R.id.carePlan_btn);
        carePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstScreenPage.this, MainActivity.class);
                startActivity(intent);
            }
        });



        Button Schedual = (Button) findViewById(R.id.schedual_btn);
        Schedual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScreenPage.this, ScheduleAppT.class);
                startActivity(intent);
            }
        });

    }
}