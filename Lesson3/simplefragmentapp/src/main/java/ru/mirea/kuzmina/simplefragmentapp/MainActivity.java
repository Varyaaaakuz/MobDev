package ru.mirea.kuzmina.simplefragmentapp;
import android.view.View;
import android.os.Bundle;
import android.content.res.Configuration;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
public class MainActivity extends AppCompatActivity {
    private Fragment fragment1, fragment2;
    private boolean isLandscape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerLeft, fragment1)
                    .replace(R.id.fragmentContainerRight, fragment2)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment1)
                    .commit();

            setupPortraitButtons();
        }
    }
    private void setupPortraitButtons() {
        Button btnFirst = findViewById(R.id.btnFirstFragment);
        Button btnSecond = findViewById(R.id.btnSecondFragment);

        btnFirst.setOnClickListener(v -> showFragment(fragment1));
        btnSecond.setOnClickListener(v -> showFragment(fragment2));
    }
    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();
    }
}
