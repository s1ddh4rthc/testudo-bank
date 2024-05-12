<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
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
        <h2><span>${user.firstName}</span> <span>${user.lastName}</span> Bank Account Info</h2>
        <span>Username: </span><span>${user.username}</span><br/>
        <span>Balance: $</span><span>${user.balance}</span><br/>
        <span>Overdraft Balance: $</span><span>${user.overDraftBalance}</span><br/>
        <span>Roundup Savings: $</span><span>${user.roundupBalance}</span><br/> <!-- Displaying Roundup Balance -->
        <span>Crypto Balance in USD: $</span><span>${user.cryptoBalanceUSD}</span><br/>
        <span>Ethereum Coins Owned: </span><span>${user.ethBalance}</span><br/>
        <span>Solana Coins Owned: </span><span>${user.solBalance}</span><br/>
        <span>Current $ETH Price: </span><span>${user.ethPrice}</span><br/>
        <span>Current $SOL Price: </span><span>${user.solPrice}</span><br/>
        <span>Re-payment logs: </span><span>${user.logs}</span><br/>
        <span>Transaction History: </span><span>${user.transactionHist}</span><br/>
        <span>Transfer History: </span><span>${user.transferHist}</span><br/>
        <span>Crypto History: </span><span>${user.cryptoHist}</span><br/>
        <br/>
        <!-- Toggle Roundup Feature -->
        <form action="/enableRoundup" method="post">
          <input type="hidden" name="username" value="${user.username}">
          <button type="submit">Enable Roundup</button>
      </form>
      <form action="/disableRoundup" method="post">
          <input type="hidden" name="username" value="${user.username}">
          <button type="submit">Disable Roundup</button>
      </form>
      <form action="/transferRoundupToMain" method="post">
        <input type="hidden" name="username" value="${user.username}">
        <button type="submit">Transfer Roundup to Main</button>
    </form>
    <br/>
      
    
      
        <a href='/deposit'>Deposit</a>
        <a href='/withdraw'>Withdraw</a>
        <a href='/dispute'>Dispute</a>
        <a href='/transfer'>Transfer</a>
        <a href='/'>Logout</a>
    </div>
</body>
</html>
