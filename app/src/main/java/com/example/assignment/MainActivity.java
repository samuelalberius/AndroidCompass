package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compassButton(View view) {
        /*
        Intent intent = new Intent(this, Compass.class);
        startActivity(intent);
         */
        Intent intent = new Intent(this, Compass.class);
        startActivity(intent);
    }

    public void sensorButton(View view) {
        Intent intent = new Intent(this, Sensors.class);
        startActivity(intent);
    }
}