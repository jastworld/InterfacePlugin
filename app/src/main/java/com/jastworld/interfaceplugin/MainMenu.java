package com.jastworld.interfaceplugin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button endPointButton;
    Button startPointButton;
    Controller control;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        endPointButton = (Button) findViewById(R.id.endPoint);//Gives the end button a declaration and tags it to end Point
        startPointButton = (Button) findViewById(R.id.startPoint);//Gives the end button a declaration and tags it to start Point
        control =  new Controller();


        //The End Point Button onClick Event
        endPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Uses Lambda to create the new intent
                Intent intent=new Intent(MainMenu.this, EndPoint.class);//choose the current place and destination for the ontent
                startActivity(intent);//executes the button intent
            }
        });



        //The Start Point Button onClick Event
        startPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Uses Lambda to create the new intent
                Intent intent=new Intent(MainMenu.this, FloorSelect.class);//choose the current place and destination for the content
                startActivity(intent);//executes the button intent
            }
        });



        displayMap();


    }

    public void mapSelect(View view){
        this.view = view;
        startActivity(new Intent(MainMenu.this,MapSelect.class));

    }
    public  void displayMap(){
        String from = control.getMapSelect();
        Toast.makeText(MainMenu.this,
                from, Toast.LENGTH_LONG).show();

    }
}
