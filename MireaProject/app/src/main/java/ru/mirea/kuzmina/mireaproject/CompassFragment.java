package ru.mirea.kuzmina.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class CompassFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private ImageView compassImage;
    private TextView directionText; // добавляем TextView для отображения направления

    private float[] gravityData = new float[3]; // Акселерометр
    private float[] geomagneticData = new float[3]; // Магнитометр

    private boolean gravityAvailable = false;
    private boolean magneticAvailable = false;

    private float currentDegree = 0f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);
        compassImage = view.findViewById(R.id.compass_image);
        directionText = view.findViewById(R.id.direction_text); // инициализация TextView
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (accelerometer != null && magnetometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, gravityData, 0, event.values.length);
            gravityAvailable = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, geomagneticData, 0, event.values.length);
            magneticAvailable = true;
        }

        if (gravityAvailable && magneticAvailable) {
            float[] R = new float[9];
            float[] I = new float[9];

            boolean success = SensorManager.getRotationMatrix(R, I, gravityData, geomagneticData);
            if (success) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimuthInRadians = orientation[0];
                float azimuthInDegrees = (float) Math.toDegrees(azimuthInRadians);
                if (azimuthInDegrees < 0) {
                    azimuthInDegrees += 360; // диапазон 0-360
                }

                // Вращение стрелки
                rotateCompass(azimuthInDegrees);

                // Обновление текста
                String directionStr = getDirectionString(azimuthInDegrees);
                directionText.setText(directionStr);
            }
        }
    }

    private void rotateCompass(float azimuth) {
        float rotation = -azimuth; // чтобы стрелка показывала в правильную сторону
        RotateAnimation ra = new RotateAnimation(
                currentDegree, rotation,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(200);
        ra.setFillAfter(true);
        compassImage.startAnimation(ra);
        currentDegree = rotation;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Можно оставить пустым
    }

    // Метод для получения строки направления и отклонения
    private String getDirectionString(float degrees) {
        String[] directions = {
                "север", "северо-восток", "восток", "юго-восток",
                "юг", "юго-запад", "запад", "северо-запад"
        };

        int index = (int)((degrees + 22.5) / 45) % 8;
        String direction = directions[index];

        // Расчет отклонения от севера
        float diff = degrees % 45;
        String deviationStr = String.format(Locale.getDefault(), "%.1f°", diff);

        // Можно дополнительно указывать, насколько отклонение от севера
        return String.format("Направление: %s, отклонение: %s", direction, deviationStr);
    }
}