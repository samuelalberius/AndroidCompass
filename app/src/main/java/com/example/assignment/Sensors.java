package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Sensors extends AppCompatActivity implements SensorEventListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        textView = findViewById(R.id.accelerometers);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Sensors.this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event != null) {
            String updatedValues = stringCreator(event);
            textView.setText(updatedValues);
            ConstraintLayout root = findViewById(R.id.root);
            int color = Color.argb(255, rgb(Math.abs(event.values[2])), rgb(Math.abs(event.values[0])), rgb(Math.abs(event.values[1])));
            root.setBackgroundColor(color);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    private int rgb(float numb) { return Math.round(numb * 25); }

    private String stringCreator(SensorEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("x axis: ").append(Math.round(event.values[0] * 1000) / 1000).append("m/s2\n");
        sb.append("y axis: ").append(Math.round(event.values[1] * 1000) / 1000).append("m/s2\n");
        sb.append("z axis: ").append(Math.round(event.values[2] * 1000) / 1000).append("m/s2\n");
        return sb.toString();
    }
}