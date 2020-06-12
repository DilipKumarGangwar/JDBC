import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatementDemo {

	static String url = "jdbc:mysql://localhost:3306/student_details";
	static String username = "root";
	static String password ="root";
	
	static int rollno =100;
	static String firstname="Mukesh";
	static String lastname="Jain";
	static int age= 25;
	
	static String query1= "insert into student values (?,?,?,?)";  //DML
	static String query2= "select * from student ";
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");       // Load and register the driver,  com.mysql.jdbc is package, Driver is the class 
		
		//DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());  Instead of Class.forName(), we can use the actual method also
		
		Connection con =  DriverManager.getConnection(url,username, password);
		PreparedStatement st =  con.prepareStatement(query1);  
		
		st.setInt(1, rollno);
		st.setString(2, firstname);
		st.setString(3,lastname );
		st.setInt(4, age);
				
		int result = st.executeUpdate();    //DML
		System.out.println(result +" rows affected");
		
		Statement st2 =  con.createStatement();
		ResultSet rs = st2.executeQuery(query2);  //DQL
		while(rs.next()) {
		
		   String data  = rs.getInt(1) + ":" + rs.getString(2)+ ":" + rs.getString(3)+ ":" + rs.getString(4);
		   System.out.println( data);
		}
		
		st.close();   //close connection
		con.close();
		
	}

}
