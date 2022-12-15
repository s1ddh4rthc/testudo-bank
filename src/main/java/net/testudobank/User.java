package net.testudobank;
import java.util.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
public class User {
  private final static String HTML_LINE_BREAK = "<br/>";
  private final static String HTML_BEGIN_RED_FONT = "<p style=\"background-color:red\">";
  private final static String HTML_END_RED_FONT = "</p>";

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

  //// Create Sub Account Fields ////
  @Setter @Getter @ToString.Include
  private String newCustomerFirstName;

  @Setter @Getter @ToString.Include
  private String newCustomerLastName;

  @Setter @Getter @ToString.Include
  private String newCustomerPassword;

  @Setter @Getter @ToString.Include
  private String newCustomerID;

  @Setter @Getter @ToString.Include
  private String stringListOfSubAccounts;

  @Setter @Getter @ToString.Include
  private List<Map<String,Object>> listOfSubAccounts;

  @Setter @Getter @ToString.Include
  private List<Customer> listOfSubAccountCustomers;

  @Setter @Getter
  private double newCustomerMinimumBalanceInDollars;
  

  /*
   * Object to store first and last name of new customer
   * to make it easier to store in the mapping.
   */
  public class FullName {
    private String firstName;
    private String lastName;
    public FullName(String firstName, String lastName) {
      firstName = this.firstName;
      lastName = this.lastName;
    }
  }

  private Map <String, String> subAccountLoginInfoMap;
  private Map <String, FullName> subAccountNamesMap;

  /*
   * Creates new sub account mappings for all users.
   */
  public User() {
    subAccountLoginInfoMap = new HashMap <String, String> ();
    subAccountNamesMap = new HashMap <String, FullName> ();
  }

  /*
   * Saves current new customer name and password.
   */
  public void linkSubAccounts(List<Customer> subAccounts) {
    for (Customer subAccount : subAccounts) {
      FullName subAccountCustomerName = new FullName(subAccount.getFirstName(), subAccount.getLastName());
      subAccountLoginInfoMap.put(subAccount.getCustomerID(), subAccount.getPassword());
      subAccountNamesMap.put(subAccount.getCustomerID(), subAccountCustomerName);
    }
  }

  /*
   * Returns a String containing listed linked sub accounts.
   */
  public String showSubAccounts() {
    String toReturn = HTML_LINE_BREAK;
    if (listOfSubAccountCustomers != null) {
      for (Customer subAccount : listOfSubAccountCustomers) {
        if (subAccount.getBalanceInPennies() >= subAccount.getMinimumBalanceInPennies()) {
          toReturn += String.format("Sub Account %s | %s %s | Minimum Balance: %.2f | Balance: $%.2f ", subAccount.getCustomerID(), subAccount.getFirstName(), subAccount.getLastName(), subAccount.getMinimumBalanceInDollars(), subAccount.getBalanceInDollars());
        }
        else {
          toReturn += String.format("Sub Account %s | %s %s | Minimum Balance: %.2f | Balance: ", subAccount.getCustomerID(), subAccount.getFirstName(), subAccount.getLastName(), subAccount.getMinimumBalanceInDollars());
          toReturn += HTML_BEGIN_RED_FONT + String.format("$%.2f", subAccount.getBalanceInDollars()) + HTML_END_RED_FONT;
        }
        toReturn += HTML_LINE_BREAK;
      }
    }
    return toReturn;
  }

}
