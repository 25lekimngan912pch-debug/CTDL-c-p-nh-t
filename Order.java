package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.io.*;
import java.util.*;

public class Order {
	private String id;
	private Customer customer;
	private ArrayList<Ticket> ticketList;
	private Calendar orderDate;
	private Payment payment;
	private boolean isConfirmed;
	private double totalAmount;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String id, Customer customer, ArrayList<Ticket> ticketList, Calendar orderDate, Payment payment,
			boolean isConfirmed, double totalAmount) {
		super();
		this.id = id;
		this.customer = customer;
		this.ticketList = new ArrayList<>();
		this.orderDate = orderDate;
		this.payment = payment;
		this.isConfirmed = isConfirmed;
		this.totalAmount = totalAmount;
	}

	public Order(String orderId, Customer customer2) {
		// TODO Auto-generated constructor stub
		this.id = orderId;
		this.customer = customer2;
		this.ticketList = new ArrayList<>();
		this.orderDate = Calendar.getInstance();
		this.isConfirmed = false;
		this.totalAmount = 0.0;
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

	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(ArrayList<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	public Calendar getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @param ticket ve can them
	 * @return
	 */
	//THEM VE 
	public void addTicket(Ticket ticket) {
		if (ticket == null) {
            System.out.println("Ve khong hop le");
            return;
        }
        // Kiem tra don hang da xac nhan chua
        if (this.isConfirmed) {
            System.out.println("Khong the them ve vao don hang da xac nhan!");
            return;
        }
        // Kiem tra neu ticketList van null thi khoi tao ngay
        if (this.ticketList == null) {
            this.ticketList = new ArrayList<>();
        }
        // Kiem tra ve da co trong don hang chua
        if (ticketList.contains(ticket)) {
            System.out.println("Ve da co trong don hang");
            return;
        }
        ticketList.add(ticket);
        calculateTotal();
        System.out.println("Da them ve " + ticket.getId() + " thanh cong.");
	}

	/**
	 * @param ticket
	 * @return
	 */
	// XOA VE KHOI DON HANG
	public boolean removeTicket(Ticket ticket) {
		// TODO implement here
		if (ticket == null) {
			System.out.println("Ve khong hop le");
			return false;
		}
		// Kiem tra don hang da duoc xac nhan chua
		if (isConfirmed) {
			System.out.println("Khoi the xoa ve khoi don hang xac nhan");
			return false;
		}
		// Xoa ve
		boolean removed = ticketList.remove(ticket);
		if (removed) {
			// Cap nhat lai trong tien
			calculateTotal();
			System.out.println("Da xoa ve " + ticket.getId());
			return true;
		} else {
			System.out.println("Khong tim thay ve trong don hang ");
			return false;
		}
	}

	/**
	 * @return
	 */
	// TINH TONG TIEN CUA DON HANG
	public double calculateTotal() {
		// TODO implement here
		totalAmount = 0.0;

		// Cong dong gia cua tat ca ve
		for (Ticket ticket : ticketList) {
			totalAmount += ticket.calculatePrice();
		}
		return totalAmount;
	}

	/**
	 * @return
	 */
	// XAC NHAN DON HANG
	public boolean confirmOrder() {
		// TODO implement here
		// Kiem tra don hang co ve khong
		if (ticketList.isEmpty()) {
			System.out.println("Don hang khong co ve nao");
			return false;
		}
		// Kiem tra da thanh toan chua
		if (payment == null || !payment.isSuccessful()) {
			System.out.println("Don hanh chua duoc thanh toan ");
			return false;
		}
		// Xac nhan don hang
		isConfirmed = true;
		System.out.println("Don hang " + id + "da duoc xac nhan");

		// Cap nhat trang thai ve
		for (Ticket ticket : ticketList) {
			ticket.setStatus("ACTIVE");
		}
		return true;
	}

	/**
	 * @return
	 */
	// KIEM TRA DON HANG DA THANH TOAN CHUA
	public boolean isPaid() {
		// TODO implement here
		return payment != null && payment.isSuccessful();
	}

	/**
	 * @return
	 */
	// SAP XEP VE THEO BAN GIA (TU THAP DEN CAO)
	public void sortTicketsByPrice() {
		// TODO implement here
		if (ticketList.isEmpty()) {
			System.out.println("Don hang khong co ve de sap xep");
			return;
		}
		// Sap xep bang thuat toan Bubble Sort
		int n = ticketList.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				Ticket ticket1 = ticketList.get(j);
				Ticket ticket2 = ticketList.get(j + 1);
				// So sanh gia
				if (ticket1.calculatePrice() > ticket2.calculatePrice()) {
					// Hoan doi
					ticketList.set(j, ticket2);
					ticketList.set(j + 1, ticket1);
				}
			}
		}
		System.out.println("Da sap xep ve theo gia");
	}

	// FORMAT CALENDAR THANH CHUOI NGAY
	public String formatCalendar(Calendar cal) {
		if (cal == null)
			return "N/A";
		return String.format("%02d/%02d/%04d %02d:%02d:%02d", cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY),
				cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}

	// HIEN THI THONG TIN DON HANG
	public void displayOrderInfo() {
		System.out.println("========== THONG TIN DON HANG ==========");
		System.out.println("Ma don hang: " + id);
		System.out.println("Khach hang: " + customer.getName());
		System.out.println("Ngay dat: " + formatCalendar(orderDate));
		System.out.println("So luong ve: " + ticketList.size());
		System.out.println("Tong tien: " + String.format("%.0f", totalAmount) + " VND");
		System.out.println("Trang thai: " + (isConfirmed ? "Da xac nhan" : "Chua xac nhan"));
		System.out.println("Thanh toan: " + (isPaid() ? "Da thanh toan" : "Chua thanh toan"));

		if (!ticketList.isEmpty()) {
			System.out.println("\n--- Chi tiet ve ---");
			int count = 1;
			for (Ticket ticket : ticketList) {
				// Kiểm tra an toàn trước khi lấy tên Ga
				String depName = (ticket.getDeparture() != null) ? ticket.getDeparture().getName() : "Chua xac dinh";
				String arrName = (ticket.getArrival() != null) ? ticket.getArrival().getName() : "Chua xac dinh";

				System.out.println(count + ". " + ticket.getId() + " | " + depName + " -> " + arrName + " | "
						+ String.format("%.0f", ticket.calculatePrice()) + " VND");
				count++;
			}
		}
	}
	
	
	    public static void main(String[] args) {
	        System.out.println("==================== TAO DOI TUONG TEST ====================");
	        Customer khach01 = new Customer("C001", "Nguyen Van A", "a@gmail.com", "0901234567");
	        Order donHang01 = new Order("O001", khach01);

	        // Tao ga
	        Station gaBenThanh = new Station("ST01", "Ben Thanh", "Quan 1", 10.77, 106.69, true);
	        Station gaSuoiTien = new Station("ST14", "Suoi Tien", "Thu Duc", 10.89, 106.80, true);

	        // Tao tuyen va doan tuyen
	        Route tuyen01 = new Route("R01", "Ben Thanh - Suoi Tien", null, null, 0, true, "FORWARD");
	        RoutePart doan01 = new RoutePart("RP01", gaBenThanh, gaSuoiTien, 19.7, tuyen01, 45);

	        // Tao loai ve
	        TicketType veLuot = new TicketType("TT01", "Ve luot", 0.0, 1, "Ve di mot luot");
	        TicketType veThang = new TicketType("TT02", "Ve thang", 0.2, 30, "Ve di trong thang");

	        // Tao lich trinh
	        Calendar ngayChay = Calendar.getInstance();
	        ngayChay.set(2026, Calendar.JANUARY, 2, 8, 0);
	        ScheduleDetail lichTrinh01 = new ScheduleDetail("SD01", ngayChay, 8.0, 9.0, tuyen01, "FORWARD",
	                null, false, "SCHEDULED", 0, 100, 100, null);

	        System.out.println("Da tao cac doi tuong test\n");

	        System.out.println("==================== TEST THEM VE THANH CONG ====================");
	        Ticket ve01 = new Ticket("T01", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
	        donHang01.addTicket(ve01);
	        System.out.println("So luong ve trong don hang: " + donHang01.getTicketList().size());
	        System.out.println("Tong tien: " + donHang01.getTotalAmount() + "\n");

	        System.out.println("==================== TEST THEM VE NULL (THAT BAI) ====================");
	        donHang01.addTicket(null);
	        System.out.println();

	        System.out.println("==================== TEST THEM VE TRUNG (THAT BAI) ====================");
	        donHang01.addTicket(ve01);
	        System.out.println();

	        System.out.println("==================== TEST XOA VE THANH CONG ====================");
	        Ticket ve02 = new Ticket("T02", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veThang);
	        donHang01.addTicket(ve02);
	        boolean xoaVe = donHang01.removeTicket(ve02);
	        System.out.println("Ket qua xoa ve: " + xoaVe);
	        System.out.println("So luong ve con lai: " + donHang01.getTicketList().size() + "\n");

	        System.out.println("==================== TEST XOA VE NULL (THAT BAI) ====================");
	        boolean xoaVeNull = donHang01.removeTicket(null);
	        System.out.println("Ket qua xoa ve null: " + xoaVeNull + "\n");

	        System.out.println("==================== TEST XOA VE KHONG TON TAI (THAT BAI) ====================");
	        Ticket veFake = new Ticket("T_FAKE", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
	        boolean xoaVeFake = donHang01.removeTicket(veFake);
	        System.out.println("Ket qua xoa ve khong ton tai: " + xoaVeFake + "\n");

	        System.out.println("==================== TEST TINH TONG TIEN ====================");
	        double tongTien = donHang01.calculateTotal();
	        System.out.println("Tong tien don hang: " + tongTien + "\n");

	        System.out.println("==================== TEST SAP XEP VE THEO GIA ====================");
	        Ticket ve03 = new Ticket("T03", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veThang);
	        donHang01.addTicket(ve03);
	        donHang01.sortTicketsByPrice();
	        for (Ticket t : donHang01.getTicketList()) {
	            System.out.println("Ve: " + t.getId() + " | Gia: " + t.calculatePrice());
	        }
	        System.out.println();

	        System.out.println("==================== TEST XAC NHAN DON HANG THAT BAI (CHUA THANH TOAN) ====================");
	        boolean xacNhanFail = donHang01.confirmOrder();
	        System.out.println("Ket qua xac nhan don hang: " + xacNhanFail + "\n");

	        System.out.println("==================== TEST XAC NHAN DON HANG THANH CONG ====================");
	        Payment payment = new Payment();
	        payment.setId("P001");
	        payment.setAmount(donHang01.getTotalAmount());
	        payment.setPaymentType("Cash"); 
	        payment.setRefundStatus( "NONE");
	        payment.process();
	        donHang01.setPayment(payment);

	        boolean xacNhan = donHang01.confirmOrder();
	        System.out.println("Ket qua xac nhan don hang: " + xacNhan);
	        System.out.println("Trang thai don hang: " + (donHang01.isConfirmed() ? "Da xac nhan" : "Chua xac nhan") + "\n");

	        System.out.println("==================== TEST THEM VE SAU KHI DON HANG DA XAC NHAN (THAT BAI) ====================");
	        Ticket ve04 = new Ticket("T04", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
	        donHang01.addTicket(ve04);
	        System.out.println();

	        System.out.println("==================== TEST XOA VE SAU KHI DON HANG DA XAC NHAN (THAT BAI) ====================");
	        boolean xoaSauXacNhan = donHang01.removeTicket(ve01);
	        System.out.println("Ket qua xoa ve sau khi don hang da xac nhan: " + xoaSauXacNhan + "\n");

	        System.out.println("==================== TEST KIEM TRA THANH TOAN ====================");
	        System.out.println("Don hang da thanh toan: " + donHang01.isPaid() + "\n");

	        System.out.println("==================== TEST HIEN THI THONG TIN DON HANG ====================");
	        donHang01.displayOrderInfo();
	    }
	
}