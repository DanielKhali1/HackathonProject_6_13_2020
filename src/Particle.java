

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Particle 
{
	double size;
	PARTICLE_TYPE type;
	ImageView imageview;
	Image img;
	Vector position;
	Vector velocity;
	Vector acceleration;
	
	double rotation = 0;
	
	
	
	public Particle(int size, PARTICLE_TYPE type)
	{
		this.type = type;
		this.size = size;
		position = new Vector(0, 0);
		velocity = new Vector(0, 0);
		acceleration = new Vector(0, 0);
		
		switch(type)
		{
			case RED:
				img = new Image("res/slime_particle_red.png");
				break;
			
			case BLUE:
				img = new Image("res/slime_particle_blue.png");
				break;
				
			case YELLOW:
				img = new Image("res/slime_particle_yellow.png");
				break;
		}
		imageview = new ImageView(img);
		imageview.setFitHeight(size);
		imageview.setFitWidth(size);
	}
	
	public Particle(Particle particle)
	{
		this.type = particle.type;
		this.size = particle.size;
		this.type = particle.type;
		switch(type)
		{
			case RED:
				img = new Image("res/slime_particle_red.png");
				break;
			
			case BLUE:
				img = new Image("res/slime_particle_blue.png");
				break;
				
			case YELLOW:
				img = new Image("res/slime_particle_yellow.png");
				break;
		}
		imageview = new ImageView(img);
		imageview.setFitHeight(size);
		imageview.setFitWidth(size);
	}
	
	public enum PARTICLE_TYPE
	{
		RED, BLUE, YELLOW
	}

}
