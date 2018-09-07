package com.pack.java;

import java.io.IOException;

public class RandomTest {

	public static void main(String[] args)
	{
		String o = "-";
		switch ("FRED".toLowerCase().substring(1, 3))
		{
		case "yellow":
			o += "y";
		case "red":
			o += "r";
		case "green":
			o += "g";
		}
		System.out.println(o);
	}

}

class Plane {
	static String s = "-";

	public static void main(String[] args)
	{
		new Plane().s1();
		System.out.println(s);
	}

	void s1()
	{
		try
		{
			s2();
		}
		catch (Exception e)
		{
			s += "c";
		}
	}

	void s2() throws Exception
	{
		s3();
		s += "2";
		s3();
		s += "2b";
	}

	void s3() throws Exception
	{
		throw new Exception();
	}
}

class Flip2 {
	public static void main(String[] args)
	{
		String o = "-";
		String[] sa = new String[4];
		for (int i = 0; i < args.length; i++)
			sa[i] = args[i];
		for (String n : sa)
		{
			switch (n.toLowerCase())
			{
			case "yellow":
				o += "y";
			case "red":
				o += "r";
			case "green":
				o += "g";
			}
		}
		System.out.print(o);
	}
}

class Loopy {
	public static void main(String[] args)
	{
		int[] x = { 7, 6, 5, 4, 3, 2, 1 };
		// insert code here
		for (int y : x)
		{
			System.out.print(y + " ");
		}
	}

}

class Emu {
	static String s = "-";

	public Emu() {
		System.out.println("cons");
	}

	{
		System.out.println("normal 1");
	}

	static
	{
		System.out.println("static 1");
	}

	public static void main(String[] args)
	{
		Emu emu = new Emu();
		try
		{
			throw new Exception();
		}
		catch (Exception e)
		{
			try
			{
				try
				{
					throw new Exception();
				}
				catch (Exception ex)
				{
					s += "ic ";
				}
				throw new Exception();
			}
			catch (Exception x)
			{
				s += "mc ";
			}
			finally
			{
				s += "mf ";
			}
		}
		finally
		{
			s += "of ";
		}
		System.out.println(s);
	}

	{
		System.out.println("normal 2");
	}

	static
	{
		System.out.println("static 2");
	}

	{
		System.out.println("normal 3");
	}
}

class Ebb {
	static int x = 7;

	public static void main(String[] args)
	{
		String s = "";
		for (int y = 0; y < 3; y++)
		{
			x++;
			switch (x)
			{
			case 8:
				s += "8 ";
			case 9:
				s += "9 ";
			case 10:
			{
				s += "10 ";
				break;
			}
			default:
				s += "d ";
			case 13:
				s += "13 ";
			}

		}
		System.out.println(s);
	}

	static
	{
		x++;
	}
}

class Infinity {
}

class Beyond extends Infinity {
	static Integer i;

	public static void main(String[] args)
	{
		int sw = 2;// (int)(Math.random() * 3);
		switch (sw)
		{
		case 0:
		{
			for (int x = 10; x > 5; x++)
				if (x > 10000000)
					x = 10;
			break;
		}
		case 1:
		{
			int y = 7 * i;
			break;
		}
		case 2:
		{
			Infinity inf = new Beyond();
			Beyond b = (Beyond) inf;
		}
		}
	}
}

class OverAndOver {
	static String s = "";

	public static void main(String[] args)
	{
		try
		{
			s += "1";
			throw new Exception();
		}
		catch (Exception e)
		{
			s += "2";
		}
		finally
		{
			s += "3";
			doStuff();
			s += "4";
		}
		System.out.println(s);
	}

	static void doStuff()
	{
		int x = 0;
		int y = 7 / x;
	}
}

class Gotcha {
	public static void main(String[] args)
	{
		// new Gotcha().go();
		// insert code here
		try
		{
			new Gotcha().go();
		}
		catch (Error e)
		{
			System.out.println("ouch");
		}

		try
		{
			new Gotcha().go();
		}
		catch (Exception e)
		{
			System.out.println("ouch");
		}
	}

	void go()
	{
		go();
	}
}

class Frisbee {
	// insert code here
	public static void main(String[] args) throws RuntimeException
	{
		int x = 0;
		System.out.println(7 / x);
	}
}

class MyException extends Exception {
}

class Tire {
	void doStuff()
	{
	}
}

class Retread extends Tire {
	public static void main(String[] args)
	{
		new Retread().doStuff();
	}

	// insert code here
	void doStuff() throws ArithmeticException
	{
		System.out.println(7 / 0);
	}
}
