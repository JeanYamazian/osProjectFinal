package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView text;
    Button print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.tvText);
        print = findViewById(R.id.btPrint);


        Intent intent = getIntent();
        int priority = intent.getIntExtra("PRIORITY", 0);

        text.setText("GOOD PRIORITY "+priority);
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Process process = new Process();
            }
        });
    }
}
