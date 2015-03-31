package placesObjects;

import java.util.List;

/**
 * Created by dunha on 16/03/2015.
 */
public class GooglePlaceResult {

    private GoogleGeometry geometry;
    private String icon;
    private String id;
    private String name;
    private String place_id;
    private String reference;
    private String scope;
    private List<GooglePhoto> photos ;
    private List<String> types;
    private String vicinity;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void setGeometry(GoogleGeometry geometry) {
        this.geometry = geometry;
    }

    public GoogleGeometry getGeometry() {
        return geometry;
    }

    public String toString(){
        return name;
    }

	public List<GooglePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<GooglePhoto> photos) {
		this.photos = photos;
	}
    
    
}
