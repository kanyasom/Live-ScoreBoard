import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
//import org.json.JSONArray;
//import org.json.JSONObject;
import java.util.Scanner;

import org.json.simple.parser.*;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

public class Matches {
	public static int mt_size;
	public static Match[] all_match;

	public Matches() throws FileNotFoundException, IOException, ParseException 
	{
		URL url = new URL("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4460");
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
		JSONArray OuterArr = (JSONArray)jobj.get("events");
	
		System.out.println("elements under events array");
		int n = OuterArr.size();
		mt_size = n;
		all_match = new Match[n];
		for(int i=0;i<n;i++) {
			//
			JSONObject obj = (JSONObject)OuterArr.get(i);
			all_match[i] = new Match();
			System.out.println("id :"+obj.get("idEvent"));
			System.out.println("Team :"+obj.get("strFilename"));
			System.out.println("Type:"+obj.get("strEvent"));
			
			
			
			all_match[i].match_id= Integer.parseInt((String) obj.get("idEvent"));
			all_match[i].teams = (String) obj.get("strFilename");
			all_match[i].type = (String) obj.get("strEvent");
			all_match[i].year = Integer.parseInt((String) obj.get("strSeason"));
			
			
		}
		
	    
	}

}
