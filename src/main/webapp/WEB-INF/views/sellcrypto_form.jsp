<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!DOCTYPE html>
    <html>

    <head>
      <link rel="icon"
        href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
      <meta charset="ISO-8859-1">
      <title>Sell Cryptocurrency Form</title>
    </head>

    <body>
      <!-- Allows For Css to be in one file also has the Navigation bar -->
      <%@ include file="base.jsp" %>
        <div class="form-container">
          <h2>Sell Crypto!!! </h2>
          <form:form action="sellcrypto" method="post" modelAttribute="user">
            <div class="form-group">
              <label for="username">Username:</label>
              <form:input path="username" id="username" />
            </div>

            <div class="form-group">
              <label for="password">Password:</label>
              <form:password path="password" id="password" />
            </div>
            <div class="form-group">
              <label for="whichCryptoToBuy">Which Crypto to sell (Type 'ETH' or 'SOL'):</label>
              <form:input path="whichCryptoToBuy" id="whichCryptoToBuy" />
            </div>

            <div class="form-group">
              <label for="amountToSellCrypto">Amount to sell (# of Coins, Fractional Allowed):</label>
              <form:input path="amountToSellCrypto" id="amountToSellCrypto" />
            </div>

            <div class="form-group">
              <span>Current $ETH Price: </span><span class="crypto-price">${user.ethPrice}</span><br>
              <span>Current $SOL Price: </span><span class="crypto-price">${user.solPrice}</span><br>
            </div>

            <form:button>Sell Crypto</form:button>
          </form:form>

        </div>
    </body>

    </html>