package com.pack.java.lambda.functionAsValues;

@FunctionalInterface
public interface FunctionOverTime {
	double valueAt(int time);
	
	static FunctionOverTime monthByMonth(final double[] values) {
		return time -> values[time - 1];
	}
	
	static FunctionOverTime constant(final double value) {
		return polynomial(new double[] { value });
	}
	
	static FunctionOverTime line(final double intercept, final double slope) {
		return polynomial(new double[] { intercept, slope });
	}
	
	static FunctionOverTime polynomial(final double[] coefficients) {
		return time -> {
			Double sum = 0.0;
			for(int i = 0 ;  i < coefficients.length ; i++) {
				sum += Math.pow(time, i) * coefficients[i];
			}
			
			return sum;
		};
	}
	
	@FunctionalInterface
	interface FunctionOf3 {
		double apply (double a, double b, double c);
		
	}

	static FunctionOverTime calculateProfit(final FunctionOverTime sales, final FunctionOverTime fixedCosts, final FunctionOverTime incrementalCosts, final FunctionOf3 f) {
		return time -> f.apply(sales.valueAt(time), fixedCosts.valueAt(time), incrementalCosts.valueAt(time));
	}
	
}

class ExampleFP {
	private static final double[] EXPECTED_SALES_JAN_TO_DEC = new double[] {
			42.0, 45.6, 43.6, 50.2, 55.6, 54.7, 58.0, 57.3, 62.0, 60.3, 71.2, 88.8
	};
	
	public static void main(final String[] args) {
		final FunctionOverTime sales = FunctionOverTime.monthByMonth(EXPECTED_SALES_JAN_TO_DEC);
		
		final FunctionOverTime fixedCosts = FunctionOverTime.constant(15);
		final FunctionOverTime incrementalCosts = FunctionOverTime.line(5.1, 0.15);
		
//		final FunctionOverTime profit = (time) -> sales.valueAt(time) - (fixedCosts.valueAt(time) + incrementalCosts.valueAt(time));
		final FunctionOverTime profit = FunctionOverTime.calculateProfit(sales, fixedCosts, incrementalCosts, (s, fc, ic) -> s + (fc + ic));
		
		Double totalProfit = 0.0;
		
		for(int time = 1; time <= 12 ; time++) {
			totalProfit += profit.valueAt(time);
		}
		
		System.out.println("Total profit is: " + totalProfit);
	}
}

interface QuantityOfInterest {
	double valueAt(int time);
	
	String getName();
}

class Sales implements QuantityOfInterest {

	private final FunctionOverTime valueFunction;
	
	public Sales(FunctionOverTime valueFunction) {
		this.valueFunction = valueFunction;
	}
	
	@Override
	public double valueAt(int time) {
		return valueFunction.valueAt(time);
	}

	@Override
	public String getName() {
		return "Sales";
	}
}

class FixedCosts implements QuantityOfInterest {

	private final FunctionOverTime valueFunction;
	
	public FixedCosts(FunctionOverTime valueFunction) {
		this.valueFunction = valueFunction;
	}
	
	@Override
	public double valueAt(int time) {
		return valueFunction.valueAt(time);
	}

	@Override
	public String getName() {
		return "Fixed Costs";
	}
}

class IncrementalCosts implements QuantityOfInterest {

	private final FunctionOverTime valueFunction;
	
	public IncrementalCosts(FunctionOverTime valueFunction) {
		this.valueFunction = valueFunction;
	}
	
	@Override
	public double valueAt(int time) {
		return valueFunction.valueAt(time);
	}

	@Override
	public String getName() {
		return "Incremental Costs";
	}
}

class Profit implements QuantityOfInterest {

	private final Sales sales;
	private final FixedCosts fixedCosts;
	private final IncrementalCosts incrementalCosts;
	
	public Profit(Sales sales, FixedCosts fixedCosts, IncrementalCosts incrementalCosts) {
		this.sales = sales;
		this.fixedCosts = fixedCosts;
		this.incrementalCosts = incrementalCosts;
	}
	
	@Override
	public double valueAt(int time) {
		return sales.valueAt(time) - (fixedCosts.valueAt(time) + incrementalCosts.valueAt(time));
	}
	
//	@Override
//	public double valueAt(int time) {
//		return FunctionOverTime.calculateProfit(sales, fixedCosts, incrementalCosts, (sales, fixedCosts, incrementalCosts) -> (sales - (fixedCosts + incrementalCosts)));
//	}

	@Override
	public String getName() {
		return "Profit";
	}
}

/**
 * We are ensuring type safety by having a class for each object i.e. Sales,
 * Fixed Costs and Incremental Costs which was lost in case of 'ExampleFP'. We
 * are also ensuring that the business logic for each class is present inside
 * each of its objects.
 * 
 * @author Aditya.Narayana
 *
 */
class ExampleFPWithOO {
	private static final double[] EXPECTED_SALES_JAN_TO_DEC = new double[] {
			42.0, 45.6, 43.6, 50.2, 55.6, 54.7, 58.0, 57.3, 62.0, 60.3, 71.2, 88.8
	};
	
	public static void main(final String[] args) {
		final Sales sales = new Sales( FunctionOverTime.monthByMonth(EXPECTED_SALES_JAN_TO_DEC) );
		
		final FixedCosts fixedCosts = new FixedCosts( FunctionOverTime.constant(15) );
		final IncrementalCosts incrementalCosts = new IncrementalCosts( FunctionOverTime.line(5.1, 0.15) );
		
		final Profit profit = new Profit(sales, fixedCosts, incrementalCosts );
		
		Double totalProfit = 0.0;
		
		for(int time = 1; time <= 12 ; time++) {
			totalProfit += profit.valueAt(time);
		}
		
		System.out.println("Total profit is: " + totalProfit);
	}
}