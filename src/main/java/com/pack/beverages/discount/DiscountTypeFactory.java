package com.pack.beverages.discount;

public class DiscountTypeFactory {

	public static BillDiscountFactory getBillDiscount() {
		return new BillDiscountFactory();
	}
	
	public static BeverageDiscountFactory getBeverageDiscount() {
		return new BeverageDiscountFactory();
	}
}
