//mouseClickControls.java
//Written by, Sohail Qayum Malik

package Hanoi;

//import java.applet.AudioClip;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class mouseClickControls implements MouseListener{

   //It defaults to false   
   boolean 	clicked;

   //int defaults to 0
   int x, y;
   
//   AudioClip	   			click_audio;

//   public mouseClickControls(AudioClip click_audio) {

   public  mouseClickControls() {

       
//	  this.click_audio = click_audio; 
   
      //Default value of boolean is false  
      clicked = true;
   }
       
 
   public void mouseExited(MouseEvent event) {
   }
 
   public void mouseEntered(MouseEvent event) {
   
   
//      click_audio.play(); 
   }

   public void mouseReleased(MouseEvent event) {

   }

   public void mousePressed(MouseEvent event) {
   
//       click_audio.play(); 
   }

   public void mouseClicked(MouseEvent event) {     
   
   
//      click_audio.play(); 
	  
      x = event.getX();
      y = event.getY();

      clicked = false; 
   }

   public boolean notClicked() {


      return clicked;
   }

   public void clickReset() {

      clicked = true;  
   }

   public int getX() {

      return x; 
   }

   public int getY() {

      return y;
   }
       
};