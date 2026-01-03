package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.LinkedList;

//Tau metro
public class Train {
	private String id; // Id cua tau
	private String name; // Ten tau
	private Locomotive locomotive; // Dau may
	private LinkedList<Carriage> carriageList; // Danh sach cac toa tau
	private String status; // Trang thai hien tai cua tau: "AVAILABLE", "IN_SERVICE",
							// "MAINTENANCE","OUT_OF_SERVICE"
	private Route currentRoute; // Tuyen hien tai tau dang chay
	private ScheduleDetail currentSchedule; // Lich trinh cu the cua tau
	private int totalCapacity; // Tong suc chua
	private Station currentStation; // Ga hien tai tau dang o
	private int manufactureYear; // Nam san xuat tau
	// Bo sung them
	private int quantity; // So luong

	public Train(String id, String name, Locomotive locomotive, LinkedList<Carriage> carriageList, String status,
			Route currentRoute, ScheduleDetail currentSchedule, int totalCapacity, Station currentStation,
			int manufactureYear) {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id required");
		if (manufactureYear <= 0)
			throw new IllegalArgumentException("manufactureYear must be > 0");
		this.id = id;
		this.name = name != null ? name : "UNKNOWN";
		this.locomotive = locomotive;
		this.carriageList = carriageList != null ? carriageList : new LinkedList<>();
		this.status = status != null ? status : "AVAILABLE";
		this.currentRoute = currentRoute;
		this.currentSchedule = currentSchedule;
		this.currentStation = currentStation;
		this.manufactureYear = manufactureYear;
		this.updateTotalCapacity(); // cap nhat ngay khi khoi tao
	}

	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCarriageList(LinkedList<Carriage> carriageList) {
		this.carriageList = carriageList;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Locomotive getLocomotive() {
		return locomotive;
	}

	public void setLocomotive(Locomotive locomotive) {
		this.locomotive = locomotive;
		System.out.println("Da gan dau may " + locomotive.getModel() + " cho tau " + name);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		System.out.println("Tau " + name + " da doi trang thai thanh: " + status);
	}

	public ScheduleDetail getCurrentSchedule() {
		return currentSchedule;
	}

	public void setCurrentSchedule(ScheduleDetail currentSchedule) {
		this.currentSchedule = currentSchedule;
	}

	public Station getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}

	public int getManufactureYear() {
		return manufactureYear;
	}

	public Route getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

	public LinkedList<Carriage> getCarriageList() {
		return carriageList;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	// Cap nhat tong suc chua cua tau
	public void updateTotalCapacity() {
        totalCapacity = 0;
        for (Carriage carriage : carriageList) {
            totalCapacity += carriage.getCapacity();
        }
        System.out.println("Tau " + name + " co tong suc chua: " + totalCapacity + " cho");
    }

	// Them toa tau
	public void addCarriage(Carriage c) {
        if (c != null) {
            carriageList.add(c);
            c.setTrain(this); 
            updateTotalCapacity(); // Cap nhat lai suc chua
            System.out.println("Da them toa " + c.getId() + " vao tau " + name);
        }
    }

	// Xoa toa tau
	public void removeCarriage(Carriage c) {
        if (carriageList.remove(c)) {
            updateTotalCapacity();
            System.out.println("Da xoa toa " + c.getId() + " khoi tau " + name);
        }
    }

	// Tinh tong so ghe trong trong tat ca carriage
	 public int getAvailableSeats() {
	        int available = 0;
	        for (Carriage carriage : carriageList) {
	            available += carriage.getAvailableSeat();
	        }
	        return available;
	    }

	// Kiem tra trang thai cua tau
	public boolean isAvailable() {
		return "AVAILABLE".equals(status);
	}

	public boolean isInMaintenance() {
		return "MAINTENANCE".equals(status);
	}

	/**
	 * Ty le lap day = reseved / totalCapicity reserved = totalCapicity -
	 * getAvailableSeat return 0.0 neu totalCapicity == 0
	 */
	public double getOccupancyRate() {
		int reserved = totalCapacity - getAvailableSeats();
		double result = 0.0;
		if (totalCapacity <= 0) {
			return result = 0.0;
		}
		return result = (double) reserved / (double) totalCapacity;
	}

	// So luong Carriage hien co
	public int getCarriageCount() {
		return carriageList.size();

	}

	public int getQuantity() {
		return quantity;

	}

	public void setQuantity(int quantity) {
		if (quantity < 0)
			throw new IllegalArgumentException("quantity must be >= 0");
		this.quantity = quantity;

	}
	
	@Override
	public String toString() {
		return "Train id= " + id + ", name= " + name + ", locomotive= " + locomotive + ", carriageList= " + carriageList.toString()
				+ ", status= " + status + ", currentRoute= " + currentRoute + ", currentSchedule= " + currentSchedule
				+ ", totalCapacity= " + totalCapacity + ", currentStation= " + currentStation + ", manufactureYear= "
				+ manufactureYear + ", quantity= " + quantity + "]";
	}

	//TEST
	public static void main(String[] args) {
		System.out.println("==================== TEST TRAIN ====================");
		//Khoi tao Train
		
	}

}
