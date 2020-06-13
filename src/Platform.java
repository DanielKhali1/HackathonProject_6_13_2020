import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform
{
	// constructor: 
	// attributes: variables
	// methods: 	functions
	private Rectangle rect;
	
	
	/**
	 * 
	 * CONSTRUCTOR
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Platform(double x, double y, double w, double h)
	{
		setRect(new Rectangle(w, h));
		getRect().relocate(x, y);
		
		// give this boi a Random Color
		int random = (int)(Math.random() *  3);
		
		switch(random){
			case 0: rect.setFill(Color.RED); break;
			case 1: rect.setFill(Color.GOLD); break;
			case 2: rect.setFill(Color.CORNFLOWERBLUE); break;
		}
		rect.setStroke(Color.BLACK);
	}
	
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
}
