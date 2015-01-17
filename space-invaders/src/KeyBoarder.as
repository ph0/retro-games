/*
   src/KeyBoarder.as
   Written by, Sohail Qayum Malik[sqm@hackers.pk]
*/

package 
{
   import flash.ui.Keyboard;
   import flash.events.Event;
   import flash.events.KeyboardEvent;
   
   public class KeyBoarder 
   {     
      public static var keyState:Array = [];
                          
      public static function onKeyDown(e:KeyboardEvent):void 
      {
         keyState[e.keyCode] = true;
      }
      
      public static function onKeyUp(e:KeyboardEvent):void
      {
         keyState[e.keyCode] = false;
      }
      
      public static function isDown(key:uint):Boolean
      {
         return keyState[key];
      }
   };  
};