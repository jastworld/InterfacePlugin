

package com.jastworld.interfaceplugin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MapSelect extends AppCompatActivity {

    /*
    DataBaseHelper myDataBase;
    Controller control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select_layout);
        control= new Controller();

        openDB();
        populateListView();


    }

    //Method to populate the list from the DB
    private void populateListView(){
        Cursor cursor = myDataBase.getAllRows(); //Looks at the getAll Row function in Database helper
        String[] rooms = new String[]{ myDataBase.ROW_BUILDING};    //Provides an array of required field
        int[] rowLayoutIDs = new int[]{ R.id.mapList};  //Provides an array of row layout ID to match field
        SimpleCursorAdapter simpleCursorAdapter;        //Creates an instance of Simple Cursor
        simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.map_select_row,cursor, rooms,rowLayoutIDs,0);//sets up the Simple cursor adapter with context, predefined layout, cursor used and arrays of room and numbers
        ListView listView = (ListView) findViewById(R.id.mapListView);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView text = ((TextView) view.findViewById(R.id.mapList));
                String from = text.getText().toString();
                Toast.makeText(MapSelect.this,
                        from, Toast.LENGTH_SHORT).show();
                // your Code here
                control.setMapSelect(from);
                startActivity(new Intent(MapSelect.this,MainMenu.class));
            }

        });
    }

    private void openDB(){
        myDataBase = new DataBaseHelper(MapSelect.this);
        try {
            myDataBase.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        myDataBase.openDataBase();

    }
}*/
    DataBaseHelper myDataBase;
    Controller control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_select);

        openDB();
        control= new Controller();
        populateListView();


    }
    //Method to populate the list from the DB
    private void populateListView(){
        Cursor cursor = myDataBase.getUniqueMaps(); //Looks at the getAll Row function in Database helper
        String[] rooms = new String[]{ myDataBase.ROW_BUILDING};    //Provides an array of required field
        int[] rowLayoutIDs = new int[]{ R.id.mapList};  //Provides an array of row layout ID to match field
        SimpleCursorAdapter simpleCursorAdapter;        //Creates an instance of Simple Cursor
        simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.map_select_row,cursor, rooms,rowLayoutIDs,0);//sets up the Simple cursor adapter with context, predefined layout, cursor used and arrays of room and numbers
        ListView listView = (ListView) findViewById(R.id.mapListView);
//        ListView listView = (ListView) findViewById(R.id.mapListView);

        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView text = ((TextView) view.findViewById(R.id.mapList));
                String from = text.getText().toString();
                Toast.makeText(MapSelect.this,
                        from, Toast.LENGTH_SHORT).show();
                // your Code here
                control.setMapSelect(from);
                startActivity(new Intent(MapSelect.this,MainMenu.class));

            }

        });
    }

    private void openDB(){
        myDataBase = new DataBaseHelper(MapSelect.this);
        try {
            myDataBase.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        myDataBase.openDataBase();

    }
}
