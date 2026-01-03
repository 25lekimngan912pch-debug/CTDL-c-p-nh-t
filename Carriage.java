package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.Objects;
//Toa tau metro
public class Carriage {
	private String id;
	private String type; // "STANDARD", "FIRST_CLASS", "VIP"
	private int capacity; // Tong so ghe
	private int availableSeat; // So ghe trong
	private String status; // "IN_USE", "MAINTENANCE", "RETIRED"
	private Train train;
	private boolean hasAirCondition;
	private boolean hasWifi;

	public Carriage(String id, String type, int capacity, int availableSeat, String status, Train train,
			boolean hasAirCondition, boolean hasWifi) {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id required");
		if (capacity <= 0)
			throw new IllegalArgumentException("capacity must be > 0");
		if (availableSeat < 0 || availableSeat > capacity)
			throw new IllegalArgumentException("availableSeat must be between 0 and capacity");
		this.id = id;
		this.type = type != null ? type : "STANDARD";
		this.capacity = capacity;
		this.availableSeat = availableSeat;
		this.status = status != null ? status : "IN_USE";
		this.train = train;
		this.hasAirCondition = hasAirCondition;
		this.hasWifi = hasWifi;
	}

	public int getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(int availableSeat) {
		if (availableSeat < 0 || availableSeat > capacity)
			throw new IllegalArgumentException("availableSeat must be between 0 and capacity");
		this.availableSeat = availableSeat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean hasAirCondition() {
		return hasAirCondition;
	}

	public boolean hasWifi() {
		return hasWifi;
	}

	// Tra ve true neu khong con ghe trong
	public boolean isFull() {
		return availableSeat <= 0;
	}

	/**
	 * Dat cho ngoi Neu du ghe trong thi giam availableSeat va tra ve true Neu khong
	 * du khong thay doi va tra ve false
	 */
	public boolean reserveSeat(int count) {
		if (availableSeat >= count) {
			availableSeat -= count;
			System.out.println("Da dat " + count + " cho tren toa " + id);
			return true;
		}
		System.out.println("Toa " + id + " khong du cho! Chi con " + availableSeat + " chỗ.");
		return false;
	}

	// Huy dat cho, tra lai ghe
	public void releaseSeat(int count) {
		availableSeat = Math.min(availableSeat + count, capacity);
		System.out.println("Da huy " + count + " cho tren toa " + id);
	}

	// Tinh ty le lap day
	public double getOccupancyRate() {
		if (capacity == 0) {
			return 0;
		}
		int occupied = capacity - availableSeat;
		return (occupied * 100.0) / capacity;
	}

	@Override
	public String toString() {
		return "Carriage id= " + id + ", type= " + type + ", capacity= " + capacity + ", availableSeat= "
				+ availableSeat + ", status= " + status + ", train= " + train + ", hasAirCondition= " + hasAirCondition
				+ ", hasWifi= " + hasWifi;
	}

	// Test
	public static void main(String[] args) {
		// Khoi tao Carriage
		System.out.println("====================TEST CARRIAGE====================");
		Carriage c1 = new Carriage("C01", "STANDARD", 42, 42, "IN_USE", null, true, true);
		Carriage c2 = new Carriage("C02", "STANDARD", 42, 0, "IN_USE", null, true, true);
		Carriage c3 = new Carriage("C03", "STANDARD", 42, 12, "IN_USE", null, true, true);
		// In thong tin tau
		System.out.println("THONG TIN CAC TOA TAU");
		System.out.println("\t" + c1.toString());
		System.out.println("\t" + c2.toString());
		System.out.println("\t" + c3.toString());
		// Kiem tra toa tau co day hay khong
		System.out.println("KIEM TRA TOA TAU CO DAY CHO HAY KHONG?");
		System.out.println("\t" + (c1.isFull() == true ? "Toa tau C01 day" : "Toa tau C01 khong day"));
		System.out.println("\t" + (c2.isFull() == true ? "Toa tau C02 day" : "Toa tau C02 khong day"));
		System.out.println("\t" + (c3.isFull() == true ? "Toa tau C03 day" : "Toa tau C03 khong day"));
		// Kiem tra ty le lap day cua toa tau
		System.out.println("KIEM TRA TY LE LAP DAY CUA CAC TOA TAU");
		System.out.println("\t" + "Ty le lap day cua C01: " + c1.getOccupancyRate());
		System.out.println("\t" + "Ty le lap day cua C02: " + c2.getOccupancyRate());
		System.out.println("\t" + "Ty le lap day cua C03: " + c3.getOccupancyRate());
		// Dat ghe
		System.out.println("DAT 10 GHE CUA TOA C01 (TOA C01 CON CHO)");
		System.out.println("\t"
				+ (c1.reserveSeat(10) == true ? "Da dat 10 ghe trong toa C01" : "Khong the dat 10 ghe trong toa C01"));
		System.out.println("\t" + "So ghe con trong cua toa C01: " + c1.getAvailableSeat());
		System.out.println("\t" + "Ty le lap day cho cua toa C01: " + c1.getOccupancyRate());
		System.out.println("DAT 10 GHE CUA TOA C02 (TOA C02 KHONG CON CHO)");
		System.out.println("\t"
				+ (c2.reserveSeat(10) == true ? "Da dat 10 ghe trong toa C02" : "Khong the dat 10 ghe trong toa C02"));
		System.out.println("\t" + "So ghe con trong cua toa C02: " + c2.getAvailableSeat());
		System.out.println("\t" + "Ty le lap day cho cua toa C02: " + c2.getOccupancyRate());
		// Huy ghe
		System.out.println("HUY 8 GHE CUA TOA C03");
		c3.releaseSeat(8);
		System.out.println("\t" + "So ghe con trong cua toa C03: " + c3.getAvailableSeat());
		System.out.println("\t" + "Ty le lap day cho cua toa C03: " + c3.getOccupancyRate());
		System.out.println("HUY 50 GHE CUA TOA C01 (VUOT CAPACITY)");
		c1.releaseSeat(50);
		System.out.println("\t" + "So ghe con trong cua toa C03: " + c1.getAvailableSeat());
		System.out.println("\t" + "Ty le lap day cho cua toa C03: " + c1.getOccupancyRate());
	}
}