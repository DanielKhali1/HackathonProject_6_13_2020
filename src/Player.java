import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Player 
{
	double moveSpeed;
	double gravity;
	Vector velocity;
	Vector acceleration;
	Vector position;

	
	Image img;
	ImageView playerBody;
	
	boolean lr = false;
	double rotation;
	
	public Player(Vector Pos, double moveSpeed, double gravity)
	{
		rotation = 0;
		img = new Image("res/slimeRed.png");
		playerBody = new ImageView(img);
		
		playerBody.setFitHeight(75);
		playerBody.setFitWidth(75);
		
		velocity = new Vector(0, -5);	
		acceleration = new Vector (0, 0);
		this.moveSpeed = moveSpeed;
		this.gravity = gravity; 
		this.position = Pos; 
		updateCircle();
	}
	
	void changeImage(String file)
	{
		img = new Image("res/" + file);
		playerBody.setImage(img);
		playerBody.setFitHeight(75);
		playerBody.setFitWidth(75);
	}
	
	void moveLeft()
	{
		Vector tempVector;
		tempVector = new Vector(moveSpeed, 0);
		position.subVector(tempVector);
		updateCircle();
		lr = false;
	}
	 
	void moveRight()
	{
		Vector tempVector;
		tempVector = new Vector(moveSpeed, 0);
		position.addVector(tempVector);
		updateCircle();
		lr = true;

	}
	 
	void updatePos()
	{
		 acceleration = new Vector (0, gravity);
		 velocity.addVector(acceleration);
		 position.addVector(velocity);
		 updateCircle();
		 
		 if(lr)
			 rotation += 2;
		 else
			 rotation -= 2;
		 
		playerBody.setRotate(rotation);
	}
	
	void updateCircle()
	{
		playerBody.setLayoutX(position.x);
		playerBody.setLayoutY(position.y);
	}
}
