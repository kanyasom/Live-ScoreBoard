import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator; 
import java.util.Map;

import java.util.Scanner;

import org.json.simple.parser.*;

import utils.League;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

public class Ranking {
	
	public static int rank_size=0;
	public static Rank[] all_rank;
	private static Connection connection=null;
	
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sports";
	private static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
	private static final String USERNAME="root";
	private static final String PASSWORD="KILLU";
	
	
	public Ranking() throws FileNotFoundException, IOException, ParseException, SQLException {
		int[] var= new int[500];
		int cnt=0;
		try {
			connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			Statement stmt = connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT COUNT(lg_id) FROM leagues;");
            rs.next();
            cnt = rs.getInt("COUNT(lg_id)");
            int [] nvar = new int[cnt];
            int i=0;
            rs = stmt.executeQuery("SELECT lg_id FROM leagues;");
            while(rs.next())
            {
            	nvar[i] = rs.getInt("lg_id");
            	i++;
            }
            var=nvar;
		}catch(SQLException e) {
			System.out.println("OOOOPS");
			e.printStackTrace();
		}
		
		
		//change url val n keep looping
		System.out.println(cnt);
		for(int j=0;j<cnt;j++) {
			System.out.println(j);
			String s = "https://www.thesportsdb.com/api/v1/json/1/lookuptable.php?l="+var[j]+"&s=2018-2019";
			URL url = new URL(s);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
				
			int responseCode = conn.getResponseCode();
			String inline="";
			if(responseCode!=200) {
				throw new RuntimeException("HTTP-RESPONSE-CODE:"+ responseCode );
			}
			else{
					Scanner sc = new Scanner(url.openStream());
								
								while(sc.hasNext()) {
									 inline = inline + sc.nextLine();
								}
				//System.out.println(inline);
				sc.close();
			}
			JSONParser input = new JSONParser();
			if(inline.equals(""))
				continue;
			JSONObject jobj = (JSONObject)input.parse(inline);
			JSONArray OuterArr = (JSONArray)jobj.get("table");
			
			//System.out.println("elements under table array");
			int n = OuterArr.size();
			rank_size = rank_size + n;
			all_rank = new Rank[n];
			for(int i=0;i<n;i++) {
				JSONObject obj = (JSONObject)OuterArr.get(i);
				all_rank[i] = new Rank();
				
				all_rank[i].name = (String)obj.get("name");
				try {
					String in = (String)obj.get("teamid");
					if(!in.isEmpty()) {
						all_rank[i].teamID= Integer.parseInt(in);
					}
					else
					{
						all_rank[i].teamID=0;
						System.out.println("!!!");
					}
				}catch(Exception e) {
					System.out.println("!");
				}
				all_rank[i].league_id = var[j];
			    all_rank[i].games_played= (int)(long)obj.get("played");
			    all_rank[i].goalsfor = (int)(long)obj.get("goalsfor");
			    all_rank[i].goalsagainst = (int)(long)obj.get("goalsagainst");
			    all_rank[i].goalsdifference =(int)(long)obj.get("goalsdifference");
			    all_rank[i].win = (int)(long)obj.get("win");
			    all_rank[i].draw = (int)(long)obj.get("draw");
			    all_rank[i].loss = (int)(long)obj.get("loss");
			    all_rank[i].total = (int)(long)obj.get("total");	
			    
				connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
				Statement stmt = connection.createStatement();

		    	String sql = "INSERT IGNORE INTO ranking " + "VALUES ("+all_rank[i].league_id+",'"+all_rank[i].teamID+"','"+all_rank[i].name+"','"+all_rank[i].games_played+"','"+all_rank[i].goalsfor+"','"+all_rank[i].goalsagainst+"','"+all_rank[i].goalsdifference+"','"+all_rank[i].win+"','"+all_rank[i].draw+"','"+all_rank[i].loss+"','"+all_rank[i].total+"');";
		    	stmt.executeUpdate(sql);

			}
			
		}
		
	    
	}

}
