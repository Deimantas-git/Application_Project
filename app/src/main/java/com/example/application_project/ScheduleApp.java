package com.example.application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ScheduleApp extends AppCompatActivity {
    Button startBreakButton, startReminderButton;
    TextView breakTimerTextView, reminderTimerTextView;
    CountDownTimer breakCountDownTimer, reminderCountDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_app);


        int check2 = 0;
        int check3 = 0;

        RadioButton medCheck = (RadioButton) findViewById(R.id.rbMedCheck);
        RadioButton skinCheck = (RadioButton) findViewById(R.id.rbSkinCheck);
        RadioButton discharge = (RadioButton) findViewById(R.id.rbDischarges);


        medCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check1 = 0;

                if(medCheck.isChecked() == true){
                    medCheck.setChecked(false);
                }


                if(medCheck.isChecked() == false) {
                    medCheck.setChecked(true);
                }


            }
        });




        startBreakButton = findViewById(R.id.startBreakButton);
        startReminderButton = findViewById(R.id.startReminderButton);
        breakTimerTextView = findViewById(R.id.breakTimerView);
        reminderTimerTextView = findViewById(R.id.reminderTimerView);

        startBreakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer("Break", 30 * 60 * 1000, breakTimerTextView, breakCountDownTimer);
            }
        });

        startReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer("Reminder for care plan to be checked", 40 * 60 * 1000, reminderTimerTextView, reminderCountDownTimer);
            }
        });
    }

    private void startTimer(final String timerName, long duration, final TextView timerTextView, CountDownTimer countDownTimer) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimerText(timerTextView, millisUntilFinished);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00:00");
                showTimerFinishedMessage(timerName);
            }
        }.start();
    }

    private void updateTimerText(TextView timerTextView, long millisUntilFinished) {
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
        int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }

    private void showTimerFinishedMessage(String timerName) {
        Toast.makeText(ScheduleApp.this, timerName + " ", Toast.LENGTH_LONG).show();
    }
}

