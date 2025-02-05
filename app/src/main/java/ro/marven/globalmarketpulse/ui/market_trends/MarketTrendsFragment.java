package ro.marven.globalmarketpulse.ui.market_trends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ro.marven.globalmarketpulse.databinding.FragmentMarketTrendsBinding;

public class MarketTrendsFragment extends Fragment {

    private FragmentMarketTrendsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MarketTrendsViewModel marketTrendsViewModel =
                new ViewModelProvider(this).get(MarketTrendsViewModel.class);

        binding = FragmentMarketTrendsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMarketTrends;
        marketTrendsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}