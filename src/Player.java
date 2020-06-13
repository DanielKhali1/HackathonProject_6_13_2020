import javafx.scene.shape.Circle;

public class Player 
{
	double moveSpeed;
	double gravity;
	Vector velocity;
	Vector acceleration;
	Vector position;

	Circle playerBody = new Circle(20);
	
	public Player(Vector Pos, double moveSpeed, double gravity)
	{
		velocity = new Vector(0, -5);	
		acceleration = new Vector (0, 0);
		this.moveSpeed = moveSpeed;
		this.gravity = gravity; 
		this.position = Pos; 
		updateCircle();
	}
	
	void moveLeft()
	{
		Vector tempVector;
		tempVector = new Vector(moveSpeed, 0);
		position.subVector(tempVector);
		updateCircle();
	}
	 
	void moveRight()
	{
		Vector tempVector;
		tempVector = new Vector(moveSpeed, 0);
		position.addVector(tempVector);
		updateCircle();
	}
	 
	void updatePos()
	{
		 acceleration = new Vector (0, gravity);
		 velocity.addVector(acceleration);
		 position.addVector(velocity);
		 updateCircle();
	}
	
	void updateCircle()
	{
		playerBody.setLayoutX(position.x);
		playerBody.setLayoutY(position.y);
	}
}
