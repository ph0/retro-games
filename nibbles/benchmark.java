////////////////////////////////////////////////////////////////
// benchmark.java, very poor way to benchmark                 //
// @author, Sohail Qayum Malik[sqm@hackers.pk]                //
// Last modified on Saturday, 10th of November, 2012.         //
////////////////////////////////////////////////////////////////
package benchmark;

import java.util.Date;

public class benchmark {

   double benchmark_seconds;

   public benchmark ( int x, int y, int z, int passes ) {
   
      Date d1 = new Date();
	  
	  for ( int i = 0; i < passes; i++ )
	     Tak ( x, y, z ); 
	  
	  Date d2 = new Date();
	  
	  benchmark_seconds = ( d2.getTime() - d1.getTime() )/1000;
   }
         
   public benchmark ( float x, float y, float z, int passes ) {
   
      Date d1 = new Date();
	  
	  for ( int i = 0; i < passes; i++ )
	     Tak ( x, y, z ); 
	  
	  Date d2 = new Date();   
	  
	  benchmark_seconds = ( d2.getTime() - d1.getTime() )/1000;
   }
   
   public double get_benchmark_seconds() {
   
      return benchmark_seconds;
	  //return 1000;
   }

   public static int Tak ( int x, int y, int z ) {
  
      if ( y >= x ) {
	
	     return z;
	  }   
      else {
	
	     return Tak ( Tak(x-1, y, z), Tak(y-1, z, x), Tak(z-1, x, y) );     
	  }   
   }
  
   public static float Tak ( float x, float y, float z ) {
  
      if (y >= x) {
	
	     return z;
	  }   
      else {
	
	     return Tak ( Tak(x-1.0f, y, z), Tak(y-1.0f, z, x), Tak(z-1.0f, x, y) );     
	  }   
   }
};