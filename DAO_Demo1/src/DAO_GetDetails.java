/*Objective: 
 * 1.Client gives the Roll No and it gets the all details of that student from database.
 * 2.Insert new Student in database with given RollNo  and then print Complete students Details from database
  The Database activities are separated into DAO1 class
*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class student{
	
	 int rollno;
	 String firstName;
	 String lastName;
     int age;
     
    public student()
    {
    	
    }
	public student(int rollno, String firstName, String lastName, int age) {
		super();
		this.rollno = rollno;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName= firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
     

	@Override
	public String toString() {
		return "student [rollno=" + rollno + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ "]";
	}
}

class DAO1{
	
	String url = "jdbc:mysql://localhost:3306/student_details";
	String username = "root";
	String password ="root";
	Connection con = null;
	void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");  // For  old version , you can use - Class.forName("com.mysql.cj.jdbc.Driver") 
	    con = (Connection) DriverManager.getConnection(url, username, password);
	}
	student getDetails(int rollno) throws Exception {
		
		     String query = "select * from student where rollno=" + rollno;
		     Statement st = con.createStatement();
		     ResultSet rs = st.executeQuery(query);  //Execute query to fetch details
		     rs.next();
		     
		     //Prepare student object 
		     student s = new student();
		     s.setRollno(rollno);
		     s.setFirstName(rs.getString(2));
		     s.setLastName(rs.getString(3));
		     s.setAge(rs.getInt(4));
		    
		     //return these details to client
		     return s;
	}
	
	int addNewStudentToDatabase(student s) throws Exception {
		
		String query = "insert into student values (?,?,?,?)";
		PreparedStatement st = con.prepareStatement(query);
		
		st.setInt(1, s.getRollno());
		st.setString(2,s.getFirstName());
		st.setString(3, s.getLastName());
		st.setInt(4, s.getAge());
		
		//Execute query
		
		int rowsAffected = st.executeUpdate();
		
		return rowsAffected;
		
		
		
	}
	
	List<student> getAllStudentsDetails() throws Exception{
		String query = "select * from student";
		Statement st =  con.createStatement();
		ResultSet rs = st.executeQuery(query);  //DQL
		List<student> stu = new ArrayList<student>();
		while(rs.next()) {
		
		   stu.add(new student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		   
		}
		
		return stu;
	}
	
	
	
}



//Client

public class DAO_GetDetails {

	public static void main(String[] args) throws Exception {
		
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter roll no of student whose Details you want: ");
	    int rollno = sc.nextInt();
		DAO1 d1 = new DAO1();
	    
		d1.connectToDatabase();
		//Fetch details from Database
		student student1 = d1.getDetails(rollno);
		
		//Print details of this  student
		student1.toString();
		
		
		
		//Now Insert new Student in database and then  print Complete students Details from database
		student student2 = new student();
		
		System.out.println("\nEnter  New student  Details you want to Add in Database: ");
		System.out.println("Enter rollno ");
		student2.setRollno(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter First name ");
		student2.setFirstName(sc.nextLine());
		System.out.println("Enter Last Name ");
		student2.setLastName(sc.nextLine());
		System.out.println("Enter Age ");
		student2.setAge(sc.nextInt());
		
		int rowsAffected = d1.addNewStudentToDatabase(student2);
		
		if (rowsAffected > 0)
			System.out.println("Details Updated in Database successfully");
		else {
			System.out.println("Error in Details Updation!!");
		}
		 
		
		//Show Complete Students details from Database
		List<student> list = d1.getAllStudentsDetails();
		
		System.out.println("\n*******Students Details*****");
		for (student student : list) {
			
			System.out.println(student.toString());
		}
		
		
		sc.close();    //close the scanner
		  
	
		

	}

}
