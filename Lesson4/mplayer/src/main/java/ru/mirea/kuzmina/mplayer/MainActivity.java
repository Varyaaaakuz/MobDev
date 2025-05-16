package ru.mirea.kuzmina.mplayer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.kuzmina.mplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageButtonPlay.setOnClickListener(v -> togglePlayPause());

        binding.imageButtonPrevious.setOnClickListener(v -> {
            binding.textViewSongTitle.setText("Предыдущая песня");
        });

        binding.imageButtonNext.setOnClickListener(v -> {
            binding.textViewSongTitle.setText("Следующая песня");
        });
    }

    private void togglePlayPause() {
        isPlaying = !isPlaying;
        if (isPlaying) {
            binding.imageButtonPlay.setImageResource(android.R.drawable.ic_media_pause);
            binding.textViewSongTitle.setText("Сейчас играет");
        } else {
            binding.imageButtonPlay.setImageResource(android.R.drawable.ic_media_play);
            binding.textViewSongTitle.setText("Пауза");
        }
    }
}