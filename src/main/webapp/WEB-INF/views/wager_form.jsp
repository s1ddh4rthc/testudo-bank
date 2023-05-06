<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <link
      rel="icon"
      href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png"
    />
    <meta charset="ISO-8859-1" />
    <title>Wager Form</title>
    <style type="text/css">
      label {
        display: inline-block;
        width: 200px;
        margin: 5px;
        text-align: left;
      }
      input[type="text"],
      input[type="password"],
      select {
        width: 200px;
      }
      input[type="radio"] {
        display: inline-block;
        margin-left: 45px;
      }

      input[type="checkbox"] {
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
      <form:form action="wager" method="post" modelAttribute="user">
        <form:label path="username">Username:</form:label>
        <form:input path="username" /><br />

        <form:label path="password">Password:</form:label>
        <form:password path="password" /><br />

        <form:label path="value"
          >Over/Under Value (Type 10, 25, 50, 75, or 90):</form:label
        >
        <form:input path="value" /><br />

        <form:label path="overOrUnder"
          >Over or Under? (Type "Over" or "Under"):</form:label
        >
        <form:input path="overOrUnder" /><br />

        <form:label path="amountToWager">Amount to Wager ($):</form:label>
        <form:input path="amountToWager" /><br />

        <form:button>Wager</form:button>
      </form:form>
      <a href="/">Home</a>
    </div>
  </body>
</html>
