package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
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
	    System.out.println("==================== TAO HE THONG METRO TP.HCM ====================");
        MetroSystem metro = new MetroSystem("METRO_HCM", "Metro TP.HCM");
        metro.setOperatingHours("5:00 - 23:00");
        metro.setEmergencyContact("1900-6000");
        metro.setFreeRideDays(new ArrayList<>());
        metro.setHolidayNames(new HashMap<>());
        
        System.out.println("Ten he thong: " + metro.getName());
        System.out.println("ID: " + metro.getId());
        System.out.println();
        
        System.out.println("==================== TEST 1: addStation() ====================");
        Station station1 = new Station("ST01", "Ben Thanh", "Quan 1", 10.7726, 106.6980, true);
        Station station2 = new Station("ST02", "Nha Hat TP", "Quan 1", 10.7769, 106.7009, false);
        Station station3 = new Station("ST03", "Ba Son", "Quan 1", 10.7877, 106.7051, false);
        Station station14 = new Station("ST14", "Suoi Tien", "Thu Duc", 10.8925, 106.8063, true);
        
        metro.addStation(station1);
        metro.addStation(station2);
        metro.addStation(station3);
        metro.addStation(station14);
        System.out.println("Tong so ga: " + metro.getStations().size());
        System.out.println();
        
        System.out.println("==================== TEST 2: addTrain() ====================");
        Train train1 = new Train("T01", "Metro HCM 01", 930);
        Train train2 = new Train("T02", "Metro HCM 02", 930);
        
        metro.addTrain(train1);
        metro.addTrain(train2);
        System.out.println("Tong so tau: " + metro.getTrains().size());
        System.out.println();
        
        System.out.println("==================== TEST 3: addRoute() ====================");
        Route route1 = new Route("R01", "Metro so 1: Ben Thanh - Suoi Tien");
        
        metro.addRoute(route1);
        System.out.println("Tong so tuyen: " + metro.getRoutes().size());
        System.out.println();
        
        System.out.println("==================== TEST 4: addSchedule() ====================");
        Calendar today = Calendar.getInstance();
        ScheduleDaily schedule1 = new ScheduleDaily("SCH-001", today, route1);
        
        metro.addSchedule(schedule1);
        System.out.println("Tong so lich trinh: " + metro.getSchedules().size());
        System.out.println();
        
        System.out.println("==================== TEST 5: addBusRoute() ====================");
        BusRoute bus1 = new BusRoute("BR01", "Tuyen 1", "Ben Thanh - Cho Lon");
        BusRoute bus93 = new BusRoute("BR93", "Tuyen 93", "Ben Thanh - Suoi Tien");
        
        metro.addBusRoute(bus1);
        metro.addBusRoute(bus93);
        System.out.println("Tong so tuyen bus: " + metro.getBusRoutes().size());
        System.out.println();
        
        System.out.println("==================== TEST 6: addCustomer() ====================");
        Customer customer1 = new Customer("24130197", "Le Thi Kim Ngan");
        Customer customer2 = new Customer("24130350", "Tran Thi Cam Tu");
        
        metro.addCustomer(customer1);
        metro.addCustomer(customer2);
        System.out.println("Tong so khach hang: " + metro.getCustomers().size());
        System.out.println();
        
        System.out.println("==================== TEST 7: addTicketType() ====================");
        TicketType ticketType1 = new TicketType("TT01", "Ve luot", 15000);
        TicketType ticketType2 = new TicketType("TT02", "Ve ngay", 40000);
        
        metro.addTicketType(ticketType1);
        metro.addTicketType(ticketType2);
        System.out.println("Tong so loai ve: " + metro.getTicketTypes().size());
        System.out.println();
        
        System.out.println("==================== TEST 8: removeStation() ====================");
        Station stationTemp = new Station("ST99", "Ga Tam", "Quan 1", 10.7726, 106.6980, false);
        metro.addStation(stationTemp);
        System.out.println("So ga truoc khi xoa: " + metro.getStations().size());
        metro.removeStation(stationTemp);
        System.out.println("So ga sau khi xoa: " + metro.getStations().size());
        System.out.println();
        
        System.out.println("==================== TEST 9: removeTrain() ====================");
        Train trainTemp = new Train("T99", "Tau Tam", 930);
        metro.addTrain(trainTemp);
        System.out.println("So tau truoc khi xoa: " + metro.getTrains().size());
        metro.removeTrain(trainTemp);
        System.out.println("So tau sau khi xoa: " + metro.getTrains().size());
        System.out.println();
        
        System.out.println("==================== TEST 10: removeSchedule() ====================");
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        ScheduleDaily scheduleTemp = new ScheduleDaily("SCH-999", tomorrow, route1);
        metro.addSchedule(scheduleTemp);
        System.out.println("So lich trinh truoc khi xoa: " + metro.getSchedules().size());
        metro.removeSchedule(scheduleTemp);
        System.out.println("So lich trinh sau khi xoa: " + metro.getSchedules().size());
        System.out.println();
        
        System.out.println("==================== TEST 11: removeBusRoute() ====================");
        BusRoute busTemp = new BusRoute("BR99", "Tuyen Tam", "A - B");
        metro.addBusRoute(busTemp);
        System.out.println("So tuyen bus truoc khi xoa: " + metro.getBusRoutes().size());
        metro.removeBusRoute(busTemp);
        System.out.println("So tuyen bus sau khi xoa: " + metro.getBusRoutes().size());
        System.out.println();
        
        System.out.println("==================== TEST 12: removeCustomer() ====================");
        Customer customerTemp = new Customer("C999", "Khach Tam");
        metro.addCustomer(customerTemp);
        System.out.println("So khach hang truoc khi xoa: " + metro.getCustomers().size());
        metro.removeCustomer(customerTemp);
        System.out.println("So khach hang sau khi xoa: " + metro.getCustomers().size());
        System.out.println();
        
        System.out.println("==================== TEST 13: findStationByName() ====================");
        String[] stationNames = {"ben thanh", "NHA HAT TP", "Ba Son"};
        for (String name : stationNames) {
            Station found = metro.findStationByName(name);
            System.out.println("Tim ga '" + name + "': " + 
                    (found != null ? "Tim thay - " + found.getName() : "Khong tim thay"));
        }
        System.out.println();
        
        System.out.println("==================== TEST 14: findStationById() ====================");
        String[] stationIds = {"ST01", "ST14", "ST99"};
        for (String id : stationIds) {
            Station found = metro.findStationById(id);
            System.out.println("Tim ga ID '" + id + "': " + 
                    (found != null ? "Tim thay - " + found.getName() : "Khong tim thay"));
        }
        System.out.println();
        
        System.out.println("==================== TEST 15: findTrainById() ====================");
        String[] trainIds = {"T01", "T02", "T99"};
        for (String id : trainIds) {
            Train found = metro.findTrainById(id);
            System.out.println("Tim tau ID '" + id + "': " + 
                    (found != null ? "Tim thay - " + found.getName() : "Khong tim thay"));
        }
        System.out.println();
        
        System.out.println("==================== TEST 16: findRouteById() ====================");
        Route foundRoute = metro.findRouteById("R01");
        System.out.println("Tim tuyen 'R01': " + 
                (foundRoute != null ? "Tim thay - " + foundRoute.getName() : "Khong tim thay"));
        System.out.println();
        
        System.out.println("==================== TEST 17: findCustomerById() ====================");
        String[] customerIds = {"24130197", "24130350", "C999"};
        for (String id : customerIds) {
            Customer found = metro.findCustomerById(id);
            System.out.println("Tim khach ID '" + id + "': " + 
                    (found != null ? "Tim thay - " + found.getName() : "Khong tim thay"));
        }
        System.out.println();
        
        System.out.println("==================== TEST 18: findScheduleByDate() ====================");
        ScheduleDaily foundSchedule = metro.findScheduleByDate(today);
        System.out.println("Tim lich trinh hom nay: " + 
                (foundSchedule != null ? "Tim thay - " + foundSchedule.getId() : "Khong tim thay"));
        System.out.println();
        
        System.out.println("==================== TEST 19: getNextDepartures() ====================");
        Calendar now = Calendar.getInstance();
        List<ScheduleDetail> nextDepartures = metro.getNextDepartures("ST01", now, 3);
        System.out.println("So chuyen tiep theo tu ga ST01: " + nextDepartures.size());
        System.out.println();
        
        System.out.println("==================== TEST 20: getActiveRoutes() ====================");
        List<Route> activeRoutes = metro.getActiveRoutes();
        System.out.println("So tuyen dang hoat dong: " + activeRoutes.size());
        System.out.println();
        
        System.out.println("==================== TEST 21: getSchedulesByRoute() ====================");
        List<ScheduleDaily> schedulesByRoute = metro.getSchedulesByRoute("R01");
        System.out.println("So lich trinh cua tuyen R01: " + schedulesByRoute.size());
        System.out.println();
        
        System.out.println("==================== TEST 22: getSchedulesByDateRange() ====================");
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 7);
        List<ScheduleDaily> schedulesByRange = metro.getSchedulesByDateRange(startDate, endDate);
        System.out.println("So lich trinh trong 7 ngay toi: " + schedulesByRange.size());
        System.out.println();
        
        System.out.println("==================== TEST 23: calculateTotalRevenue() ====================");
        double revenue = metro.calculateTotalRevenue();
        System.out.println("Tong doanh thu: " + revenue + " VND");
        System.out.println();
        
        System.out.println("==================== TEST 24: validateTicket() ====================");
        Calendar validTime = Calendar.getInstance();
        boolean isValid = metro.validateTicket("TICKET-001", validTime, "ST01");
        System.out.println("Ve TICKET-001 hop le? " + isValid);
        System.out.println();
        
        System.out.println("==================== TEST 25: getSystemSummary() ====================");
        System.out.println(metro.getSystemSummary());
        System.out.println();
        
        System.out.println("==================== TEST 26: updateOperatingHours() ====================");
        System.out.println("Gio hoat dong cu: " + metro.getOperatingHours());
        metro.updateOperatingHours("5:30 - 22:30");
        System.out.println("Gio hoat dong moi: " + metro.getOperatingHours());
        System.out.println();
        
        System.out.println("==================== TEST 27: addFreeRideDay() ====================");
        Calendar tet = Calendar.getInstance();
        tet.set(2025, Calendar.JANUARY, 29);
        metro.addFreeRideDay(tet, "Tet Nguyen Dan");
        
        Calendar quocKhanh = Calendar.getInstance();
        quocKhanh.set(2025, Calendar.SEPTEMBER, 2);
        metro.addFreeRideDay(quocKhanh, "Quoc Khanh");
        
        System.out.println("Tong so ngay le: " + metro.getFreeRideDays().size());
        System.out.println();
        
        System.out.println("==================== TEST 28: removeFreeRideDay() ====================");
        Calendar gioTo = Calendar.getInstance();
        gioTo.set(2025, Calendar.APRIL, 10);
        metro.addFreeRideDay(gioTo, "Gio To Hung Vuong");
        System.out.println("So ngay le truoc khi xoa: " + metro.getFreeRideDays().size());
        metro.removeFreeRideDay(gioTo);
        System.out.println("So ngay le sau khi xoa: " + metro.getFreeRideDays().size());
        System.out.println();
        
        System.out.println("==================== TEST 29: isFreeRideDay() ====================");
        System.out.println("Tet co phai ngay mien phi? " + metro.isFreeRideDay(tet));
        System.out.println("Quoc Khanh co phai ngay mien phi? " + metro.isFreeRideDay(quocKhanh));
        System.out.println("Hom nay co phai ngay mien phi? " + metro.isFreeRideDay(today));
        System.out.println();
        
        System.out.println("==================== TEST 30: getHolidayName() ====================");
        System.out.println("Ten ngay le Tet: " + metro.getHolidayName(tet));
        System.out.println("Ten ngay le Quoc Khanh: " + metro.getHolidayName(quocKhanh));
        System.out.println();
        
        System.out.println("==================== TEST 31: getAllFreeRideDays() ====================");
        List<Calendar> allHolidays = metro.getAllFreeRideDays();
        System.out.println("Danh sach cac ngay le:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Calendar holiday : allHolidays) {
            System.out.println("  - " + sdf.format(holiday.getTime()) + 
                    " (" + metro.getHolidayName(holiday) + ")");
        }
        System.out.println();
        
        System.out.println("==================== TEST 32: getUpcomingHolidays() ====================");
        List<Calendar> upcomingHolidays = metro.getUpcomingHolidays(5);
        System.out.println("Cac ngay le sap toi (gioi han 5):");
        for (Calendar holiday : upcomingHolidays) {
            System.out.println("  - " + sdf.format(holiday.getTime()) + 
                    " (" + metro.getHolidayName(holiday) + ")");
        }
        System.out.println();
        
       
	}
}
