package net.testudobank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestudoBankRepository {
  public static String getCustomerPassword(JdbcTemplate jdbcTemplate, String customerID) {
    String getCustomerPasswordSql = String.format("SELECT Password FROM Passwords WHERE CustomerID='%s';", customerID);
    String customerPassword = jdbcTemplate.queryForObject(getCustomerPasswordSql, String.class);
    return customerPassword;
  }

  public static void setCustomerPassword(JdbcTemplate jdbcTemplate, String customerPassword, String customerID) {
    String customerPasswordUpdateSql = String.format("UPDATE Passwords SET Password='%s' WHERE CustomerID='%s';", customerPassword, customerID);
    jdbcTemplate.update(customerPasswordUpdateSql);
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

  public static List<Map<String,Object>> getTransferLogs(JdbcTemplate jdbcTemplate, String customerID, int numTransfersToFetch) {
    String getTransferHistorySql = String.format("Select * from TransferHistory WHERE TransferFrom='%s' OR TransferTo='%s' ORDER BY Timestamp DESC LIMIT %d;", customerID, customerID, numTransfersToFetch);
    List<Map<String,Object>> transferLogs = jdbcTemplate.queryForList(getTransferHistorySql);
    return transferLogs;
  }

  public static List<Map<String,Object>> getOverdraftLogs(JdbcTemplate jdbcTemplate, String customerID){
    String getOverDraftLogsSql = String.format("SELECT * FROM OverdraftLogs WHERE CustomerID='%s';", customerID);
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

  public static int getCustomerNumberOfDepositsForInterest(JdbcTemplate jdbcTemplate, String customerID) {
    String getCustomerNumberOfDepositsForInterestSql = String.format("SELECT NumDepositsForInterest FROM Customers WHERE CustomerID='%s';", customerID);
    int numberOfDepositsForInterest = jdbcTemplate.queryForObject(getCustomerNumberOfDepositsForInterestSql, Integer.class);
    return numberOfDepositsForInterest;
  }

  public static String getSecurityAnswer1(JdbcTemplate jdbcTemplate, String customerID) {
    String getSecurityAnswer1Sql = String.format("SELECT SecurityAnswer1 FROM Passwords WHERE CustomerID='%s';", customerID);
    String securityAnswer1 = jdbcTemplate.queryForObject(getSecurityAnswer1Sql, String.class);
    return securityAnswer1;
  }

  public static String getSecurityAnswer2(JdbcTemplate jdbcTemplate, String customerID) {
    String getSecurityAnswer2Sql = String.format("SELECT SecurityAnswer2 FROM Passwords WHERE CustomerID='%s';", customerID);
    String securityAnswer2 = jdbcTemplate.queryForObject(getSecurityAnswer2Sql, String.class);
    return securityAnswer2;
  }

  public static String getSecurityAnswer3(JdbcTemplate jdbcTemplate, String customerID) {
    String getSecurityAnswer3Sql = String.format("SELECT SecurityAnswer3 FROM Passwords WHERE CustomerID='%s';", customerID);
    String securityAnswer3 = jdbcTemplate.queryForObject(getSecurityAnswer3Sql, String.class);
    return securityAnswer3;
  }

  public static void setSecurityAnswer1(JdbcTemplate jdbcTemplate, String securityAnswer1, String customerID) {
    String securityAnswer1UpdateSql = String.format("UPDATE Passwords SET SecurityAnswer1='%s' WHERE CustomerID='%s';", securityAnswer1, customerID);
    jdbcTemplate.update(securityAnswer1UpdateSql);
  }

  public static void setSecurityAnswer2(JdbcTemplate jdbcTemplate, String securityAnswer2, String customerID) {
    String securityAnswer2UpdateSql = String.format("UPDATE Passwords SET SecurityAnswer2='%s' WHERE CustomerID='%s';", securityAnswer2, customerID);
    jdbcTemplate.update(securityAnswer2UpdateSql);
  }

  public static void setSecurityAnswer3(JdbcTemplate jdbcTemplate, String securityAnswer3, String customerID) {
    String securityAnswer3UpdateSql = String.format("UPDATE Passwords SET SecurityAnswer3='%s' WHERE CustomerID='%s';", securityAnswer3, customerID);
    jdbcTemplate.update(securityAnswer3UpdateSql);
  }

  public static int getResetPasswordDay(JdbcTemplate jdbcTemplate, String customerID) {
    String getResetPasswordDaySql = String.format("SELECT ResetPasswordDay FROM Customers WHERE CustomerID='%s';", customerID);
    int resetPasswordDay = jdbcTemplate.queryForObject(getResetPasswordDaySql, Integer.class);
    return resetPasswordDay;
  }

  public static void setResetPasswordDay(JdbcTemplate jdbcTemplate, int resetPasswordDay, String customerID) {
    String resetPasswordDayUpdateSql = String.format("UPDATE Customers SET ResetPasswordDay=%d WHERE CustomerID='%s';", resetPasswordDay, customerID);
    jdbcTemplate.update(resetPasswordDayUpdateSql);
  }

  public static String getNewPasswordForReset(JdbcTemplate jdbcTemplate, String customerID) {
    String getnewPasswordForResetSql = String.format("SELECT NewPasswordForReset FROM Passwords WHERE CustomerID='%s';", customerID);
    String newPasswordForReset = jdbcTemplate.queryForObject(getnewPasswordForResetSql, String.class);
    return newPasswordForReset;
  }

  public static void setNewPasswordForReset(JdbcTemplate jdbcTemplate, String newPasswordForReset, String customerID) {
    String newPasswordForResetUpdateSql = String.format("UPDATE Passwords SET NewPasswordForReset='%s' WHERE CustomerID='%s';", newPasswordForReset, customerID);
    jdbcTemplate.update(newPasswordForResetUpdateSql);
  }

  public static int getPasswordAttempts(JdbcTemplate jdbcTemplate, String customerID) {
    String getPasswordAttemptsSql = String.format("SELECT PasswordAttempts FROM Passwords WHERE CustomerID='%s';", customerID);
    int passwordAttempts = jdbcTemplate.queryForObject(getPasswordAttemptsSql, Integer.class);
    return passwordAttempts;
  }

  public static void setPasswordAttempts(JdbcTemplate jdbcTemplate, int passwordAttempts, String customerID) {
    String passwordAttemptsUpdateSql = String.format("UPDATE Passwords SET PasswordAttempts=%d WHERE CustomerID='%s';", passwordAttempts, customerID);
    jdbcTemplate.update(passwordAttemptsUpdateSql);
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
}
