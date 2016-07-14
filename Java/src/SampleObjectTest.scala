//package com.pack.scala;

/**
 * @author Aditya.Narayana
 */
object SampleObjectTest {
  def main(args: Array[String]): Unit = {
    
    /*val sampleObjectTest = SampleObjectTest
    val sampleObjectTest2 = SampleObjectTest*/
    
    /*var intArray = Array[Int](20, 100, 50, 60, 90, 60, 10, 70)
    intArray = sampleObjectTest.sort(intArray);
    
     for ( x <- intArray ) {
         println( x )
      }*/
    
//    println(square(4))
    
    val list:List[Double]  = List(1.0,2.0,3.0);
    println(list.map { boolean })
  }

  def abs(x: Double): Double = {
    if (x >= 0) 
    {
      return x 
    } 
    else 
    {
      return -x
    }
  }
  
  def square(s: Double): Double = {
    s*s;
  }
  
  def squareAndMore(s: Double): Double = {
    s*s;
  }
  
  def boolean(s: Double): Boolean = {
    true;
  }

  def While(p: => Boolean)(s: => Unit): Unit = {
    if (p) { s; While(p)(s) }
    
  }

  def sort(xs: Array[Int]): Array[Int] = {
    if (xs.length <= 1) xs
    else {
      val pivot = xs(xs.length / 2)
      Array.concat(
        sort(xs filter (pivot >)),
        xs filter (pivot ==),
        sort(xs filter (pivot <)))
    }
  }
}