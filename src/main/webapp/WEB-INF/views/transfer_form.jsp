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
          <h2>Make a Transfer </h2>
          <form:form action="transfer" method="post" modelAttribute="user">
            <div class="form-group">
              <form:label path="username">Username:</form:label>
              <form:input path="username" /><br />
            </div>
            <div class="form-group">
              <form:label path="password">Password:</form:label>
              <form:password path="password" /><br />
            </div>
            <div class="form-group">
              <form:label path="transferRecipientID">Username of Recipient:</form:label>
              <form:input path="transferRecipientID" /><br />
            </div>
            <div class="form-group">
              <form:label path="amountToTransfer">Amount to Transfer ($):</form:label>
              <form:input path="amountToTransfer" /><br />
            </div>
            <form:button>Transfer</form:button>
          </form:form>

        </div>
    </body>

    </html>