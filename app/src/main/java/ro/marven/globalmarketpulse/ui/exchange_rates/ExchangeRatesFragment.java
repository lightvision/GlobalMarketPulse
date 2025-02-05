package ro.marven.globalmarketpulse.ui.exchange_rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ro.marven.globalmarketpulse.databinding.FragmentExchangeRatesBinding;

public class ExchangeRatesFragment extends Fragment {

    private FragmentExchangeRatesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExchangeRatesViewModel exchangeRatesViewModel =
                new ViewModelProvider(this).get(ExchangeRatesViewModel.class);

        binding = FragmentExchangeRatesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textExchangeRates;
        exchangeRatesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}