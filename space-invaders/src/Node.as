// src/node.as
// Written by, Sohail Qayum Malik

package
{
	public class Node
	{
		/* We need to remember here, that for non-simple or basic types(as in :String, :Number, :int, :boolean) arguments to function calls  are by reference and for other basic types the arguments to function calls are by-value */
		public var data:*;
		public var prv:Node; 
		public var nxt:Node;
		
		public function Node(data:*)
		{			
		   this.data = data;
		   prv = null;
		   nxt = null;
		   
		   super();		   
		}
		
		public function set_data(data:*):void
		{		
		   this.data = data;	
		}
		
		public function get_data():*
		{			
		   return this.data;	
		}
		
		public function set_prv(prv:Node):void 
		{	
		   this.prv = prv;		   
		}
		
		public function get_prv():Node 
		{
			return this.prv;
		}
		
		public function set_nxt(nxt:Node):void
		{
		   this.nxt = nxt;	
		}
		
		public function get_nxt():Node 
		{			
		  return nxt;	
		}								
	};
};