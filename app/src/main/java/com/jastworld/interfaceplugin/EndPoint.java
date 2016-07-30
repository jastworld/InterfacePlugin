package com.jastworld.interfaceplugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EndPoint extends AppCompatActivity {
    Controller control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        control= new Controller();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_point);
        Toast.makeText(EndPoint.this,
                control.getStartPoint(), Toast.LENGTH_SHORT).show();
    }
}
