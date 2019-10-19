/*
    src/Bullet.as
    Written by, Sohail Qayum Malik
*/

package 
{
   import flash.display.BitmapData;
   import flash.display.Loader;
   import flash.events.Event;
   import flash.net.URLRequest;

   import org.flixel.FlxPoint;

   public class Bullet extends Overrider
   {    
      public static const CHARACTER:String = "assets/images/Bullet.png";
      public static var bitmap:BitmapData = null;
      
      private var velocity:FlxPoint = null;
   
      public function Bullet(center:FlxPoint, velocity:FlxPoint)
      {
          var loader:Loader = null;       
      
          size = new FlxPoint(15, 13);
          this.center = new FlxPoint(center.x, center.y - size.y - 15);
          this.velocity = velocity;
          
          if (bitmap == null)
          {
             loader = new Loader();

             loader.contentLoaderInfo.addEventListener(Event.COMPLETE, imgGotLoaded);
             loader.load(new URLRequest(CHARACTER));
             loader.name = CHARACTER;  
          }          
      }
      
      public function update():void   
      {
         center.x = center.x + velocity.x;
         center.y = center.y + velocity.y;                  
      }
      
      private function imgGotLoaded(e:Event):void
      {
         bitmap = e.target.content.bitmapData
      }
      
   };
};