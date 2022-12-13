<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>${user.firstName} ${user.lastName} - Testudo Bank</title>
  <style type="text/css">
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
    ul{
      display: inline-block;
    }
    li{
      text-align: start;
    }
  </style>
</head>
<body>
	<div align="center">
		<h2><span>${user.firstName}</span> <span>${user.lastName}</span> Bank Account Info</h2>
    <span>Username: </span><span>${user.username}</span><br/>
		<span>Current Balance: $</span><span>${user.balance}</span><br/>
    <br/>
    <span>Last Month Starting Balance: $</span><span>${user.lastSavingsCalculatedBalance}</span><br/>
    <span>Last updated date: </span><span>${user.savingsPercentageDate}</span><br/>
    <br/>
    <br/>
    <span>Your budget this month is:</span><br/>
    <ul>
      <span>Needs (50%): $</span><span>${user.lastSavingsCalculatedBalance *.5}</span>
      <li>Housing $${(user.lastSavingsCalculatedBalance *.5) * .6}</li>
      <li>Food $${(user.lastSavingsCalculatedBalance *.5) * .1}</li>
      <li>Transportation $${(user.lastSavingsCalculatedBalance *.5) * .3}</li>
    </ul><br/>
    
    <ul>
      <span>Wants (30%): $</span><span>${user.lastSavingsCalculatedBalance *.3}</span>
      <li>Resturants/Bars $${(user.lastSavingsCalculatedBalance *.3) * .1}</li>
      <li>Traveling $${(user.lastSavingsCalculatedBalance *.3) * .3}</li>
      <li>Entertainment $${(user.lastSavingsCalculatedBalance *.3) * .1}</li>
      <li>Misc $${(user.lastSavingsCalculatedBalance *.3) * .5}</li>
    </ul><br/>
    
    <ul>
      <span>Savings (20%): $</span><span>${user.lastSavingsCalculatedBalance *.2}</span>
      <li>401K $${(user.lastSavingsCalculatedBalance *.5) * .4}</li>
      <li>Stocks/Investments $${(user.lastSavingsCalculatedBalance *.5) * .2}</li>
      <li>Savings $${(user.lastSavingsCalculatedBalance *.5) * .4}</li>
    </ul><br/>

    <br/>
    <a href='/deposit'>Deposit</a>
    <a href='/withdraw'>Withdraw</a>
    <a href='/dispute'>Dispute</a>
    <a href='/transfer'>Transfer</a>
    <a href='/login_savings'>Savings Calculator</a>
    <a href='/'>Logout</a>
	</div>
</body>
</html>