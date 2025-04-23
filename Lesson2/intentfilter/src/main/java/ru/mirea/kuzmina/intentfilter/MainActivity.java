package ru.mirea.kuzmina.intentfilter;

import android.os.Bundle;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;
import android.widget.Button;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    View.OnClickListener onClicklink = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri address = Uri.parse("https://www.mirea.ru/");
            Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openLinkIntent);
        }
    };
    View.OnClickListener onClickfio = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Кузьмина Варвара Владиславовна");
            startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));
        }
    };
    button2.setOnClickListener(onClickfio);
    button1.setOnClickListener(onClicklink);
}
}
