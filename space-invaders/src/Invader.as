/*
   src/Invader.as (the recipe of invader, the invader attacks the earth)
   Written by, Sohail Qayum Malik[sqm@hackers.pk]
*/

package 
{
   import flash.display.BitmapData;
   import flash.display.Loader;
   import flash.events.Event;
   import flash.net.URLRequest;
   
   import org.flixel.FlxPoint;
   import org.flixel.FlxSprite;
   
   public class Invader extends Overrider 
   {  
      //public var center:FlxPoint
      /* The size of the player in the units of pixels */
      //public var size:FlxPoint;
   
      public static const CHARACTER:String = "assets/images/Invader.png";
      public static var bitmap:BitmapData = null;
      
      private var gameState:GameState = null;
      
      //public var center:FlxPoint
      /* The size of the player in the units of pixels */
      //public var size:FlxPoint;
      
      private var patrolX:int = 0; /* Left/right distance covered by each invader and then collectively by the whole row */ 
      private var speedX:int = 5;  /* Speed at which to move left/right */
      private var patrolY:int = 0; /* Backward/forward distance covered */
      private var speedY:int = 1 /* 2 */;  /* Speed at which to move forward/backwards */
      
      //this.patrolX = 0; /* Left/right distance covered by each invader and then collectively by the whole row */
      //this.speedX = 0.3; /* Speed at which to move left/right */
      //this.patrolY = 0; /* Backward/forward distance covered */
      //this.speedY = 0.3; /* Speed at which to move forward/backwards */
      
      /* It will have an image sprite */
      //public var image:FlxSprite = null;
      
      public function Invader(gameState:GameState, center:FlxPoint)
      {
         var loader:Loader = null;
         
         this.center = center;
         size = new FlxPoint(47, 34);
         this.gameState = gameState;
         
         if (bitmap == null) 
         {
            loader = new Loader();

            loader.contentLoaderInfo.addEventListener(Event.COMPLETE, imgGotLoaded);
            loader.load(new URLRequest(CHARACTER));
            loader.name = CHARACTER;                  
         } 

         //super(center, size);         
      }
      
      public function update():void      
      {
         /* We start by moving to the right at speedX at a time until our move(patrol) to right is more than 30 and then we move back to the left and reach the same original horizontal x-axis from where we actually started, what should be kept in mind here that we are actually just moving to the right the movement to the left is just going back to our original starting place */
         if (patrolX < /* -50 */ 0 || patrolX > 80) {
            speedX = -speedX;
         }
                  
         /* We move to the right and then pull back to our original starting place in the units of speedX */
         center.x += speedX;
         /* The distance from our original starting place. We move to the right and then go back(moving to the left) in the units of speedX */
         patrolX += speedX; 
         
         /* We move down in the units of speedY at the increment which is equal to speedY */
         center.y += this.speedY;
         patrolY += this.speedY;
      }

      private function imgGotLoaded(e:Event):void
      {
         bitmap = e.target.content.bitmapData
      }           
   };
};
 //Eight invaders per row and each invader has a width of 15 pixels and horizontally each invader is 15 pixels apart from the next invader in the same row(left sides of the invaders in the same row are 30 pixels apart) 