package ru.mirea.kuzmina.lesson6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText group, listNumber, film;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        group = findViewById(R.id.editTextText);
        listNumber = findViewById(R.id.editTextText2);
        film = findViewById(R.id.editTextText3);
        Button saveButton = findViewById(R.id.button);

        prefs = getSharedPreferences("task_1", MODE_PRIVATE);

        loadPrefs();

        saveButton.setOnClickListener(v -> savePrefs());
    }

    private void loadPrefs() {
        group.setText(prefs.getString("group", ""));
        listNumber.setText(prefs.getString("listNumber", ""));
        film.setText(prefs.getString("film", ""));
    }

    private void savePrefs() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("group", group.getText().toString());
        editor.putString("listNumber", listNumber.getText().toString());
        editor.putString("film", film.getText().toString());
        editor.apply();
    }
}