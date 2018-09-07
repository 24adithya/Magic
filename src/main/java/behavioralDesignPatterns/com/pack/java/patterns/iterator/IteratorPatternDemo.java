package com.pack.java.patterns.iterator;

import java.util.Iterator;

public class IteratorPatternDemo {

	public static void main(String[] args)
	{
		ExoticCarsRepository exoticCarRepository = new ExoticCarsRepository();
		exoticCarRepository.addCar("Bugatti Veyron Super Sport");
		exoticCarRepository.addCar("Koenigsegg Agera R");
		exoticCarRepository.addCar("Koenigsegg CCXR Trevita");
		
		Iterator<String> exoticCarIterator = exoticCarRepository.iterator();
		
		while(exoticCarIterator.hasNext())
		{
			String name = exoticCarIterator.next();
			System.out.println(name);
		}
		
		for (String name : exoticCarRepository)
		{
			System.out.println(name);
		}
	}
}

class ExoticCarsRepository implements Iterable<String>
{
	private String[] cars;
	private int index;
	
	public ExoticCarsRepository() {
		cars = new String[10];
		index = 0;
	}
	
	public void addCar(String name)
	{
		if(index == cars.length)
		{
			String[] additionalCars = new String[cars.length + 5];
			System.arraycopy(cars, 0, additionalCars, 0, cars.length);
			cars = additionalCars;
			additionalCars = null;
		}
		cars[index++] = name;
	}

	@Override
	public Iterator<String> iterator()
	{
		Iterator<String> exoticCarIterator = new Iterator<String>() {

			private int currentIndex;
			
			@Override
			public boolean hasNext()
			{
				return currentIndex < cars.length && cars[currentIndex] != null ;
			}

			@Override
			public String next()
			{
				return cars[currentIndex++];
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		};
		
		return exoticCarIterator;
	}
}