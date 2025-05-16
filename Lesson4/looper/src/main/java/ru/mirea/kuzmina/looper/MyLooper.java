package ru.mirea.kuzmina.looper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;

public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                try {
                    String job = msg.getData().getString("KEY");
                    long delay = msg.getData().getLong("DELAY");

                    Log.d("MyLooper", "Processing job: " + job + " with delay: " + delay + "ms");

                    // Имитируем обработку с задержкой
                    Thread.sleep(delay);

                    int lettersCount = job.length();
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("result",
                            "Job: " + job +
                                    ", Letters count: " + lettersCount +
                                    ", Processed after " + (delay/1000) + " seconds");
                    message.setData(bundle);

                    mainHandler.sendMessage(message);

                } catch (InterruptedException e) {
                    Log.e("MyLooper", "Thread interrupted", e);
                }
            }
        };
        Looper.loop();
    }
}
