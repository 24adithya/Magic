package com.pack.java;

public class TestChildVarAccess {

	public static void main(String[] args)
	{
		Parent p = new Child();
		p.setChildName();
	}
}

class Parent {
	
	protected void setChildName() {
		if(this instanceof Child) {
			((Child)this).setName("ChildParent");
		}
	}
}

class Child extends Parent {
	private String name = null;

	protected String getName()
	{
		return name;
	}

	protected void setName(String name)
	{
		this.name = name;
	}
	
	
}
