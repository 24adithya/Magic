package com.pack.beverages.discount;

public class TwentyPercentDiscount implements BillDiscount {

	@Override
	public double calculateDiscount(double totalBill) {
		double amountForTwentyDiscount = totalBill - 200;
		return totalBill - ((200 * 0.1d) + (amountForTwentyDiscount * 0.2d));
	}

}
