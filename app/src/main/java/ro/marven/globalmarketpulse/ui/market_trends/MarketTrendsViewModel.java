package ro.marven.globalmarketpulse.ui.market_trends;

    import android.app.Application;
    import android.content.Context;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;
    import android.util.Log;

    import androidx.lifecycle.AndroidViewModel;
    import androidx.lifecycle.LiveData;
    import androidx.lifecycle.MutableLiveData;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

    import okhttp3.Call;
    import okhttp3.Callback;
    import okhttp3.OkHttpClient;
    import okhttp3.Request;
    import okhttp3.Response;

    public class MarketTrendsViewModel extends AndroidViewModel {
        String url = "https://tradestie.com/api/v1/apps/reddit";

        private final MutableLiveData<List<MarketTrends>> marketTrendsLiveData;
        private final MutableLiveData<String> errorStateLiveData;

        public MarketTrendsViewModel(Application application) {
            super(application);
            marketTrendsLiveData = new MutableLiveData<>();
            errorStateLiveData = new MutableLiveData<>();
        }

        public LiveData<List<MarketTrends>> getMarketTrends() {
            return marketTrendsLiveData;
        }

        public LiveData<String> getErrorState() {
            return errorStateLiveData;
        }

        public void fetchMarketSentiment() {
            if (!isNetworkAvailable()) {
                errorStateLiveData.postValue("No network connection available.");
                marketTrendsLiveData.postValue(new ArrayList<>());
                return;
            }

            OkHttpClient client = new OkHttpClient();

            Log.i("MarketSentimentVM", "Fetching marketing sentiment from Reddit.");

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    errorStateLiveData.postValue(e.getMessage());
                    marketTrendsLiveData.postValue(new ArrayList<>());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful() && response.body() != null) {
                        try {
                            String responseBody = response.body().string();
                            JSONArray jsonArray = new JSONArray(responseBody);
                            List<MarketTrends> sentiments = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                sentiments.add(new MarketTrends(
                                        obj.getString("ticker"),
                                        obj.getString("sentiment"),
                                        obj.getDouble("no_of_comments"),
                                        obj.getString("sentiment_score")
                                ));
                            }

                            marketTrendsLiveData.postValue(sentiments);
                            errorStateLiveData.postValue(null);
                        } catch (Exception e) {
                            errorStateLiveData.postValue("Error parsing data: " + e.getMessage());
                            marketTrendsLiveData.postValue(new ArrayList<>());
                        }
                    } else {
                        errorStateLiveData.postValue("Failed to fetch data. Response not successful.");
                        marketTrendsLiveData.postValue(new ArrayList<>());
                    }
                }
            });
        }

        public void updateMarketTrends(List<MarketTrends> marketTrends) {
            marketTrendsLiveData.setValue(marketTrends);
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }