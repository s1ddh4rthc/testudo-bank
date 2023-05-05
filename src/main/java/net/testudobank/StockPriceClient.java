package net.testudobank;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import yahoofinance.YahooFinance;

import java.io.IOException;

@Component
public class StockPriceClient {

  public double getCurrentStockValue(String cryptoName) {
    if (cryptoName.equals("AAPL")) {
      return getCurrentAppleValue();
    } else if (cryptoName.equals("GOOG")) {
      return getCurrentGoogleValue();
    } else if (cryptoName.equals("MSFT")) {
      return getCurrentMicrosoftValue();
    } else {
      return -1;
    }
  }

  /**
   * Method which is used to return the current value of Ethereum
   * in USD. This method uses a Yahoo Finance Wrapper API
   * (https://github.com/sstrickx/yahoofinance-api).
   * NOTE: If the web scraper fails, a value of -1 is returned
   *
   * @return the current value of 1 AAPL Share in USD
   */
  @Cacheable("aapl-value")
  public double getCurrentAppleValue() {
    try {
      return YahooFinance.get("AAPL").getQuote().getPrice().doubleValue();
    } catch (IOException e1) {
      e1.printStackTrace();
      return -1;
    }
  }

  /**
   * @return the current value of 1 GOOG Share in USD
   */
  @Cacheable("goog-value")
  public double getCurrentGoogleValue() {
    try {
      return YahooFinance.get("GOOG").getQuote().getPrice().doubleValue();
    } catch (IOException e1) {
      e1.printStackTrace();
      return -1;
    }
  }

  /**
   * @return the current value of 1 MSFT Share in USD
   */
  @Cacheable("goog-value")
  public double getCurrentMicrosoftValue() {
    try {
      return YahooFinance.get("MSFT").getQuote().getPrice().doubleValue();
    } catch (IOException e1) {
      e1.printStackTrace();
      return -1;
    }
  }

  /**
   * Clear the cached price of Apple.
   * <p>
   * This method is scheduled to run every 30 seconds.
   */
  @Scheduled(fixedRate = 30000)
  @CacheEvict("aapl-value")
  public void clearApplePriceCache() {
  }

  /**
   * Clear the cached price of Google.
   * <p>
   * This method is scheduled to run every 30 seconds.
   */
  @Scheduled(fixedRate = 30000)
  @CacheEvict("goog-value")
  public void clearGooglePriceCache() {
  }

  /**
   * Clear the cached price of Microsoft.
   * <p>
   * This method is scheduled to run every 30 seconds.
   */
  @Scheduled(fixedRate = 30000)
  @CacheEvict("msft-value")
  public void clearMicrosoftPriceCache() {
  }

}
