package net.testudobank.tests;

import net.testudobank.CryptoPriceClient;
import net.testudobank.tests.MvcControllerIntegTest.CryptoTransaction;
import net.testudobank.tests.MvcControllerIntegTest.CryptoTransactionTestType;
import net.testudobank.tests.MvcControllerIntegTest.CryptoTransactionTester;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Map;

import javax.script.ScriptException;

public class CryptoPriceClientTest {
    CryptoPriceClient cryptoPriceClient = new CryptoPriceClient();

    /**
     * Ensure the client is able to get a valid price from the Yahoo Finance API
     */
    @Test
    public void testBasic() {

        assertTrue(cryptoPriceClient.getCurrentEthValue() > 0);
        assertTrue(cryptoPriceClient.getCurrentSolValue() > 0);

    }

    @Test
    public void testBuyETHBuySOLSellSOLUserFlow() throws ScriptException {
        CryptoTransactionTester tester = CryptoTransactionTester.builder()
                .initialBalanceInDollars(1000)
                .initialCryptoBalance(Map.of("SOL", 0.0, "ETH", 0.0))
                .build();

        tester.initialize();
        // Buy ETH
        CryptoTransaction buyEth = CryptoTransaction.builder()
                .expectedEndingBalanceInDollars(900)
                .expectedEndingCryptoBalance(0.1)
                .cryptoPrice(1000)
                .cryptoAmountToTransact(0.1)
                .cryptoName("ETH")
                .cryptoTransactionTestType(CryptoTransactionTestType.BUY)
                .shouldSucceed(true)
                .build();
        tester.test(buyEth);

        // Buy SOL
        CryptoTransaction buySolTransaction = CryptoTransaction.builder()
                .expectedEndingBalanceInDollars(800)
                .expectedEndingCryptoBalance(0.1)
                .cryptoPrice(1000)
                .cryptoAmountToTransact(0.1)
                .cryptoName("SOL")
                .cryptoTransactionTestType(CryptoTransactionTestType.BUY)
                .shouldSucceed(true)
                .build();

        // Sell some SOL
        tester.test(buySolTransaction);
        CryptoTransaction sellSolTransaction = CryptoTransaction.builder()
                .expectedEndingBalanceInDollars(890)
                .expectedEndingCryptoBalance(.01)
                .cryptoPrice(1000)
                .cryptoAmountToTransact(0.09)
                .cryptoName("SOL")
                .cryptoTransactionTestType(CryptoTransactionTestType.SELL)
                .shouldSucceed(true)
                .build();

        tester.test(sellSolTransaction);
    }
}
