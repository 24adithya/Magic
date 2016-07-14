package com.pack.java.thread;

import java.util.Arrays;


enum BreadType {
	HONEY_OATS, MULTI_GRAIN, NORMAL, PARMESAN_OREGANO;
	public String toString() {return this.name();};
}

enum Extra {
	CHEESE, PEPPER, SALT;
	public String toString() {return this.name();};
}

enum Filling {
	ALOO_TIKKI, CHOLE, CORN_PEAS, MEXICAN, PANEER;
	public String toString() {return this.name();};	
}

class Sandwich {
	private BreadType breadType;
	private Extra[] extras;
	private Filling filling;
	private String name;
	private SandwichSauce[] sandwichSauces;

	private Veggie[] veggies;

	public Sandwich(String name, BreadType breadType,
			SandwichSauce[] sandwichSauces, Veggie[] veggies, Filling filling,
			Extra[] extras) {
		super();
		this.breadType = breadType;
		this.sandwichSauces = sandwichSauces;
		this.veggies = veggies;
		this.filling = filling;
		this.extras = extras;
	}

	public BreadType getBreadType() {
		return breadType;
	}

	public Extra[] getExtras() {
		return extras;
	}

	public Filling getFilling() {
		return filling;
	}

	public String getName() {
		return name;
	}

	public SandwichSauce[] getSandwichSauces() {
		return sandwichSauces;
	}

	public Veggie[] getVeggies() {
		return veggies;
	}

	public void setBreadType(BreadType breadType) {
		this.breadType = breadType;
	}

	public void setExtras(Extra[] extras) {
		this.extras = extras;
	}

	public void setFilling(Filling filling) {
		this.filling = filling;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSandwichSauces(SandwichSauce[] sandwichSauces) {
		this.sandwichSauces = sandwichSauces;
	}

	public void setVeggies(Veggie[] veggies) {
		this.veggies = veggies;
	}
}

class SandwichDecorator implements Runnable {
	Sandwich sandwich;

	public SandwichDecorator(Sandwich sandwich) {
		super();
		this.sandwich = sandwich;
	}
	private void decorateSandwich() {
		synchronized (this) {
			System.out.println("Dressings " + Arrays.toString(sandwich.getSandwichSauces())
					+ " added to sandwich with extras " + Arrays.toString(sandwich.getExtras()));
			notify();
		}
	}

	@Override
	public void run() {
		decorateSandwich();
	}
}

class SandwichMaker implements Runnable {
	SandwichMaterialAssembler assembler;
	SandwichDecorator decorator;
	SandwichPresenter presenter;
	
	Runnable[] array;

	public SandwichMaker(SandwichMaterialAssembler assembler,
			SandwichDecorator decorator,
			SandwichPresenter presenter) {
		super();
		this.assembler = assembler;
		this.decorator = decorator;
		this.presenter = presenter;
		this.array = new Runnable[]{assembler, decorator, presenter};
	}

	private void makeSandwich() {
		
		synchronized (presenter) {
			synchronized (decorator) {
				
				//Wait for Assembler
				synchronized (assembler) {
					System.out.println("Waiting for Sandwich Assembling..");
					try {
						assembler.wait();
						System.out.println("Assembled bread with veggies and filling. "
								+ "Now time for dressing and extras");
					} catch (InterruptedException e) {
						System.out.println("Sandwich material unavailable !");
					}
				}
				
				//Wait for Decorator
				System.out.println("Waiting for Sandwich Dressing");
				try {
					decorator.wait();
					System.out.println("Added dressing and extras. Time for presenting.");
				} catch (InterruptedException e) {
					System.out.println("Ran out of sauces and extras !!");
				}
			}
			
			//Wait for presenter
			System.out.println("Waiting to present sandwich to .." );
			try {
				presenter.wait();
				System.out.println("Customer liked the sandwich.. :) ..5 stars !!");
			} catch (InterruptedException e) {
				System.out.println("Customer dissatisfied with sandwich ! No problem..Next time :)");
			}
		}
	}

	@Override
	public void run() {
		makeSandwich();
	}
}

class SandwichMaterialAssembler implements Runnable {

	Sandwich sandwich;

	public SandwichMaterialAssembler(Sandwich sandwich) {
		super();
		this.sandwich = sandwich;
	}

	private void assembleRawMaterial() {
		
		synchronized (this) {
			System.out.println("Assembling sandwich : " + sandwich.getBreadType()
					+ " with " + Arrays.toString(sandwich.getVeggies()) + " and filling "
					+ sandwich.getFilling());
			notify();
			
			System.out.println("Assembling done ? :) ");
			System.out.println("Assembling done ? :D ");
		}
	}

	@Override
	public void run() {
		assembleRawMaterial();
	}
}

class SandwichPresenter implements Runnable {
	Sandwich sandwich;

	public SandwichPresenter(Sandwich sandwich) {
		super();
		this.sandwich = sandwich;
	}

	private void presentToCustomer() {
		synchronized (this) {
			System.out.println("Presenting the sandwich to " + sandwich.getName());
			notify();
		}
	}

	@Override
	public void run() {
		presentToCustomer();
	}
}

enum SandwichSauce {
	CHILLI, CHIPOTLE, HONEY_MUSTARD, MAYO, MINT_MAYO, MUSTARD;
	public String toString() {return this.name();};
}

public class ThreadInteractionTest {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void main(String[] args) {
		Sandwich adithyaSandwich = new Sandwich("Adithya",
				BreadType.PARMESAN_OREGANO, new SandwichSauce[] {
						SandwichSauce.CHIPOTLE, SandwichSauce.HONEY_MUSTARD },
				new Veggie[] { Veggie.CAPSICUM, Veggie.ONION, Veggie.TOMATO,
						Veggie.OLIVE }, Filling.PANEER, new Extra[] {
						Extra.CHEESE, Extra.SALT, Extra.PEPPER });

		Sandwich pranaliSandwich = new Sandwich("Pranali",
				BreadType.MULTI_GRAIN, new SandwichSauce[] {
						SandwichSauce.MINT_MAYO, SandwichSauce.HONEY_MUSTARD },
				new Veggie[] { Veggie.CAPSICUM, Veggie.ONION, Veggie.TOMATO,
						Veggie.OLIVE }, Filling.CHOLE, new Extra[] {
						Extra.CHEESE, Extra.SALT, Extra.PEPPER });

		Sandwich mohitSandwich = new Sandwich("Mohit", BreadType.MULTI_GRAIN,
				new SandwichSauce[] { SandwichSauce.CHILLI,
						SandwichSauce.HONEY_MUSTARD }, new Veggie[] {
						Veggie.CAPSICUM, Veggie.ONION, Veggie.TOMATO,
						Veggie.JALAPENO, Veggie.OLIVE }, Filling.ALOO_TIKKI,
				new Extra[] { Extra.CHEESE, Extra.SALT, Extra.PEPPER });

		Sandwich nitishSandwich = new Sandwich("Nitish", BreadType.HONEY_OATS,
				new SandwichSauce[] { SandwichSauce.MINT_MAYO,
						SandwichSauce.HONEY_MUSTARD }, new Veggie[] {
						Veggie.CAPSICUM, Veggie.ONION, Veggie.TOMATO,
						Veggie.JALAPENO }, Filling.MEXICAN, new Extra[] {
						Extra.CHEESE, Extra.SALT, Extra.PEPPER });

		Sandwich guestSandwich = new Sandwich("Guest", BreadType.NORMAL,
				new SandwichSauce[] { SandwichSauce.MINT_MAYO,
						SandwichSauce.CHIPOTLE, SandwichSauce.CHILLI },
				new Veggie[] { Veggie.CAPSICUM, Veggie.ONION, Veggie.TOMATO,
						Veggie.JALAPENO }, Filling.CORN_PEAS, new Extra[] {
						Extra.CHEESE, Extra.SALT, Extra.PEPPER });
		
		Thread[] threads = new Thread[20];
		
		SandwichMaterialAssembler assembler1 = new SandwichMaterialAssembler(adithyaSandwich);
		SandwichDecorator decorator1 = new SandwichDecorator(adithyaSandwich);
		SandwichPresenter presenter1 = new SandwichPresenter(adithyaSandwich);
		SandwichMaker maker1 = new SandwichMaker(assembler1,decorator1,presenter1);
		
		Thread threadA1 = new Thread(maker1);
		Thread threadA3 = new Thread(presenter1);
		Thread threadA2 = new Thread(decorator1);
		Thread threadA4 = new Thread(assembler1);
		
		threadA1.start();
		threadA2.start();
		threadA3.start();
		threadA4.start();
		
		/*Queue<String> queue = new PriorityQueue<String>();
		String[] jobs = new String[]{"com.pack.java.thread.SandwichMaker", "com.pack.java.thread.SandwichMaker","com.pack.java.thread.SandwichMaker","com.pack.java.thread.SandwichMaker","com.pack.java.thread.SandwichMaker",
				"com.pack.java.thread.SandwichPresenter","com.pack.java.thread.SandwichPresenter","com.pack.java.thread.SandwichPresenter","com.pack.java.thread.SandwichPresenter",
				"com.pack.java.thread.SandwichMaterialAssembler","com.pack.java.thread.SandwichMaterialAssembler","com.pack.java.thread.SandwichMaterialAssembler","com.pack.java.thread.SandwichMaterialAssembler","com.pack.java.thread.SandwichMaterialAssembler",
				"com.pack.java.thread.SandwichDecorator","com.pack.java.thread.SandwichDecorator","com.pack.java.thread.SandwichDecorator","com.pack.java.thread.SandwichDecorator","com.pack.java.thread.SandwichDecorator"};
		
		for(String tempJob : jobs) {
			queue.add(tempJob);
		}
		
		for(Thread tempThread : threads) {
			Class clazz;
			try {
				clazz = Class.forName(queue.poll());
				Runnable obj = (Runnable)clazz.newInstance();
				tempThread = new Thread(obj);
				tempThread.start();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}*/
	}
}

enum Veggie {
	CAPSICUM, CUCUMBER, JALAPENO, OLIVE, ONION, PICKLE, TOMATO;
}
