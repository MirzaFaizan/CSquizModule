package com.developmethis.csguide.csquizmodule;

/**
 * Created by Faizan Ejaz on 1/22/2018.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "sample.sqlite";
    public static final String TABLE_QUEST = "quest";
    public static final String KEY_QUIZ_ID = "q_id";
    private final static String DB_PATH = "/data/data/package com.developmethis.csguide/databases/";

    String dbName;
    Context context;

    File dbFile;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        this.dbName = DATABASE_NAME;
        dbFile= new File(DB_PATH + DATABASE_NAME);
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {

        if(!dbFile.exists()){
            SQLiteDatabase db = super.getWritableDatabase();
            copyDataBase(db.getPath());
        }
        return super.getWritableDatabase();
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        if(!dbFile.exists()){
            SQLiteDatabase db = super.getReadableDatabase();
            copyDataBase(db.getPath());
        }
        return super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    private void copyDataBase(String dbPath){
        try{
            InputStream assestDB = context.getAssets().open("databases/"+dbName);

            OutputStream appDB = new FileOutputStream(dbPath,false);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = assestDB.read(buffer)) > 0) {
                appDB.write(buffer, 0, length);
            }

            appDB.flush();
            appDB.close();
            assestDB.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public List<question> getAllQuestions(String quizID) {
        SQLiteDatabase dbase;
        List<question> quesList = new ArrayList<question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST+" WHERE "+KEY_QUIZ_ID+"='"+quizID+"'";
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                question quest = new question();
                quest.setID(cursor.getInt(cursor.getColumnIndex("id")));
                quest.setQUIZ_ID(cursor.getString(cursor.getColumnIndex("q_id")));
                quest.setQUESTION(cursor.getString(cursor.getColumnIndex("question")));
                quest.setANSWER(cursor.getString(cursor.getColumnIndex("answer")));
                quest.setOPTA(cursor.getString(cursor.getColumnIndex("opta")));
                quest.setOPTB(cursor.getString(cursor.getColumnIndex("optb")));
                quest.setOPTC(cursor.getString(cursor.getColumnIndex("optc")));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
}


//public class DbHelper extends SQLiteOpenHelper {
//    public static final String DBLOCATION = "/data/data/package com.developmethis.csguide/databases/";
//    private Context mContext;
//    private static final int DATABASE_VERSION = 1;
//    // Database Name
//    public static final String DATABASE_NAME = "sample.sqlite";
//    public static final String TABLE_QUEST = "quest";
//    public static final String KEY_QUIZ_ID = "q_id";
//    private SQLiteDatabase dbase;
//    String mypath;


//    public DbHelper(Context context) {
//        super(context,DATABASE_NAME,null,1);
//        this.mContext=context;
//        boolean dbexist = checkdatabase();
//        if (dbexist) {
//            System.out.println("Database exists");
//            opendatabase();
//        } else {
//            System.out.println("Database doesn't exist");
//            createdatabase();
//        }
//    }
//
//    public void createdatabase()  {
//        boolean dbexist = checkdatabase();
//        if(dbexist) {
//            System.out.println(" Database exists.");
//        } else {
//            this.getReadableDatabase();
//            try {
//                copydatabase();
//            } catch(IOException e) {
//                throw new Error("Error copying database");
//            }
//        }
//    }
//
//    private boolean checkdatabase() {
//
//        boolean checkdb = false;
//        try {
//            String myPath = DBLOCATION + DATABASE_NAME;
//            File dbfile = new File(myPath);
//            checkdb = dbfile.exists();
//        } catch(SQLiteException e) {
//            System.out.println("Database doesn't exist");
//        }
//        return checkdb;
//    }
//
//    private void copydatabase() throws IOException {
//        //Open your local db as the input stream
//        InputStream myinput = mContext.getAssets().open(DATABASE_NAME);
//
//        // Path to the just created empty db
//        String outfilename = DBLOCATION + DATABASE_NAME;
//
//        //Open the empty db as the output stream
//        OutputStream myoutput = new FileOutputStream(outfilename);
//
//        // transfer byte to inputfile to outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myinput.read(buffer))>0) {
//            myoutput.write(buffer,0,length);
//        }
//
//        //Close the streams
//        myoutput.flush();
//        myoutput.close();
//        myinput.close();
//    }
//
//    public void opendatabase() {
//        //Open the database
//        String mypath = DBLOCATION+ DATABASE_NAME;
//        dbase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
//    }
//
//    public synchronized void close() {
//        if(dbase != null) {
//            dbase.close();
//        }
//        super.close();
//    }
//    //initializers
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//    //open database
//    public void openDatabase() {
//        if (dbase != null && dbase.isOpen()) {
//            return;
//        }
//        dbase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
//    }
//
//    public List<question> getAllQuestions(String quizID) {
//        opendatabase();
//        List<question> quesList = new ArrayList<question>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_QUEST+" WHERE "+KEY_QUIZ_ID+"='"+quizID+"'";
//        dbase=this.getReadableDatabase();
//        Cursor cursor = dbase.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                question quest = new question();
//                quest.setID(cursor.getInt(cursor.getColumnIndex("id")));
//                quest.setQUIZ_ID(cursor.getString(cursor.getColumnIndex("q_id")));
//                quest.setQUESTION(cursor.getString(cursor.getColumnIndex("question")));
//                quest.setANSWER(cursor.getString(cursor.getColumnIndex("answer")));
//                quest.setOPTA(cursor.getString(cursor.getColumnIndex("opta")));
//                quest.setOPTB(cursor.getString(cursor.getColumnIndex("optb")));
//                quest.setOPTC(cursor.getString(cursor.getColumnIndex("optc")));
//                quesList.add(quest);
//            } while (cursor.moveToNext());
//        }
//        // return quest list
//        return quesList;
//    }
//    public int rowcount()
//    {
//        int row=0;
//        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        row=cursor.getCount();
//        return row;
//    }
//}
