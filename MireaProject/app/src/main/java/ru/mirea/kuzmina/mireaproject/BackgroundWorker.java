package ru.mirea.kuzmina.mireaproject;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.concurrent.TimeUnit;

public class BackgroundWorker extends Worker {
    private static final String TAG = "BackgroundWorker";
    public BackgroundWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }
    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Задача ожидает выполнения условий");

        if (!checkConditions()) {
            Log.w(TAG, "Условия для выполнения не выполнены, задача будет перезапущена");
            return Result.retry();
        }

        Log.d(TAG, "Фоновая задача начата");
        try {
            TimeUnit.SECONDS.sleep(10);
            Log.d(TAG, "Фоновая задача завершена");
            return Result.success();
        } catch (InterruptedException e) {
            Log.e(TAG, "Ошибка в фоновой задаче", e);
            return Result.failure();
        }
    }
    private boolean checkConditions() {
        boolean isUnmeteredNetwork = true;
        Log.d(TAG, String.format("Проверка условий: сеть=%s",
                isUnmeteredNetwork));
        return isUnmeteredNetwork;
    }
}