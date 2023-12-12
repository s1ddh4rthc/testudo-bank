<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <link rel="stylesheet" type="text/css" href="https://panko2128.github.io/tbcss/styles.css">
  <meta charset="ISO-8859-1">
  <title>Welcome Page</title>
  <style type="text/css">
    label {
      display: inline-block;
      width: 200px;
      margin: 5px;
      text-align: left;
    }
    input[type=text], input[type=password], select {
      width: 200px;	
    }
    input[type=radio] {
      display: inline-block;
      margin-left: 45px;
    }
    
    input[type=checkbox] {
      display: inline-block;
      margin-right: 190px;
    }	
    
    button {
      padding: 10px;
      margin: 10px;
    }
  </style>
</head>

<body>
	<div class="welcome-banner">
		<h2>Welcome to Testudo Bank!</h2>
    <div>
      <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png" style="width:100px;height:100px;">
    </div>

		
	</div>
  <br>
  <div class="centered-buttons">
    <a class="nav-button" href='/login'>View Account</a> <br />
    <a class="nav-button" href='/deposit'>Deposit</a> <br />
    <a class="nav-button" href='/withdraw'>Withdraw</a> <br />
    <a class="nav-button" href='/dispute'>Dispute</a> <br />
    <a class="nav-button" href='/transfer'>Transfer</a> <br />
    <a class="nav-button" href='/buycrypto'>Buy Cryptocurrency</a> <br>
    <a class="nav-button" href='/sellcrypto'>Sell Cryptocurrency</a> <br />
  </div>
</body>

</html>