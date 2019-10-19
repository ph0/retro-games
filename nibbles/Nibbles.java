////////////////////////////////////////////////////////////////
// Nibbles.java                                               //
// @author, Sohail Qayum Malik                                //
// Last modified on Saturday, 10th of November, 2012.         //
////////////////////////////////////////////////////////////////

//The Applet class must be the superclass of any applet that is to be embedded
//in a Web page or viewed by the Java Applet Viewer. 
//The Applet class provides a standard interface between applets and their environment.
import java.applet.Applet;

import java.awt.*;
import java.awt.event.*;

import java.lang.Thread;

import java.util.LinkedList;
import java.util.Date;

import Nibbles.NibblesConstants;
import benchmark.benchmark;
import Nibbles.NibblesKeyBoardControls;
import Nibbles.NibblesRandomEventGenerator;
import Nibbles.NibblesBox;
//import Nibbles.NibblesGraphicsFunctions;
import Nibbles.NibblesFunctions;

//The top level class can be public or package-private(no explicit modifier means package-private). Here it should be public or else you'll see run time exceptions
public class Nibbles extends Applet implements Runnable, ActionListener {
  
   private	boolean						play;   
   private	int							height_max, height_min, width_max, width_min, radius, snake_height, snake_width, temp_x, temp_y, xpos_increment, ypos_increment, morsel_x, morsel_y, direction;
   
   private	Button						play_button;
   private	Thread						nibble;
   
   private  Image						db_image; 
   private  Graphics					db_graphics; 

   private 	benchmark					benchmark_obj; 
   private  double						benchmark_seconds;  

   private 	String 						number_of_passes_str;
   private  int 						number_of_passes_int;
   
   private	NibblesKeyBoardControls		kb_controls;
   private	NibblesRandomEventGenerator random_event;
   
   private 	LinkedList<NibblesBox>		snake;

      
   // ActionListener::actionPerformed() being implemented
   @Override
   public void actionPerformed(ActionEvent e) {
        
      play = true;
	  
	  remove( play_button );
	  
	  nibble.start();
	  
	  requestFocus();
   }
         
   //An applet constructor(Not really, the constructor is Nibbles() but its execution is not guaranteed)
   //It even executes each time I click the refresh button(true for IE8 and FF)
   @Override
   @SuppressWarnings("unchecked")   
   public void init() {
         
      height_max = getHeight();
	  width_max = getWidth();
	  
	  number_of_passes_str = getParameter("NUMBEROFPASSES");
	  
      if ( number_of_passes_str != null ) {
         try {
       
            //java.lang.Integer, all classes of java.lang package are automaticlly imported
            number_of_passes_int = Integer.parseInt( number_of_passes_str ); 
         }
         catch( NumberFormatException e ) {

            // Default number of passes(see file benchmark.java)
            number_of_passes_int = NibblesConstants.passes;  
         } 
      }
      else
         number_of_passes_int = NibblesConstants.passes; // Default number of passes(see file benchmark.java)

      radius = (NibblesConstants.DEFAULT_RADIUS*height_max)/NibblesConstants.DEFAULT_HEIGHT;	  		 
      // width_min, the abscissa of an ordered pair (x, y) for the inner box
	  width_min = (NibblesConstants.DEFAULT_FRAME_WIDTH*width_max)/NibblesConstants.DEFAULT_WIDTH;	  
	  // height_min, the ordinate of an ordered pair (x, y) for the inner box
	  height_min = (NibblesConstants.DEFAULT_FRAME_WIDTH*height_max)/NibblesConstants.DEFAULT_HEIGHT; 
	  
	  snake_height = (NibblesConstants.DEFAULT_BOX_HEIGHT*height_max)/NibblesConstants.DEFAULT_HEIGHT;
	  snake_width = (NibblesConstants.DEFAULT_BOX_WIDTH*width_max)/NibblesConstants.DEFAULT_WIDTH;
	     	
	  benchmark_obj = new benchmark(NibblesConstants.x_i, NibblesConstants.y_i, NibblesConstants.z_i, number_of_passes_int); 
      benchmark_seconds = benchmark_obj.get_benchmark_seconds(); 
   
      play_button = new Button("Click here to start Nibbles");	 
	  
	  setLayout(new BorderLayout());	  
      add(play_button, BorderLayout.SOUTH);

      play_button.addActionListener( this ); 	

      kb_controls = new NibblesKeyBoardControls(); 	 	  
	  random_event = new NibblesRandomEventGenerator(radius, width_min, width_max - width_min*2, height_min, height_max - height_min*2, snake_width, snake_height);

	  xpos_increment                = width_max/NibblesConstants.DEFAULT_WIDTH;
	  ypos_increment				= height_max/NibblesConstants.DEFAULT_HEIGHT;

      temp_x = random_event.getSnakeX();
	  temp_y = random_event.getSnakeY();
	  morsel_x = random_event.getX();
	  morsel_y = random_event.getY();
	  direction = random_event.getSnakeInitialDirection(temp_x, temp_y);	  
	  
	  snake = new LinkedList<NibblesBox>();	  	 	  
	  snake.add(new NibblesBox(temp_x, temp_y, direction, snake_width, snake_height, xpos_increment, ypos_increment, true));
	  	  
      addKeyListener(kb_controls);	  	  	  	  
   }

   //An applet (sort of)main(), don't consider it a C/C++ main(). Leave, as soon as you can
   //It will be executed each time the page is refreshed(true for IE8 and FF)
   @Override
   public void start() {
  	  	  	   
      if(nibble == null)
         nibble = new Thread(this);	  
		 
	  nibble.setPriority(Thread.MIN_PRIORITY);	 	  
   }

   //When browser window is closed(only true for IE8)
   @Override
   public void stop() {
	  
      //Stop the game thread
      play = false;  
   }
   
   //When browser window is closed(only true for FF)
   @Override
   public void destroy() {
    	 
	  play = false;	  
   }
      
   @Override
   synchronized public void run() {
   
      while ( play ) { 

	    /** 
		 * The repaint calls update(), update is in seperate thread. Each repaint() should be followed by a an update(). 
		 * Since the caller and calee are in seperate threads, the caller should wait for the callee to start and complete its work before the next call.
		 * it is a producer/consumer problem. The producer produces exactly one thing and waits for the consumer to come and consume it. Here consumer does not wait for the 
         * producer to produce. The producer(here called the caller) produces and waits. The consumer consumes and notifies the producer(the caller)
         * that it is ready to consume 
		 **/	
	     repaint();
		          
		 try {
		 
		    wait(); // wait till get notified... see the call to method notify() at the end of method update()                            
		 }
		 catch(InterruptedException e) {
		 }   	  
	  }	 
   }
   
   @Override 
   synchronized public void update(Graphics graphics) {
   
      if ( db_image == null ) {
	  
	     db_image = createImage(getWidth(), getHeight());
         db_graphics = db_image.getGraphics();
      }
	    	  	  
      paint(db_graphics);
	  
      graphics.drawImage(db_image, 0, 0, this);	
	  
	  /**  
	   * TODO, I'm hating this. Find a method more fluent and less visible 
	   **/
      try {
	  
	     /**
		  * TODO, the type of benchmark_seconds in double and here we are typecasting it to long. It is not great do some thing else here
 	      **/         
         nibble.sleep( /*  1 */ (long)benchmark_seconds );	  	  	  	  	  
      }
      catch( InterruptedException e ) {
      } 
	  	                               
      notify();
   }
   
   @Override
   public void paint(Graphics graphics) { 
   
      int i;

      graphics.setColor(NibblesConstants.BOARD_FRAME_COLOR); 
	  graphics.fillRect(0, 0, width_max, height_max);
	  
	  graphics.setColor(NibblesConstants.BOARD_COLOR);
	  graphics.fillRect(width_min, height_min, width_max - width_min*2, height_max - height_min*2);	  	  	  	     	  	 
	  
      graphics.setColor(NibblesConstants.CIRCLE_COLOR);	 
	  NibblesFunctions.fillCircle(graphics, morsel_x, morsel_y, radius);  
	  
      //Collision detection, when snake collides against any of the four walls 	  
      if (random_event.collided_to_walls(temp_x, temp_y, snake.get(0).getDirection())) {	  
	  
	     play = false;
	  }
	  else {
	  
	     //Programming is all about moving the nasty out of the way and putting it some where else. Sorry, this nasty has to be here, with little effort bit more abstraction can be found and that will be done in its own time 	 
	     //Collision detection, when snake collides against its own self	
         switch(snake.getFirst().getDirection()) {
		 
		    case NibblesConstants.DIRECTION_RIGHT:	
								
			   for (i = 1; i < snake.size(); i++) {			   
			      if ( (snake.getFirst().getY() >= snake.get(i).getY()) &&  (snake.getFirst().getY() < (snake.get(i).getY() + snake.get(i).getHeight())) ) {				  
				     if ((snake.getFirst().getX() + snake.getFirst().getWidth()) >= snake.get(i).getX()) {						   											  
				        //graphics.drawString("Right>", 100, 200);					 
                        play = false;														   
					    break;
					 }   	
                  }				  			   			   		
               } 
            break;  		    
			
			case NibblesConstants.DIRECTION_LEFT:
						   
			   for (i = 1; i < snake.size(); i++) {
				   if ( (snake.getFirst().getY() >= snake.get(i).getY()) &&  (snake.getFirst().getY() < (snake.get(i).getY() + snake.get(i).getHeight())) ) {				  
				      if ((snake.getFirst().getX() >=  snake.get(i).getX()) && (snake.getFirst().getX() <= (snake.get(i).getX() + snake.get(i).getWidth())) )  {						   											  
				         //graphics.drawString("Left", 100, 200);					  
                         play = false;														   
					     break;
					 }   	
                   }					   
               }			   
			break;
			
			case NibblesConstants.DIRECTION_UP:
						   
			   for (i = 1; i < snake.size(); i++) {
				  if ( (snake.getFirst().getY() < (snake.get(i).getY() + snake.get(i).getHeight())) && (snake.getFirst().getY() > snake.get(i).getY() ) ) {				  
				     if ( (snake.getFirst().getX() < (snake.get(i).getX() + snake.get(i).getWidth())) && (snake.getFirst().getX() > snake.get(i).getX())  )  {						   											  
					    //graphics.drawString("Up", 100, 200);
                        play = false;														   
					    break;
					 }   	
                   }					   
			   }			   
			break;
			
			case NibblesConstants.DIRECTION_DOWN:
						   
			   for (i = 1; i < snake.size(); i++) {			   
				  if ( ((snake.getFirst().getY() + snake.getFirst().getHeight()) >= snake.get(i).getY()) && ((snake.getFirst().getY() + snake.getFirst().getHeight()) <=  (snake.get(i).getY() + snake.get(i).getHeight())) ) {				  
				     if ( (snake.getFirst().getX() < (snake.get(i).getX() + snake.get(i).getWidth())) && (snake.getFirst().getX() > snake.get(i).getX())  )  {						   											  
					    //graphics.drawString("Down", 100, 200);
                        play = false;														   
					    break;
					 }   	
                   }			   
			   }
			break;
         }   		 			  
      }	  
	  	  
	  if (play) {
	  
	     graphics.setColor(NibblesConstants.SNAKE_COLOR);			 		 		 		 		 		 		 		
		 
		 //NibblesFunctions.drawRectangle_special(graphics, snake.get(0).getX(), snake.get(0).getY(), snake.get(0).getWidth(), snake.get(0).getHeight());
		 
		 for (i = 0; i < snake.size(); i++)
		    NibblesFunctions.drawRectangle(graphics, snake.get(i).getX(), snake.get(i).getY(), snake.get(i).getWidth(), snake.get(i).getHeight());
	  }
	  
	  // When snake collides with the morsel
	  if (random_event.eaten_morsel(morsel_x, morsel_y, radius, temp_x, temp_y, snake.get(0).getDirection())) {
	  
	     morsel_x = random_event.getX();
		 morsel_y = random_event.getY();
	  }	  
	  
	  switch(direction) {
	  
	     case NibblesConstants.DIRECTION_UP:
		  
			temp_y = temp_y - ypos_increment;			 
         break;
         case NibblesConstants.DIRECTION_DOWN:
		  
			temp_y = temp_y + ypos_increment;
         break;		  
		 case NibblesConstants.DIRECTION_RIGHT:
		  
		    temp_x = temp_x + xpos_increment;
		 break;
		 case NibblesConstants.DIRECTION_LEFT:
		  
			temp_x = temp_x - xpos_increment;
		 break;
	  }      	  
	 	  
	  direction = kb_controls.getDirection(direction);  
	  
 	  if (direction != snake.getFirst().getDirection()) {
	  
	     snake.addFirst(new NibblesBox(temp_x, temp_y, direction, snake_width, snake_height, xpos_increment, ypos_increment, false)); 
	  }
	  else  {   	  
	  
	     snake.getFirst().setXY(temp_x, temp_y);	
		 snake.getFirst().increment();
      }		 

      snake.getLast().decrement();	  		        		 
	  
	  if (snake.getLast().getIncrement() == 0) 	  
         snake.removeLast();		 	  
   		 		 
      if (random_event.increase_size()) 	 
	     snake.getLast().increment();  

/*		 
	  switch ( kb_controls.getKey() ) {
	  
	     case java.awt.event.KeyEvent.VK_UP:		 
    	    graphics.drawString("VK_UP", 100, 100);  
	     break;
	     case java.awt.event.KeyEvent.VK_DOWN:
	        graphics.drawString("VK_DOWN", 100, 100);
         break;
	     case java.awt.event.KeyEvent.VK_RIGHT:
            graphics.drawString("VK_RIGHT", 100, 100);            
         break;
         case java.awt.event.KeyEvent.VK_LEFT:
            graphics.drawString("VK_LEFT", 100, 100);
	     break;
	  }	  	 	  
*/	  
   }
};

