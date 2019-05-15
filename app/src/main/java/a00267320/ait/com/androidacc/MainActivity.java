package a00267320.ait.com.androidacc;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    long changeTimeAcc = System.currentTimeMillis();
    long changeTimeGyro = System.currentTimeMillis();
    private float lastX, lastY, lastZ;
    private TextView textXGyro, textYGyro, textZGyro, textXAcc, textYAcc, textZAcc, maxX, maxY, maxZ;

    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    Map<Date, Map<String, Double>> sensorValueMap = new HashMap<>();

    private SensorManager sensorManager;
    private SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor sensor = event.sensor;
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                if (System.currentTimeMillis() - changeTimeAcc > 500) {
                    //displayCleanValues();
                    displayCurrentValues(event);
                    displayMaxValues();
                    changeTimeAcc = System.currentTimeMillis();
                }
                // get the change of the x,y,z values of the accelerometer
                deltaX = lastX - event.values[0];
                deltaY = lastY - event.values[1];
                deltaZ = lastZ - event.values[2];
                lastX = event.values[0];
                lastY = event.values[1];
                lastZ = event.values[2];
                Map<String, Double> accData = new HashMap<>();
                accData.put(Constants.X_COOR, (double) deltaX);
                accData.put(Constants.Y_COOR, (double) deltaY);
                accData.put(Constants.Z_COOR, (double) deltaZ);
                sensorValueMap.put(new Date(), accData);
            } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                if (System.currentTimeMillis() - changeTimeGyro > 500) {
                    textXGyro.setText("X : " + String.format("%.4f", x) + " rad/s");
                    textYGyro.setText("Y : " + String.format("%.4f", y) + " rad/s");
                    textZGyro.setText("Z : " + String.format("%.4f", z) + " rad/s");
                    changeTimeGyro = System.currentTimeMillis();
                }
                Map<String, Double> gyroData = new HashMap<>();
                gyroData.put(Constants.X_AXIS, (double) x);
                gyroData.put(Constants.Y_AXIS, (double) y);
                gyroData.put(Constants.Z_AXIS, (double) z);
                sensorValueMap.put(new Date(), gyroData);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        startTimer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        registerListeners();
    }

    private void registerListeners() {
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void initializeViews() {
        textXGyro = findViewById(R.id.currentXAxis);
        textYGyro = findViewById(R.id.currentYAxis);
        textZGyro = findViewById(R.id.currentZAxis);
        textXAcc = findViewById(R.id.currentX);
        textYAcc = findViewById(R.id.currentY);
        textZAcc = findViewById(R.id.currentZ);
        maxX = findViewById(R.id.maxX);
        maxY = findViewById(R.id.maxY);
        maxZ = findViewById(R.id.maxZ);
    }

    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        registerListeners();
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }


    public void displayCleanValues() {
        textXAcc.setText("0.0");
        textYAcc.setText("0.0");
        textZAcc.setText("0.0");
    }

    // display the current x,y,z accelerometer values
    public void displayCurrentValues(SensorEvent event) {
        textXAcc.setText("" + deltaX);
        textYAcc.setText("" + deltaY);
        textZAcc.setText("" + deltaZ);
    }

    // display the max x,y,z accelerometer values
    public void displayMaxValues() {
        if (deltaX > deltaXMax) {
            deltaXMax = deltaX;
            maxX.setText(Float.toString(deltaXMax));
        }
        if (deltaY > deltaYMax) {
            deltaYMax = deltaY;
            maxY.setText(Float.toString(deltaYMax));
        }
        if (deltaZ > deltaZMax) {
            deltaZMax = deltaZ;
            maxZ.setText(Float.toString(deltaZMax));
        }
    }

    private void startTimer() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                saveSensorValuesInDB();
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 3000L;
        long period = 3000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }


    private void saveSensorValuesInDB() {
        List<SensorData> sensorDataList = new ArrayList<>();
        for (Map.Entry<Date, Map<String, Double>> sensorEntry : sensorValueMap.entrySet()) {
            Map<String, Double> sensorValues = sensorEntry.getValue();
            SensorData sensorData = new SensorData();
            sensorData.setCreateTime(sensorEntry.getKey());
            sensorData.setxCoor(sensorValues.get(Constants.X_COOR));
            sensorData.setyCoor(sensorValues.get(Constants.Y_COOR));
            sensorData.setzCoor(sensorValues.get(Constants.Z_COOR));
            sensorData.setxAxis(sensorValues.get(Constants.X_AXIS));
            sensorData.setyAxis(sensorValues.get(Constants.Y_AXIS));
            sensorData.setzAxis(sensorValues.get(Constants.Z_AXIS));
            sensorDataList.add(sensorData);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
        if (!sensorDataList.isEmpty()) {
            new PostTask().execute(gson.toJson(sensorDataList));
        }
        sensorValueMap.clear();
    }
}