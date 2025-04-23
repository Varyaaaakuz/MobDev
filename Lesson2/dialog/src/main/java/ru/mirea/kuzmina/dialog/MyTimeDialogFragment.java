package ru.mirea.kuzmina.dialog;

import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
import android.app.Dialog;
public class MyTimeDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),
                (view, hourOfDay, minute1) -> {
                    // Действие при выборе времени
                    String time = hourOfDay + ":" + minute1;
                    showSnackbar("Выбрано время: " + time);
                }, hour, minute, true);
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