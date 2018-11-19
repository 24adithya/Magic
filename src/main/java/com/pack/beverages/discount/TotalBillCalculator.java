package com.pack.beverages.discount;

import java.util.Iterator;
import java.util.Map;

import com.pack.beverages.model.Beverages;

public class TotalBillCalculator {

	/**
	 * Method to calculate total bill after applying discount.
	 * 
	 * @param beveragesCountMap
	 * @return
	 */
	public static Double calculateTotal(
			Map<Beverages.BEVERAGES, Integer> beveragesCountMap) {

		Iterator<Beverages.BEVERAGES> iterator = beveragesCountMap.keySet()
				.iterator();
		Beverages.BEVERAGES tempBeverage = null;
		Double total = 0.0d, discountedTotal = 0.0d;

		while (iterator.hasNext()) {
			tempBeverage = iterator.next();
			total += tempBeverage.getPrice()
					* beveragesCountMap.get(tempBeverage);
		}
		discountedTotal = DiscountCalculator.applyDiscountOnBill(total);
		return discountedTotal;
	}
}
