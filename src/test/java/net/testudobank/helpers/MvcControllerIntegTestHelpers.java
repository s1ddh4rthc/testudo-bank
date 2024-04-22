package net.testudobank.helpers;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import javax.script.ScriptException;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.delegate.DatabaseDelegate;
import org.testcontainers.ext.ScriptUtils;

import net.testudobank.MvcController;
import net.testudobank.tests.MvcControllerIntegTest;

public class MvcControllerIntegTestHelpers {
  // Fetches DB credentials to initialize jdbcTemplate client
  public static DataSource dataSource(MySQLContainer db) {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl(db.getJdbcUrl());
    dataSource.setUser(db.getUsername());
    dataSource.setPassword(db.getPassword());
    return dataSource;
  }

  // Uses given customer details to initialize the customer in the Customers and Passwords table in the MySQL DB.
  public static void addCustomerToDB(DatabaseDelegate dbDelegate, String ID, String password, String firstName, String lastName, int balance, int overdraftBalance, int numFraudReversals, int numInterestDeposits) throws ScriptException {
    String insertCustomerSql = String.format("INSERT INTO Customers VALUES ('%s', '%s', '%s', %d, %d, %d, %d)", ID, firstName, lastName, balance, overdraftBalance, numFraudReversals, numInterestDeposits);
    ScriptUtils.executeDatabaseScript(dbDelegate, null, insertCustomerSql);

    String insertCustomerPasswordSql = String.format("INSERT INTO Passwords VALUES ('%s', '%s')", ID, password);
    ScriptUtils.executeDatabaseScript(dbDelegate, null, insertCustomerPasswordSql);
  }

  // Adds a customer to the MySQL DB with no overdraft balance or fraud disputes
  public static void addCustomerToDB(DatabaseDelegate dbDelegate, String ID, String password, String firstName, String lastName, int balance, int interestDeposits) throws ScriptException {
    addCustomerToDB(dbDelegate, ID, password, firstName, lastName, balance, 0, 0, 0);
  }

  public static void createCertificateOfDepositLogsTable(DatabaseDelegate dbDelegate) throws ScriptException {
    ScriptUtils.executeDatabaseScript(dbDelegate, null, "CREATE TABLE CertificateOfDepositLogs (CertificateOfDepositID int PRIMARY KEY AUTO_INCREMENT, CustomerID varchar(255), TimestampPurchased DATETIME, TimestampMatured DATETIME, Status varchar(255) CHECK (Status IN ('Active', 'Redeemed')), DepositAmount int, InterestRate float, EarlyWithdrawlPenaltyRate float);");
  }

  public static void insertRowToCertificateOfDepositLogsTable(DatabaseDelegate dbDelegate, String customerID, String timestampPurchased, String timestampMatured, String status, int amtInPennies, double interestRate, double earlyWithdrawlPenaltyRate) throws ScriptException {

    String insertRowToCertificateOfDepositLogsSql = String.format("INSERT INTO CertificateOfDepositLogs VALUES (NULL, '%s', '%s', '%s', '%s', %d, %f, %f);",                    
                                                    customerID, 
                                                    timestampPurchased,
                                                    timestampMatured,
                                                    status,
                                                    amtInPennies,
                                                    interestRate,
                                                    earlyWithdrawlPenaltyRate);
    ScriptUtils.executeDatabaseScript(dbDelegate, null, insertRowToCertificateOfDepositLogsSql);
  }


  // Set crypto balance to specified amount
  public static void setCryptoBalance(DatabaseDelegate dbDelegate, String userID, String cryptoName, double cryptoAmount) throws ScriptException {
    String removeOldBalanceSql = String.format("DELETE FROM CryptoHoldings WHERE CustomerID='%s' AND CryptoName= '%s';", userID, cryptoName);
    ScriptUtils.executeDatabaseScript(dbDelegate, null, removeOldBalanceSql);
    String setBalanceSql = String.format("INSERT INTO CryptoHoldings (CryptoAmount,CustomerID,CryptoName) VALUES (%f, '%s' , '%s')", cryptoAmount, userID, cryptoName);
    ScriptUtils.executeDatabaseScript(dbDelegate, null, setBalanceSql);
  }

  // Verifies that a single transaction log in the TransactionHistory table matches the expected customerID, timestamp, action, and amount
  public static void checkTransactionLog(Map<String,Object> transactionLog, LocalDateTime timeWhenRequestSent, String expectedCustomerID, String expectedAction, int expectedAmountInPennies) {
    assertEquals(expectedCustomerID, (String)transactionLog.get("CustomerID"));
    assertEquals(expectedAction, (String)transactionLog.get("Action"));
    assertEquals(expectedAmountInPennies, (int)transactionLog.get("Amount"));
    // verify that the timestamp for the Deposit is within a reasonable range from when the request was first sent
    LocalDateTime transactionLogTimestamp = (LocalDateTime)transactionLog.get("Timestamp");
    LocalDateTime transactionLogTimestampAllowedUpperBound = timeWhenRequestSent.plusSeconds(MvcControllerIntegTest.REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS);
    assertTrue(transactionLogTimestamp.compareTo(timeWhenRequestSent) >= 0 && transactionLogTimestamp.compareTo(transactionLogTimestampAllowedUpperBound) <= 0);
    System.out.println("Timestamp stored in TransactionHistory table for the request: " + transactionLogTimestamp);
  }

  // Verifies that a single overdraft repayment log in the OverdraftLogs table matches the expected customerID, timestamp, depositAmt, oldOverBalance, and newOverBalance
  public static void checkOverdraftLog(Map<String,Object> overdraftLog, LocalDateTime timeWhenRequestSent, String expectedCustomerID, int expectedDepositAmtInPennies, int expectedOldOverBalanceInPennies, int expectedNewOverBalanceInPennies) {
    assertEquals(expectedCustomerID, (String)overdraftLog.get("CustomerID"));
    assertEquals(expectedDepositAmtInPennies, (int)overdraftLog.get("DepositAmt"));
    assertEquals(expectedOldOverBalanceInPennies, (int)overdraftLog.get("OldOverBalance"));
    assertEquals(expectedNewOverBalanceInPennies, (int)overdraftLog.get("NewOverBalance"));
    // verify that the timestamp for the overdraft repayement is within a reasonable range from when the Deposit request was first sent
    LocalDateTime overdraftLogTimestamp = (LocalDateTime)overdraftLog.get("Timestamp");
    LocalDateTime overdraftLogTimestampAllowedUpperBound = timeWhenRequestSent.plusSeconds(MvcControllerIntegTest.REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS);
    assertTrue(overdraftLogTimestamp.compareTo(timeWhenRequestSent) >= 0 && overdraftLogTimestamp.compareTo(overdraftLogTimestampAllowedUpperBound) <= 0);
    System.out.println("Timestamp stored in OverdraftLogs table for the Repayment: " + overdraftLogTimestamp);
  }

  // Verifies that a single crypto log in the CryptoHistory table matches the expected customerID, timestamp, action, cryptocurrency, and amount
  public static void checkCryptoLog(Map<String,Object> cryptoLog, LocalDateTime timeWhenRequestSent, String expectedCustomerID, String expectedAction, String expectedCryptoName, double expectedCryptoAmount) {
    assertEquals(expectedCustomerID, cryptoLog.get("CustomerID"));
    assertEquals(expectedAction, cryptoLog.get("Action"));
    assertEquals(expectedCryptoAmount, ((BigDecimal) cryptoLog.get("CryptoAmount")).doubleValue());
    assertEquals(expectedCryptoName, cryptoLog.get("CryptoName"));
    // verify that the timestamp for the Deposit is within a reasonable range from when the request was first sent
    LocalDateTime transactionLogTimestamp = (LocalDateTime)cryptoLog.get("Timestamp");
    LocalDateTime transactionLogTimestampAllowedUpperBound = timeWhenRequestSent.plusSeconds(MvcControllerIntegTest.REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS);
    assertTrue(transactionLogTimestamp.compareTo(timeWhenRequestSent) >= 0 && transactionLogTimestamp.compareTo(transactionLogTimestampAllowedUpperBound) <= 0);
    System.out.println("Timestamp stored in CryptoHistory table for the request: " + transactionLogTimestamp);
  }

  // Verifies that a single CD log in the CryptoHistory table matches the expected customerID, timestampPurchased, timestampMatured, Status, DepositAmount
  public static void checkCDLog(Map<String,Object> CDLog, String expectedCustomerID, LocalDateTime timeWhenRequestSent, int expectedDepositAmt, String expectedStatus) {
    assertEquals(expectedCustomerID, CDLog.get("CustomerID"));
    assertEquals(expectedStatus, CDLog.get("Status"));
    assertEquals(expectedDepositAmt, ((Integer) CDLog.get("DepositAmount")).intValue());
    // verify that the timestamp for the Deposit is within a reasonable range from when the request was first sent
    LocalDateTime transactionLogTimestampPurchased = (LocalDateTime)CDLog.get("TimestampPurchased");
    LocalDateTime transactionLogTimestampAllowedUpperBound = timeWhenRequestSent.plusSeconds(MvcControllerIntegTest.REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS);
    LocalDateTime transactionLogTimestampAllowedLowerBound = timeWhenRequestSent.plusSeconds(-MvcControllerIntegTest.REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS);
    assertTrue(transactionLogTimestampPurchased.compareTo(transactionLogTimestampAllowedLowerBound) >= 0 && transactionLogTimestampPurchased.compareTo(transactionLogTimestampAllowedUpperBound) <= 0);

    // verify that the timestampMatured is reasonable
    LocalDateTime transactionLogTimestampMatured = (LocalDateTime)CDLog.get("TimestampMatured");
    LocalDateTime transactionLogTimestampMaturedAllowedLowerBound = timeWhenRequestSent.plusSeconds(MvcControllerIntegTest.SECONDS_IN_YEAR - MvcControllerIntegTest.REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS);
    LocalDateTime transactionLogTimestampMaturedAllowedUpperBound = timeWhenRequestSent.plusSeconds(MvcControllerIntegTest.SECONDS_IN_YEAR + MvcControllerIntegTest.REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS);
    assertTrue(transactionLogTimestampMatured.compareTo(transactionLogTimestampMaturedAllowedLowerBound) >= 0 && transactionLogTimestampMatured.compareTo(transactionLogTimestampMaturedAllowedUpperBound) <= 0);
  }

  // Converts dollar amounts in frontend to penny representation in backend MySQL DB
  public static int convertDollarsToPennies(double dollarAmount) {
    return (int) (dollarAmount * 100);
  }

  // Applies overdraft interest rate to a dollar amount in pennies, and returns an int penny result
  public static int applyOverdraftInterest(int dollarAmountInPennies) {
    return (int) (dollarAmountInPennies * MvcController.INTEREST_RATE);
  }

  // Fetches current local time with no milliseconds because the MySQL DB has granularity only up to seconds (does not use milliseconds)
  public static LocalDateTime fetchCurrentTimeAsLocalDateTimeNoMilliseconds() {
    LocalDateTime currentTimeAsLocalDateTime = convertDateToLocalDateTime(new java.util.Date());
    currentTimeAsLocalDateTime = currentTimeAsLocalDateTime.truncatedTo(ChronoUnit.SECONDS);
    return currentTimeAsLocalDateTime;
  }

  // Converts the java.util.Date object into the LocalDateTime returned by the MySQL DB
  public static LocalDateTime convertDateToLocalDateTime(Date dateToConvert) { 
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
  // Subtracts a year from the input date
  public static Date getOneYearBefore(Date start) {
    return new Date(start.getTime() - MvcControllerIntegTest.MILLISECONDS_IN_YEAR);
  }

  // Adds a year to the input date
  public static Date getOneYearAfter(Date start) {
    return new Date(start.getTime() + MvcControllerIntegTest.MILLISECONDS_IN_YEAR);
  }
}