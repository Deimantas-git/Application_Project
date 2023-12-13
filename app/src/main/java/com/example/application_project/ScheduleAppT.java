package com.example.application_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ScheduleAppT extends AppCompatActivity {
    Button startBreakButton, startReminderButton, savePatientButton;
    TextView breakTimerTextView, reminderTimerTextView;
    CountDownTimer breakCountDownTimer, reminderCountDownTimer;
    DatabaseSetUp databaseHelper;
    RadioButton radioButton1,radioButton3, radioButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_app);





        radioButton1 = findViewById(R.id.rbDischarges);
        radioButton2 = findViewById(R.id.rbMedCheck);
        radioButton3 = findViewById(R.id.rbSkinCheck);
        startBreakButton = findViewById(R.id.startBreakButton);
        startReminderButton = findViewById(R.id.startReminderButton);
        breakTimerTextView = findViewById(R.id.breakTimerView);
        reminderTimerTextView = findViewById(R.id.reminderTimerView);
        savePatientButton = findViewById(R.id.savePatientButton);
        databaseHelper = new DatabaseSetUp(this);

        savePatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatient();
            }
        });
    }
    private void savePatient() {
        EditText patientNameEditText = findViewById(R.id.patientNameEditText);
        String patientName = patientNameEditText.getText().toString();

        if (!patientName.isEmpty()) {
            long result = databaseHelper.insertPatient(patientName);

            if (result != -1) {
                showPatientSavedMessage();
                patientNameEditText.setText("");
            } else {
                Toast.makeText(ScheduleAppT.this, "Error saving patient", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ScheduleAppT.this, "Please enter a patient name", Toast.LENGTH_SHORT).show();
        }


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

    private void showPatientSavedMessage() {
        Toast.makeText(ScheduleAppT.this, "Patient Saved!", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(ScheduleAppT.this, timerName + " ", Toast.LENGTH_LONG).show();
    }

}

