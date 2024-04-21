<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Purchase Certificate Of Deposit Form</title>
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
		<form:form action="purchaseCD" method="post" modelAttribute="user">
			<form:label path="username">Username:</form:label>
			<form:input path="username"/><br/>

			<form:label path="password">Password:</form:label>
			<form:password path="password"/><br/>		

      <form:label path="amountToDepositCD">Amount to deposit into CD (minimum $100):</form:label>
			<form:input path="amountToDepositCD"/><br/>
      
      <span>Certificate of Deposit Yield: </span><span>${String.format("%.2f%%", (user.certificateOfDepositInterestRate - 1) * 100)}</span><br/>
      <span>Term: </span><span>${user.certificateOfDepositTermInMonths} Months</span><br/>
      <span>Early Withdrawl Penalty: </span><span>${String.format("%.2f%%", (user.certificateOfDepositEarlyWithdrawlPenalty) * 100)}</span><br/>

			<form:button>Purchase Certificate of Deposit</form:button>
		</form:form>
    <a href='/'>Home</a>
	</div>
</body>
</html>