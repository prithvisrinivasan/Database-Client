package database_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Withdraw {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/project3-nudb";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "N4408550";
	
	//private String withdraw_ccode;
	private String withdraw_studId;
	private String yearNow;
	private String quarterNow;
	
	public Withdraw(String studId, String year, String quarter){
		//withdraw_ccode = c_code;
		withdraw_studId = studId;
		yearNow = year;
		quarterNow = quarter;
	}
	
	public void WithdrawCourse(String c_code){
		Connection conn = null;
		Statement stmt = null;
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		   
		      //STEP 3: Open a connection
		      //System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      //STEP 4: Execute a query
		      //System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "call withdraw('" + c_code + "', " + withdraw_studId + ", "+ yearNow + ", '" + quarterNow + "')";
		      System.out.println(sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	}
	
	public boolean checkCourseEnroll(String c_code){
		Connection conn = null;
		Statement stmt = null;
		boolean is_enroll = false;
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		   
		      //STEP 3: Open a connection
		      //System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      //STEP 4: Execute a query
		      //System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT UoSCode from transcript WHERE StudId = "  + withdraw_studId + " and Year = " + yearNow + " and Semester = '" + quarterNow + "' and Grade is null";
		      //System.out.println(sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){ 
		    	  String row = rs.getString("UoSCode");
		    	  //System.out.println(row);
		    	  if (c_code.equals(row)){
		    		  is_enroll = true;
		    	  }
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return is_enroll;
	}
	

}
