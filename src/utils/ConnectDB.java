package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class ConnectDB {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sports";
	private static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
	private static final String USERNAME="root";
	private static final String PASSWORD="KILLU";
	
	private static Connection connection=null;
	private static Statement stmt = null;
	static{
        try
        {
            System.out.println("searching for driver");
            Class.forName(DRIVER_NAME);
            
        }
        catch(ClassNotFoundException cnfe)
        {      
        	System.out.println("OOPS! CHECK DRIVER");
        }
    }
	
	public ConnectDB() {
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
			/*Leagues all = new Leagues();
		    for(int i=0;i<all.lg_size;i++) {
		    	String sql = "INSERT INTO leagues " + "VALUES ("+all.all_league[i].league_id+","+all.all_league[i].league_name+","+all.all_league[i].league_sports+","+all.all_league[i].league_alt+")";
			    stmt.executeUpdate(sql);
		    }*/
			
			
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            connection.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(connection!=null)
		            connection.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		 }
	}
}
