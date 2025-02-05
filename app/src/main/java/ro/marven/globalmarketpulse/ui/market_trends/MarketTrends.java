package ro.marven.globalmarketpulse.ui.market_trends;

public class MarketTrends {
    private String ticker; // simbolul ticker
    private String sentiment; // sentimentul
    private double numberOfComments; // numarul de comentarii
    private String sentimentScore; // scorul sentimentului


    public MarketTrends(String ticker, String sentiment, double numberOfComments, String sentimentScore) {
        this.ticker = ticker;
        this.sentiment = sentiment;
        this.numberOfComments = numberOfComments;
        this.sentimentScore = sentimentScore;
    }

    public String getTicker() {
        return ticker;
    }

    public String getSentiment() {
        return sentiment;
    }
    public double getNumberOfComments() {
        return numberOfComments;
    }

    public String getSentimentScore() {
        return sentimentScore;
    }
}
