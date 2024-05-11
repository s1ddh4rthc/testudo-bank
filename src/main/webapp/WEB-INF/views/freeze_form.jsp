<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Freeze Account Form</title>
    <style>
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

      button {
        padding: 5px;
        margin: 10px;
      }
    </style>
  </head>
  <body>
    <div align="center">
      <form:form action="freeze" method="post" modelAttribute="user">
        <form:label path="username">Username:</form:label>
        <form:input path="username" /><br />

        <form:label path="password">Password:</form:label>
        <form:password path="password" /><br />

        <form:label path="selectFreezeUnfreeze"
          >Choose to either freeze or unfreeze your account:</form:label
        >
        <form:select path="selectFreezeUnfreeze">
          <form:option value="Frozen">Freeze</form:option>
          <form:option value="Unfrozen">Unfreeze</form:option>
        </form:select><br />

        <form:button>Submit</form:button>
      </form:form>
      <a href="/">Home</a>
    </div>
    <div align="center">
    </div>
  </body>
</html>
