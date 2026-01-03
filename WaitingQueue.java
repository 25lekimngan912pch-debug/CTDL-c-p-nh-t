package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

//Hang doi cho tuyen tau day
public class WaitingQueue {
    // ===== ATTRIBUTES =====
    private String id;                           // Ma dinh danh hang doi
    private ScheduleDetail scheduleDetail;       // Chuyen tau tuong ung
    private Station station;                     // Ga nao
    private Queue<Ticket> waitingList;           // Danh sach ve dang cho
    private int maxCapacity;                     // So nguoi toi da co the cho
    private String status;                       // "OPEN", "FULL", "CLOSED", "BOARDING"
    private Calendar createdTime;                // Thoi gian tao hang doi

	public WaitingQueue(String id, ScheduleDetail scheduleDetail, Station station, Queue<Ticket> waitingList,
			int maxCapacity, String status, Calendar createdTime) {
		super();
		this.id = id;
		this.scheduleDetail = scheduleDetail;
		this.station = station;
		this.waitingList = new LinkedList<>();;
		this.maxCapacity = maxCapacity;
		this.status = "OPEN";
		this.createdTime = Calendar.getInstance();
	}

	public WaitingQueue(String id, ScheduleDetail scheduleDetail, Station station, int maxCapacity) {
	    this.id = id;
	    this.scheduleDetail = scheduleDetail;
	    this.station = station;
	    this.maxCapacity = maxCapacity;
	    this.waitingList = new LinkedList<>();
	    this.status = "OPEN";
	    this.createdTime = Calendar.getInstance();
	}
	
	public WaitingQueue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
        return id;
    }
    
    public ScheduleDetail getScheduleDetail() {
        return scheduleDetail;
    }
    
    public Station getStation() {
        return station;
    }
    
    public Queue<Ticket> getWaitingList() {
        return waitingList;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    public String getStatus() {
        return status;
    }
    
    public Calendar getCreatedTime() {
        return createdTime;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public void setId(String id) {
		this.id = id;
	}

	public void setScheduleDetail(ScheduleDetail scheduleDetail) {
		this.scheduleDetail = scheduleDetail;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public void setWaitingList(Queue<Ticket> waitingList) {
		this.waitingList = waitingList;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	/**
     * Them ve vao hang doi (FIFO)
     * @param ticket ve can xep
     * @return true neu them thanh cong, false neu  hang day
     */
    public boolean enqueue(Ticket ticket) {
        if (isFull()) {
            System.out.println("Hang cho day! Khong the them ve " + ticket.getId());
            return false;
        }
        
        if (ticket == null) {
            return false;
        }
        
        // Them vao cuoi hang
        waitingList.offer(ticket);
        
        // Cap nhat vi tri va thoi gian cho cho ve
        ticket.setQueuePosition(waitingList.size());
        ticket.setQueueTime(Calendar.getInstance());
        ticket.setStatus("WAITING");
        
        System.out.println("Ve " + ticket.getId() + " da vao hang doi "+ id +" vi tri " + ticket.getQueuePosition());
        
        // Cap nhat trang thai
        if (isFull()) {
            status = "FULL";
        }
        
        return true;
    }
    
    /**
     * Lay ve dau tien ra khoi hang cho (FIFO)
     * 
     * @return Ve o dau hang, null neu hang rong
     */
    public Ticket dequeue() {
        if (isEmpty()) {
            System.out.println("Hang cho rong!");
            return null;
        }
        
        // Lay va xoa phan tu dau tien
        Ticket ticket = waitingList.poll();
        
        if (ticket != null) {
            ticket.setQueuePosition(0); 
            System.out.println("Ve " + ticket.getId() + " da duoc lay khoi hang cho");
        }
        
        // Cap nhat lai vi tri
        updatePositions();
        
        // Cap nhat lai trang thai
        if (status.equals("FULL") && !isFull()) {
            status = "OPEN";
        }
        
        return ticket;
    }
    
    /**
     * Xem ve dau tien nhung khong xoa
     * 
     * @return Ve dau hang
     */
    public Ticket peek() {
        return waitingList.peek();
    }
    
    /**
     * Lay kich thuoc hang cho
     * 
     * @return So nguoi dang cho
     */
    public int size() {
        return waitingList.size();
    }
    
    /**
     * Kiem tra hang cho rong
     */
    public boolean isEmpty() {
        return waitingList.isEmpty();
    }
    
    /**
     * Kiem tra hang cho day
     */
    public boolean isFull() {
        return waitingList.size() >= maxCapacity;
    }
    
    /**
     * Xoa tat ca ve trong hang cho
     */
    public void clear() {
        int count = waitingList.size();
        waitingList.clear();
        status = "CLOSED";
        System.out.println("Da xoa " + count + " ve khoi hang cho " + id);
    }
    
    /**
     * Cap nhat lai vi tri tat ca ve
     */
    public synchronized void updatePositions() {
        int position = 1;
        for (Ticket ticket : waitingList) {
            ticket.setQueuePosition(position++);
        }
    }
    
    /**
     * Them ve uu tien vao dau hang (Cho nguoi cao tuoi/khuyet tat)
     * 
     * @param ticket Ve can uu tien
     * @return true neu thanh cong
     */
    public boolean priorityEnqueue(Ticket ticket) {
        
            if (isFull()) {
                System.out.println("❌ Hàng chờ đã đầy!");
                return false;
            }
            // Chuyen Queue thanh List
            List<Ticket> tempList = new ArrayList<>(waitingList);
            waitingList.clear();
            // Them ve uu tien vao dau
            waitingList.offer(ticket);
            ticket.setQueuePosition(1);
            ticket.setQueueTime(Calendar.getInstance());
            ticket.setStatus("WAITING");
            // Them lai cac ve cu
            tempList.forEach(waitingList::offer);
            // Cap nhat lai vi tri
            updatePositions(); 
            System.out.println("Ve " + ticket.getId() + " da duoc sap xep uu tien vao vi tri 1");
            return true;
            
        }
    
    /**
     * Xoa mot ve khoi hang cho
     * 
     * @param ticket Ve can xoa
     * @return true neu xoa thanh cong
     */
    public boolean removeTicket(Ticket ticket) {
        if (waitingList.remove(ticket)) {
            System.out.println("Da xoa ve " + ticket.getId() + " khoi hang cho");
            updatePositions();
            return true;
        }
        return false;
    }

    /**
     * Thong bao cho khach hang tiep theo
     */
    public void notifyNextCustomer() {
        Ticket nextTicket = peek();
        Optional.ofNullable(nextTicket).ifPresent(ticket -> {
                    Customer customer = ticket.getCustomer();
                    System.out.println("Khach: " + customer.getName());
                    System.out.println("Ve: " + ticket.getId());
                    System.out.println("Vi tri: 1 (dau hang)");
                    System.out.println("Chuyen: " + scheduleDetail.getId());
                    System.out.println(" DA DEN LUOT BAN!");
                });
    }
    
    /**
     * Lay vi tri cua khach hang trong hang cho
     * 
     * @param customer Khach hang
     * @return Vi tri, 0 neu khong tim thay
     */
    public int getCustomerPosition(Customer customer) {
        if (customer == null) return 0;
        List<Ticket> list = new ArrayList<>(waitingList);
        return Optional.of(list.stream().filter(t -> t.getCustomer().equals(customer)).findFirst().map(list::indexOf).map(i -> i + 1).orElse(0)).get();
    }
    
    /**
     * Kiem tra ve co trong hang cho khong
     * 
     * @param ticket Ve can kiem tra
     * @return true neu co
     */
    public boolean contains(Ticket ticket) {
        return waitingList.contains(ticket);
    }
    
    /**
     * Gan them toa de tang cho
     * 
     * @param train Tau can gan them toa
     */
    public void assignExtraCarriage(Train train) {
        if (train != null && !isEmpty()) {
            System.out.println("Dang gan them toa cho tau " + train.getName());
            System.out.println("Hang doi hien tai: " + size() + " nguoi");

        }
    }
    
    /**
     * Lay danh sach khach hang dang cho
     * 
     * @return List ten khach hang
     */
    public List<String> getCustomerNames() {
        return waitingList.stream() .map(Ticket::getCustomer).map(Customer::getName).collect(Collectors.toList());
    }

    public int getEstimatedWaitTime() {
        // TODO implement here
    	//Gia su thoi gian cho la 2 phut 
    	 int minutesPerPerson = 2;
         return waitingList.size() * minutesPerPerson;
    }
    
    
}
