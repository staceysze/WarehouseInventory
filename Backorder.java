import java.util.LinkedList;
/**
 * "Backorder" class inherited the built-in LinkedList structure. This is a queue(FIFO) structure. 
 * 'bOrder' is a queue which manages 'widgets' object. 
 * @author Stacey So
 */
public class Backorder extends LinkedList{
	protected int bOrderCount=0; //the counter for the node/widget objects
	LinkedList<Widgets> bOrder=new LinkedList<Widgets>();

	public Backorder(){
		super();
		bOrderCount=0; //The initial count of queue is zero.
	}
	/**
	 * This 'enQ' method initially checks if the queue is empty or not. If so, the head will point at the first node added.
	 * Else, the new node will be added to the back of the queue.
	 * And, the 'bOrderCount' will be incremented each time.
	 */
	public void enQ(Widgets w){
		if(bOrder.isEmpty()){
			bOrder.addFirst(w);
		}
		bOrder.addLast(w);
		bOrderCount++;
	}
	/**
	 * This 'deQ' method returns and removes the first node in the queue if it is called.
	 * And, the'bOrderCount' will be decremented each time.
	 */
	public void deQ(){
		bOrder.poll();
		bOrderCount--;
	}
	/**
	 * This 'bCount' method returns the variable 'bOrderCount'. The minimum value for 'bOrderCount' is zero.
	 * The value of 'bOrderCount' cannot be zero.
	 */
	public int bCount() {
		if(bOrderCount<0){
			bOrderCount=0;
		}
		return bOrderCount;
	}
}//end of the "Backorder" class
