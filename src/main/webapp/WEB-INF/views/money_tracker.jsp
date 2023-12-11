<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="https://fanapeel.com/wp-content/uploads/logo_-university-of-maryland-terrapins-testudo-turtle-hold-red-white-m.png">
  <meta charset="ISO-8859-1">
  <title>Money Tracker</title>
  <style type="text/css">
    label {
      display: inline-block;
      width: 200px;
      margin: 5px;
      text-align: left;
    }
    input[type=text], input[type=password], select {
      width: 200px; 
    }
    input[type=radio] {
      display: inline-block;
      margin-left: 45px;
    }
    
    input[type=checkbox] {
      display: inline-block;
      margin-right: 190px;
    }   
    button {
      padding: 10px;
      margin: 10px;
    }
    .slider {
      width: 200px;
    }
  </style>
</head>
<body>
  <div align="center">
    <form:form action="money_tracker" method="post" modelAttribute="user">
      <form:label path="username">Username:</form:label>
      <form:input path="username"/><br/>
      
      <form:label path="password">Password:</form:label>
      <form:password path="password"/><br/>    

      <form:label path="income">Post-Tax Income:</form:label>
      <form:input path="income" id="income"/><br/>  

      <form:button>Submit New Income</form:button>  
    </form:form>
    
    <div id="sliders">
      <label for="mortgageSlider">Mortgage/Rent:</label>
      <input type="range" min="0" max="100" value="30" class="slider" id="mortgageSlider"><br>
      
      <label for="carSlider">Car Payments/Transportation:</label>
      <input type="range" min="0" max="100" value="12" class="slider" id="carSlider"><br>
      
      <label for="retirementSlider">Retirement:</label>
      <input type="range" min="0" max="100" value="13" class="slider" id="retirementSlider"><br>
      
      <label for="emergencySlider">Emergency Fund:</label>
      <input type="range" min="0" max="100" value="5" class="slider" id="emergencySlider"><br>
      
      <label for="foodSlider">Food:</label>
      <input type="range" min="0" max="100" value="10" class="slider" id="foodSlider"><br>
      
      <label for="utilitiesSlider">Utilities/Services:</label>
      <input type="range" min="0" max="100" value="8" class="slider" id="utilitiesSlider"><br>
      
      <label for="insuranceSlider">Insurance:</label>
      <input type="range" min="0" max="100" value="7" class="slider" id="insuranceSlider"><br>
      
      <label for="childCareSlider">Child Care/Education:</label>
      <input type="range" min="0" max="100" value="10" class="slider" id="childCareSlider"><br>
      
      <label for="otherSlider">Other/Discretionary:</label>
      <input type="range" min="0" max="100" value="5" class="slider" id="otherSlider"><br>
    </div>   
    <div id="incomeSplitTable">
    </div>
    <div id="totalPercentage" style="margin-top: 10px;"></div>

    <a href='/'>Home</a>
  </div>
  
  <script>
    function updateTable() {
      var income = parseFloat(document.getElementById('income').value);

      if (!isNaN(income)) {
        var categories = [
          { name: 'Mortgage/Rent', percentage: parseInt(document.getElementById('mortgageSlider').value) },
          { name: 'Car Payments/Transportation', percentage: parseInt(document.getElementById('carSlider').value) },
          { name: 'Retirement', percentage: parseInt(document.getElementById('retirementSlider').value) },
          { name: 'Emergency Fund', percentage: parseInt(document.getElementById('emergencySlider').value) },
          { name: 'Food', percentage: parseInt(document.getElementById('foodSlider').value) },
          { name: 'Utilities', percentage: parseInt(document.getElementById('utilitiesSlider').value) },
          { name: 'Insurance', percentage: parseInt(document.getElementById('insuranceSlider').value) },
          { name: 'Child Care/Education', percentage: parseInt(document.getElementById('childCareSlider').value) },
          { name: 'Other', percentage: parseInt(document.getElementById('otherSlider').value) }
        ];

        var table = '<table border="1"><tr><th>Category</th><th>Allocated Amount</th></tr>';
        var totalPercentage = 0; // Variable to store the total percentage

        for (var i = 0; i < categories.length; i++) {  //Go through each category
          var category = categories[i];
          var allocatedAmount = (income * (category.percentage / 100)).toFixed(2);

          table += '<tr><td>' + category.name + '</td><td>' + allocatedAmount + '</td></tr>';

          totalPercentage += category.percentage; // Calculate total percentage. 
        }

        table += '</table>';
        document.getElementById('incomeSplitTable').innerHTML = table;

        // Create and display total percentage separately
        var totalPercentageDisplay = document.getElementById('totalPercentage');
        totalPercentageDisplay.textContent = 'Total Percentage: ' + totalPercentage + '%';

        //This all happens dynamically on the change of any value of teh income field
        if (totalPercentage > 100) {
          totalPercentageDisplay.style.color = 'red'; // Change text color to red if total exceeds 100%
          totalPercentageDisplay.textContent += ' - You have exceeded 100%'; //Add the text to the end so the user can see what happened. 
        } else {
          totalPercentageDisplay.style.color = ''; //Make the the text go back to the original color. 
        }
      } else { //else case when empty
        document.getElementById('incomeSplitTable').innerHTML = '';
        document.getElementById('totalPercentage').innerHTML = '';
      }
    }

    //Event listeners to check when the sliders have chnaged the value of the percentage. Dynamically changes value in the table as well via the updateTabel function!

    document.getElementById('income').addEventListener('input', updateTable);
    document.getElementById('mortgageSlider').addEventListener('input', updateTable);
    document.getElementById('carSlider').addEventListener('input', updateTable);
    document.getElementById('retirementSlider').addEventListener('input', updateTable);
    document.getElementById('emergencySlider').addEventListener('input', updateTable);
    document.getElementById('foodSlider').addEventListener('input', updateTable);
    document.getElementById('utilitiesSlider').addEventListener('input', updateTable);
    document.getElementById('insuranceSlider').addEventListener('input', updateTable);
    document.getElementById('childCareSlider').addEventListener('input', updateTable);
    document.getElementById('otherSlider').addEventListener('input', updateTable);

    document.addEventListener('DOMContentLoaded', updateTable);
  </script>
</body>
</html>
