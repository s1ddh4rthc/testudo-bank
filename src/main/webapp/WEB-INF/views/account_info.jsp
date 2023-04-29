<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
  <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
  <link rel="stylesheet" href="account_info.css"> 
  <script> 
    // pulling data from user logs
    let transactions = `${user.transactionHist}`.split("<br/>");
    transactions = transactions.filter(t => t); // filter out empty entries

    // parsing each log into a table entry
    const regex = /^{CustomerID=[0-9]{9}, Timestamp=([0-9]{4}-[0-9]{2}-[0-9]{2})T([0-9]{2}:[0-9]{2})(:[0-9]{2})?, Action=(Deposit|Withdraw|TransferSend|TransferReceive|CryptoSell|CryptoBuy|Interest), Amount=([0-9]+), Balance=([0-9]+)}$/;
    const transactionRows = transactions.reduce((acc, trans) => {
      const matchedGroups = trans.match(regex);
      if (matchedGroups) {
        const date = matchedGroups[1];
        const time = matchedGroups[2];
        const type = matchedGroups[4];
        const amount = (matchedGroups[5] / 100).toFixed(2); // convert from pennies to dollars, truncate to 2 decimal points
        const balance = (matchedGroups[6] / 100).toFixed(2);
        return acc + `
          <tr>
            <td> \${date} </td> 
            <td> \${time} </td> 
            <td> \${type} </td> 
            <td> \${amount} </td> 
            <td> \${balance} </td>
          </tr>`;
      }
      return acc + ``;
    }, ``);
    
    // updating table
    const transactionTable = 
      `<table id="transactionTable"> 
        <tr> 
          <th> Date </th>
          <th> Time </th>
          <th> Type </th>
          <th> Amount </th>
          <th> Balance </th>
        </tr>
        <div> 
          \${transactionRows == ''?  '<tr><td colspan="5"> No history available </td></tr>' : transactionRows}
        </div>
      </table>`;

    window.onload = function() {
      document.getElementById("tableWrapper").innerHTML = transactionTable;
    }
  </script>
</head>
<body>
	<div>
    <div id="header"> <img src="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">Testudo Bank </div>
		<div id="body"> 
      <div id="introMsg"> 
        <h1> Welcome, ${user.firstName} ${user.lastName}</h1>
        <span>Username: </span><span>${user.username}</span><br/>
        <br /> 
        <a href='/deposit'>Deposit</a>
        <a href='/withdraw'>Withdraw</a>
        <a href='/dispute'>Dispute</a>
        <a href='/transfer'>Transfer</a>
      </div>
  
      <h2> Account Information </h2> <hr> 
      <div> 
        <span> <h3>Balance </h3>$${user.balance}</span><br/>
        <span> <h3>Overdraft Balance </h3>$${user.overDraftBalance}</span><br/>
        <br/>
        <span> <h3>Interest Rate </h3>${user.interestRate}%</span><br/>
      </div>
  
      <h2> Cryptocurrency </h2> <hr> 
      <span> <h3>Crypto Balance </h3>$${user.cryptoBalanceUSD}</span><br/>
      <table> 
        <tr> 
          <td><h3> Ethereum </h3>$${user.ethBalance} </td>
          <td><em> Current Price: ${user.ethPrice} </em></td>
        </tr>
        <tr> 
          <td><h3> Solana </h3> $${user.solBalance}</td>
          <td><em> Current Price: ${user.solPrice} </em></td>
        </tr>
      </table>
  
      <h2> Transactions </h2> <hr> 
      <div id="tableWrapper"> </div>

      <div id="footer"> <a href='/'>Logout</a> </div>
    </div>

</body>
</html>