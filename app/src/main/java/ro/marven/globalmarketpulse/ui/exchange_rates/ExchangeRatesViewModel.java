package ro.marven.globalmarketpulse.ui.exchange_rates;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExchangeRatesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ExchangeRatesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}