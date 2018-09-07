package com.pack.java.thread.communication;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WaitNotifyTest {

	public static void main(String[] args) {
		List<String> alphabets = new LinkedList<>();
		AlphabetHolder alphabetHolder = new AlphabetHolder(alphabets);

//		Thread consumer = new Thread(new ListConsumer(alphabetHolder), "Consumer");
//		Thread producer = new Thread(new ListProducer(alphabetHolder), "Producer");
		
		List<Integer> numbers = new LinkedList<>();
		NumberHolder numberHolder = new NumberHolder(numbers);
		
		Thread consumer = new Thread(new NumberConsumer(numberHolder), "Consumer");
		Thread producer = new Thread(new NumberProducer(numberHolder), "Producer");

		consumer.start();
		producer.start();
	}
}

class AlphabetHolder {
	private List<String> alphabets;
	private boolean isProductionComplete = false;
	private boolean isConsumptionComplete = false;

	public AlphabetHolder(List<String> alphabets) {
		this.alphabets = alphabets;
	}

	public void produceAlphabet() {
		if (alphabets.size() == 0) {
			for (char ch = 'A'; ch <= 'Z'; ch++) {
				alphabets.add(Character.toString(ch));

				if (ch == 'Z') {
					isProductionComplete = true;
				}
			}
		}
	}

	public void consumeAlphabet() {
		if (alphabets.size() > 0) {
			for (String alphabet : alphabets) {
				System.out.println(Thread.currentThread().getName() + " consumed : " + alphabet);
				
				if (alphabet.equals("Z")) {
					isConsumptionComplete = true;
				}
			}
		}
	}

	public boolean isProductionComplete() {
		return isProductionComplete;
	}

	public void setProductionComplete(boolean isProductionComplete) {
		this.isProductionComplete = isProductionComplete;
	}

	public boolean isConsumptionComplete() {
		return isConsumptionComplete;
	}

	public void setConsumptionComplete(boolean isConsumptionComplete) {
		this.isConsumptionComplete = isConsumptionComplete;
	}
}

class NumberHolder {
	private List<Integer> numbers;
	private boolean isProductionComplete = false;
	private boolean isConsumptionComplete = false;

	public NumberHolder(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public void produceNumber() {
		if (numbers.size() == 0) {
			for (int i = 0; i <= 100; i++) {
				numbers.add(i);
				System.out.println(Thread.currentThread().getName() + " produced : " + i);

				if(i % 10 == 0) {
					notify();
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (i == 100) {
					isProductionComplete = true;
				} 
			}
		}
	}

	public void consumeNumber() {
		if (numbers.size() > 0) {
			Iterator<Integer> iterator = numbers.iterator();
			Integer temp = null;
			while (iterator.hasNext()) {
				temp = iterator.next();
				System.out.println(Thread.currentThread().getName() + " consumed : " + temp);
				iterator.remove();
				
				if (temp.equals(100)) {
					isConsumptionComplete = true;
					notify();
				}  
			}
		} else {
			notify();
		}
	}

	public boolean isProductionComplete() {
		return isProductionComplete;
	}

	public void setProductionComplete(boolean isProductionComplete) {
		this.isProductionComplete = isProductionComplete;
	}

	public boolean isConsumptionComplete() {
		return isConsumptionComplete;
	}

	public void setConsumptionComplete(boolean isConsumptionComplete) {
		this.isConsumptionComplete = isConsumptionComplete;
	}
}

class ListProducer implements Runnable {

	private AlphabetHolder alphabetHolder;

	public ListProducer(AlphabetHolder alphabetHolder) {
		this.alphabetHolder = alphabetHolder;
	}

	@Override
	public void run() {
		produceListObjects();
	}

	private void produceListObjects() {
		synchronized (alphabetHolder) {
			while (!alphabetHolder.isProductionComplete()) {
				alphabetHolder.produceAlphabet();
				alphabetHolder.notify();
			}
		}
	}
}

class NumberProducer implements Runnable {

	private NumberHolder numberHolder;

	public NumberProducer(NumberHolder numberHolder) {
		this.numberHolder = numberHolder;
	}

	@Override
	public void run() {
		produceListObjects();
	}

	private void produceListObjects() {
		synchronized (numberHolder) {
			while (!numberHolder.isProductionComplete()) {
				numberHolder.produceNumber();
//				numberHolder.notify();
			}
		}
	}
}

class ListConsumer implements Runnable {

	private AlphabetHolder alphabetHolder;

	public ListConsumer(AlphabetHolder alphabetHolder) {
		this.alphabetHolder = alphabetHolder;
	}

	@Override
	public void run() {
		consumeListObjects();
	}

	private void consumeListObjects() {

		synchronized (alphabetHolder) {
			try {
				while (!alphabetHolder.isConsumptionComplete()) {
					alphabetHolder.wait(1l);
					alphabetHolder.consumeAlphabet();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class NumberConsumer implements Runnable {

	private NumberHolder numberHolder;

	public NumberConsumer(NumberHolder numberHolder) {
		this.numberHolder = numberHolder;
	}

	@Override
	public void run() {
		consumeListObjects();
	}

	private void consumeListObjects() {

		synchronized (numberHolder) {
			try {
				while (!numberHolder.isConsumptionComplete()) {
					numberHolder.wait(1l);
					numberHolder.consumeNumber();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
