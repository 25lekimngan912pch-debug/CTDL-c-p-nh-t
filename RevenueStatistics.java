package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.io.*;
import java.util.*;

public class RevenueStatistics {

	private String id; // Ma thong ke
	private double totalRevenue; // Tong doanh thu
	private Map<Calendar, Double> dailyRevenue; // Doanh thu theo ngay
	private Map<String, Double> monthlyRevenue; // Doanh thu theo thang ("2025-01")
	private Map<Station, Double> stationRevenue; // Doanh thu theo ga
	private Map<Train, Double> trainRevenue; // Doanh thu theo tau
	private Map<TicketType, Double> ticketTypeRevenue; // Doanh thu theo loai ve
	private String status; // "UPDATING", "COMPLETED"
	private Calendar lastUpdateTime; // Lan cap nhat cuoi

	/**
	 * Constructor
	 */
	public RevenueStatistics(String id) {
		this.id = id;
		this.totalRevenue = 0;
		this.dailyRevenue = new HashMap<>();
		this.monthlyRevenue = new HashMap<>();
		this.stationRevenue = new HashMap<>();
		this.trainRevenue = new HashMap<>();
		this.ticketTypeRevenue = new HashMap<>();
		this.status = "COMPLETED";
		this.lastUpdateTime = Calendar.getInstance();
	}

	// ===== GETTER METHODS =====
	public String getId() {
		return id;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public Map<Calendar, Double> getDailyRevenue() {
		return dailyRevenue;
	}

	public Map<String, Double> getMonthlyRevenue() {
		return monthlyRevenue;
	}

	public Map<Station, Double> getStationRevenue() {
		return stationRevenue;
	}

	public Map<Train, Double> getTrainRevenue() {
		return trainRevenue;
	}

	public Map<TicketType, Double> getTicketTypeRevenue() {
		return ticketTypeRevenue;
	}

	public String getStatus() {
		return status;
	}

	public Calendar getLastUpdateTime() {
		return lastUpdateTime;
	}

	// ===== SETTER METHODS =====
	public void setStatus(String status) {
		this.status = status;
	}

	// ===== BUSINESS METHODS =====

	/**
	 * 
	 * @param tickets danh sach ve
	 * @return tong doanh thu
	 */
	// TINH TONG DOANH THU TU DANH SACH VE
	public double calculateTotalRevenue(List<Ticket> tickets) {
		this.status = "UPDATING";
		totalRevenue = 0;

		if (tickets == null || tickets.isEmpty()) {
			this.status = "COMPLETED";
			return 0;
		}

		for (Ticket ticket : tickets) {
			if (ticket.getIsPaid() && !"CANCELLED".equals(ticket.getStatus())) {
				totalRevenue += ticket.calculatePrice();
			}
		}

		this.status = "COMPLETED";
		this.lastUpdateTime = Calendar.getInstance();

		System.out.println("Tổng doanh thu: " + String.format("%,.0f", totalRevenue) + " VND");
		return totalRevenue;
	}

	/**
	 */
	// TINH DOANH THU THEO GA
	public double calculateRevenueByStation(List<Ticket> tickets, Station station) {
		double revenue = 0;

		if (tickets == null || station == null) {
			return 0;
		}

		for (Ticket ticket : tickets) {
			if (ticket.getIsPaid() && (ticket.getDeparture().equals(station) || ticket.getArrival().equals(station))) {
				revenue += ticket.calculatePrice();
			}
		}

		stationRevenue.put(station, revenue);
		return revenue;
	}

	/**
	 * 
	 */
	// TINH DOAN THU THEO TAU
	public double calculateRevenueByTrain(List<Ticket> tickets, Train train) {
		double revenue = 0;

		if (tickets == null || train == null) {
			return 0;
		}

		for (Ticket ticket : tickets) {
			if (ticket.getIsPaid() && ticket.getScheduleDetail() != null
					&& train.equals(ticket.getScheduleDetail().getTrain())) {
				revenue += ticket.calculatePrice();
			}
		}

		trainRevenue.put(train, revenue);
		return revenue;
	}

	/**
	 * 
	 */
	// TINH DOANH THU THEO LOAI VE
	public double calculateRevenueByTicketType(List<Ticket> tickets, TicketType type) {
		double revenue = 0;

		if (tickets == null || type == null) {
			return 0;
		}

		for (Ticket ticket : tickets) {
			if (ticket.getIsPaid() && type.equals(ticket.getTicketType())) {
				revenue += ticket.calculatePrice();
			}
		}

		ticketTypeRevenue.put(type, revenue);
		return revenue;
	}

	/**
	 * 
	 */
	// TINH DOANH THU THEO NGAY
	public void calculateDailyRevenue(List<Ticket> tickets) {
		this.status = "UPDATING";
		dailyRevenue.clear();

		if (tickets == null) {
			this.status = "COMPLETED";
			return;
		}

		for (Ticket ticket : tickets) {
			if (ticket.getIsPaid() && ticket.getPurchaseTime() != null) {
				Calendar date = ticket.getPurchaseTime();

				// Làm tròn về đầu ngày
				Calendar dateKey = Calendar.getInstance();
				dateKey.setTime(date.getTime());
				dateKey.set(Calendar.HOUR_OF_DAY, 0);
				dateKey.set(Calendar.MINUTE, 0);
				dateKey.set(Calendar.SECOND, 0);

				double currentRevenue = dailyRevenue.getOrDefault(dateKey, 0.0);
				dailyRevenue.put(dateKey, currentRevenue + ticket.calculatePrice());
			}
		}

		this.status = "COMPLETED";
		this.lastUpdateTime = Calendar.getInstance();
	}

	/**
	 */
	// TINH DOANH THU THEO THANG
	public void calculateMonthlyRevenue(List<Ticket> tickets) {
		this.status = "UPDATING";
		monthlyRevenue.clear();

		if (tickets == null) {
			this.status = "COMPLETED";
			return;
		}

		for (Ticket ticket : tickets) {
			if (ticket.getIsPaid() && ticket.getPurchaseTime() != null) {
				Calendar date = ticket.getPurchaseTime();

				// Format: "2025-01"
				String monthKey = String.format("%d-%02d", date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1);

				double currentRevenue = monthlyRevenue.getOrDefault(monthKey, 0.0);
				monthlyRevenue.put(monthKey, currentRevenue + ticket.calculatePrice());
			}
		}

		this.status = "COMPLETED";
		this.lastUpdateTime = Calendar.getInstance();
	}

	/**
	 */
	// RESET TAT CA THONG KE
	public void resetStatistics() {
		totalRevenue = 0;
		dailyRevenue.clear();
		monthlyRevenue.clear();
		stationRevenue.clear();
		trainRevenue.clear();
		ticketTypeRevenue.clear();
		System.out.println("Da reset toan bo thong ke ");
	}

	/**
	 */
	// LAY DOANH THU CUA 1 NGAY CU THE
	public double getRevenueForDate(Calendar date) {
		// Lam tron ve dau ngay
		Calendar dateKey = Calendar.getInstance();
		dateKey.setTime(date.getTime());
		dateKey.set(Calendar.HOUR_OF_DAY, 0);
		dateKey.set(Calendar.MINUTE, 0);
		dateKey.set(Calendar.SECOND, 0);

		return dailyRevenue.getOrDefault(dateKey, 0.0);
	}

	/**
	 */
	// LAY DOANH THU CUA 1 THANG
	public double getRevenueForMonth(String month) {
		return monthlyRevenue.getOrDefault(month, 0.0);
	}

	/**
	 */
	// LAP TOP N GA CO DOANH THU CAO NHAT
	public List<Station> getTopRevenueStations(int limit) {
		List<Map.Entry<Station, Double>> entries = new ArrayList<>(stationRevenue.entrySet());
		// Sap xep theo doanh thu giam dan
		entries.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

		List<Station> topStations = new ArrayList<>();
		for (int i = 0; i < Math.min(limit, entries.size()); i++) {
			topStations.add(entries.get(i).getKey());
		}

		return topStations;
	}

	/**
	 */
	// TINH GIA VE TRUNG BINH
	public double getAverageTicketPrice() {
		if (dailyRevenue.isEmpty()) {
			return 0;
		}

		int ticketCount = dailyRevenue.size();
		return totalRevenue / ticketCount;
	}

	public static void main(String[] args) {
		System.out.println("==================== TAO DOI TUONG TEST ====================");

		RevenueStatistics stats = new RevenueStatistics("RS01");

		// ===== GA =====
		Station gaBenThanh = new Station();
		gaBenThanh.setName("Ben Thanh");
		Station gaSuoiTien = new Station();
		gaSuoiTien.setName("Suoi Tien");
		Station gaThuDuc = new Station();
		gaThuDuc.setName("Thu Duc");
		Station gaAnPhu = new Station();
		gaAnPhu.setName("An Phu");

		// ===== TUYEN & DOAN =====
		Route tuyen01 = new Route();
		tuyen01.setId("R01");
		tuyen01.setName("Ben Thanh - Suoi Tien");
		tuyen01.setRoutePartList(new ArrayList<>());
		tuyen01.setStationList(new ArrayList<>(Arrays.asList(gaBenThanh, gaThuDuc, gaSuoiTien)));

		RoutePart doan1 = new RoutePart("RP01", gaBenThanh, gaThuDuc, 10.0, tuyen01, 20);
		RoutePart doan2 = new RoutePart("RP02", gaThuDuc, gaSuoiTien, 9.7, tuyen01, 25);
		tuyen01.getRoutePartList().add(doan1);
		tuyen01.getRoutePartList().add(doan2);

		// ===== TAU =====
		Train tau01 = new Train();
		tau01.setId("T01");
		tau01.setName("Tau 01");
		Train tau02 = new Train();
		tau02.setId("T02");
		tau02.setName("Tau 02");

		// ===== LICH TRINH =====
		ScheduleDetail lichTrinhSang = new ScheduleDetail();
		lichTrinhSang.setId("SD01");
		lichTrinhSang.setRoute(tuyen01);
		lichTrinhSang.setTrain(tau01);
		lichTrinhSang.setTotalSeat(100);
		lichTrinhSang.setAvailableSeat(80);

		ScheduleDetail lichTrinhChieu = new ScheduleDetail();
		lichTrinhChieu.setId("SD02");
		lichTrinhChieu.setRoute(tuyen01);
		lichTrinhChieu.setTrain(tau02);
		lichTrinhChieu.setTotalSeat(120);
		lichTrinhChieu.setAvailableSeat(100);

		// ===== LOAI VE =====
		TicketType veLuot = new TicketType("TT01", "Ve luot", 0.0, 1, "Ve di mot luot");
		TicketType veNgay = new TicketType("TT02", "Ve ngay", 0.1, 1, "Ve di trong ngay");
		TicketType veThang = new TicketType("TT03", "Ve thang", 0.2, 30, "Ve di trong thang");

		// ===== KHACH HANG =====
		Customer khachThuong = new Customer("24130350", "Tran Thi Cam Tu");
		khachThuong.setPriority(0);
		Customer khachCaoTuoi = new Customer("24130197", "Le Thi Kim Ngan");
		khachCaoTuoi.setPriority(1);
		Customer khachKhuyetTat = new Customer("C03", "Le Van C");
		khachKhuyetTat.setPriority(2);

		// ===== VE =====
		Ticket v1 = new Ticket("T01", khachThuong, lichTrinhSang, gaBenThanh, gaThuDuc, doan1, veLuot);
		v1.setPaid(true);
		v1.setStatus("ACTIVE");
		v1.setPurchaseTime(Calendar.getInstance());

		Ticket v2 = new Ticket("T02", khachCaoTuoi, lichTrinhSang, gaThuDuc, gaSuoiTien, doan2, veNgay);
		v2.setPaid(true);
		v2.setStatus("ACTIVE");
		Calendar thangTruoc = Calendar.getInstance();
		thangTruoc.add(Calendar.MONTH, -1);
		v2.setPurchaseTime(thangTruoc);

		Ticket v3 = new Ticket("T03", khachKhuyetTat, lichTrinhChieu, gaBenThanh, gaSuoiTien, doan2, veThang);
		v3.setPaid(true);
		v3.setStatus("ACTIVE");
		Calendar namTruoc = Calendar.getInstance();
		namTruoc.add(Calendar.YEAR, -1);
		v3.setPurchaseTime(namTruoc);

		Ticket v4 = new Ticket("T04", khachThuong, lichTrinhChieu, gaAnPhu, gaSuoiTien, doan2, veLuot);
		v4.setPaid(false);
		v4.setStatus("CANCELLED");
		v4.setPurchaseTime(Calendar.getInstance());

		Ticket v5 = new Ticket("T05", khachCaoTuoi, lichTrinhSang, gaBenThanh, gaSuoiTien, doan1, veNgay);
		v5.setPaid(true);
		v5.setStatus("ACTIVE");
		Calendar homQua = Calendar.getInstance();
		homQua.add(Calendar.DATE, -1);
		v5.setPurchaseTime(homQua);

		List<Ticket> tickets = Arrays.asList(v1, v2, v3, v4, v5);

		System.out.println("Da tao " + tickets.size() + " ve test\n");

		// ===== TEST CAC PHUONG THUC =====
		System.out.println("==================== TEST TINH TONG DOANH THU TU DANH SACH VE ====================");
		stats.calculateTotalRevenue(tickets);
		System.out.println("Tong doanh thu: " + stats.getTotalRevenue());
		System.out.println();

		System.out.println("==================== TEST TINH DOANH THU THEO GA ====================");
		double revStation = stats.calculateRevenueByStation(tickets, gaBenThanh);
		System.out.println("Doanh thu ga Ben Thanh: " + revStation);
		System.out.println();

		System.out.println("==================== TEST TINH DOAN THU THEO TAU ====================");
		double revTrain1 = stats.calculateRevenueByTrain(tickets, tau01);
		double revTrain2 = stats.calculateRevenueByTrain(tickets, tau02);
		System.out.println("Doanh thu tau 01: " + revTrain1);
		System.out.println("Doanh thu tau 02: " + revTrain2);
		System.out.println();

		System.out.println("==================== TEST TINH DOANH THU THEO LOAI VE ====================");
		double revLuot = stats.calculateRevenueByTicketType(tickets, veLuot);
		double revNgay = stats.calculateRevenueByTicketType(tickets, veNgay);
		double revThang = stats.calculateRevenueByTicketType(tickets, veThang);
		System.out.println("Doanh thu ve luot: " + revLuot);
		System.out.println("Doanh thu ve ngay: " + revNgay);
		System.out.println("Doanh thu ve thang: " + revThang);
		System.out.println();

		System.out.println("==================== TEST TINH DOANH THU THEO NGAY ====================");
		stats.calculateDailyRevenue(tickets);
		for (Map.Entry<Calendar, Double> entry : stats.getDailyRevenue().entrySet()) {
			System.out.println("Ngay: " + entry.getKey().getTime() + " | Doanh thu: " + entry.getValue());
		}
		System.out.println();

		System.out.println("==================== TEST TINH DOANH THU THEO THANG ====================");
		stats.calculateMonthlyRevenue(tickets);
		for (Map.Entry<String, Double> entry : stats.getMonthlyRevenue().entrySet()) {
			System.out.println("Thang: " + entry.getKey() + " | Doanh thu: " + entry.getValue());
		}
		System.out.println();

		System.out.println("==================== TEST LAY DOANH THU CUA 1 NGAY CU THE ====================");
		Calendar today = Calendar.getInstance();
		double revDate = stats.getRevenueForDate(today);
		System.out.println("Doanh thu ngay hom nay: " + revDate);
		System.out.println();

		System.out.println("==================== TEST LAY DOANH THU CUA 1 THANG ====================");
		String monthKey = String.format("%d-%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1);
		double revMonth = stats.getRevenueForMonth(monthKey);
		System.out.println("Doanh thu thang " + monthKey + ": " + revMonth);
		System.out.println();

		System.out.println("==================== TEST LAP TOP N GA CO DOANH THU CAO NHAT ====================");
		List<Station> topStations = stats.getTopRevenueStations(2);
		for (Station st : topStations) {
			System.out.println("Top station: " + st.getName());
		}
		System.out.println();

		System.out.println("==================== TEST TINH GIA VE TRUNG BINH ====================");
		double avgPrice = stats.getAverageTicketPrice();
		System.out.println("Gia ve trung binh: " + avgPrice);
		System.out.println();

		System.out.println("==================== TEST RESET TAT CA CAC THONG ====================");
		stats.resetStatistics();
		System.out.println("Tong doanh thu sau reset: " + stats.getTotalRevenue());
	}
}