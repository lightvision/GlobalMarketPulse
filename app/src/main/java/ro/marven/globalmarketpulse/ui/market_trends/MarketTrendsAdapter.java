package ro.marven.globalmarketpulse.ui.market_trends;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.marven.globalmarketpulse.R;

public class MarketTrendsAdapter extends RecyclerView.Adapter<MarketTrendsAdapter.ViewHolder>{
    private List<MarketTrends> marketTrends;

    public MarketTrendsAdapter(List<MarketTrends> marketSentiments) {
        this.marketTrends = marketSentiments;
    }

    public void updateData(List<MarketTrends> marketSentiments) {
        this.marketTrends = marketSentiments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_trends, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarketTrends sentiment = marketTrends.get(position);
        holder.textTicker.setText(sentiment.getTicker());

        // Create a SpannableString for the sentiment text
        String sentimentText = holder.itemView.getContext().getString(R.string.sentiment, sentiment.getSentiment());
        SpannableString spannableString = new SpannableString(sentimentText);
        int start = sentimentText.indexOf(sentiment.getSentiment());
        int end = start + sentiment.getSentiment().length();
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.textSentiment.setText(spannableString);
        holder.textComments.setText(holder.itemView.getContext().getString(R.string.number_of_comments, (int) sentiment.getNumberOfComments()));
        holder.textSentimentScore.setText(holder.itemView.getContext().getString(R.string.sentiment_score, sentiment.getSentimentScore()));
    }

    @Override
    public int getItemCount() {
        // Returneaza numarul de elemente din lista
        return marketTrends.size();
    }

    /**
     * ViewHolder pentru Market Sentiment.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTicker, textSentiment, textComments, textSentimentScore;

        /**
         * Constructor pentru ViewHolder.
         *
         * @param itemView view-ul pentru fiecare element din lista
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTicker = itemView.findViewById(R.id.textTicker);
            textSentiment = itemView.findViewById(R.id.textSentiment);
            textComments = itemView.findViewById(R.id.textComments);
            textSentimentScore = itemView.findViewById(R.id.textSentimentScore);
        }
    }
}
