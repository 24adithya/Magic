package com.pack.java.patterns.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class GrandParent
{
	String name;
	
	static int count;
	
	List<GrandParent> people = new ArrayList<>();
	
	abstract public String toString();
	
	String print(GrandParent grandParent)
	{
		StringBuilder builder = new StringBuilder(name);
		builder.append("\n");
		return builder.toString();
	}
}

class Parent extends GrandParent
{
	public Parent() {
		name = "Parent" + count++;
	}
	
	public GrandParent add(GrandParent grandParent)
	{
		people.add(grandParent);
		return grandParent;
	}
	
	public GrandParent remove(GrandParent grandParent)
	{
		people.remove(grandParent);
		return grandParent;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();//builder pattern 
		builder.append(print(this));
		
		Iterator<GrandParent> itr = people.iterator();
		while(itr.hasNext())
		{
			GrandParent grandParent = itr.next();
			builder.append(grandParent.toString());
		}
		
		return builder.toString();
	}
}

class Child extends GrandParent
{
	public Child() {
		name = "Child" + count++;
	}
	
	@Override
	public String toString()
	{
		return print(this);
	}
}

public class CompositePatternDemo {

	public static void main(String[] args)
	{
		Menu menu = new Menu("VP", "\\highest");
		
		MenuItem menuItem = new MenuItem("President","\\higher");

		Menu subMenu = new Menu("CEO", "\\high");
		
		MenuItem subMenuItem1 = new MenuItem("COO","\\medium");
		MenuItem subMenuItem2 = new MenuItem("CTO","\\small");
		
		menu.add(menuItem);
		
		subMenu.add(subMenuItem1);
		menu.add(subMenu);
		
		subMenu.add(subMenuItem2);
		
		System.out.println(menu.toString());
		
		
		System.out.println("--------------------------------");
		
		Parent p1 = new Parent();
		Parent p2 = new Parent();
		
		Child c1 = new Child();
		Child c2 = new Child();
		Child c3 = new Child();
		
		p1.add(c1);
		p1.add(c2);
		p2.add(c3);
		
		p1.add(p2);
		
		System.out.println(p1);
		
	}
}

abstract class MenuComponent
{
	String name;
	String url;
	
	List<MenuComponent> menuComponents = new ArrayList<>(); 
	
	/*public MenuComponent() {
		menuComponents = new ArrayList<>();
	}
	
	public MenuComponent(String name, String url) {
		menuComponents = new ArrayList<>();
		this.name = name;
		this.url = url;
	}*/
	
	public abstract String toString();
	
	String print(MenuComponent menuComponent)
	{
		StringBuilder builder = new StringBuilder(name);
		builder.append(": ");
		builder.append(url);
		builder.append("\n");
		return builder.toString();
	}
}

class Menu extends MenuComponent
{
	public Menu(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public MenuComponent add(MenuComponent menuComponent)
	{
		menuComponents.add(menuComponent);
		return menuComponent;
	}
	
	public MenuComponent remove(MenuComponent menuComponent)
	{
		menuComponents.remove(menuComponent);
		return menuComponent;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();//builder pattern 
		builder.append(print(this));
		
		Iterator<MenuComponent> itr = menuComponents.iterator();
		while(itr.hasNext())
		{
			MenuComponent menuComponent = itr.next();
			builder.append(menuComponent.toString());
		}
		
		return builder.toString();
	}
}

class MenuItem extends MenuComponent
{
	public MenuItem(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	@Override
	public String toString()
	{
		return print(this);
	}
}