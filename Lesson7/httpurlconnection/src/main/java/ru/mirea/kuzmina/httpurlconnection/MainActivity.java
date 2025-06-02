package ru.mirea.kuzmina.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "NetTask";

    private Button fetchButton;
    private TextView ipText, cityText, regionText, countryText, latText, lonText;
    private TextView tempText, windText, windDirText;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchButton = findViewById(R.id.button);
        ipText = findViewById(R.id.textView6);
        cityText = findViewById(R.id.textView);
        regionText = findViewById(R.id.textView2);
        countryText = findViewById(R.id.textView3);
        latText = findViewById(R.id.textView4);
        lonText = findViewById(R.id.textView5);
        tempText = findViewById(R.id.textView7);
        windText = findViewById(R.id.textView8);
        windDirText = findViewById(R.id.textView9);
        statusText = findViewById(R.id.textView13);
        fetchButton.setOnClickListener(v -> fetchData());
    }
    private void fetchData() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager != null ? connManager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnected()) {
            resetViews();
            new IpInfoTask().execute("https://ipinfo.io/json");
        } else {
            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }
    private void resetViews() {
        statusText.setText("");
        ipText.setText("IP: ");
        cityText.setText("Город: ");
        regionText.setText("Регион: ");
        countryText.setText("Страна: ");
        latText.setText("Широта: ");
        lonText.setText("Долгота: ");
        tempText.setText("Температура: ");
        windText.setText("Скорость ветра: ");
        windDirText.setText("Направление ветра: ");
    }
    private class IpInfoTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            statusText.setText("Загружаем IP...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return fetchUrl(urls[0]);
            } catch (IOException e) {
                Log.e(TAG, "Ошибка", e);
                return null;
            }
        }
        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                statusText.setText("Ошибка IP");
                return;
            }
            try {
                JSONObject json = new JSONObject(result);
                updateIpViews(json);
                fetchWeatherData(json.optString("loc"));
            } catch (JSONException e) {
                statusText.setText("Ошибка JSON");
            }
        }

        private void updateIpViews(JSONObject json) throws JSONException {
            ipText.setText("IP: " + json.optString("ip", "—"));
            cityText.setText("Город: " + json.optString("city", "—"));
            regionText.setText("Регион: " + json.optString("region", "—"));
            countryText.setText("Страна: " + json.optString("country", "—"));

            String[] coords = json.optString("loc", "—,—").split(",");
            latText.setText("Широта: " + (coords.length > 0 ? coords[0] : "—"));
            lonText.setText("Долгота: " + (coords.length > 1 ? coords[1] : "—"));
        }

        private void fetchWeatherData(String location) {
            if (location.contains(",")) {
                statusText.setText("Получаем погоду...");
                new WeatherTask().execute(location.split(","));
            }
        }
    }

    private class WeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            statusText.setText("Загрузка погоды...");
            tempText.setText("Температура: ");
            windText.setText("Скорость ветра: ");
            windDirText.setText("Направление ветра: ");
        }

        @Override
        protected String doInBackground(String... coords) {
            if (coords.length < 2) return null;

            try {
                String url = buildWeatherUrl(coords[0], coords[1]);
                Log.d(TAG, "Weather URL: " + url);
                return fetchUrl(url);
            } catch (Exception e) {
                Log.e(TAG, "Weather error", e);
                return null;
            }
        }

        private String buildWeatherUrl(String lat, String lon) throws Exception {
            return "https://api.open-meteo.com/v1/forecast" +
                    "?latitude=" + URLEncoder.encode(lat, "UTF-8") +
                    "&longitude=" + URLEncoder.encode(lon, "UTF-8") +
                    "&current_weather=true" +
                    "&timezone=" + URLEncoder.encode("Europe/Moscow", "UTF-8");
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                statusText.setText("Ошибка погоды");
                return;
            }

            try {
                JSONObject json = new JSONObject(result.trim());
                if (json.has("current_weather")) {
                    JSONObject weather = json.getJSONObject("current_weather");
                    updateWeatherViews(weather);
                    statusText.setText("Погода получена");
                } else {
                    statusText.setText("Нет данных");
                }
            } catch (JSONException e) {
                statusText.setText("Ошибка JSON");
            }
        }

        private void updateWeatherViews(JSONObject weather) throws JSONException {
            tempText.setText(String.format("Температура: %.1f°C", weather.optDouble("temperature", Double.NaN)));
            windText.setText(String.format("Скорость ветра: %.1f м/с", weather.optDouble("windspeed", Double.NaN)));
            windDirText.setText(String.format("Направление ветра: %.0f°", weather.optDouble("winddirection", Double.NaN)));
        }
    }

    private String fetchUrl(String url) throws IOException {
        HttpURLConnection connection = null;
        InputStream input = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                input = connection.getInputStream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                int b;
                while ((b = input.read()) != -1) {
                    output.write(b);
                }
                return output.toString("UTF-8");
            }
            return connection.getResponseMessage() + ". Code: " + connection.getResponseCode();
        } finally {
            if (input != null) input.close();
            if (connection != null) connection.disconnect();
        }
    }
}