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

public class Basketball {
	public static int bl_size;
	public static ball[] all_ball;

	public Basketball() throws FileNotFoundException, IOException, ParseException 
	{
		URL url = new URL("https://api.sportsdata.io/v3/nba/scores/json/TeamSeasonStats/2019?key=376891e1abec44f4a4565e30943b2e4e");
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
		//JSONObject jobj = (JSONObject)input.parse(inline);
		//JSONArray OuterArr = (JSONArray)jobj.get("leagues");
		JSONArray  ar = (JSONArray)input.parse(inline);
		
		
		int n = ar.size();
		bl_size = n;
		all_ball = new ball[n];
		for(int i=0;i<n;i++) {
			
			JSONObject obj = (JSONObject)ar.get(i);
			all_ball[i] = new ball();
			System.out.println("id :"+obj.get("StatID"));
			System.out.println("name :"+obj.get("Name"));
			
			
			all_ball[i].id= (int)(long)(obj.get("StatID"));
			all_ball[i].name = (String) obj.get("Name");
			all_ball[i].team = (String) obj.get("Team");
			all_ball[i].season = (int)(long) obj.get("Season");
			all_ball[i].win = (int)(long) obj.get("Wins");
			all_ball[i].loss = (int)(long) obj.get("Losses");
			all_ball[i].games = (int)(long) obj.get("Games");
			all_ball[i].goal = (int)(long)(double)obj.get("FieldGoalsMade");
			
			
		}
		
	    
	}

}
