package CC;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseM {

	private int Sid;
	private String SName;
	private Double Yid;
	public Connection connection = null;

	public void Semester(int SID, String SName, Double YID) {
		this.Sid = SID;
		this.SName = SName;
		this.Yid = YID;
	}
	public int getSID() {
		return Sid;
	}

	public String getSName() {
		return SName;
	}

	public Double getYId() {
		return Yid;
	}

	public Connection getConnection() {

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test_db", "root", "");
			return connection;
		} catch (Exception e) {
			return null;
		}
	}

	public void Insert(int Sid, String Sname, Double Yid) {

		{
			String sql = "INSERT INTO Semesters (Sid, SName, Yid) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, "Sid");
			statement.setString(2, "SName");
			statement.setDouble(3, "Yid");
			statement.executeUpdate();

		}
	}
	  public void selectAll(int Sid, String Sname, Double Yid){
	        String sql = "SELECT Sid, Sname, Yid FROM Semesters";
	        
	        try (Connection connection = this.getConnection();
	             Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            while (rs.next()) {
	                System.out.println(rs.getInt("Sid") +  "\t" + 
	                                   rs.getString("Sname") + "\t" +
	                                   rs.getDouble("Yid"));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	public void Update(int Sid, String Sname, Double Yid) {
		{
			String sql = "UPDATE Semesters SET Sid = ?" + "SName = ?" + "WHERE Yid = ?";
			PreparedStatement statemente = connection.prepareStatement(sql);
			statemente.setInt(1, "Sid");
			statemente.setString(2, "SName");
			statemente.setDouble(3, "Yid");
			statemente.executeUpdate();
		}
	}

	public void Delete(int Sid, String Sname, int Yid) {
		String sql = "DELETE FROM Semesters WHERE Yid = ?";

		PreparedStatement statemente = connection.prepareStatement(sql);
		statemente.setInt(1, "Sid");
		statemente.setString(2, "SName");
		statemente.setDouble(3, "Yid");
		statemente.executeUpdate();

	}

	public static void main(String[] args) {

		CourseM CourseManagement = new CourseM();
	}
}
