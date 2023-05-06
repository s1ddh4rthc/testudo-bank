import pymysql
import names
import random
import string
from credentials import mysql_endpoint, username, password, database_name

# Connect to local MySQL Server and delete a DB called 'testudo_bank'
connection = pymysql.connect(host=mysql_endpoint, user=username, passwd = password, db=database_name)
cursor = connection.cursor()

delete_credit_table_sql = '''
  DROP TABLE Credit;
  '''

cursor.execute(delete_credit_table_sql)

connection.commit()
cursor.close()