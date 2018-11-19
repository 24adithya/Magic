package com.pack.beverages.discount;

import java.util.Iterator;
import java.util.Map;

import com.pack.beverages.model.Beverages;

public class DiscountCalculator {

	//Story #1
	public static Double applyDiscountOnBill(Double total) {

		double discountedTotal = 0.0d;
		
		if (total > 200.0d) {
			discountedTotal = BillDiscountFactory.applyTwentyPercentDiscount().calculateDiscount(total);
		} else if (total > 100.0d) {
			discountedTotal = BillDiscountFactory.applyTenPercentDiscount().calculateDiscount(total);
		}

		return discountedTotal;
	}
	
	//Story #2
	public static Double applyDiscountForBeverages(Double total, Map<Beverages.BEVERAGES, Integer> beveragesCountMap) {

		double discountedTotal = 0.0d;

		Iterator<Beverages.BEVERAGES> beverageSet = beveragesCountMap.keySet().iterator();
		Beverages.BEVERAGES tempBeverage = null;
		while(beverageSet.hasNext()) {
			tempBeverage = beverageSet.next();
			
			/*if(Beverages.BEVERAGES.CL.equals(tempBeverage) && beveragesCountMap.get(tempBeverage) >= 2 ) {
				discountedTotal = BeverageDiscountFactory.applyCoffeeLatteDiscount().calculateDiscount(total, beveragesCountMap.get(tempBeverage));
			} else if(Beverages.BEVERAGES.TM.equals(tempBeverage) && beveragesCountMap.get(tempBeverage) >= 5 ) {
				discountedTotal = BeverageDiscountFactory.applyTeaMasalaDiscount().calculateDiscount(total, beveragesCountMap.get(tempBeverage));
			} else if(Beverages.BEVERAGES.CDC.equals(tempBeverage) && beveragesCountMap.get(tempBeverage) >= 10 ) {
				discountedTotal = BeverageDiscountFactory.applyTeaMasalaDiscount().calculateDiscount(total, beveragesCountMap.get(tempBeverage));
			}*/
			
			if(Beverages.BEVERAGES.CL.equals(tempBeverage) && beveragesCountMap.get(tempBeverage) >= 2 ) {
				discountedTotal = BeverageDiscountFactory.applyCoffeeLatteDiscount().calculateDiscount(total, beveragesCountMap.get(tempBeverage));
			} else if(Beverages.BEVERAGES.TM.equals(tempBeverage) && beveragesCountMap.get(tempBeverage) >= 5 ) {
				discountedTotal = BeverageDiscountFactory.applyTeaMasalaDiscount().calculateDiscount(total, beveragesCountMap.get(tempBeverage));
			} else if(Beverages.BEVERAGES.CDC.equals(tempBeverage) && beveragesCountMap.get(tempBeverage) >= 10 ) {
				discountedTotal = BeverageDiscountFactory.applyTeaMasalaDiscount().calculateDiscount(total, beveragesCountMap.get(tempBeverage));
			} 
			
			BeverageDiscountFactory.applyDiscount(tempBeverage);
		}

		
		
		return discountedTotal;
	}
	
	
}





