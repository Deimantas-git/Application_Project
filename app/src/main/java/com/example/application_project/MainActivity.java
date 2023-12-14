package com.example.application_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView dateDisplay;
    private GridView PatientGridList;
    private Button addBtn;
    private EditText itemEdt;
    private ArrayList<String> ptntList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateDisplay = findViewById(R.id.txt_display);



        PatientGridList = findViewById(R.id.PatientInfo);
        addBtn = findViewById(R.id.addBtn);
        itemEdt = findViewById(R.id.idEdtItemName);
        ptntList = new ArrayList<>();

        ArrayAdapter<String> adt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ptntList);

        PatientGridList.setAdapter(adt);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEdt.getText().toString();

                if (!item.isEmpty()) {
                    ptntList.add(item);
                    adt.notifyDataSetChanged();
                }
            }
        });
    }
}
