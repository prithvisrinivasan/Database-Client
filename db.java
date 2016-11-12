	import java.sql.*;
	import java.util.Scanner;
	import java.util.*;
	public class db {
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/project3-nudb";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	  // public char charAt(int);
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	    System.out.print("Enter ID:");
	    Scanner id = new Scanner (System.in);
	    String username = id.nextLine();
	    System.out.print("Password:");
	    Scanner password = new Scanner (System.in);
	    String psswd = password.nextLine();
	    System.out.print("You int " +psswd);
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      
	      
	     // public class login_details 
	   
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT Password from Student WHERE ID = "  + username;
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	        // int i  = rs.getInt("id");
	      //   int age = rs.getInt("name");
	       String first = rs.getString("Password");
	      System.out.print(first);
	//   int index =0;
	    //  for (int index = 0; index != first.length() ; index ++ ){
	       	    // char[] c = first.toCharArray();
	            //char[] n = psswd.toCharArray();
	    	//    char m = first.charAt(index);
	    	  //  char j = psswd.charAT(index);
	    	    //System.out.println(psswd.charAt(0));
	    	   if (psswd.equals(first)){
	    		   System.out.println("Logging in succesful");
	    		   System.out.println("Select an option from the Student Menu below:\n 1)Personal Details \n 2)Transcript \n 3)Courses \n 4)Enroll \n 5)Withdraw \n >>");
	    		   Scanner smenu = new Scanner (System.in);
	    		   String stdmenu = smenu.nextLine();
	    		   if(stdmenu.equals("1") || stdmenu.equals("2") || stdmenu.equals("3") || stdmenu.equals("4") || stdmenu.equals("5") ){
	    			   
	    			   System.out.println("Good option"); 
	    		   }
	    		   else {
	    			   System.out.println("please try again!");
	    		   }
	    	   }
	    	   
	    	   else 
	    	   {
	    		   
	    		   System.out.print("Enter the right username and password!");
	    	   }
	    	//System.out.print("Check the character at the index " +index);	   
	    	  	   
	      // } 
	      
	       //  String last = rs.getString("last");

	         //Display values
	        //System.out.print("ID: " + i);
	      //   System.out.print(", Age: " + age);
	        // System.out.print("Password " + first);
	        // System.out.println(", Last: " + last);
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
	   //System.out.println("Goodbye!");
	}//end main
	}//end FirstExample
		
	


