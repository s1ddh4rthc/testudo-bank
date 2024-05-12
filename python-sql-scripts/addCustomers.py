import pymysql
import names
import random
import string
from credentials import mysql_endpoint, username, password, database_name

# SQL Config Values
num_customers_to_add = 100

# Connect to testudo_bank db in local MySQL Server
connection = pymysql.connect(host=mysql_endpoint, user=username, passwd=password, db=database_name)
cursor = connection.cursor()

# Create the Customers table with an additional column for RoundupBalance
create_customer_table_sql = '''
CREATE TABLE Customers (
  CustomerID varchar(255),
  FirstName varchar(255),
  LastName varchar(255),
  Balance int,
  OverdraftBalance int,
  NumFraudReversals int,
  NumDepositsForInterest int,
  RoundupEnabled BOOLEAN DEFAULT FALSE,
  RoundupBalance int DEFAULT 0 
);
'''
cursor.execute(create_customer_table_sql)

# Create the Passwords table
create_password_table_sql = '''
CREATE TABLE Passwords (
  CustomerID varchar(255),
  Password varchar(255)
);
'''
cursor.execute(create_password_table_sql)

# Create the OverdraftLogs table
create_overdraftlogs_table_sql = '''
CREATE TABLE OverdraftLogs (
  CustomerID varchar(255),
  Timestamp DATETIME,
  DepositAmt int,
  OldOverBalance int,
  NewOverBalance int
);
'''
cursor.execute(create_overdraftlogs_table_sql)

# Create the TransactionHistory table with an additional column for RoundupAmount
create_transactionhistory_table_sql = '''
CREATE TABLE TransactionHistory (
  CustomerID varchar(255),
  Timestamp DATETIME,
  Action varchar(255) CHECK (Action IN ('Deposit', 'Withdraw', 'TransferSend', 'TransferReceive', 'CryptoBuy', 'CryptoSell')),
  Amount int,
  RoundupAmount int DEFAULT 0
);
'''
cursor.execute(create_transactionhistory_table_sql)

# Make empty Transfer table
create_transferhistory_table_sql = '''
CREATE TABLE TransferHistory (
  TransferFrom varchar(255),
  TransferTo varchar(255),
  Timestamp DATETIME,
  Amount int
);
'''
cursor.execute(create_transferhistory_table_sql)

# Make empty CryptoHoldings table
create_cryptoholdings_table_sql = '''
CREATE TABLE CryptoHoldings (
  CustomerID varchar(255),
  CryptoName varchar(255),
  CryptoAmount decimal(30,18)
);
'''
cursor.execute(create_cryptoholdings_table_sql)


# Make empty CryptoHistory table
create_cryptohistory_table_sql = '''
CREATE TABLE CryptoHistory (
  CustomerID varchar(255),
  Timestamp DATETIME,
  Action varchar(255) CHECK (Action IN ('Buy', 'Sell')),
  CryptoName varchar(255),
  CryptoAmount decimal(30,18)
);
'''
cursor.execute(create_cryptohistory_table_sql)


# Generate random customers and add them to the database
get_all_ids_sql = '''SELECT CustomerID FROM Customers;'''
cursor.execute(get_all_ids_sql)
ids_in_db = set(cursor.fetchall())

ids_just_added = set()
for i in range(num_customers_to_add):
    customer_id = ''.join(random.choices(string.digits, k=9))
    if customer_id not in ids_in_db and customer_id not in ids_just_added:
        customer_first_name = names.get_first_name()
        customer_last_name = names.get_last_name()
        customer_balance = random.randint(100, 10000) * 100  # Balance in pennies
        customer_password = ''.join(random.choices(string.ascii_letters + string.digits, k=9))
        
        insert_customer_sql = f'''
        INSERT INTO Customers (CustomerID, FirstName, LastName, Balance, OverdraftBalance, NumFraudReversals, NumDepositsForInterest, RoundupEnabled, RoundupBalance)
        VALUES ('{customer_id}', '{customer_first_name}', '{customer_last_name}', {customer_balance}, 0, 0, 0, FALSE, 0);
        '''
        cursor.execute(insert_customer_sql)
        
        insert_password_sql = f'''
        INSERT INTO Passwords (CustomerID, Password)
        VALUES ('{customer_id}', '{customer_password}');
        '''
        cursor.execute(insert_password_sql)
        
        ids_just_added.add(customer_id)

connection.commit()
cursor.close()
