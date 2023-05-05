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
    .buttonGameValidReward {
        background-color: white; 
        color: black; 
        border: 2px solid #4CAF50;
    }
    .buttonGameValidReward:hover {
        background-color: #4CAF50;
        color: white;
    }
    .buttonGameInvalidReward {
        background-color: white; 
        color: black; 
        border: 2px solid #ff0000;
    }
    .buttonGameInvalidReward:hover {
        background-color: #ff0000;;
        color: white;
    }
    .buttonGameRedeemedReward {
        background-color: white; 
        color: black; 
        border: 2px solid #808080;
    }
    .buttonGameRedeemedReward:hover {
        background-color: #808080;;
        color: white;
    }
    table, th, td {
      border: 1px solid black;
    }
  </style>
</head>
<body>
  <span id="hiddenNumWeeklyRewards" style="display: none;">${user.numWeeklyRewards}</span>
  <span id="hiddenUsername" style="display: none;">${user.username}</span>
  <script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function(event) { 
      document.getElementById('username').setAttribute('value', document.getElementById("hiddenUsername").innerHTML);
      document.getElementById('numWeeklyRewards').setAttribute('value', document.getElementById("hiddenNumWeeklyRewards").innerHTML);
      for (let i = 1; i <= 52; i++) {
        var numWeeklyRewards = parseInt(document.getElementById("hiddenNumWeeklyRewards").innerHTML) + 1;
        if (i < numWeeklyRewards) {
          document.getElementById("week" + i).classList.add('buttonGameRedeemedReward');
          document.getElementById("week" + i).disabled = true;
        }
        else if (i == numWeeklyRewards) {
          document.getElementById("week" + i).classList.add('buttonGameValidReward');
        }
        else {
          document.getElementById("week" + i).classList.add('buttonGameInvalidReward');
          document.getElementById("week" + i).disabled = true;
        }
      }
    });
  </script>
	<div align="center">
    <form action="weeklyrewards_redeem" method="post" modelAttribute="user">
      <input type="text" id="username" style="display: none;" name="username">
      <input type="text" id="numWeeklyRewards" style="display: none;" name="numWeeklyRewards"><br>
      <table>
        <thead>
          <tr>
              <th colspan="4">Weekly Rewards!</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><button id="week1">Week 1</button></td>
            <td><button id="week2">Week 2</button></td>
            <td><button id="week3">Week 3</button></td>
            <td><button id="week4">Week 4</button></td>
          </tr>
          <tr>
            <td><button id="week5">Week 5</button></td>
            <td><button id="week6">Week 6</button></td>
            <td><button id="week7">Week 7</button></td>
            <td><button id="week8">Week 8</button></td>
          </tr>
          <tr>
            <td><button id="week9">Week 9</button></td>
            <td><button id="week10">Week 10</button></td>
            <td><button id="week11">Week 11</button></td>
            <td><button id="week12">Week 12</button></td>
          </tr>
          <tr>
            <td><button id="week13">Week 13</button></td>
            <td><button id="week14">Week 14</button></td>
            <td><button id="week15">Week 15</button></td>
            <td><button id="week16">Week 16</button></td>
          </tr>
          <tr>
            <td><button id="week17">Week 17</button></td>
            <td><button id="week18">Week 18</button></td>
            <td><button id="week19">Week 19</button></td>
            <td><button id="week20">Week 20</button></td>
          </tr>
          <tr>
            <td><button id="week21">Week 21</button></td>
            <td><button id="week22">Week 22</button></td>
            <td><button id="week23">Week 23</button></td>
            <td><button id="week24">Week 24</button></td>
          </tr>
          <tr>
            <td><button id="week25">Week 25</button></td>
            <td><button id="week26">Week 26</button></td>
            <td><button id="week27">Week 27</button></td>
            <td><button id="week28">Week 28</button></td>
          </tr>
          <tr>
            <td><button id="week29">Week 29</button></td>
            <td><button id="week30">Week 30</button></td>
            <td><button id="week31">Week 31</button></td>
            <td><button id="week32">Week 32</button></td>
          </tr>
          <tr>
            <td><button id="week33">Week 33</button></td>
            <td><button id="week34">Week 34</button></td>
            <td><button id="week35">Week 35</button></td>
            <td><button id="week36">Week 36</button></td>
          </tr>
          <tr>
            <td><button id="week37">Week 37</button></td>
            <td><button id="week38">Week 38</button></td>
            <td><button id="week39">Week 39</button></td>
            <td><button id="week40">Week 40</button></td>
          </tr>
          <tr>
            <td><button id="week41">Week 41</button></td>
            <td><button id="week42">Week 42</button></td>
            <td><button id="week43">Week 43</button></td>
            <td><button id="week44">Week 44</button></td>
          </tr>
          <tr>
            <td><button id="week45">Week 45</button></td>
            <td><button id="week46">Week 46</button></td>
            <td><button id="week47">Week 47</button></td>
            <td><button id="week48">Week 48</button></td>
          </tr>
          <tr>
            <td><button id="week49">Week 49</button></td>
            <td><button id="week50">Week 50</button></td>
            <td><button id="week51">Week 51</button></td>
            <td><button id="week52">Week 52</button></td>
          </tr>    
        </tbody>
      </table>
    </form>
      <a href='/'>Home</a>
	</div>
</body>
</html>