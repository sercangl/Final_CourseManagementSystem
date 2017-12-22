import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// This is better if it is a singleton 
public class MyDBConnection {
	public Connection connection;
	public PreparedStatement ekle;
	public PreparedStatement studentekle;
	public PreparedStatement courseekle;
	public PreparedStatement gradeekle;
	public PreparedStatement yearekle;
	public PreparedStatement semesterekle;
	public PreparedStatement sectionekle;

	public PreparedStatement studentlistele;
	public PreparedStatement courselistele;
	public PreparedStatement gradelistele;
	public PreparedStatement yearlistele;
	public PreparedStatement semesterlistele;
	public PreparedStatement sectionlistele;
	
	public PreparedStatement studentsilme;
	public PreparedStatement coursesilme;
	public PreparedStatement gradesilme;
	public PreparedStatement yearsilme;
	public PreparedStatement semestersilme;
	public PreparedStatement sectionsilme;
	
	
	
	
	public PreparedStatement listele;
	public static MyDBConnection instance = null;
	MyDBConnection() {
		// A private constructor restricts the creation of an object from this class... 
		connection = null;
	}
	
	// Java is easy. Use synchronized for thread-safety. 
	// This synchronized approach can be improved if it is checked 
	// inside the method. 
	
	
	synchronized public static MyDBConnection getInstance() {
		if(instance == null) {
			instance = new MyDBConnection(); 
			instance.connect();
		}
		return instance;
	}
	
	
	
	
	public void connect() {
		if (connection == null) {
			try {
				File dosya = new File("mydatabase.db");
				boolean firstRun = !dosya.exists(); // if file does not exist, then it is first run
				connection = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
				
				if(firstRun) {
					Statement statement = connection.createStatement();
					statement.executeUpdate("create table mytable(name string)");
					
					statement.executeUpdate("create table student(studentName string,studentId int)");
					statement.executeUpdate("create table course(courseName int,courseId string)");
					statement.executeUpdate("create table grade(gradeType string,gradeNot int)");
					statement.executeUpdate("create table year(yearId int)");
					statement.executeUpdate("create table semester(semesterName string)");
					statement.executeUpdate("create table section(sectionId int,sectionHour int)");
					
					
					
				}
					
				
				studentekle = connection.prepareStatement("insert into student(studentName,studentId) values(?,?)");
				courseekle = connection.prepareStatement("insert into course(courseName,courseId) values(?,?)");
				gradeekle = connection.prepareStatement("insert into grade(gradeType,gradeNot) values(?,?)");
				yearekle = connection.prepareStatement("insert into year(yearId) values(?)");
				semesterekle = connection.prepareStatement("insert into semester(semesterName) values(?)");
				sectionekle = connection.prepareStatement("insert into section(sectionId,sectionHour) values(?,?)");
			
				studentlistele = connection.prepareStatement("select * from student");
				courselistele = connection.prepareStatement("select * from course");
				gradelistele = connection.prepareStatement("select * from grade");
				yearlistele = connection.prepareStatement("select * from year");
				semesterlistele = connection.prepareStatement("select * from semester");
				sectionlistele = connection.prepareStatement("select * from section");
			
				studentsilme = connection.prepareStatement("DELETE FROM student WHERE studentName = ?");
				coursesilme = connection.prepareStatement("DELETE FROM course WHERE courseName = ?");
				gradesilme = connection.prepareStatement("DELETE FROM grade WHERE gradeType = ?");
				yearsilme = connection.prepareStatement("DELETE FROM year WHERE yearId = ?");
				semestersilme = connection.prepareStatement("DELETE FROM semester WHERE semesterName = ?");
				sectionsilme = connection.prepareStatement("DELETE FROM section WHERE sectionId = ?");
				
			
				
			
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Connection to sql failed");
			}
		}
	}
	
	
	public void insertStudent(String n,int i){
		try {
			studentekle.setString(1, n);
			studentekle.setInt(1, i);
			studentekle.execute();
		} catch(SQLException e) {}
		
	}

	public void insertYear(int i){
		try {
			yearekle.setInt(1, i);
			yearekle.execute();
		} catch(SQLException e) {}
		
	}
	
	public void insertCourse(int n,int k){
		try {
			courseekle.setLong(0, k);
			courseekle.setInt(1, n);
			courseekle.execute();
		} catch(SQLException e) {}
		
	}
	
	public void insertGrade(String gradeTypee,int gradeNot) {
	try {
		gradeekle.setString(1, gradeTypee);
		gradeekle.setInt(1, gradeNot);
		gradeekle.execute();
	} catch(SQLException e) {}
		
	}
	
	public void insertSemester(String i){
		try {
			
			semesterekle.setString(0, i);
			semesterekle.execute();
		} catch(SQLException e) {}
	}
 
	public void insertSection(int i,int j){
		try {
		

			sectionekle.setInt(1, i);
			sectionekle.setInt(1, j);
			sectionekle.execute();
		} catch(SQLException e) {}
	}

	public void loopText() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	/*
	public void loopText() {
		try {
			ResultSet rs = listele.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString("name"));
				JOptionPane.showMessageDialog(null, rs.getString("name"));
			}
		} catch(SQLException e) {}
	}
	
	*/
	
	
	
	
	/*
	public ArrayList<String> getList() {
		ArrayList<String> l = new ArrayList<>();
		try {
			ResultSet rs = listele.executeQuery();
			while(rs.next()) {
				l.add(rs.getString("name"));
			}
		} catch(SQLException e) {}
		return l;
	}
*/
	
}
