/*
   src/Main.as - Main.as, it is an actual application class; don't tell this to FB) 
   Written by, Sohail Qayum Malik[sqm@hackers.pk]
   Note to self... 
   To use your preloader in an ActionScript Project you need to make your preloader the default application and make your default application run in frame 2 by adding "-frame appFrame MyApp" in the additional compiler arguments, where MyApp is the classname of your main class file, which would've been in the frame one instead of preloader, if you would've not been using the preloading and bootstrapping the main class
*/   

package
{	
	
    //import flash.display.StageAlign;
    //import flash.display.StageScaleMode;
	import org.flixel.*;
		
	[SWF(width=500, height=500, backgroundColor="#ffffff")]
	
	/* 
	   This will not work in FB AC3 projects, instead you've to follow the path 
	 */
	//[Frame(factoryClass = "Preloader")]
	
	public class Main extends FlxGame		
	{
					
		public function Main()
		{   		
           //stage.scaleMode = StageScaleMode.NO_SCALE;
           //stage.align = StageAlign.TOP_LEFT;
		   		 					
		   super(500, 500, GameState, 1, 20, 20);
		}					
	}
}