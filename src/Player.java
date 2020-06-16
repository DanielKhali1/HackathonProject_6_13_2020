import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Player 
{
	double moveSpeed;
	double gravity;
	int imageSize;
	Vector velocity;
	Vector acceleration;
	Vector position;
	
	Image img;
	ImageView playerBody;
	
	boolean lr = false;
	double rotation;
	
	public Player(Vector Pos, double moveSpeed, double gravity, int imageSize)
	{
		rotation = 0;
		img = new Image("res/redboi.gif");
		playerBody = new ImageView(img);
		
		playerBody.setFitHeight(imageSize);
		playerBody.setFitWidth(imageSize);
		
		velocity = new Vector(0, -5);	
		acceleration = new Vector (0, 0);
		this.moveSpeed = moveSpeed;
		this.gravity = gravity; 
		this.position = Pos; 
		this.imageSize = imageSize; 
		updateCircle();
	}
	
	void changeImage(String file)
	{
		img = new Image("res/" + file);
		playerBody.setImage(img);
		playerBody.setFitHeight(imageSize);
		playerBody.setFitWidth(imageSize);
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
