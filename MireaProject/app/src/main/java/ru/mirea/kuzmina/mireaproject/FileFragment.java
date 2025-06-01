package ru.mirea.kuzmina.mireaproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class FileFragment extends DialogFragment {

    private FileDialogListener listener;

    public interface FileDialogListener {
        void onDialogSave(String filename, String content);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            if (context instanceof FileDialogListener) {
                listener = (FileDialogListener) context;
            } else {
                throw new ClassCastException(context.toString() + " must implement FileDialogListener");
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FileDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText inputFilename = new EditText(requireContext());
        inputFilename.setHint("Имя файла");
        inputFilename.setInputType(InputType.TYPE_CLASS_TEXT);

        EditText inputContent = new EditText(requireContext());
        inputContent.setHint("Содержимое");
        inputContent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        inputContent.setMinLines(4);

        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);
        layout.addView(inputFilename);
        layout.addView(inputContent);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Создание файла")
                .setView(layout)
                .setPositiveButton("Сохранить", (dialog, which) -> {
                    String filename = inputFilename.getText().toString();
                    String content = inputContent.getText().toString();
                    if (listener != null) {
                        listener.onDialogSave(filename, content);
                    }
                })
                .setNegativeButton("Отмена", null);

        return builder.create();
    }
}