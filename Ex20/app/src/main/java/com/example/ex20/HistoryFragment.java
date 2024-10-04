package com.example.ex20;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ResultsAdapter adapter;
    private ResultsViewModel resultsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);

        // Initialize RecyclerView and ViewModel
        recyclerView = view.findViewById(R.id.recyclerView);
        resultsViewModel = new ViewModelProvider(requireActivity()).get(ResultsViewModel.class);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ResultsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Observe the results LiveData
        resultsViewModel.getResults().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> results) {
                adapter.updateResults(results); // Update adapter data
            }
        });

        return view;
    }

    // Adapter class for RecyclerView
    private static class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultViewHolder> {
        private ArrayList<String> results;

        ResultsAdapter(ArrayList<String> results) {
            this.results = results;
        }

        @NonNull
        @Override
        public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ResultViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
            holder.bind(results.get(position));
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        void updateResults(ArrayList<String> newResults) {
            this.results.clear();
            this.results.addAll(newResults);
            notifyDataSetChanged();
        }

        static class ResultViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;

            ResultViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }

            void bind(String result) {
                textView.setText(result);
            }
        }
    }
}
