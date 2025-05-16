package ru.mirea.kuzmina.workmanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkInfo;
import androidx.lifecycle.Observer;
import androidx.work.WorkRequest;
public class MainActivity extends AppCompatActivity {
    private TextView TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView = findViewById(R.id.TextView);

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .build();

        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .setConstraints(constraints)
                .build();

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(uploadWorkRequest);

        workManager.getWorkInfoByIdLiveData(uploadWorkRequest.getId()).observe(this, workInfo -> {
            if (workInfo != null) {
                WorkInfo.State state = workInfo.getState();
                switch (state) {
                    case ENQUEUED:
                        TextView.setText("Task Enqueued: Waiting for conditions...");
                        break;
                    case RUNNING:
                        TextView.setText("Task Running: Work in progress...");
                        break;
                    case SUCCEEDED:
                        TextView.setText("Task Succeeded: Work completed!");
                        break;
                    case FAILED:
                        TextView.setText("Task Failed: Something went wrong.");
                        break;
                    case BLOCKED:
                        TextView.setText("Task Blocked: Waiting for dependencies...");
                        break;
                    case CANCELLED:
                        TextView.setText("Task Cancelled.");
                        break;
                }
            }
        });
    }
}