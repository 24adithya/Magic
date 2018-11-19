package com.pack.beverages.model;

public class Beverages {

	public enum BEVERAGES {
		TM("Tea - Masala", 10.0d), TI("Tea - Ice",15.0d), TL("Tea - Lemon",15.0d),
		CC("Coffee - COLD", 15.0d), CL("Coffee - Latte",30.0d), CM("Coffee - Mocha",40.0d),
		CDC("Cold Drinks - Coke", 20.0d), CDP("Cold Drinks - Pepsi",20.0d), CDS("Cold Drinks - Sprite",15.0d);
		
		String name;
		Double price;
		
		
		BEVERAGES(String name, Double price) {
			this.name = name;
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
	}
}

class Beverage {
	String itemCode;
	String name;
	PricingAttributes attributes;
}

class PricingAttributes {
	double price;
	double discount;
	
}
