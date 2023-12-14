<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
    <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        header {
            background-color: #007bff;
            padding: 10px;
            text-align: center;
            color: white;
            border-radius: 8px 8px 0 0;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        h1 {
            margin: 0;
        }

        p {
          margin-right: 10px;
        }

        .container {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            max-width: 800px;
            background-color: white;
            padding: 20px;
            border-radius: 0 0 8px 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .info-box {
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        span {
            display: block;
            margin-bottom: 15px;
        }

        button {
            padding: 10px;
            margin: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        a.button {
            display: inline-block;
            padding: 10px;
            margin: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <header>
      <p>${user.username}</p>
      <h1>${user.firstName} ${user.lastName} Bank Account Info</h1>
      <a href='/' class='button'>Logout</a>
    </header>

    <div class="container">

      <div class="info-box">
          <label>Balance:</label>
          <span style="font-size: xxx-large;">$${user.balance}</span>
      </div>

      <div class="info-box">
          <label>Overdraft Balance:</label>
          <span id="balance_amount">$${user.overDraftBalance}</span>
      </div>

      <div class="info-box">
          <label>Crypto Balance in USD:</label>
          <span>$${user.cryptoBalanceUSD}</span>
      </div>

      <div class="info-box">
          <label>Ethereum Coins Owned:</label>
          <span>${user.ethBalance}</span>
      </div>

      <div class="info-box">
          <label>Solana Coins Owned:</label>
          <span>${user.solBalance}</span>
      </div>

      <div class="info-box">
          <label>Current $ETH Price:</label>
          <span>$${user.ethPrice}</span>
      </div>

      <div class="info-box">
          <label>Current $SOL Price:</label>
          <span>$${user.solPrice}</span>
      </div>

      <div class="info-box">
          <label>Re-payment logs:</label>
          <span>${user.logs}</span>
      </div>

      <div class="info-box">
          <label>Transaction History:</label>
          <span>${user.transactionHist}</span>
      </div>

      <div class="info-box">
          <label>Transfer History:</label>
          <span>${user.transferHist}</span>
      </div>

      <div class="info-box">
          <label>Crypto History:</label>
          <span>${user.cryptoHist}</span>
      </div>

      <div class="info-box">
          <a href='/deposit' class='button'>Deposit</a>
          <a href='/withdraw' class='button'>Withdraw</a>
          <a href='/dispute' class='button'>Dispute</a>
          <a href='/transfer' class='button'>Transfer</a>
      </div>
    </div>
</body>
</html>