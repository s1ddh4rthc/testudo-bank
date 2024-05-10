<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <link rel="icon"
                href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
            <meta charset="ISO-8859-1">
            <title>Welcome Page</title>
            <style type="text/css">
                body {
                    text-align: center;
                    margin: 0;
                    padding-top: 20px;
                }

                .links {
                    margin-top: 20px;
                }

                .links a {
                    display: block;
                    margin: 5px auto;
                    width: fit-content;
                }
            </style>
        </head>

        <body>
            <h2>Welcome to Testudo Bank!</h2>
            <div class="links">
                <a href='/login'>View Account</a>
                <a href='/deposit'>Deposit</a>
                <a href='/withdraw'>Withdraw</a>
                <a href='/dispute'>Dispute</a>
                <a href='/transfer'>Transfer</a>
                <a href='/buycrypto'>Buy Cryptocurrency</a>
                <a href='/sellcrypto'>Sell Cryptocurrency</a>
                <a href='/invest'>Invest in Stock</a>
                <a href='/sellstock'>Sell Stock</a>
            </div>
            <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png"
                style="position: absolute; left: 10px; top: 10px; width:100px; height:100px;">
        </body>

    </html>