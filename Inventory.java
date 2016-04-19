import java.util.Stack;
import java.math.BigDecimal;
/**
 * Comments are made before each method.
 * "Inventory" class includes methods to handle incoming and outgoing(sold) widgets.
 * Variables namely 'count', 'latestCost' and 'latestPrice' are global because the values of these variables need to be
 * updated whenever their values are being changed.
 * The variable 'latestCost' stores the value of the most recent cost of a widget when it is received.
 * The variable 'latestPrice' stores the value of the most recent price(1.04*Cost) of a widget when it is received.
 * @author Stacey So
 */
public class Inventory extends Stack {
	private int count = 0;
	private Stack<Widgets> stock = new Stack<Widgets>(); //'stock' is an instance of the stack structure.
	private Backorder b1 = new Backorder(); //b1 is a queue(linked list) structure to store the widgets on back order.
	private double latestCost;
	private double latestPrice;
	private double totalCost;

	public Inventory() { //constructor for the Inventory class which is inherited from built-in stack class.
		super();
	}
	/**
	 * The "Receiving" method takes in 2 parameters which are for: 1)the quantity of widgets received into the stack
	 * and 2)the cost of the widgets.
	 */
	public void receiving(int amt, double cost) {
		totalCost+=(amt*cost);
		latestCost=cost;
		latestPrice=cost*1.40;
		Widgets w = new Widgets(cost);
		/**
		 * If "BackOrder" queue is empty, the quantity of widgets received will be pushed directly onto the stack.
		 * And, the 'count' of widgets in the stack will increase every time a widget is pushed onto the stack.
		 */
		if(b1.bCount()==0){
			for(int i = 1; i <= amt; i++) {
				stock.push(w);
				count++;
			}
			System.out.println("~~~~~~~~~~~~~~~| Incoming Invoice |~~~~~~~~~~~~~~~");
			System.out.println("| Shipments Received : " + amt + " @ $" + round(cost)+ " | ");
			System.out.println("| Actual Cost: $" + round(cost * amt)+" |");
			System.out.println("| Quantity of Widgets in the Invertory: "+count+" |");
			System.out.println("| Total Cost: $"+ round(totalCost) +" |");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		/** If the stack is empty and there are widgets on back order:
		 * 1)Suppose that the quantity of widgets received is greater than the quantity of widgets in the back order,
		 * dequeue the "BackOrder" queue by the amount of widgets received and then, push the remaining widgets onto the stack.
		 */
		else if(stock.empty() && b1.bCount()>0){
			if(amt>b1.bCount()){
				int temp=amt-b1.bCount();
				for(int j=1;j<=temp;j++){
					stock.push(new Widgets(cost));
					count++;
				}
			}
			for(int i=1; i<=amt ;i++){
				b1.deQ();
			}
			System.out.println("~~~~~~~~~~~~~~~| Incoming Invoice |~~~~~~~~~~~~~~~");
			System.out.println("| Shipments Received : " + amt + " @ $" + round(cost)+ " | ");
			System.out.println("| Shipments on backorder: "+(b1.bCount())+" |");
			System.out.println("| Quantity of Widgets in the Invertory: "+count+" |");
			System.out.println("| Total Cost: $"+ round(totalCost) +" |");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		System.out.println();
		System.out.println();
	}
	/**
	 * The "Selling" method takes in a parameter, which is the amount of widgets that will be sold.
	 * This method also display customer copy of the invoice on the console.
	 * It consists of three major comparisons :
	 */
	public void selling(int amt) {
		int diff=amt-count;
		Widgets lWidgets=new Widgets(latestCost);
		/**
		 * 1)If the stack is empty and the quantity of widgets ordered is greater than the quantity of widgets in the stack,
		 * enqueue the amount of widgets ordered to the "BackOrder" queue called 'b1'.
		 */
		if((stock.empty())&&(diff>0)){
			for(int j=1; j<=amt; j++) {
				b1.enQ(lWidgets);
			}
			System.out.println("---------------| Customer Receipt |---------------");
			System.out.println("| Quantity of Widgets Ordered: "+ amt+" |");
			System.out.println("| Quantity of Widgets Sold : " + count);
			System.out.println("| Amount charged : $"+ round((amt-diff)*lWidgets.price)+" |");
			System.out.println("| Shipments on backorder: "+(b1.bCount())+" |");
			System.out.println("| Quantity of Widgets in the Inventory: "+count+" |");
		}
		/**
		 * Else, if the stack is not empty and the quantity ordered is greater than the quantity of widgets in the stack,
		 * the stack is emptied(by popping the stack) and the remaining amount of widgets is enqueued to 'b1'.
		 * The 'count' of the stack equals to zero.
		 */
		else if((amt>count)) {
			int currentCount=count;
			for(int i=1; i<=currentCount; i++) {
				stock.pop();
				count--;
			}
			for(int j=1; j<=diff; j++) {
				b1.enQ(lWidgets);
			}
			System.out.println("---------------| Customer Receipt |---------------");
			System.out.println("| Quantity of Widgets Ordered: "+ amt+" |");
			System.out.println("| Quantity of Widgets Sold: " + (amt-diff)+ " @ $" +round(latestPrice)+ " | ");
			System.out.println("| Amount charged: $"+ round((amt-diff)*latestPrice)+" |");
			System.out.println("| Shipments on backorder: " +(b1.bCount())+" |");
			System.out.println("| Quantity of Widgets in the Inventory: "+count+" |");
		}
		/**
		 * Else, if the stack is not empty and the quantity ordered is less than the quantity of widgets in the stack,
		 * meaning that the stack has enough widgets to fulfill the order without using the "Back Order" queue,
		 * the amount ordered will be popped from the stack accordingly.
		 * The 'count' of widgets in the stack will be decremented with every time the stack is poppped.
		 */
		else if(amt<count){
			for(int i = 1; i <= amt; i++) {
				stock.pop();
				count--;
			}
			System.out.println("---------------| Customer Receipt |---------------");
			System.out.println("| Quantity of Widgets Ordered: "+ amt+" |");
			System.out.println("| Quantity of Widgets Sold : " + amt+ " @ $" +round(latestPrice) + " | ");
			System.out.println("| Amount Charged: $" + round((latestPrice * amt))+ " | ");
			System.out.println("| Shipments on backorder: " +(b1.bCount())+" |");
			System.out.println("| Quantity of Widgets in the Inventory: "+count+" |");
		}
		System.out.println("--------------------THANK YOU!--------------------");
		System.out.println();
		System.out.println();
	}

	//This method is to handle the format of the double value of price and cost of the widgets to 2 decimal places.
	public static double round(double d) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
} //end of the "Inventory" class
