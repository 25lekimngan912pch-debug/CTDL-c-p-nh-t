package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * He thong Metro tong Quản lý: - Stations, Trains, Routes, Schedules -
 * BusRoutes - Customers, TicketTypes - RevenueStatistics, RoutePlanner - Free
 * ride days (ngay le mien phi)
 */
public class MetroSystem {
	private String id; // Ma dinh danh he thong
	private String name; // Ten he thong metro
	private List<Station> stations; // Danh sach tat ca ga trong he thong
	private List<Train> trains; // Danh sach tat ca cac tau
	private List<Route> routes; // Danh sach cac tuyen
	private List<ScheduleDaily> schedules; // Danh sach lich trih hang ngay
	private List<BusRoute> busRoutes; // Danh sach tuyen bus ket noi
	private List<Customer> customers; // Danh sach khach hang
	private List<TicketType> ticketTypes; // Danh sach cac loai ve
	private RevenueStatistics revenueStatistics; // Thong ke doanh thu
	private RoutePlanner routePlanner; // Tim duong
	private String operatingHours; // Gio hoat dong
	private String emergencyContact; // So dien thoai khan cap
	private List<Calendar> freeRideDays; // Danh sach cac ngay le duoc mien phi
	private Map<Calendar, String> holidayNames; // Ten ngay le

	public MetroSystem(String id, String name, List<Station> stations, List<Train> trains, List<Route> routes,
			List<ScheduleDaily> schedules, List<BusRoute> busRoutes, List<Customer> customers,
			List<TicketType> ticketTypes, RevenueStatistics revenueStatistics, RoutePlanner routePlanner,
			String operatingHours, String emergencyContact, List<Calendar> freeRideDays,
			Map<Calendar, String> holidayNames) {
		this.id = Optional.ofNullable(id).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("ID khong duoc rong"));
		this.name = Optional.ofNullable(name).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("Name không được rỗng"));
		this.stations = new ArrayList<>();
		this.trains = new ArrayList<>();
		this.routes = new ArrayList<>();
		this.schedules = new ArrayList<>();
		this.busRoutes = new ArrayList<>();
		this.customers = new ArrayList<>();
		this.ticketTypes = new ArrayList<>();
		this.revenueStatistics = revenueStatistics;
		this.routePlanner = routePlanner;
		this.operatingHours = operatingHours;
		this.emergencyContact = emergencyContact;
		this.freeRideDays = freeRideDays;
		this.holidayNames = holidayNames;
	}

	public MetroSystem(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.stations = new ArrayList<>();
		this.trains = new ArrayList<>();
		this.routes = new ArrayList<>();
		this.schedules = new ArrayList<>();
		this.busRoutes = new ArrayList<>();
		this.customers = new ArrayList<>();
		this.ticketTypes = new ArrayList<>();
		this.revenueStatistics = revenueStatistics;
		this.routePlanner = routePlanner;
		this.operatingHours = operatingHours;
		this.emergencyContact = emergencyContact;
		this.freeRideDays = freeRideDays;
		this.holidayNames = holidayNames;
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

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public List<ScheduleDaily> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<ScheduleDaily> schedules) {
		this.schedules = schedules;
	}

	public List<BusRoute> getBusRoutes() {
		return busRoutes;
	}

	public void setBusRoutes(List<BusRoute> busRoutes) {
		this.busRoutes = busRoutes;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<TicketType> getTicketTypes() {
		return ticketTypes;
	}

	public void setTicketTypes(List<TicketType> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}

	public RevenueStatistics getRevenueStatistics() {
		return revenueStatistics;
	}

	public void setRevenueStatistics(RevenueStatistics revenueStatistics) {
		this.revenueStatistics = revenueStatistics;
	}

	public RoutePlanner getRoutePlanner() {
		return routePlanner;
	}

	public void setRoutePlanner(RoutePlanner routePlanner) {
		this.routePlanner = routePlanner;
	}

	public String getOperatingHours() {
		return operatingHours;
	}

	public void setOperatingHours(String operatingHours) {
		this.operatingHours = operatingHours;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public List<Calendar> getFreeRideDays() {
		return freeRideDays;
	}

	public void setFreeRideDays(List<Calendar> freeRideDays) {
		this.freeRideDays = freeRideDays;
	}

	public Map<Calendar, String> getHolidayNames() {
		return holidayNames;
	}

	public void setHolidayNames(Map<Calendar, String> holidayNames) {
		this.holidayNames = holidayNames;
	}

	/**
	 * Them ga vao he thong
	 * 
	 * @param s Ga can them
	 */
	public void addStation(Station s) {
		if (s != null && !stations.contains(s)) {
			stations.add(s);
			System.out.println("Da them ga: " + s.getName());
		}
	}

	/**
	 * Them tau vao he thong
	 * 
	 * @param t Tau can them
	 */
	public void addTrain(Train t) {
		if (t != null && !trains.contains(t)) {
			trains.add(t);
			System.out.println("Da them tau " + t.getName());
		}
	}

	/**
	 * Them lich trinh hang ngay
	 * 
	 * @param sd Lich trinh can them
	 */
	public void addSchedule(ScheduleDaily sd) {
		if (sd != null && !schedules.contains(sd)) {
			schedules.add(sd);
		}
	}

	/**
	 * Them tuyen bus
	 * 
	 * @param b Tuyen bus can them
	 */
	public void addBusRoute(BusRoute b) {
		if (b != null && !busRoutes.contains(b)) {
			busRoutes.add(b);
			System.out.println("Da them bus: " + b.getName());
		}
	}

	public void addRoute(Route r) {
		if (r != null && !routes.contains(r)) {
			routes.add(r);
			System.out.println("Da them tuyen: " + r.getName());
		}
	}

	/**
	 * Them khach hang
	 * 
	 * @param c Khach hang can them
	 */
	public void addCustomer(Customer c) {
		if (c != null && !customers.contains(c)) {
			customers.add(c);
			System.out.println("Da them khach: " + c.getName());
		}
	}

	/**
	 * Them loai ve
	 * 
	 * @param tt Loai ve can them
	 */
	public void addTicketType(TicketType tt) {
		if (tt != null && !ticketTypes.contains(tt)) {
			ticketTypes.add(tt);
			System.out.println("Da them loai ve: " + tt.getName());
		}
	}

	// Xoa ga
	public void removeStation(Station s) {
		if (stations.remove(s)) {
			System.out.println("Da xoa ga: " + s.getName());
		}
	}

	// Xoa tau
	public void removeTrain(Train t) {
		if (trains.remove(t)) {
			System.out.println("Da xoa tau: " + t.getName());
		}
	}

	// Xoa lich trinh
	public void removeSchedule(ScheduleDaily sd) {
		schedules.remove(sd);
	}

	// Xoa tuyen bus
	public void removeBusRoute(BusRoute b) {
		busRoutes.remove(b);
	}

	// Xoa khach hang
	public void removeCustomer(Customer c) {
		customers.remove(c);
	}

	/**
	 * Tim ga theo ten(không phan biet chu hoa chu thuong)
	 * 
	 * @param name Ten ga can tim
	 * @return Ga tim thay hiac null
	 */
	public Station findStationByName(String name) {
		return stations.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	/**
	 * Tim ga theo ID
	 * 
	 * @param id ID ga can tim
	 * @return Ga tim thay hoac null
	 */
	public Station findStationById(String id) {
		return stations.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
	}

	/**
	 * Tim tau theo ID
	 * 
	 * @param id ID tau can tim
	 * @return Tau tim thay hoac null
	 */
	public Train findTrainById(String id) {
		return trains.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
	}

	/**
	 * Tim tuyen theo ID
	 * 
	 * @param id ID tuyen can tim
	 * @return Tuyen tim thay hoac null
	 */
	public Route findRouteById(String id) {
		return routes.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
	}

	/**
	 * Tim khach hang theo ID
	 * 
	 * @param id ID khach hang can tim
	 * @return Khach hang tim thay hoac null
	 */
	public Customer findCustomerById(String id) {
		return customers.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
	}

	/**
	 * Tim lich trinh theo ngay
	 * 
	 * @param date Ngay can tim
	 * @return Lich trinh hoac null
	 */
	public ScheduleDaily findScheduleByDate(Calendar date) {
		return schedules.stream().filter(sd -> isSameDay(sd.getDate(), date)).findFirst().orElse(null);
	}

	private boolean isSameDay(Calendar c1, Calendar c2) {
		if (c1 == null || c2 == null)
			return false;

		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Lay cac chuyen tiep theo tu ga
	 * 
	 * @param stationId ID ga
	 * @param after     Thoi diem bat dau
	 * @param limit     So luong chuyen
	 * @return Danh sach chuyen tiep theo
	 */
	public List<ScheduleDetail> getNextDepartures(String stationId, Calendar after, int limit) {
		Station station = findStationById(stationId);
		if (station == null) {
			System.out.println("Khong tim thay ga: " + stationId);
			return new ArrayList<>();
		}
		return schedules.stream().flatMap(sd -> sd.getDetails().stream())
				.filter(detail -> detail.getRoute().getStationList().contains(station))
				.filter(detail -> detail.getStartDateTime().after(after))
				.filter(detail -> !"CANCELLED".equals(detail.getStatus()))
				.sorted(Comparator.comparing(ScheduleDetail::getStartDateTime)).limit(limit)
				.collect(Collectors.toList());
	}

	/**
	 * Lay cac tuyen dang hoat dong
	 * 
	 * @return Danh sach tuyen active
	 */
	public List<Route> getActiveRoutes() {
		return routes.stream().filter(Route::isActive).collect(Collectors.toList());
	}

	/**
	 * Lay lich trinh theo tuyen
	 * 
	 * @param routeId ID tuyến
	 * @return Danh sách lịch trình
	 */
	public List<ScheduleDaily> getSchedulesByRoute(String routeId) {
		Route route = findRouteById(routeId);
		if (route == null)
			return new ArrayList<>();

		return schedules.stream().filter(sd -> sd.getRoute().equals(route)).collect(Collectors.toList());
	}

	/**
	 * Lay lich trinh trong khoang thoi gian
	 * 
	 * @param start Ngay bat dau
	 * @param end   Ngay ket thuc
	 * @return Danh sach lich trinh
	 */
	public List<ScheduleDaily> getSchedulesByDateRange(Calendar start, Calendar end) {
		return schedules.stream().filter(sd -> !sd.getDate().before(start) && !sd.getDate().after(end))
				.collect(Collectors.toList());
	}

	/**
	 * Tinh tong doanh thu
	 * 
	 * @return Tong doanh thu (VNĐ)
	 */
	public double calculateTotalRevenue() {
		return Optional.ofNullable(revenueStatistics).map(RevenueStatistics::getTotalRevenue).orElse(0.0);
	}

	/**
	 * Validate ve tai ga
	 * 
	 * @param ticketId  ID ve
	 * @param at        Thoi diem kiem tra
	 * @param stationId ID ga
	 * @return true neu ve hop le
	 */
	public boolean validateTicket(String ticketId, Calendar at, String stationId) {

		Station station = findStationById(stationId);
		if (station == null)
			return false;

		QrValidator validator = new QrValidator("VAL_" + stationId, station);

		// Tim ve
		Optional<Ticket> ticketOpt = customers.stream().flatMap(c -> c.getActiveTickets().stream())
				.filter(t -> t.getId().equals(ticketId)).findFirst();

		return ticketOpt.map(ticket -> validator.validate(ticket, at)).orElse(false);

	}

	/**
	 * Tom tat he thong
	 * 
	 * @return Chuoi tom tat
	 */
	public String getSystemSummary() {
		return String.format("%s: %d ga, %d tàu, %d tuyến, %d ngày lễ", name, stations.size(), trains.size(),
				routes.size(), freeRideDays.size());
	}

	/**
	 * Cap nhat gio hoat dong
	 * 
	 * @param hours Gio hoat dong moi
	 */
	public void updateOperatingHours(String hours) {
		this.operatingHours = hours;
		System.out.println("Cap nhat gio hoat dong: " + hours);
	}

	/**
	 * Them ngay le mien phi
	 */
	private Calendar truncateToDay(Calendar cal) {
		Calendar truncated = (Calendar) cal.clone();
		truncated.set(Calendar.HOUR_OF_DAY, 0);
		truncated.set(Calendar.MINUTE, 0);
		truncated.set(Calendar.SECOND, 0);
		truncated.set(Calendar.MILLISECOND, 0);
		return truncated;
	}

	private String formatDate(Calendar cal) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(cal.getTime());
	}

	public void addFreeRideDay(Calendar date, String holidayName) {

		Calendar truncated = truncateToDay(date);

		if (!freeRideDays.contains(truncated)) {
			freeRideDays.add(truncated);
			holidayNames.put(truncated, holidayName);
			System.out.println("Them  ngay le: " + holidayName + " (" + formatDate(truncated) + ")");

		}
	}

	/**
	 * Xoa ngay le
	 */
	public void removeFreeRideDay(Calendar date) {
		Calendar truncated = truncateToDay(date);
		if (freeRideDays.remove(truncated)) {
			holidayNames.remove(truncated);
			System.out.println("Xoa ngay le " + formatDate(truncated));
		}
	}

	/**
	 * Kiem tra co phai ngay le mien phi khong
	 */
	public boolean isFreeRideDay(Calendar date) {
		Calendar truncated = truncateToDay(date);
		return freeRideDays.stream().anyMatch(freeDay -> isSameDay(freeDay, truncated));
	}

	/**
	 * Lay ten ngay le
	 */
	public String getHolidayName(Calendar date) {
		Calendar truncated = truncateToDay(date);
		return holidayNames.getOrDefault(truncated, "Unknown Holiday");
	}

	/**
	 * Lay tat ca ngay le
	 */
	public List<Calendar> getAllFreeRideDays() {
		return new ArrayList<>(freeRideDays);
	}

	/**
	 * Lay cac ngay le sap toi
	 */
	public List<Calendar> getUpcomingHolidays(int limit) {
		Calendar now = Calendar.getInstance();

		return freeRideDays.stream().filter(date -> date.after(now)).sorted().limit(limit).collect(Collectors.toList());
	}

	public double calculateTicketPrice(Ticket ticket, Calendar date) {
		if (ticket == null) {
			return 0.0;
		}

		// Kiem tra co phai ngay le mien phi khong
		if (isFreeRideDay(date)) {
			String holidayName = getHolidayName(date);
			System.out.println("Ngay le " + holidayName + " - MIEN PHI ve!");
			return 0.0;
		}

		// Ngay thuong su dung logic tinh gia cua Ticket
		return ticket.calculatePrice();
	}

	@Override
	public String toString() {
		return "MetroSystem [id=" + id + ", name=" + name + ", stations=" + stations + ", trains=" + trains
				+ ", routes=" + routes + ", schedules=" + schedules + ", busRoutes=" + busRoutes + ", customers="
				+ customers + ", ticketTypes=" + ticketTypes + ", revenueStatistics=" + revenueStatistics
				+ ", routePlanner=" + routePlanner + ", operatingHours=" + operatingHours + ", emergencyContact="
				+ emergencyContact + ", freeRideDays=" + freeRideDays + ", holidayNames=" + holidayNames + "]";
	}

	public static void main(String[] args) {
		System.out.println("==================== KHOI TAO HE THONG METRO ====================");

		// ===== KHOI TAO HE THONG =====
		MetroSystem metro = new MetroSystem("MS01", "Metro TP.HCM");
		metro.setOperatingHours("5:00 - 23:00");
		metro.setEmergencyContact("1900-6000");
		metro.setFreeRideDays(new ArrayList<>());
		metro.setHolidayNames(new HashMap<>());
		metro.setRevenueStatistics(new RevenueStatistics("RS01"));

		// ===== GA =====
		Station gaBenThanh = new Station("ST01", "Ben Thanh", "Quan 1", 10.77, 106.69, true);
		Station gaSuoiTien = new Station("ST14", "Suoi Tien", "Thu Duc", 10.89, 106.80, true);

		// ===== TUYEN =====
		Route tuyen01 = new Route("R01", "Ben Thanh - Suoi Tien", new ArrayList<>(), new ArrayList<>(), 19.7, true,
				"FORWARD");
		tuyen01.addStation(gaBenThanh);
		tuyen01.addStation(gaSuoiTien);
		RoutePart doan01 = new RoutePart("RP01", gaBenThanh, gaSuoiTien, 19.7, tuyen01, 45);
		tuyen01.addRoutePart(doan01);

		// ===== LICH TRINH =====
		Calendar ngayChay = Calendar.getInstance();
		ngayChay.add(Calendar.HOUR_OF_DAY, 2);
		ScheduleDetail lichTrinh01 = new ScheduleDetail("SD01", ngayChay, 8.0, 9.0, tuyen01, "FORWARD", null, false,
				"SCHEDULED", 0, 100, 100, null);
		ScheduleDaily lichNgay = new ScheduleDaily("SCH01", ngayChay, tuyen01);
		lichNgay.addDetail(lichTrinh01);

		// ===== LOCOMOTIVE & TRAIN =====
		Calendar baoTriCu = Calendar.getInstance();
		baoTriCu.set(2025, Calendar.DECEMBER, 1);
		Calendar baoTriTiep = Calendar.getInstance();
		baoTriTiep.set(2026, Calendar.JUNE, 1);
		Locomotive loco1 = new Locomotive("L01", "Metro 01", "OPERATIONAL", 60, 80, 2020, baoTriCu, baoTriTiep);

		LinkedList<Carriage> dsToa = new LinkedList<>();
		Train tau01 = new Train("T01", "Metro HCM 01", loco1, dsToa, "AVAILABLE", tuyen01, lichTrinh01, 0, gaBenThanh,
				2020);
		Carriage toa1 = new Carriage("C01", "STANDARD", 50, 50, "IN_USE", tau01, true, true);
		Carriage toa2 = new Carriage("C02", "STANDARD", 60, 60, "IN_USE", tau01, true, false);
		tau01.getCarriageList().add(toa1);
		tau01.getCarriageList().add(toa2);
		tau01.updateTotalCapacity();

		// ===== BUS ROUTE =====
		BusRoute bus01 = new BusRoute("B01", "Tuyen 01", "Ben Thanh - Cho Lon", 12.5, 35, 7000);

		// ===== KHACH HANG & VE =====
		Customer khach01 = new Customer("C01", "Nguyen Van A");
		TicketType veLuot = new TicketType("TT01", "Ve luot", 0.0, 1, "Ve di mot luot");
		Ticket ve01 = new Ticket("TKT01", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
		ve01.setStatus("ACTIVE");
		ve01.setPaid(true);
		Calendar hanSuDung = Calendar.getInstance();
		hanSuDung.add(Calendar.DATE, 1);
		ve01.setValidUntil(hanSuDung);
		khach01.getActiveTickets().add(ve01);

		System.out.println("Da tao cac doi tuong test\n");

		// ===== TEST ADD =====
		System.out.println("==================== TEST ADD ====================");
		metro.addStation(gaBenThanh);
		metro.addStation(gaSuoiTien);
		metro.addTrain(tau01);
		metro.addRoute(tuyen01);
		metro.addSchedule(lichNgay);
		metro.addBusRoute(bus01);
		metro.addCustomer(khach01);
		metro.addTicketType(veLuot);
		System.out.println();

		// ===== TEST REMOVE =====
		System.out.println("==================== TEST REMOVE ====================");
		metro.removeStation(gaSuoiTien);
		metro.removeTrain(tau01);
		// metro.removeRoute(tuyen01);
		metro.removeSchedule(lichNgay);
		metro.removeBusRoute(bus01);
		metro.removeCustomer(khach01);
		System.out.println();

		// ===== TEST FIND =====
		System.out.println("==================== TEST FIND ====================");
		System.out.println("Tim ga Ben Thanh: " + (metro.findStationByName("Ben Thanh") != null));
		System.out.println("Tim ga ST01: " + (metro.findStationById("ST01") != null));
		System.out.println("Tim tau T01: " + (metro.findTrainById("T01") != null));
		System.out.println("Tim tuyen R01: " + (metro.findRouteById("R01") != null));
		System.out.println("Tim khach C01: " + (metro.findCustomerById("C01") != null));
		System.out.println("Tim lich trinh hom nay: " + (metro.findScheduleByDate(ngayChay) != null));
		System.out.println();

		// ===== TEST LICH TRINH =====
		System.out.println("==================== TEST LICH TRINH ====================");
		List<ScheduleDetail> nextTrips = metro.getNextDepartures("ST01", Calendar.getInstance(), 3);
		System.out.println("Chuyen tiep theo tu ST01: " + nextTrips.size());

		List<Route> activeRoutes = metro.getActiveRoutes();
		System.out.println("Tuyen dang hoat dong: " + activeRoutes.size());

		List<ScheduleDaily> schedulesByRoute = metro.getSchedulesByRoute("R01");
		System.out.println("Lich trinh theo tuyen R01: " + schedulesByRoute.size());

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.add(Calendar.DAY_OF_MONTH, 7);
		List<ScheduleDaily> schedulesRange = metro.getSchedulesByDateRange(start, end);
		System.out.println("Lich trinh trong 7 ngay toi: " + schedulesRange.size());
		System.out.println();

		// ===== TEST VE & DOANH THU =====
		System.out.println("==================== TEST VE & DOANH THU ====================");
		boolean hopLe = metro.validateTicket("TKT01", Calendar.getInstance(), "ST01");
		System.out.println("Ve TKT01 hop le? " + hopLe);
		System.out.println("Tong doanh thu: " + metro.calculateTotalRevenue() + " VND");
		System.out.println();

		// ===== TEST NGAY LE =====
		System.out.println("==================== TEST NGAY LE ====================");
		Calendar tet = Calendar.getInstance();
		tet.set(2026, Calendar.JANUARY, 29);
		metro.addFreeRideDay(tet, "Tet Nguyen Dan");
		System.out.println("So ngay le: " + metro.getFreeRideDays().size());
		System.out.println("Tet co phai ngay le? " + metro.isFreeRideDay(tet));
		System.out.println("Ten ngay le Tet: " + metro.getHolidayName(tet));
		metro.removeFreeRideDay(tet);
		System.out.println("So ngay le sau khi xoa: " + metro.getFreeRideDays().size());
		System.out.println();

		// ===== TEST TINH GIA VE =====
		System.out.println("==================== TEST TINH GIA VE ====================");
		double giaThuong = metro.calculateTicketPrice(ve01, Calendar.getInstance());
		metro.addFreeRideDay(tet, "Tet Nguyen Dan");
		double giaTet = metro.calculateTicketPrice(ve01, tet);
		System.out.println("Gia ve ngay thuong: " + giaThuong);
		System.out.println("Gia ve ngay Tet: " + giaTet);
		System.out.println();

		// ===== TEST TOM TAT HE THONG =====
		System.out.println("==================== TEST TOM TAT HE THONG ====================");
		System.out.println(metro.getSystemSummary());
		metro.updateOperatingHours("5:30 - 22:30");

		// ===== TEST ADD & REMOVE FREE RIDE DAYS =====
		System.out.println("==================== TEST NGAY LE MIEN PHI ====================");
		Calendar tet1 = Calendar.getInstance();
		tet1.set(2026, Calendar.JANUARY, 29);
		Calendar quocKhanh = Calendar.getInstance();
		quocKhanh.set(2026, Calendar.SEPTEMBER, 2);

		metro.addFreeRideDay(tet1, "Tet Nguyen Dan");
		metro.addFreeRideDay(quocKhanh, "Quoc Khanh");

		System.out.println("Tong so ngay le: " + metro.getFreeRideDays().size());
		System.out.println("Tet co phai ngay le? " + metro.isFreeRideDay(tet1));
		System.out.println("Ten ngay le Tet: " + metro.getHolidayName(tet1));

		metro.removeFreeRideDay(tet1);
		System.out.println("So ngay le sau khi xoa Tet: " + metro.getFreeRideDays().size());
		System.out.println();

		// ===== TEST GET ALL & UPCOMING HOLIDAYS =====
		System.out.println("==================== TEST LAY DANH SACH NGAY LE ====================");
		List<Calendar> allHolidays = metro.getAllFreeRideDays();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Calendar holiday : allHolidays) {
			System.out.println("Ngay le: " + sdf.format(holiday.getTime()) + " - " + metro.getHolidayName(holiday));
		}

		List<Calendar> upcoming = metro.getUpcomingHolidays(5);
		System.out.println("So ngay le sap toi: " + upcoming.size());
		System.out.println();

		// ===== TEST CALCULATE TICKET PRICE =====
		System.out.println("==================== TEST TINH GIA VE ====================");
		double giaThuong1 = metro.calculateTicketPrice(ve01, Calendar.getInstance());
		double giaLe = metro.calculateTicketPrice(ve01, quocKhanh);
		System.out.println("Gia ve ngay thuong: " + giaThuong1);
		System.out.println("Gia ve ngay le (Quoc Khanh): " + giaLe);
		System.out.println();

		// ===== TEST TO STRING =====
		System.out.println("==================== TEST TO STRING ====================");
		System.out.println(metro.toString());
	}

}
