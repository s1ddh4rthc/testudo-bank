<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Deposit Form</title>
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
		<form:form action="createSub" method="post" modelAttribute="user">            
            <form:label path="newCustomerFirstName">New Customer First Name:</form:label>
			<form:input path="newCustomerFirstName"/><br/>

            <form:label path="newCustomerLastName">New Customer Last Name:</form:label>
			<form:input path="newCustomerLastName"/><br/>

            <form:label path="newCustomerPassword">New Customer Password:</form:label>
			<form:password path="newCustomerPassword"/><br/>	
				
			<form:button>Submit</form:button>
		</form:form>
    <a href='/'>Home</a>
	</div>
</body>
</html>