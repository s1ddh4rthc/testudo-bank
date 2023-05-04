<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Freeze Account Form</title>
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
		<form:form action="freeze" method="post" modelAttribute="user">
      <form:label path="username">Username:</form:label>
			<form:input path="username"/><br/>
			
			<form:label path="password">Password:</form:label>
			<form:password path="password"/><br/>		

      <form:label path="selectFreezeUnfreeze">Select to freeze or unfreeze your assets:</form:label>
			<form:select path="selectFreezeUnfreeze">
        <form:option value="Frozen">Freeze</form:option>
        <form:option value="Unfrozen">Unfreeze</form:option>
      </form:select><br/>		

      <form:button>Submit</form:button>
		</form:form>
    <a href='/'>Home</a>
	</div>
  <div align="center">
    <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png" style="float:left;width:100px;height:100px;">
  </div>
</body>
</html>