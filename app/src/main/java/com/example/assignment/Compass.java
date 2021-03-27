package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Compass extends AppCompatActivity implements SensorEventListener {

    private TextView textView;
    private Sensor sensor;
    private float[] accelerometerReadings = new float[3];
    private float[] magnetometerReadings = new float[3];
    private final float[] rotationMatrix = new float[9];
    private final float[] orientationAngles = new float[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        // create SensorManager, accelerometer senor and magnetic filed sensor and tells This to listen
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor acc_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Compass.this, acc_sensor, SensorManager.SENSOR_DELAY_FASTEST);
        Sensor mag_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(Compass.this, mag_sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event != null) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerReadings = event.values;
            } else {
                magnetometerReadings = event.values;
            }
            updateOrientationAngles();
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do the nothing
    }

    private void updateOrientationAngles() {
        SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReadings, magnetometerReadings);
        float[] orientation = SensorManager.getOrientation(rotationMatrix, orientationAngles);
        double degrees = (Math.toDegrees(orientation[0] + 360) % 360);
        long angle = Math.round(degrees * 100) / 100;
        String direction = getDirections(angle);
        TextView heading = findViewById(R.id.heading);
        String string = "Heading " + direction + " " + angle + "°";
        heading.setText(string);
        ImageView circle = findViewById(R.id.circle);
        circle.setRotation(angle);
    }


    private String getDirections(double angle) {
        if (angle >= 350 || angle <= 10) {
            return "NORTH";
        } else if (angle < 350 && angle > 280) {
            return "NORTHWEST";
        } else if (angle <= 280 && angle > 260) {
            return "WEST";
        } else if (angle < 260 && angle > 190) {
            return "SOUTHWEST";
        } else if (angle <= 190 && angle > 170) {
            return "SOUTH";
        } else if (angle <= 170 && angle > 100) {
            return "SOUTHEAST";
        } else if (angle <= 100 && angle > 80) {
            return "EAST";
        } else {
            return "NORTHEAST";
        }
    }
}