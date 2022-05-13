package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrintActivity extends AppCompatActivity {

    Button button;
    TextView text;
    Button print , buttonOpen;


    String processPriority = "", startProcess1 = "100", startProcess2 = "200",
            startProcess3 = "300", processId = "", processState = "", processIoInformation = "";

    SQLDBHelper db;
    ArrayList<User> lstUser;
    ArrayList<Process> lstProcess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        text = findViewById(R.id.tvText);
        print = findViewById(R.id.btPrint);
        buttonOpen = findViewById(R.id.buttonOpen);

        db = SQLDBHelper.getInstance(getApplicationContext());
        lstUser = db.getAllUsers();
        lstProcess = db.getAllProcesses();


        Intent intent = getIntent();
        User user = (User) intent.getParcelableExtra("USER");

        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lstUser = db.getAllUsers();
                lstProcess = db.getAllProcesses();

                allWork(user);
            }
        });

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),QueueActivity.class);
                startActivity(i);
            }
        });
    }

    public User getUser (String username){
        User user = new User();

        for (int i =0; i<lstUser.size();++i){
            user = lstUser.get(i);
            String test = user.getUsername();
            if (username.equals(test)){
                user = lstUser.get(i);
            }
        }

        return user;
    }

    private void allWork(User user) {

        Process process = new Process();
        processPriority = user.getPriorityLevel();

        if (user.getPriorityLevel().equals("1")){

            if (getIndex(1) == -1)
            {
                processId = startProcess1;
                processPriority = "1";
                processState = "New";
                processIoInformation = "Requesting to access printer";
            }
            else {

                process = lstProcess.get(getIndex(1));
                int temp = Integer.parseInt(process.getId());

                if (temp >= 101){
                    Toast.makeText(PrintActivity.this, "Limit is over!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    temp ++;
                    processId = String.valueOf(temp);
                    processPriority = "1";
                    processState = "New";
                    processIoInformation = "Requesting to access printer";
                }
            }
        }
        else if (user.getPriorityLevel().equals("2")){
            if (getIndex(2) == -1)
            {
                processId = startProcess2;
                processPriority = "2";
                processState = "New";
                processIoInformation = "Requesting to access printer";
            }
            else {
                process = lstProcess.get(getIndex(2));
                int temp = Integer.parseInt(process.getId());

                if (temp >= 201){
                    Toast.makeText(PrintActivity.this, "Limit is over!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    temp ++;
                    processId = String.valueOf(temp);
                    processPriority = "2";
                    processState = "New";
                    processIoInformation = "Requesting to access printer";
                }
            }
        }
        else if (user.getPriorityLevel().equals("3")){
            if (getIndex(3) == -1)
            {
                processId = startProcess3;
                processPriority = "3";
                processState = "New";
                processIoInformation = "Requesting to access printer";
            }
            else {
                process = lstProcess.get(getIndex(3));
                int temp = Integer.parseInt(process.getId());

                if (temp >= 301){
                    Toast.makeText(PrintActivity.this, "Limit is over!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    temp ++;
                    processId = String.valueOf(temp);
                    processPriority = "3";
                    processState = "New";
                    processIoInformation = "Requesting to access printer";
                }
            }
        }

        process.setIoInformation(processIoInformation);
        process.setState(processState);
        process.setId(processId);
        process.setPriority(processPriority);

        db.insertProcess(process);
    }

    public int getIndex ( int index){
        int tempId = 0;
        String userId = "";
        Process process;
        int ret = -1;
        if (index == 1) {
            for (int i = lstProcess.size() -1; i >= 0; --i) {
                process = lstProcess.get(i);
                userId = process.getId();
                tempId = Integer.parseInt(userId);

                if (tempId / 100 == 1) {
                    ret = i;
                    break;
                }
            }
            if (ret != -1) {
                return ret;
            }
        }else if (index == 2) {
            for (int i = lstProcess.size() -1; i >= 0; --i) {
                process = lstProcess.get(i);
                userId = process.getId();
                tempId = Integer.parseInt(userId);

                if (tempId / 100 == 2) {
                    ret = i;
                    break;
                }
            }
            if (ret != -1) {
                return ret;
            }
        } else if (index == 3) {
            for (int i = lstProcess.size() -1; i >= 0; --i) {
                process = lstProcess.get(i);
                userId = process.getId();
                tempId = Integer.parseInt(userId);

                if (tempId / 100 == 3) {
                    ret = i;
                    break;
                }
            }
            if (ret != -1) {
                return ret;
            }
        }
        return -1;
    }
}

