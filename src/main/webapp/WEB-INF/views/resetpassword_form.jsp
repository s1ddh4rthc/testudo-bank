<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Reset Password Form</title>
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
    .flex-container {
      display: flex;
      justify-content: space-around;
    }
    .flex-item {
      flex: 1;
      padding: 10px;
      margin: 10px;
    }
   
  </style>
</head>
<body>
	<div align="center">
		<h2>Reset Password</h2>
		<form:form action="resetpassword" method="post" modelAttribute="user">
            <form:label path="username">Username:</form:label>
            <form:input path="username"/><br/>

            <div class="flex-container">
                <div class="flex-item">
                    <h3>Enter Old Password</h3>
                    <form:label path="password">Old Password:</form:label>
                    <form:password path="password"/><br/>	
                </div>
                <div class="flex-item">
                    <h3>Or Answer Security Questions</h3>
                    <form:label path="securityAnswer1">Question 1: What was your first car?</form:label>
                    <form:input path="securityAnswer1"/><br/>
                    
                    <form:label path="securityAnswer2">Question 2: What is the name of your first pet?</form:label>
                    <form:input path="securityAnswer2"/><br/>
                    
                    <form:label path="securityAnswer3">Question 3: What is your mother's maiden name?</form:label>
                    <form:input path="securityAnswer3"/><br/>
                </div>
            </div>	

            <form:label path="newPasswordForReset">New Password:</form:label>
            <form:password path="newPasswordForReset"/><br/>

			<form:button>Reset Password</form:button>
		</form:form>
    <a href='/'>Home</a>
	</div>
</body>
</html>