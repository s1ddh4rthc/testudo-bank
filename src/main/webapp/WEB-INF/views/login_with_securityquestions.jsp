<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>User Login Form with Security Questions</title>
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
    
    .button-link {
      display: inline-block;
      padding: 10px 15px;
      margin: 10px;
      background-color: #4CAF50;
      color: white;
      text-align: center;
      text-decoration: none;
      font-size: 16px;
      border-radius: 5px;
    }
    .button-link:hover {
      background-color: #45a049;
    }
   
  </style>
</head>
<body>
	<div align="center">
        <h3>Three failed attempts. Please try logging in with your security questions:</h3>
        <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png" style="float:left;width:130px;height:130px;">
		<form:form action="login_with_securityquestions" method="post" modelAttribute="user">
			<form:label path="username">Username:</form:label>
			<form:input path="username"/><br/>
			
            <form:label path="securityAnswer1">Question 1: What was your first car?</form:label>
			<form:input path="securityAnswer1"/><br/>
			
			<form:label path="securityAnswer2">Question 2: What is the name of your first pet?</form:label>
			<form:input path="securityAnswer2"/><br/>
            
            <form:label path="securityAnswer3">Question 3: What is your mother's maiden name?</form:label>
			<form:input path="securityAnswer3"/><br/>	
				
			<form:button>Log in</form:button>
            <a href='/resetpassword' class="button-link">Reset Password</a>
		</form:form>
    <a href='/'>Home</a>
	</div>
</body>
</html>