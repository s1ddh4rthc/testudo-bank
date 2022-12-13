<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Contact Us</title>
  <style type="text/css">
    label {
      display: inline-block;
      width: 200px;
      margin: 5px;
      text-align: left;
    }
    input[type=text], input[type=password], select {
      width: 200px;	
    }
    input[type=radio] {
      display: inline-block;
      margin-left: 45px;
    }
    
    input[type=checkbox] {
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
		<h2>Request a quote</h2>
        <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png" style="float:left;width:100px;height:100px;">

    <form:form method="POST" action="/contact_success" modelAttribute="contact">
      <label>Name:</label>
      <input type="text" name="name" />
      <br/>
      <label>Email:</label>
      <input type="text" name="email" />
      <br/>
      <label>Phone Number:</label>
      <input type="text" name="phone" />
      <br/>
      <button type="submit">Submit</button>
    </form:form>
	</div>
</body>

</html>
