package pond.swan;

// DOES NOT COMPILE
import static java.util.Arrays.asList;

import pond.goose.Gosling;
import pond.shore.Bird;



public class Swan extends Bird { // but subclass of bird

	public void swim() {
		floatInWater(); // package access to superclass
		System.out.println(text); // package access to superclass
	}

	public void helpOtherSwanSwim() {
		Swan other = new Swan();
		other.floatInWater(); // package access to superclass
		System.out.println(other.text);// package access to superclass

		Gosling goose = new Gosling();

	}

	public void helpOtherBirdSwim() {
		Bird other = new Bird();
		// other.floatInWater(); // DOES NOT COMPILE
		// System.out.println(other.text); // DOES NOT COMPILE
	}

	public static void main(String[] args) {
		Koala k = new Koala();
		System.out.println(k.count); // k is a Koala
		k = null;
		System.out.println(k.count);
		
		asList("one"); // DOES NOT COMPILE
		fly(1, 2, 3);
		fly(new int [] {1, 2, 3,4});
	}
	
	public static void fly(int... numMiles) {
		System.out.println(numMiles.length);
	} // DOES NOT COMPILE
	
	public void fly(int numMiles) { }
	public void fly(Integer numMiles) { }
	
	public static void play(Long l) { }
	public static void play(Long... l) { }
}

class Koala {
	static int count;
}
