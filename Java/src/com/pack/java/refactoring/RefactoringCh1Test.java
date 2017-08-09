package com.pack.java.refactoring;

import java.util.Enumeration;
import java.util.Vector;

public class RefactoringCh1Test {

}

class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private String _title;
	private int _priceCode;

	public Movie(String title, int priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public int getPriceCode() {
		return _priceCode;
	}

	public void setPriceCode(int arg) {
		_priceCode = arg;
	}

	public String getTitle() {
		return _title;
	};
}

class Rental {
	private Movie _movie;
	private int _daysRented;

	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}
}

class Customer {
	private String _name;
	private Vector _rentals = new Vector();

	public Customer(String name) {
		_name = name;
	};

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentals.nextElement();
			// determine amounts for each line
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (each.getDaysRented() > 2)
					thisAmount += (each.getDaysRented() - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (each.getDaysRented() > 3)
					thisAmount += (each.getDaysRented() - 3) * 1.5;
				break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}
}

class MovieR {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private String _title;
	private Price _price;

	public MovieR(String title, int priceCode) {
		_title = title;
		setPriceCode(priceCode);
	}

	public int getPriceCode() {
		return _price.getPriceCode();
	}

	public void setPriceCode(int priceCode) {
		switch (priceCode) {
		case MovieR.REGULAR:
			_price = new RegularPrice();
			break;
		case MovieR.NEW_RELEASE:
			_price = new NewReleasePrice();
			break;
		case MovieR.CHILDRENS:
			_price = new ChildrensPrice();
			break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	public String getTitle() {
		return _title;
	}

	public double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}

	int getFrequentRenterPoints(int daysRented) {
		return _price.getFrequentRenterPoints(daysRented);
	}
}

interface Price {
	double getCharge(int daysRented);
	int getPriceCode();
	
	default int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}

class RegularPrice implements Price {
	public double getCharge(int daysRented) {
		double result = 2;
		if (daysRented > 2)
			result += (daysRented - 2) * 1.5;
		return result;
	}

	@Override
	public int getPriceCode() {
		return MovieR.REGULAR;
	}
}

class ChildrensPrice implements Price {
	public double getCharge(int daysRented) {
		double result = 1.5;
		if (daysRented > 3)
			result += (daysRented - 3) * 1.5;
		return result;
	}

	@Override
	public int getPriceCode() {
		return MovieR.CHILDRENS;
	}
}

class NewReleasePrice implements Price {
	public double getCharge(int daysRented) {
		return daysRented * 3;
	}

	@Override
	public int getPriceCode() {
		return MovieR.NEW_RELEASE;
	}
	
	public int getFrequentRenterPoints(int daysRented) {
		if(daysRented > 1) {
			return 2;
		}else {
			return Price.super.getFrequentRenterPoints(daysRented);
		}
	}
}

class RentalR {
	private MovieR _movie;
	private int _daysRented;

	public RentalR(MovieR movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	public MovieR getMovie() {
		return _movie;
	}

	public double getCharge() {
		return getMovie().getCharge(getDaysRented());
	}

	int getFrequentRenterPoints() {
		return getMovie().getFrequentRenterPoints(getDaysRented());
	}
}

class CustomerR {
	private String _name;
	private Vector _rentals = new Vector();

	public CustomerR(String name) {
		_name = name;
	};

	public void addRental(RentalR arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	};

	private double getTotalCharge() {
		double result = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			RentalR each = (RentalR) rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}

	private int getTotalFrequentRenterPoints() {
		int result = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			RentalR each = (RentalR) rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}

	public String statement() {
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			RentalR each = (RentalR) rentals.nextElement();
			// add frequent renter points
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}
}
