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

public class DateList {
	public static int dt_size;
	public static date[] all_dt;

	public DateList() throws FileNotFoundException, IOException, ParseException 
	{
		URL url = new URL("https://cricapi.com/api/matchCalendar?apikey=3CraSchrK8ZX3jNfEG4O5EX4ooe2");
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
			sc.close();
		}
		
		
		JSONParser input = new JSONParser();
		JSONObject jobj = (JSONObject)input.parse(inline);
		JSONArray OuterArr = (JSONArray)jobj.get("data");
		
		System.out.println("elements under DATA array");
		int n = OuterArr.size();
		dt_size = n;
		all_dt = new date[500];
		for(int i=0;i<n;i++) {
			//
			JSONObject obj = (JSONObject)OuterArr.get(i);
			all_dt[i] = new date();
			System.out.println("id :"+obj.get("unique_id"));
			System.out.println("name :"+obj.get("name"));
			System.out.println("date :"+obj.get("date"));
			
			all_dt[i].unique_id= ((String)obj.get("unique_id"));
			all_dt[i].name = (String) obj.get("name");
			all_dt[i].dates = (String) obj.get("date");
			
		}
		
	    
	}

}
