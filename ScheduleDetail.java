package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.Calendar;
import java.util.Optional;

//Chi tiet lich trinh mot chuyen tau
public class ScheduleDetail {
	private String id; // Ma dinh danh chuyen tay
	private Calendar date; // Ngay chay
	private double timeStart; // Gio khoi hanh (VD: 8.00 = 8h00, 8.75 = 8h45)
	private double timeEnd; // Gio ket thuc
	private Route route; // Tuyen duong
	private String direction; // Chieu: "FORWARD" hoac "BACKWARD"
	private Train train; // Tau nao chay chuyen nay
	private boolean isCancelled; // Co bi huy khong
	private String status; // Trang thai: "SCHEDULED", "ON_TIME", "DELAYED", "CANCELLED", "COMPLETED"
	private int delayMinutes; // So phut tre
	private int availableSeat; // So ghe con trong
	private int totalSeat; // Tong so ghe
	private WaitingQueue waitingQueue; // Hang doi neu het cho

	public ScheduleDetail(String id, Calendar date, double timeStart, double timeEnd, Route route, String direction,
			Train train, boolean isCancelled, String status, int delayMinutes, int availableSeat, int totalSeat,
			WaitingQueue waitingQueue) {
		this.id = Optional.ofNullable(id).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("ID khong duoc rong"));
		this.date = Optional.ofNullable(date).orElseThrow(() -> new IllegalArgumentException("Ngay la bat buoc!"));
		if (timeStart < 0 || timeStart >= 24) {
			throw new IllegalArgumentException("Gio khoi hanh phai tu 0-24");
		}
		if (timeEnd < 0 || timeEnd >= 24) {
			throw new IllegalArgumentException("Gio ket thuc phai tu 0-24");
		}
		if (timeEnd <= timeStart) {
			throw new IllegalArgumentException("Gio ket thuc phai sau gio khoi hanh");
		}
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.route = Optional.ofNullable(route).orElseThrow(() -> new IllegalArgumentException("Route la bat buoc!"));
		this.direction = Optional.ofNullable(direction).filter(d -> d.equals("FORWARD") || d.equals("BACKWARD"))
				.orElseThrow(() -> new IllegalArgumentException("Direction phai la FORWARD hoac BACKWARD"));
		this.train = train;
		this.isCancelled = isCancelled;
		this.status = status;
		this.delayMinutes = delayMinutes;
		this.availableSeat = availableSeat;
		this.totalSeat = totalSeat;
		this.waitingQueue = waitingQueue;
	}

	
	public ScheduleDetail(String id, double timeStart, String direction) {
		this.id = Optional.ofNullable(id).filter(s -> !s.trim().isEmpty())
				.orElseThrow(() -> new IllegalArgumentException("ID khong duoc rong"));
		this.date = date;
		if (timeStart < 0 || timeStart >= 24) {
			throw new IllegalArgumentException("Gio khoi hanh phai tu 0-24");
		}
		if (timeEnd < 0 || timeEnd >= 24) {
			throw new IllegalArgumentException("Gio ket thuc phai tu 0-24");
		}
		
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.route = route;
		this.direction = Optional.ofNullable(direction).filter(d -> d.equals("FORWARD") || d.equals("BACKWARD"))
				.orElseThrow(() -> new IllegalArgumentException("Direction phai la FORWARD hoac BACKWARD"));
		this.train = train;
		this.isCancelled = isCancelled;
		this.status = status;
		this.delayMinutes = delayMinutes;
		this.availableSeat = availableSeat;
		this.totalSeat = totalSeat;
		this.waitingQueue = waitingQueue;
	}


	public ScheduleDetail(String string, Calendar ngayHienTai, double d, String string2) {
		// TODO Auto-generated constructor stub
	}


	public ScheduleDetail() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public double getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(double timeStart) {
		this.timeStart = timeStart;
	}

	public double getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(double timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
		// Cap nhat lai tong so ghe khi doi tau
		if (train != null) {
			this.totalSeat = train.getTotalCapacity();
			this.availableSeat = this.totalSeat;
		}
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDelayMinutes() {
		return delayMinutes;
	}

	public void setDelayMinutes(int delayMinutes) {
		this.delayMinutes = delayMinutes;
	}

	public int getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public WaitingQueue getWaitingQueue() {
		return waitingQueue;
	}

	public void setWaitingQueue(WaitingQueue waitingQueue) {
		this.waitingQueue = waitingQueue;
	}

	/**
     * Huy chuyen tau
     */
	public void cancel() {
		this.isCancelled = true;
		this.status = "CANCELLED";
		System.out.println("Chuyen " + id + " da bi huy!");
		// Thong bao cho hang cho (neu co)
		Optional.ofNullable(waitingQueue).filter(q -> !q.isEmpty()).ifPresent(q -> {
			System.out.println("Thong bao cho " + q.size() + " ngoi dang cho");
			// Clear waiting queue
			q.clear();
		});
	}

	 /**
     * Danh dau chuyen bi tre
     * 
     * @param minutes So phut tre
     */
	public void markDelayed(int minutes) {
		this.delayMinutes = minutes;
		this.status = "DELAYED";
		System.out.println("Chuyen " + id + " bi tre " + minutes + "phut");
	}

	/**
     * Đánh dấu chuyến hoàn thành
     */
	public void complete() {
		this.status = "COMPLETED";
		System.out.println("Chuyen " + id + " da hoan thanh");
	}

	 /**
     * Dat 1 cho ngoi
     * 
     * @return true neu dat thanh cong
     */
	public boolean reserveSeat() {
		if (availableSeat > 0) {
			availableSeat--;
			return true;
		}
		System.out.println("Het cho! Vui long vao hang cho.");
		return false;
	}

	/**
     * Dat nhieu cho
     * 
     * @param count So cho can dat
     * @return true neu du cho
     */
	public boolean reserveSeats(int count) {
		if (availableSeat >= count) {
			availableSeat -= count;
			return true;
		}
		System.out.println("Khong con du cho! Chi con " + availableSeat + " cho trong.");
		return false;

	}

	/**
     * Huy dat 1 cho
     */
	public void releaseSeat() {
		if (availableSeat < totalSeat) {
			availableSeat++;
		}
		System.out.println("Da huy cho.");
	}

	/**
     * Huy nhieu cho
     * 
     * @param count So cho can huy
     */
	public void releaseSeats(int count) {
		availableSeat = Math.min(availableSeat + count, totalSeat);
		System.out.println("Da huy cho");
	}

     /**
      * Tinh thoi gian chuyen
      * 
      * @return So gio di chuyen
      */
	public double getDurationHours() {
		return timeEnd - timeStart;
	}

	/**
     * Tinh ty le lap day
     * 
     * @return Phan tram ghe da duoc dat
     */
	public double getOccupancyRate() {
		if (totalSeat == 0)
			return 0;
		return ((totalSeat - availableSeat) * 100.0) / totalSeat;
	}

	/**
     * Kiem tra da day chua
     * 
     * @return true neu het  cho
     */
	public boolean isFull() {
		return availableSeat <= 0;
	}

	// Xu ly hang doi khi co ghe trong
	public void processWaitingQueue() {
		if (waitingQueue == null || waitingQueue.isEmpty()) {
			return;
		}

		// Lay nguoi tu hang doi ra khi co cho
		while (!waitingQueue.isEmpty() && availableSeat > 0) {
			Ticket ticket = waitingQueue.dequeue();
			if (ticket != null) {
				reserveSeat();
				ticket.setStatus("ACTIVE");
				System.out.println("Da xep khach tu hang doi: " + ticket.getId());
			}
		}
	}

	/**
     * Kiem tra co phai gio cao diem khong
     * 
     * @return true neu la gio cao diem
     */
	public boolean isInPeakHour() {
		int hour = (int) timeStart;
		// Sang: 6h - 9h, Chieu: 17h - 20h
		return (hour >= 6 && hour < 9) || (hour >= 17 && hour < 20);
	}

	/**
	 * Kiem tra chuyen co san san khong
	 * 
	 * @return true neu chua huy va co the dat ve
	 */
	public boolean isAvailable() {
		return !isCancelled && (status.equals("SCHEDULED") || status.equals("ON_TIME") || status.equals("DELAYED"))
				&& train != null;
	}
	 /**
     * Lay thoi gian bat dau
     */
    public Calendar getStartDateTime() {
        Calendar start = (Calendar) date.clone();
        int hours = (int) timeStart;
        int minutes = (int) ((timeStart - hours) * 60);
        start.set(Calendar.HOUR_OF_DAY, hours);
        start.set(Calendar.MINUTE, minutes);
        return start;
    }

    /**
     * Lay thoi gian ket thuc
     */
    public Calendar getEndDateTime() {
        Calendar end = (Calendar) date.clone();
        int hours = (int) timeEnd;
        int minutes = (int) ((timeEnd - hours) * 60);
        end.set(Calendar.HOUR_OF_DAY, hours);
        end.set(Calendar.MINUTE, minutes);
        return end;
    }

}
