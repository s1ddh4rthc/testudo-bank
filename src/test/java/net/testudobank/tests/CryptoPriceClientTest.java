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
}
