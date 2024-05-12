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

  public static void setCustomerNumberOfDepositsForInterest(JdbcTemplate jdbcTemplate, String customerID, int numDepositsForInterest) { 
    String customerInterestDepositsSql = String.format("UPDATE Customers SET NumDepositsForInterest = %d WHERE CustomerID='%s';", numDepositsForInterest, customerID);
    jdbcTemplate.update(customerInterestDepositsSql);
  }

  public static void insertRowToTransactionHistoryTable(JdbcTemplate jdbcTemplate, String customerID, String timestamp, String action, int amtInPennies, int roundup) {
    String insertRowToTransactionHistorySql = "INSERT INTO TransactionHistory (CustomerID, Timestamp, Action, Amount, RoundupAmount) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(insertRowToTransactionHistorySql, customerID, timestamp, action, amtInPennies, roundup);
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
/*
 public static void logRoundupTransaction(JdbcTemplate jdbcTemplate, String customerID, String timestamp, int roundupAmountInPennies) {
    String action = "Roundup";
    // Assuming you want to log the transaction with zero as the primary transaction amount
    // and the roundup amount as the roundup figure.
    String sql = "INSERT INTO TransactionHistory (CustomerID, Timestamp, Action, Amount, RoundupAmount) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, customerID, timestamp, action, 0, roundupAmountInPennies);
}
 */

 public static void applyRoundup(JdbcTemplate jdbcTemplate, String customerID, int roundupAmountInPennies) {
  int overdraftBalanceInPennies = getCustomerOverdraftBalanceInPennies(jdbcTemplate, customerID);

  if (overdraftBalanceInPennies > 0) {
      if (roundupAmountInPennies > overdraftBalanceInPennies) {
          int excess = roundupAmountInPennies - overdraftBalanceInPennies;
          // Clear the overdraft with the exact required amount
          decreaseCustomerOverdraftBalance(jdbcTemplate, customerID, overdraftBalanceInPennies);
          // Any excess from the roundup goes to the customer's roundup balance
          updateRoundupBalance(jdbcTemplate, customerID, excess);
      } else {
          // Deduct the roundup amount directly from the overdraft
          decreaseCustomerOverdraftBalance(jdbcTemplate, customerID, roundupAmountInPennies);
      }
  } else {
      // If no overdraft, proceed as normal
      updateRoundupBalance(jdbcTemplate, customerID, roundupAmountInPennies);
  }
}


public static void decreaseCustomerOverdraftBalance(JdbcTemplate jdbcTemplate, String customerID, int decreaseAmtInPennies) {
  String sql = "UPDATE Customers SET OverdraftBalance = OverdraftBalance - ? WHERE CustomerID = ?";
  jdbcTemplate.update(sql, decreaseAmtInPennies, customerID);
}


public static void updateRoundupBalance(JdbcTemplate jdbcTemplate, String customerID, int increaseAmtInPennies) {
    String sql = "UPDATE Customers SET RoundupBalance = RoundupBalance + ? WHERE CustomerID = ?";
    jdbcTemplate.update(sql, increaseAmtInPennies, customerID);
}



  
  public static void setRoundupEnabled(JdbcTemplate jdbcTemplate, String customerID, boolean enabled) {
    String sql = "UPDATE Customers SET RoundupEnabled = ? WHERE CustomerID = ?";
    jdbcTemplate.update(sql, enabled, customerID);
  }


  public static int getCustomerRoundupBalance(JdbcTemplate jdbcTemplate, String customerID) {
    String sql = "SELECT RoundupBalance FROM Customers WHERE CustomerID = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{customerID}, Integer.class);
}

public static void setCustomerRoundupBalance(JdbcTemplate jdbcTemplate, String customerID, int newBalanceInPennies) {
    String sql = "UPDATE Customers SET RoundupBalance = ? WHERE CustomerID = ?";
    jdbcTemplate.update(sql, newBalanceInPennies, customerID);
}




}
