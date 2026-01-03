package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
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

	/**
	 * Them khach hang
	 * 
	 * @param c Khach hang can them
	 */
	public void addCustomer(Customer c) {
		if (c != null && !customers.contains(c)) {
			customers.add(c);
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
		return schedules.stream().flatMap(sd -> sd.getDetails().stream()).filter(detail -> detail.getRoute().getStationList().contains(station)).filter(detail -> detail.getStartDateTime().after(after)).filter(detail -> !"CANCELLED".equals(detail.getStatus())).sorted(Comparator.comparing(ScheduleDetail::getStartDateTime)).limit(limit).collect(Collectors.toList());
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
        if (route == null) return new ArrayList<>();
        
        return schedules.stream().filter(sd -> sd.getRoute().equals(route)).collect(Collectors.toList());
    }

    /**
     * Lay lich trinh trong khoang thoi gian
     * 
     * @param start Ngay bat dau
     * @param end Ngay ket thuc
     * @return Danh sach lich trinh
     */
    public List<ScheduleDaily> getSchedulesByDateRange(Calendar start, Calendar end) {
        return schedules.stream().filter(sd -> !sd.getDate().before(start) && !sd.getDate().after(end)).collect(Collectors.toList());
    }
    
    /**
     * Tinh tong doanh thu
     * 
     * @return Tong doanh thu (VNĐ)
     */
    public double calculateTotalRevenue() {
        return Optional.ofNullable(revenueStatistics)
            .map(RevenueStatistics::getTotalRevenue)
            .orElse(0.0);
    }
    /**
     * Validate ve tai ga
     * 
     * @param ticketId ID ve
     * @param at Thoi diem kiem tra
     * @param stationId ID ga
     * @return true neu ve hop le
     */
    public boolean validateTicket(String ticketId, Calendar at, String stationId) {
      
            Station station = findStationById(stationId);
            if (station == null) return false;
            
            QrValidator validator = new QrValidator("VAL_" + stationId, station);
            
            // Tim ve
            Optional<Ticket> ticketOpt = customers.stream()
                .flatMap(c -> c.getActiveTickets().stream())
                .filter(t -> t.getId().equals(ticketId))
                .findFirst();
            
            return ticketOpt.map(ticket -> validator.validate(ticket, at))
                .orElse(false);
                
        
        }

    /**
     * Lay trang thai he thong
     * 
     * @return Chuoi mo ta trang thai
     */
    public String getSystemStatus() {
        return String.format(
            "========== METRO SYSTEM STATUS ==========\n" +
            "System: %s\n" +
            "Stations: %d\n" +
            "Trains: %d (Available: %d)\n" +
            "Routes: %d (Active: %d)\n" +
            "Customers: %d\n" +
            "Bus Routes: %d\n" +
            "Ticket Types: %d\n" +
            "Free Ride Days: %d\n" +
            "Operating Hours: %s\n" +
            "Emergency: %s\n" +
            "=========================================",
            name, stations.size(), trains.size(),
            trains.stream().filter(Train::isAvailable).count(),
            routes.size(),
            routes.stream().filter(Route::isActive).count(),
            customers.size(), busRoutes.size(),
            ticketTypes.size(), freeRideDays.size(),
            operatingHours, emergencyContact
        );
    }
    /**
     * Tom tat he thong
     * 
     * @return Chuoi tom tat
     */
    public String getSystemSummary() {
        return String.format("%s: %d ga, %d tàu, %d tuyến, %d ngày lễ",
            name, stations.size(), trains.size(), routes.size(), freeRideDays.size());
    }
    /**
     * Health check he thong
     * 
     * @return Trang thai health
     */
    public String healthCheck() {
        boolean hasStations = !stations.isEmpty();
        boolean hasTrains = !trains.isEmpty();
        boolean hasRoutes = !routes.isEmpty();
        boolean hasSchedules = !schedules.isEmpty();
        
        if (hasStations && hasTrains && hasRoutes && hasSchedules) {
            return "System HEALTHY";
        } else {
            return "System UNHEALTHY";
        }
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
                System.out.println("Them  ngay le: " + holidayName + 
                    " (" + formatDate(truncated) + ")");
            
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
        return freeRideDays.stream()
            .anyMatch(freeDay -> isSameDay(freeDay, truncated));
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
        
        return freeRideDays.stream()
            .filter(date -> date.after(now))
            .sorted()
            .limit(limit)
            .collect(Collectors.toList());
    }

}
