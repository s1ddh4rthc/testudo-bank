<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Dispute Form</title>

</head>
<body>
  <%@ include file="base.jsp" %>
  <div class="form-container">
    <h2>Make a Dispute </h2>
		<form:form action="dispute" method="post" modelAttribute="user">
  
    <div class="form-group">
			<label for="username">Username:</label>
			<form:input path="username"/><br/>
    </div>

    <div class="form-group">
			<label for="password">Password:</label>
			<form:password path="password"/><br/>		
    </div>

    <div class="form-group">
      <label for="numTransactionsAgo">Transaction to Reverse: <br>(1=Most Recent, 2=2 Transactions Ago, Max = 3)</label>
			<form:input path="numTransactionsAgo"/><br/>	
		</div>

			<form:button>Reverse</form:button>
		</form:form>
	</div>
</body>
</html>