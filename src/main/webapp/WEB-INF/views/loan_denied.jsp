<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
    <meta charset="ISO-8859-1">
    <title>Request Loan</title>
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
        <h2>Loan Request Denied</h2>
        <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png" style="float:left;width:100px;height:100px;">
        <h3>Ensure that your balance is over $100, that you have no outstanding loans and that your requested loan amount is less than your current account balance.</h3>
        <a href='/login'>Go Back</a>
    </div>
</body>
