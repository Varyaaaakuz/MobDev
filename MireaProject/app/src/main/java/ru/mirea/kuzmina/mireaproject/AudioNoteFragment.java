package ru.mirea.kuzmina.mireaproject;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.Manifest;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

public class AudioNoteFragment extends Fragment {
    private MediaRecorder recorder;
    private Button recordBtn;
    private Button playButton;
    private boolean isRecording = false;
    private String outputFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_note, container, false);
        recordBtn = view.findViewById(R.id.record_button);
        playButton = view.findViewById(R.id.play_button);
        outputFile = requireContext().getExternalCacheDir().getAbsolutePath() + "/recording.3gp";

        playButton.setEnabled(false);

        recordBtn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 100);
            } else {
                toggleRecording();
            }
        });

        playButton.setOnClickListener(v -> {
            playAudio();
        });

        return view;
    }

    private void toggleRecording() {
        if (isRecording) {
            stopRecording();
            recordBtn.setText("Начать запись");
        } else {
            startRecording();
            recordBtn.setText("Остановить запись");
        }
        isRecording = !isRecording;
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(outputFile);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        playButton.setEnabled(false);
    }

    private void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
            playButton.setEnabled(true);
        }
    }

    private void playAudio() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(outputFile);
            mediaPlayer.prepare();
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(mp -> {
                mp.release();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}