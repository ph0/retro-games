////////////////////////////////////////////////////////////////
// NibblesConstants.java                                      //
// @author, Sohail Qayum Malik[sqm@hackers.pk]                //
// Last modified on Saturday, 10th of November, 2012.         //
////////////////////////////////////////////////////////////////

package Nibbles;

import java.lang.Math;
import java.awt.Color;

//This interface has to be public else it can only be used in the package(Nibbles)(no explicit modifier means package-private)
public interface NibblesConstants {

   // The BOARD_FRAME_COLOR is the color of the area around the game board
   // The BOARD_COLOR is the color of the game board 
   public static final Color BOARD_FRAME_COLOR					  = Color.BLUE;
   public static final Color BOARD_COLOR						  = Color.YELLOW;
   public static final Color CIRCLE_COLOR						  = Color.BLUE;
   public static final Color SNAKE_COLOR						  = Color.RED;
   public static final Color CIRCLE_COLOR_TEST					  = BOARD_COLOR;

   //This is our default applet area
   //All constants begning with word DEFAULT are based on these undergiven values
   //Any changes done here should also be made/done to DEFAULT values which are based on these following values
   public static final int DEFAULT_HEIGHT                 = 300;
   public static final int DEFAULT_WIDTH                  = 300;
   
   public static final int DEFAULT_FRAME_WIDTH			  = 10;  
   public static final int DEFAULT_RADIUS				  = 3;

   // Snake is made up of contegious chain of boxes
   // Height and width is proportional to the DEFAULT_RADIUS
   public static final int DEFAULT_BOX_HEIGHT			  = 6;
   public static final int DEFAULT_BOX_WIDTH			  = 6;   
   
   // Snake takes these directions...
   public static final int DIRECTION_UP                   = 1;
   public static final int DIRECTION_DOWN                 = 2;
   public static final int DIRECTION_LEFT                 = 3;
   public static final int DIRECTION_RIGHT                = 4;

   // The applet needs to know the speed of the processor. The applet will use for() loop in between two getTime() calls
   // The diference will be in mill seconds...
   //public static final long A_VERY_BIG_NUMBER		  = 18446744070000000000; // 2^sizeof(long)
   //public static final double A_VERY_BIG_NUMBER = Math.pow( 2, 64 ); 
   //public static final double A_VERY_BIG_NUMBER = 10000000;
   public static final int x_i 							  = 18;
   public static final int y_i 							  = 12;
   public static final int z_i 							  = 6;
   public static final float x_f 						  = 18.0f;
   public static final float y_f 						  = 12.0f;
   public static final float z_f 						  = 6.0f;
   public static int passes 							  = 40000;      
};
