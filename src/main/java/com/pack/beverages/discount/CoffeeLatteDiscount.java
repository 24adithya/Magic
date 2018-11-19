package com.pack.beverages.discount;

public class CoffeeLatteDiscount implements BeverageDiscount {

	@Override
	public double calculateDiscount(double totalBill, int beverageCount) {
		return totalBill - (totalBill * 0.25);
	}
	
}
