package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.channels.AcceptPendingException;
import java.util.*;

public class Customer {
	private String id;// ID cua khach hang
	private String name;// ten khach hang
	private String email;// email khach hang
	private String phone;// so dien thoai khach hang
	private ArrayList<Ticket> ticketHistory;
	private ArrayList<Order> orderHistory;
	private Set<Ticket> activeTickets;
	private Set<Ticket> waitingTickets;
	private int priority;// 0 = thuong, 1= cao tuoi, 2= khuyet tat

	public Customer(String id, String name, String email, String phone, ArrayList<Ticket> ticketHistory,
			ArrayList<Order> orderHistory, Set<Ticket> activeTickets, Set<Ticket> waitingTickets, int priority) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.ticketHistory = new ArrayList<>();
		this.orderHistory = new ArrayList<>();
		this.activeTickets = new HashSet<>();
		this.waitingTickets = new HashSet<>();
		this.priority = 0;// mac dinh la khach thuong
	}

	public Customer(String id, String name) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.ticketHistory = new ArrayList<>();
		this.orderHistory = new ArrayList<>();
		this.activeTickets = new HashSet<>();
		this.waitingTickets = new HashSet<>();
		this.priority = 0;// mac dinh la khach thuong
	}

	public Customer(String id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.ticketHistory = new ArrayList<>();
		this.orderHistory = new ArrayList<>();
		this.activeTickets = new HashSet<>();
		this.waitingTickets = new HashSet<>();
		this.priority = 0;// mac dinh la khach thuong
	}
/// get set///

	public Customer() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<Ticket> getTicketHistory() {
		return ticketHistory;
	}

	public void setTicketHistory(ArrayList<Ticket> ticketHistory) {
		this.ticketHistory = ticketHistory;
	}

	public ArrayList<Order> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(ArrayList<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}

	public Set<Ticket> getActiveTickets() {
		return activeTickets;
	}

	public void setActiveTickets(Set<Ticket> activeTickets) {
		this.activeTickets = activeTickets;
	}

	public Set<Ticket> getWaitingTickets() {
		return waitingTickets;
	}

	public void setWaitingTickets(Set<Ticket> waitingTickets) {
		this.waitingTickets = waitingTickets;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		if (priority >= 0 && priority <= 2) {
			this.priority = priority;
		}
	}
///////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return
	 */
	// TAO DON MOI
	public Order placeOrder() {
		// TODO implement here
		// tao ID oder
		String orderId = "Order ID: " + System.currentTimeMillis();
		Order newOrder = new Order(orderId, this);
		if (this.orderHistory == null)
			this.orderHistory = new ArrayList<>();
		orderHistory.add(newOrder);
		return newOrder;
	}

	/**
	 * @param departure
	 * @param arrival
	 * @param ticketType
	 * @param scheduleDetail
	 * @return
	 */
	// MUA VE TAU
	public Ticket buyTicket(Station departure, Station arrival, TicketType ticketType, ScheduleDetail scheduleDetail) {
	    // 1. Kiem tra neu chuyen tau da het cho
	    if (scheduleDetail.isFull()) {
	        System.out.println("Xin loi " + name + ", chuyen tau nay da het cho.");
	        return null;
	    }

	    // 2. Tao don hang moi de quan ly viec thanh toan
	    Order order = this.placeOrder();

	    // 3. Tao ma ve ngau nhien
	    String ticketId = "TKT-" + System.currentTimeMillis();

	    // 4. Tim doan tuyen giua 2 ga
	    RoutePart routePart = scheduleDetail.getRoute().getRoutePartBetween(departure, arrival);

	    // Neu khong tim thay doan tuyen, tao doan tuyen tam thoi
	    if (routePart == null) {
	        double distance = departure.calculateDistanceTo(arrival);
	        int estimatedTime = (int) (distance * 2); // Uoc tinh 2 phut/km
	        routePart = new RoutePart("RP_TEMP_" + System.currentTimeMillis(), departure, arrival, distance,
	                scheduleDetail.getRoute(), estimatedTime);
	    }

	    // 5. Tao doi tuong ve moi
	    Ticket newTicket = new Ticket(ticketId, this, scheduleDetail, departure, arrival, routePart, ticketType);
	    newTicket.setStatus("ACTIVE"); // Trang thai hoat dong ngay
	    newTicket.setPaid(true);       // Mac dinh da thanh toan
	    newTicket.setPurchaseTime(Calendar.getInstance());
	    newTicket.calculateValidUntil(); // Tinh han su dung dua tren loai ve

	    // 6. Cap nhat du lieu he thong
	    order.addTicket(newTicket);
	    this.activeTickets.add(newTicket);
	    this.ticketHistory.add(newTicket);

	    // 7. Giam so ghe trong tren tau
	    scheduleDetail.reserveSeat();

	    // 8. Thong bao
	    System.out.println("Chuc mung " + name + ", ban da mua ve thanh cong ma: " + ticketId);
	    return newTicket;
	}

	/**
	 * @param departure
	 * @param arrival
	 * @param ticketType
	 * @param scheduleDetail
	 * @return
	 */
	// MUA VE VA CHO NEU DA HET CHO
	public Ticket buyTicketWithWaiting(Station departure, Station arrival, TicketType ticketType,
			ScheduleDetail scheduleDetail) {
		// Neu tau con cho thi goi phuong thuc buyTicket mua truc tiep
		if (!scheduleDetail.isFull()) {
			return buyTicket(departure, arrival, ticketType, scheduleDetail);
		}

		// Neu het cho, tien hanh tao ve o trang thai "WAITING"
		String ticketId = "WAIT-" + System.currentTimeMillis();

		// Tim doan tuyen giua 2 ga
		RoutePart routePart = scheduleDetail.getRoute().getRoutePartBetween(departure, arrival);

		// Neu khong tim thay doan tuyen, tao doan tuyen tam thoi
		if (routePart == null) {
			double distance = departure.calculateDistanceTo(arrival);
			int estimatedTime = (int) (distance * 2);

			routePart = new RoutePart("RP_TEMP_" + System.currentTimeMillis(), departure, arrival, distance,
					scheduleDetail.getRoute(), estimatedTime);
		}

		Ticket waitingTicket = new Ticket(ticketId, this, scheduleDetail, departure, arrival, routePart, ticketType);
		waitingTicket.setStatus("WAITING");

		// Cap nhat danh sach customer
		this.addWaitingTicket(waitingTicket);
		this.ticketHistory.add(waitingTicket);

		// Dua ve vao hang cho
		WaitingQueue queue = departure.getWaitingQueueForSchedule(scheduleDetail);
		if (queue != null) {
			queue.enqueue(waitingTicket);
			System.out.println("Tau da day. Da them " + name + " vao hang doi " + queue.getId());
		}

		return waitingTicket;
	}

	/**
	 * @param ticket
	 * @return
	 */
	// HUY VE
	// true neu huy thanh cong
	public boolean cancelTicket(Ticket ticket) {
		// TODO implement here
		if (ticket == null) {
			System.out.println("Ve khong hop le ");
			return false;
		}
		// Kiem tra ve co thuoc ve hanh khach nay khong
		if (!activeTickets.contains(ticket) && !waitingTickets.contains(ticket)) {
			System.out.println("Khong tim thay thong tin ve ");
			return false;
		}
		// Kiem tra trang thai ve
		String status = ticket.getStatus();
		if (status.trim().equalsIgnoreCase("USED") || status.trim().equalsIgnoreCase("EXPIRED")
				|| status.trim().equalsIgnoreCase("CANCELLED")) {
			System.out.println("Khong the huy ve da " + status.toLowerCase());
			return false;
		}
		// Thuc hien huy ve
		boolean cancelled = ticket.cancel();

		if (cancelled) {
			// Xoa khoi danh sach active hoac waiting
			activeTickets.remove(ticket);
			waitingTickets.remove(ticket);

			// Hoan tien neu da thanh toan
			if (ticket.getIsPaid()) {
				Order order = ticket.getOrder();
				if (order != null && order.getPayment() != null) {
					Payment payment = order.getPayment();

					// Hoan 100% tien ve
					double refundAmount = ticket.calculatePrice() * 1;
					payment.partialRefund(refundAmount);
					System.out.println("Hoan tien: " + refundAmount);
				}
			}
			// Lam trong cho ngoi
			ScheduleDetail schedule = ticket.getScheduleDetail();
			schedule.releaseSeat();

			// Xu ly hang cho ( neu co )
			schedule.processWaitingQueue();
			System.out.println("Huy ve thanh cong");
			return true;
		}

		return false;
	}

	/**
	 * @return
	 */
	// LAY DANH SACH VE HET HAN
	public ArrayList<Ticket> getExpiredTickets() {
		// TODO implement here
		ArrayList<Ticket> expiredTickets = new ArrayList<>();
		Calendar now = Calendar.getInstance();

		for (Ticket ticket : ticketHistory) {
			// Kiem tra ve da qua han su dung
			if (ticket.getValidUntil() != null && ticket.getValidUntil().before(now)
					&& ticket.getStatus().equalsIgnoreCase("ACTIVE")) {
				expiredTickets.add(ticket);
			}
		}
		return expiredTickets;
	}

	/**
	 * @param name
	 * @param email
	 * @param phone
	 * @return
	 */
	// CAP NHAT THONG TIN CA NHAN
	public void updatePersonalInfo(String name, String email, String phone) {
		// TODO implement here
		boolean update = false;

		if (name != null && !name.trim().isEmpty()) {
			this.name = name;
			update = true;
		}
		if (email != null && !email.trim().isEmpty()) {
			this.email = email;
			update = true;
		}
		if (phone != null && !phone.trim().isEmpty()) {
			this.phone = phone;
			update = true;
		}
		if (update) {
			System.out.println("Cap nhat thong tin thanh cong ");
		} else {
			System.out.println("Khong co thong tin nao duoc cap nhat");
		}
	}

	/**
	 * @param ticket
	 * @return
	 */
	// THEM VE VAO LICH SU
	public void addTicketToHistory(Ticket ticket) {
		// TODO implement here
		if (ticket != null && !ticketHistory.contains(ticket)) {
			ticketHistory.add(ticket);
		}
	}

	/**
	 * @param ticket
	 * @return
	 */
	// THEM VE VAO DANH SACH CHO
	public void addWaitingTicket(Ticket ticket) {
		// TODO implement here
		if (ticket != null) {
			waitingTickets.add(ticket);
			ticket.setQueuePosition(waitingTickets.size());
			ticket.setQueueTime(Calendar.getInstance());
		}
	}

	/**
	 * @param ticket
	 * @return
	 */
	//XOA VE CHO
	public void removeWaitingTicket(Ticket ticket) {
		// TODO implement here
		if (ticket != null) {
			waitingTickets.remove(ticket);
			ticket.setQueuePosition(0);
			ticket.setQueueTime(null);
		}
	}

	/**
	 * @return
	 */
	// LAY THONG TIN LIEN HE
	public String getContactInfo() {
		// TODO implement here
		StringBuilder info = new StringBuilder();
		info.append("========== THONG TIN LIEN HE ========== \n");
		info.append("Ma Khach hang: ").append(id).append("\n");
		info.append("Ho ten: ").append(name).append("\n");
		info.append("Email: ").append(email).append("\n");
		info.append("Dien thoai: ").append(phone).append("\n");
		info.append("Uu tien: ").append(getPriorityText()).append("\n");
		return info.toString();

	}

	/**
	 * @return
	 */
	// LAY DON HANG MOI NHAT
	public Order getLatestOrder() {
		// TODO implement here
		if (orderHistory.isEmpty()) {
			return null;
		}
		return orderHistory.get(orderHistory.size() - 1);
	}

	/**
	 * @param ticket
	 * @return
	 */
	// TIM HIEU DON HANG CHUA VE CU THE
	public Order getOrderByTicket(Ticket ticket) {
		// TODO implement here
		if (ticket == null) {
			return null;
		}
		for (Order order : orderHistory) {
			if (order.getTicketList().contains(ticket)) {
				return order;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	// KIEM TRA VA HIEN THI TRANG THAI DANH SACH CHO
	public void checkWaitingStatus() {
		// TODO implement here
		if (waitingTickets.isEmpty()) {
			System.out.println("Ban khong co ve nao trong danh sach cho");
			return;
		}
		System.out.println("\n===== DANH SACH VE CHO =======\n");
		System.out.println("Tong so ve cho: " + waitingTickets.size() + "\n");

		int count = 1;
		for (Ticket ticket : waitingTickets) {
			System.out.println("Ve " + count);
			System.out.println("Ma ve: " + ticket.getId());
			System.out.println("Tuyen: " + ticket.getDeparture().getName() + " -> " + ticket.getArrival().getName());
			System.out.println("Ngay: " + formatCalendar(ticket.getScheduleDetail().getDate()));
			System.out.println("Gio khoi hanh: " + formatTime(ticket.getScheduleDetail().getTimeStart()));
			System.out.println("Vi tri cho: " + ticket.getQueuePosition());

			long waitingMinutes = ticket.getWaitingDuration();
			System.out.println("Da cho: " + waitingMinutes + " phut");
			System.out.println();

			count++;
		}
	}

	// MO TA DO UU TIEN
	public String getPriorityText() {
		switch (priority) {
		case 1:
			return "Cao tuoi";
		case 2:
			return "Khuyet tat";
		default:
			return "Thuong";
		}
	}

	// FORMAT CALENDAR THANH CHUOI NGAY
	public String formatCalendar(Calendar cal) {
		if (cal == null)
			return "N/A";
		return String.format("%02d/%02d/%04d %02d:%02d:%02d", cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY),
				cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}

	// Format time tu double (8.75 = 8h45)
	public String formatTime(double time) {
		int hour = (int) time;
		int minute = (int) ((time - hour) * 60);
		return String.format("%02d:%02d", hour, minute);
	}

	@Override
	public String toString() {
		return "Customer{" + "id=" + id + "\n" + "name=" + name + "\n" + "email='" + email + "\n" + "phone='" + phone
				+ "\n" + "priority=" + getPriorityText() + "\n" + "activeTickets=" + activeTickets.size() + "\n"
				+ "waitingTickets=" + waitingTickets.size() + "\n";
	}


	    public static void main(String[] args) {
	        System.out.println("==================== TAO DOI TUONG TEST ====================");

	        // ===== KHOI TAO DU LIEU =====
	        // Ga
	        Station gaBenThanh = new Station("ST01", "Ben Thanh", "Quan 1", 10.77, 106.69, true);
	        Station gaSuoiTien = new Station("ST14", "Suoi Tien", "Thu Duc", 10.89, 106.80, true);

	        // Tuyen & doan
	        Route tuyen01 = new Route("R01", "Ben Thanh - Suoi Tien", new ArrayList<>(), new ArrayList<>(), 19.7, true, "FORWARD");
	        RoutePart doan01 = new RoutePart("RP01", gaBenThanh, gaSuoiTien, 19.7, tuyen01, 45);
	        tuyen01.getRoutePartList().add(doan01);
	        tuyen01.getStationList().add(gaBenThanh);
	        tuyen01.getStationList().add(gaSuoiTien);

	        // Lich trinh
	        Calendar ngayChay = Calendar.getInstance();
	        ngayChay.add(Calendar.HOUR_OF_DAY, 3);
	        ScheduleDetail lichTrinh01 = new ScheduleDetail("SD01", ngayChay, 8.0, 9.0, tuyen01, "FORWARD", null, false, "SCHEDULED", 0, 10, 10, null);

	        // Loai ve
	        TicketType veLuot = new TicketType("TT01", "Ve luot", 0.0, 1, "Ve di mot luot");
	        TicketType veThang = new TicketType("TT02", "Ve thang", 0.2, 30, "Ve di trong thang");

	        // Khach hang
	        Customer khachThuong = new Customer("24130350", "Tran Thi Cam Tu", "a@gmail.com", "0901111111");
	        khachThuong.setPriority(0);
	        Customer khachCaoTuoi = new Customer("24130197", "Le Thi Kim Ngan", "b@gmail.com", "0902222222");
	        khachCaoTuoi.setPriority(1);
	        Customer khachKhuyetTat = new Customer("C03", "Le Van C", "c@gmail.com", "0903333333");
	        khachKhuyetTat.setPriority(2);
	        Customer khachMoi = new Customer("C04", "Pham Van D", "d@gmail.com", "0904444444");

	        // Tao ve
	        Ticket fakeTicket = new Ticket("FAKE", khachKhuyetTat, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);

	        System.out.println("Da tao khach hang test\n");

	        // ===== TEST PLACEORDER =====
	        System.out.println("==================== TEST TAO DON MOI ====================");
	        Order order1 = khachThuong.placeOrder();
	        System.out.println("Don hang moi tao: " + order1.getId());
	        System.out.println();

	        // ===== TEST BUYTICKET =====
	        System.out.println("==================== TEST MUA VE TAU ====================");
	        System.out.println();
	        Ticket t1 = khachThuong.buyTicket(gaBenThanh, gaSuoiTien, veLuot, lichTrinh01);
	        System.out.println();
	        Ticket t2 = khachCaoTuoi.buyTicket(gaBenThanh, gaSuoiTien, veThang, lichTrinh01);
	        System.out.println();
	        System.out.println("Khach thuong mua ve: " + (t1 != null));
	        System.out.println("Khach cao tuoi mua ve: " + (t2 != null));
	        System.out.println();

	        // ===== TEST BUYTICKETWITHWAITING =====
	        System.out.println("==================== TEST MUA VE VA CHO NEU DA HET CHO ====================");
	        Ticket t3 = khachThuong.buyTicketWithWaiting(gaBenThanh, gaSuoiTien, veLuot, lichTrinh01);
	        
	        lichTrinh01.reserveSeats(2); // het cho
	        System.out.println("Khach thuong mua ve cho doi: " + (t3 != null && "WAITING".equals(t3.getStatus())));
	        System.out.println();

	        // ===== TEST CANCELTICKET =====
	        System.out.println("==================== TEST HUY VE ====================");
	        khachThuong.getActiveTickets().add(t1);
	        khachThuong.getTicketHistory().add(t1);
	     // Mua vé bằng buyTicket để nó nằm trong activeTickets
	        Ticket veHuy = khachThuong.buyTicket(gaBenThanh, gaSuoiTien, veLuot, lichTrinh01);
	        if (veHuy != null) {
	            veHuy.setStatus("ACTIVE");
	            boolean huy = khachThuong.cancelTicket(veHuy);
	            System.out.println("Ket qua huy ve: " + huy);
	        } else {
	            System.out.println("Khong the tao ve de test huy vi tau het cho");
	        }


	      
	     // Trường hợp hủy vé ACTIVE
	     System.out.println();
	     boolean huyActive = khachThuong.cancelTicket(veHuy);
	     System.out.println("Huy ve ACTIVE: " + huyActive);

	     // Trường hợp hủy vé đã USED
	     System.out.println();
	     veHuy.setStatus("USED");
	     boolean huyUsed = khachThuong.cancelTicket(veHuy);
	     System.out.println("Huy ve da su dung: " + huyUsed);

	     // Trường hợp hủy vé đã EXPIRED
	     System.out.println();
	     veHuy.setStatus("EXPIRED");
	     boolean huyExpired = khachThuong.cancelTicket(veHuy);
	     System.out.println("Huy ve da het han: " + huyExpired);

	     // Trường hợp hủy vé đã CANCELLED
	     System.out.println();
	     veHuy.setStatus("CANCELLED");
	     boolean huyCancelled = khachThuong.cancelTicket(veHuy);
	     System.out.println("Huy ve da huy truoc do: " + huyCancelled);

	        // ===== TEST GETEXPIREDTICKETS =====
	        System.out.println("==================== TEST LAY DANH SACH VE HET HAN ====================");
	        t2.setValidUntil(Calendar.getInstance()); // han su dung = hien tai
	        ArrayList<Ticket> expired = khachCaoTuoi.getExpiredTickets();
	        System.out.println("So ve het han: " + expired.size());
	        System.out.println();

	        // ===== TEST UPDATEPERSONALINFO =====
	        System.out.println("==================== TEST CAP NHAT THONG TIN CA NHAN ====================");
	        khachThuong.updatePersonalInfo("Tran Thi Cam Tu Updated", "newmail@gmail.com", "0999999999");
	        khachThuong.updatePersonalInfo("Chi doi email", "onlymail@gmail.com", "");
	        khachThuong.updatePersonalInfo("", "", "");
	        System.out.println();

	        // ===== TEST ADDTICKETTOHISTORY =====
	        System.out.println("==================== TEST THEM VE VAO LICH SU ====================");
	        khachThuong.addTicketToHistory(t1);
	        khachThuong.addTicketToHistory(t1); // them trùng
	        System.out.println("So ve trong lich su: " + khachThuong.getTicketHistory().size());
	        System.out.println();

	        // ===== TEST ADDWAITINGTICKET & REMOVEWAITINGTICKET =====
	        System.out.println("==================== TEST THEM VE VAO DANH SACH CHO VA XOA VE CHO ====================");
	        
	        khachThuong.addWaitingTicket(t3);
	        System.out.println("So ve cho: " + khachThuong.getWaitingTickets().size());
	        khachThuong.removeWaitingTicket(t3);
	        System.out.println("So ve cho sau khi xoa: " + khachThuong.getWaitingTickets().size());
	        khachThuong.removeWaitingTicket(null); // xoa null
	        System.out.println();

	        // ===== TEST GETCONTACTINFO =====
	        System.out.println("==================== TEST LAY THONG TIN LIEN HE ====================");
	        System.out.println(khachThuong.getContactInfo());
	        System.out.println();

	        // ===== TEST GETLATESTORDER =====
	        System.out.println("==================== TEST LAY DON HANG MOI NHAT ====================");
	        Order latest = khachThuong.getLatestOrder();
	        System.out.println("Don hang moi nhat: " + (latest != null));
	        System.out.println("Khach moi khong co don hang: " + (khachMoi.getLatestOrder() == null));
	        System.out.println();

	        // ===== TEST GETORDERBYTICKET =====
	        System.out.println("==================== TEST TIM HIEU DON HANG CHUA VE CU THE ====================");
	        Order orderByTicket = khachThuong.getOrderByTicket(t1);
	        System.out.println("Tim thay don hang theo ve: " + (orderByTicket != null));
	        System.out.println("Tim don hang voi ve null: " + (khachThuong.getOrderByTicket(null) == null));
	        System.out.println();

	        // ===== TEST CHECKWAITINGSTATUS =====
	        System.out.println("==================== TEST KIEM TRA VA HIEN THI TRANG THAI DANH SACH CHO ====================");
	        khachThuong.checkWaitingStatus(); // danh sach rong
	        khachThuong.addWaitingTicket(t3);
	        khachThuong.checkWaitingStatus(); // co ve cho
	    }
}
