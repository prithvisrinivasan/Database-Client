package database_project;

import java.sql.*;
import java.util.Scanner;
import java.util.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class db {
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/project3-nudb";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "N4408550";
	   
	   public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   boolean isLog = false;
		   
		   //  Determine the year and the quarter
		   Date date=new Date();  
		   SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		   String time=formatter.format(date);  
		   String year = time.substring(0,4);
		   String monthStr = time.substring(5,7);
		   int month = Integer.parseInt(monthStr);
		   String quarter = "";
		   if (month >= 9 && month <= 12){
			   quarter = "Q1";
		   }
		   if (month >= 1 && month <= 3){
			   quarter = "Q2";
		   }
		   if (month >= 4 && month <= 6){
			   quarter = "Q3";
		   }
		   if (month >= 7 && month <= 8){
			   quarter = "Q4";
		   }
		   System.out.println(year);
		   System.out.println(quarter);
		   System.out.println(time);
		   
		   System.out.print("Enter ID:");
		   Scanner id = new Scanner (System.in);
		   String username = id.nextLine();
		   System.out.print("Password:");
		   Scanner password = new Scanner (System.in);
		   String psswd = password.nextLine();
		   
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver"); 
		   
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      if (username.equals("")){
		    	  sql = "SELECT Password from Student WHERE ID is null ";
		      }
		      else{
		    	  sql = "SELECT Password from Student WHERE ID = "  + username;
		      }
		      //sql = "call test()";
		      //System.out.println(sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      //System.out.println(rs.next());
		      if (rs.next() && psswd.equals(rs.getString("Password"))){
		    	  isLog = true;
		      }
		      else{
		    	  System.out.print("Please enter the right username and password!");
		      }
		      
		      /*while(rs.next()){
		    	  //Retrieve by column name
		          String first = rs.getString("Password");
		    	  if (psswd.equals(first)){
		    		  System.out.print("got it right"); 
		    	  }
		    	  else 
		    	  {
		    		  System.out.print("Enter the right username and password!");
		    	  }
		      }*/
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
		   
		   Scanner tmp = null;
		   
		   while(isLog){
			   System.out.println("W --- Withdraw");
		       System.out.println("P --- Personal Details");
		       System.out.println("L --- Log out");
		       System.out.print("Please enter your option:");
		       tmp = new Scanner (System.in);
			   String option = tmp.nextLine();
			   if (option.equals("W")){
				   // Withdraw test
				   Withdraw w = new Withdraw(username, year, quarter);
				   System.out.print("Please input the code of the course you want to withdraw:");
				   String withdraw_ccode = tmp.nextLine();
				   //System.out.println(withdraw_ccode);
				   if (w.checkCourseEnroll(withdraw_ccode)){
					   w.WithdrawCourse(withdraw_ccode);
					   System.out.println("The course has been withdrawn!");
				   }
				   else{
					   System.out.println("You can only withdraw a course that you are enrolled in and have not finished.");
				   }
			   }
			   if (option.equals("P")){
				   PersonalDetails p = new PersonalDetails(username);
				   System.out.print("MaximumPreferredEnrollment:");
				   System.out.println(p.getMaxPreferredEnroll());
				   System.out.print("NonPreferredClassroomType:");
				   System.out.println(p.getNonPreferredClassroomType());
				   System.out.println("");
				   System.out.println("C --- Change the details");
				   System.out.println("B --- Back to Menu");
				   System.out.print("Please enter your option:");
				   String detailOption = tmp.nextLine();
				   if (detailOption.equals("C")){
					   System.out.print("Please input the new MaximumPreferredEnrollment:");
					   String maxPE = tmp.nextLine();
					   p.setMaxPreferredEnroll(maxPE);
					   System.out.print("Please input the new NonPreferredClassroomType:");
					   String nonPCT = tmp.nextLine();
					   p.setNonPreferredClassroomType(nonPCT);
					   System.out.println("The Detail has changed!");
					   System.out.print("MaximumPreferredEnrollment:");
					   System.out.println(p.getMaxPreferredEnroll());
					   System.out.print("NonPreferredClassroomType:");
					   System.out.println(p.getNonPreferredClassroomType());
				   }
			   }
			   if (option.equals("L")){
				   // Log out
				   System.out.println("See you next time!");
				   isLog = false;
			   }
		   }
		   password.close();
		   id.close();
		   if (tmp != null){
			   tmp.close();
		   }
		   //System.out.println("Goodbye!");
	}//end main
}//end FirstExample