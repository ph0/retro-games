/*
   src/Player.as (the recipe of player, the player defends the earth against the invaders)
   Written by, Sohail Qayum Malik[sqm@hackers.pk]   
*/

package 
{
   import flash.display.BitmapData;
   import flash.display.Loader;
   import flash.events.Event;
   import flash.net.URLRequest;
   import flash.ui.Keyboard;
   
   import org.flixel.FlxPoint;
   import org.flixel.FlxSprite;
     
   public class Player extends Overrider
   {
      
      /* Player moves horizontally, the center has the current x, y ordinates of the player */
      //public var center:FlxPoint;
      /* The size of the player in the units of pixels */
      //public var size:FlxPoint;
      /* This is the picture of player. The player will defend earth against alien invaders */
      /* The player has dimensions 35x60 */      
      public static const CHARACTER:String = "assets/images/Ship.png";
      public static var bitmap:BitmapData = null;
      
      /* Player moves horizontally, the center has the current x, y ordinates of the player */
      //public var center:FlxPoint;
      /* The size of the player in the units of pixels */
      //public var size:FlxPoint;
      
      private var gameState:GameState = null;
      private var gameSize:FlxPoint;
      
      /* It will have an image sprite */
      //public var image:FlxSprite = null;
                              
      public function Player(gameState:GameState, gameSize:FlxPoint) 
      {        
         var loader:Loader = null;
         
         //super(new FlxPoint(gameSize.x/2 - 35/2, gameSize.y - 60), new FlxPoint(35, 60));
         
         //this.center = new FlxPoint(gameSize.x/2 - 35/2, gameSize.y - 60);
         
         /* The size in pixels of the picture of the player */
         size = new FlxPoint(35, 60); 
         this.center = new FlxPoint(gameSize.x / 2, gameSize.y - size.y);
         this.gameSize = gameSize; 
         this.gameState = gameState;         
         
         loader = new Loader();

         loader.contentLoaderInfo.addEventListener(Event.COMPLETE, imgGotLoaded);
         loader.load(new URLRequest(CHARACTER));
         loader.name = CHARACTER;
         
         var bullet:Bullet = new Bullet(new FlxPoint(this.center.x, this.center.y), new FlxPoint(0, -10));
         //var bullet:Bullet = new Bullet(new FlxPoint(gameSize.x / 2, gameSize.y - size.y), new FlxPoint(0, -10));
                          
         //super(new FlxPoint(gameSize.x / 2, gameSize.y - size.y), new FlxPoint(35, 60));         
      }
      
      public function update():void
      {               
         if (KeyBoarder.isDown(Keyboard.RIGHT)) 
         {
            if ((center.x + size.x/2 + 8) < gameSize.x ) 
            {
               center.x = center.x + 8;
            }   
         }
         if (KeyBoarder.isDown(Keyboard.LEFT))
         {
            if ((center.x - size.x/2 - 8) > 0)
            {
               this.center.x = center.x - 8;
            }
         }
         if (KeyBoarder.isDown(Keyboard.SPACE))
         {
            gameState.addBody(new Bullet(new FlxPoint(center.x, center.y), new FlxPoint(0, -10)));
         }         
      }
      
      private function imgGotLoaded(e:Event):void
      {
         bitmap = e.target.content.bitmapData
      }
   };
};