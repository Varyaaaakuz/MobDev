package ru.mirea.kuzmina.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
    private EditText yName, yAge, yGroup, yHobby;
    private Button btnSave;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        yName = view.findViewById(R.id.editTextText8);
        yAge = view.findViewById(R.id.editTextText10);
        yGroup = view.findViewById(R.id.editTextText11);
        yHobby = view.findViewById(R.id.editTextText12);
        btnSave = view.findViewById(R.id.button2);

        preferences = requireActivity().getSharedPreferences("user_profile", Context.MODE_PRIVATE);

        yName.setText(preferences.getString("name", ""));
        yAge.setText(preferences.getString("age", ""));
        yGroup.setText(preferences.getString("group", ""));
        yHobby.setText(preferences.getString("hobby", ""));

        btnSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", yName.getText().toString());
            editor.putString("age", yAge.getText().toString());
            editor.putString("group", yGroup.getText().toString());
            editor.putString("hobby", yHobby.getText().toString());
            editor.apply();
            Toast.makeText(getContext(), "Данные сохранены", Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}