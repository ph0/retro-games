//gameBoard.java
//Written by, Sohail Qayum Malik[sqm@hckers.pk]

package Hanoi;

import java.awt.Graphics;
import java.awt.Color;

public class gameBoard {
  
   //Primitive data type int defaults to 0
   int height, width, numberOfSlabs, slabHeight, slabWidth; 
  
   //from = where you clicked first, where the slab is resting
   //to   = where you clicked next,  where you want the slab to rest
   //Initially both default to 0
   int from, to;
  
   //There are three towers to keep the state of
   int[][] state         = new int[3][];

   int[] state_top       = new int[3]; 

   //It defaults to false(The boolean data type)
   //When the game finishes this will be set to false
   boolean game_is_on;

   public gameBoard(int numberOfSlabs, int height, int width) {
     
      //Okhey, Java does not have pointers and I used this-> and go corrected by javac
      this.numberOfSlabs = numberOfSlabs;
      this.height        = height;
      this.width         = width;

      slabHeight         = (int)(height*0.80)/numberOfSlabs;        
      slabWidth          = (int)width/3;

      from               = -1;
      to                 = -1; 

      state[0]           = new int[numberOfSlabs];
      state[1]           = new int[numberOfSlabs];
      state[2]           = new int[numberOfSlabs];
      for(int i = 0; i <3; i++)
         for(int j = 0; j < numberOfSlabs; j++)
            if(i > 0) 
               state[i][j] = -1;
            else state[i][j] = j;


      state_top[0]  = numberOfSlabs - 1;
      state_top[1]  = 0;
      state_top[2]  = 0; 

      game_is_on   = true;
   }

   public boolean heat_is_on() {

      return game_is_on;
   }

   //Finally when game is over, we'll call this function. This will break the main game loop
   void heat_is_off() {

      game_is_on   = false;
   }

   public void determineTower(int x, int y) {

      int tower = 1;  

      if(x < slabWidth)
         tower = 0;
      else if(x > 2*slabWidth) 
         tower = 2;

      //It is our first of the two clicks
      if(from < 0)
         from = tower;
      //It is our 2nd and last click, now we should request the move to be made
      //As a last step the function makeMove() will reset the from and to, to -1
      else if (to < 0)
         to   = tower;
      //We've not made the move(called the function, makeMove()). from and to both are either 0 or more
      else {         
         from = tower;
         to   = -1;
      }

      //Rest of the code, it deals with the updating the state of towers   

      //Early exit, it is premature move. We moved earlier than two clicks 
      //Most probably since this function gets its call from the paint() function of the applet, 
      //and it called this function prematurely
      //The follwing two statements are necesaary here 
      //      if( !((from >= 0) && (to >= 0)) ) 
      //         return; 
                                
   }

   public void makeMove(Graphics graphics) {

      graphics.setColor(Color.yellow);
      graphics.fillRect(0, 0, width, height); 

      drawPoles(graphics);

      if(from == 0) {

         graphics.setColor(Color.blue);
         graphics.fillRect(0, 0, slabWidth, (int)slabHeight/2); 
      }          
      else if(from == 1) {

         graphics.setColor(Color.blue);
         graphics.fillRect(slabWidth, 0, slabWidth, (int)slabHeight/2); 
      }
      else {

         graphics.setColor(Color.blue);
         graphics.fillRect(2*slabWidth, 0, slabWidth, (int)slabHeight/2);           
      }

     if(to == 0) {

         graphics.setColor(Color.green);
         graphics.fillRect(0, 0, slabWidth, (int)slabHeight/2); 
      }          
      else if(to == 1) {

         graphics.setColor(Color.green);
         graphics.fillRect(slabWidth, 0, slabWidth, (int)slabHeight/2); 
      }
      else {

         graphics.setColor(Color.green);
         graphics.fillRect(2*slabWidth, 0, slabWidth, (int)slabHeight/2);           
      }


      //Rest of the code related to displaying the state of all three towers go here

      //Early exit, it is premature move. We moved earlier than two clicks 
      //Most probably since this function gets its call from the paint() function of the applet, 
      //and it called this function prematurely
      if( !((from >= 0) && (to >= 0)) ) 
         return; 
                                 
       updateState(graphics);                        
                      
      //Getting ready for the next move
      from = -1;
      to   = -1;       
   }   

   
   public void drawState(Graphics graphics) {

      int adjustment = 0; 

      for(int i = 0; i < 3; i++)
         for(int j = 0; j<numberOfSlabs; j++) {           
            if(state[i][j] >= 0)  {
               to = i;
               if(j > state[i][j]) 
                  adjustment = j - state[i][j];
               else if( j < state[i][j])
                  adjustment = state[i][j] - j; 
               else
                  adjustment = 0;   
               drawSlab(graphics, state[i][j], adjustment);
            }
         }

      to   = -1;
   }

   void updateState(Graphics graphics) {

      int from_top = state_top[from];
      int to_top   = state_top[to];

      if( !(state[from][from_top] >= 0) ) 
         return; 
            
      if( from == to )
         return;
      
      if(to_top >= (numberOfSlabs - 1))
         return;

      if( (state[from][from_top] > state[to][to_top]) ) {

         if( !((to_top == 0) && (state[to][to_top] < 0)) )
            state_top[to]+=1;         
         to_top=state_top[to];
         state[to][to_top] = state[from][from_top];
         state[from][from_top] = -1;                     
      }       
       
      if( (state[from][from_top] < 0) && (from_top > 0) ) {
         from_top-=1; 
         state_top[from]= from_top;       
      }
                    
   }


   void drawPoles(Graphics graphics) {

      int i = 0;

      int x1, y1, x2, y2; 

      for(;i<3;) {
        
         x1 = i*slabWidth + (int)((slabWidth*(1 - Math.pow(0.80, numberOfSlabs)))/2);  
         y1 = width -  (numberOfSlabs)*slabHeight;
         x2 = (int)(slabWidth*Math.pow(0.80, numberOfSlabs));
         y2 = width;

         graphics.setColor(Color.blue); 
         graphics.fillRect(x1, y1, x2, y2);

         i++; 
      }       
   } 

   void drawSlab(Graphics graphics, int position, int adjustment) {

      int x1, y1, x2, y2;            

      x1  = to*slabWidth + (int)((slabWidth*(1 - Math.pow(0.80, position)))/2);
      //The multiplier should not be 0(that is why we'll increment it by 1)
      y1 = width - (position - adjustment + 1)*slabHeight;      
      //The width of each slab is determined by its place in the tower 
      //The higher the slab is in the tower the smaller it is
      x2  = (int)(slabWidth*Math.pow(0.80, position));
      y2  = slabHeight;

      graphics.setColor(Color.red);
      graphics.fillRect(x1, y1, x2, y2);   
   }
   
   public void drawPath(Graphics g, int x, int y) {
      
      g.setColor(Color.green);
      g.drawString("#*#", x, y);
   }
    
};