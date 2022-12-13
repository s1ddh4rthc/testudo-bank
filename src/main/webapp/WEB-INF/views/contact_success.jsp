 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Success</title>
<style type="text/css">
    span {
        display: inline-block;
        width: 200px;
        text-align: left;
    }
</style>
</head>
<body>
    <div align="center">
        <h2>Inquiry Successful!</h2>
        <span>Name:</span><span>${contact.name}</span><br/>
        <span>E-mail:</span><span>${contact.email}</span><br/>
        <span>Phone:</span><span>${contact.phone}</span><br/>
    </div>
</body>
</html>