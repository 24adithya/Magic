package com.pack.java.patterns.decorator;

public class CustomDecoratorPatternDemo {

	public static void main(String[] args)
	{
		System.out.println(new DressingDecorator(new CottageCheeseDecorator(new SaladDressingDecorator(new SimpleSandwich()))).make());
	}
}

interface Sandwich
{
	String make();
}

class SimpleSandwich implements Sandwich
{
	@Override
	public String make()
	{
		return "Bread";
	}
}

//important class for decorating..contains the object(sandwich) to be decorated
abstract class SandwichDecorator implements Sandwich
{
	protected Sandwich customSandwich;
	
	public SandwichDecorator(Sandwich customSandwich) {
		this.customSandwich = customSandwich;
	}
	
	@Override
	public String make()
	{
		return customSandwich.make();
	}
}

class CottageCheeseDecorator extends SandwichDecorator
{
	public CottageCheeseDecorator(Sandwich customSandwich) {
		super(customSandwich);
	}
	
	@Override
	public String make()
	{
		return customSandwich.make() + addPaneer();
	}

	private String addPaneer()
	{
		return " + Paneer";
	}
}

class DressingDecorator extends SandwichDecorator
{
	public DressingDecorator(Sandwich customSandwich) {
		super(customSandwich);
	}
	
	@Override
	public String make()
	{
		return customSandwich.make() + addDressing();
	}

	private String addDressing()
	{
		return " + Chipotle";
	}
}

class SaladDressingDecorator extends SandwichDecorator
{
	public SaladDressingDecorator(Sandwich customSandwich) {
		super(customSandwich);
	}
	
	@Override
	public String make()
	{
		return customSandwich.make() + addSalad();
	}

	private String addSalad()
	{
		return " + Onions";
	}
}