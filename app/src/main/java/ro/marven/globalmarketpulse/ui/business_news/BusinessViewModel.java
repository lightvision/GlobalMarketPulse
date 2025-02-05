package ro.marven.globalmarketpulse.ui.business_news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BusinessViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BusinessViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}