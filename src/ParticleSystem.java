

import java.util.ArrayList;import com.sun.accessibility.internal.resources.accessibility;

import javafx.scene.layout.Pane;

public class ParticleSystem 
{
	ArrayList<Particle> particles = new ArrayList<Particle>();
	double lifetime;
	double speed;
	double maxAlive;
	double gravity;
	double turbulance;
	double width;
	double height;
	
	public ParticleSystem(double width, double height, Vector position, double speed, double lifetime, int maxAlive, double turbulance, double gravity, Particle ... particle_type )
	{
		this.turbulance = turbulance;
		this.lifetime = lifetime;
		this.speed = speed;
		this.maxAlive = maxAlive;
		this.gravity = gravity;
		this.width = width;
		this.height = height;
		
		
		int num_particles = particle_type.length;
		int rand = 0;
		
		for(int i = 0; i < maxAlive; i++)
		{
			rand =(int) ( Math.random() * num_particles ) ;
			for(int j = 0; j < num_particles; j++)
			{
				if(rand == j)
				{
					particles.add(new Particle(particle_type[j]));
					particles.get(i).position = new Vector(position.x, position.y);
				}
			}
			particles.get(i).rotation = (Math.random() < 0.5) ?  1 : -1;
		}
		

		
	}
	
	public void play(Pane pane)
	{
		for(int i = 0; i < particles.size(); i++)
		{
			pane.getChildren().add(particles.get(i).imageview);
			particles.get(i).imageview.setLayoutX(particles.get(i).position.x);
			particles.get(i).imageview.setLayoutY(particles.get(i).position.y);
			particles.get(i).velocity = new Vector(Math.random() *2 -1, Math.random() * 2 -1);
			particles.get(i).velocity.normalize();
			particles.get(i).velocity.multVector(speed);
		}
	}
	
	public void update(Pane pane)
	{
		for(int i = 0; i < particles.size(); i++)
		{
			particles.get(i).acceleration = new Vector(Math.random()*2 -1, Math.random()*2 -1);
			particles.get(i).acceleration.multVector(turbulance);
			particles.get(i).acceleration = new Vector(0, gravity);

			
			
			particles.get(i).velocity.addVector(particles.get(i).acceleration);
			particles.get(i).position.addVector(particles.get(i).velocity);
			
			particles.get(i).imageview.setLayoutX(particles.get(i).position.x);
			particles.get(i).imageview.setLayoutY(particles.get(i).position.y);
			
			particles.get(i).imageview.setRotate(particles.get(i).imageview.getRotate()+particles.get(i).rotation*5);
		}
		
		for(int i = 0; i < particles.size(); i++)
		{
			if(particles.get(i).position.x > width || particles.get(i).position.x < 0)
			{
				particles.get(i).velocity.x *= -1;
			}
			if( particles.get(i).position.y > height || particles.get(i).position.y < 0)
			{
				pane.getChildren().remove(particles.get(i).imageview);
				particles.remove(i);
			}
		}
	}
	
	
	
	
	

}
