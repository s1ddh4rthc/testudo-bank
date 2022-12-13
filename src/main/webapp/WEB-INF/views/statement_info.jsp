<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
  <style type="text/css">
    table {
      border-spacing: 10px;
      padding: 10px 0px
    }
    caption {
      font-size: large;
      font-weight: bold;
    }
    div.summaryInfo {
      padding: 10px 0px
    }
  </style>
</head>
<body>
	<div align="center">
		<h2><span>Statement for ${user.statementYear}-<fmt:formatNumber minIntegerDigits="2" value="${user.statementMonth}" /> - ${user.firstName}</span> <span>${user.lastName}</span></h2>
    <div class="summaryInfo" id="statementSummary">
      <span>Username: </span><span>${user.username}</span><br/>
    </div>
    <table>
      <caption>Re-payment Logs</caption>
      <thead>
        <tr>
          <th>Time</th>
          <th>Old Balance</th>
          <th>Remaining Balance</th>
          <th>Deposit Amount</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="transaction" items="${user.monthlyOverdraftLogs}">
          <tr>
            <td>${transaction.get("Timestamp")}</td>
            <td>${user.convertPenniesToDollars(transaction.get("OldOverBalance"))}</td>
            <td>${user.convertPenniesToDollars(transaction.get("NewOverBalance"))}</td>
            <td>${user.convertPenniesToDollars(transaction.get("DepositAmount"))}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <table>
      <caption>Transaction History</caption>
      <thead>
        <tr>
          <th>Time</th>
          <th>Category</th>
          <th>Amount</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="transaction" items="${user.monthlyTransactionLogs}">
          <tr>
            <td>${transaction.get("Timestamp")}</td>
            <td>${transaction.get("Action")}</td>
            <td>${user.convertPenniesToDollars(transaction.get("Amount"))}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <table>
      <caption>Transfer History</caption>
      <thead>
        <tr>
          <th>Time</th>
          <th>From</th>
          <th>To</th>
          <th>Amount</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="transaction" items="${user.monthlyTransferLogs}">
          <tr>
            <td>${transaction.get("Timestamp")}</td>
            <td>${transaction.get("TransferFrom")}</td>
            <td>${transaction.get("TransferTo")}</td>
            <td>${user.convertPenniesToDollars(transaction.get("Amount"))}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <table>
      <caption>Crypto History</caption>
      <thead>
        <tr>
          <th>Time</th>
          <th>Category</th>
          <th>Currency</th>
          <th>Amount</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="transaction" items="${user.monthlyCryptoLogs}">
          <tr>
            <td>${transaction.get("Timestamp")}</td>
            <td>${transaction.get("Action")}</td>
            <td>${transaction.get("CryptoName")}</td>
            <td>${transaction.get("CryptoAmount")}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <a href='/deposit'>Deposit</a>
    <a href='/withdraw'>Withdraw</a>
    <a href='/dispute'>Dispute</a>
    <a href='/transfer'>Transfer</a>
    <a href='/'>Logout</a>
	</div>
</body>
</html>