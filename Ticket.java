package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.io.*;
import java.util.*;

public class Ticket {
	private String id;
	private Customer customer;
	private ScheduleDetail scheduleDetail;
	private Station departure;
	private Station arrival;
	private RoutePart routePart;
	private TicketType ticketType;
	private Order order;
	private boolean isPaid;
	private boolean isCheckIn;
	private String status;// "ACTIVE", "WAITING", "USED", "EXPIRED", "CANCELLED"
	private String qrCode;
	private String cardCode;
	private Calendar purchaseTime;
	private Calendar validUntil;
	private int queuePosition;// 0 neu ko cho
	private Calendar queueTime;// null neu khong cho

	public Ticket(String id, Customer customer, ScheduleDetail scheduleDetail, Station departure, Station arrival,
			RoutePart routePart, TicketType ticketType, Order order, boolean isPaid, boolean isCheckIn, String status,
			String qrCode, String cardCode, Calendar purchaseTime, Calendar validUntil, int queuePosition,
			Calendar queueTime) {
		super();
		this.id = id;
		this.customer = customer;
		this.scheduleDetail = scheduleDetail;
		this.departure = departure;
		this.arrival = arrival;
		this.routePart = routePart;
		this.ticketType = ticketType;
		this.order = order;
		this.status = "ACTIVE";
		this.isPaid = true;
		this.isCheckIn = false;
		this.qrCode = qrCode;
		this.cardCode = cardCode;
		this.purchaseTime = Calendar.getInstance();
		this.validUntil = validUntil;
		this.queuePosition = queuePosition;
		this.queueTime = queueTime;
	}

	public Ticket(String id, Customer customer, ScheduleDetail scheduleDetail, Station departure, Station arrival,
			RoutePart routePart, TicketType ticketType) {
		super();
		this.id = id;
		this.customer = customer;
		this.scheduleDetail = scheduleDetail;
		this.departure = departure;
		this.arrival = arrival;
		this.routePart = routePart;
		this.ticketType = ticketType;
		this.order = order;
		this.status = "ACTIVE";
		this.isPaid = true;
		this.isCheckIn = false;
		this.qrCode = qrCode;
		this.cardCode = cardCode;
		this.purchaseTime = Calendar.getInstance();
		this.validUntil = validUntil;
		this.queuePosition = queuePosition;
		this.queueTime = queueTime;
	}

	public Ticket(String id, Customer customer, ScheduleDetail scheduleDetail) {
		this.id = id;
		this.customer = customer;
		this.scheduleDetail = scheduleDetail;
		this.departure = departure;
		this.arrival = arrival;
		this.routePart = routePart;
		this.ticketType = ticketType;
		this.order = order;
		this.status = "ACTIVE";
		this.isPaid = true;
		this.isCheckIn = false;
		this.qrCode = qrCode;
		this.cardCode = cardCode;
		this.purchaseTime = Calendar.getInstance();
		this.validUntil = validUntil;
		this.queuePosition = queuePosition;
		this.queueTime = queueTime;
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub

	}

	public Ticket(String id, Customer customer, Station departure, Station arrival, RoutePart routePart,
			TicketType ticketType) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.customer = customer;
		this.scheduleDetail = scheduleDetail;
		this.departure = departure;
		this.arrival = arrival;
		this.routePart = routePart;
		this.ticketType = ticketType;
		this.order = order;
		this.status = "ACTIVE";
		this.isPaid = true;
		this.isCheckIn = false;
		this.qrCode = qrCode;
		this.cardCode = cardCode;
		this.purchaseTime = Calendar.getInstance();
		this.validUntil = validUntil;
		this.queuePosition = queuePosition;
		this.queueTime = queueTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ScheduleDetail getScheduleDetail() {
		return scheduleDetail;
	}

	public void setScheduleDetail(ScheduleDetail scheduleDetail) {
		this.scheduleDetail = scheduleDetail;
	}

	public Station getDeparture() {
		return departure;
	}

	public void setDeparture(Station departure) {
		this.departure = departure;
	}

	public Station getArrival() {
		return arrival;
	}

	public void setArrival(Station arrival) {
		this.arrival = arrival;
	}

	public RoutePart getRoutePart() {
		return routePart;
	}

	public void setRoutePart(RoutePart routePart) {
		this.routePart = routePart;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean isCheckIn() {
		return isCheckIn;
	}

	public void setCheckIn(boolean isCheckIn) {
		this.isCheckIn = isCheckIn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public Calendar getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Calendar purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public Calendar getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Calendar validUntil) {
		this.validUntil = validUntil;
	}

	public int getQueuePosition() {
		return queuePosition;
	}

	public void setQueuePosition(int queuePosition) {
		this.queuePosition = queuePosition;
	}

	public Calendar getQueueTime() {
		return queueTime;
	}

	public void setQueueTime(Calendar queueTime) {
		this.queueTime = queueTime;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	/////////////////////////// phuong thuc /////////////////////////////
	private static final double Duoi_5KM = 7000.0; // Duoi 5 km dau tien
	private static final double Tu_5_15 = 1000.0; // Moi km tu 5-15km
	private static final double Tren_15 = 500.0; // Moi km tren 15km
	private static final double Gia_1ngay = 40000.0; // Gia ve 1 ngay

	/**
	 * @return
	 */
	//TINH GIA VE
	public double calculatePrice() {
		// TODO implement here
		double price = 0;
		String type = ticketType.getName();

		// 1. Kiem tra loai ve
		if (type.equalsIgnoreCase("Ve ngay")) {
			price = Gia_1ngay;
		} else if (type.equalsIgnoreCase("Ve luot")) {
			double distance = routePart.getDistanceKm();

			// Tinh gia quang duong
			if (distance <= 5) {
				price = Duoi_5KM;
			} else if (distance <= 15) {
				price = Duoi_5KM + (distance - 5) * Tu_5_15;
			} else {
				price = Duoi_5KM + (10 * Tu_5_15) + (distance - 15) * Tren_15;
			}
		}
		// 2. Xet doi tuong hanh khach (Priority)
		// 0: Thuong
		// 1: Sinh viên/Nguoi gia (giam 50%)
		// 2: Nguoi khuyet tat (mien phi)
		int priority = customer.getPriority();
		if (priority == 1) {
			price = price * 0.5;
		} else if (priority == 2) {
			price = 0.0;
		}
		// 3. Lam tron len hang nghin ( Vi metro khong thu tien le duoi 1000đ)
		return Math.ceil(price / 1000) * 1000;
	}

	// KIEM TRA VE CON HIEU LUC HAY KHONG
	public boolean isValid() {
		// TODO implement here
		Calendar now = Calendar.getInstance();
		// Kiem tra trang thai
		if (!status.trim().equalsIgnoreCase("ACTIVE")) {
			return false;
		}

		// Kiem tra da thanh toan
		if (!isPaid) {
			return false;
		}

		// Kiem tra han su dung
		if (validUntil != null && now.after(validUntil)) {
			return false;
		}

		// Kiem tra tau khoi hanh chua
		Calendar departureTime = scheduleDetail.getStartDateTime();
		if (now.after(departureTime)) {
			return false;
		}

		return true;
	}

	// KIEM TRA VE CO DANG CHO KHONG
	public boolean isWaiting() {
		// TODO implement here
		return status.trim().equalsIgnoreCase("WAITING") && queuePosition > 0;
	}

	/**
	 * @return
	 */
	// QUET MA QR
	public boolean scanQr() {
		// TODO implement here
		if (!isValid()) {
			System.out.println("Ve khong hop le !");
			return false;
		}

		// Kiem tra da check in chua
		if (isCheckIn) {
			System.out.println("Ve da duoc check in truoc do ");
			return false;
		}

		// Kiem tra thoi gian check in ( trc 15 phut)
		Calendar now = Calendar.getInstance();
		Calendar departureTime = scheduleDetail.getStartDateTime();
		long diffMinutes = (departureTime.getTimeInMillis() - now.getTimeInMillis()) / (60 * 1000);

		if (diffMinutes > 15) {
			System.out.println("Chua den gio check in");
			return false;
		}

		if (diffMinutes < 0) {
			System.out.println("Da qua gio khoi hanh");
			return false;
		}

		// Check-in thanh cong
		checkIn();
		System.out.println("Check- in thanh cong");
		return true;
	}

	/**
	 * @return
	 */
	// THUC HIEN CHECK IN
	public void checkIn() {
		// TODO implement here
		this.isCheckIn = true;
		this.status = "USED";

		// Tang so luong hanh khach tai ga
		departure.incrementPassengerCount();
	}

	/**
	 * @return
	 */
	// DANH DAU VE HET HAN
	public void expire() {
		// TODO implement here
		if (status.trim().equalsIgnoreCase("ACTIVE") || status.trim().equalsIgnoreCase("WAITING")) {
			this.status = "EXPIRED";
			System.out.println("Ve " + id + " da het han");
		}
	}

	/**
	 * @return
	 */
	// HUY VE
	public boolean cancel() {
		// TODO implement here
		// Kiem tra trang thai
		if (status.trim().equalsIgnoreCase("USED")) {
			System.out.println("Khong the huy ve da su dung");
			return false;
		}

		if (status.trim().equalsIgnoreCase("CANCELLED")) {
			System.out.println("Ve da duoc huy truoc do");
			return false;
		}

		// Kiem tra thoi gian huy ( truoc 2 gio)
		Calendar now = Calendar.getInstance();
		Calendar departureTime = scheduleDetail.getStartDateTime();
		long diffHours = (departureTime.getTimeInMillis() - now.getTimeInMillis()) / (60 * 60 * 1000);

		if (diffHours < 2) {
			System.out.println("Khong the huy ve");
			return false;
		}

		// Huy ve
		this.status = "CANCELLED";
		this.isPaid = false;

		System.out.println("Da huy ve " + id);
		return true;
	}

	/**
	 * @return
	 */
	// TINH THOI GIAN CHO (PHUT)
	public long getWaitingDuration() {
		// TODO implement here
		if (queueTime == null) {
			return 0;
		}

		Calendar now = Calendar.getInstance();
		long diffMillis = now.getTimeInMillis() - queueTime.getTimeInMillis();
		return diffMillis / (60 * 1000); // chuyen sang phut
	}

	/**
	 * @return
	 */
	// TAO MA QR CHO VE
	public String generateQrCode() {
		// TODO implement here
		// MA QR = ID ve+ thoi gian mua + ma khach hang
		this.qrCode = "QR-" + id + "-" + customer.getId() + "-" + purchaseTime.getTimeInMillis();
		return qrCode;
	}

	/**
	 * @return
	 */
	// KIEM TRA CO PHAI VE THANG KHONG
	public boolean isSubscription() {
		// TODO implement here
		return ticketType.getName().trim().equalsIgnoreCase("Ve thang");
	}

	// HAN SU DUNG LUC
	public void calculateValidUntil() {
		validUntil = (Calendar) purchaseTime.clone();
		validUntil.add(Calendar.DAY_OF_MONTH, ticketType.getValidityDays());
	}

	// THONG TIN VE
	public String getInfo() {
		StringBuilder info = new StringBuilder();
		info.append("Ten khach hang: ").append(customer.getName()).append("\n");
		info.append("Ve: ").append(id).append("\n");
		info.append("Tuyen: ").append(departure.getName()).append(" -> ").append(arrival.getName()).append("\n");
		info.append("Loai ve: ").append(ticketType.getName()).append("\n");
		info.append("Gia: ").append(String.format("%.0f", calculatePrice())).append(" VND\n");
		info.append("Trang thai: ").append(status).append("\n");
		info.append("Ngay mua: ").append(formatCalendar(purchaseTime)).append("\n");
		info.append("Hieu luc den: ").append(formatCalendar(validUntil));
		return info.toString();
	}

	// FORMAT CALENDAR THANH CHUOI NGAY
	public String formatCalendar(Calendar cal) {
		if (cal == null)
			return "N/A";
		return String.format("%02d/%02d/%04d %02d:%02d:%02d", cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY),
				cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}

	public boolean isCheckedIn() {
		// TODO Auto-generated method stub
		return this.isCheckIn;
	}
	    public static void main(String[] args) {
	        System.out.println("==================== TAO DOI TUONG TEST ====================");

	        // ===== GA =====
	        Station gaBenThanh = new Station("ST01", "Ben Thanh", "Quan 1", 10.77, 106.69, true);
	        Station gaSuoiTien = new Station("ST14", "Suoi Tien", "Thu Duc", 10.89, 106.80, true);
	        Station gaThuDuc = new Station("ST11", "Thu Duc", "Thu Duc", 10.85, 106.75, false);

	        // ===== TUYEN & DOAN =====
	        Route tuyen01 = new Route("R01", "Ben Thanh - Suoi Tien", new ArrayList<>(), new ArrayList<>(), 19.7, true, "FORWARD");
	        RoutePart doan01 = new RoutePart("RP01", gaBenThanh, gaThuDuc, 10.0, tuyen01, 20);
	        RoutePart doan02 = new RoutePart("RP02", gaThuDuc, gaSuoiTien, 9.7, tuyen01, 25);

	        // ===== LOAI VE =====
	        TicketType veLuot = new TicketType("TT01", "Ve luot", 0.0, 1, "Ve di mot luot");
	        TicketType veNgay = new TicketType("TT02", "Ve ngay", 0.0, 1, "Ve di trong ngay");
	        TicketType veThang = new TicketType("TT03", "Ve thang", 0.2, 30, "Ve di trong thang");

	        // ===== LICH TRINH =====
	        Calendar ngayChay = Calendar.getInstance();
	        ngayChay.add(Calendar.HOUR_OF_DAY, 3); // khoi hanh sau 3h
	        ScheduleDetail lichTrinh01 = new ScheduleDetail("SD01", ngayChay, 8.0, 9.0, tuyen01, "FORWARD",
	                null, false, "SCHEDULED", 0, 100, 100, null);

	        // ===== KHACH HANG =====
	        Customer khachThuong = new Customer("24130350", "Tran Thi Cam Tu", "a@gmail.com", "0901111111"); khachThuong.setPriority(0);
	        Customer khachCaoTuoi = new Customer("24130197", "Le Thi Kim Ngan", "b@gmail.com", "0902222222"); khachCaoTuoi.setPriority(1);
	        Customer khachKhuyetTat = new Customer("C03", "Le Van C", "c@gmail.com", "0903333333"); khachKhuyetTat.setPriority(2);

	        // ===== VE =====
	        Ticket t1 = new Ticket("T01", khachThuong, lichTrinh01, gaBenThanh, gaThuDuc, doan01, veLuot);
	        Ticket t2 = new Ticket("T02", khachCaoTuoi, lichTrinh01, gaThuDuc, gaSuoiTien, doan02, veLuot);
	        Ticket t3 = new Ticket("T03", khachKhuyetTat, lichTrinh01, gaBenThanh, gaSuoiTien, doan02, veLuot);
	        Ticket t4 = new Ticket("T04", khachThuong, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veNgay);
	        Ticket t5 = new Ticket("T05", khachCaoTuoi, lichTrinh01, gaBenThanh, gaSuoiTien, doan02, veThang);

	        System.out.println("Da tao " + Arrays.asList(t1,t2,t3,t4,t5).size() + " ve test\n");

	        // ===== TEST CAC PHUONG THUC =====
	        System.out.println("==================== TEST TINH GIA VE ====================");
	        System.out.println("Gia ve luot (thuong): " + t1.calculatePrice());
	        System.out.println("Gia ve luot (cao tuoi - giam 50%): " + t2.calculatePrice());
	        System.out.println("Gia ve luot (khuyet tat - mien phi): " + t3.calculatePrice());
	        System.out.println("Gia ve ngay: " + t4.calculatePrice());
	        System.out.println("Gia ve thang (giam 20%): " + t5.calculatePrice());
	        System.out.println();

	        System.out.println("==================== TEST KIEM TRA VE CON HIEU LUC HAY KHONG ====================");
	        t1.setValidUntil((Calendar) ngayChay.clone());
	        System.out.println("Ve hop le: " + t1.isValid());
	        t1.setStatus("EXPIRED");
	        System.out.println("Ve het han: " + t1.isValid());
	        t1.setStatus("ACTIVE"); t1.setPaid(false);
	        System.out.println("Ve chua thanh toan: " + t1.isValid());
	        System.out.println();

	        System.out.println("==================== TEST KIEM TRA VE CO DANG CHO KHONG ====================");
	        t1.setStatus("WAITING"); t1.setQueuePosition(2);
	        System.out.println("Ve dang cho: " + t1.isWaiting());
	        t1.setQueuePosition(0);
	        System.out.println("Ve khong cho: " + t1.isWaiting());
	        System.out.println();

	        System.out.println("==================== TEST QUET MA QR ====================");
	        t1.setStatus("ACTIVE"); t1.setPaid(true); t1.setValidUntil((Calendar) ngayChay.clone());
	        boolean scan1 = t1.scanQr();
	        System.out.println("Ket qua scan: " + scan1);
	        System.out.println();

	        System.out.println("==================== TEST THUC HIEN CHECK IN ====================");
	        t1.checkIn();
	        System.out.println("Trang thai ve sau checkin: " + t1.getStatus());
	        System.out.println("So khach tai ga Ben Thanh: " + gaBenThanh.getPassengerCount());
	        System.out.println();

	        System.out.println("==================== TEST DANH DAU VE HET HAN ====================");
	        t1.setStatus("ACTIVE"); t1.expire();
	        System.out.println("Trang thai ve sau expire: " + t1.getStatus());
	        System.out.println();

	        System.out.println("==================== TEST HUY VE ====================");
	        Ticket tCancel = new Ticket("T06", khachThuong, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
	        tCancel.setValidUntil((Calendar) ngayChay.clone());
	        System.out.println("Ket qua huy ve: " + tCancel.cancel());
	        tCancel.setStatus("USED"); System.out.println("Huy ve da su dung: " + tCancel.cancel());
	        tCancel.setStatus("CANCELLED"); System.out.println("Huy ve da huy truoc do: " + tCancel.cancel());
	        System.out.println();

	        System.out.println("==================== TEST TINH THOI GIAN CHO (PHUT) ====================");
	        Ticket tWait = new Ticket("T07", khachThuong, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
	        Calendar queueTime = Calendar.getInstance(); queueTime.add(Calendar.MINUTE, -30);
	        tWait.setQueueTime(queueTime);
	        System.out.println("Thoi gian cho: " + tWait.getWaitingDuration() + " phut");
	        System.out.println();

	        System.out.println("==================== TEST TAO MA QR CHO VE ====================");
	        System.out.println("Ma QR: " + t1.generateQrCode());
	        System.out.println();

	        System.out.println("==================== TEST KIEM TRA CO PHAI VE THANG KHONG ====================");
	        Ticket tSub = new Ticket("T08", khachThuong, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veThang);
	        System.out.println("Ve thang: " + tSub.isSubscription());
	        System.out.println();

	        System.out.println("==================== TEST HAN SU DUNG CUA VE ====================");
	        tSub.calculateValidUntil();
	        System.out.println("Han su dung ve thang: " + tSub.formatCalendar(tSub.getValidUntil()));
	        System.out.println();

	        System.out.println("==================== TEST THONG TIN VE ====================");
	        System.out.println("\n Thong tin ve khach hang 1 \n");
	        System.out.println(t1.getInfo());
	        System.out.println("\n Thong tin ve khach hang 2 \n");
	        System.out.println(t2.getInfo());
	    }
}