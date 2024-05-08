<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <!DOCTYPE html>
  <html>

  <head>
    <link rel="icon"
      href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
    <meta charset="ISO-8859-1">
    <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
    <style type="text/css">
      /*CSS for Basic Account info Table*/
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

      /*CSS for Logged History Tables*/
      .bank-table {
        margin-bottom: 1rem;
      }

      .bank-table th,
      .bank-table td {
        padding: 8px;
        border: 1px solid #a29f9f;
      }



      /*CSS for Grid for buttons*/
      .button-container {
        margin-left: 17rem;
        margin-right: 17rem;
        display: grid;
        grid-template-columns: repeat(5, auto);
        grid-gap: 0;
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

        <!-- Css For User Info table -->
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

        <!-- HTML for Current Crypto Prices -->
        <h3>Crypto Prices</h3>
        <span>Current $ETH Price: </span><span class="crypto-price">${user.ethPrice}</span><br />
        <span>Current $SOL Price: </span><span class="crypto-price">${user.solPrice}</span><br />

        <!-- Re-Payment Logs Table -->
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
              logEntries.pop();

              logEntries.forEach(function (logEntry) {
                // Parse the Information
                var fields = logEntry.replaceAll("{", "").split(", ");

                // Extract fields
                var customerId = fields[0].split("=")[1];
                var timestamp = fields[1].split("=")[1];
                var depositAmt = fields[2].split("=")[1];
                var oldOverBalance = fields[3].split("=")[1];
                var newOverBalance = fields[4].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var createRow = document.createElement("tr");
                // Create the cells to add to row
                var idCell = document.createElement("td");
                var timeCell = document.createElement("td");
                var depositCell = document.createElement("td");
                var oldOverBalanceCell = document.createElement("td");
                var newOverBalanceCell = document.createElement("td");
                // Add information to cell
                idCell.textContent = customerId;
                timeCell.textContent = formattedTimestamp;
                depositCell.textContent = depositAmt;
                oldOverBalanceCell.textContent = oldOverBalance;
                newOverBalanceCell.textContent = newOverBalance;
                // Add cells to the row
                createRow.appendChild(idCell);
                createRow.appendChild(timeCell);
                createRow.appendChild(depositCell);
                createRow.appendChild(oldOverBalanceCell);
                createRow.appendChild(oldOverBalanceCell);
                document.getElementById("logsBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>

        <!-- Transaction History Table HTML -->
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
              transactions.pop();

              transactions.forEach(function (transaction) {
                // Parse Data
                var fields = transaction.replaceAll("{", "").split(", ");

                // Extract fields
                var customerId = fields[0].split("=")[1];
                var timestamp = fields[1].split("=")[1];
                var action = fields[2].split("=")[1];
                var amount = fields[3].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var row = document.createElement("tr");
                //Create Cells
                var idCell = document.createElement("td");
                var timeCell = document.createElement("td");
                var actionCell = document.createElement("td");
                var amountCell = document.createElement("td");
                //Input the text for cells
                idCell.textContent = customerId;
                timeCell.textContent = formattedTimestamp;
                actionCell.textContent = action;
                amountCell.textContent = amount;
                // Add cells to row
                row.appendChild(idCell);
                row.appendChild(timeCell);
                row.appendChild(actionCell);
                row.appendChild(amountCell);
                document.getElementById("transactionBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>


        <!-- Tansfer History Table HTML -->
        <h3>Transfer History:</h3>
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
              transfers.pop();

              transfers.forEach(function (transfer) {
                // Parse Data
                var fields = transfer.replaceAll("{", "").split(", ");

                // Extract fields
                var transferFrom = fields[0].split("=")[1];
                var transferTo = fields[1].split("=")[1];
                var timestamp = fields[2].split("=")[1];
                var amount = fields[3].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var row = document.createElement("tr");
                // Create cells for data
                var transferFromCell = document.createElement("td");
                var transferToCell = document.createElement("td");
                var timeCell = document.createElement("td");
                var amountCell = document.createElement("td");
                // Fill cells with data
                transferFromCell.textContent = transferFrom;
                transferToCell.textContent = transferTo;
                timeCell.textContent = formattedTimestamp;
                amountCell.textContent = amount;
                row.appendChild(transferFromCell);
                row.appendChild(transferToCell);
                row.appendChild(timeCell);
                row.appendChild(amountCell);
                document.getElementById("transferBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>

        <!-- Crypto History Table HTML -->
        <h3>Crypto History:</h3>
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
              cryptoEntries.pop();

              cryptoEntries.forEach(function (cryptoEntry) {
                // Parse Data
                var fields = cryptoEntry.replaceAll("{", "").split(", ");

                // Extract fields
                var customerId = fields[0].split("=")[1];
                var timestamp = fields[1].split("=")[1];
                var action = fields[2].split("=")[1];
                var cryptoName = fields[3].split("=")[1];
                var cryptoAmount = fields[4].split("=")[1];

                // Format the timestamp
                var formattedTimestamp = new Date(timestamp).toLocaleString();

                // Create table row
                var row = document.createElement("tr");
                // Create cells
                var idCell = document.createElement("td");
                var timeCell = document.createElement("td");
                var actionCell = document.createElement("td");
                var cryptoNameCell = document.createElement("td");
                var cryptoAmountCell = document.createElement("td");
                // Fill the cells with corresponding
                idCell.textContent = customerId;
                timeCell.textContent = formattedTimestamp;
                actionCell.textContent = action;
                cryptoNameCell.textContent = cryptoName;
                cryptoAmountCell.textContent = cryptoAmount;
                row.appendChild(idCell);
                row.appendChild(timeCell);
                row.appendChild(actionCell);
                row.appendChild(cryptoNameCell);
                row.appendChild(cryptoAmountCell);
                document.getElementById("cryptoBody").appendChild(row);
              });
            </script>
          </tbody>
        </table>

        <br>

        <!-- Navigate buttons -->
        <div class=" button-container">
          <a href='/deposit'><button>Deposit</button></a>
          <a href='/withdraw'><button>Withdraw</button></a>
          <a href='/dispute'><button>Dispute</button></a>
          <a href='/transfer'><button>Transfer</button></a>
          <a href='/'><button>Logout</button></a>
        </div>
      </div>
  </body>

  </html>