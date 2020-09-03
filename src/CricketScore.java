import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator; 
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CricketScore {
	public static int score;
	public static cScore[] all_score;

	public CricketScore() throws FileNotFoundException, IOException, ParseException 
	{
		URL url = new URL("https://rest.entitysport.com/v2/matches/?status=2&token=ec471071441bb2ac538a0ff901abd249");
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
		JSONObject res = (JSONObject)jobj.get("response");
		JSONArray OuterArr = (JSONArray)res.get("items");
		
		System.out.println("elements under items array");
		int n = OuterArr.size();
		score = n;
		all_score = new cScore[n];
		for(int i=0;i<n;i++) {
			//
			JSONObject obj = (JSONObject)OuterArr.get(i);
			all_score[i] = new cScore();
			System.out.println("id :"+obj.get("match_id"));
			System.out.println("title :"+obj.get("short_title"));
			System.out.println("format :"+obj.get("format_str"));
			System.out.println("subtitle :"+obj.get("subtitle"));
			System.out.println("win :"+obj.get("status_note"));
			
			all_score[i].CS_id= (int)(long)obj.get("match_id");
			all_score[i].title = (String) obj.get("short_title");	
			all_score[i].subtitle=(String) obj.get("subtitle");
			all_score[i].format = (String) obj.get("format_str");
			all_score[i].win=(String) obj.get("status_note");
			
		}
		
	    
	}
}
