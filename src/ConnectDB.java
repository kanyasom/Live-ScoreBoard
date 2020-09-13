

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class ConnectDB {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sports";
	private static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
	private static final String USERNAME="****";
	private static final String PASSWORD="*****";
	
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
	public ConnectDB()
	{
		//fillLeagues();
		//fillRanking();
		//fillMatch();
	     //fillDate();
		//fillCricketScore();
		//fillBasketball();
		fillPlayer();
	}
	
	public void fillPlayer() {
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
			Player all = new Player();
		    
		    System.out.println("Done!");
			
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
	
	public void fillBasketball() {
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
			Basketball all = new Basketball();
		    for(int i=0;i<all.bl_size;i++) {
		    		String sql = "INSERT IGNORE INTO basketball " + "VALUES ('"+all.all_ball[i].id+"','"+all.all_ball[i].name+"','"+all.all_ball[i].team+"','"+all.all_ball[i].season+"','"+all.all_ball[i].win+"','"+all.all_ball[i].loss+"','"+all.all_ball[i].games+"','"+all.all_ball[i].goal+"');";
		    		stmt.executeUpdate(sql);
		    	
		    }
		    System.out.println("Done!");
			
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
	public void fillCricketScore() {
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
			CricketScore all = new CricketScore();
		    for(int i=0;i<all.score;i++) {
		    		String sql = "INSERT IGNORE INTO cricket " + "VALUES ('"+all.all_score[i].CS_id+"','"+all.all_score[i].title+"','"+all.all_score[i].subtitle+"','"+all.all_score[i].format+"','"+all.all_score[i].win+"');";
		    		stmt.executeUpdate(sql);
		    	
		    }
		    System.out.println("Done!");
			
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
	public void fillLeagues() {
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
			Leagues all = new Leagues();
		    for(int i=0;i<all.lg_size;i++) {
		    	//if not needed 
		    	if(all.all_league[i].league_sports.equals("Basketball") ||all.all_league[i].league_sports.equals("Cricket") ||all.all_league[i].league_sports.equals("Motorsport") ||all.all_league[i].league_sports.equals("Soccer"))
		    	{
		    		String sql = "INSERT IGNORE INTO leagues " + "VALUES ('"+all.all_league[i].league_id+"','"+all.all_league[i].league_name+"','"+all.all_league[i].league_sports+"','"+all.all_league[i].league_alt+"');";
		    		stmt.executeUpdate(sql);
		    	}
		    }
		    System.out.println("Done!");
			
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
	
	public void fillDate() {
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
		    DateList all = new DateList();
		    for(int i=0;i<all.dt_size;i++) {
		    	String sql = "INSERT IGNORE INTO date " + "VALUES ('"+all.all_dt[i].unique_id+"','"+all.all_dt[i].name+"','"+all.all_dt[i].dates+"');";
		    		stmt.executeUpdate(sql); 	
		    }
		    System.out.println("Done!");
			
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

	
	
	
	
	public void fillMatch() {
		
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
			Matches all = new Matches();
		    for(int i=0;i<all.mt_size;i++) {
		    	//if not needed 
		    		String sql = "INSERT IGNORE INTO matches " + "VALUES ('"+all.all_match[i].match_id+"','"+all.all_match[i].type+"','"+all.all_match[i].teams+"','"+all.all_match[i].year+"');";
		    		stmt.executeUpdate(sql);
		    	
		    }
		    System.out.println("Done!");
			
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

    public void fillRanking() {
    	//composite primary key -->league_id(lg_id) and team_id
    	try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("CONNECTED TO DB");
			stmt = connection.createStatement();
			//Execute all query and put into respective tables
			Ranking all = new Ranking();
			
			/*System.out.println(all.rank_size);
			System.out.println("Start Insert");
		    for(int i=0;i<all.rank_size;i++) {
		    	String sql = "INSERT INTO ranking " + "VALUES ("+all.all_rank[i].league_id+",'"+all.all_rank[i].teamID+"','"+all.all_rank[i].name+"','"+all.all_rank[i].games_played+"','"+all.all_rank[i].goalsfor+"','"+all.all_rank[i].goalsagainst+"','"+all.all_rank[i].goalsdifference+"','"+all.all_rank[i].win+"','"+all.all_rank[i].draw+"','"+all.all_rank[i].loss+"','"+all.all_rank[i].total+"');";
		    	stmt.executeUpdate(sql);
		    	System.out.println(i);
		    }*/
		    System.out.println("Done!");
			
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
  

