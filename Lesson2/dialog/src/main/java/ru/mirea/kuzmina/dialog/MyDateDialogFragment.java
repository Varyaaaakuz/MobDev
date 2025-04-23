package ru.mirea.kuzmina.dialog;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class MyDateDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),
                (view, year1, month1, dayOfMonth) -> {
                    // Действие при выборе даты
                    String date = dayOfMonth + "." + (month1 + 1) + "." + year1;
                    showSnackbar("Выбрана дата: " + date);
                }, year, month, day);
    }

    private void showSnackbar(String message) {
        androidx.core.content.ContextCompat.getMainExecutor(getContext()).execute(() ->
                com.google.android.material.snackbar.Snackbar.make(
                        requireView(), message,
                        com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                ).show()
        );
    }
}