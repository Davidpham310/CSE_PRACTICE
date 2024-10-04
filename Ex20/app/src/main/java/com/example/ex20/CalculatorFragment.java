package com.example.ex20;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CalculatorFragment extends Fragment {
    private EditText txtA, txtB;
    private Button btnSum;
    private SharedPreferences sharedPreferences;
    private ResultsViewModel resultsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        // Initialize views
        txtA = view.findViewById(R.id.editA);
        txtB = view.findViewById(R.id.editB);
        btnSum = view.findViewById(R.id.btnSum);

        // Initialize results array
        resultsViewModel = new ViewModelProvider(requireActivity()).get(ResultsViewModel.class);
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Set click listener for the sum button
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateSum();
            }
        });

        return view;
    }

    private void calculateSum() {
        String inputA = txtA.getText().toString();
        String inputB = txtB.getText().toString();

        if (!inputA.isEmpty() && !inputB.isEmpty()) {
            try {
                int a = Integer.parseInt(inputA);
                int b = Integer.parseInt(inputB);
                String sum = String.format("%d + %d = %d", a, b, a + b);
                resultsViewModel.addResult(sum);
                Toast.makeText(getContext(), sum, Toast.LENGTH_SHORT).show(); // Show the result to the user
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid input, please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Please fill in both fields", Toast.LENGTH_SHORT).show();
        }
    }


}
