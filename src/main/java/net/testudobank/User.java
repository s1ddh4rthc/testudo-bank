package net.testudobank;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
public class User {
  //// General Customer Fields ////

  @Setter @Getter @ToString.Include
	private String username;

  @Setter @Getter @ToString.Include
	private String password;

  @Setter @Getter
  private String firstName;

  @Setter @Getter
  private String lastName;

  @Setter  @Getter @PositiveOrZero @ToString.Include
	private double balance;

  @Setter @Getter @PositiveOrZero
	private double overDraftBalance;

  @Setter @Getter
	private String logs;

  @Setter @Getter
  private String transactionHist;

  //// Dispute Fields ////

  @Setter @Getter
  private int numTransactionsAgo;

  //// Deposit Fields ////

  @Setter @Getter @Positive
  private double amountToDeposit;

  //// Withdraw Fields ////

  @Setter @Getter @Positive
  private double amountToWithdraw;

  //// Transfer Fields ////

  @Setter @Getter
  private double amountToTransfer;

  @Setter @Getter
  private String transferRecipientID;

  @Setter @Getter
  private boolean isTransfer;

  @Setter @Getter
  private String transferHist;

  //// Crypto Fields ////

  @Setter @Getter
  private boolean isCryptoTransaction;

  @Setter @Getter
  private String cryptoHist;

  @Setter @Getter @Positive
  private double amountToBuyCrypto;

  @Setter @Getter @Positive
  private double amountToSellCrypto;

  @Setter @Getter
  private String whichCryptoToBuy;

  @Setter @Getter
  private double ethPrice;

  @Setter @Getter
  private double solPrice;

  @Setter  @Getter @PositiveOrZero @ToString.Include
  private double ethBalance;

  @Setter  @Getter @PositiveOrZero @ToString.Include
  private double solBalance;

  @Setter  @Getter @PositiveOrZero @ToString.Include
  private double cryptoBalanceUSD;

  //// Interest Fields ////

  @Setter @Getter
  private int numDepositsForInterest;

  //// Monthly Statement Fields ////
  @Setter @Getter @Positive
  private int statementMonth;

  @Setter @Getter @Positive
  private int statementYear;

  @Setter  @Getter @PositiveOrZero
  private double monthlyInterest;
  
  @Setter @Getter
  private List<Map<String, Object>> monthlyTransactionLogs;

  @Setter @Getter
  private List<Map<String, Object>> monthlyCryptoLogs;

  @Setter @Getter
  private List<Map<String, Object>> monthlyTransferLogs;

  @Setter @Getter
  private List<Map<String, Object>> monthlyOverdraftLogs;

  @Setter  @Getter 
  private int netCash;

  @Setter @Getter
  private Map<String, Double> netCryptoFlow;

  // Converts penny representation in backend MySQL DB to formatted dollar amount for frontend
  public static String convertPenniesToDollars(int pennyAmount) {
    String prefix = "$";
    if (pennyAmount < 0) {
      prefix = "-" + prefix;
      pennyAmount *= -1;
    }

    int dollars = pennyAmount / 100;
    int pennies = pennyAmount % 100;

    String dollarsAndCents = String.format("%s%d.%02d", prefix, dollars, pennies);

    return dollarsAndCents;
  }
}
