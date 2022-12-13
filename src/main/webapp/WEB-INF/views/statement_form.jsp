<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Monthly Statements - Testudo Bank</title>
  <style type="text/css">
    label {
      display: inline-block;
      margin: 5px;
      text-align: left;
    }
    button {
      padding: 10px;
      margin: 10px;
    }
    a.button {
      -webkit-appearance: button;
      -moz-appearance: button;
      appearance: button;

      text-decoration: none;
      color: initial;
    }
    /* div.page-container {
      display: flex;
      justify-content: center;
      flex-direction: column;
    } */
    div[class$='-container'] {
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    
  </style>
</head>
<body>
  <div class="page-container">
		<h2><span>Monthly Statements</span></h2>
    <div class="form-container">
      <div>
      <form:form action="statement" method="post" modelAttribute="user">
        <span>
        <form:label path="statementYear">Year:</form:label>
        <form:select path="statementYear">
          <form:option path="statementYear" value="2021">2021</form:option>
          <form:option path="statementYear" value="2022" selected="true">2022</form:option>
          <form:option path="statementYear" value="2023">2023</form:option>
        </form:select>
        </span>
        <span>
        <form:label path="statementMonth">Month:</form:label>
        <form:select path="statementMonth">
          <form:option value="01" selected="true">January</form:option>
          <form:option value="02">February</form:option>
          <form:option value="03">March</form:option>
          <form:option value="04">April</form:option>
          <form:option value="05">May</form:option>
          <form:option value="06">June</form:option>
          <form:option value="07">July</form:option>
          <form:option value="08">August</form:option>
          <form:option value="09">September</form:option>
          <form:option value="10">October</form:option>
          <form:option value="11">November</form:option>
          <form:option value="12">December</form:option>
        </form:select>
        </span>
      </div>
      <div>
        <form:label path="username">Username:</form:label>
        <form:input path="username"/>
      </div>
      <div>
        <form:label path="password">Password:</form:label>
        <form:password path="password"/>
      </div>
      <div>
        <form:button>View Statement</form:button>
      </div>
      </form:form>
    </div>
    <br/>
    <a href='/'>Home</a>
    </div>
</body>
</html>