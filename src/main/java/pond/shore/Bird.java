package pond.shore;

public class Bird {
	private Integer number;
	protected String text = "floating"; // protected access

	protected void floatInWater() { // protected access
		System.out.println(text);
	}

	public static void main(String[] args) {
		new Bird().testRuntimeException();
	}

	private void testRuntimeException() {
		try {
			System.out.println(Integer.valueOf(number));
		} catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
		}

		abstract class SaleTodayOnly {
			abstract int dollarsOff();
		}
		SaleTodayOnly sale = new SaleTodayOnly() {
			int dollarsOff() {
				return 3;
			}
		};

		final class LocalInner {

		}

		LocalInner inner = new LocalInner();
		MemberInner memberInner = new MemberInner();
	}

	final public class MemberInner {

	}
}

abstract class Animal {
	String name = "???";

	public void printName() {
		System.out.println(name);
	}
}

class Lion extends Animal {
	String name = "Leo";

	public void printName() {
		System.out.println(name);
	}
}

class PlayWithAnimal {
	public static void main(String... args) {
		System.out.println(null instanceof Object);
		Animal animal = new Lion();
		animal.printName();
	}
}

enum OnlyOne {
	ONCE(true);
	private OnlyOne(boolean b) {
		System.out.println("constructing");
	}

	public static void main(String[] args) {
		OnlyOne firstCall = OnlyOne.ONCE; // prints constructing
		OnlyOne secondCall = OnlyOne.ONCE; // doesn't print anything
	}
}

enum Season3 {
	WINTER {
		public void printHours() {
			System.out.println("short hours");
		}
	},
	SUMMER {
		public void printHours() {
			System.out.println("long hours");
		}
	},
	SPRING, FALL;

	public void printHours() {
		System.out.println("default hours");
	}
}

class AnonInner {
	interface SaleTodayOnly {
		int dollarsOff(int a);
	}

	public int pay() {
//		return admission(5, new SaleTodayOnly() {
//			public int dollarsOff() {
//				return 3;
//			}
//		});
		
		return admission(5, (a) -> a);
	}

	public int admission(int basePrice, SaleTodayOnly sale) {

		return basePrice - sale.dollarsOff(10);
	}
}

interface A {
	int test();
}

interface B {
	int test();
}

class C  {
	
	
	public static void main(String[] args) {
		C c = new C();
		c.testA(() -> 10);
		c.testB(() -> 20);
	}
	
	public int testA(A a) {
		return a.test();
	}
	
	public int testB(B b) {
		return b.test();
	}
	
	
}

