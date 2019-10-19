//Hanoi.java
//Written by, Sohail Qayum Malik

//This applet won't be using the javax.swing GUI components inherited by javax.swing.JApplet
//This applet will do all its graphics rendring usging java.awt.Graphics libraries 
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;

//Local packages. In Java every class is in a package
//All required mouse related events will be handled by the following classes and passed on to this applet 
//by the instances of the handling classes
import Hanoi.mouseClickControls;
import Hanoi.mouseMovementControls;

//It maintains the main game loop and the whole state of the game
//Then it finally draws the current state of the game(on the game board), when the repaint() function is called
import Hanoi.gameBoard;

public class Hanoi extends Applet implements Runnable {
  
   AudioClip	   click_audio;
   AudioClip	   move_audio;
  
   Graphics	   	   graphics;
 
   mouseClickControls      hanoiMouseClickControls;
   mouseMovementControls   hanoiMouseMovementControls;   
   gameBoard		   	   hanoiGameBoard;
   
   Thread          		   hanoiThread;

   boolean game_is_on;    
  
   //init() acts like a constructor of an applet
   //It executes when the applet is loaded for the first time or it is reloaded
   public void init() {

      int numberOfSlabs;  
	  
	  click_audio				 = getAudioClip(getCodeBase(), "0.au");
	  move_audio			     = getAudioClip(getCodeBase(), "gong.au");
	  	  
//	  hanoiMouseClickControls    = new mouseClickControls(click_audio);
	  hanoiMouseClickControls	 = new mouseClickControls();
      hanoiMouseMovementControls = new mouseMovementControls();

      //Get the number of slabs in a tower, this should be provided by the user      
      //java.lang.String, the classes of java.lang package get imported automatically
      String numberOfSlabs_str = getParameter("NUMBEROFSLABS");
      //If parameter does not exit, the null is retured by getparameter() 
      if(numberOfSlabs_str != null) {
         try {
       
            //java.lang.Integer, all classes of java.lang package are automaticlly imported
            numberOfSlabs = Integer.parseInt(numberOfSlabs_str); 
         }
         catch(NumberFormatException e) {

            //Default number of slabs
            numberOfSlabs = 7;  
         } 
      }
      else
         numberOfSlabs = 7;

      //getHeight(), getWidth() are the abstract functions of java Component class(It is one of the ancestors of Applet) 
      hanoiGameBoard             = new gameBoard(numberOfSlabs, getHeight() - 1, getWidth() - 1);
    
      //Registering the mouse event handlers/controls
      addMouseListener(hanoiMouseClickControls);
      addMouseMotionListener(hanoiMouseMovementControls);

      //Applet class is a descendent of java.awt.Component class
      //getGraphics() is the public function of java.awt.Component class
      //I've tested, even after calling the repaint this graphic context does not go out of context
      graphics = getGraphics(); 

      //The main game loop
      //I wanted to name it heat_is_on(One of my favorite 80's song, by Mr. Glen Frey)
      game_is_on = true;      
   } 

   //Sort of main() function
   //When applet is loaded for the first time or when applet is reviseted
   public void start() {
   
   
	  if(hanoiThread == null) 
	     hanoiThread  = new Thread(this);   
		 
	  hanoiThread.start();	          
   }

   //When the start() function ends or when the page carrying the applet is left
   public void stop() {
   }

   //When ever repaint() function is called
   public void paint(Graphics g) {

      if(game_is_on) {  
         hanoiGameBoard.makeMove(g);
		 move_audio.play(); 
	  }	 
      hanoiGameBoard.drawState(g);
   }
      
   @Override
   public void run() {
              
      //Yep, I've found the way to get the mention of one of my favorite songs
      while((game_is_on=hanoiGameBoard.heat_is_on())) {           
         
         while(hanoiMouseClickControls.notClicked());
       		click_audio.play(); 
         hanoiGameBoard.determineTower(hanoiMouseClickControls.getX(), hanoiMouseClickControls.getY());
         hanoiMouseClickControls.clickReset();
         while(hanoiMouseClickControls.notClicked()) {
                
            hanoiGameBoard.drawPath(graphics, hanoiMouseMovementControls.getX(), hanoiMouseMovementControls.getY());                                      
         }
		 click_audio.play();     
         hanoiGameBoard.determineTower(hanoiMouseClickControls.getX(), hanoiMouseClickControls.getY());         
         hanoiMouseClickControls.clickReset();

         //Finally the new or the current state of the gameboard is drawn...
         repaint();             
      }         
   }
}; //The encapsulating class ends here