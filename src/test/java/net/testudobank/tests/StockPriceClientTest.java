package net.testudobank.tests;

import net.testudobank.StockPriceClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockPriceClientTest {

    @Test
    public void testValidTicker() {
        StockPriceClient client = new StockPriceClient();
        double price = client.getCurrentStockValue("GOOG");
        assertTrue(price > 0);
    }

    @Test
    public void testValidTicker2() {
        StockPriceClient client = new StockPriceClient();
        double price = client.getCurrentStockValue("TSLA");
        assertTrue(price > 0);
    }

    @Test
    public void testValidTicker3() {
        StockPriceClient client = new StockPriceClient();
        double price = client.getCurrentStockValue("BRK-A");
        assertTrue(price > 0);
    }

    @Test
    public void testInvalidTicker2() {
        StockPriceClient client = new StockPriceClient();
        double price = client.getCurrentStockValue(null);
        assertTrue(price == -1);
    }

    @Test
    public void testInvalidTicker3() {
        StockPriceClient client = new StockPriceClient();
        double price = client.getCurrentStockValue("");
        assertTrue(price == -1);
    }
}