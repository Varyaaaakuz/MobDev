package ru.mirea.kuzmina.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private EditText editTextFileName, editTextQuote;
    private static final String APP_TAG = "FileStorageApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextText);
        editTextQuote = findViewById(R.id.editTextText2);

        Button saveButton = findViewById(R.id.button);
        Button loadButton = findViewById(R.id.button2);

        saveButton.setOnClickListener(v -> performFileSave());
        loadButton.setOnClickListener(v -> performFileLoad());
    }

    private void performFileSave() {
        if (!checkStorageAvailability(true)) {
            showMessage("Невозможно сохранить: хранилище недоступно");
            return;
        }

        String fileName = editTextFileName.getText().toString();
        String text = editTextQuote.getText().toString();

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);

        try {
            path.mkdirs();
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(text.getBytes(StandardCharsets.UTF_8));
            stream.close();
            showMessage("Данные успешно сохранены");
        } catch (Exception e) {
            Log.e(APP_TAG, "Ошибка при сохранении: " + e.getMessage());
            showMessage("Ошибка при сохранении данных");
        }
    }

    private void performFileLoad() {
        if (!checkStorageAvailability(false)) {
            showMessage("Невозможно загрузить: хранилище недоступно");
            return;
        }

        String fileName = editTextFileName.getText().toString();
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);

        try {
            FileInputStream stream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8));
            StringBuilder content = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                content.append(line).append("\n");
                line = reader.readLine();
            }
            stream.close();
            editTextQuote.setText(content.toString().trim());
            showMessage("Данные успешно загружены");
        } catch (Exception e) {
            Log.e(APP_TAG, "Ошибка при загрузке: " + e.getMessage());
            showMessage("Ошибка при загрузке данных");
        }
    }

    private boolean checkStorageAvailability(boolean requireWriteAccess) {
        String state = Environment.getExternalStorageState();
        return requireWriteAccess ?
                Environment.MEDIA_MOUNTED.equals(state) :
                Environment.MEDIA_MOUNTED.equals(state) ||
                        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}