package com.pack.java.interfaces.test;

public class InterfacesTest implements ParentInterface {
	
	private boolean multiCRUD = false;

	public static void main(String[] args) {
		InterfacesTest test = new InterfacesTest();
		test.setMultiCRUD(true);
		test.testMultiCRUD(test);
	}
	
	private void testMultiCRUD(InterfacesTest test) {
		System.out.println(test.isMultiCRUD());
	}
	
	public void setMultiCRUD(boolean multiCRUD) {
		this.multiCRUD = multiCRUD;
	}

	public boolean isMultiCRUD() {
		return multiCRUD;
	}
}



interface ParentInterface {
	public default boolean isMultiCRUD() {
		return false;
	}
}
