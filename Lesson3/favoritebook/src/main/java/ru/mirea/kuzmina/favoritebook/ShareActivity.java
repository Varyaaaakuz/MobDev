package ru.mirea.kuzmina.favoritebook;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Intent;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
public class ShareActivity extends AppCompatActivity {
    private EditText inputBookName;
    private EditText inputFavoriteQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        TextView showExampleBook = findViewById(R.id.showExampleBook);
        TextView showExampleQuote = findViewById(R.id.showExampleQuote);
        inputBookName = findViewById(R.id.inputBookName);
        inputFavoriteQuote = findViewById(R.id.inputFavoriteQuote);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        Intent receivedData = getIntent();
        showExampleBook.setText(receivedData.getStringExtra(MainActivity.BOOK_NAME_KEY));
        showExampleQuote.setText(receivedData.getStringExtra(MainActivity.QUOTES_KEY));

        buttonSubmit.setOnClickListener(v -> sendResult());
    }
    private void sendResult() {
        String bookName = inputBookName.getText().toString().trim();
        String favoriteQuote = inputFavoriteQuote.getText().toString().trim();
        if (bookName.isEmpty()) {
            inputBookName.setError("Введите название книги");
            return;
        }
        if (favoriteQuote.isEmpty()) {
            inputFavoriteQuote.setError("Введите вашу любимую цитату");
            return;
        }
        String resultMessage = "Книга: " + bookName + "\n" +
                "Цитата: \"" + favoriteQuote + "\"";
        Intent resultData = new Intent();
        resultData.putExtra(MainActivity.USER_MESSAGE, resultMessage);
        setResult(Activity.RESULT_OK, resultData);
        finish();
    }
}