package net.testudobank;

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

  @Setter @Getter
  private int numDepositsForInterest;

  // Add these fields to the User class
  private String userId;
  private String transactionId;
  private double transactionAmount;
  private int transactionAgeInDays;
  private String disputeReason;

  // Add getters for the new fields
  public String getUserId() {
      return userId;
  }

  public void setUserId(String userId) {
      this.userId = userId;
  }

  public String getTransactionId() {
      return transactionId;
  }

  public void setTransactionId(String transactionId) {
      this.transactionId = transactionId;
  }

  public double getTransactionAmount() {
      return transactionAmount;
  }

  public void setTransactionAmount(double transactionAmount) {
      this.transactionAmount = transactionAmount;
  }

  public int getTransactionAgeInDays() {
      return transactionAgeInDays;
  }

  public void setTransactionAgeInDays(int transactionAgeInDays) {
      this.transactionAgeInDays = transactionAgeInDays;
  }

  public String getDisputeReason() {
    return disputeReason;
  }

  public void setDisputeReason(String disputeReason) {
    this.disputeReason = disputeReason;
  }
}
