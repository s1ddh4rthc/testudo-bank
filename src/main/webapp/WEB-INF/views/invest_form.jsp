<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="net.testudobank.StockPriceClient" %>

    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <link rel="icon"
                href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
            <meta charset="ISO-8859-1">
            <title>Invest in Stock Form</title>
            <style type="text/css">
                label {
                    display: inline-block;
                    width: 200px;
                    margin: 5px;
                    text-align: left;
                }

                input[type=text],
                input[type=password],
                select {
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
                <form:form action="invest" method="post" modelAttribute="user">
                    <form:label path="username">Username:</form:label>
                    <form:input path="username" /><br />

                    <form:label path="password">Password:</form:label>
                    <form:password path="password" /><br />

                    <form:label path="whichStockToInvest">Which Stock to invest in (Type ticker symbol):</form:label>
                    <form:input path="whichStockToInvest" /><br />

                    <form:label path="amountToBuyStock">Amount of shares to buy (fractional allowed):</form:label>
                    <form:input path="amountToBuyStock" /><br />

                    <form:button>Invest in Stock</form:button>
                </form:form>
                <a href='/'>Home</a>
            </div>
        </body>

        </html>