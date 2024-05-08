package net.testudobank;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/*
 * Decided to switch to web scraping as API verification and token generation were haeavily convoluted
 * This makes obtaining stock prices easier and simplifies the development process
 * BASE URL: "http://finance.yahoo.com/q?s={ticker}"
 * Ticker would be the user input of what stock they want to invest in 
 */

public class StockPriceClient {

    String baseUrl = "http://finance.yahoo.com/q?s=";
    
    /**
     * Method to control which Stock price should be returned.
     * 
     * @param ticker the stock symbol
     * @return the price of the stock or -1 if an error occurs
     */
    public double getCurrentStockValue(String ticker) {
        if (ticker == null || ticker.trim().isEmpty()) {
            return -1;
        }

        return getStockPrice(ticker);
    }

    /**
     * Method which is used to return the current value of a stock
     * in USD
     * 
     * @param ticker the stock symbol
     * @return the price of the stock or -1 if an error occurs
     */
    public double getStockPrice(String ticker) {
        double returnValue = -1;
        try {
            String url = baseUrl + ticker;
            Document doc = Jsoup.connect(url).get();
            Element priceElement = doc.select("fin-streamer[data-field='regularMarketPrice']").first();

            if (priceElement != null && !priceElement.text().isEmpty()) {
                String priceText = priceElement.text().replace(",", "");
                try {
                    returnValue = Double.parseDouble(priceText);

                    if (returnValue < 0) {
                        returnValue = -1;
                    }
                } catch (NumberFormatException e) {
                    returnValue = -1;
                }
            }
        } catch (Exception e) {
            returnValue = -1;
        }

        return returnValue;
    }
}