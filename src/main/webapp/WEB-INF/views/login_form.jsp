<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!DOCTYPE html>
    <html>

    <head>
      <link rel="icon"
        href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
      <meta charset="ISO-8859-1">
      <title>User Login Form</title>
    </head>

    <body>
      <%@ include file="base.jsp" %>
      <div class="form-container">
        <h2>Please sign in:</h2>
        <!-- <img class="logo" src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png"> -->
        <form:form action="login" method="post" modelAttribute="user">
          <div class="form-group">
            <br>
            <label for="username">Username:</label>
            <form:input path="username" id="username" />
          </div>
          <div class="form-group">
            <label for="password">Password:</label>
            <form:password path="password" id="password" />
          </div>
          <button type="submit">Log in</button>
        </form:form>

      </div>
    </body>

    </html>