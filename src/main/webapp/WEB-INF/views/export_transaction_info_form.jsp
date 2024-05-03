<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Export Transaction Info</title>
  <style type="text/css">
    label.form-label {
      display: inline-block;
      margin: 5px;
      text-align: left;
    }
    input[type=text], input[type=password], select {
      width: 200px;	
      text-align: left;
    }
    input[type=radio], input[type=checkbox] {
      display: inline-block;
      margin-left: 5px;
    }
    button {
      padding: 10px;
      margin: 10px;
    }
  </style>
</head>
<body>
	<div align="center">
		<form:form action="exportinfo" method="post" modelAttribute="user">
			<form:label class="form-label" path="username">Username:</form:label>
			<form:input path="username"/><br/>

			<form:label class="form-label" path="password">Password:</form:label>
			<form:password path="password"/><br/>		

      <form:label class="form-label" path="dateFrom">Enter Date From (YYYY-MM-DD):</form:label>
      <form:input path="dateFrom"/><br/>

      <form:label class="form-label" path="dateTo">Enter Date To (YYYY-MM-DD):</form:label>
      <form:input path="dateTo"/><br/>
      <label>Select All That Apply:</label><br>
        <div style="text-align: center;">
          <form:checkbox path="requestingCryptoHistory" value="CryptoHistory"/>Crypto History <br>
          <form:checkbox path="requestingTransferHistory" value="TransferHistory"/>Transfer History <br>
          <form:checkbox path="requestingTransactionHistory" value="TransactionHistory"/>Transaction History <br>
        </div>
        
        

			<form:button>Generate Export</form:button>
		</form:form>
    <a href='/'>Home</a>
	</div>
</body>
</html>