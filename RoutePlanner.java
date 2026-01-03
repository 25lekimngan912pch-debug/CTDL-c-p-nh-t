package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.*;
import java.util.stream.*;

/**
 * Class RoutePlanner - Tìm đường Metro + Bus YÊU CẦU ĐỀ BÀI: Tìm đường bằng
 * thuật toán (Dijkstra) NÂNG CAO: Kết hợp Metro + Bus
 * 
 * Tính năng: - Dijkstra tìm đường ngắn nhất giữa 2 ga Metro - Tìm ga Metro gần
 * nhất từ tọa độ (lat/long) - Tìm bus kết nối - Gợi ý lộ trình: Bus → Metro →
 * Bus
 * 
 * Java 8: Stream API, Lambda, PriorityQueue, Collectors
 * 
 * @author Metro System Team
 */
public class RoutePlanner {
	private String id;
	private MetroSystem metroSystem;
	private List<Route> plannedRoutes;
	private String status;

	public RoutePlanner(String id, MetroSystem metroSystem) {
		this.id = id;
		this.metroSystem = metroSystem;
		this.plannedRoutes = new ArrayList<>();
		this.status = "ACTIVE";
	}

	/**
	 * Tìm đường Metro bằng Dijkstra YÊU CẦU ĐỀ BÀI
	 */
	public Route findMetroRoute(Station start, Station end) {
		try {
			Map<Station, Double> distances = new HashMap<>();
			Map<Station, Station> previous = new HashMap<>();
			PriorityQueue<StationDistance> pq = new PriorityQueue<>();

			// Init distances
			metroSystem.getStations().forEach(station -> distances.put(station, Double.MAX_VALUE));

			distances.put(start, 0.0);
			pq.offer(new StationDistance(start, 0.0));

			// Dijkstra algorithm
			while (!pq.isEmpty()) {
				StationDistance current = pq.poll();
				Station currentStation = current.station;

				if (currentStation.equals(end))
					break;

				// Duyệt các ga kề
				for (Station neighbor : getNeighborStations(currentStation)) {
					double distance = getDistanceBetween(currentStation, neighbor);
					double newDist = distances.get(currentStation) + distance;

					if (newDist < distances.get(neighbor)) {
						distances.put(neighbor, newDist);
						previous.put(neighbor, currentStation);
						pq.offer(new StationDistance(neighbor, newDist));
					}
				}
			}

			// Build route từ kết quả
			return buildRoute(start, end, previous);

		} catch (Exception e) {
			System.err.println("✗ Lỗi Dijkstra: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Lấy các ga kề nhau
	 */
	private List<Station> getNeighborStations(Station station) {
		List<Station> neighbors = new ArrayList<>();

		for (Route route : metroSystem.getRoutes()) {
			List<Station> stations = route.getStationList();
			int index = stations.indexOf(station);

			if (index >= 0) {
				if (index > 0)
					neighbors.add(stations.get(index - 1));
				if (index < stations.size() - 1)
					neighbors.add(stations.get(index + 1));
			}
		}

		return neighbors;
	}

	/**
	 * Tính khoảng cách giữa 2 ga (Haversine)
	 */
	private double getDistanceBetween(Station s1, Station s2) {
		return calculateDistance(s1.getLatitude(), s1.getLongitude(), s2.getLatitude(), s2.getLongitude());
	}

	/**
	 * Xây dựng Route từ kết quả Dijkstra
	 */
	private Route buildRoute(Station start, Station end, Map<Station, Station> previous) {
		List<Station> path = new ArrayList<>();
		Station current = end;

		while (current != null) {
			path.add(0, current);
			current = previous.get(current);
		}

		if (path.isEmpty() || !path.get(0).equals(start)) {
			return null;
		}

		Route result = new Route("TEMP_" + System.currentTimeMillis(), start.getName() + " - " + end.getName(), null,
				path, 0, false, "FORWARD");

		for (int i = 0; i < path.size() - 1; i++) {
			Station s1 = path.get(i);
			Station s2 = path.get(i + 1);
			double dist = getDistanceBetween(s1, s2);

			RoutePart part = new RoutePart("RP" + i, s1, s2, dist, result, (int) (dist * 2));
			result.addRoutePart(part);
			result.addStation(s1);
		}
		result.addStation(path.get(path.size() - 1));

		return result;
	}

	/**
	 * Tìm ga Metro gần nhất từ tọa độ Sử dụng Stream API
	 */
	public Station findNearestStation(double lat, double lng) {
		return metroSystem.getStations().stream()
				.min(Comparator.comparingDouble(s -> calculateDistance(lat, lng, s.getLatitude(), s.getLongitude())))
				.orElse(null);
	}

	/**
	 * Tìm N ga gần nhất
	 */
	public List<Station> findNearestStations(double lat, double lng, int limit) {
		return metroSystem.getStations().stream()
				.sorted(Comparator.comparingDouble(s -> calculateDistance(lat, lng, s.getLatitude(), s.getLongitude())))
				.limit(limit).collect(Collectors.toList());
	}

	/**
	 * Tìm bus đến ga Metro NÂNG CAO
	 */
	public List<BusRoute> findBusToStation(double startLat, double startLng, Station station) {
		List<BusRoute> result = new ArrayList<>();

		for (BusRoute busRoute : metroSystem.getBusRoutes()) {
			BusStop nearStart = findNearestBusStop(busRoute, startLat, startLng);
			BusStop nearStation = findNearestBusStop(busRoute, station.getLatitude(), station.getLongitude());

			if (nearStart != null && nearStation != null
					&& calculateDistance(startLat, startLng, nearStart.getLatitude(), nearStart.getLongitude()) < 0.5) {
				result.add(busRoute);
			}
		}

		return result;
	}

	/**
	 * Tìm bus từ ga Metro NÂNG CAO
	 */
	public List<BusRoute> findBusFromStation(Station station, double endLat, double endLng) {
		return station.getBusRoute().stream().filter(busRoute -> {
			BusStop nearEnd = findNearestBusStop(busRoute, endLat, endLng);
			return nearEnd != null
					&& calculateDistance(endLat, endLng, nearEnd.getLatitude(), nearEnd.getLongitude()) < 0.5;
		}).collect(Collectors.toList());
	}

	/**
	 * Tìm trạm bus gần nhất trên tuyến
	 */
	private BusStop findNearestBusStop(BusRoute route, double lat, double lng) {
		return route.getBusStops().stream()
				.filter(stop -> calculateDistance(lat, lng, stop.getLatitude(), stop.getLongitude()) < 0.5)
				.min(Comparator
						.comparingDouble(stop -> calculateDistance(lat, lng, stop.getLatitude(), stop.getLongitude())))
				.orElse(null);
	}

	/**
	 * Tính khoảng cách Haversine (km)
	 */
	private double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
		final int R = 6371; // Radius of earth in km

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lng2 - lng1);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c;
	}

	/**
	 * Gợi ý lộ trình Metro + Bus (NÂNG CAO)
	 */
	public RouteResult planRoute(double startLat, double startLng, double endLat, double endLng) {
		try {
			Station nearestStart = findNearestStation(startLat, startLng);
			Station nearestEnd = findNearestStation(endLat, endLng);

			if (nearestStart == null || nearestEnd == null) {
				System.out.println("✗ Không tìm thấy ga gần nhất");
				return null;
			}

			Route metroRoute = findMetroRoute(nearestStart, nearestEnd);

			if (metroRoute == null) {
				System.out.println("✗ Không tìm thấy đường Metro");
				return null;
			}

			List<BusRoute> busToMetro = findBusToStation(startLat, startLng, nearestStart);
			List<BusRoute> busFromMetro = findBusFromStation(nearestEnd, endLat, endLng);

			int totalTime = calculateTotalTime(busToMetro, metroRoute, busFromMetro);
			double totalPrice = calculateTotalPrice(busToMetro, metroRoute, busFromMetro);

			return new RouteResult(busToMetro, metroRoute, busFromMetro, totalTime, totalPrice);

		} catch (Exception e) {
			System.err.println("✗ Lỗi plan route: " + e.getMessage());
			return null;
		}
	}

	private int calculateTotalTime(List<BusRoute> busTo, Route metro, List<BusRoute> busFrom) {
		int time = 0;
		if (!busTo.isEmpty())
			time += busTo.get(0).getDurationMinutes();
		time += metro.getRoutePartList().stream().mapToInt(RoutePart::getEstimatedMinutes).sum();
		if (!busFrom.isEmpty())
			time += busFrom.get(0).getDurationMinutes();
		return time;
	}

	private double calculateTotalPrice(List<BusRoute> busTo, Route metro, List<BusRoute> busFrom) {
		double price = 0;
		if (!busTo.isEmpty())
			price += busTo.get(0).getFarePrice();
		price += metro.getTotalDistance() * 3000;
		if (!busFrom.isEmpty())
			price += busFrom.get(0).getFarePrice();
		return price;
	}

	// Getters
	public String getId() {
		return id;
	}

	public MetroSystem getMetroSystem() {
		return metroSystem;
	}

	public List<Route> getPlannedRoutes() {
		return plannedRoutes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void addPlannedRoute(Route r) {
		plannedRoutes.add(r);
	}

	public void removePlannedRoute(Route r) {
		plannedRoutes.remove(r);
	}

	/**
	 * Inner class - Kết quả tìm đường
	 */
	public static class RouteResult {
		private List<BusRoute> busToMetro;
		private Route metroRoute;
		private List<BusRoute> busFromMetro;
		private int totalTime;
		private double totalPrice;
		private String description;

		public RouteResult(List<BusRoute> busToMetro, Route metroRoute, List<BusRoute> busFromMetro, int totalTime,
				double totalPrice) {
			this.busToMetro = busToMetro;
			this.metroRoute = metroRoute;
			this.busFromMetro = busFromMetro;
			this.totalTime = totalTime;
			this.totalPrice = totalPrice;
			this.description = generateDescription();
		}

		private String generateDescription() {
			StringBuilder sb = new StringBuilder();

			if (!busToMetro.isEmpty()) {
				sb.append("1. Bus ").append(busToMetro.get(0).getName()).append(" → ga ")
						.append(metroRoute.getStationList().get(0).getName()).append("\n");
			} else {
				sb.append("1. Đi bộ → ga ").append(metroRoute.getStationList().get(0).getName()).append("\n");
			}

			sb.append("2. Metro từ ").append(metroRoute.getStationList().get(0).getName()).append(" → ")
					.append(metroRoute.getStationList().get(metroRoute.getStationList().size() - 1).getName())
					.append(" (").append(metroRoute.getStationList().size() - 1).append(" ga)\n");

			if (!busFromMetro.isEmpty()) {
				sb.append("3. Bus ").append(busFromMetro.get(0).getName()).append(" → điểm đến");
			} else {
				sb.append("3. Đi bộ → điểm đến");
			}

			return sb.toString();
		}

		public List<BusRoute> getBusToMetro() {
			return busToMetro;
		}

		public Route getMetroRoute() {
			return metroRoute;
		}

		public List<BusRoute> getBusFromMetro() {
			return busFromMetro;
		}

		public int getTotalTime() {
			return totalTime;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public String getDescription() {
			return description;
		}

		@Override
		public String toString() {
			return String.format("Lộ trình: %d phút, %.0f VNĐ\n%s", totalTime, totalPrice, description);
		}
	}

	@Override
	public String toString() {
		return String.format("RoutePlanner[id=%s, status=%s]", id, status);
	}

	/**
	 * Helper class cho Dijkstra
	 */
	class StationDistance implements Comparable<StationDistance> {
		Station station;
		double distance;

		StationDistance(Station station, double distance) {
			this.station = station;
			this.distance = distance;
		}

		@Override
		public int compareTo(StationDistance other) {
			return Double.compare(this.distance, other.distance);
		}
	}
}