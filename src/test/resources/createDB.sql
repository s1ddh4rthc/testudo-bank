CREATE TABLE Customers (
  CustomerID varchar(255),
  FirstName varchar(255),
  LastName varchar(255),
  Balance int,
  OverdraftBalance int,
  NumFraudReversals int,
  NumDepositsForInterest int
);

CREATE TABLE Passwords (
  CustomerID varchar(255),
  Password varchar(255)
);

CREATE TABLE OverdraftLogs (
  CustomerID varchar(255),
  Timestamp DATETIME,
  DepositAmt int,
  OldOverBalance int,
  NewOverBalance int
);

CREATE TABLE TransactionHistory (
  CustomerID varchar(255),
  Timestamp DATETIME,
  Action varchar(255) CHECK (Action IN ('Deposit', 'Withdraw', 'TransferSend', 'TransferRecieve', 'CryptoBuy', 'CryptoSell')),
  Amount int
);

CREATE TABLE TransferHistory (
  TransferFrom varchar(255),
  TransferTo varchar(255),
  Timestamp DATETIME,
  Amount int
);

CREATE TABLE CryptoHoldings (
  CustomerID varchar(255),
  CryptoName varchar(255),
  CryptoAmount decimal(30,18)
);

CREATE TABLE CryptoHistory (
  CustomerID varchar(255),
  Timestamp DATETIME,
  Action varchar(255) CHECK (Action IN ('Buy', 'Sell')),
  CryptoName varchar(255),
  CryptoAmount decimal(30,18)
);

CREATE TABLE SavingsAccounts (
  AccountID VARCHAR(255) PRIMARY KEY,
  CustomerID VARCHAR(255) NOT NULL,
  Balance INT,
  InterestRate FLOAT
);

CREATE TABLE SavingsGoals (
  GoalID VARCHAR(255) PRIMARY KEY,
  AccountID VARCHAR(255) NOT NULL,
  GoalName VARCHAR(255),
  TargetAmount INT,
  CurrentAmount INT,
  Deadline DATETIME
);

-- Insert initial data
INSERT INTO Customers (CustomerID, FirstName, LastName, Balance, OverdraftBalance, NumFraudReversals, NumDepositsForInterest)
VALUES ('C001', 'John', 'Doe', 10000, 0, 0, 0);

INSERT INTO SavingsAccounts (AccountID, CustomerID, Balance, InterestRate)
VALUES ('A001', 'C001', 5000, 0.01);

INSERT INTO Passwords (CustomerID, Password)
VALUES ('C001', 'password123');

-- More initial data as required for your tests
