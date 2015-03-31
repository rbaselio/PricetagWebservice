package placesObjects;


import java.util.List;
/**
 * Created by dunha on 16/03/2015.
 */
public class GooglePlace {
    private List<String> html_attributions;
    private String next_page_token;
    public List<GooglePlaceResult> results;
    private String status;


    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GooglePlaceResult> getResult() {
        return results;
    }

    public void setResult(List<GooglePlaceResult> result) {
        this.results = result;
    }

    public void setDistances(String coordenadas){
    	GooglePlaceResult tempplace;
    	GoogleGeometry tempgeo;
    	GoogleLocationResult temploc;
    	String a[] = coordenadas.split(",");		
    	double minhalat = Double.parseDouble(a[0]);
    	double minhalong = Double.parseDouble(a[1]);
    	
    	for(int i = 0; i< results.size(); i++){
    		tempplace = results.get(i);
    		tempgeo = tempplace.getGeometry();
    		
    		temploc = tempgeo.getLocationResult();
    		temploc.setDistancia(minhalat, minhalong);
    		
    		tempgeo.setLocationResult(temploc);
    		tempplace.setGeometry(tempgeo);
    		
    		results.set(i, tempplace);   		
    		
    		
    	}
    }
    
    
    
}
