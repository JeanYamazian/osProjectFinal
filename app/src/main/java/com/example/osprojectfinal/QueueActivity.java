package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QueueActivity extends AppCompatActivity {

    TextView p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15;
    Button changeCurrent;
    ArrayList<Process> lstProcess;
    SQLDBHelper db;

    Queue queue;
    int queueSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        db = SQLDBHelper.getInstance(getApplicationContext());
        lstProcess = db.getAllProcesses();

        queueSize = lstProcess.size();

        changeCurrent = findViewById(R.id.btChangeCurrent);
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

        queue = new Queue(queueSize);

        for (int i =0;i<lstProcess.size();++i){
            queue.enqueue(lstProcess.get(i));
        }
        
        showQueue();

    }

    public void showQueue (){
        int size = queue.currentSize;

        if (size == 0){
           p1.setText("none");
        }
        else if (size == 1){
            p1.setText(queue.processes[0].getId());
        }
        else if (size == 2){
            p1.setText(queue.processes[0].getId());
            p2.setText(queue.processes[1].getId());
        }else if (size == 3){
            p1.setText(queue.processes[0].getId());
            p2.setText(queue.processes[1].getId());
            p3.setText(queue.processes[2].getId());
        }else if (size == 4){
            p1.setText(queue.processes[0].getId());
            p2.setText(queue.processes[1].getId());
            p3.setText(queue.processes[2].getId());
            p4.setText(queue.processes[3].getId());
        }else if (size == 5){
            p1.setText(queue.processes[0].getId());
            p2.setText(queue.processes[1].getId());
            p3.setText(queue.processes[2].getId());
            p4.setText(queue.processes[3].getId());
            p5.setText(queue.processes[4].getId());
        }else if (size == 6){
            p1.setText(queue.processes[0].getId());
            p2.setText(queue.processes[1].getId());
            p3.setText(queue.processes[2].getId());
            p4.setText(queue.processes[3].getId());
            p5.setText(queue.processes[4].getId());
            p6.setText(queue.processes[5].getId());
        }
    }
}