<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <link rel="stylesheet" type="text/css" href="https://panko2128.github.io/tbcss/styles.css">
  <!-- <link rel="stylesheet" type="text/css" href="styles.css">
  <meta charset="ISO-8859-1"> -->
  <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
  <!-- <style type="text/css">
    label {
      display: inline-block;
      width: 200px;
      margin: 5px;
      text-align: left;
    }
    button {
      padding: 10px;
      margin: 10px;
    }
    a.button {
      -webkit-appearance: button;
      -moz-appearance: button;
      appearance: button;

      text-decoration: none;
      color: initial;
    }
  </style> -->
</head>
<body>
	
      <div class="navbar">
        <a class="button" href='/deposit'>Deposit</a>
        <a class="button" href='/withdraw'>Withdraw</a>
        <a class="button" href='/dispute'>Dispute</a>
        <a class="button" href='/transfer'>Transfer</a>
        <a class="button" href='/'>Logout</a>
      </div>

      <div class="header">
        <h2><span>${user.firstName}</span> <span>${user.lastName}</span> Account Info</h2>
      </div>
		
    
    <div class="info-container">
      <div class="info-item"><span class="label">Username: </span><span>${user.username}</span></div>
      <div class="info-item"><span class="label">Balance: </span><span>${user.balance} </span></div>
      <div class="info-item"><span class="label">Overdraft Balance: $</span><span>${user.overDraftBalance} </span></div>
      <div class="info-item"><span class="label">Crypto Balance in USD: </span><span>${user.cryptoBalanceUSD} </span></div>
      <div class="info-item"><span class="label">Ethereum Coins Owned:  </span><span>${user.ethBalance} </span></div>
      <div class="info-item"><span class="label">Solana Coins Owned:  </span><span>${user.solBalance} </span></div>
      <div class="info-item"><span class="label">Current $ETH Price:  </span><span>${user.ethPrice} </span></div>
      <div class="info-item"><span class="label">Current $SOL Price:  </span><span>${user.solPrice} </span></div>
      <div class="info-item"><span class="label">Re-payment logs:  </span><span>${user.logs} </span></div>
      <!-- <div class="info-item"><span class="label">Transaction History:  </span><span>${user.transactionHist} </span>
        <table id="transactionHistoryTable">
          add table entries with JS
        </table>
      </div> -->
      <button class="collapsible nav-button">Transaction History</button>
      <div class="content" style="display: none;">
        <table id="transactionHistTable">
          <thead>
            <tr>
              <th>ID</th>
              <th>Timestamp</th>
              <th>Action</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody id="transactionHistBody">
          </tbody>
        </table>
      </div>
      <br>
      <button class="collapsible nav-button">Transfer History</button>
      <div class="content" style="display: none;">
        <table id="transferHistTable">
          <thead>
            <tr>
              <th>From</th>
              <th>To</th>
              <th>Timestamp</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody id="transferHistBody">
          </tbody>
        </table>
      </div>
      <br>
      <button class="collapsible nav-button">Crypto History</button>
      <div class="content" style="display: none;">
        <table id="cryptoHistTable">
          <thead>
            <tr>
              <th>ID</th>
              <th>Time</th>
              <th>Action</th>
              <th>Crypto</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody id="cryptoHistBody">
          </tbody>
        </table>
      </div>
      <br>

    <br />

      <script>

        var transactionHistString = "${user.transactionHist}";
        var transferHistString = "${user.transferHist}";
        var cryptoHistString = "${user.cryptoHist}"

        var transactionHistArray = processStringToArr(transactionHistString);
        var transferHistArray = processStringToArr(transferHistString);
        var cryptoHistArray = processStringToArr(cryptoHistString);

        var transacBody = document.getElementById('transactionHistBody');
        var transferBody = document.getElementById('transferHistBody');
        var cryptoBody = document.getElementById('cryptoHistBody');

        addEntriesToTable(transacBody, transactionHistArray);
        addEntriesToTable(transferBody, transferHistArray);
        addEntriesToTable(cryptoBody, cryptoHistArray);

        function processStringToArr(historyString) {
          historyString = historyString.replace(/}/g, "")
          historyArray = historyString.split("<br/>")
          if (historyArray[0] === "") {
            historyArray.shift();
          }
          if (historyArray[historyArray.length - 1] === "") {
            historyArray.pop();
          }
          return historyArray
        }

        // takes a history dict and returns the html row
        function createTableRow(transaction) {
            var row = document.createElement('tr');
            for (var key in transaction) {
                var cell = document.createElement('td');
                cell.textContent = transaction[key];
                row.appendChild(cell);
            }
            return row;
        }


        function addEntriesToTable(table, historyArray) {  
          for (var i = 0; i < historyArray.length; i++) {
              var entry = historyArray[i];
              var keyValuePairs = entry.split(", ");
              var event = {};

              for (var j = 0; j < keyValuePairs.length; j++) {
                  var pair = keyValuePairs[j].split("=");
                  var key = pair[0].trim();
                  var value = pair[1].trim();
                  event[key] = value;
              }

              var tableRow = createTableRow(event);
              table.appendChild(tableRow);
          }
        }

        var coll = document.getElementsByClassName("collapsible");
        for (var i = 0; i < coll.length; i++) {
          coll[i].addEventListener("click", function () {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "table") {
              content.style.display = "none";
            } else {
              content.style.display = "table";
            }
          });
        }
    </script>

    </div>

    
	</div>
</body>
</html>