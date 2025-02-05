package ro.marven.globalmarketpulse.ui.business_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ro.marven.globalmarketpulse.databinding.FragmentBusinessNewsBinding;

public class BusinessNewsFragment extends Fragment {

    private FragmentBusinessNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BusinessViewModel businessViewModel =
                new ViewModelProvider(this).get(BusinessViewModel.class);

        binding = FragmentBusinessNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBusinessNews;
        businessViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}