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
    <span>Crypto Balance in USD: $</span><span>${user.cryptoBalanceUSD}</span><br/>
    <span>Ethereum Coins Owned: </span><span>${user.ethBalance}</span><br/>
    <span>Solana Coins Owned: </span><span>${user.solBalance}</span><br/>
    <span>Current $ETH Price: </span><span>${user.ethPrice}</span><br/>
    <span>Current $SOL Price: </span><span>${user.solPrice}</span><br/>
    <span>Re-payment logs: </span><span>${user.logs}</span><br/>
    <span>Transaction History: </span><span>${user.transactionHist}</span><br/>
    <span>Transfer History: </span><span>${user.transferHist}</span><br/>
    <span>Crypto History: </span><span>${user.cryptoHist}</span><br/>
    
    <br>
    <form method="post" action="/downloadreport">
      <input type="hidden" name="username" value="${user.username}">
      <select name="month" required>
        <option value="" disabled selected>Select Month</option>
        <option value="01">January</option>
        <option value="02">February</option>
        <option value="03">March</option>
        <option value="04">April</option>
        <option value="05">May</option>
        <option value="06">June</option>
        <option value="07">July</option>
        <option value="08">August</option>
        <option value="09">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
      </select>
      <button type="submit">Download Summary Report</button>
    </form>
    <br>

    <a href='/deposit'>Deposit</a>
    <a href='/withdraw'>Withdraw</a>
    <a href='/dispute'>Dispute</a>
    <a href='/transfer'>Transfer</a>
    <a href='/'>Logout</a>
	</div>
</body>
</html>