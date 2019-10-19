//mouseMovementControls.java
//Wtitten by, Sohail Qayum Malik

package Hanoi;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class mouseMovementControls implements MouseMotionListener{

   //Default value of int is 0
   int x, y;

   public mouseMovementControls() {
   }

   public void mouseMoved(MouseEvent event) {

      x = event.getX();
      y = event.getY();      
   }

   public void mouseDragged(MouseEvent event) {
   }   


   public int getX() {
  
      return x; 
   }

   public int getY() {

      return y;
   }
       
};