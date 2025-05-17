package ru.mirea.kuzmina.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkInfo;
import androidx.lifecycle.Observer;
import ru.mirea.kuzmina.mireaproject.databinding.FragmentBackgroundTaskBinding;

public class BackgroundTaskFragment extends Fragment {
    private FragmentBackgroundTaskBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBackgroundTaskBinding.inflate(inflater, container, false);

        binding.startTaskButton.setOnClickListener(v -> {
            binding.statusTextView.setText("Status: Waiting");
            startBackgroundTask();
        });

        return binding.getRoot();
    }

    private void startBackgroundTask() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();
        OneTimeWorkRequest workRequest =
                new OneTimeWorkRequest.Builder(BackgroundWorker.class)
                        .setConstraints(constraints)
                        .build();

        WorkManager.getInstance(requireContext())
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(getViewLifecycleOwner(), new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null) {
                            switch (workInfo.getState()) {
                                case ENQUEUED:
                                    binding.statusTextView.setText("Status: Waiting");
                                    break;
                                case RUNNING:
                                    binding.statusTextView.setText("Status: Running");
                                    break;
                                case SUCCEEDED:
                                    binding.statusTextView.setText("Status: Succeeded");
                                    break;
                                case FAILED:
                                    binding.statusTextView.setText("Status: Failed");
                                    break;
                            }
                        }
                    }
                });

        WorkManager.getInstance(requireContext()).enqueue(workRequest);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}