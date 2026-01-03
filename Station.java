package LeThiKimNgan_24130197_TranThiCamTu_24130350;
//Ga tau

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Station {
	private String id; // Ma dinh danh ga
	private String name; // Ten ga
	private String location; // Dia chi
	private double latitude; // Vi do
	private double longitude; // Kinh do
	private List<BusRoute> busRoutes; // Danh sach tuyen bus ket noi
	private List<String> facilities; // Danh sach tien ich o ga
	private Map<String, Boolean> facilityStatus; // Trang thai tien ich
	private boolean isTerminal; // Co phai ga dau hoac cuoi khong
	private String openingHours; // Gio mo cua
	private Map<String, WaitingQueue> waitingQueues; // Hang cho
	private volatile int passengerCount; // So luong khach hien tai cua ga

	public Station(String id, String name, String location, double latitude, double longitude, List<BusRoute> busRoutes,
			List<String> facilities, Map<String, Boolean> facilityStatus, boolean isTerminal, String openingHours,
			Map<String, WaitingQueue> waitingQueues, int passengerCount) {
		this.id = Optional.ofNullable(id).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("ID khong duoc rong!"));
		this.name = Optional.ofNullable(name).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("Ten ga khong duoc rong!"));
		;
		this.location = location;
		if (latitude < -90 || latitude > 90) {
			throw new IllegalArgumentException("Vi do phải từ -90 đến 90");
		}
		if (longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException("Kinh do phải từ -180 đến 180");
		}
		this.latitude = latitude;
		this.longitude = longitude;
		this.busRoutes = new ArrayList<>();
		this.facilities = new ArrayList<>();
		this.facilityStatus = new HashMap<>();
		this.isTerminal = isTerminal;
		this.openingHours = openingHours;
		this.waitingQueues = new HashMap<>();
		this.passengerCount = passengerCount;
	}

	public Station(String id, String name, String location, double latitude, double longitude, boolean isTerminal) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isTerminal = isTerminal;
		this.busRoutes = new ArrayList<>();
		this.facilities = new ArrayList<>();
		this.facilityStatus = new HashMap<>();
		this.waitingQueues = new HashMap<>();
		this.passengerCount = 0;
		this.openingHours = "05:00-22:00";
	}

	public Station() {
		// TODO Auto-generated constructor stub
		this.waitingQueues = new HashMap<>();
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

	public List<BusRoute> getBusRoute() {
		return busRoutes;
	}

	public void setBusRoute(List<BusRoute> busRoute) {
		this.busRoutes = busRoute;
	}

	public List<String> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<String> facilities) {
		this.facilities = facilities;
	}

	public Map<String, Boolean> getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(Map<String, Boolean> facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public boolean isTerminal() {
		return isTerminal;
	}

	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
		System.out.println("Ga " + name + " " + (isTerminal ? "la" : "khong la") + " ga dau/cuoi");
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public Map<String, WaitingQueue> getWaitingQueues() {
		return waitingQueues;
	}

	public void setWaitingQueues(Map<String, WaitingQueue> waitingQueues) {
		this.waitingQueues = waitingQueues;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	/**
	 * Cap nhat toa do
	 * 
	 * @param latNew Vi do moi
	 * @param lonNew Kinh do moi
	 */
	public void setCoordinates(double latNew, double lonNew) {
		if (latNew < -90 || latNew > 90 || lonNew < -180 || lonNew > 180) {
			throw new IllegalArgumentException("Toa do khong hop le");
		}
		this.latitude = latNew;
		this.longitude = lonNew;
		System.out.println("Da cap nhat toa do tai ga " + name);
	}

	/**
	 * Them tuyen bus
	 */
	public void addBusRoute(BusRoute route) {
		if (!busRoutes.contains(route)) {
			busRoutes.add(route);
			System.out.println("Da them tuyen " + route.getName());
		}
	}

	/**
	 * Xoa tuyen bus
	 */
	public boolean removeBusRoute(BusRoute route) {
		if (busRoutes.remove(route)) {
			System.out.println("Da xoa tuyen " + route.getName() + " khoi ga " + name);
			return true;
		}
		return false;
	}

	/**
	 * Them tien ich
	 * 
	 * @param facilityName Ten tien ich
	 */
	public void addFacility(String facilityName) {
		if (!facilities.contains(facilityName)) {
			facilities.add(facilityName);
			facilityStatus.put(facilityName, true); // Mac dinh available
			System.out.println("Da them tien ich: " + facilityName + " vao ga " + name);
		}
	}

	/**
	 * Xoa tien ich
	 */
	public boolean removeFacility(String facilityName) {
		if (facilities.remove(facilityName)) {
			facilityStatus.remove(facilityName);
			System.out.println("Da xoa tien ich: " + facilityName + " khoi ga " + name);
			return true;
		}
		return false;
	}

	// Kiem tra ga co mo cua hay khong
	public boolean isOpen(Calendar time) {
		// Cho kieu cua openingHours la "5:00-23:00"
		if (openingHours == null || openingHours.isEmpty()) {
			return true; // Mac dinh luon mo
		}

		try {
			String[] hours = openingHours.split("-");
			String[] startTime = hours[0].split(":");
			String[] endTime = hours[1].split(":");

			int startHour = Integer.parseInt(startTime[0]);
			int endHour = Integer.parseInt(endTime[0]);
			int currentHour = time.get(Calendar.HOUR_OF_DAY);

			return currentHour >= startHour && currentHour < endHour;
		} catch (Exception e) {
			return true; // Neu sai format thi la mo
		}
	}

	/**
	 * Lay hang cho cho chuyen tau cu the
	 */
	public WaitingQueue getWaitingQueueForSchedule(ScheduleDetail scheduleDetail) {
		return Optional.ofNullable(scheduleDetail).map(ScheduleDetail::getId).map(waitingQueues::get).orElse(null);
	}

	/**
	 * Them hang cho
	 */
	public void addWaitingQueue(WaitingQueue queue) {
		String scheduleId = queue.getScheduleDetail().getId();
		waitingQueues.put(scheduleId, queue);
		System.out.println("Da tao hang doi cho tuyen " + scheduleId);

	}

	/**
	 * Xoa hang cho
	 */
	public boolean removeWaitingQueue(String scheduleId) {
		if (waitingQueues.remove(scheduleId) != null) {
			System.out.println("Da xoa hang doi cho tuyen " + scheduleId);
			return true;
		}
		return false;
	}

	/**
	 * Lay tat ca hang cho
	 */
	public List<WaitingQueue> getAllWaitingQueues() {
		return new ArrayList<>(waitingQueues.values());
	}

	/**
	 * Thong bao cho hang cho khi co cho trong
	 */
	public void notifyWaitingQueue(ScheduleDetail scheduleDetail) {
		Optional.ofNullable(scheduleDetail).map(this::getWaitingQueueForSchedule).filter(q -> !q.isEmpty())
				.ifPresent(q -> {
					System.out.println("Thong bao hang doi tai ga " + name);
					q.notifyNextCustomer();
				});
	}

	/**
	 * Tang so khach tai ga
	 */
	public void incrementPassengerCount() {
		passengerCount++;
	}

	/**
	 * Giam so khach tai ga
	 */
	public void decrementPassengerCount() {
		if (passengerCount > 0) {
			passengerCount--;
		}
	}

	/**
	 * Tinh khoang cach den ga khac
	 * 
	 * @param other Ga khac
	 * @return Khoang cach
	 */
	public double calculateDistanceTo(Station other) {
		if (other == null)
			return 0.0;

		double lat1 = Math.toRadians(this.latitude);
		double lon1 = Math.toRadians(this.longitude);
		double lat2 = Math.toRadians(other.latitude);
		double lon2 = Math.toRadians(other.longitude);

		double dLat = lat2 - lat1;
		double dLon = lon2 - lon1;

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return 6371 * c;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + ", location=" + location + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", busRoutes=" + busRoutes + ", facilities=" + facilities
				+ ", facilityStatus=" + facilityStatus + ", isTerminal=" + isTerminal + ", openingHours=" + openingHours
				+ ", waitingQueues=" + waitingQueues + ", passengerCount=" + passengerCount + "]";
	}

	// TEST
	public static void main(String[] args) {
		System.out.println("==================== TAO 14 GA METRO HCM ====================");
		Station station1 = new Station("ST01", "Ben Thanh", "Quan 1", 10.7726, 106.6980, true);
		Station station2 = new Station("ST02", "Nha Hat TP", "Quan 1", 10.7769, 106.7009, false);
		Station station3 = new Station("ST03", "Ba Son", "Quan 1", 10.7877, 106.7051, false);
		Station station4 = new Station("ST04", "Cong Vien Van Thanh", "Binh Thanh", 10.8015, 106.7094, false);
		Station station5 = new Station("ST05", "Tan Cang", "Binh Thanh", 10.8102, 106.7223, false);
		Station station6 = new Station("ST06", "Thao Dien", "Thu Duc", 10.8133, 106.7314, false);
		Station station7 = new Station("ST07", "An Phu", "Thu Duc", 10.8165, 106.7442, false);
		Station station8 = new Station("ST08", "Rach Chiec", "Thu Duc", 10.8207, 106.7580, false);
		Station station9 = new Station("ST09", "Phuoc Long", "Thu Duc", 10.8393, 106.7634, false);
		Station station10 = new Station("ST10", "Binh Thai", "Thu Duc", 10.8508, 106.7701, false);
		Station station11 = new Station("ST11", "Thu Duc", "Thu Duc", 10.8554, 106.7753, false);
		Station station12 = new Station("ST12", "Khu Cong Nghe Cao", "Thu Duc", 10.8706, 106.7856, false);
		Station station13 = new Station("ST13", "Dai Hoc Quoc gia", "Thu Duc", 10.8811, 106.7948, false);
		Station station14 = new Station("ST14", "Ben Xe Suoi Tien", "Thu Duc", 10.8925, 106.8063, true);
		Station[] allStations = { station1, station2, station3, station4, station5, station6, station7, station8,
				station9, station10, station11, station12, station13, station14 };
		// In thong tin
		for (int i = 0; i < allStations.length; i++) {
			System.out
					.println("Ga " + (i + 1) + ": " + allStations[i].getName() + " - " + allStations[i].getLocation());
		}
		System.out.println();
		System.out.println("==================== TEST GA DAU HOAC GA CUOI ====================");
		if (station1.isTerminal()) {
			System.out.println("Ga Ben Thanh la ga dau");
		}
		if (station14.isTerminal()) {
			System.out.println("Ga Suoi Tien la ga cuoi");
		}
		if (!station7.isTerminal()) {
			System.out.println("Ga An Phu khong phai ga dau/cuoi");
		}
		System.out.println();
		System.out.println("==================== TEST CAP NHAT TOA DO ====================");
		System.out.println("Toa do cu cua ga Ben Thanh:");
		System.out.println("  Vi do: " + station1.getLatitude());
		System.out.println("  Kinh do: " + station1.getLongitude());
		station1.setCoordinates(10.7730, 106.6985);
		System.out.println("Toa do moi cua ga Ben Thanh:");
		System.out.println("  Vi do: " + station1.getLatitude());
		System.out.println("  Kinh do: " + station1.getLongitude());
		System.out.println();

		System.out.println("==================== TEST THEM TUYEN BUS ====================");
		BusRoute bus1 = new BusRoute("B01", "Bus 01", "Ben Thanh - Cho Lon");
		BusRoute bus2 = new BusRoute("B19", "Bus 19", "Ben Thanh - Binh Tan");
		BusRoute bus3 = new BusRoute("B03", "Bus 03", "Ben Thanh - An Suong");
		station1.addBusRoute(bus1);
		station1.addBusRoute(bus2);
		station1.addBusRoute(bus3);
		System.out.println("Ga Ben Thanh hien co " + station1.getBusRoute().size() + " tuyen bus");
		// Thu them tuyen bus trung
		System.out.println("\nThu them tuyen Bus 01 lan nua:");
		station1.addBusRoute(bus1);
		System.out.println("So tuyen bus sau khi them trung: " + station1.getBusRoute().size());
		System.out.println();

		System.out.println("==================== TEST XOA TUYEN BUS ====================");
		boolean removeBus = station1.removeBusRoute(bus3);
		System.out.println("So tuyen bus con lai: " + station1.getBusRoute().size());
		System.out.println();
		System.out.println("==================== TEST THEM TIEN ICH ====================");
		// Ga Ben Thanh
		station1.addFacility("Thang may");
		station1.addFacility("WC");
		station1.addFacility("ATM");
		station1.addFacility("Cua hang tien loi");
		station1.addFacility("Phong y te");
		System.out.println("Ga Ben Thanh co " + station1.getFacilities().size() + " tien ich");
		System.out.println("\n");
		// Ga Thu Duc
		station11.addFacility("Thang may");
		station11.addFacility("WC");
		station11.addFacility("Bai xe");
		System.out.println("Ga Thu Duc co " + station11.getFacilities().size() + " tien ich");
		System.out.println("\n");
		// Ga Suoi Tien
		station14.addFacility("Thang may");
		station14.addFacility("WC");
		station14.addFacility("ATM");
		System.out.println("Ga Suoi Tien co " + station14.getFacilities().size() + " tien ich");
		System.out.println();
		System.out.println("==================== TEST XOA TIEN ICH ====================");
		// Ga Ben Thanh
		boolean xoaTienIch = station1.removeFacility("Cua hang tien loi");
		System.out.println("So tien ich con lai cua ga Ben Thanh: " + station1.getFacilities().size());
		System.out.println();
		System.out.println("==================== TEST GIO MO CUA ====================");
		station1.setOpeningHours("5:00-23:00");
		Calendar sang = Calendar.getInstance();
		sang.set(Calendar.HOUR_OF_DAY, 6);
		Calendar trua = Calendar.getInstance();
		trua.set(Calendar.HOUR_OF_DAY, 12);
		Calendar chieu = Calendar.getInstance();
		chieu.set(Calendar.HOUR_OF_DAY, 18);
		Calendar toi = Calendar.getInstance();
		toi.set(Calendar.HOUR_OF_DAY, 23);
		Calendar khuya = Calendar.getInstance();
		khuya.set(Calendar.HOUR_OF_DAY, 2);
		System.out.println("Gio mo cua ga: 5:00 - 23:00");
		System.out.println("Luc 6h sang: " + (station1.isOpen(sang) ? "Mo cua" : "Dong cua"));
		System.out.println("Luc 12h trua: " + (station1.isOpen(trua) ? "Mo cua" : "Dong cua"));
		System.out.println("Luc 6h chieu: " + (station1.isOpen(chieu) ? "Mo cua" : "Dong cua"));
		System.out.println("Luc 11h toi: " + (station1.isOpen(toi) ? "Mo cua" : "Dong cua"));
		System.out.println("Luc 2h sang: " + (station1.isOpen(khuya) ? "Mo cua" : "Dong cua"));
		System.out.println();
		System.out.println("==================== TEST THEM HANG DOI ====================");
		ScheduleDetail lichTrinh01 = new ScheduleDetail("SD01", 6.00, "FORWARD");
		ScheduleDetail lichTrinh02 = new ScheduleDetail("SD02", 18.00, "BACKWARD");
		// Tao hang cho cho lich trinh 01
		WaitingQueue hangDoi01 = new WaitingQueue("WQ01", lichTrinh01, station1, 100);
		Customer khach01 = new Customer("24130350", "Tran Thi Cam Tu");
		Customer khach02 = new Customer("C02", "Pham Thi B");
		Customer khach03 = new Customer("C03", "Nguyen Van A");
		Ticket ve01 = new Ticket("T01", khach01, lichTrinh01);
		Ticket ve02 = new Ticket("T02", khach02, lichTrinh01);
		Ticket ve03 = new Ticket("T03", khach03, lichTrinh01);
		hangDoi01.enqueue(ve01);
		hangDoi01.enqueue(ve02);
		hangDoi01.enqueue(ve03);
		// Tao hang cho cho lich trinh 02
		WaitingQueue hangDoi02 = new WaitingQueue("WQ02", lichTrinh02, station1, 100);
		Customer khach04 = new Customer("24130197", "Le Thi Kim Ngan");
		Customer khach05 = new Customer("C05", "Hoang Van C");
		Ticket ve04 = new Ticket("T04", khach04, lichTrinh02);
		Ticket ve05 = new Ticket("T05", khach05, lichTrinh02);
		hangDoi02.enqueue(ve04);
		hangDoi02.enqueue(ve05);
		// Theo hang doi vao ga
		station1.addWaitingQueue(hangDoi01);
		station1.addWaitingQueue(hangDoi02);
		System.out.println();
		System.out.println("==================== TEST LAY HANG DOI THEO LICH TRINH ====================");
		WaitingQueue layHangDoi01 = station1.getWaitingQueueForSchedule(lichTrinh01);
		if (layHangDoi01 != null) {
			System.out.println("Tim thay hang doi cho lich trinh 06:00");
			System.out.println("So nguoi dang doi: " + layHangDoi01.size());
		}

		WaitingQueue layHangDoi02 = station1.getWaitingQueueForSchedule(lichTrinh02);
		if (layHangDoi02 != null) {
			System.out.println("Tim thay hang cho cho lich trinh 18:00");
			System.out.println("So nguoi dang cho: " + layHangDoi02.size());
		}
		System.out.println();
		System.out.println("==================== TEST LAY TAT CA HANG DOI====================");
		List<WaitingQueue> allWaitingQueue = station1.getAllWaitingQueues();
		System.out.println("Tong so hang cho tai ga: " + allWaitingQueue.size());
		System.out.println();
		System.out.println("==================== TEST THONG BAO HANG DOI KHI CO CHO TRONG====================");
		System.out.println("Thong bao hang doi 06:00:");
		station1.notifyWaitingQueue(lichTrinh01);
		System.out.println();
		System.out.println("Thong bao hang doi 18:00:");
		station1.notifyWaitingQueue(lichTrinh02);
		System.out.println();
		System.out.println("==================== TEST XOA HANG DOI====================");
		boolean xoaHangCho = station1.removeWaitingQueue("SD01");
		if (xoaHangCho) {
			System.out.println("Da xoa hang doi 18:00");
		}
		System.out.println("So hang cho con lai: " + station1.getAllWaitingQueues().size());
		System.out.println();
		System.out.println("==================== TEST DEM SO KHACH TAI GA KHI TANG HOAC GIAM====================");
		// Ga Ben Thanh - gio cao diem sang
		station1.setPassengerCount(0);
		System.out.println("Ga Ben Thanh ban dau: " + station1.getPassengerCount() + " khach");

		for (int i = 0; i < 50; i++) {
			station1.incrementPassengerCount();
		}
		System.out.println("Sau gio cao diem sang: " + station1.getPassengerCount() + " khach");

		for (int i = 0; i < 30; i++) {
			station1.decrementPassengerCount();
		}
		System.out.println("Sau khi khach len tau: " + station1.getPassengerCount() + " khach");
		System.out.println();
		System.out.println("==================== TEST KHOANG CACH DEN GA KHAC====================");
		double kc1 = station1.calculateDistanceTo(station2);
        double kc2 = station1.calculateDistanceTo(station14);
        double kc3 = station11.calculateDistanceTo(station14);
        double kc4 = station5.calculateDistanceTo(station6);
        System.out.println("Khoang cach Ben Thanh den Nha Hat TP: " + String.format("%.2f", kc1) + " km");
        System.out.println("Khoang cach Ben Thanh den Suoi Tien: " + String.format("%.2f", kc2) + " km");
        System.out.println("Khoang cach Thu Duc den Suoi Tien: " + String.format("%.2f", kc3) + " km");
        System.out.println("Khoang cach Tan Cang den Thao Dien: " + String.format("%.2f", kc4) + " km");
        System.out.println();
	}

}
