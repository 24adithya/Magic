package com.pack.beverages.discount;

public class TenPercentDiscount implements BillDiscount {

	@Override
	public double calculateDiscount(double totalBill) {
		return totalBill - (totalBill * 0.1d);
	}

}