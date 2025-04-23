package ru.mirea.kuzmina.dialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.app.Dialog;
public class MyProgressDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Загрузка!");
        return progressDialog;
    }
}