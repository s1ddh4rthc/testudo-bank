<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>View Budget - Testudo Bank</title>
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
    table {
        border: 2px solid;
    }
  </style>
</head>
<body>
	<div align="center">
		<h2><span>${user.firstName}</span> <span>${user.lastName}</span> Suggested Budget</h2>
    <br/>
    <table> 
        <tr> 
            <th>
                Necessary Spending
            </th>
            <td>
                ${user.needsBudget}
            </td>
        </tr>
        <tr> 
            <th>
                Discretionary Spending
            </th>
            <td>
                ${user.wantsBudget}
            </td>
        </tr>
        <tr> 
            <th>
                Savings and Debt Re-payment
            </th>
            <td>
                ${user.savingsBudget}
            </td>
        </tr>
    </table>
    <a href='/deposit'>Deposit</a>
    <a href='/withdraw'>Withdraw</a>
    <a href='/dispute'>Dispute</a>
    <a href='/transfer'>Transfer</a>
    <a href='/budget'>View Budget</a>
    <a href='/'>Logout</a>
	</div>
</body>
</html>