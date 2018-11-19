package com.pack.beverages.discount;

public class BeverageDiscountFactory {

	public static CoffeeLatteDiscount applyCoffeeLatteDiscount() {
		return new CoffeeLatteDiscount();
	}

	public static TeaMasalaDiscount applyTeaMasalaDiscount() {
		return TeaMasalaDiscount();
	}
}
