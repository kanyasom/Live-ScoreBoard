import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.util.Pair;

public class Player {

	public static play[] all_player; 
	public static int pl_size;
	
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sports";
	private static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
	private static final String USERNAME="root";
	private static final String PASSWORD="KILLU";
	
	private static Connection connection=null;
	private static Statement stmt = null;
	
	
	public Player() throws FileNotFoundException, IOException, ParseException, java.text.ParseException, SQLException 
	{
		ArrayList <String> name = new ArrayList <String> ();

		 name.add("Danny Welbeck");
		 name.add("Mohamed Salah");
		 name.add("Harry Kane");
		 name.add("Frank Lampard");
		 name.add("Ashley Cole");
		 name.add("Steven Gerrard");
		 name.add("Lionel Messi");
		 name.add("Cristiano Ronaldo");
		 name.add("Neymar");
		 name.add("Diego Maradona");
		 name.add("Ronaldinho");
		 name.add("Cristiano Ronaldo");
		 name.add("Paul Pogba");
		 name.add("Eden Hazard");
		 name.add("Raphael Varane");
		 name.add("Florian Thauvin");
		 name.add("Gareth Bale");
		 
		 
		 for(int k=0;k<name.size();k++) {
			 
			 URL url;
			 String[] tnamea = name.get(k).split(" ");
			 if(tnamea.length<2) {
				 url = new URL("https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?p="+tnamea[0]);
			 }else {
				 url = new URL("https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?p="+tnamea[0]+"%20"+tnamea[1]);
			 }
			
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				System.out.println(url);
				int responseCode = conn.getResponseCode();
				String inline="";
				if(responseCode!=200) {
					throw new RuntimeException("HTTP-RESPONSE-CODE:"+ responseCode );
				}
				else {
					Scanner sc = new Scanner(url.openStream());
					
					while(sc.hasNext()) {
						 inline = inline + sc.nextLine();
					}
					//System.out.println(inline);
					sc.close();
				}
				
				JSONParser input = new JSONParser();
				JSONObject jobj = (JSONObject)input.parse(inline);
				JSONArray OuterArr = (JSONArray)jobj.get("player");
				
				System.out.println("elements under player array");
				int n = OuterArr.size();
				pl_size = n;
				all_player = new play[n];
				for(int i=0;i<n;i++) {
					//
					JSONObject obj = (JSONObject)OuterArr.get(i);
					all_player[i] = new play();
					
					System.out.println("id :"+obj.get("idTeam"));
					System.out.println("player :"+obj.get("strPlayer"));
					System.out.println("team :"+obj.get("strTeam"));
					System.out.println("date signed :"+obj.get("dateSigned"));
					System.out.println("desc :"+obj.get("strDescription"));
					
					all_player[i].idTeam= Integer.parseInt((String)obj.get("idTeam"));
					all_player[i].strPlayer = (String) obj.get("strPlayer");
					all_player[i].strTeam = (String) obj.get("strTeam");
					String strDescription = (String) obj.get("strDescriptionEN");
					String substr = strDescription.substring(0, 150);
					all_player[i].strDescription= substr ;
					all_player[i].dateSigned =  (String) obj.get("dateSigned");
					
					connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
					Statement stmt = connection.createStatement();
					
					String sql = "INSERT IGNORE INTO player " + "VALUES ('"+all_player[i].idTeam+"','"+all_player[i].strPlayer+"','"+all_player[i].strTeam+"','"+all_player[i].dateSigned+"','"+all_player[i].strDescription+"');";
		    		System.out.println(sql);
					stmt.executeUpdate(sql);
	
				}
		
		 }
	}
		
}
