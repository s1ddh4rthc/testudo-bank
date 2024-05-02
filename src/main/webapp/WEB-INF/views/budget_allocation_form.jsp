<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Budget Allocation Form </title>
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
        <form:form action="allocate" method="post" modelAttribute="user">
            <!-- Username & PW Details -->
            <form:label path="username">Username:</form:label>
            <form:input path="username"/><br/>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/><br/>
            <!-- Amount Allocated To Each Budget -->
            <form:label path="budgetGroceries">Food and Groceries Budget:</form:label>
			<form:input path="budgetGroceries"/><br/>	
            <form:label path="budgetHousing">Housing and Utilities Budget:</form:label>
			<form:input path="budgetHousing"/><br/>	
            <form:label path="budgetTransportation">Transportation Budget:</form:label>
			<form:input path="budgetTransportation"/><br/>
            <form:label path="budgetSavings">Savings and Investments Budget:</form:label>
			<form:input path="budgetSavings"/><br/>	
            <form:label path="budgetOther">Other Budget:</form:label>
			<form:input path="budgetOther"/><br/>		
            <form:button>Submit Allocated Budgets </form:button>
        </form:form>
    <a href='/'>Home</a>
    </div>
</body>

</html>