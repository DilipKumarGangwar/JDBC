# JDBC is an API for accessing relational databases using Java.

java Application <----------- > Connectivity via Driver <------------------> Database

# Steps in connecting Java Aplication to Database (Here MySQL)
 * import package 
 * load and register the driver      -mysql.connector jar file needed
 * Establish the connection         
 * Create the statement
 * Exceute the query
 * Process the result
 * Close the connection

# preparedStatement() makes Insertion  syntax easy

# DAO- Data Access object

DAO can do CRUD operations, it can Create, Retreive, Updata, Delete from our table
Here we have separted all database operation in DAO class


