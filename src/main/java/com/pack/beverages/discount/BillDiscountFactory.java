package com.pack.beverages.discount;

public class BillDiscountFactory {

	public static BillDiscount applyTwentyPercentDiscount() {
		return new TwentyPercentDiscount();
	}
	
	public static BillDiscount applyTenPercentDiscount() {
		return new TenPercentDiscount();
	}
}
