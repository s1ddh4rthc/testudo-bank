package net.testudobank;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class StockPriceClient {
    /**
     * Method to control which Stock price should be returned.
     * 
     * @return
     */

    // public double getCurrentStockValue(String ticker) {
    //     if (ticker == null || ticker.trim().isEmpty()) {
    //         return -1;
    //     }

    //     return getStockPrice(ticker);
    // }

    /**
     * Method which is used to return the current value of a stock
     * in USD
    **/
    // public double getStockValue(String ticker) {


    //     // try {
    //     // // return YahooFinance.get("ETH-USD").getQuote().getPrice().doubleValue();
    //     // } catch (IOException e1) {
    //     // // Print Stack Trace for Debugging
    //     // e1.printStackTrace();
    //     // return -1;
    //     // }
    // }
}

// This method uses a Yahoo Finance Wrapper API
//      * (https://github.com/sstrickx/yahoofinance-api).
//      * <p>
//      * To avoid frequent calls to the external service, the value is cached.
//      * See {@link #clearEthPriceCache()}
//      * <p>
//      * NOTE: If the web scraper fails, a value of -1 is returned
//      *
//      * @return the current value of 1 ETH in USD
