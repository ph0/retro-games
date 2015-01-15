////////////////////////////////////////////////////////////////////////
// NibblesFunctions.java, does most of software rasterization         //
// Written by, Sohail Qayum Malik[sqm@hackers.pk]                     //
// Last modified on, Monday, 7th of March, 2010 @7:12AM               //
////////////////////////////////////////////////////////////////////////

package Nibbles;

import java.lang.Math;
import java.awt.Graphics;

import Nibbles.NibblesGraphicsFunctions;

// The NibblesGraphicsFunctions is empty, later on I'll move basic rasterization entities like lines and circles renderers to this parent class(Just a notion for the moment)
public class NibblesFunctions extends NibblesGraphicsFunctions {


   public NibblesFunctions() {

      //Not needed since the parent's constructor is default(not parametarized)   
      super();
   }
   
   //Bresenham circle
   //Read the following book at page 29...
   //http://books.google.com.pk/books?id=7gT1MhI1SbIC&pg=PP3&dq=%2B%22Computer+Graphics+%2B%22SCHAUM's+outline+series%22&cd=1#v=onepage&q=%20%22Computer%20Graphics%20%20%22SCHAUM's%20outline%20series%22&f=false 
   // a and b is an origin ordinate pair(a,b)
   // r is the radius                                   
   public static void drawCircle(Graphics graphics, int a, int b, int r) {
 
      //We'll start at the top of the circle
	  //First point is always on the circle so error is zero and we know that x is zero and y is r       	  
      int x = 0, y = r, d = 3 - 2*r;
 
      // x is initially zero, x will be same as y at 45(degree) angle
      while(x <= y) {
         
		 // Eight way symmetry of circle
         graphics.drawString(".", y + a, x + b); 
         graphics.drawString(".", x + a, y + b);  
         graphics.drawString(".", x + a, (-1)*y + b); 
         graphics.drawString(".", y + a, (-1)*x + b); 
         graphics.drawString(".", (-1)*y + a, (-1)*x + b);
         graphics.drawString(".", (-1)*x + a, (-1)*y + b);
         graphics.drawString(".", (-1)*x + a, y + b);
         graphics.drawString(".", (-1)*y + a, x + b);
       
         if(d < 0) // move right
            d = d + 4*x + 6;
         else { // move down
            d = d + 4*(x - y) + 10;
			//Since we've started at the top of the circle
            y = y - 1;
         }
         
		 // Since we have started at top of the circle
         x = x + 1;                                
      }      
   }
      
   //Bresenham line
   //Read chapter 3 at page 28 of the following book   
   //http://books.google.com.pk/books?id=7gT1MhI1SbIC&pg=PP3&dq=%2B%22Computer+Graphics+%2B%22SCHAUM's+outline+series%22&cd=1#v=onepage&q=%20%22Computer%20Graphics%20%20%22SCHAUM's%20outline%20series%22&f=false
   //I also went through following two documents
   //http://cs.fit.edu/~wds/classes/graphics/Rasterize/rasterize/rasterize.html
   //http://en.wikipedia.org/wiki/Bresenham's_line_algorithm
   public static void drawLine(Graphics graphics, int x1, int y1, int x2, int y2)  {
      
	  int x, y, dx, dy, d, ystep, tmp;

	  //This algorithm only deals with lines having shallow slopes. When a line has steep slope then we take the advantage of the fact that steep line can be reflected across the line y = x
      boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1); 	  

      //Yes line has steep slope make it shallow	  
	  if(steep) {
	  
	     //swap(x1, y1) 
		 //Because Java for scalar types is pass by value
	     tmp = y1;
         y1 = x1;
         x1 = tmp;
		 
		 //swap(x2, y2)
		 //Because Java for scalar types is pass by value
		 tmp = y2;
		 y2 = x2;
		 x2 = tmp;        		 	  	    
	  }
	  
	  //We always move from left to right(that is x is always incremented)
	  if(x1 > x2) {
	  
	    //swap(x1, x2);
		//Because Java for scalar types is pass by value
		tmp = x2;
		x2 = x1;
		x1 = tmp;
		
		//swap(y1, y2)
		//Because Java for scalar types is pass by value
		tmp = y2;
		y2 = y1;
		y1 = tmp;
	  }
	  
	  dx = x2 - x1;	  
	  dy = Math.abs(y2 - y1);
	  //Initial value, the first and the last points are always on the line, so error is zero(2e=2(0)=0)
	  //e = dyX - dxY + c
	  //eR = dy(X + 1) - dxY + c = e + dy
	  //eD = dy(X + 1) - dx(Y + 1) + c = e + dy - dx
	  //d = eR + eD
	  d = 2*dy - dx;
	  
	  //Find out if we'll increment or decrement y
 	  if(y1 < y2) 
	     ystep = 1;
	  else
         ystep = -1;

      //Initial values(initial ordinate pair)		 
	  x = x1;
      y = y1;
	  
	  while(x <= x2) {
	  	 
         //x is reflected as y(transitive) 		 
	     if(steep)
		    graphics.drawString(".", y, x);
	     else
            graphics.drawString(".", x, y);		   
		
         //We only allow two moves, move to the right, or move diagonally. when we move to the right we only increment x otherwise we increment both(sign of ystep)
	     if(d < 0) 
            d = d + 2*dy;
         else {
		    
			d = d + 2*dy - 2*dx;
            y = y + ystep;			
         }

         x = x + 1; 		 		    
	  }	  		 	  	  	  	  	  	     
   }
         
   //Bresenham line
   //Read chapter 3 at page 28 of the following book   
   //http://books.google.com.pk/books?id=7gT1MhI1SbIC&pg=PP3&dq=%2B%22Computer+Graphics+%2B%22SCHAUM's+outline+series%22&cd=1#v=onepage&q=%20%22Computer%20Graphics%20%20%22SCHAUM's%20outline%20series%22&f=false
   //I also went through following two documents
   //http://cs.fit.edu/~wds/classes/graphics/Rasterize/rasterize/rasterize.html
   //http://en.wikipedia.org/wiki/Bresenham's_line_algorithm
   public static void drawLine_head(Graphics graphics, int x1, int y1, int x2, int y2)  {
      
	  int x, y, dx, dy, d, ystep, tmp;

	  //This algorithm only deals with lines having shallow slopes. When a line has steep slope then we take the advantage of the fact that steep line can be reflected across the line y = x
      boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1); 	  

      //Yes line has steep slope make it shallow	  
	  if(steep) {
	  
	     //swap(x1, y1) 
		 //Because Java for scalar types is pass by value
	     tmp = y1;
         y1 = x1;
         x1 = tmp;
		 
		 //swap(x2, y2)
		 //Because Java for scalar types is pass by value
		 tmp = y2;
		 y2 = x2;
		 x2 = tmp;        		 	  	    
	  }
	  
	  //We always move from left to right(that is x is always incremented)
	  if(x1 > x2) {
	  
	    //swap(x1, x2);
		//Because Java for scalar types is pass by value
		tmp = x2;
		x2 = x1;
		x1 = tmp;
		
		//swap(y1, y2)
		//Because Java for scalar types is pass by value
		tmp = y2;
		y2 = y1;
		y1 = tmp;
	  }
	  
	  dx = x2 - x1;	  
	  dy = Math.abs(y2 - y1);
	  //Initial value, the first and the last points are always on the line, so error is zero(2e=2(0)=0)
	  //e = dyX - dxY + c
	  //eR = dy(X + 1) - dxY + c = e + dy
	  //eD = dy(X + 1) - dx(Y + 1) + c = e + dy - dx
	  //d = eR + eD
	  d = 2*dy - dx;
	  
	  //Find out if we'll increment or decrement y
 	  if(y1 < y2) 
	     ystep = 1;
	  else
         ystep = -1;

      //Initial values(initial ordinate pair)		 
	  x = x1;
      y = y1;
	  
	  while(x <= x2) {
	  	 
         //x is reflected as y(transitive) 		 
	     if(steep)
		    graphics.drawString("*", y, x);
	     else
            graphics.drawString("*", x, y);		   
		
         //We only allow two moves, move to the right, or move diagonally. when we move to the right we only increment x otherwise we increment both(sign of ystep)
	     if(d < 0) 
            d = d + 2*dy;
         else {
		    
			d = d + 2*dy - 2*dx;
            y = y + ystep;			
         }

         x = x + 1; 		 		    
	  }	  		 	  	  	  	  	  	     
   }


   // Trigonometric method
   // a = length of major axis, b = length of minor axis
   // h,k ordinate pair for the center of the ellipse
   // x = a * cos(0 to PI/2 radians) + h 
   // y = b * sin(0 to PI/2 radians) + k
   // Inorder to rotate on axis, make minor greater than major
   public static void drawEllipse(Graphics graphics, int h, int k, int a, int b) {      
   
      int x = 0, y = 0; 
	  
	  //i is the magnitude of increment to radian at each step, this should not be fixed as it is now
	  double radian = 0, i = 0.01;

	  while(radian <= Math.PI/2) {
	     	  		 		 	    
         x = (int)(a*(Math.cos(radian)));
		 y = (int)(b*(Math.sin(radian)));
		 
		 //Ellipses have 4 way symmetry
		 graphics.drawString(".", x + h, y + k);	  
	     graphics.drawString(".", (-1)*x + h, y + k);	 
	     graphics.drawString(".", (-1)*x + h, (-1)*y + k);	 
         graphics.drawString(".", x + h, (-1)*y + k);		 

         radian = radian + i;		 		 
	  }	  	  	  	  	     
   }   
     
   // It is easy, no special algorithm there, just draw four lines
   public static void drawRectangle(Graphics graphics, int x1, int y1, int width, int height) {
   
      drawLine(graphics, x1, y1, x1 + width, y1);
      drawLine(graphics, x1, y1 + height, x1 + width, y1 + height);
      drawLine(graphics, x1, y1, x1, y1 + height);
      drawLine(graphics, x1 + width, y1, x1 + width, y1 + height); 	     
   }

   
      // It is easy, no special algorithm there, just draw four lines
   public static void drawRectangle_special(Graphics graphics, int x1, int y1, int width, int height) {
   
      drawLine_head(graphics, x1, y1, x1 + width, y1);
      drawLine_head(graphics, x1, y1 + height, x1 + width, y1 + height);
      drawLine_head(graphics, x1, y1, x1, y1 + height);
      drawLine_head(graphics, x1 + width, y1, x1 + width, y1 + height); 	     
   }
   
   
   /* No not the right way, should've used flood fill. I will implement it later, don't need it now */
   public static void fillRectangle(Graphics graphics, int x1, int y1, int width, int height) {
   
   	  int x, y;
	  
	  if(width < 2 || height < 2) {
	  
	     drawRectangle(graphics, x1, y1, width, height);
		 return;
	  }
	  
	  for(y = 0; y < height + 1; y++)
	     for(x = 0; x < width + 1; x++) 	     
            graphics.drawString(".", x1 + x, y1 + y);  		 	  	  	  	  	  
   }
   
   /* No not the right way, should've used flood fill. I will implement it later, don't need it now */
   public static void fillCircle(Graphics graphics, int a, int b, int r) {
   
      int r1;
	  
	  for(r1 = r; r1 > 0; r1--)	  
	     drawCircle(graphics, a, b, r1); 	  	  
   }           
};