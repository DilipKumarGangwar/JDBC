/*  JDBC Demo
 * 
 * STEPS:
 * import package 
 * load and register the driver      -mysql.connector jar file needed
 * Establish the connection         
 * Create the statement
 * Exceute the query
 * Process the result
 *  close
 * 
 */

import java.sql.*;

public class JdbcDemo {

	static String url = "jdbc:mysql://localhost:3306/student_details";
	static String username = "root";
	static String password ="root";
	
	static String query1= "select * from student where age=14";
	static String query2= "insert into student values ( 22,'Ramesh','Kishore',24)";
	
	static int rollno =50;
	static String firstname="Mukesh";
	static String lastname="Jain";
	static int age= 25;
	
	static String query3= "insert into student values (" + rollno  + ",'" + firstname + "','" + lastname + "','" + age + "')";  //DML
	
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");       // Load and register the driver,  com.mysql.jdbc is package, Driver is the class 
		Connection con =  DriverManager.getConnection(url,username, password);
		Statement st =  con.createStatement();
		ResultSet rs = st.executeQuery(query1);  //DQL
		while(rs.next()) {
		
		   String data  = rs.getInt(1) + ":" + rs.getString(2)+ ":" + rs.getString(3)+ ":" + rs.getString(4);
		   System.out.println( data);
		}
		
		int result = st.executeUpdate(query2);    //DML
		System.out.println(result +" rows affected"); 
		
		result = st.executeUpdate(query3);    //DML
		System.out.println(result +" rows affected"); 
		
		 st.close();   //close connection
		 con.close();
	}
}
