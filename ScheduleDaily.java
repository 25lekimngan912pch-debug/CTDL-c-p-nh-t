package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

//Lich trinh trong mot ngay
public class ScheduleDaily {
	private String id; //Ma dinh danh lich trinh trong ngay
	private Calendar date; //Ngay cua lich trinh
	private Route route; //Tuyen duong
	private List<ScheduleDetail> details; // Danh sach cac chuyen trong ngay

	public ScheduleDaily(String id, Calendar date, Route route) {
		this.id = Optional.ofNullable(id).filter(s -> !s.trim().isEmpty()).orElseThrow(() -> new IllegalArgumentException("ID khong duoc rong"));
		this.date = Optional.ofNullable(date).map(d -> (Calendar) d.clone()).orElseThrow(() -> new IllegalArgumentException("Ngay la bat buoc!"));
		this.route = route;
		this.details = new ArrayList<>();
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

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public List<ScheduleDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ScheduleDetail> details) {
		this.details = details;
	}
	 /**
     * Kiem tra 2 chuyen co trung nhau khong
     * 
     * @param c1 Chuyen 1
     * @param c1 Chuyen 2
     * @return true neu trung
     */
    private boolean isTimeConflict(ScheduleDetail c1, ScheduleDetail c2) {
        return !(c1.getTimeEnd() <= c2.getTimeStart() || 
                 c2.getTimeEnd() <= c1.getTimeStart());
    }

    /**
     * Them mot chuyen
     * 
     * @param detail Chi tiet chuyen tau
     * @throws IllegalArgumentException neu detail da ton tai
     */

	public void addDetail(ScheduleDetail detail) {
		 if (details.contains(detail)) {
             throw new IllegalArgumentException("Chuyen " + detail.getId() + " da ton tai");
         }
         
         //Kiem tra trung thoi gian
         boolean hasConflict = details.stream()
                 .anyMatch(d -> isTimeConflict(d, detail));
         
         if (hasConflict) {
             System.out.println("CANH BAO: Chuyen co the trung gio voi chuyen khac");
         }
         
         details.add(detail);
         System.out.println("Da them chuyen " + detail.getId() + " vao lich trinh");
	}

	/**
     * Xoa mot chuyen khoi lich trinh
     * 
     * @param detail Chuyen can xoa
     * @return true neu xoa thanh cong
     */
	public void removeDetail(ScheduleDetail detail) {
		if (details.remove(detail)) {
			System.out.println("Da xoa chuyen " + detail.getId() + " khoi lich trinh " + id);
		}
	}

	 /**
     * Tim chuyen theo ID
     * 
     * @param id ID chuyen can tim
     * @return ScheduleDetail neu tim thay, null neu khong thay
     */
	public ScheduleDetail getDetailById(String id) {
		for (ScheduleDetail detail : details) {
			if (detail.getId().equals(id)) {
				return detail;
			}
		}
		return null;
	}

	/**
     * Lay danh sach cac chuyen bi huy
     * 
     * @return List cac chuyen bi huy
     */
	public List<ScheduleDetail> getCancelledTrips() {
		List<ScheduleDetail> cancelledList = new ArrayList<>();
		for (ScheduleDetail detail : details) {
			if (detail.isCancelled()) {
				cancelledList.add(detail);
			}
		}
		return cancelledList;
	}

	/**
     * Lay danh sach cac chuyen bi tre
     * 
     * @return List cac chuyen bi tre
     */
	public List<ScheduleDetail> getDelayedTrips() {
		List<ScheduleDetail> delayedList = new ArrayList<>();
		for (ScheduleDetail detail : details) {
			if ("DELAYED".equals(detail.getStatus())) {
				delayedList.add(detail);
			}
		}
		return delayedList;
	}

	/**
     * Lay danh sach cac chuyen con cho ngoi
     * 
     * @return List cac chuyen chua day
     */
	public List<ScheduleDetail> getAvailableTrips() {
		List<ScheduleDetail> availableList = new ArrayList<>();
		for (ScheduleDetail detail : details) {
			if (!detail.isFull() && !detail.isCancelled()) {
				availableList.add(detail);
			}
		}
		return availableList;
	}

	/**
     * Lay danh sach cac chuyen da hoan thanh
     * 
     * @return List cac chuyen hoan thanh
     */
	public List<ScheduleDetail> getCompletedTrips() {
		List<ScheduleDetail> completedList = new ArrayList<>();
		for (ScheduleDetail detail : details) {
			if ("COMPLETED".equals(detail.getStatus())) {
				completedList.add(detail);
			}
		}
		return completedList;
	}

	/**
     * Lay danh sach cac chuyen khong bi huy
     * 
     * @return List cac chuyen khong bi huy
     */
	public List<ScheduleDetail> getActiveTrips() {
		List<ScheduleDetail> activeList = new ArrayList<>();
		for (ScheduleDetail detail : details) {
			if (!detail.isCancelled()) {
				activeList.add(detail);
			}
		}
		return activeList;
	}

	/**
     * Lay cac chuyen trong khoang thoi gian
     * 
     * @param startTime Gio bat dau
     * @param endTime Gio ket thuc
     * @return List cac chuyen trong khoang thoi gian
     */
	public List<ScheduleDetail> getTripsInTimeRange(double startTime, double endTime) {
		List<ScheduleDetail> tripsInRange = new ArrayList<>();
		for (ScheduleDetail detail : details) {
			double tripTime = detail.getTimeStart();
			if (tripTime >= startTime && tripTime <= endTime) {
				tripsInRange.add(detail);
			}
		}
		return tripsInRange;
	}

	/**
     * Lay chuyen tiep theo sau thoi diem hien tai
     * 
     * @param currentTime Thoi gian hien tai
     * @return Chuyen sap chay gan nhat
     */
	public ScheduleDetail getNextTrip(Calendar currentTime) {
		// Chuyen Calender thanh so gio
		int hour = currentTime.get(Calendar.HOUR_OF_DAY);
		int minute = currentTime.get(Calendar.MINUTE);
		double currentTimeDouble = hour + (minute / 60.0);

		ScheduleDetail nextTrip = null;
		double minTimeDiff = Double.MAX_VALUE;

		for (ScheduleDetail detail : details) {
			if (!detail.isCancelled() && detail.getTimeStart() > currentTimeDouble) {
				double timeDiff = detail.getTimeStart() - currentTimeDouble;
				if (timeDiff < minTimeDiff) {
					minTimeDiff = timeDiff;
					nextTrip = detail;
				}
			}
		}

		return nextTrip;
	}

	/**
     * Dem tong so chuyen trong ngay
     * 
     * @return So luong chuyen
     */
	public int getTotalTrips() {
		return details.size();
	}
	/**
     * Sap xep cac chuyen theo gio khoi hanh
     */
    public void sortByTime() {
        details.sort(Comparator.comparingDouble(ScheduleDetail::getTimeStart));
        System.out.println("Da sap xep " + details.size() + " chuyen theo thoi gian");
    }
    
}
