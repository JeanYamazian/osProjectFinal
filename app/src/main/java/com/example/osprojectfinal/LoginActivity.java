package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button registerBtn,loginBtn;
    TextView usernamTxtV, passwordTxtV;
    ArrayList<User> lstUser;

    SQLDBHelper db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerBtn = findViewById(R.id.btRegister);
        usernamTxtV = findViewById(R.id.userName);
        passwordTxtV = findViewById(R.id.password);
        loginBtn = findViewById(R.id.LoginBTN);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = SQLDBHelper.getInstance(getApplicationContext());
                lstUser = db.getAllUsers();

                User u = new User();

                if(!usernamTxtV.getText().toString().equals("") && !passwordTxtV.getText().toString().equals(""))
                {
                    if(lstUser.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"List is empty! No users.",Toast.LENGTH_LONG).show();
                    }

                    boolean userCheck = false;
                    for(int i=0;i<lstUser.size();++i)
                    {
                        User user = lstUser.get(i);

                        if(user.getUsername().equals(usernamTxtV.getText().toString()) && user.getPassword().equals(passwordTxtV.getText().toString()))
                        {
                            userCheck = true;
                            Intent intent  = new Intent(getApplicationContext(), PrintActivity.class);
                            intent.putExtra("USER", user);
                            startActivity(intent);
                            finish();
                        }
                    }

                    if(!userCheck)
                    {
                        Toast.makeText(getApplicationContext(),"User is not found!",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Both fields are required",Toast.LENGTH_LONG).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean checkUsernameAndPassword(User username, User password)
    {
        boolean userCheck = false;
        for(int i=0;i<lstUser.size();++i)
        {
            User u = lstUser.get(i);

            if(username.username.equals(u.getUsername()) && password.password.equals(u.getPassword()))
            {
                userCheck = true;
                break;
            }
        }
        return userCheck;
    }
}

