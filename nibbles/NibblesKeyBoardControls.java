////////////////////////////////////////////////////////////////
// NibblesKeyBoardControls.java                               //
// @author, Sohail Qayum Malik                                //
// Last modified on Saturday, 10th of November, 2012.         //
////////////////////////////////////////////////////////////////

package Nibbles;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.applet.AudioClip;
import Nibbles.NibblesConstants;

//Here the top level class should be public, it needs to be accessed out side of its package(no explicit modifier means package-private)
public class NibblesKeyBoardControls implements KeyListener{
       
   private int 				key; 

   //It has to be public, since this class is instantiated in file Nibbles.java and it is not part of package Nibbles
   public NibblesKeyBoardControls() {
   
      key = 0;
   }

   @Override                  
   public void keyReleased(KeyEvent e) {
   
      switch(e.getKeyCode()) {
	     case java.awt.event.KeyEvent.VK_UP:
         case java.awt.event.KeyEvent.VK_DOWN:
	     case java.awt.event.KeyEvent.VK_RIGHT:
	     case java.awt.event.KeyEvent.VK_LEFT:		 
	     break;
      }	  
   }

   @Override
   public void keyPressed(KeyEvent e) {
                             
      switch(e.getKeyCode()) {
	     
         case java.awt.event.KeyEvent.VK_UP:		 
    	    key = java.awt.event.KeyEvent.VK_UP;  
	     break;
	     case java.awt.event.KeyEvent.VK_DOWN:
	        key	= java.awt.event.KeyEvent.VK_DOWN;
         break;
	     case java.awt.event.KeyEvent.VK_RIGHT:
            key	= java.awt.event.KeyEvent.VK_RIGHT;
         break;
         case java.awt.event.KeyEvent.VK_LEFT:
	        key	= java.awt.event.KeyEvent.VK_LEFT;
	     break;
      }	  
   }
   
   @Override
   public void keyTyped(KeyEvent e) {        
   }
   
   public int getKey() {
   
      return key;
   }
   
   
   public int getDirection(int direction) {
            
      if (key != 0) {
   
         switch(key) {
	  
	        case java.awt.event.KeyEvent.VK_UP:
			
		       switch (direction) {
			   
			      case NibblesConstants.DIRECTION_LEFT:
                  case NibblesConstants.DIRECTION_RIGHT:
				  
				     direction = NibblesConstants.DIRECTION_UP;
                  break;				  
			   }
		    break;
		 
	        case java.awt.event.KeyEvent.VK_DOWN:
			
			   switch (direction) {
			   
			      case NibblesConstants.DIRECTION_LEFT:
				  case NibblesConstants.DIRECTION_RIGHT:
				     direction = NibblesConstants.DIRECTION_DOWN;
				  break;
			   }
            break;

	        case java.awt.event.KeyEvent.VK_RIGHT:
		       
		       switch (direction) {
			   
			      case NibblesConstants.DIRECTION_UP:
                  case NibblesConstants.DIRECTION_DOWN:				  
				     direction = NibblesConstants.DIRECTION_RIGHT;
                  break;				  
               }			   
            break;

            case java.awt.event.KeyEvent.VK_LEFT:		 
			
			   switch (direction) {
			   
			      case NibblesConstants.DIRECTION_UP:
				  case NibblesConstants.DIRECTION_DOWN:				  
				     direction = NibblesConstants.DIRECTION_LEFT;
				  break;
			   }
		    break;		 
	     }
		 
		 //key = 0;
	  } 
	  	  
	  return direction;
   }

   public void resetKey() {

      key = 0;
   }
                   
}; //End of public class
