package ru.mirea.kuzmina.intentapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.kuzmina.intentapp.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView resultTextView = findViewById(R.id.textView4);
        String currentTime = getIntent().getStringExtra("CURRENT_TIME");
        int studentNumber = getIntent().getIntExtra("NUMBER", 0);
        int square = studentNumber * studentNumber;

        String resultText = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ "
                + square + ", а текущее время " + currentTime;

        resultTextView.setText(resultText);
    }
}