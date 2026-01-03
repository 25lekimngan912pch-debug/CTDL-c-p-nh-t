package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

//May quet ve
public class QrValidator {
	private String id; // Ma dinh danh may quet
	private Station station; // Ga nao co may nay

	public QrValidator(String id, Station station) {
		super();
		this.id = id;
		this.station = station;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	/**
	 * Kiem tra ve co hop le khong
	 * 
	 * @param ticket      ve can kiem tra
	 * @param currentTime thoi gian hien tai
	 * @return true neu ve hop le
	 */
	//
	public boolean validate(Ticket ticket, Calendar currentTime) {
		// Kiem tra ve co ton tai khong
		if (ticket == null) {
			System.out.println("Ve khong ton tai!");
			return false;
		}

		System.out.println("=== BAT DAU XAC THUC VE " + ticket.getId() + " ===");

		// Kiem tra ve da thanh toan chua
		if (!ticket.getIsPaid()) {
			System.out.println("Ve chua duoc thanh toan");
			return false;
		}

		// Kiem tra trang thai ve
		if (!checkTicketValidity(ticket)) {
			System.out.println("Ve khong hop le do trang thai: " + ticket.getStatus());
			return false;
		}

		// Kiem tra ve con hieu luc khong
		if (!ticket.isValid()) {
			System.out.println("Ve da het han!");
			return false;
		}

		// Kiem tra lich trinh
		if (!checkScheduleStatus(ticket)) {
			System.out.println("Lich trinh khong khop!");
			return false;
		}

		// Kiem tra ghe
		if (!checkSeatAvailability(ticket)) {
			System.out.println("Khong con cho ngoi");
			return false;
		}

		System.out.println("Ve hop le!");
		logScan(ticket, true);
		return true;
	}

	/**
	 * Quet ve (scan QR code)
	 * 
	 * @param ticket ve can quet
	 * @return true neu quet thanh cong
	 */
	public boolean scan(Ticket ticket) {
		System.out.println("Dang quet ma QR " + ticket.getId() + " tai ga " + station.getName() + "...");

		Calendar now = Calendar.getInstance();
		boolean isValid = validate(ticket, now);

		if (isValid) {
			// Check-in ve
			ticket.checkIn();
			System.out.println("Check-in thanh cong! Moi ban qua!");
			return true;
		} else {
			System.out.println("Check-in that bai!");
			return false;
		}
	}

	/**
	 * Vo hieu hoa ve (Sau khi su dung thanh cong)
	 * 
	 * @param ticket Ve can co hieu hoa
	 */
	public void invalidate(Ticket ticket) {
		if (ticket != null) {
			ticket.setStatus("USED");
			System.out.println("Ve " + ticket.getId() + " da duoc danh dau la da su ");
		}
	}

	/**
	 * Kiem tra con cho khong
	 * 
	 * @param ticket ve can kiem tra
	 * @return true neu con cho
	 */
	public boolean checkSeatAvailability(Ticket ticket) {
		if (ticket.getScheduleDetail() == null) {
			return false;
		}

		ScheduleDetail schedule = (ScheduleDetail) ticket.getScheduleDetail();
		return schedule.getAvailableSeat() > 0 || ticket.isCheckedIn();
	}

	/**
	 * Kiem tra trang thai lich trinh
	 * 
	 * @param ticket ve can kiem tra
	 * @return true neu lich trinh hop le
	 */
	public boolean checkScheduleStatus(Ticket ticket) {
		if (ticket.getScheduleDetail() == null) {
			return false;
		}

		ScheduleDetail schedule = (ScheduleDetail) ticket.getScheduleDetail();
		if (schedule.isCancelled()) {
			System.out.println("Chuyen tau da bi huy");
			return false;
		}

		String status = schedule.getStatus();
		return "SCHEDULED".equals(status) || "ON_TIME".equals(status) || "DELAYED".equals(status);
	}

	/**
	 * Kiem tra ve co hop le khong
	 * 
	 * @param ticket ve can kiem tra
	 * @return true neu status = ACTIVE hoac da check-in
	 */
	public boolean checkTicketValidity(Ticket ticket) {
		String status = ticket.getStatus();
		return "ACTIVE".equals(status) || ticket.isCheckedIn();
	}

	/**
	 * Ghi log khi quet
	 * 
	 * @param ticket  ve da quet
	 * @param success thanh cong hay khong
	 */
	//GHI LAI QUA TRINH QUET
	public void logScan(Ticket ticket, boolean success) {
		Calendar now = Calendar.getInstance();
		System.out.println("LOG " + now.getTime() + " - Ga: " + station.getName() + " - Ve: " + ticket.getId()
				+ " - Ket qua: " + (success ? "THANH CONG" : "THAT BAI"));
	}

	public static void main(String[] args) {
		System.out.println("==================== TAO DOI TUONG TEST ====================");

		// ===== GA =====
		Station gaBenThanh = new Station("ST01", "Ben Thanh", "Quan 1", 10.77, 106.69, true);
		Station gaSuoiTien = new Station("ST14", "Suoi Tien", "Thu Duc", 10.89, 106.80, true);

		// ===== TUYEN & DOAN =====
		RoutePart doan01 = new RoutePart("RP01", gaBenThanh, gaSuoiTien, 19.7, null, 45);
		List<RoutePart> danhSachDoan = new ArrayList<>();
		danhSachDoan.add(doan01);
		List<Station> danhSachGa = new ArrayList<>(Arrays.asList(gaBenThanh, gaSuoiTien));
		Route tuyen01 = new Route("R01", "Ben Thanh - Suoi Tien", danhSachDoan, danhSachGa, 19.7, true, "FORWARD");
		doan01.setRoute(tuyen01);

		// ===== LICH TRINH =====
		Calendar ngayChay = Calendar.getInstance();
		ngayChay.set(2026, Calendar.JANUARY, 2, 8, 0);
		ScheduleDetail lichTrinh01 = new ScheduleDetail("SD01", ngayChay, 8.0, 9.0, tuyen01, "FORWARD", null, false,
				"SCHEDULED", 0, 10, 10, null);

		ScheduleDetail lichTrinhHuy = new ScheduleDetail("SD02", Calendar.getInstance(), 10.0, 11.0, tuyen01, "FORWARD",
				null, true, "CANCELLED", 0, 10, 100, null);

		ScheduleDetail lichTrinhHetCho = new ScheduleDetail("SD03", Calendar.getInstance(), 12.0, 13.0, tuyen01,
				"FORWARD", null, false, "SCHEDULED", 0, 0, 100, null);

		ScheduleDetail lichTrinhDelay = new ScheduleDetail("SD04", Calendar.getInstance(), 14.0, 15.0, tuyen01,
				"FORWARD", null, false, "DELAYED", 15, 5, 100, null);

		// ===== LOAI VE =====
		TicketType veLuot = new TicketType("TT01", "Ve luot", 0.0, 1, "Ve di mot luot");

		// ===== KHACH HANG =====
		Customer khach01 = new Customer("24130350", "Tran Thi Cam Tu", "a@gmail.com", "0901234567");

		// ===== VE =====
		Calendar hanMoi = Calendar.getInstance();
		hanMoi.add(Calendar.DATE, 1);
		Calendar hanCu = Calendar.getInstance();
		hanCu.add(Calendar.DATE, -1);

		Ticket veHopLe = new Ticket("T01", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veHopLe.setStatus("ACTIVE");
		veHopLe.setPaid(true);
		veHopLe.setValidUntil(hanMoi);

		Ticket veChuaTT = new Ticket("T02", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veChuaTT.setStatus("ACTIVE");
		veChuaTT.setPaid(false);
		veChuaTT.setValidUntil(hanMoi);

		Ticket veKhongHopLe = new Ticket("T03", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veKhongHopLe.setStatus("CANCELLED");
		veKhongHopLe.setPaid(true);
		veKhongHopLe.setValidUntil(hanMoi);

		Ticket veHetHan = new Ticket("T04", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veHetHan.setStatus("ACTIVE");
		veHetHan.setPaid(true);
		veHetHan.setValidUntil(hanCu);

		Ticket veLichTrinhHuy = new Ticket("T05", khach01, lichTrinhHuy, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veLichTrinhHuy.setStatus("ACTIVE");
		veLichTrinhHuy.setPaid(true);
		veLichTrinhHuy.setValidUntil(hanMoi);

		Ticket veHetCho = new Ticket("T06", khach01, lichTrinhHetCho, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veHetCho.setStatus("ACTIVE");
		veHetCho.setPaid(true);
		veHetCho.setValidUntil(hanMoi);

		Ticket veDelay = new Ticket("T07", khach01, lichTrinhDelay, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veDelay.setStatus("ACTIVE");
		veDelay.setPaid(true);
		veDelay.setValidUntil(hanMoi);
		// Tao ve hop le de test log thanh cong
		Ticket veLogThanhCong = new Ticket("T08", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veLogThanhCong.setStatus("ACTIVE");
		veLogThanhCong.setPaid(true);
		veLogThanhCong.setValidUntil(hanMoi);

		// Tao ve khong hop le de test log that bai
		Ticket veLogThatBai = new Ticket("T09", khach01, lichTrinh01, gaBenThanh, gaSuoiTien, doan01, veLuot);
		veLogThatBai.setStatus("CANCELLED");
		veLogThatBai.setPaid(true);
		veLogThatBai.setValidUntil(hanMoi);

		// ===== MAY QUET =====
		QrValidator mayQuet = new QrValidator("QR01", gaBenThanh);

		System.out.println("Da tao cac doi tuong test\n");

		// ===== TEST CAC TRUONG HOP =====
		System.out.println("==================== TEST VE HOP LE (THANH CONG) ====================");
		System.out.println("Ket qua quet: " + mayQuet.scan(veHopLe) + "\n");

		System.out.println("==================== TEST VE NULL ====================");
		System.out.println("Ket qua validate ve null: " + mayQuet.validate(null, Calendar.getInstance()) + "\n");

		System.out.println("==================== TEST VE CHUA THANH TOAN ====================");
		System.out.println("Ket qua quet ve chua thanh toan: " + mayQuet.scan(veChuaTT) + "\n");

		System.out.println("==================== TEST VE TRANG THAI CANCELLED ====================");
		System.out.println("Ket qua quet ve CANCELLED: " + mayQuet.scan(veKhongHopLe) + "\n");

		System.out.println("==================== TEST VE HET HAN ====================");
		System.out.println("Ket qua quet ve het han: " + mayQuet.scan(veHetHan) + "\n");

		System.out.println("==================== TEST LICH TRINH CANCELLED ====================");
		System.out.println("Ket qua quet ve co lich trinh CANCELLED: " + mayQuet.scan(veLichTrinhHuy) + "\n");

		System.out.println("==================== TEST KHONG CON CHO NGOI ====================");
		System.out.println("Ket qua quet ve khong con cho: " + mayQuet.scan(veHetCho) + "\n");

		System.out.println("==================== TEST LICH TRINH DELAYED ====================");
		System.out.println("Ket qua quet ve co lich trinh DELAYED: " + mayQuet.scan(veDelay) + "\n");

		System.out.println("==================== TEST VE MAT HIEU LUC ====================");
		mayQuet.invalidate(veHopLe);
		System.out.println("Trang thai veHopLe sau mat hieu luc : " + veHopLe.getStatus() + "\n");

		System.out.println("==================== TEST KIEM TRA HIEU LUC CUA VE ====================");
		System.out.println("VeHopLe co hop le? " + mayQuet.checkTicketValidity(veHopLe));
		System.out.println("VeKhongHopLe co hop le? " + mayQuet.checkTicketValidity(veKhongHopLe));
		System.out.println();

		System.out.println("==================== TEST KIEM TRA TRANG THAI LICH TRINH ====================");
		System.out.println("LichTrinh01 hop le? " + mayQuet.checkScheduleStatus(veHopLe));
		System.out.println("LichTrinhHuy hop le? " + mayQuet.checkScheduleStatus(veLichTrinhHuy));
		System.out.println("LichTrinhDelay hop le? " + mayQuet.checkScheduleStatus(veDelay));
		System.out.println();

		System.out.println("==================== TEST KIEM TRA TINH TRANG CHO NGOI ====================");
		System.out.println("VeHopLe con cho? " + mayQuet.checkSeatAvailability(veHopLe));
		System.out.println("VeHetCho con cho? " + mayQuet.checkSeatAvailability(veHetCho));
		System.out.println("==================== TEST GHI LAI QUA TRINH QUET ====================");
		// Ghi log cho ve hop le
		mayQuet.logScan(veLogThanhCong, true);
		// Ghi log cho ve khong hop le
		mayQuet.logScan(veLogThatBai, false);
	}

}
