package ro.marven.globalmarketpulse.ui.market_trends;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ro.marven.globalmarketpulse.R;
import ro.marven.globalmarketpulse.databinding.FragmentMarketTrendsBinding;

public class MarketTrendsFragment extends Fragment {

    private static final String TAG = "MarketSentimentFragment";

    private MarketTrendsViewModel viewModel; // ViewModel pentru Market Sentiment
    private FragmentMarketTrendsBinding binding; // Binding pentru fragment
    private MarketTrendsAdapter adapter; // Adapter pentru RecyclerView
    private ProgressBar progressBar; // ProgressBar pentru a indica incarcarea

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMarketTrendsBinding.inflate(inflater, container, false);
        setupViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = binding.getRoot().findViewById(R.id.progressBar);
        setupRecyclerView();
        setupSwipeRefreshLayout();

        Log.i(TAG, "Starting initial data fetch.");
        binding.swipeRefreshLayout.setRefreshing(true);
        progressBar.setVisibility(View.VISIBLE);
        viewModel.fetchMarketSentiment();
    }

    /**
     * Configureaza RecyclerView.
     */
    private void setupRecyclerView() {
        binding.recyclerViewMarketTrends.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MarketTrendsAdapter(new ArrayList<>());
        binding.recyclerViewMarketTrends.setAdapter(adapter);
    }

    /**
     * Configureaza ViewModel-ul.
     */
    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MarketTrendsViewModel.class);

        // Observam lista de Market Sentiments
        viewModel.getMarketTrends().observe(getViewLifecycleOwner(), sentiments -> {
            // Logam numarul de elemente actualizate
            Log.i(TAG, "Data updated successfully: " + sentiments.size() + " items.");
            // Actualizam datele din adapter
            adapter.updateData(sentiments);
            // Oprim animatia de refresh
            binding.swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
        });

        // Observam starea de eroare
        viewModel.getErrorState().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty()) {
                // Logam eroarea
                Log.e(TAG, "Error during data fetch: " + error);
                // Afisam un mesaj de eroare
                Toast.makeText(requireContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
                // Oprim animatia de refresh
                binding.swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Configureaza SwipeRefreshLayout.
     */
    private void setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.i(TAG, "Swipe-to-Refresh started.");
            progressBar.setVisibility(View.VISIBLE);
            viewModel.fetchMarketSentiment();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}