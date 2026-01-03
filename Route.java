package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Tuyen metro: Ben Thanh - Suoi Tien
public class Route {
	private String id; // Ma dinh danh tuyen
	private String name; // Ten tuyen
	private List<RoutePart> routePartList; // Danh sach cac doan tuyen
	private List<Station> stationList; // Danh sach cac ga theo thu tu
	private double totalDistance; // Tong khoang cach (km)
	private boolean isActive; // Trang thai hoat dong
	private String direction; // Chieu di: "FORWARD" hoac "BACKWARD"

	public Route(String id, String name, List<RoutePart> routePartList, List<Station> stationList, double totalDistance,
			boolean isActive, String direction) {
		this.id = Optional.ofNullable(id).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("ID khong duoc rong!"));
		this.name = Optional.ofNullable(name).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("Ten tuyen khong duoc rong"));
		this.routePartList = new ArrayList<>();
		this.stationList = new ArrayList<>();
		this.totalDistance = totalDistance;
		this.isActive = isActive;
		this.direction = Optional.ofNullable(direction).filter(d -> d.equals("FORWARD") || d.equals("BACKWARD"))
				.orElseThrow(() -> new IllegalArgumentException("Chieu di phai la FORWARD hoac BACKWARD"));
	}

	public Route() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<RoutePart> getRoutePartList() {
		return routePartList;
	}

	public void setRoutePartList(List<RoutePart> routePartList) {
		this.routePartList = routePartList;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean status) {
		this.isActive = status;
		String statusOther = status ? "da hoat dong tro lai" : "tam ngung hoat dong";
		System.out.println("⚠️ Tuyến " + name + " đã " + statusOther);
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * Tinh tong khoang cach cua chuyen
	 * 
	 * 
	 * @return Tong khoang cach (km)
	 */
	public double calculateTotalDistance() {
		totalDistance = routePartList.stream().mapToDouble(RoutePart::getDistanceKm).sum();
		System.out.println("Tuyen " + name + " có tổng khoảng cách: " + totalDistance + " km");
		return totalDistance;

	}

	/**
	 * Them doan tuyen Tu dong them cac ga vao stationList neu chua co
	 * 
	 * @param part Doan tuyen can them
	 */
	public void addRoutePart(RoutePart part) {
		if (routePartList.contains(part)) {
			throw new IllegalArgumentException("RoutePart da ton tai trong tuyen");
		}
		routePartList.add(part);
		part.setRoute(this);
		// Tu dong them ga neu chua co
		Stream.of(part.getBeginStation(), part.getEndStation()).filter(Objects::nonNull)
				.filter(station -> !stationList.contains(station)).forEach(stationList::add);
		// Tinh lai tong khoang cach
		calculateTotalDistance();
		System.out.println("Da them doan " + part + " vao tuyen " + name);

	}

	/**
	 * Xoa doan tuyen
	 * 
	 * @param part Doan tuyen can xoa
	 * @return true neu xoa thanh cong
	 */
	public boolean removeRoutePart(RoutePart part) {
		if (routePartList.remove(part)) {
			calculateTotalDistance();
			System.out.println("Da xoa doan " + part + " khoi tuyen " + name);
			return true;
		}
		return false;
	}

	/**
	 * Them ga
	 * 
	 * @param station Ga can them
	 * @throws IllegalArgumentException neu station da ton tai
	 */
	public void addStation(Station station) {
		if (stationList.contains(station)) {
			throw new IllegalArgumentException("Ga " + station.getName() + " da ton tai trong tuyen");
		}
		stationList.add(station);
		System.out.println("Da them ga " + station.getName() + " vao tuyen " + name);
	}

	/**
	 * Xoa ga khoi tuyen
	 * 
	 * @param station Ga can xoa
	 * @return true neu xoa thanh cong
	 */
	public boolean removeStation(Station station) {
		if (stationList.remove(station)) {
			System.out.println("Da xoa ga " + station.getName() + " khoi tuyen " + name);
			return true;
		}
		return false;
	}

	/**
	 * Tim ga theo ten (Khong phan biet chu hoa hay chu thuong)
	 * 
	 * @param name Ten ga can tim
	 * @return Station neu tim thay, null neu khong
	 */
	public Station findStationByName(String name) {
		return Optional.ofNullable(name)
				.flatMap(n -> stationList.stream().filter(s -> s.getName().equalsIgnoreCase(n)).findFirst())
				.orElse(null);
	}

	/**
	 * Tim ga theo ID
	 * 
	 * @param id ID ga can tim
	 * @return Station neu tim thay, null neu khong
	 */
	public Station findStationById(String id) {
		return Optional.ofNullable(id).flatMap(i -> stationList.stream().filter(s -> s.getId().equals(i)).findFirst())
				.orElse(null);
	}

	/**
	 * Lay ga tiep theo
	 * 
	 * @param station Ga hien tai
	 * @return Ga tiep theo, null neu khong co hoac day la ga cuoi
	 */
	public Station getNextStation(Station station) {
		int index = stationList.indexOf(station);
		return (index != -1 && index < stationList.size() - 1) ? stationList.get(index + 1) : null;
	}

	/**
	 * Lay ga truoc
	 * 
	 * @param station Ga hien tai
	 * @return Ga truoc do, null neu khong co hoac la ga dau
	 */
	public Station getPreviousStation(Station station) {
		int index = stationList.indexOf(station);
		return (index > 0) ? stationList.get(index - 1) : null;
	}

	/**
	 * Lay ci tri cua ga
	 * 
	 * @param station Ga can tim
	 * @return Vi tri cua ga, -1 neu khong thay
	 */
	public int getStationIndex(Station station) {
		return stationList.indexOf(station);
	}

	/**
	 * Lay doan tuyen giua 2 ga
	 * 
	 * @param start Ga dau
	 * @param end   Ga cuoi
	 * @return RoutePart neu tim thay, null neu khong
	 */
	public RoutePart getRoutePartBetween(Station start, Station end) {
		return routePartList.stream()
				.filter(part -> (part.getBeginStation().equals(start) && part.getEndStation().equals(end))
						|| (part.getBeginStation().equals(end) && part.getEndStation().equals(start)))
				.findFirst().orElse(null);
	}

	/**
	 * Kiem tra tuyen co chua ga khong
	 * 
	 * @param station Ga can kiem tra
	 * @return true neu tuyen chua ga
	 */
	public boolean containsStation(Station station) {
		return Optional.ofNullable(station).map(stationList::contains).orElse(false);
	}

	/**
	 * Lay danh sach ten cac ga
	 * 
	 * @return List ten ga
	 */
	public List<String> getStationNames() {
		return stationList.stream().map(Station::getName).collect(Collectors.toList());
	}

	/**
	 * Kiem tra tuyen co hop le khong
	 * 
	 * @return true neu tuyen hop le (co it nhat 2 ga)
	 */
	public boolean isValid() {
		return stationList.size() >= 2;
	}

}
