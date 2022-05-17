package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;


public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, email, phoneNumber, address, username, password, confirmPassword;
    Spinner position;
    Button register;

    String entryStart = "100", midStart = "200", seniorStart = "300", tempId = "";
    int tempIntId = 0, tempCounter = 0, index = 0;

    SQLDBHelper db;
    ArrayList<User> lstUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        email = findViewById(R.id.etEmail);
        phoneNumber = findViewById(R.id.etPhoneNumber);
        address = findViewById(R.id.etAddress);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);

        position = findViewById(R.id.spPositions);
        register = findViewById(R.id.btRegister);


        db = SQLDBHelper.getInstance(getApplicationContext());
        lstUser = db.getAllUsers();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allWork();
            }
        });


    }

    private boolean checkUser(User user) {
        boolean userResult = false;

        for (int i = 0; i < lstUser.size(); ++i) {
            User users = (User) lstUser.get(i);
            if (user.email.equals(users.getEmail())) {
                userResult = true;
                Toast.makeText(getApplicationContext(), "User already registered using this email!", Toast.LENGTH_LONG).show();
                break;

            } else if (user.phoneNumber.equals(users.getPhoneNumber())) {
                userResult = true;
                Toast.makeText(getApplicationContext(), "User already registered using this phone number!", Toast.LENGTH_LONG).show();
                break;
            } else if (user.username.equals(users.getUsername())) {
                userResult = true;
                Toast.makeText(getApplicationContext(), "User already registered using this username! Try a new username", Toast.LENGTH_LONG).show();
                break;
            }

        }
        return userResult;
    }

    private void allWork() {

        User u = new User();
        String positionStr = position.getSelectedItem().toString();


        if (positionStr.equals("Entry-Level")) {

            if (getIndex(1) == -1) {
                u.setId(entryStart);
            }
            else {
                User lastUser = lstUser.get(getIndex(1));
                tempId = lastUser.getId();
                tempCounter = Integer.parseInt(tempId);
                index = tempCounter;
                tempCounter = tempCounter % 100;

                if (index >= 103) {
                    Toast.makeText(RegisterActivity.this, "Database full! Can't add users anymore.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    tempIntId = Integer.parseInt(tempId);
                    tempIntId++;
                    tempId = String.valueOf(tempIntId);
                    u.setId(tempId);
                }

            }
        }
        else if (positionStr.equals("Mid-Level")) {

            if (getIndex(2) == -1) {
                u.setId(midStart);
            }
            else {
                User lastUser = lstUser.get(getIndex(2));
                tempId = lastUser.getId();
                tempCounter = Integer.parseInt(tempId);
                index = tempCounter;
                tempCounter = tempCounter % 100;

                if (index >= 203) {
                    Toast.makeText(RegisterActivity.this, "Database full! Can't add users anymore.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    tempIntId = Integer.parseInt(tempId);
                    tempIntId++;
                    tempId = String.valueOf(tempIntId);
                    u.setId(tempId);
                }

            }
        }
        else if (positionStr.equals("Senior-Level")) {

            if (getIndex(3) == -1) {
                u.setId(seniorStart);
            }
            else {
                User lastUser = lstUser.get(getIndex(3));
                tempId = lastUser.getId();
                tempCounter = Integer.parseInt(tempId);
                index = tempCounter;
                tempCounter = tempCounter % 100;

                if (index >= 301) {
                    Toast.makeText(RegisterActivity.this, "Database full! Can't add users anymore.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    tempIntId = Integer.parseInt(tempId);
                    tempIntId++;
                    tempId = String.valueOf(tempIntId);
                    u.setId(tempId);
                }

            }
        }



        if (u.getId() != null) {
            String priority = String.valueOf(getPriority (u.getId()));
            u.setFirstName(firstName.getText().toString());
            u.setLastName(lastName.getText().toString());
            u.setEmail(email.getText().toString());
            u.setPhoneNumber(phoneNumber.getText().toString());
            u.setAddress(address.getText().toString());
            u.setUsername(username.getText().toString());
            u.setPassword(password.getText().toString());
            u.setPosition(position.getSelectedItem().toString());
            u.setPriorityLevel(priority);
            if (!firstName.getText().toString().equals("") && !lastName.getText().toString().equals("") && !email.getText().toString().equals("") && !phoneNumber.getText().toString().equals("") &&
                    !address.getText().toString().equals("") && !username.getText().toString().equals("") && !password.getText().toString().equals("") && !confirmPassword.getText().toString().equals("")) {
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    if (!checkUser(u)) {
                        db.insertUser(u);
                        Toast.makeText(getApplicationContext(), "User has been added!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        i.putExtra("username", username.getText().toString());
                        i.putExtra("password", password.getText().toString());
                        startActivity(i);
                        finish();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Both passwords must match!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int getPriority(String id) {
        int temp = Integer.parseInt(id);
        temp = temp/100;
        return temp;
    }

    public int getIndex ( int index){
        int tempId = 0;
        String userId = "";
        User user;
        int ret = -1;
        if (index == 1) {
            for (int i = lstUser.size()-1; i >=0 ; --i) {
                user = lstUser.get(i);
                userId = user.getId();
                tempId = Integer.parseInt(userId);

                if (tempId / 100 == 1) {
                    ret = i;
                    break;
                }
            }
            if (ret != -1) {
                return ret;
            }
        } else if (index == 2) {
            for (int i = 0; i < lstUser.size(); ++i) {
                user = lstUser.get(i);
                userId = user.getId();
                tempId = Integer.parseInt(userId);
                if (tempId / 100 == 2) {
                    ret = i;
                }
            }
            if (ret != -1) {
                return ret;
            }
        } else if (index == 3) {
            for (int i = 0; i < lstUser.size(); ++i) {
                user = lstUser.get(i);
                userId = user.getId();
                tempId = Integer.parseInt(userId);
                if (tempId / 100 == 3) {
                    ret = i;
                }
            }
            if (ret != -1) {
                return ret;
            }
        }
        return -1;
    }
}

