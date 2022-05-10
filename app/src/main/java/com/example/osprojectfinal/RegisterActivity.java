package com.example.osprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, email, phoneNumber, address, username, password, confirmPassword;
    Spinner position;
    Button register;

    ArrayList<String> ids = new ArrayList<>();


    SQLiteDatabase database ;
    int idGenerated = 0;
    int max = 1;
    int min = 0;
    private static int counterMid = 0;
    String startMid = "";
    int finalId = 0;
    int index =0;

    SQLDBHelper db;
    ArrayList<User> lstUser;

    private String userId;

    private static final int entryLevel = 1000;
    private static final int midLevel = 2000;
    private static final int seniorLevel = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        insertDatabase();

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


        database = openOrCreateDatabase("mydb", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS counter (id NUMBER, value NUMBER);");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {allWork();}
        });


    }

    private boolean checkUser(User user) {
        boolean userResult = false;

        for (int i = 0; i < lstUser.size(); ++i)
        {
            User users = (User) lstUser.get(i);
            if (user.email.equals(users.getEmail())) {
                userResult = true;
                Toast.makeText(getApplicationContext(), "User already registered using this email!", Toast.LENGTH_LONG).show();
                break;

            } else if (user.phoneNumber.equals(users.getPhoneNumber())) {
                userResult = true;
                Toast.makeText(getApplicationContext(), "User already registered using this phone number!", Toast.LENGTH_LONG).show();
                break;
            }
            else if(user.username.equals(users.getUsername()))
            {
                userResult = true;
                Toast.makeText(getApplicationContext(), "User already registered using this username! Try a new username", Toast.LENGTH_LONG).show();
                break;
            }

        }
        return userResult;
    }

    private void allWork()
    {
        User u = new User();


        String positionStr = position.getSelectedItem().toString();

//            Random rand = new Random();
//            userId = String.valueOf(rand.nextInt(max - min + 1) + min);
//
//
//
//            String tempStr = String.valueOf(rand.nextInt(max - min + 1) + min);


//                    if (positionStr.equals("Entry-Level")) {
//                        int min = 100;
//                        int max = 199;
//                        Random rng = new Random(); // Ideally just create one instance globally
//                        List<String> generated = new ArrayList<String>();
//                        for (int i = 0; i < lstUser.size(); i++)
//                        {
//                            while(true)
//                            {
//                                String nexttt = String.valueOf(rng.nextInt(max - min + 1) + min);
//                                if (!generated.contains(next))
//                                {
//                                    // Done for this iteration
//                                    generated.add(nexttt);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    else



//
//        int min = 200;
//        int max = 202;
//        Random rng = new Random(); // Ideally just create one instance globally
//
//
//        List<Integer> generated = new ArrayList<Integer>();
//
//        while(true)
//        {
//            next = rng.nextInt(max - min + 1) + min;
//            if (!generated.contains(next))
//            {
//                // Done for this iteration
//                generated.add(next);
//                userId.equals(String.valueOf(next));
//                break;
//            }
//            else
//            {
//                Toast.makeText(getApplicationContext(), "Unable to insert user!", Toast.LENGTH_LONG).show();
//            }
//        }

//    ids.add("q");
//        ids.add("q");
//        ids.add("q");
//        ids.add("q");

        counterMid = getValueDatabase ();
                        if (positionStr.equals("Mid-Level")) {
                            if ( counterMid<201) {

                                if (counterMid == 0) {
                                    finalId = 200;
                                    u.setId(finalId);
                                    updateDatabase(counterMid);
                                    counterMid = getValueDatabase ();
                                } else {
                                    finalId++;
                                    u.setId(finalId);
                                    updateDatabase(counterMid);
                                    counterMid = getValueDatabase ();
                                }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Full ya khaye", Toast.LENGTH_SHORT).show();
                            finish();
                        }


//                            if (counterMid<=1) {
//                                Random rand = new Random();
//                                idGenerated = rand.nextInt((max - min + 1) + min);
//                                finalId = "20" + String.valueOf(idGenerated);
//
//                                if (ids.isEmpty()) {
//                                    //ids.add(finalId);
//                                    u.setId(finalId);
//                                    counterMid++;
//                                } else if (!ids.isEmpty()) {
//
//
//                                    for (int i = 0; i < ids.size(); ++i) {
//                                        if (finalId.equals(ids.get(i))) {
//                                            idGenerated = rand.nextInt((max - min + 1) + min);
//                                            finalId = "20" + String.valueOf(idGenerated);
//                                        }
//                                        else {
//                                            break;
//                                        }
//                                    }
//                                    u.setId(finalId);
//                                    //ids.add(finalId);
//                                    Toast.makeText(RegisterActivity.this, ids.get(index), Toast.LENGTH_SHORT).show();
//                                    counterMid++;
//
//                                }
//
//
//                            }
//                            else
//                            {
//
//                                Toast.makeText(RegisterActivity.this, "DB Full", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }




                    }
//                    else  if (positionStr.equals("Senior-Level")) {
//                        int min = 100;
//                        int max = 199;
//                        Random rng = new Random(); // Ideally just create one instance globally
//                        List<String> generated = new ArrayList<String>();
//                        for (int i = 0; i < lstUser.size(); i++)
//                        {
//                            while(true)
//                            {
//                                String nextt = String.valueOf(rng.nextInt(max - min + 1) + min);
//                                if (!generated.contains(next))
//                                {
//                                    // Done for this iteration
//                                    generated.add(nextt);
//                                    break;
//                                }
//                            }
//                        }
//
//                    }

//        class SelectedRandomNumberBetweenRangeNoDuplicate {
//
//            public void main(String[] args) {
//
//                //Select Eight random number without duplicate between 0 and 50
//                ArrayList list = getRandomNonRepeatingIntegers(lstUser.size(), min, max);
//                for (int i = 0; i < list.size(); i++) {
//                    userId = String.valueOf(list.get(i));
//                }
//            }
//
//            //Get selected size number without duplicate
//            public ArrayList getRandomNonRepeatingIntegers(int size, int min, int max)
//            {
//
//
//            }
//        }




//        for(int i=0;i<lstUser.size();++i)
//        {
//            if (positionStr.equals("Entry-Level")) {
//                userId = String.valueOf(entryLevel++);
//
//            } else if (positionStr.equals("Mid-Level")) {
//                userId = String.valueOf(midLevel++);
//
//            } else if (positionStr.equals("Senior-Level")) {
//                userId = String.valueOf(seniorLevel++);
//
//            }
//        }


        //u.setId();
        u.setFirstName(firstName.getText().toString());
        u.setLastName(lastName.getText().toString());
        u.setEmail(email.getText().toString());
        u.setPhoneNumber(phoneNumber.getText().toString());
        u.setAddress(address.getText().toString());
        u.setUsername(username.getText().toString());
        u.setPassword(password.getText().toString());
        u.setPosition(position.getSelectedItem().toString());


        if (!firstName.getText().toString().equals("") && !lastName.getText().toString().equals("") && !email.getText().toString().equals("") && !phoneNumber.getText().toString().equals("") &&
                !address.getText().toString().equals("") && !username.getText().toString().equals("") && !password.getText().toString().equals("") && !confirmPassword.getText().toString().equals("")) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                if (!checkUser(u)) {
                        db.insertUser(u);
                        //Toast.makeText(getApplicationContext(), "User has been added!", Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                        i.putExtra("username",username.getText().toString());
//                        i.putExtra("password",password.getText().toString());
//                        startActivity(i);
                        finish();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Both passwords must match!", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_LONG).show();
        }
    }

    public void updateDatabase (int value){
        database.execSQL("UPDATE counter SET value = '"+ value +"' WHERE id = '"+1+"'");
    }
    public void insertDatabase (){
        database.execSQL("INSERT INTO counter VALUES (1, 0)");
    }
    public int getValueDatabase(){
       Cursor c  =  database.rawQuery("SELECT value FROM counter WHERE id = '"+1+"'",null);
       if (c.moveToFirst()){
           int i  = c.getInt(0);
           return i;
       }

       return -1;
    }

}
