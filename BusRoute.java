package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.ArrayList;
import java.util.List;

public class BusRoute {
    private String id;
    private String name;
    private String info;
    private List<BusStop> busStops;
    private double distanceKm;
    private int durationMinutes;
    private boolean isActive;
    private int frequency;  // So chuyen/h
    private String operatingHours;
    private double farePrice;

    public BusRoute(String id, String name,String info, double distanceKm, 
                    int durationMinutes, double farePrice) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
        this.farePrice = farePrice;
        this.busStops = new ArrayList<>();
        this.isActive = true;
        this.frequency = frequency;  
        this.operatingHours = "05:00-22:00";
    }
    

    public BusRoute(String id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.busStops = new ArrayList<>();
        this.isActive = true;
        this.operatingHours = "05:00-22:00";
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


	public List<BusStop> getBusStops() {
		return busStops;
	}


	public void setBusStops(List<BusStop> busStops) {
		this.busStops = busStops;
	}


	public double getDistanceKm() {
		return distanceKm;
	}


	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}


	public int getDurationMinutes() {
		return durationMinutes;
	}


	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public String getOperatingHours() {
		return operatingHours;
	}


	public void setOperatingHours(String operatingHours) {
		this.operatingHours = operatingHours;
	}


	public double getFarePrice() {
		return farePrice;
	}


	public void setFarePrice(double farePrice) {
		this.farePrice = farePrice;
	}


	/**
     * Them tram bus
     */
    public void addBusStop(BusStop busStop) {
        if (!busStops.contains(busStop)) {
            busStops.add(busStop);
            busStop.addRoute(this);
        }
    }

    /**
     * Xoa tram bus
     */
    public void removeBusStop(BusStop busStop) {
        busStops.remove(busStop);
    }

    /**
     * Tim tram bus theo ten
     */
    public BusStop findBusStopByName(String name) {
        return busStops.stream().filter(stop -> stop.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    /**
     * Lay tram tiep thao
     */
    public BusStop getNextBusStop(BusStop current) {
        int index = busStops.indexOf(current);
        return (index >= 0 && index < busStops.size() - 1) 
            ? busStops.get(index + 1) 
            : null;
    }

    /**
     * Lay tram truoc
     */
    public BusStop getPreviousBusStop(BusStop current) {
        int index = busStops.indexOf(current);
        return (index > 0) ? busStops.get(index - 1) : null;
    }
    
}