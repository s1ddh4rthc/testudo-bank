package net.testudobank.tests;

import net.testudobank.CryptoPriceClient;
import net.testudobank.tests.MvcControllerIntegTest.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import javax.script.ScriptException;

public class CryptoPriceClientTest {
    CryptoPriceClient cryptoPriceClient = new CryptoPriceClient();

    /**
     *  Ensure the client is able to get a valid price from the Yahoo Finance API
     */
    @Test
    public void testBasic() {
        assertTrue(cryptoPriceClient.getCurrentEthValue() > 0);
        assertTrue(cryptoPriceClient.getCurrentSolValue() > 0);
    }

    /**
     *  Test the situation in which a customer with no pre-existing Crypto buys ETH, buys SOL, 
     *  and then sells some of their SOL.
     * @throws ScriptException
     */
    @Test
    public void testBuyValidCrypto() throws ScriptException {
        CryptoTransactionTester cryptoETHBuyTransactionTester = CryptoTransactionTester.builder()
        .initialBalanceInDollars(1000)
        .initialCryptoBalance(Collections.singletonMap("ETH", 0.0))
        .build();

        cryptoETHBuyTransactionTester.initialize();
        
        CryptoTransaction cryptoETHBuyTransaction = CryptoTransaction.builder()
            .expectedEndingBalanceInDollars(900)
            .expectedEndingCryptoBalance(0.1)
            .cryptoPrice(1000)
            .cryptoAmountToTransact(0.1)
            .cryptoName("ETH")
            .cryptoTransactionTestType(CryptoTransactionTestType.BUY)
            .shouldSucceed(true)
            .build();
            cryptoETHBuyTransactionTester.test(cryptoETHBuyTransaction);

        CryptoTransactionTester cryptoSOLBuyTransactionTester = CryptoTransactionTester.builder()
        .initialBalanceInDollars(1000)
        .initialCryptoBalance(Collections.singletonMap("SOL", 0.0))
        .build();

        cryptoSOLBuyTransactionTester.initialize();
        
        CryptoTransaction cryptoSOLBuyTransaction = CryptoTransaction.builder()
            .expectedEndingBalanceInDollars(900)
            .expectedEndingCryptoBalance(0.1)
            .cryptoPrice(1000)
            .cryptoAmountToTransact(0.1)
            .cryptoName("SOL")
            .cryptoTransactionTestType(CryptoTransactionTestType.BUY)
            .shouldSucceed(true)
            .build();
            cryptoSOLBuyTransactionTester.test(cryptoSOLBuyTransaction);

        CryptoTransactionTester cryptoSOLSellTransactionTester = CryptoTransactionTester.builder()
            .initialBalanceInDollars(1000)
            .initialCryptoBalance(Collections.singletonMap("SOL", 0.1))
            .build();

            cryptoSOLSellTransactionTester.initialize();

        CryptoTransaction cryptoSOLSellTransaction = CryptoTransaction.builder()
                .expectedEndingBalanceInDollars(1100)
                .expectedEndingCryptoBalance(0)
                .cryptoPrice(1000)
                .cryptoAmountToTransact(0.1)
                .cryptoName("SOL")
                .cryptoTransactionTestType(CryptoTransactionTestType.SELL)
                .shouldSucceed(true)
                .build();
                cryptoSOLSellTransactionTester.test(cryptoSOLSellTransaction);
    }

    /**
     *  TestudoBank does not currently support BitCoin ($BTC). Write an integ test that 
     *  ensures that the "welcome" page is returned when a user attempts to put "BTC" as 
     *  the crypto name in the front-end when filling out the CryptoBuy form.
     * @throws ScriptException
     */
    @Test
    public void testBuyInvalidBTC() throws ScriptException {
        CryptoTransactionTester cryptoETHBuyTransactionTester = CryptoTransactionTester.builder()
        .initialBalanceInDollars(1000)
        .initialCryptoBalance(Collections.singletonMap("BTC", 0.0))
        .build();

        cryptoETHBuyTransactionTester.initialize();
        
        CryptoTransaction cryptoETHBuyTransaction = CryptoTransaction.builder()
            .expectedEndingBalanceInDollars(900)
            .expectedEndingCryptoBalance(0.1)
            .cryptoPrice(1000)
            .cryptoAmountToTransact(0.1)
            .cryptoName("BTC")
            .cryptoTransactionTestType(CryptoTransactionTestType.BUY)
            .shouldSucceed(true)
            .build();
            try{
                cryptoETHBuyTransactionTester.test(cryptoETHBuyTransaction);
                assertTrue(false);
            }
            catch (AssertionError e){
                assertTrue(true);
            }

    }

    /**
     *  TestudoBank does not currently support BitCoin ($BTC). Write an integ test that 
     *  ensures that the "welcome" page is returned when a user attempts to put "BTC" as 
     *  the crypto name in the front-end when filling out the CryptoSell form.
     * @throws ScriptException
     */
    @Test
    public void testSellInvalidBTC() throws ScriptException {
        CryptoTransactionTester cryptoTransactionTester = CryptoTransactionTester.builder()
            .initialBalanceInDollars(1000)
            .initialCryptoBalance(Collections.singletonMap("SOL", 0.1))
            .build();

            cryptoTransactionTester.initialize();

        CryptoTransaction cryptoTransaction = CryptoTransaction.builder()
                .expectedEndingBalanceInDollars(1100)
                .expectedEndingCryptoBalance(0)
                .cryptoPrice(1000)
                .cryptoAmountToTransact(0.1)
                .cryptoName("SOL")
                .cryptoTransactionTestType(CryptoTransactionTestType.SELL)
                .shouldSucceed(true)
                .build();
        try{
            cryptoTransactionTester.test(cryptoTransaction);
            assertTrue(false);
        }
        catch (AssertionError e){
            assertTrue(true);
        }
    }
}
