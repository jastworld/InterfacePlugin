package com.jastworld.interfaceplugin;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Tayo on 7/27/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    Controller control;
    String DB_PATH = null;
    private static String DB_NAME= "LocationInformation.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    public static final String ROW_ID="_id";
    public static final String ROW_BUILDING="Building";
    public static final String ROW_FLOOR="Floor";
    public static final String ROW_ROOM="Room";
    public static final String ROW_DESC="Description";
    public static final String[] ALL_ROWS = new String[]{ROW_ID,ROW_BUILDING,ROW_FLOOR,ROW_ROOM,ROW_DESC};
    public static final String[] FLOOR_ROWS = new String[]{ROW_ID,ROW_FLOOR};

    public DataBaseHelper(Context context){

        super(context,DB_NAME, null, 2);
        this.myContext=context;
        this.DB_PATH = "/data/data/"+context.getPackageName()+"/databases/";
        Log.e("Path 1", DB_PATH);
        control = new Controller();


    }
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query("LocationData", null, null, null, null, null, null);
    }
    public Cursor getAllRows(){
        String where=null;
        Cursor c = myDataBase.query(true,"LocationData", ALL_ROWS, where,null, null, null, null,null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;

    }

    public Cursor getUniqueMaps(){
        String where = null;
        Cursor c = myDataBase.query(true, "LocationData",ALL_ROWS, where,null, ROW_BUILDING, null, null,null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }
    public Cursor getUniqueFloors(){
        String building = control.getMapSelect();
        String buildingChoice= "\""+building+"\"";
        Log.e("ASDF",buildingChoice);
        String where = null;
        String floorQuery = "SELECT DISTINCT * FROM LocationData where Building ="// \"BMCC Main Building\"";
                           +buildingChoice
                            +"GROUP BY Floor";
        Cursor c = myDataBase.rawQuery(floorQuery, null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getUniqueRooms(){
        String building = control.getMapSelect();
        String buildingChoice= "\""+building+"\"";
        String floorChoice = control.getFloorSelect();
        Log.e("ASDF",buildingChoice);
        String where = null;
        String roomQuery = "SELECT DISTINCT * FROM LocationData where Building ="// \"BMCC Main Building\"";
                +buildingChoice
                +" AND Floor = "
                +floorChoice
                +" GROUP BY Room";
        Cursor c = myDataBase.rawQuery(roomQuery, null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }
}
