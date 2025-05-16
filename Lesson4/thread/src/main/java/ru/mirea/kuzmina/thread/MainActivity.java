package ru.mirea.kuzmina.thread;
import android.os.Process;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import ru.mirea.kuzmina.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.textView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-09-22, НОМЕР ПО СПИСКУ: 16, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Блондинка в шоколаде");
        binding.textView.append("\nНовое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.buttonMirea.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                    int numberThread = counter++;
                    Log.d("ThreadProject",
                            "Запущен поток № " + numberThread +
                                    " студентом группы № БСБО-09-22 номер по списку № 16");

                    long endTime = System.currentTimeMillis() + 20 * 1000;
                    while (System.currentTimeMillis() < endTime) {
                        synchronized (this) {
                            try {
                                wait(endTime - System.currentTimeMillis());
                                Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                    }

                    int totalPairs = Integer.parseInt(binding.editTextPairs.getText().toString());
                    int days = Integer.parseInt(binding.editTextDays.getText().toString());
                    double avg = (double)totalPairs / days;

                    runOnUiThread(() -> {
                        binding.resultTextView.setText("Среднее: " + avg + " пар/день");
                    });
                }
            }).start();
        });
    }
}