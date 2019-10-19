/* 
   src/SpaceInvaders.as - (The Preloader)
   Written by, Sohail Qayum Malik
*/   
   
package
{	 
    import flash.display.LoaderInfo;
    import org.flixel.system.FlxPreloader;
	
	public class SpaceInvaders extends FlxPreloader
	{
	
	    public static var SpaceInvadersURL:String;
	    
		public function SpaceInvaders()
		{
				   		   		   
		   SpaceInvadersURL = LoaderInfo(this.root.loaderInfo).parameters.spaceInvadersUrl; 
		   
		   className = "Main";
		   super();	
		}															
	}
}