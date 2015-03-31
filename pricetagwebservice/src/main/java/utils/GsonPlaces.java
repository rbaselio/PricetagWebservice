package utils;



import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import placesObjects.GooglePlace;

/**
 * Created by dunha on 13/02/2015.
 */
public class GsonPlaces  { 
	
	private GooglePlace places;
	Reader reader;
	
	String url = "https://maps.googleapis.com/maps/api/place/search/json";
    Map<String, String> params = new HashMap<String, String>();   
    private  Gson gson = new Gson(); 
    
    
 public GooglePlace getAllPlaces(String coordenadas) throws IOException { 	 
	 	
		params.put("location", coordenadas);
		params.put("rankby", "distance");
		params.put("type", "grocery_or_supermarket");
		params.put("sensor", "true");
		params.put("key", "AIzaSyA3mNfzyHZ4K4pviZStxXCCQBemWoXgkBg");
        
        reader = new InputStreamReader(new URL(getUrl(url, params)).openStream());
        places = gson.fromJson(reader, new GooglePlace().getClass());
        return places;        	 
    }
 
	 public static String getUrl(String webserviceUrl, Map<String, String> params) {
		 if (params == null) return webserviceUrl;
		
	     
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : params.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
		return webserviceUrl + "?" + paramsString;
	 }
 
}
    
    
    
