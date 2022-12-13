package net.testudobank;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ToString(onlyExplicitlyIncluded = true)
public class User {

  //// Password Helper Methods ////
  public static String toHexString(byte[] hashBytes) {
    // Convert byte array into signum representation
    BigInteger number = new BigInteger(1, hashBytes);
    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));
    // Pad with leading zeros
    while (hexString.length() < 64)
    {
        hexString.insert(0, '0');
    }
    return hexString.toString();
  }
  //// General Customer Fields ////

  @Setter @Getter @ToString.Include
	private String username;

  @Getter @ToString.Include
	private String password;

  public void setPassword(String password) {
    try {
      MessageDigest hasher = MessageDigest.getInstance("SHA-256");
      this.password = toHexString(hasher.digest(password.getBytes(StandardCharsets.UTF_8)));
    }
    catch(NoSuchAlgorithmException e) {
      this.password = password;
    }
  }

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

}
