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
    <title>Sell Stock Form</title>
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
      <form:form action="sellstock" method="post" modelAttribute="user">
        <form:label path="username">Username:</form:label>
        <form:input path="username" /><br />

        <form:label path="password">Password:</form:label>
        <form:password path="password" /><br />

        <form:label path="whichStockToBuy"
          >Which Stock to buy (Type 'ETH' or 'SOL'):</form:label
        >
        <form:input path="whichStockToBuy" /><br />

        <form:label path="amountToSellStock"
          >Amount to sell (# of Coins, Fractional Not Allowed):</form:label
        >
        <form:input path="amountToSellStock" /><br />

        <span>Current $AAPL Price: </span><span>${user.aaplPrice}</span><br />
        <span>Current $GOOG Price: </span><span>${user.googPrice}</span><br />
        <span>Current $MSFT Price: </span><span>${user.msftPrice}</span><br />

        <form:button>Sell Shares</form:button>
      </form:form>
      <a href="/">Home</a>
    </div>
  </body>
</html>
