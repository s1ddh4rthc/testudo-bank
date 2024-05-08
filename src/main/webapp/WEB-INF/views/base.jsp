<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <link rel="icon"
            href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
        <meta charset="ISO-8859-1">
        <!-- CSS for all the HTML files -->
        <style>
            body {
                font-family: sans-serif;
                margin: auto;
                padding: 0;
                background-color: #f5f5f5;
            }

            /* Navigation bar CSS */
            header {
                /*Text color for Nav Bar*/
                color: #fff;
            }

            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #e31638;
                position: sticky;
                top: 0;
            }

            li {
                float: left;
                border-right: 1px solid #e9b9b9;
                display: inline;

            }



            li a {
                display: block;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover {
                background-color: #b911278a;
            }


            /*Css For Buttons*/
            button {
                background: #e32343;
                border: 1px solid #e32343;
                border-radius: 6px;
                box-shadow: rgba(0, 0, 0, 0.1) 1px 2px 4px;
                color: #FFFFFF;
                display: inline-block;
                font-family: nunito, roboto, proxima-nova, "proxima nova", sans-serif;
                font-size: 16px;
                font-weight: 800;
                line-height: 16px;
                padding: 12px 14px;
                text-align: center;
            }

            button:hover,
            button:active {
                background-color: #f5f5f5;
                background-position: 0 0;
                color: #FF4742;
            }

            button:active {
                opacity: .5;
            }

            /*Css for Forms*/
            .form-container {
                max-width: 400px;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .form-container h2 {
                text-align: center;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                display: inline-block;
                margin-bottom: 5px;
                color: #282828;
            }

            input[type=text],
            input[type=password],
            input[type=number] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }


            /*Css for Crypto Prices*/
            .crypto-price {
                font-weight: bold;
                color: #d5203f;
            }

            .center-div-css {
                text-align: center;
                margin-top: 50px;
            }

            /*Css for Welcome Page Testudo*/
            .main-img {
                width: 115px;
                height: 115px;
                margin-bottom: 20px;
            }

            h1 {
                font-size: 40px;
            }

            p {
                font-size: 21px;
                color: #666;
                line-height: 1.6;
            }
        </style>
    </head>


    <body>
        <header>
            <ul>
                <li><a href='/'>Home</a></li>
                <li><a href='/login'>View Accounts</a> </li>
                <li><a href='/deposit'>Deposit</a> </li>
                <li><a href='/withdraw'>Withdraw</a> </li>
                <li><a href='/transfer'>Transfer</a> </li>
                <li style="float:right"><a href='/dispute'>Dispute</a> </li>
                <li style="float:right"><a href='/buycrypto'>Buy Cryptocurrency</a></li>
                <li style="float:right; border-left:1px solid #e9b9b9"><a href='/sellcrypto'>Sell Cryptocurrency</a>
                </li>
            </ul>
        </header>
    </body>

    </html>