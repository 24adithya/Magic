package com.pack.reflection.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ReflectionTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		BigDecimal[] qtyArray = new BigDecimal[] {BigDecimal.valueOf(1000),BigDecimal.valueOf(2000)};
		Sample sample = new Sample(10, BigDecimal.valueOf(100), BigDecimal.valueOf(100), qtyArray);
//		sample.setNumber(10);
//		sample.setQtyDay1(new BigDecimal(100.0));
		
		String str = "qtyArray.0";
		String[] array =  str.split("\\.");
		
		SampleIDTO sampleIDTO = setQtyNormal(sample, array[0], 1);
		
		findMet(new SampleIDTO(), "qtyArray" );
		
		System.out.println("Modified sample qtyDay1 = " + sampleIDTO.toString());
		
		int res = callFinally();
		System.out.println("Res = " + res); 
	}

	private static void findMet(SampleIDTO sampleIDTO, String str) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String getMethodStr = str.substring(0, 1).toUpperCase() + str.substring(1);
		getMethodStr = "get" + getMethodStr;
		
		
		BigDecimal qty = null;
		Method getMethod = sampleIDTO.getClass().getMethod(getMethodStr, null);
		
		if(getMethod != null) {
			Object value = getMethod.invoke(sampleIDTO, null);
			if(value instanceof BigDecimal[]) {
//				qty = ((BigDecimal[]) value)[count];
			}
		}
	}

	private static int callFinally() {

		/*try {
			System.out.println("Inside callFinally");
		}
		finally {
			return -1;
		}*/
		return 0;
	}

	/**
	 * Access private members
	 * @param sample
	 * @param str
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static Sample setQty(Sample sample, String str) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if(sample != null) {
			
			String getMethodStr = str.substring(0, 1).toUpperCase() + str.substring(1);
			getMethodStr = "get" + getMethodStr;
			
			String setMethodStr = str.substring(0, 1).toUpperCase() + str.substring(1);
			setMethodStr = "set" + setMethodStr;
			
			BigDecimal qty = null;
			Method getMethod = null;
			Method setMethod = null;
//			Method getMethod = sample.getClass().getMethod(getMethodStr, null);
			Method[] methods = sample.getClass().getDeclaredMethods();
			for(Method tempM : methods) {
				if(tempM != null && tempM.getName().equals(getMethodStr)) {
					getMethod = tempM;
				}
				
				if(tempM != null && tempM.getName().equals(setMethodStr)) {
					setMethod = tempM;
				}
				
				if(getMethod != null && setMethod != null) {
					break;
				}
			}
			
			if(getMethod != null) {
				getMethod.setAccessible(true);
				Object value = getMethod.invoke(sample, null);
    			if(value instanceof BigDecimal) {
    				qty = (BigDecimal) value;
    			}
			}
			
			if(qty != null) {
				setMethod.setAccessible(true);
//				setMethod = sample.getClass().getMethod(setMethodStr, new Class[]{BigDecimal.class});
				setMethod.invoke(sample, BigDecimal.valueOf(200));
			}
		}
		return sample;
	}
	
	/**
	 * Access members normally
	 * @param sample
	 * @param str
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static SampleIDTO setQtyNormal(SampleIDTO sample, String str, int count) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if(sample != null) {
			
			String getMethodStr = str.substring(0, 1).toUpperCase() + str.substring(1);
			getMethodStr = "get" + getMethodStr;
			
			String setMethodStr = str.substring(0, 1).toUpperCase() + str.substring(1);
			setMethodStr = "set" + setMethodStr;
			
			BigDecimal qty = null;
			Method getMethod = sample.getClass().getMethod(getMethodStr, null);
			
			if(getMethod != null) {
				Object value = getMethod.invoke(sample, null);
    			if(value instanceof BigDecimal[]) {
    				qty = ((BigDecimal[]) value)[count];
    			}
			}
			
//			if(qty != null) {
//				Method setMethod = sample.getClass().getMethod(setMethodStr, new Class[]{BigDecimal[].class});
//				setMethod.invoke(sample, new BigDecimal[]{BigDecimal.valueOf(3000),BigDecimal.valueOf(4000)});
//			}
		}
		return sample;
	}
}

class SampleIDTO {
	
}

class Sample extends SampleIDTO {
	private Integer number;
	private BigDecimal qtyDay1;
	private BigDecimal qtyDay2;
	private BigDecimal[] qtyArray;
	
	public Sample() {
		
	}
	
	public Sample(Integer number, BigDecimal qtyDay1, BigDecimal qtyDay2) {
		super();
		this.number = number;
		this.qtyDay1 = qtyDay1;
		this.qtyDay2 = qtyDay2;
	}
	
	public Sample(Integer number, BigDecimal qtyDay1, BigDecimal qtyDay2, BigDecimal[] qtyArray) {
		super();
		this.number = number;
		this.qtyDay1 = qtyDay1;
		this.qtyDay2 = qtyDay2;
		this.qtyArray = qtyArray;
	}

	/*private Integer getNumber() {
		return number;
	}

	private void setNumber(Integer number) {
		this.number = number;
	}

	private BigDecimal getQtyDay1() {
		return qtyDay1;
	}

	private void setQtyDay1(BigDecimal qtyDay1) {
		this.qtyDay1 = qtyDay1;
	}

	private BigDecimal getQtyDay2() {
		return qtyDay2;
	}

	private void setQtyDay2(BigDecimal qtyDay2) {
		this.qtyDay2 = qtyDay2;
	}*/

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public BigDecimal getQtyDay1() {
		return qtyDay1;
	}

	public void setQtyDay1(BigDecimal qtyDay1) {
		this.qtyDay1 = qtyDay1;
	}

	public BigDecimal getQtyDay2() {
		return qtyDay2;
	}

	public void setQtyDay2(BigDecimal qtyDay2) {
		this.qtyDay2 = qtyDay2;
	}

	public BigDecimal[] getQtyArray() {
		return qtyArray;
	}

	public void setQtyArray(BigDecimal[] qtyArray) {
		this.qtyArray = qtyArray;
	}

	@Override
	public String toString() {
		
		return "Number = " + number + ", qtyDay1 = " + qtyDay1 + ", qtyDay2 = " + qtyDay2 + ", qtyArray = " + qtyArray;
	}
	
}
