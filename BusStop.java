package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.ArrayList;
import java.util.List;

public class BusStop {
    private String id;
    private String name;
    private String location;
    private double latitude;
    private double longitude;
    private List<BusRoute> routes;
    private boolean isActive;
    private Station nearbyStation;  // Ga Metro gan nhat
    private double distanceToStation;

    public BusStop(String id, String name, String location,
                   double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.routes = new ArrayList<>();
        this.isActive = true;
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


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public List<BusRoute> getRoutes() {
		return routes;
	}


	public void setRoutes(List<BusRoute> routes) {
		this.routes = routes;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Station getNearbyStation() {
		return nearbyStation;
	}


	public void setNearbyStation(Station nearbyStation) {
		this.nearbyStation = nearbyStation;
	}


	public double getDistanceToStation() {
		return distanceToStation;
	}


	public void setDistanceToStation(double distanceToStation) {
		this.distanceToStation = distanceToStation;
	}


	/**
     * Set toa do
     */
    public void setCoordinates(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    /**
     * Them tuyen bus
     */
    public void addRoute(BusRoute route) {
        if (!routes.contains(route)) {
            routes.add(route);
        }
    }

    /**
     * Xoa tuyen bus
     */
    public void removeRoute(BusRoute route) {
        routes.remove(route);
    }

    /**
     * Tinh khoang cach den ga metro
     */
    public double calculateDistanceToStation() {
        if (nearbyStation == null) return 0.0;
        
        final int R = 6371; // Radius of earth in km
        
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(nearbyStation.getLatitude());
        double lon1 = Math.toRadians(longitude);
        double lon2 = Math.toRadians(nearbyStation.getLongitude());
        
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        this.distanceToStation = R * c;
        return distanceToStation;
    }

    

    @Override
    public String toString() {
        return String.format("BusStop[%s: %s, %.4f,%.4f, %d tuyến]",
            id, name, latitude, longitude, routes.size());
    }
}
