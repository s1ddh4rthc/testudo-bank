<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <!DOCTYPE html>
  <html>

  <head>
    <link rel="icon"
      href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
    <meta charset="ISO-8859-1">
    <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
    <style type="text/css">
      .user-info-table {
        width: 60%;
        margin-bottom: 1rem;
      }

      .user-info-table td {
        padding: 8px;
        border: 1px solid #a29f9f;
      }

      .user-info-table td:first-child {
        font-weight: bold;

      }

      .bank-table {
        margin-bottom: 1rem;
      }

      .bank-table th,
      .bank-table td {
        padding: 8px;
        border: 1px solid #a29f9f;
      }




      .button-container {
        margin-left: 2rem;
        margin-right: 2rem;
        display: grid;
        grid-template-columns: repeat(5, auto);
        gap: 10px;
      }

      .button-container button {
        padding-left: 20px;
        padding-right: 20px;
        margin-bottom: 5rem;
      }
    </style>
  </head>

  <body>
    <%@ include file="base.jsp" %>
      <div align="center">

        <h2><span>${user.firstName}</span> <span>${user.lastName}</span> Bank Account Info</h2>

        <table class="user-info-table">
          <tr>
            <td><span>Username: </span></td>
            <td><span>${user.username}</span></td>
          </tr>
          <tr>
            <td><span>Balance: $</span></td>
            <td><span>${user.balance}</span></td>
          </tr>
          <tr>
            <td><span>Overdraft Balance: $</span></td>
            <td><span>${user.overDraftBalance}</span></td>
          </tr>
          <tr>
            <td><span>Crypto Balance in USD: $</span></td>
            <td><span>${user.cryptoBalanceUSD}</span></td>
          </tr>
          <tr>
            <td><span>Ethereum Coins Owned: </span></td>
            <td><span>${user.ethBalance}</span></td>
          </tr>
          <tr>
            <td><span>Solana Coins Owned: </span></td>
            <td><span>${user.solBalance}</span></td>
          </tr>
        </table>
        <h3>Crypto Prices</h3>
        <span>Current $ETH Price: </span><span class="crypto-price">${user.ethPrice}</span><br />
        <span>Current $SOL Price: </span><span class="crypto-price">${user.solPrice}</span><br />

        <h3>Re-payment logs:</h3>
        <table class="bank-table">
          <thead>
            <tr>
              <th>Customer ID</th>
              <th>Timestamp</th>
              <th>Deposit Amount</th>
              <th>Old Over Balance</th>
              <th>New Over Balance</th>
            </tr>
          </thead>
          <tbody id="logsBody">
            <script>
              var logs = "${user.logs}";
              var logEntries = logs.split("}");
              logEntries.pop(); // Remove the last empty element

              logEntries.forEach(function (logEntry) {
                // Remove the curly braces and split the entry by commas
                var fields = logEntry.replaceAll("{", "").split(", ");

                // Extract individual fields
                var customerId = fields[0].split("=")[1];
                var timestamp = fields[1].split("=")[1];
                var depositAmt = fields[2].split("=")[1];
                var oldOverBalance = fields[3].split("=")[1];
                var newOverBalance = fields[4].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var row = document.createElement("tr");
                var cell1 = document.createElement("td");
                var cell2 = document.createElement("td");
                var cell3 = document.createElement("td");
                var cell4 = document.createElement("td");
                var cell5 = document.createElement("td");
                cell1.textContent = customerId;
                cell2.textContent = formattedTimestamp;
                cell3.textContent = depositAmt;
                cell4.textContent = oldOverBalance;
                cell5.textContent = newOverBalance;
                row.appendChild(cell1);
                row.appendChild(cell2);
                row.appendChild(cell3);
                row.appendChild(cell4);
                row.appendChild(cell5);
                document.getElementById("logsBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>

        <h3>Transaction History:</h3>
        <table class="bank-table">
          <thead>
            <tr>
              <th>Customer ID</th>
              <th>Timestamp</th>
              <th>Action</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody id="transactionBody">
            <script>
              var transactionHist = "${user.transactionHist}";
              var transactions = transactionHist.split("}");
              transactions.pop(); // Remove the last empty element

              transactions.forEach(function (transaction) {
                // Remove the curly braces and split the transaction by commas
                var fields = transaction.replaceAll("{", "").split(", ");

                // Extract individual fields
                var customerId = fields[0].split("=")[1];
                var timestamp = fields[1].split("=")[1];
                var action = fields[2].split("=")[1];
                var amount = fields[3].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var row = document.createElement("tr");
                var cell1 = document.createElement("td");
                var cell2 = document.createElement("td");
                var cell3 = document.createElement("td");
                var cell4 = document.createElement("td");
                cell1.textContent = customerId;
                cell2.textContent = formattedTimestamp;
                cell3.textContent = action;
                cell4.textContent = amount;
                row.appendChild(cell1);
                row.appendChild(cell2);
                row.appendChild(cell3);
                row.appendChild(cell4);
                document.getElementById("transactionBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>
        <h3>Transfer History:</h3>
        <!-- <span>Transfer History: </span><span>${user.transferHist}</span><br /> -->
        <table class="bank-table">
          <thead>
            <tr>
              <th>Transfer From</th>
              <th>Transfer To</th>
              <th>Timestamp</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody id="transferBody">
            <script>
              var transferHist = "${user.transferHist}";
              var transfers = transferHist.split("}");
              transfers.pop(); // Remove the last empty element

              transfers.forEach(function (transfer) {
                // Remove the curly braces and split the transfer by commas
                var fields = transfer.replaceAll("{", "").split(", ");

                // Extract individual fields
                var transferFrom = fields[0].split("=")[1];
                var transferTo = fields[1].split("=")[1];
                var timestamp = fields[2].split("=")[1];
                var amount = fields[3].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var row = document.createElement("tr");
                var cell1 = document.createElement("td");
                var cell2 = document.createElement("td");
                var cell3 = document.createElement("td");
                var cell4 = document.createElement("td");
                cell1.textContent = transferFrom;
                cell2.textContent = transferTo;
                cell3.textContent = formattedTimestamp;
                cell4.textContent = amount;
                row.appendChild(cell1);
                row.appendChild(cell2);
                row.appendChild(cell3);
                row.appendChild(cell4);
                document.getElementById("transferBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>

        <h3>Crypto History:</h3>
        <!-- <span>Crypto History: </span><span>${user.cryptoHist}</span><br /> -->
        <table class="bank-table">
          <thead>
            <tr>
              <th>Customer ID</th>
              <th>Timestamp</th>
              <th>Action</th>
              <th>Crypto Name</th>
              <th>Crypto Amount</th>
            </tr>
          </thead>
          <tbody id="cryptoBody">
            <script>
              var cryptoHist = "${user.cryptoHist}";
              var cryptoEntries = cryptoHist.split("}");
              cryptoEntries.pop(); // Remove the last empty element

              cryptoEntries.forEach(function (cryptoEntry) {
                // Remove the curly braces and split the entry by commas
                var fields = cryptoEntry.replaceAll("{", "").split(", ");

                // Extract individual fields
                var customerId = fields[0].split("=")[1];
                var timestamp = fields[1].split("=")[1];
                var action = fields[2].split("=")[1];
                var cryptoName = fields[3].split("=")[1];
                var cryptoAmount = fields[4].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var row = document.createElement("tr");
                var cell1 = document.createElement("td");
                var cell2 = document.createElement("td");
                var cell3 = document.createElement("td");
                var cell4 = document.createElement("td");
                var cell5 = document.createElement("td");
                cell1.textContent = customerId;
                cell2.textContent = formattedTimestamp;
                cell3.textContent = action;
                cell4.textContent = cryptoName;
                cell5.textContent = cryptoAmount;
                row.appendChild(cell1);
                row.appendChild(cell2);
                row.appendChild(cell3);
                row.appendChild(cell4);
                row.appendChild(cell5);
                document.getElementById("cryptoBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>

        <br>
        <div class=" button-container">
          <button href='/deposit'>Deposit</button>
          <button href='/withdraw'>Withdraw</button>
          <button href='/dispute'>Dispute</button>
          <button href='/transfer'>Transfer</button>
          <button href='/'>Logout</button>
        </div>
      </div>
  </body>

  </html>