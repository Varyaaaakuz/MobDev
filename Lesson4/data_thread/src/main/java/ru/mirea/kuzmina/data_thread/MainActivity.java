package ru.mirea.kuzmina.data_thread;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.concurrent.TimeUnit;
import ru.mirea.kuzmina.data_thread.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn1");
                Log.d("UI_TEST", "runn1 выполнен");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn2");
                Log.d("UI_TEST", "runn2 выполнен");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn3");
                Log.d("UI_TEST", "runn3 выполнен");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        binding.tvInfo.append("Последовательность выполнения:\n" +
                "1. runOnUiThread() - выполняется немедленно в UI-потоке\n" +
                "2. post() - добавляет Runnable в очередь сообщений UI-потока\n" +
                "3. postDelayed() - выполняет Runnable с указанной задержкой\n\n" +
                "Реальные результаты выполнения:");
    }
}