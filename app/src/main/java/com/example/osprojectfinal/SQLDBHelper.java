package com.example.osprojectfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class SQLDBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "MY_DATABASE";
    private static final int DATABASE_VERSION = 1;

    private final static String TBL_USERS = "TBL_USERS";
    private final static String COLUMN_USER_ID = "USER_ID";
    private final static String COLUMN_USERNAME = "USER_USERNAME";
    private final static String COLUMN_FIRST_NAME = "USER_FIRST_NAME";
    private final static String COLUMN_LAST_NAME = "USER_LAST_NAME";
    private final static String COLUMN_USER_PRIORITY = "USER_PRIORITY";
    private final static String COLUMN_PASSWORD = "USER_PASSWORD";
    private final static String COLUMN_EMAIL = "USER_EMAIL";
    private final static String COLUMN_ADDRESS = "USER_ADDRESS";
    private final static String COLUMN_PHONE_NUMBER = "USER_PHONE_NUMBER";
    private final static String COLUMN_POSITION = "USER_POSITION";

    private final static String TBL_PROCESS = "TBL_PROCESS";
    private final static String COLUMN_PROCESS_ID = "COLUMN_PROCESS_ID";
    private final static String COLUMN_PRIORITY = "COLUMN_PRIORITY";
    private final static String COLUMN_STATE= "COLUMN_STATE";
    private final static String COLUMN_IO_INFORMATION = "COLUMN_IO_INFORMATION";

    private SQLiteDatabase db;
    private static SQLDBHelper instance;

    public SQLDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    public static synchronized SQLDBHelper getInstance(Context context) {
        if(instance == null)
        {
            instance = new SQLDBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_TABLE_USER = " CREATE TABLE " + TBL_USERS + " ( " + COLUMN_USER_ID + " TEXT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_FIRST_NAME + " TEXT, " + COLUMN_LAST_NAME + " TEXT, " + COLUMN_USER_PRIORITY + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " + COLUMN_POSITION + " TEXT, " + COLUMN_PASSWORD + " TEXT " + " ) ";

        final String SQL_CREATE_TABLE_PROCESS = " CREATE TABLE " + TBL_PROCESS + " ( " + COLUMN_PROCESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRIORITY + " TEXT, " + COLUMN_STATE + " TEXT, " + COLUMN_IO_INFORMATION + " TEXT " + " ) ";

        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_PROCESS);
    }

    public long insertUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_ID,u.getId());
        cv.put(COLUMN_USERNAME,u.getUsername());
        cv.put(COLUMN_FIRST_NAME,u.getFirstName());
        cv.put(COLUMN_LAST_NAME,u.getLastName());
        cv.put(COLUMN_USER_PRIORITY,u.getPriorityLevel());
        cv.put(COLUMN_EMAIL,u.getEmail());
        cv.put(COLUMN_PHONE_NUMBER,u.getPhoneNumber());
        cv.put(COLUMN_ADDRESS,u.getAddress());
        cv.put(COLUMN_POSITION,u.getPosition());
        cv.put(COLUMN_PASSWORD,u.getPassword());

        long id = db.insert(TBL_USERS,null,cv);

        return id;
    }


    public long insertProcess(Process p)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PROCESS_ID,p.getId());
        cv.put(COLUMN_PRIORITY,p.getPriority());
        cv.put(COLUMN_STATE,p.getState());
        cv.put(COLUMN_IO_INFORMATION,p.getIoInformation());


        long id = db.insert(TBL_PROCESS,null,cv);

        return id;
    }

    public int updatePassword(String oldPass, String newPass)
    {
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD,newPass);
        String[] whereArgs = {oldPass};
        int update = db.update(TBL_USERS, values, COLUMN_PASSWORD + "= ?",whereArgs);

        return update;
    }

    public int updateProcess(String oldState, String newState,String oldIO, String newIO)
    {
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATE,newState);
        values.put(COLUMN_IO_INFORMATION,newIO);
        String[] whereArgs = {oldState,oldIO};
        int update = db.update(TBL_PROCESS, values, new String[]{COLUMN_STATE, COLUMN_IO_INFORMATION} + "= ?",whereArgs);

        return update;
    }

    public void deleteProcess(String processID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBL_PROCESS, "name=?", new String[]{processID});
        db.close();
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_USERS, null);

        if (cursor.moveToFirst()) {
            do {
                User u = new User();
                u.setId(cursor.getString((int)cursor.getColumnIndex(COLUMN_USER_ID)));
                u.setFirstName(cursor.getString((int)cursor.getColumnIndex(COLUMN_FIRST_NAME)));
                u.setLastName(cursor.getString((int)cursor.getColumnIndex(COLUMN_LAST_NAME)));
                u.setPriorityLevel(cursor.getString((int)cursor.getColumnIndex(COLUMN_USER_PRIORITY)));
                u.setEmail(cursor.getString((int)cursor.getColumnIndex(COLUMN_EMAIL)));
                u.setAddress(cursor.getString((int)cursor.getColumnIndex(COLUMN_ADDRESS)));
                u.setPhoneNumber(cursor.getString((int)cursor.getColumnIndex(COLUMN_PHONE_NUMBER)));
                u.setPassword(cursor.getString((int)cursor.getColumnIndex(COLUMN_PASSWORD)));
                u.setPosition(cursor.getString((int)cursor.getColumnIndex(COLUMN_POSITION)));
                u.setUsername(cursor.getString((int)cursor.getColumnIndex(COLUMN_USERNAME)));

                usersList.add(u);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }
    public ArrayList<Process> getAllProcesses() {
        ArrayList<Process> processList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_PROCESS, null);

        if (cursor.moveToFirst()) {
            do {
                Process p = new Process();
                p.setId(cursor.getString( (int) cursor.getColumnIndex(COLUMN_PROCESS_ID)));
                p.setPriority(cursor.getString( (int) cursor.getColumnIndex(COLUMN_PRIORITY)));
                p.setState(cursor.getString((int)cursor.getColumnIndex(COLUMN_STATE)));
                p.setIoInformation(cursor.getString((int)cursor.getColumnIndex(COLUMN_IO_INFORMATION)));
                processList.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return processList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ TBL_USERS);
        db.execSQL( " DROP TABLE IF EXISTS " + TBL_PROCESS);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}

