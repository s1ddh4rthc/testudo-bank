package net.testudobank;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestudoBankRepository {
  private static final List<String> VALID_CRYPTOCURRENCIES = Arrays.asList("ETH", "SOL" );
  
  public static String getCustomerPassword(JdbcTemplate jdbcTemplate, String customerID) {
    String getCustomerPasswordSql = String.format("SELECT Password FROM Passwords WHERE CustomerID='%s';", customerID);
    String customerPassword = jdbcTemplate.queryForObject(getCustomerPasswordSql, String.class);
    return customerPassword;
  }

  public static int getCustomerNumberOfReversals(JdbcTemplate jdbcTemplate, String customerID) {
    String getNumberOfReversalsSql = String.format("SELECT NumFraudReversals FROM Customers WHERE CustomerID='%s';", customerID);
    int numOfReversals = jdbcTemplate.queryForObject(getNumberOfReversalsSql, Integer.class);
    return numOfReversals;
  }

  public static int getCustomerCashBalanceInPennies(JdbcTemplate jdbcTemplate, String customerID) {
    String getUserBalanceSql =  String.format("SELECT Balance FROM Customers WHERE CustomerID='%s';", customerID);
    int userBalanceInPennies = jdbcTemplate.queryForObject(getUserBalanceSql, Integer.class);
    return userBalanceInPennies;
  }

  public static Optional<Double> getCustomerCryptoBalance(JdbcTemplate jdbcTemplate, String customerID, String cryptoName) {
    String getUserCryptoBalanceSql = "SELECT CryptoAmount FROM CryptoHoldings WHERE CustomerID= ? AND CryptoName= ?;";

    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(getUserCryptoBalanceSql, BigDecimal.class, customerID, cryptoName)).map(BigDecimal::doubleValue);
    } catch (EmptyResultDataAccessException ignored) {
      // user may not have crypto row yet
      return Optional.empty();
    }

  }

  public static int getCustomerOverdraftBalanceInPennies(JdbcTemplate jdbcTemplate, String customerID) {
    String getUserOverdraftBalanceSql = String.format("SELECT OverdraftBalance FROM Customers WHERE CustomerID='%s';", customerID);
    int userOverdraftBalanceInPennies = jdbcTemplate.queryForObject(getUserOverdraftBalanceSql, Integer.class);
    return userOverdraftBalanceInPennies;
  }

  public static List<Map<String,Object>> getRecentTransactions(JdbcTemplate jdbcTemplate, String customerID, int numTransactionsToFetch) {
    String getTransactionHistorySql = String.format("Select * from TransactionHistory WHERE CustomerId='%s' ORDER BY Timestamp DESC LIMIT %d;", customerID, numTransactionsToFetch);
    List<Map<String,Object>> transactionLogs = jdbcTemplate.queryForList(getTransactionHistorySql);
    return transactionLogs;
  }
  
  public static List<Map<String,Object>> getTransactionsByMonth(JdbcTemplate jdbcTemplate, String customerID, int year, int month) {
    String getTransactionHistorySql = String.format("Select * from TransactionHistory WHERE CustomerId='%s' AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d ORDER BY Timestamp;", customerID, year, month);
    List<Map<String,Object>> transactionLogs = jdbcTemplate.queryForList(getTransactionHistorySql);
    return transactionLogs;
  }

  public static List<Map<String,Object>> getTransferLogs(JdbcTemplate jdbcTemplate, String customerID, int numTransfersToFetch) {
    String getTransferHistorySql = String.format("Select * from TransferHistory WHERE TransferFrom='%s' OR TransferTo='%s' ORDER BY Timestamp DESC LIMIT %d;", customerID, customerID, numTransfersToFetch);
    List<Map<String,Object>> transferLogs = jdbcTemplate.queryForList(getTransferHistorySql);
    return transferLogs;
  }

  public static List<Map<String,Object>> getTransferLogsByMonth(JdbcTemplate jdbcTemplate, String customerID, int year, int month) {
    String getTransferHistorySql = String.format("Select * from TransferHistory WHERE (TransferFrom='%s' OR TransferTo='%s') AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d ORDER BY Timestamp;", customerID, customerID, year, month);
    List<Map<String,Object>> transferLogs = jdbcTemplate.queryForList(getTransferHistorySql);
    return transferLogs;
  }

  public static List<Map<String,Object>> getOverdraftLogs(JdbcTemplate jdbcTemplate, String customerID){
    String getOverDraftLogsSql = String.format("SELECT * FROM OverdraftLogs WHERE CustomerID='%s';", customerID);
    List<Map<String,Object>> overdraftLogs = jdbcTemplate.queryForList(getOverDraftLogsSql);
    return overdraftLogs;
  }

  public static List<Map<String,Object>> getOverdraftLogsByMonth(JdbcTemplate jdbcTemplate, String customerID, int year, int month){
    String getOverDraftLogsSql = String.format("SELECT * FROM OverdraftLogs WHERE CustomerID='%s' AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d;", customerID, year, month);
    List<Map<String,Object>> overdraftLogs = jdbcTemplate.queryForList(getOverDraftLogsSql);
    return overdraftLogs;
  }
  
  public static List<Map<String,Object>> getOverdraftLogs(JdbcTemplate jdbcTemplate, String customerID, String timestamp){
    String getOverDraftLogsSql = String.format("SELECT * FROM OverdraftLogs WHERE CustomerID='%s' AND Timestamp='%s';", customerID, timestamp);
    List<Map<String,Object>> overdraftLogs = jdbcTemplate.queryForList(getOverDraftLogsSql);
    return overdraftLogs;
  }

  public static List<Map<String,Object>> getCryptoLogs(JdbcTemplate jdbcTemplate, String customerID) {
    String getTransferHistorySql = "Select * from CryptoHistory WHERE CustomerID=? ORDER BY Timestamp DESC";
    return jdbcTemplate.queryForList(getTransferHistorySql, customerID);
  }
  
  public static List<Map<String,Object>> getCryptoLogsByMonth(JdbcTemplate jdbcTemplate, String customerID, int year, int month) {
    String getTransferHistorySql = String.format("Select * from CryptoHistory WHERE CustomerID=?  AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d ORDER BY Timestamp DESC", year, month);
    return jdbcTemplate.queryForList(getTransferHistorySql, customerID);
  }

  public static int getCustomerNumberOfDepositsForInterest(JdbcTemplate jdbcTemplate, String customerID) {
    String getCustomerNumberOfDepositsForInterestSql = String.format("SELECT NumDepositsForInterest FROM Customers WHERE CustomerID='%s';", customerID);
    int numberOfDepositsForInterest = jdbcTemplate.queryForObject(getCustomerNumberOfDepositsForInterestSql, Integer.class);
    return numberOfDepositsForInterest;
  }

  public static void setCustomerNumberOfDepositsForInterest(JdbcTemplate jdbcTemplate, String customerID, int numDepositsForInterest) { 
    String customerInterestDepositsSql = String.format("UPDATE Customers SET NumDepositsForInterest = %d WHERE CustomerID='%s';", numDepositsForInterest, customerID);
    jdbcTemplate.update(customerInterestDepositsSql);
  }

  public static void insertRowToTransactionHistoryTable(JdbcTemplate jdbcTemplate, String customerID, String timestamp, String action, int amtInPennies) {
    String insertRowToTransactionHistorySql = String.format("INSERT INTO TransactionHistory VALUES ('%s', '%s', '%s', %d);",
                                                              customerID,
                                                              timestamp,
                                                              action,
                                                              amtInPennies);
    jdbcTemplate.update(insertRowToTransactionHistorySql);
  }

  public static void insertRowToOverdraftLogsTable(JdbcTemplate jdbcTemplate, String customerID, String timestamp, int depositAmtIntPennies, int oldOverdraftBalanceInPennies, int newOverdraftBalanceInPennies) {
    String insertRowToOverdraftLogsSql = String.format("INSERT INTO OverdraftLogs VALUES ('%s', '%s', %d, %d, %d);", 
                                                        customerID,
                                                        timestamp,
                                                        depositAmtIntPennies,
                                                        oldOverdraftBalanceInPennies,
                                                        newOverdraftBalanceInPennies);
    jdbcTemplate.update(insertRowToOverdraftLogsSql);
  }

  public static void setCustomerNumFraudReversals(JdbcTemplate jdbcTemplate, String customerID, int newNumFraudReversals) {
    String numOfReversalsUpdateSql = String.format("UPDATE Customers SET NumFraudReversals = %d WHERE CustomerID='%s';", newNumFraudReversals, customerID);
    jdbcTemplate.update(numOfReversalsUpdateSql);
  }

  public static void setCustomerOverdraftBalance(JdbcTemplate jdbcTemplate, String customerID, int newOverdraftBalanceInPennies) {
    String overdraftBalanceUpdateSql = String.format("UPDATE Customers SET OverdraftBalance = %d WHERE CustomerID='%s';", newOverdraftBalanceInPennies, customerID);
    jdbcTemplate.update(overdraftBalanceUpdateSql);
  }

  public static void increaseCustomerOverdraftBalance(JdbcTemplate jdbcTemplate, String customerID, int increaseAmtInPennies) {
    String overdraftBalanceIncreaseSql = String.format("UPDATE Customers SET OverdraftBalance = OverdraftBalance + %d WHERE CustomerID='%s';", increaseAmtInPennies, customerID);
    jdbcTemplate.update(overdraftBalanceIncreaseSql);
  }

  public static void setCustomerCashBalance(JdbcTemplate jdbcTemplate, String customerID, int newBalanceInPennies) {
    String updateBalanceSql = String.format("UPDATE Customers SET Balance = %d WHERE CustomerID='%s';", newBalanceInPennies, customerID);
    jdbcTemplate.update(updateBalanceSql);
  }

  public static void increaseCustomerCashBalance(JdbcTemplate jdbcTemplate, String customerID, int increaseAmtInPennies) {
    String balanceIncreaseSql = String.format("UPDATE Customers SET Balance = Balance + %d WHERE CustomerID='%s';", increaseAmtInPennies, customerID);
    jdbcTemplate.update(balanceIncreaseSql);
  }

  public static void initCustomerCryptoBalance(JdbcTemplate jdbcTemplate, String customerID, String cryptoName) {
    // TODO: this currently does not check if row with customerID and cryptoName already exists, and can create a duplicate row!
    String balanceInitSql = "INSERT INTO CryptoHoldings (CryptoAmount,CustomerID,CryptoName) VALUES (0, ? , ? )";
    jdbcTemplate.update(balanceInitSql, customerID, cryptoName);
  }

  public static void increaseCustomerCryptoBalance(JdbcTemplate jdbcTemplate, String customerID, String cryptoName, double increaseAmt) {
    String balanceIncreaseSql = "UPDATE CryptoHoldings SET CryptoAmount = CryptoAmount + ? WHERE CustomerID= ? AND CryptoName= ?";
    jdbcTemplate.update(balanceIncreaseSql, increaseAmt, customerID, cryptoName);
  }

  public static void decreaseCustomerCashBalance(JdbcTemplate jdbcTemplate, String customerID, int decreaseAmtInPennies) {
    String balanceDecreaseSql = String.format("UPDATE Customers SET Balance = Balance - %d WHERE CustomerID='%s';", decreaseAmtInPennies, customerID);
    jdbcTemplate.update(balanceDecreaseSql);
  }

  public static void decreaseCustomerCryptoBalance(JdbcTemplate jdbcTemplate, String customerID, String cryptoName, double decreaseAmt) {
    String balanceDecreaseSql = "UPDATE CryptoHoldings SET CryptoAmount = CryptoAmount - ? WHERE CustomerID= ? AND CryptoName= ?";
    jdbcTemplate.update(balanceDecreaseSql, decreaseAmt, customerID, cryptoName);
  }

  public static void deleteRowFromOverdraftLogsTable(JdbcTemplate jdbcTemplate, String customerID, String timestamp) {
    String deleteRowFromOverdraftLogsSql = String.format("DELETE from OverdraftLogs where CustomerID='%s' AND Timestamp='%s';", customerID, timestamp);
    jdbcTemplate.update(deleteRowFromOverdraftLogsSql);
  }

  public static void insertRowToTransferLogsTable(JdbcTemplate jdbcTemplate, String customerID, String recipientID, String timestamp, int transferAmount) {
    String transferHistoryToSql = String.format("INSERT INTO TransferHistory VALUES ('%s', '%s', '%s', %d);",
                                                    customerID,
                                                    recipientID,
                                                    timestamp,
                                                    transferAmount);
    jdbcTemplate.update(transferHistoryToSql);
  }

  public static void insertRowToCryptoLogsTable(JdbcTemplate jdbcTemplate, String customerID, String cryptoName, String action, String timestamp, double cryptoAmount) {
    String cryptoHistorySql = "INSERT INTO CryptoHistory (CustomerID, Timestamp, Action, CryptoName, CryptoAmount) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(cryptoHistorySql, customerID, timestamp, action, cryptoName, cryptoAmount);
  }
  
  public static boolean doesCustomerExist(JdbcTemplate jdbcTemplate, String customerID) { 
    String getCustomerIDSql =  String.format("SELECT CustomerID FROM Customers WHERE CustomerID='%s';", customerID);
    if (jdbcTemplate.queryForObject(getCustomerIDSql, String.class) != null) {
     return true;
    } else {
      return false;
    }
  }
  
  public static int getNetCashFlowInMonthInPennies(JdbcTemplate jdbcTemplate, String customerID, int year, int month) {
    String getPositiveCashFlowInMonthSql = String.format(
        "SELECT SUM(Amount) FROM transactionhistory WHERE CustomerID = '%s' AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d AND Action IN ('Deposit', 'Interest', 'CryptoSell', 'TransferReceive')",
        customerID, year, month);
    String getNegativeCashFlowInMonthSql = String.format(
        "SELECT SUM(Amount) FROM transactionhistory WHERE CustomerID = '%s' AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d AND Action IN ('Withdraw', 'CryptoBuy', 'TransferSend')",
        customerID, year, month);

    Optional<Integer> positiveCashFlowResult = Optional.ofNullable(jdbcTemplate.queryForObject(getPositiveCashFlowInMonthSql, Integer.class));
    Optional<Integer> negativeCashFlowResult = Optional.ofNullable(jdbcTemplate.queryForObject(getNegativeCashFlowInMonthSql, Integer.class));
    int positiveCashFlow = positiveCashFlowResult.orElse(0);
    int negativeCashFlow = negativeCashFlowResult.orElse(0);

    return positiveCashFlow - negativeCashFlow;
  }
  
  public static int getInterestInMonthInPennies(JdbcTemplate jdbcTemplate, String customerID, int year, int month) {
    String getInterestInMonthSql = String.format(
        "SELECT SUM(Amount) FROM transactionhistory WHERE CustomerID = '%s' AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d AND Action='Interest'",
        customerID, year, month);

    Optional<Integer> accruedInterestResult = Optional.ofNullable(jdbcTemplate.queryForObject(getInterestInMonthSql, Integer.class));
    int accruedInterest = accruedInterestResult.orElse(0);

    return accruedInterest;
  }

  public static Map<String, Double> getNetCryptoInMonth(JdbcTemplate jdbcTemplate, String customerID, int year, int month) {
    Map<String, Double> netCryptoFlow = new LinkedHashMap<>();
    
    for (String currency : VALID_CRYPTOCURRENCIES) {
      String getCryptoBuyInMonthSql = String.format(
        "SELECT SUM(CryptoAmount) FROM cryptohistory WHERE CustomerID = '%s' AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d AND CryptoName = '%s' AND Action='Buy'",
          customerID, year, month, currency);
        String getCryptoSellInMonthSql = String.format(
        "SELECT SUM(CryptoAmount) FROM cryptohistory WHERE CustomerID = '%s' AND YEAR(Timestamp) = %d AND MONTH(Timestamp) = %d AND CryptoName = '%s' AND Action='Sell'",
            customerID, year, month, currency);
        
        Optional<Double> positiveCryptoFlowResult = Optional
            .ofNullable(jdbcTemplate.queryForObject(getCryptoBuyInMonthSql, BigDecimal.class)).map(BigDecimal::doubleValue);
        Optional<Double> negativeCryptoFlowResult = Optional
            .ofNullable(jdbcTemplate.queryForObject(getCryptoSellInMonthSql, BigDecimal.class)).map(BigDecimal::doubleValue);
      
        Double netCryptoFlowResult = positiveCryptoFlowResult.orElse(0.0) - negativeCryptoFlowResult.orElse(0.0);
        netCryptoFlow.put(currency, netCryptoFlowResult);
    }

    return netCryptoFlow;
  }
}
