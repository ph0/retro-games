////////////////////////////////////////////////////////////////
// NibblesRandomEventGenerator.java                           //
// Written by, Sohail Qayum Malik                             //
// Last modified on Thursday, 25th of June, 2010.             //
////////////////////////////////////////////////////////////////

package Nibbles;

import java.util.Random;

import Nibbles.NibblesConstants;
//It is only for study purposes(1)
//import Nibbles.NibblesGraphicsFunctions;

public class NibblesRandomEventGenerator {

   private int 			radius, width_min, width, height_min, height, snake_width, snake_height; 
           
   private	Random		random;
   
   //It is only for study purposes(1)
//   NibblesGraphicsFunctions NGF_obj;
      
   public NibblesRandomEventGenerator(int radius, int width_min, int width, int height_min, int height, int snake_width, int snake_height) {
   
	  random = new Random();
	  
	  this.radius = radius;
	  // width_min, the abscissa of an ordered pair (x, y) for the inner box
	  this.width_min = width_min;
	  // height_min, the ordinate of an ordered pair (x, y) for the inner box
	  this.height_min = height_min;
	  // Width of the inner box. Width of the outer box is getWidth()
	  this.width = width;
	  // Height of the inner box. Height of the outer box is getHeight()
	  this.height = height;	  
	  
	  // Snake is made up of individual boxes
	  this.snake_width = snake_width;
	  this.snake_height = snake_height;
      
	  //It is only for study purposes(1)
      //What we are doing here is, we are augumenting the code base of a constructor which already exists	  
//      NGF_obj = new NibblesGraphicsFunctions(12) { };	  
   }  
   
   public int getX() {
   
      int x;
   	  
      x = random.nextInt(width - radius);
	  	  	  	  
	  if (x <=  width_min + radius)
	     x = getX();
      
      return x;	  	  
   }
   
   public int getY() {
   
      int y;

      y = random.nextInt(height - radius);
	  	  
	  if( y <= height_min + radius )
	     y = getY();
	  
      return y;	  
   } 
   
   public int getSnakeX() {
   
      int x;

      x = random.nextInt(width - snake_width);
	  if (x < width_min) 
	     x = getSnakeX(); 

      return x;	  
   }
   
   public int getSnakeY() {
   
      int y;
   
      y = random.nextInt(height - snake_height);
	  if (y < (height_min + snake_height))
	     y = getSnakeY();
   
      return y;
   }

   public int getSnakeInitialDirection(int x, int y) {
   
      int h, v, lR, uD, direction;
   	  
	  h = width_min + width - x;
	  v = height_min + height - y;
	  
	  lR = (width_min + width )/2;
	  uD = (height_min + height)/2;
	  	  
	  if ( h > v ) {	  	 
	     // We move horizontally		 		 		 
		 if ( x > lR) 
		    direction = NibblesConstants.DIRECTION_LEFT;
	     else
            direction = NibblesConstants.DIRECTION_RIGHT;		 	  
	  }
	  else {
	     // We move vertically
		 if ( y > uD )
		    direction = NibblesConstants.DIRECTION_UP;
	     else
            direction = NibblesConstants.DIRECTION_DOWN;		 
	  }
	  	  	  	  	         
      return direction; 	  
   }

   public boolean eaten_morsel(int morsel_x, int morsel_y, int radius, int snake_x, int snake_y, int direction) {
   
      boolean ret = false;
	  
	  switch (direction) {
	  
	     case NibblesConstants.DIRECTION_UP:		   
		    if ( snake_x <= morsel_x && (snake_x + snake_width) >= morsel_x  && (snake_y - snake_height) <= morsel_y && snake_y >= morsel_y ) 			
			   ret = true;            					 			  
		 break;
		 
		 case NibblesConstants.DIRECTION_DOWN:		 		    
		    if ( snake_x <= morsel_x && (snake_x + snake_width) >= morsel_x  && snake_y >= morsel_y && (snake_y - snake_height) <= morsel_y )
			   ret = true; 			   
		 break;
		 
		 case NibblesConstants.DIRECTION_LEFT:			
			if ( snake_x <= morsel_x && (snake_x + snake_width) >= morsel_x && snake_y >= morsel_y && (snake_y - snake_height) <= morsel_y )
               ret = true;			
		 break;
		 
		 case NibblesConstants.DIRECTION_RIGHT:		 
			if ( snake_x <= morsel_x && (snake_x + snake_width) >= morsel_x && snake_y >= morsel_y && (snake_y - snake_height) <= morsel_y )
               ret = true;			
		 break;
	  }
	  
	  return ret;
   }   

   
   public boolean collided_to_walls_new(int x, int y, int direction, double n) {
   
      boolean ret = false;
	  
	  switch (direction) {
	  	  
	     case NibblesConstants.DIRECTION_UP:
		 
			if (y <= height_min)
			   ret = true;			 	 
		 break;
		 
		 case NibblesConstants.DIRECTION_DOWN:
		 
		    if ((y + snake_height*n) >= (height_min + height))	
			   ret = true;		    
		 break;
		 
		 case NibblesConstants.DIRECTION_LEFT:
		 
		    if (x <= width_min)
			   ret = true;		 
		 break;
		 
		 case NibblesConstants.DIRECTION_RIGHT:
		 
		    if ((x + snake_width*n) >= (width_min + width))
			   ret = true;		 
		 break;
      }	  
	  
	  return ret;
   }
   
   public boolean collided_to_walls(int x, int y, int direction) {
   
      boolean ret = false;
	  
	  switch(direction) {
	  
	     case NibblesConstants.DIRECTION_UP:
		 
		    if (y <= height_min)
			   ret = true;			   
		 break;
		 
		 case NibblesConstants.DIRECTION_DOWN:
	
            if ((y + snake_height) >= (height_min + height))	
			   ret = true;		    
		 break;
		 
		 case NibblesConstants.DIRECTION_LEFT:
		 
		    if (x <= width_min)
			   ret = true;
		 break;
		 
		 case NibblesConstants.DIRECTION_RIGHT:
		 
		    if ((x + snake_width) >= (width_min + width))
			   ret = true;
		 break;
	  }
	  
	  return ret;      
   }   
   
   public boolean increase_size() {
   
      boolean ret = false;
	  
	  int x = random.nextInt(width_min * height_min);
	  
	  if ((x % (width_min*height_min - 0)) == 0)
         ret = true;	  
	  	  				 		 	  	  	  
	  return ret;
   }      
};
