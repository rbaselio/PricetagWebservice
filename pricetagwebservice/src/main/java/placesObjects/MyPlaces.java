package placesObjects;



public class MyPlaces {
		private String id;
		private String name;
		private String place_id;
		private String reference;
		private String photos;
		private String icon;
		private String endereco;		
		private Double latitude;
		private Double longitude;
		private Double distance;
		
		
				
		
		public MyPlaces(String id, String name,
				String place_id, String reference, String photos, String icon,
				String endereco, Double latitude, Double longitude, Double distance) {
			super();
			this.id = id;
			this.name = name;
			this.icon = icon;
			this.place_id = place_id;
			this.reference = reference;
			this.photos = photos;
			this.endereco = endereco;
			this.latitude = latitude;
			this.longitude = longitude;
			this.distance = distance;
		}
		
		
		public Double getDistance() {
			return distance;
		}


		public void setDistance(Double distance) {
			this.distance = distance;
		}


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
		public String getPhotos() {
			return photos;
		}
		public void setPhotos(String photos) {
			this.photos = photos;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public Double getLatitude() {
			return latitude;
		}
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}
		public Double getLongitude() {
			return longitude;
		}
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}
	    
	
	
	
	
	
	
	
	

}
