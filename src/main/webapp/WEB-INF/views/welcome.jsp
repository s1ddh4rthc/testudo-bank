<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <link
      rel="icon"
      href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png"
    />
    <meta charset="ISO-8859-1" />
    <title>Welcome Page</title>
    <style type="text/css">
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
      input[type="radio"] {
        display: inline-block;
        margin-left: 45px;
      }

      input[type="checkbox"] {
        display: inline-block;
        margin-right: 190px;
      }

      button {
        padding: 10px;
        margin: 10px;
      }
    </style>

    <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
    <script
      src="https://unpkg.com/react@18/umd/react.development.js"
      crossorigin
    ></script>
    <script
      src="https://unpkg.com/react-dom@18/umd/react-dom.development.js"
      crossorigin
    ></script>
    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"
      integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
      crossorigin="anonymous"
    ></script>
    <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
    <script type="text/babel" src="/js/App.jsx"></script>

    <script type="text/babel">
      $(document).ready(function () {
        console.log("ready!");
        const root = ReactDOM.createRoot(document.getElementById("mydiv"));
        root.render(<App />);
      });
    </script>

    <!-- Load our React component. -->
    <!-- <script src="like_button.js"></script> -->
  </head>

  <body>
    <div align="center">
      <h2>Welcome to Testudo Bank!</h2>
      <img
        src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png"
        style="float: left; width: 100px; height: 100px"
      />
      <a href="/login">View Account</a> <br />
      <a href="/deposit">Deposit</a> <br />
      <a href="/withdraw">Withdraw</a> <br />
      <a href="/dispute">Dispute</a> <br />
      <a href="/transfer">Transfer</a> <br />
      <a href="/buycrypto">Buy Cryptocurrency</a>
      <a href="/sellcrypto">Sell Cryptocurrency</a> <br />
    </div>
    <div id="mydiv"></div>
  </body>
</html>
