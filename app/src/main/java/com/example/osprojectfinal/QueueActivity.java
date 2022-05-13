package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class QueueActivity extends AppCompatActivity {

    TextView p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15;
    ArrayList<Process> lstProcess;
    SQLDBHelper db;

    int queueSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        db = SQLDBHelper.getInstance(getApplicationContext());
        lstProcess = db.getAllProcesses();

        queueSize = lstProcess.size();

        p1 = findViewById(R.id.P1);
        p2 = findViewById(R.id.P2);
        p3 = findViewById(R.id.P3);
        p4 = findViewById(R.id.P4);
        p5 = findViewById(R.id.P5);
        p6 = findViewById(R.id.P6);
        p7 = findViewById(R.id.P7);
        p8 = findViewById(R.id.P8);
        p9 = findViewById(R.id.P9);
        p10 = findViewById(R.id.P10);
        p11 = findViewById(R.id.P11);
        p12 = findViewById(R.id.P12);
        p13 = findViewById(R.id.P13);
        p14 = findViewById(R.id.P14);
        p15 = findViewById(R.id.P15);



    }
}