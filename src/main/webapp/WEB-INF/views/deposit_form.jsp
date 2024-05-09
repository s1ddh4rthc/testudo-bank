<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!DOCTYPE html>
    <html>

    <head>
      <link rel="icon"
        href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
      <meta charset="ISO-8859-1">
      <title>Deposit Form</title>
    </head>

    <body>
      <!-- Allows For Css to be in one file also has the Navigation bar -->
      <%@ include file="base.jsp" %>
        <div class="form-container">
          <h2>Make a Deposit</h2>
          <form:form action="deposit" method="post" modelAttribute="user">
            <div class="form-group">
              <label for="username">Username:</label>
              <form:input path="username" id="username" />
            </div>
            <div class="form-group">
              <label for="password">Password:</label>
              <form:password path="password" id="password" />
            </div>
            <div class="form-group">
              <label for="amountToDeposit">Amount to Deposit ($):</label>
              <form:input path="amountToDeposit" id="amountToDeposit" type="number" />
            </div>
            <button type="submit">Deposit</button>
          </form:form>
        </div>
    </body>

    </html>