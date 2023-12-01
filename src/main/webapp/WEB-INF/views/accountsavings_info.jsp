<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>${user.firstName} ${user.lastName} - Testudo Bank Savings</title>
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
		<span>Balance: $</span><span>${user.savingsBalance}</span><br/>
    <span>Withdrawls left:</span><span>${3 - user.numWithdrawls}</span><br/>
    <span>Make a deposit to reset number of withdrawls </span>
   
    <br/>
    <a href='/depositsavings'>Deposit</a>
    <a href='/withdrawsavings'>Withdraw</a>
   
    <a href='/'>Logout</a>
	</div>
</body>
</html>