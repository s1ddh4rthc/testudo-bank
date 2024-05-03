<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Welcome Page</title>
  <style type="text/css">
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
    .buy-crypto {
            margin-left: 100px;
        }
    
  </style>
</head>

<body>
	<div align="center">
		<h2>Welcome to Testudo Bank!</h2>
    <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png" style="float:left;width:100px;height:100px;">
		<a href='/login'>View Account</a> <br/>
    <a href='/deposit'>Deposit</a> <br/>
    <a href='/withdraw'>Withdraw</a> <br/>
    <a href='/dispute'>Dispute</a> <br/>
    <a href='/transfer'>Transfer</a> <br/>
    <a href='/exportinfo'>Export Transactions</a> <br/>
    <a href='/buycrypto' class="buy-crypto">Buy Cryptocurrency</a> 
    <a href='/sellcrypto' class="buy-crypto">Sell Cryptocurrency</a> <br/>

	</div>
</body>

</html>