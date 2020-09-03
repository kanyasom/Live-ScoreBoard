package utils;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator; 
import java.util.Map;
//import org.json.JSONArray;
//import org.json.JSONObject;
import java.util.Scanner;

import org.json.simple.parser.*;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

public class Leagues {
	
	public static int lg_size;
	public static League[] all_league;

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
	{
		URL url = new URL("https://www.thesportsdb.com/api/v1/json/1/all_leagues.php");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
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
		JSONArray OuterArr = (JSONArray)jobj.get("leagues");
		
		System.out.println("elements under leagues array");
		int n = OuterArr.size();
		lg_size = n;
		all_league = new League[n];
		for(int i=0;i<n;i++) {
			//
			JSONObject obj = (JSONObject)OuterArr.get(i);
			
			System.out.println("id :"+obj.get("idLeague"));
			System.out.println("leaguename :"+obj.get("strLeague"));
			System.out.println("sport :"+obj.get("strSport"));
			System.out.println("img :"+obj.get("strLeagueAlternate"));
			
			all_league[i].league_id= (int) obj.get("idLeague");
			all_league[i].league_name = (String) obj.get("strLeague");
			all_league[i].league_sports = (String) obj.get("strSport");
			all_league[i].league_alt =  (String) obj.get("strLeagueAlternate");
			
		}
		
	    
	}

}
