/*
   src/GameState.as
   Written by, Sohail Qayum Malik[sqm@hackers.pk]
*/   

package
{      
   import flash.events.Event;   
   import flash.events.KeyboardEvent;   
   import flash.ui.Keyboard;
   import flash.utils.getQualifiedClassName;
   
   import org.flixel.FlxG;
   import org.flixel.FlxPoint;
   import org.flixel.FlxState;    
   import org.flixel.FlxSprite;
         
   public class GameState extends FlxState				      
   {         
                                                                  
      /* It contains a Player, Ship, Rockets, Invaders and Bullets */
	  private var bodies:Node; // Head of doubly linked list to store references of Loader instances      
                                               
	  public function GameState()
	  {                   
         bodies = null;
                   
		 super();
	  }	
	  	  	  	  
	  override public function create():void
	  {	                         
         /* We are adding total of 20 invaders */
         for (var i:uint = 0; i < 20; i++) 
         {                    
            var x:uint = 50 + (i % 5) * 80; // Each row has 5 invaders. A invader has size of 47x34 pixels. Each row of invaders has 50px empty on both sides of the row. There's 33pxs of blank space between two neighbouring invaders. Thus 47*5 + 33*5 = 400  
            var y:uint = /* 10 */50 + (i % 4) * 67; // Each column has 4 invaders. A invader has size of 47x34 pixels. In an xth the first invader of this xth column starts after the first 50 pixels then each next invader of this xth column is 67px apart. Vertically each invader has a distance of 33px            
            addBody(new Invader(this, new FlxPoint(x, y))); 
         }
         /* Add a single player which will defend against the invaders */
         addBody(new Player(this, new FlxPoint(FlxG.height, FlxG.width)));         
                        
         FlxG.stage.addEventListener(KeyboardEvent.KEY_DOWN, KeyBoarder.onKeyDown);
         FlxG.stage.addEventListener(KeyboardEvent.KEY_UP, KeyBoarder.onKeyUp);
         
		 super.create();
	  }
	  
	  override public function destroy():void
	  {
		 super.destroy(); 
	  }
	  
	  /* Override this function to update your class's position and appearance */
	  override public function update():void 
	  {           
         var nxt:Node = bodies;
                  
         while (nxt != null && Player.bitmap != null && Invader.bitmap != null && Bullet.bitmap != null)
         {         
            var data:* = nxt.get_data();
            
            var body:Overrider = Overrider(data);
            
            if (body.image != null)
            {
               remove(body.image);
               body.image = null;                  
            }
            
            body.image = new FlxSprite(body.center.x - body.size.x / 2, body.center.y); 
            
            if (getQualifiedClassName(data) == "Player")
            {
               body.image.pixels = Player.bitmap;
               Player(data).update();
            }
            else if (getQualifiedClassName(data) == "Invader")
            {
               body.image.pixels = Invader.bitmap;
               Invader(data).update();
            }
            else if (getQualifiedClassName(data) == "Bullet")
            {            
               body.image.pixels = Bullet.bitmap;
               Bullet(data).update();
            }
                        
            add(body.image);
         
            nxt = nxt.get_nxt();
         }
                                                                        
         /* REPORT COLLISIONS */         
         var prv:Node = bodies;
                           
         while (prv != null) 
         {                        
            nxt = prv.get_nxt();                        
            while (nxt != null)
            {                             
               /* Now we've prv<--nxt and prv-->nxt relationship, check if they are colliding or not */
               if (isColliding(prv.get_data(), nxt.get_data()) == true)                
               {
                  remove(Overrider(prv.get_data()).image);                
                  removeBody(prv);
                  remove(Overrider(nxt.get_data()).image);
                  removeBody(nxt);
               }                               
               nxt = nxt.get_nxt();
            }
            /* REMOVE OUT OF FRAME BULLETS */ 
            if (getQualifiedClassName(prv.get_data()) == "Bullet")
            {            
               var overrider:Overrider = Overrider(prv.get_data()); 
               if (overrider.center.y < 0 || overrider.center.y > FlxG.height)
               {
                  remove(overrider.image);
                  removeBody(prv);
               }
            } 
            /* REMOVE OUT OF FRAME BULLETS ENDS HERE */ 
            /* FOR EACH INVADER WHICH HAS NO INVADER BELOW OF IT, FIRE BULLET */            
            if (getQualifiedClassName(prv.get_data()) == "Invader")
            {
               nxt = prv.get_nxt();                        
               while (nxt != null)
               {
                  if (getQualifiedClassName(nxt.get_data()) == "Invader")
                  {                                                     
                     if (Math.abs(Overrider(prv.get_data()).center.x - Overrider(nxt.get_data()).center.x) && (Overrider(nxt.get_data()).center.y > Overrider(prv.get_data()).center.y)) 
                     {
                        break;
                     }                                                       
                  }
                  nxt = nxt.get_nxt();
               }
               
               if ((nxt == null) && (Math.random() > 0.995))
               {               
                  addBody(new Bullet(new FlxPoint(Overrider(prv.get_data()).center.x, Overrider(prv.get_data()).center.y + Overrider(prv.get_data()).size.y + 20), new FlxPoint(0, 10)));                                  
               }
            }
            /* FOR EACH INVADER WHICH HAS NO INVADER BELOW OF IT, FIRE BULLET ENDS HERE */
            prv = prv.get_nxt();
         }
                                               
		 super.update();
	  }
      
      public function isColliding(bodyA:*, bodyB:*):Boolean
      {
         var bA:Overrider = Overrider(bodyA);
         var bB:Overrider = Overrider(bodyB);
                         
         if (/*(sameInTypeAndValue(bodyA, bodyB) == false) && */((bA.center.x + bA.size.x / 2) > (bB.center.x - bB.size.x / 2)) && ((bA.center.y + bA.size.y / 2) > (bB.center.y - bB.size.y / 2)) && ((bA.center.x - bA.size.x / 2) < (bB.center.x + bB.size.x / 2)) && ((bA.center.y - bA.size.y / 2) < (bB.center.y + bB.size.y / 2)))
         {         
            return true;
         }
         
         return false;
      }
      
      /* 
         TODO, Check does it really work?
      */      
      public function sameInTypeAndValue(dataB1:*, dataB2:*):Boolean 
      {
         if (getQualifiedClassName(dataB1) == getQualifiedClassName(dataB2))
         {
            var overriderB1:Overrider = Overrider(dataB1);
            var overriderB2:Overrider = Overrider(dataB2);
            
            if (overriderB1.center == overriderB2.center && overriderB1.size == overriderB2.size)
            {
               return true;
            }                       
         }
         
         return false;
      }      
            
      public function addBody(data:*):void 
      { 
         var cur:Node = null;
         
         if (bodies == null) 
		 {		 
		    bodies = new Node(data);					   
         } 
         else 
         {	
            var nxt:Node = bodies;
            
            while (nxt != null)
            {
               cur = nxt;            
               nxt = nxt.get_nxt();
            }
  
            /* We've finally found an instance of node in which the next link is null */ 
            /* Create new node, assign its previous link to the current node, its next link is already null  */
            var node:Node = new Node(data);            
            node.set_prv(cur);
            cur.set_nxt(node); 
         }                  
      }
      
      public function removeBody(node:Node):void 
      {                    
         if (node != null)
         {
            var prv:Node = node.get_prv();
            var nxt:Node = node.get_nxt();
         
            node = null; 
            
            if (prv == null)
            {
               bodies = nxt;
               if (nxt != null)
               {               
                  nxt.set_prv(null);
               }
            }
            else 
            {
               prv.set_nxt(nxt); 
               if (nxt != null)
               {
                  nxt.set_prv(prv);                
               }            
            }         
         }                                
      }              
   }; // End of class		
}; // End of package