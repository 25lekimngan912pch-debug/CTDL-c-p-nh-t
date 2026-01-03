package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.Calendar;

public class Payment {
	private String id;
	private Order order;
	private double amount;
	private Calendar date;
	private String paymentType;// "CreditCard", "DebitCard", "E-Wallet", "Cash"
	private String provider;// "Visa", "Mastercard", "Momo", "ZaloPay"
	private String detail;// chi tiet giao dich
	private boolean success;
	private String transactionId;
	private String refundStatus;// "NONE", "REQUESTED", "PROCESSING", "COMPLETED", "FAILED"
	private double refundAmount;
	private Calendar refundDate;

	public Payment(String id, Order order, double amount, Calendar date, String paymentType, String provider,
			String detail, boolean success, String transactionId, String refundStatus, double refundAmount,
			Calendar refundDate) {
		super();
		this.id = "PAY" + System.currentTimeMillis();
		this.order = order;
		this.amount = amount;
		this.date = Calendar.getInstance();
		this.paymentType = paymentType;
		this.provider = provider;
		this.detail = detail;
		this.success = success;
		this.transactionId = transactionId;
		this.refundStatus = refundStatus;
		this.refundAmount = refundAmount;
		this.refundDate = refundDate;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Order order2, double price) {
		// TODO Auto-generated constructor stub
		this.id = "PAY" + System.currentTimeMillis();
		this.order = order;
		this.amount = amount;
		this.date = Calendar.getInstance();
		this.paymentType = paymentType;
		this.provider = provider;
		this.detail = detail;
		this.success = success;
		this.transactionId = transactionId;
		this.refundStatus = refundStatus;
		this.refundAmount = refundAmount;
		this.refundDate = refundDate;
	}

	public Payment(String string, Order order2, double amount, String paymentType) {

		// TODO Auto-generated constructor stub
		this.id = "PAY" + System.currentTimeMillis();
		this.order = order;
		this.amount = amount;
		this.date = Calendar.getInstance();
		this.paymentType = paymentType;
		this.provider = provider;
		this.detail = detail;
		this.success = false;
		this.transactionId = transactionId;
		this.refundStatus = refundStatus;
		this.refundAmount = refundAmount;
		this.refundDate = refundDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Calendar getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Calendar refundDate) {
		this.refundDate = refundDate;
	}

///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return
	 */
	// XU LI THANH TOAN
	public boolean process() {
		// TODO implement here
		// Kiem tra so tien
		if (this.amount <= 0) {
			System.out.println("So tien khong hop le: " + this.amount);
			this.success = false;
			return false;
		}

		// Gia lap xu ly thanh cong
		this.success = true;
		this.transactionId = generateTransactionId();
		// Cap nhat thoi gian thanh toan la luc process
		this.date = Calendar.getInstance();

		System.out.println("Dang xu ly giao dich " + this.id + "...");
		return true;
	}

	/**
	 * @return
	 */

	public boolean refund() {
		// TODO implement here
		if (canRefund()) {
			this.refundAmount = this.amount;
			this.refundStatus = "COMPLETED";
			System.out.println("Da hoan tra toan bo: " + String.format("%,.0f", refundAmount) + " VND");
			return true;
		}
		return false;
	}

	/**
	 * @param amount
	 * @return
	 */
	public boolean partialRefund(double amount) {
		// TODO implement here
		// Kiem tra co the hoan tien hay ko
		if (!canRefund()) {
			System.out.println("Khong the hoan tien!");
			return false;
		}

		// Kiem tra so tien hoan hop le
		if (amount <= 0 || amount > this.amount) {
			System.out.println("So tien hoan khong hop le!");
			System.out.println("So tien thanh toan: " + this.amount);
			System.out.println("So tien yeu cau hoan: " + amount);
			return false;
		}

		// Kiem tra da hoan tien chua
		if (refundStatus.trim().equalsIgnoreCase("COMPLETED")) {
			System.out.println("Da hoan tien truoc do!");
			return false;
		}

		// Xu ly hoan tien
		this.refundStatus = "PROCESSING";
		System.out.println("Dang xu ly hoan tien " + String.format("%.0f", amount) + " VND...");

		// Gia lap xu ly
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Hoan tien thanh cong
		this.refundStatus = "COMPLETED";
		this.refundAmount = amount;
		this.refundDate = Calendar.getInstance();
		this.detail += " | Hoan tien mot phan: " + String.format("%.0f", amount) + " VND";
		System.out.println("Hoan tien thanh cong!");
		System.out.println("So tien hoan: " + String.format("%.0f", refundAmount) + " VND");
		System.out.println("So tien con lai: " + String.format("%.0f", this.amount - refundAmount) + " VND");
		return true;
	}

	/**
	 * @return
	 */
	// KIEM TRA THANH TOAN CO THANH CONG HAY KHONG
	public boolean isSuccessful() {
		// TODO implement here
		return success;
	}

	/**
	 * @return
	 */
	public boolean canRefund() {
		// TODO implement here
		return this.success && this.refundStatus.trim().equalsIgnoreCase("NONE");
	}

	public boolean validate() {
		// Kiem tra paymentType
		if (paymentType == null || paymentType.isEmpty()) {
			System.out.println("Chua chon phuong thuc thanh toan!");
			return false;
		}

		// Kiem tra paymentType hop le
		if (!isValidPaymentType(paymentType)) {
			System.out.println("Phuong thuc thanh toan khong hop le: " + paymentType);
			return false;
		}

		return success;
	}

	// Tao ma giao dich
	public String generateTransactionId() {
		return "TXN" + System.currentTimeMillis() + (int) (Math.random() * 10000);
	}

	// Kiem tra paymentType hop le
	public boolean isValidPaymentType(String type) {
		return type.trim().equalsIgnoreCase("CreditCard") || type.trim().equalsIgnoreCase("DebitCard")
				|| type.trim().equalsIgnoreCase("E-Wallet") || type.trim().equalsIgnoreCase("Cash");
	}

	// FORMAT CALENDAR THANH CHUOI NGAY
	public String formatCalendar(Calendar cal) {
		if (cal == null)
			return "N/A";
		return String.format("%02d/%02d/%04d %02d:%02d:%02d", cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY),
				cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println("==================== TEST THANH TOAN THANH CONG ====================");
		Payment p1 = new Payment();
		p1.id = "P1";
		p1.amount = 50000;
		p1.paymentType = "CreditCard";
		p1.refundStatus = "NONE";
		p1.process();
		System.out.println("Ket qua thanh cong: " + p1.isSuccessful());
		System.out.println("TransactionId: " + p1.transactionId);
		System.out.println("Thoi gian: " + p1.formatCalendar(p1.date));
		System.out.println();

		System.out.println("==================== TEST SO TIEN KHONG HOP LE ====================");
		Payment p2 = new Payment();
		p2.id = "P2";
		p2.amount = -1000;
		p2.paymentType = "Cash";
		p2.refundStatus = "NONE";
		p2.process();
		System.out.println("Ket qua thanh cong: " + p2.isSuccessful());
		System.out.println();

		System.out.println("==================== TEST REFUND TOAN BO ====================");
		Payment p3 = new Payment();
		p3.id = "P3";
		p3.amount = 100000;
		p3.paymentType = "DebitCard";
		p3.refundStatus = "NONE";
		p3.process();
		boolean refundAll = p3.refund();
		System.out.println("Refund all: " + refundAll);
		System.out.println("RefundStatus: " + p3.refundStatus);
		System.out.println("RefundAmount: " + p3.refundAmount);
		System.out.println();

		System.out.println("==================== TEST REFUND MOT PHAN ====================");
		Payment p4 = new Payment();
		p4.id = "P4";
		p4.amount = 200000;
		p4.paymentType = "E-Wallet";
		p4.refundStatus = "NONE";
		p4.process();
		boolean refundPartial = p4.partialRefund(50000);
		System.out.println("Refund partial: " + refundPartial);
		System.out.println("RefundStatus: " + p4.refundStatus);
		System.out.println("RefundAmount: " + p4.refundAmount);
		System.out.println("So tien con lai: " + (p4.amount - p4.refundAmount));
		System.out.println();

		System.out.println("==================== TEST REFUND KHONG HOP LE ====================");
		Payment p5 = new Payment();
		p5.id = "P5";
		p5.amount = 30000;
		p5.paymentType = "Cash";
		p5.refundStatus = "NONE";
		p5.process();
		boolean refundInvalid = p5.partialRefund(50000);
		System.out.println("Refund partial: " + refundInvalid);
		System.out.println();

		System.out.println("==================== TEST VALIDATE HOP LE ====================");
		Payment p6 = new Payment();
		p6.id = "P6";
		p6.amount = 40000;
		p6.paymentType = "CreditCard";
		p6.refundStatus = "NONE";
		p6.process();
		System.out.println("Validate: " + p6.validate());
		System.out.println();

		System.out.println("==================== TEST VALIDATE KHONG HOP LE ====================");
		Payment p7 = new Payment();
		p7.id = "P7";
		p7.amount = 40000;
		p7.paymentType = "Bitcoin";
		p7.refundStatus = "NONE";
		p7.process();
		System.out.println("Validate: " + p7.validate());
		System.out.println();
	}

}
