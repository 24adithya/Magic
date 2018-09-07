package com.pack.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

	public static void main(String[] args) {
		MatrixRuleData data1 = new MatrixRuleData(10);
		MatrixRuleData data2 = new MatrixRuleData(20);
		MatrixRuleData data3 = new MatrixRuleData(5);
		MatrixRuleData data4 = new MatrixRuleData(2);
		MatrixRuleData data5 = new MatrixRuleData(1);
		MatrixRuleData data6 = new MatrixRuleData(4);
		
		List<MatrixRuleData> list = new ArrayList<>();
		list.add(data1);
		list.add(data2);
		list.add(data3);
		list.add(null);
		list.add(data4);
		list.add(data5);
		list.add(null);
		list.add(data6);
		
		Collections.sort(list, new Sorter());
		
		System.out.println(list);
	}
}

class MatrixRuleData {
	private Integer rank;

	public MatrixRuleData(Integer rank) {
		super();
		this.rank = rank;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
}

class Sorter implements Comparator<MatrixRuleData> {

	@Override
	public int compare(MatrixRuleData o1, MatrixRuleData o2) {
		if(o1 != null && o2 != null && o1.getRank() > o2.getRank())
			return -1;
		else 
			return 0;
	}
	
}
