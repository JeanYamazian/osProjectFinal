package com.example.osprojectfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class SQLDBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "MY_DATABASE";
    private static final int DATABASE_VERSION = 1;

    private final static String TBL_USERS = "USERS_TBL";
    private final static String COLUMN_USER_ID = "USER_ID";
    private final static String COLUMN_USERNAME = "USERNAME";
    private final static String COLUMN_FIRST_NAME = "FIRST_NAME";
    private final static String COLUMN_LAST_NAME = "LAST_NAME";
    private final static String COLUMN_LEVEL_PRIORITY = "LEVEL_PRIORITY";
    private final static String COLUMN_PASSWORD = "PASSWORD";
    private final static String COLUMN_EMAIL = "EMAIL_ADDRESS";
    private final static String COLUMN_ADDRESS = "ADDRESS_LOCATION";
    private final static String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
    private final static String COLUMN_POSITION = "POSITION";

    private final static String TBL_PROCESS = "PROCESS_TBL";
    private final static String COLUMN_PROCESS_ID = "PROCESS_ID";
    private final static String COLUMN_PRIORITY = "PRIORITY";
    private final static String COLUMN_STATE= "STATE";
    private final static String COLUMN_IO_INFORMATION = "IO_INFORMATION";


    private final static String TBL_ME = "ME_TBL";
    private final static String COLUMN_ME = "ME";

    private final static String TBL_CURRENTLY_RUNNING = "CURRENTLY_RUNNING_TBL";
    private final static String COLUMN_PROCESS_ID_2 = "PROCESS_ID";
    private final static String COLUMN_PRIORITY_2 = "PRIORITY";
    private final static String COLUMN_STATE_2 = "STATE";
    private final static String COLUMN_IO_INFORMATION_2 = "IO_INFORMATION";


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

        final String SQL_CREATE_TABLE_USER = " CREATE TABLE " + TBL_USERS + " ( " + COLUMN_USER_ID + " INTEGER PRIMARY KEY, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_FIRST_NAME + " TEXT, " + COLUMN_LAST_NAME + " TEXT, " +  COLUMN_EMAIL + " TEXT, " + COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " + COLUMN_LEVEL_PRIORITY + " TEXT, "  + COLUMN_POSITION + " TEXT " +" ) ";

        final String SQL_CREATE_TABLE_PROCESS = " CREATE TABLE " + TBL_PROCESS + " ( " + COLUMN_PROCESS_ID + " TEXT PRIMARY KEY, "
                + COLUMN_PRIORITY + " TEXT, " + COLUMN_STATE + " TEXT, " + COLUMN_IO_INFORMATION + " TEXT " + " ) ";

        final String SQL_CREATE_TABLE_ME = " CREATE TABLE " + TBL_ME + " ( " + COLUMN_ME + " INTEGER PRIMARY KEY " + " ) ";

        final String SQL_CREATE_TABLE_CURRENTLY_RUNNING = " CREATE TABLE " + TBL_CURRENTLY_RUNNING + " ( " + COLUMN_PROCESS_ID_2 + " TEXT PRIMARY KEY, "
                + COLUMN_PRIORITY_2 + " TEXT, " + COLUMN_STATE_2 + " TEXT, " + COLUMN_IO_INFORMATION_2 + " TEXT " + " ) ";


        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_PROCESS);
        db.execSQL(SQL_CREATE_TABLE_ME);
        db.execSQL(SQL_CREATE_TABLE_CURRENTLY_RUNNING);
    }

    public String getCurrentProcessId (){
        String i = null;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_CURRENTLY_RUNNING, null);
        if (cursor.moveToFirst()){
            i = String.valueOf(cursor.getString(0));
            return i;
        }
        return i;

    }
    public void updateCurrent (Process p, String newId, String newPriority, String newState, String newIoInfo){
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long NoOfRows = DatabaseUtils.queryNumEntries(db,TBL_CURRENTLY_RUNNING);


        String oldId = "";

        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_CURRENTLY_RUNNING, null);
        if (cursor.moveToFirst()){
            oldId = String.valueOf(cursor.getString(0));
        }

        values.put(COLUMN_STATE,newState);
        values.put(COLUMN_IO_INFORMATION,newIoInfo);
        values.put(COLUMN_PRIORITY,newPriority);
        values.put(COLUMN_PROCESS_ID,newId);
        String[] whereArgs = {oldId};

        if (NoOfRows==0){
            db.insert(TBL_CURRENTLY_RUNNING,null, values);
        }
        else {
            db.update(TBL_CURRENTLY_RUNNING, values, COLUMN_PROCESS_ID + "= ?", whereArgs);
        }
    }
    public String getMeValue(){
        String i = "";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_ME, null);
        if (cursor.moveToFirst()){
            i = String.valueOf(cursor.getString(0));
        }
        return i;
    }
    public void updateMe (String newNumber, String oldNumber){
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ME,newNumber);
        String[] whereArgs = {oldNumber};
        db.update(TBL_ME, values, COLUMN_ME + "= ?",whereArgs);
    }
    public void insertMe(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long NoOfRows = DatabaseUtils.queryNumEntries(db,TBL_ME);
        cv.put(COLUMN_ME, 0);

        if(NoOfRows>0){
            return;
        }
        else {
            db.insert(TBL_ME,null,cv);
        }
    }
    public long insertUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_ID,u.getId());
        cv.put(COLUMN_USERNAME,u.getUsername());
        cv.put(COLUMN_FIRST_NAME,u.getFirstName());
        cv.put(COLUMN_LAST_NAME,u.getLastName());
        cv.put(COLUMN_LEVEL_PRIORITY,u.getPriorityLevel());
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

    public void updateProcess(Process p, String newId, String newPriority, String newState, String newIoInformation)
    {
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STATE,newState);
        values.put(COLUMN_IO_INFORMATION,newIoInformation);
        values.put(COLUMN_PRIORITY,newPriority);
        values.put(COLUMN_PROCESS_ID,newId);
        String[] whereArgs = {p.getId()};
        db.update(TBL_PROCESS, values, COLUMN_PROCESS_ID + "= ?",whereArgs);
    }

    public boolean deleteProcess(String processID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TBL_PROCESS, "PROCESS_ID=?", new String[]{processID}) > 0;

    }

    public void deleteCurrenlyRunning (){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBL_CURRENTLY_RUNNING, null, null);
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
                u.setPriorityLevel(cursor.getString((int)cursor.getColumnIndex(COLUMN_LEVEL_PRIORITY)));
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

