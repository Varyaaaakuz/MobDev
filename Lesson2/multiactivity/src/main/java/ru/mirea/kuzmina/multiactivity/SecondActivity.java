package ru.mirea.kuzmina.multiactivity;

import android.os.Bundle;
import android.util.Log;
import com.google.android.material.snackbar.Snackbar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ru.mirea.kuzmina.multiactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_second);

        String text = (String) getIntent().getSerializableExtra("key");
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState()");
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

}