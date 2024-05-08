<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Deposit Form</title>

</head>
<body>
  <%@ include file="base.jsp" %>
  <div class="form-container">
    <h2>Make a Withdraw</h2>
		<form:form action="withdraw" method="post" modelAttribute="user">
      <div class="form-group">
			<form:label path="username">Username:</form:label>
			<form:input path="username"/><br/>
      </div>
      <div class="form-group">
			<form:label path="password">Password:</form:label>
			<form:password path="password"/><br/>		
      </div>
      <div class="form-group">
      <form:label path="amountToWithdraw">Amount to Withdraw ($):</form:label>
			<form:input path="amountToWithdraw"/><br/>	
      </div>
			<form:button>Withdraw</form:button>
		</form:form>
	</div>
</body>
</html>