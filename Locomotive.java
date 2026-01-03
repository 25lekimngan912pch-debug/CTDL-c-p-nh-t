package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.Calendar;

//Dau tau metro
public class Locomotive {
	private String id;
	private String model;
	private String status; // "OPERATIONAL", "MAINTENANCE", "RETIRED"
	private double speed; // km/h
	private double maxSpeed; // Toc do toi da
	private int manufactureYear; // Nam san xuat
	private Calendar lastMaintenanceDate; // Thoi gian bao trilan cuoi
	private Calendar nextMaintenanceDate; // Thoi gian bao tri tiep theo

	public Locomotive(String id, String model, String status, double speed, double maxSpeed, int manufactureYear,
			Calendar lastMaintenanceDate, Calendar nextMaintenanceDate) {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("ID la bat buoc!");
		if (maxSpeed <= 0)
			throw new IllegalArgumentException("Toc do toi da phai > 0 !");
		if (manufactureYear <= 0)
			throw new IllegalArgumentException("Nam san xuat phai > 0 !");
		if (speed < 0 || speed > maxSpeed)
			throw new IllegalArgumentException("Toc do phai tu 0 den toc do toi da !");
		this.id = id;
		this.model = model != null ? model : "UNKNOWN";
		this.status = status != null ? status : "OPERATIONAL";
		this.speed = speed;
		this.maxSpeed = maxSpeed;
		this.manufactureYear = manufactureYear;
		this.lastMaintenanceDate = (lastMaintenanceDate == null) ? null : (Calendar) lastMaintenanceDate.clone();
		this.nextMaintenanceDate = (nextMaintenanceDate == null) ? null : (Calendar) nextMaintenanceDate.clone();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		System.out.println("Dau may " + model + " da doi trang thai thanh " + status);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		// Khong cho phep vuot qua toc do toi da
		if (speed > maxSpeed) {
			System.out.println("CANH BAO: Toc do " + speed + " km/h vuot qua gioi han!");
			this.speed = maxSpeed;
		} else if (speed < 0) {
			this.speed = 0;
		} else {
			this.speed = speed;
		}
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public Calendar getLastMaintenanceDate() {
		return (lastMaintenanceDate == null) ? null : (Calendar) lastMaintenanceDate.clone();
	}

	public void setLastMaintenanceDate(Calendar lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	public Calendar getNextMaintenanceDate() {
		return (nextMaintenanceDate == null) ? null : (Calendar) nextMaintenanceDate.clone();
	}

	public void setNextMaintenanceDate(Calendar nextMaintenanceDate) {
		this.nextMaintenanceDate = nextMaintenanceDate;
	}

	// Len lich bao tri
	public void scheduleMaintenance(Calendar date) {
		this.nextMaintenanceDate = date;
		System.out.println("Da len lich bao tri dau may " + model + " vao " + date.getTime());
	}

	// Kiem tra co can bao tri khong
	public boolean needsMaintenance() {
		if (nextMaintenanceDate == null) {
			return false; // Chua co lich bao tri
		}
		if(this.status=="MAINTENANCE") {
			return true;
		}
		Calendar now = Calendar.getInstance();
		// Neu hien tai >= ngay bao tri thi can bao tri
		return now.compareTo(nextMaintenanceDate) >= 0;
	}
	@Override
	public String toString() {
		return "Locomotive id='" + id + '\'' + ", model='" + model + '\'' + ", status='" + status + '\''
				+ ", speed=" + speed + ", maxSpeed=" + maxSpeed + ", manufactureYear=" + manufactureYear
				+ ", lastMaintenanceDate=" + (lastMaintenanceDate != null ? lastMaintenanceDate.getTime() : "null")
				+ ", nextMaintenanceDate=" + (nextMaintenanceDate != null ? nextMaintenanceDate.getTime() : "null");
	}

	// Test
	public static void main(String[] args) {
		System.out.println("====================TEST LOCOMOTIVE====================");
		// Khoi tao ngay bao tri
		Calendar last = Calendar.getInstance();
		last.set(2025, Calendar.DECEMBER, 1);
		Calendar next = Calendar.getInstance();
		next.set(2026, Calendar.JANUARY, 1);
		// Khoi tao Locomotive
		Locomotive locomotive01 = new Locomotive("L01", "Kinki Sharyo 2020", "OPERATIONAL", 60.0, 120, 2020, last, next);
		// In thong tin
		System.out.println("THONG TIN DAU MAY");
		System.out.println("\t" + locomotive01.toString());
		// Set toc do
		System.out.println("SET TOC DO TRONG GIOI HAN");
		locomotive01.setSpeed(100);
		System.out.println("\t" + "Set toc do L01 thanh 100");
		System.out.println("\t" + "Toc do hien tai cua L01: " + locomotive01.getSpeed());
		System.out.println("SET TOC DO VUOT GIOI HAN");
		locomotive01.setSpeed(200);
		System.out.println("\t" + "Set toc do L01 thanh 200");
		System.out.println("\t" + "Toc do hien tai cua L01: " + locomotive01.getSpeed());
		// Kiem tra can bao tri hay khong theo ngay
		System.out.println("KIEM TRA BAO TRI");
		System.out.println(
				"\t " + (locomotive01.needsMaintenance() == true ? "L01 can bao tri" : "L01 khong can bao tri"));
		// Doi trang thai sang MAINTENANCE
		locomotive01.setStatus("MAINTENANCE");
		System.out.println("DOI STATUS CUA L01 THANH MAINTENANCE");
		System.out.println("\t" + "Status MAINTENANCE nen L01 can bao tri: " + locomotive01.needsMaintenance());
		// Len lich bao tri
		System.out.println("LICH BAO TRI MOI");
		Calendar newDate = Calendar.getInstance();
		newDate.add(Calendar.DAY_OF_MONTH, -1); // ngay da qua
		locomotive01.scheduleMaintenance(newDate);
		System.out.println("\t" + "Ngay bao tri tiep theo: " + locomotive01.getNextMaintenanceDate().getTime());
		System.out.println(
				"\t" + (locomotive01.needsMaintenance() == true ? "L01 can bao tri" : "L01 khong can bao tri"));
	}
	

}