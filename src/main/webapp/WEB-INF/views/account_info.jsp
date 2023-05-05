<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <link
      rel="icon"
      href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png"
    />
    <meta charset="ISO-8859-1" />
    <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
    <style type="text/css">
      label {
        display: inline-block;
        width: 200px;
        margin: 5px;
        text-align: left;
      }
      button {
        padding: 10px;
        margin: 10px;
      }
      a.button {
        -webkit-appearance: button;
        -moz-appearance: button;
        appearance: button;

        text-decoration: none;
        color: initial;
      }
    </style>
  </head>
  <body>
    <div align="center">
      <h2>
        <span>${user.firstName}</span> <span>${user.lastName}</span> Bank
        Account Info
      </h2>
      <span>Username: </span><span>${user.username}</span><br />
      <span>Balance: $</span><span>${user.balance}</span><br />
      <span>Overdraft Balance: $</span><span>${user.overDraftBalance}</span
      ><br />
      <span>Crypto Balance in USD: $</span><span>${user.cryptoBalanceUSD}</span
      ><br />
      <span>Ethereum Coins Owned: </span><span>${user.ethBalance}</span><br />
      <span>Solana Coins Owned: </span><span>${user.solBalance}</span><br />
      <span>Current $ETH Price: </span><span>${user.ethPrice}</span><br />
      <span>Current $SOL Price: </span><span>${user.solPrice}</span><br />
      <span>Stock Balance in USD: $</span><span>${user.stockBalanceUSD}</span
      ><br />
      <span>Apple Shares Owned: </span><span>${user.aaplBalance}</span><br />
      <span>Google Shares Owned: </span><span>${user.googBalance}</span><br />
      <span>Microsoft Shares Owned: </span><span>${user.msftBalance}</span
      ><br />
      <span>Current $AAPL Price: </span><span>${user.aaplPrice}</span><br />
      <span>Current $GOOG Price: </span><span>${user.googPrice}</span><br />
      <span>Current $MSFT Price: </span><span>${user.msftPrice}</span><br />
      <span>Re-payment logs: </span><span>${user.logs}</span><br />
      <span>Transaction History: </span><span>${user.transactionHist}</span
      ><br />
      <span>Transfer History: </span><span>${user.transferHist}</span><br />
      <span>Crypto History: </span><span>${user.cryptoHist}</span><br />
      <span>Stock History: </span><span>${user.stockHist}</span><br />
      <br />
      <a href="/deposit">Deposit</a>
      <a href="/withdraw">Withdraw</a>
      <a href="/dispute">Dispute</a>
      <a href="/transfer">Transfer</a>
      <a href="/">Logout</a>
    </div>
  </body>
</html>
