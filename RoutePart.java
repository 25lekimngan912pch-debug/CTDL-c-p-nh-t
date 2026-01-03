package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.Optional;
import java.util.function.Predicate;

//Doan tuyen giua 2 ga lien tiep
public class RoutePart {
	private String id; // Ma dinh danh doan tuyen
	private Station beginStation; // Ga bat dau cua doan tuyen
	private Station endStation; // Ga ket thuc cua doan tuyen
	private double distanceKm; // Khoang cach giua 2 ga (km)
	private Route route; // Tuyen chua doan tuyen nay
	private int estimatedMinutes; // Thoi gian di chuyen uoc tinh

	public RoutePart(String id, Station beginStation, Station endStation, double distanceKm, Route route,
			int estimatedMinutes) {
		this.id = Optional.ofNullable(id).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("ID khong duoc rong!"));
		this.beginStation = Optional.ofNullable(beginStation)
				.orElseThrow(() -> new IllegalArgumentException("Ga dau la bat buoc!"));
		this.endStation = Optional.ofNullable(endStation)
				.orElseThrow(() -> new IllegalArgumentException("Ga cuoi la bat buoc!"));
		if (beginStation.equals(endStation)) {
			throw new IllegalArgumentException("Ga dau va ga cuoi phai khac nhau");
		}
		if (distanceKm <= 0) {
			throw new IllegalArgumentException("Khoang cach (km) phải > 0 !");
		}

		if (estimatedMinutes <= 0) {
			throw new IllegalArgumentException("Thoi gian di chuyen uoc tinh phải > 0 !");
		}
		this.distanceKm = distanceKm;
		this.route = route;
		this.estimatedMinutes = estimatedMinutes;
	}

	public RoutePart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Station getBeginStation() {
		return beginStation;
	}

	public void setBeginStation(Station beginStation) {
		this.beginStation = beginStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public int getEstimatedMinutes() {
		return estimatedMinutes;
	}

	public void setEstimatedMinutes(int minutes) {
		if (minutes <= 0) {
			throw new IllegalArgumentException("Thoi gian phai > 0");
		}
		this.estimatedMinutes = minutes;
		System.out.println("Da cap nhat thoi gian: " + minutes + " phut");
	}

	/**
	 * Kiem tra ga co trong chang nay khong Su dung Java 8: Predicate
	 * 
	 * @param station ga can kiem tra
	 * @return true neu ga la beginStation hoac endStation
	 */
	public boolean contains(Station station) {
		Predicate<Station> isBeginOrEnd = s -> s != null && (s.equals(beginStation) || s.equals(endStation));

		return isBeginOrEnd.test(station);
	}

	/**
	 * Tinh toc do trung binh tren doan tuyen nay
	 * 
	 * @return toc do (km/h)
	 */
	public double getAverageSpeed() {
		// Toc do = khoang cach / thoi gian (chuyen phut thanh gio)
		double timeInHours = estimatedMinutes / 60.0;
		return distanceKm / timeInHours;
	}

	/**
	 * Tinh thoi gian di chuyen voi toc do tuy chinh
	 * 
	 * @param speed toc do (km/h)
	 * @return thoi gian (phut)
	 * @throws IllegalArgumentException neu speed sai
	 */
	public int calculateTimeWithSpeed(double speed) {
		if (speed < 20 || speed > 80) {
			throw new IllegalArgumentException("Toc do phai trong khoang tu 20 km/h den 80 km/h");
		}

		double timeInHours = distanceKm / speed;
		return (int) Math.ceil(timeInHours * 60);
	}

	/**
	 * Lay ga con lai trong doan tuyen
	 * 
	 * @param station ga da biet
	 * @return ga con lai, neu station khong thuoc doan tuyen thi tra ve null
	 */
	public Station getOtherStation(Station station) {
		return Optional.ofNullable(station).filter(this::contains)
				.map(s -> s.equals(beginStation) ? endStation : beginStation).orElse(null);
	}

	 /**
     * So sanh voi doan tuyen khac theo khoang cach
     * 
     * @param other Doan tuyen khac
     * @return -1, 0, 1
     */
    public int compareByDistance(RoutePart other) {
        return Double.compare(this.distanceKm, other.distanceKm);
    }
    
    /**
     * So sanh voi doan tuyen khac theo thoi gian
     * 
     * @param other Doan tuyen khac
     * @return -1, 0, 1
     */
    public int compareByTime(RoutePart other) {
        return Integer.compare(this.estimatedMinutes, other.estimatedMinutes);
    }

	@Override
	public String toString() {
		return "RoutePart [id=" + id + ", beginStation=" + beginStation + ", endStation=" + endStation + ", distanceKm="
				+ distanceKm + ", route=" + route + ", estimatedMinutes=" + estimatedMinutes + "]";
	}
    
	//TEST
	public static void main(String[] args) {
		System.out.println("====================TEST ROUTPART==========");
	}
    
}
