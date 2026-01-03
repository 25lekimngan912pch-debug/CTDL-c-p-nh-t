package LeThiKimNgan_24130197_TranThiCamTu_24130350;

import java.io.*;
import java.util.*;

public class TicketType {
	private String id;
	private String name; // ve luoc ve ngay ve thang
	private double discountRate;
	private int validityDays; // 1 day , 30 day ,1 turn
	private String description;

	public TicketType(String id, String name, double discountRate, int validityDays, String description) {
		super();
		this.id = id;
		this.name = name;
		this.discountRate = discountRate;
		this.validityDays = validityDays;
		this.description = description;
	}

	public TicketType() {
		super();
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

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		if (discountRate >= 0.0 && discountRate <= 1.0) {
			this.discountRate = discountRate;
		} else {
			System.out.println("Nhap sai");
		}
	}

	public int getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(int validityDays) {
		if (validityDays >= 0) {
			this.validityDays = validityDays;
		} else {
			System.out.println("So ngay hieu luc phai lon hon 0");
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	////////////////////// phuong thuc ///////////////////////////////
	/**
	 * @param basePrice
	 * @return
	 */
	public double calculatePrice(double basePrice) {
		// TODO Auto-generated method stub
		if (basePrice < 0) {
			System.out.println("Gia goc khong hop le");
			return 0;
		}
		double finalPrice = basePrice * (1 - discountRate);
		// lam tron
		return Math.round(finalPrice);
	}
	//KIEM TRA CO GIAM GIA KHONG
	public boolean isDiscounted() {
		return this.discountRate > 0.0;
	}
	//GIA CHIET KHAU
	public double getDiscountPercentage() {
		return this.discountRate * 100;

	}

	public static void main(String[] args) {
		System.out.println("==================== TAO DOI TUONG VE ====================");
		TicketType veLuot = new TicketType("TT01", "Ve luot", 0.0, 1, "Ve di mot luot");
		TicketType veNgay = new TicketType("TT02", "Ve ngay", 0.1, 1, "Ve di trong ngay");
		TicketType veThang = new TicketType("TT03", "Ve thang", 0.2, 30, "Ve di trong thang");
		TicketType veKhuyenMai = new TicketType("TT04", "Ve khuyen mai", 0.5, 7, "Ve giam 50% trong 1 tuan");
		TicketType veMienPhi = new TicketType("TT05", "Ve mien phi", 1.0, 1, "Ve giam 100%");

		System.out.println("Danh sach cac loai ve:");
		System.out.println(veLuot.getName() + " - Giam gia: " + veLuot.getDiscountRate());
		System.out.println(veNgay.getName() + " - Giam gia: " + veNgay.getDiscountRate());
		System.out.println(veThang.getName() + " - Giam gia: " + veThang.getDiscountRate());
		System.out.println(veKhuyenMai.getName() + " - Giam gia: " + veKhuyenMai.getDiscountRate());
		System.out.println(veMienPhi.getName() + " - Giam gia: " + veMienPhi.getDiscountRate());
		System.out.println();

		System.out.println("==================== TEST TINH GIA SAU GIAM ====================");
		double[] giaGocList = { 5000, 10000, 20000, 50000, 100000 };
		for (double goc : giaGocList) {
			System.out.println("Gia goc: " + goc);
			System.out.println("Ve luot: " + veLuot.calculatePrice(goc));
			System.out.println("Ve ngay (10%): " + veNgay.calculatePrice(goc));
			System.out.println("Ve thang (20%): " + veThang.calculatePrice(goc));
			System.out.println("Ve khuyen mai (50%): " + veKhuyenMai.calculatePrice(goc));
			System.out.println("Ve mien phi (100%): " + veMienPhi.calculatePrice(goc));
			System.out.println();
		}

		System.out.println("==================== TEST GIA GOC AM (THAT BAI) ====================");
		double giaAm = -100000;
		System.out.println("Ket qua tinh gia voi gia am: " + veLuot.calculatePrice(giaAm));
		System.out.println();

		System.out.println("==================== TEST KIEM TRA CO GIAM GIA KHONG ====================");
		System.out.println("Ve luot: " + (veLuot.isDiscounted() ? "Co giam gia" : "Khong giam gia"));
		System.out.println("Ve ngay: " + (veNgay.isDiscounted() ? "Co giam gia" : "Khong giam gia"));
		System.out.println("Ve mien phi: " + (veMienPhi.isDiscounted() ? "Co giam gia" : "Khong giam gia"));
		System.out.println();

		System.out.println("==================== TEST PHAN TRAM GIAM GIA ====================");
		System.out.println("Ve ngay: " + veNgay.getDiscountPercentage() + "%");
		System.out.println("Ve thang: " + veThang.getDiscountPercentage() + "%");
		System.out.println("Ve khuyen mai: " + veKhuyenMai.getDiscountPercentage() + "%");
		System.out.println("Ve mien phi: " + veMienPhi.getDiscountPercentage() + "%");
		System.out.println();

		System.out.println("==================== TEST GIA CHIET KHAU ====================");
		veLuot.setDiscountRate(0.25);
		System.out.println("Gia chiet khau moi cua ve luot: " + veLuot.getDiscountRate());
		veLuot.setDiscountRate(-0.5); // sai
		veLuot.setDiscountRate(1.5); // sai
		System.out.println("Gia chiet khau sau khi set sai: " + veLuot.getDiscountRate());
		System.out.println();

		System.out.println("==================== TEST NGAY HIEU LUC ====================");
		veNgay.setValidityDays(5);
		System.out.println("So ngay hieu luc moi cua ve ngay: " + veNgay.getValidityDays());
		veNgay.setValidityDays(-10); // sai
		System.out.println("So ngay hieu luc sau khi set sai: " + veNgay.getValidityDays());
	}
}