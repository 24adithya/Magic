package com.pack.beverages.client;

import java.util.HashMap;
import java.util.Map;

import com.pack.beverages.discount.TotalBillCalculator;
import com.pack.beverages.model.Beverages;

public class BeverageClient {

	public static void main(String[] args) {
		BeverageClient client = new BeverageClient();
		
		
		Map<Beverages.BEVERAGES, Integer> beveragesCountMap = new HashMap<>();
		beveragesCountMap.put(Beverages.BEVERAGES.TM, 10); //10 * 10 = 100
		beveragesCountMap.put(Beverages.BEVERAGES.CDC, 6); //6 * 20 = 120
		
		client.calculateTotalBill(beveragesCountMap);
	}

	private void calculateTotalBill(Map<Beverages.BEVERAGES, Integer> beveragesCountMap) {
		//Null check
		System.out.println(TotalBillCalculator.calculateTotal(beveragesCountMap));
	}
}
