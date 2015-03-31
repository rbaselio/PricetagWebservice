package placesObjects;

import java.util.ArrayList;
import java.util.List;

public class MyPlacesJson {
	private List<MyPlaces> myplaces;
	private String status;
	
	
	
	public List<MyPlaces> getMyplaces() {
		return myplaces;
	}
	public void setMyplaces(List<MyPlaces> myplaces) {
		this.myplaces = myplaces;
	}
	
	
	public void setMyplacesList(List<GooglePlaceResult> googlePlaces) {
		myplaces = new ArrayList<MyPlaces>();
		for (int i = 0; i < googlePlaces.size();i++){
			GooglePlaceResult temp = googlePlaces.get(i);
			
			
			myplaces.add(new MyPlaces(temp.getId(), 
										temp.getName(),
										temp.getPlace_id(), 
										temp.getReference(), 
										"foto",
										temp.getIcon(),
										temp.getVicinity(), 
										temp.getGeometry().getLocationResult().getLat(), 
										temp.getGeometry().getLocationResult().getLng(),
										temp.getGeometry().getLocationResult().getDistance()
										)
						);	
					
		}
		setStatus("GOOGLE");
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	

}
