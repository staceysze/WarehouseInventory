/**
 * This "Widgets" class is to create an object which accepts the cost and set the price of this widget to 40% more than the cost.
 * The 'cost' and 'price' variables are protected so that it is only available in this package.
 * @author Stacey So
 */
public class Widgets{
	protected double cost;
	protected double price;

	public Widgets(double c){
		cost=c;
		price= (0.40*cost)+cost;
	}

	public void setCost(double c){
		cost=c;
	}

	public double getCost(){
		return cost;
	}

	public double getPrice(){
		return price;
	}


}
