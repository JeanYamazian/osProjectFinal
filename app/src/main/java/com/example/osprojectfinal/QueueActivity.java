package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QueueActivity extends AppCompatActivity {

    TextView p1, p2, p3, p4, p5, p6;
    TextView r1, r2, r3, r4, r5, r6;
    TextView currentlyRunning;
    Button changeCurrent;
    ArrayList<Process> lstProcess;
    SQLDBHelper db;

    Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        db = SQLDBHelper.getInstance(getApplicationContext());
        lstProcess = db.getAllProcesses();

        changeCurrent = findViewById(R.id.btChangeCurrent);
        currentlyRunning = findViewById(R.id.currentlyRunning);
        p1 = findViewById(R.id.P1);
        p2 = findViewById(R.id.P2);
        p3 = findViewById(R.id.P3);
        p4 = findViewById(R.id.P4);
        p5 = findViewById(R.id.P5);
        p6 = findViewById(R.id.P6);

        r1 = findViewById(R.id.R1);
        r2 = findViewById(R.id.R2);
        r3 = findViewById(R.id.R3);
        r4 = findViewById(R.id.R4);
        r5 = findViewById(R.id.R5);
        r6 = findViewById(R.id.R6);

        queue = new Queue(lstProcess.size());
        db.insertMe();

        if (db.getCurrentProcessId() == null) {
            currentlyRunning.setText("NULL");
        }
        else {
            currentlyRunning.setText(db.getCurrentProcessId());
        }


        for (int i = 0; i < lstProcess.size(); ++i) {
            Process p = lstProcess.get(i);
            if (i == 0) {
                queue.enqueue(lstProcess.get(i));
                db.updateProcess(p,p.getId(), p.getPriority(), "Ready","Connected to Printer",p.getInitializedBy());
                mutualExclusion();
            } else {
                queue.enqueue(lstProcess.get(i));
                db.updateProcess(p,p.getId(), p.getPriority(), "Ready","Connected to Printer",p.getInitializedBy());
            }
        }

        changeCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateMe("0", "1");
                mutualExclusion();
            }
        });

        showQueue();


    }

    public void currentFront() {
        if (!queue.isEmpty()) {
            Process p = queue.dequeue();

            db.updateCurrent(p, p.getId(), p.getPriority(), "Running", "Linked to Printer",p.getInitializedBy());

            currentlyRunning.setText(String.valueOf(p.getId()));
            showQueue();
            db.deleteProcess(String.valueOf(p.getId()));

        } else {
            db.updateMe("0", "1");
            db.deleteCurrentlyRunning();
            currentlyRunning.setText("NULL");
            Toast.makeText(getApplicationContext(), "Your queue is empty!", Toast.LENGTH_LONG).show();
            return;
        }

    }

    public void mutualExclusion() {
        if (db.getMeValue().equals("0")) {
            db.updateMe("1", "0");
            currentFront();
        } else {
            return;
        }
    }


    public void showQueue() {
        int size = queue.currentSize;

        if (size == 0) {
            p1.setText("---");
            p2.setText("---");
            p3.setText("---");
            p4.setText("---");
            p5.setText("---");
            p6.setText("---");

            r1.setText("      |\n REAR");
            r5.setText("");
            r2.setText("");
            r3.setText("");
            r4.setText("");
            r6.setText("");

        } else if (size == 1) {
            p1.setText(queue.processes[queue.front].getId());

            p2.setText("---");
            p3.setText("---");
            p4.setText("---");
            p5.setText("---");
            p6.setText("---");

            r1.setText("      |\n REAR");
            r5.setText("");
            r2.setText("");
            r3.setText("");
            r4.setText("");
            r6.setText("");
        } else if (size == 2) {
            p1.setText(queue.processes[queue.front].getId());
            p2.setText(queue.processes[queue.front + 1].getId());

            p3.setText("---");
            p4.setText("---");
            p5.setText("---");
            p6.setText("---");

            r2.setText("      |\n REAR");
            r5.setText("");
            r1.setText("");
            r3.setText("");
            r4.setText("");
            r6.setText("");
        } else if (size == 3) {
            p1.setText(queue.processes[queue.front].getId());
            p2.setText(queue.processes[queue.front + 1].getId());
            p3.setText(queue.processes[queue.front + 2].getId());
            p4.setText("---");
            p5.setText("---");
            p6.setText("---");

            r3.setText("      |\n REAR");
            r5.setText("");
            r1.setText("");
            r2.setText("");
            r4.setText("");
            r6.setText("");
        } else if (size == 4) {
            p1.setText(queue.processes[queue.front].getId());
            p2.setText(queue.processes[queue.front + 1].getId());
            p3.setText(queue.processes[queue.front + 2].getId());
            p4.setText(queue.processes[queue.front + 3].getId());
            p5.setText("---");
            p6.setText("---");

            r4.setText("      |\n REAR");
            r5.setText("");
            r1.setText("");
            r2.setText("");
            r3.setText("");
            r6.setText("");
        } else if (size == 5) {
            p1.setText(queue.processes[queue.front + 0].getId());
            p2.setText(queue.processes[queue.front + 1].getId());
            p3.setText(queue.processes[queue.front + 2].getId());
            p4.setText(queue.processes[queue.front + 3].getId());
            p5.setText(queue.processes[queue.front + 4].getId());
            p6.setText("---");

            r5.setText("      |\n REAR");
            r1.setText("");
            r2.setText("");
            r3.setText("");
            r4.setText("");
            r6.setText("");
        } else if (size == 6) {
            p1.setText(queue.processes[queue.front + 0].getId());
            p2.setText(queue.processes[queue.front + 1].getId());
            p3.setText(queue.processes[queue.front + 2].getId());
            p4.setText(queue.processes[queue.front + 3].getId());
            p5.setText(queue.processes[queue.front + 4].getId());
            p6.setText(queue.processes[queue.front + 5].getId());

            r6.setText("      |\n REAR");
            r5.setText("");
            r1.setText("");
            r2.setText("");
            r3.setText("");
            r4.setText("");
        }
    }
}