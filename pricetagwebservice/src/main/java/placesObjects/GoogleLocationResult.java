package placesObjects;

import utils.GeoCoordinate;



/**
 * Created by dunha on 16/03/2015.
 */
public class GoogleLocationResult {
    private Double lat;
    private Double lng;
    private Double distance;


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
    
    
    public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public void setDistancia(double latitudeInicial, double longitudeInicial){
		GeoCoordinate local = new GeoCoordinate(latitudeInicial,longitudeInicial);
		GeoCoordinate destino = new GeoCoordinate(getLat(), getLng());
		setDistance(local.distanceInKm(destino));
		
	}

    
    
}
