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
	<div align="center">
		<h2>Welcome to Dank Bank!</h2>
        <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png" style="float:left;width:100px;height:100px;">
		<a href='/login'>View Account</a> <br/>
    <a href='/deposit'>Deposit</a> <br/>
    <a href='/withdraw'>Withdraw</a> <br/>
    <a href='/dispute'>Dispute</a> <br/>
    <a href='/transfer'>Transfer</a> <br/>
    <a href='/buycrypto'>Buy Cryptocurrency</a>
    <a href='/sellcrypto'>Sell Cryptocurrency</a> <br/>
    

	</div>
</body>
<div style="right:0; bottom:50%; width:200px; margin:0 10px;">
  <img src="advertise.png"  />
  <a href='/contact'>Buy this space</a>
</div>
<div style="position:fixed; bottom:0; left:50%; right:0; width:600px; margin:0 10px;">
  <img src="advertise.png"  />
  <a href='/contact'>Buy this space</a>
</div>

</html>