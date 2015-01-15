////////////////////////////////////////////////////////////////
// NibblesBox.java                                            //
// Written by, Sohail Qayum Malik[sqm@hackers.pk]			  //
// Last modified on, Sunday, 20th of February, 2011.          //
////////////////////////////////////////////////////////////////

package Nibbles;

import java.awt.Point;

import Nibbles.NibblesConstants;

public class NibblesBox {

   private Point pair;
   private int n, d, snake_width, snake_height, xpos_increment, ypos_increment;   
           
   public NibblesBox(int x, int y, int d, int snake_width, int snake_height, int xpos_increment, int ypos_increment, boolean flag) {
   
      pair = new Point(x, y);
	  
	  if (flag) {
	  
	     switch(d) {
		 
		    case NibblesConstants.DIRECTION_UP:
			case NibblesConstants.DIRECTION_DOWN:
			
			   n = snake_height / ypos_increment;
			   
			break;
			
			case NibblesConstants.DIRECTION_LEFT:
			case NibblesConstants.DIRECTION_RIGHT:
			
			   n = snake_width / xpos_increment;
			break;
		 }
	  }
	  else {
	  
	     switch(d) {
		 
		    case NibblesConstants.DIRECTION_UP:
			case NibblesConstants.DIRECTION_DOWN:
			
			   n = ypos_increment; 
			break;
			
			case NibblesConstants.DIRECTION_LEFT:
			case NibblesConstants.DIRECTION_RIGHT:
			
			   n = xpos_increment;
			break;
		 }
	  }
	  
	  this.d = d;	  	 
	  this.snake_width = snake_width;
	  this.snake_height = snake_height;
	  this.xpos_increment = xpos_increment;
	  this.ypos_increment = ypos_increment;
   }   
   
   public void increment() {
      
 	  switch (d) {
		    
         case NibblesConstants.DIRECTION_UP:
         case NibblesConstants.DIRECTION_DOWN:
			
			n = n + ypos_increment;
		 break;
			
		 case NibblesConstants.DIRECTION_LEFT:
		 case NibblesConstants.DIRECTION_RIGHT:
			
			n = n + xpos_increment;
		  break;					    
	  }       
   }
   
   //Yites! Java does not support default parameter arguments
   public void increment(int these_many) {
       
 	  switch (d) {
		    
         case NibblesConstants.DIRECTION_UP:
         case NibblesConstants.DIRECTION_DOWN:
			
			n = n + ypos_increment*these_many;
		 break;
			
		 case NibblesConstants.DIRECTION_LEFT:
		 case NibblesConstants.DIRECTION_RIGHT:
			
			n = n + xpos_increment*these_many;
		  break;					    
	  }         
   }
   
   public void decrement() {
   
      if (n != 0) {
	  
	     switch (d) {
		    
            case NibblesConstants.DIRECTION_UP:
            case NibblesConstants.DIRECTION_DOWN:
			
			   n = n - ypos_increment;
			break;
			
			case NibblesConstants.DIRECTION_LEFT:
			case NibblesConstants.DIRECTION_RIGHT:
			
			   n = n - xpos_increment;
			break;					    
		 }
	  }
   }
   

   public int getWidth() {
   
      int ret = 0;
   
      switch (d) {
	  
	     case NibblesConstants.DIRECTION_UP:
		 case NibblesConstants.DIRECTION_DOWN:
		 
		    ret = snake_width;
		 break;
		 
		 case NibblesConstants.DIRECTION_LEFT:
		 case NibblesConstants.DIRECTION_RIGHT:
		 
		    ret = n*xpos_increment;
		 break;
	  }

      return ret;	  
   }    
   
   public int getHeight() {
   
      int ret = 0;
   
      switch (d) {
	  
	     case NibblesConstants.DIRECTION_UP:
		 case NibblesConstants.DIRECTION_DOWN:
		 
		    ret = n*ypos_increment;
		 break;
		 
		 case NibblesConstants.DIRECTION_LEFT:
		 case NibblesConstants.DIRECTION_RIGHT:
		 
		    ret = snake_height;
		 break;
	  } 

      return ret;	  
   }
   
   public int getIncrement() {
   
      return n;
   }
   
   public void setDirection(int d) {
   
      this.d = d;
   }
   
   public int getDirection() {
   
      return d;
   }
   
   public int getX() {
   
      int ret = (int)pair.getX();
	  
	  if (d == NibblesConstants.DIRECTION_RIGHT)
	     ret = ret - getWidth();
   
      return ret;
   }
   
   public int getY() {
   
      int ret = (int)pair.getY();
	  
	  if (d == NibblesConstants.DIRECTION_DOWN)
	     ret = ret - getHeight();
		 
      return ret;		 
   }
   
   public void setXY(int x, int y) {
   
      pair.setLocation(x, y);   
   }      
};