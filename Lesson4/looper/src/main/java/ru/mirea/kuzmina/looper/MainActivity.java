package ru.mirea.kuzmina.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.kuzmina.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity	{
    @Override
    protected	void	onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler mainThreadHandler	=	new	Handler(Looper.getMainLooper())	{
            @Override
            public	void	handleMessage(Message msg)	{
                String result = msg.getData().getString("result");
                Log.d(MainActivity.class.getSimpleName(), "Task executed. Result: " + result);
            }
        };
        MyLooper	myLooper	=	new	MyLooper(mainThreadHandler);
        myLooper.start();
        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public void onClick(View v) {
                try {
                    // Получаем возраст и профессию из EditText
                    int age = Integer.parseInt(binding.editTextAge.getText().toString());
                    String job = binding.editTextJob.getText().toString();

            // Задержка равна возрасту в секундах
                    long delaySeconds = age * 1000L;
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY", job);
                    bundle.putLong("DELAY", delaySeconds);
                    msg.setData(bundle);

                    myLooper.mHandler.sendMessage(msg);

                    Log.d(MainActivity.class.getSimpleName(),
                        "Sent message with job: " + job + " and delay: " + delaySeconds + "ms");
                } catch (NumberFormatException e) {
                    Log.e(MainActivity.class.getSimpleName(), "Invalid age input");
                }
            }
        });
    }
}