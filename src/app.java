import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class app extends Application {

	
	public final int width = 1080;
	public final int height = 720;
	public final int initialPlatforms = 50;
	
	double playerVelocity = 0;
	double spawnChance = 0.1;
	public final double MOVESPEED = 10;
	public final double GRAVITY = .3;
	public final double BOUNCE_SPEED = 13;
	public final int IMAGESIZE = 60; 
	public final double background_Entity_Spawn_Chance = 0.03;
	
	boolean movingLeft = false;
	boolean isDead = false; 
	boolean movingRight = false;
	
	Button a_Red_bt = new Button("A");
	Button s_Blue_bt = new Button("S");
	Button d_Yellow_bt = new Button("D");
	Text how2play_txt = new Text("Use 'A', 'S', and 'D' to change colors and your right and left\narrow keys to move. Press any button to start.");
	Player player = new Player(new Vector(width/2, height/2), MOVESPEED, GRAVITY, IMAGESIZE);

	Button play_bt = new Button("PLAY");
	Button hiscore_bt = new Button("HI-SCORE");
	
	Pane pane = new Pane();
	Pane gamePane = new Pane();
	Pane titlePane = new Pane();
	Scene scene = new Scene(pane, width, height);
	Pane backgroundPane = new Pane();
	
	
	
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<Entity> background_stuff = new ArrayList<Entity>(); 
	ArrayList<ParticleSystem> particleSystems = new ArrayList<ParticleSystem>();
	Media m;
    MediaPlayer mp;
    Media sfx;
    MediaPlayer mpsfx;
    
	Media switchm;
	MediaPlayer switchc;
    
	int[] colors = { 1, 0, 0 };
	
	int score = 0;
	
	Text heighttxt;
	Text titletxt;
	Text deathtxt; 
	
	Timeline timeline;
	boolean start = true;
	
	/*
	 * RUNS PER FRAME OF THE GAME
	 * 
	 */
	private void update(Stage primaryStage) 
	{
		if(score > 20000)
			pane.setStyle("-fx-background-color: #15243d;");
		else if(score > 19000)
			pane.setStyle("-fx-background-color: #1f3252;");
		else if(score > 18000)
			pane.setStyle("-fx-background-color: #283e63;");
		else if(score > 17000)
			pane.setStyle("-fx-background-color: #2f4770;");
		else if(score > 16000)
			pane.setStyle("-fx-background-color: #37517d;");
		else if(score > 15000)
			pane.setStyle("-fx-background-color: #3f5b8a;");
		else if(score > 14000)
			pane.setStyle("-fx-background-color: #4c6b9e;");
		else if(score > 13000)
			pane.setStyle("-fx-background-color: #5576ab;");
		else if(score > 12000)
			pane.setStyle("-fx-background-color: #5e80b8;");
		else if(score > 11000)
			pane.setStyle("-fx-background-color: #6b8fc9;");
		else if(score > 10000)
			pane.setStyle("-fx-background-color: #7298d4;");
		else if(score > 9000)
			pane.setStyle("-fx-background-color: #84a9e3;");
		else if(score > 8000)
			pane.setStyle("-fx-background-color: #9dbef2;");
		else if(score > 7000)
			pane.setStyle("-fx-background-color: #b5d1ff;");
		else if(score > 6000)
			pane.setStyle("-fx-background-color: #bfd7ff;");
		else if(score > 5000)
			pane.setStyle("-fx-background-color: #cddefa;");
		

		
		
		
		if(Math.random() < background_Entity_Spawn_Chance)
		{
			if(score < 10000)
			{
				if(Math.random()>.02)
				{
					background_stuff.add(new Entity((Math.random() < 0.5) ? Entity.TYPE.CLOUD_1 : Entity.TYPE.CLOUD_2, ((int)(Math.random() + 0.5)) == 1, 1, Math.random() * height));
					backgroundPane.getChildren().add(background_stuff.get(background_stuff.size()-1).body);
				}
				
				else
				{
					background_stuff.add(new Entity(Entity.TYPE.PLANE, true, 3, Math.random() * height));
					backgroundPane.getChildren().add(background_stuff.get(background_stuff.size()-1).body);
				}
			}
			else
			{
				if(Math.random() < 0.2)
				{
					background_stuff.add(new Entity((Math.random() < 0.5) ? Entity.TYPE.PLANET_1 : Entity.TYPE.PLANET_2, ((int)(Math.random() + 0.5)) == 1, 0, Math.random() * height));
					backgroundPane.getChildren().add(background_stuff.get(background_stuff.size()-1).body);
				}
				else
				{
					background_stuff.add(new Entity(Entity.TYPE.STAR, false, 0, Math.random() * height));
					backgroundPane.getChildren().add(background_stuff.get(background_stuff.size()-1).body);
				}
			}
		}
		for(int i = 0; i < background_stuff.size(); i++)
		{
			background_stuff.get(i).update(playerVelocity);
			if(background_stuff.get(i).body.getLayoutX() > width+background_stuff.get(i).body.getFitWidth() || background_stuff.get(i).body.getLayoutX() < 0-background_stuff.get(i).body.getFitWidth() )
			{
				gamePane.getChildren().remove(background_stuff.get(i).body);
				background_stuff.remove(i);
			}
		}
		
		updatePlatform();
		
			
		
		
		if(movingRight)
		{
			player.moveRight();
		}
		
		if(movingLeft)
		{
			player.moveLeft();
		}
		
		player.updatePos();
		
		
		if(player.velocity.y < 0)
			score += Math.abs(player.velocity.y);
		
		heighttxt.setText("Score: " + score+"");
		
		
		for(int i = 0 ; i < particleSystems.size(); i++)
		{
			if(particleSystems.get(i).particles.size() == 0)
			{
				particleSystems.remove(i);
				break;
			}
		}
		
		for(int i = 0 ; i < particleSystems.size(); i++)
		{
			particleSystems.get(i).update(gamePane);
		}
		
		
		/**
		 * FUNCTIONALITY THAT FORCES PLAYER (IF OUT OF BOUNDS) TELEPORT TO OTHER SIDE OF SCREEN
		 */
		if(player.position.x < -player.playerBody.getFitWidth()) player.position.x = width;
		if(player.position.x > width) player.position.x = 0-player.playerBody.getFitWidth();
		
		if(player.position.y < 10)
		{
			playerVelocity = 20;
			if(player.velocity.y < 0)
				player.velocity.multVector(0.5);
		}
		else if((player.position.y < height/1.2 && player.velocity.y < 0 ))
			playerVelocity = -player.velocity.y;
		else if((player.position.y < height/1.5 && player.velocity.y < 0 ))
			playerVelocity = -player.velocity.y;
		else
			playerVelocity = 0;

		
		if(player.velocity.y > 0)
		{
			for(int i = 0; i < platforms.size(); i++)
			{
				if (player.playerBody.getLayoutX() < platforms.get(i).getRect().getLayoutX() + platforms.get(i).getRect().getWidth() &&
						player.playerBody.getLayoutX()+player.playerBody.getFitWidth() > platforms.get(i).getRect().getLayoutX() &&
						player.playerBody.getLayoutY() < platforms.get(i).getRect().getLayoutY() + platforms.get(i).getRect().getHeight() &&
						player.playerBody.getLayoutY() + player.playerBody.getFitHeight() > platforms.get(i).getRect().getLayoutY()
						&& player.velocity.y > 0 && ( ( colors[0] == 1 && platforms.get(i).color == 1) 
								|| (colors[2] == 1 && platforms.get(i).color == 3)
								|| (colors[1] == 1 && platforms.get(i).color == 2))) 
				{
					mpsfx = new MediaPlayer(sfx);
					player.position.y = platforms.get(i).getRect().getLayoutY() - player.playerBody.getFitHeight();
					player.velocity = new Vector(0, -BOUNCE_SPEED);
					
					if(platforms.get(i).color == 1)
					{
						particleSystems.add(new ParticleSystem(width, height, player.position.clone(), 4, 2, 10, 2,0.3,new Particle(25, Particle.PARTICLE_TYPE.RED)));
						particleSystems.get(particleSystems.size()-1).play(gamePane);
					}
					else if(platforms.get(i).color == 3)
					{
						particleSystems.add(new ParticleSystem(width, height, player.position.clone(), 4, 2, 10, 2,0.3,new Particle(25, Particle.PARTICLE_TYPE.YELLOW)));
						particleSystems.get(particleSystems.size()-1).play(gamePane);
					}
					else if(platforms.get(i).color == 2)
					{
						particleSystems.add(new ParticleSystem(width, height, player.position.clone(), 4, 2, 10, 2,0.3,new Particle(25, Particle.PARTICLE_TYPE.BLUE)));
						particleSystems.get(particleSystems.size()-1).play(gamePane);
					}				
					gamePane.getChildren().remove(platforms.get(i).getRect());
					
					platforms.remove(i);
					
	
					
					sfx = new Media(getClass().getResource("res/boingboi.wav").toString());
					mpsfx = new MediaPlayer(sfx);
					mpsfx.play();
	
					break;
					
				}
				
			}
		}
		
		if (player.position.y > height)
		{
			mp.stop();
			timeline.stop();
			isDead = true; 
			new RestartMenu(score).start(new Stage());
			primaryStage.close();
		}
	}	
	
	void updatePlatform()
	{
		//make a random chance of spawning
		for(int i = 0; i < platforms.size(); i++)
		{
			//if red
			if( ( colors[0] == 1 && platforms.get(i).color == 1) 
					|| (colors[2] == 1 && platforms.get(i).color == 3)
					|| (colors[1] == 1 && platforms.get(i).color == 2) )
				platforms.get(i).getRect().setStyle("-fx-opacity: 1");
			else
				platforms.get(i).getRect().setStyle("-fx-opacity: 0.2");
			
			platforms.get(i).getRect().setLayoutY(platforms.get(i).getRect().getLayoutY()+Math.abs(playerVelocity));
			if(platforms.get(i).getRect().getLayoutY() > height)
			{
				//mpsfx.play();
				gamePane.getChildren().remove(platforms.get(i).getRect());
				platforms.remove(i);
				i--;
			}
		}
		
		if(Math.random() < spawnChance)
		{
			platforms.add(new Platform((int)((width * Math.random())/100) * 100 ,-20, 100, 20));
			gamePane.getChildren().add(platforms.get(platforms.size()-1).getRect());
			
		}
	}
	
	/*
	 * RUNS BEFORE THE GAME LOOP STARTS
	 * 
	 */
	private void initialize(Stage primaryStage) 
	{

		pane.setStyle("-fx-background-color:#c4feff" );
		try {
			switchm = new Media(getClass().getResource("res/switch.wav").toString());
			switchc = new MediaPlayer(switchm);
		    ((Pane)scene.getRoot()).getChildren().add(new MediaView(switchc));
			m = new Media(getClass().getResource("res/BeepBox-Song.wav").toString());
		    mp = new MediaPlayer(m);
//	        Runnable onEnd = new Runnable() {
//	            @Override
//	            public void run() {
	                mp.dispose();
	                mp = new MediaPlayer(m);
	                mp.setAutoPlay(true);
	                mp.setCycleCount(MediaPlayer.INDEFINITE);
	                mp.play();
//	            }
//	        };
//	        mp.setOnEndOfMedia(onEnd);
//	        mp.play();
	    } catch (Exception ex) {
	        Alert al = new Alert(AlertType.ERROR);
	        al.setContentText(ex.getMessage());
	        al.showAndWait();
	        ex.printStackTrace();
		}
		
		sfx = new Media(getClass().getResource("res/boingboi.mp3").toString());
		mpsfx = new MediaPlayer(sfx);
		gamePane.getChildren().add(backgroundPane);
		pane.getChildren().add(gamePane);
		pane.getChildren().add(titlePane);
		
		takeKeyInput(primaryStage);
		
		how2play_txt.relocate(130, 280);
		how2play_txt.setTextAlignment(TextAlignment.CENTER);
		how2play_txt.setStyle("-fx-font-size: 30; -fx-font-weight: bold");
		
		a_Red_bt.relocate(100, 630);
		a_Red_bt.setStyle("-fx-background-color: red; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
		s_Blue_bt.relocate(200, 630);
		s_Blue_bt.setStyle("-fx-background-color: lightblue; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
		d_Yellow_bt.relocate(300, 630);
		d_Yellow_bt.setStyle("-fx-background-color: yellow; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");

		
		heighttxt = new Text("Score: " + score+"");
		heighttxt.setStyle("-fx-font-size: 30; -fx-font-weight: bold");
		heighttxt.relocate(20, 20);
		pane.getChildren().add(heighttxt);
		
		for(int i = 0; i < initialPlatforms; i++)
		{
			platforms.add(new Platform((int)((width * Math.random())/100) * 100 ,(int)((height * Math.random())/20) * 20, 100, 20));
			gamePane.getChildren().add(platforms.get(i).getRect());
		}
		
		
		pane.getChildren().add(player.playerBody);
		pane.getChildren().addAll(a_Red_bt, s_Blue_bt, d_Yellow_bt, how2play_txt);
	}
	
	private void takeKeyInput(Stage primaryStage) 
	{
		scene.setOnKeyPressed
		(e->{
			if(e.getCode() == KeyCode.RIGHT)
			{
				movingRight = true; 
				//---------- TODO: PUT MOVE RIGHT CODE HERE ----------//
			}
			if(e.getCode() == KeyCode.LEFT)
			{
				movingLeft = true; 
				//---------- TODO: PUT MOVE LEFT CODE HERE ----------//
			}
			if(e.getCode() == KeyCode.A)
			{

				//---------- TODO: PUT CHANGE COLOR _RED_ CODE HERE ----------//
				a_Red_bt.setStyle("-fx-background-color: darkred; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				colors[0] = 1;
				colors[1] = 0;
				colors[2] = 0;
				player.changeImage("redboi.gif");

			}
			if(e.getCode() == KeyCode.S)
			{
				//---------- TODO: PUT CHANGE COLOR _BLUE_ CODE HERE ----------//
				s_Blue_bt.setStyle("-fx-background-color: #3366ff; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				colors[0] = 0;
				colors[1] = 1;
				colors[2] = 0;
				player.changeImage("blueboi.gif");
			}
			if(e.getCode() == KeyCode.D)
			{
				//---------- TODO: PUT CHANGE COLOR _YELLOW_ CODE HERE ----------//
				
				d_Yellow_bt.setStyle("-fx-background-color: #b3b300; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				colors[0] = 0;
				colors[1] = 0;
				colors[2] = 1;
				player.changeImage("yellowboi.gif");
			}
			if(e.getCode() == KeyCode.R)
			{
				if(isDead == true)
				{
					System.exit(1);
				}
			}
			
			if(e.getCode() == KeyCode.A || e.getCode() == KeyCode.S || e.getCode() == KeyCode.D )
			{
				switchc.seek(switchc.getStartTime());	
				switchc.play();
			}
			
			if(start)
			{
				how2play_txt.setVisible(false);
				timeline.play();
				start = false;
			}
			
		});
		
		scene.setOnKeyReleased
		(e->{
			if(e.getCode() == KeyCode.RIGHT)
			{
				movingRight = false;
				//---------- TODO: PUT MOVE RIGHT CODE HERE ----------//
			}
			if(e.getCode() == KeyCode.LEFT)
			{
				movingLeft = false; 
				//---------- TODO: PUT MOVE LEFT CODE HERE ----------//
			}
			if(e.getCode() == KeyCode.A)
			{
				a_Red_bt.setStyle("-fx-background-color: red; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				particleSystems.add(new ParticleSystem(width, height, player.position.clone(), 5, 2, 10, 2,0.3,new Particle(25, Particle.PARTICLE_TYPE.RED)));
				particleSystems.get(particleSystems.size()-1).play(gamePane);

				//---------- TODO: PUT CHANGE COLOR _RED_ CODE HERE ----------//
			}
			if(e.getCode() == KeyCode.S)
			{
				//---------- TODO: PUT CHANGE COLOR _BLUE_ CODE HERE ----------//
				s_Blue_bt.setStyle("-fx-background-color: lightblue; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				particleSystems.add(new ParticleSystem(width, height, player.position.clone(), 5, 2, 10, 2,0.3,new Particle(25, Particle.PARTICLE_TYPE.BLUE)));
				particleSystems.get(particleSystems.size()-1).play(gamePane);
			}
			if(e.getCode() == KeyCode.D)
			{
				//---------- TODO: PUT CHANGE COLOR _YELLOW_ CODE HERE ----------//
				d_Yellow_bt.setStyle("-fx-background-color: yellow; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				particleSystems.add(new ParticleSystem(width, height, player.position.clone(), 5, 2, 10, 2,0.3,new Particle(25, Particle.PARTICLE_TYPE.YELLOW)));
				particleSystems.get(particleSystems.size()-1).play(gamePane);
			}
		});
	}

	public void Game(Stage primaryStage)
	{
		// initializes the game state
		initialize(primaryStage);
		
		// begins the game loop
		timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
			update(primaryStage);
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	@Override
	public void start(Stage primaryStage)
	{	
		Game(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Only One Color Keeping It Alive");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

class Entity
{
	public enum TYPE{
			CLOUD_1,
			CLOUD_2,
			STAR,
			PLANET_1,
			PLANET_2,
			PLANE,
	};
	
	ImageView body;
	private TYPE type;
	private boolean direction;
	private double speed;
	
	public Entity(TYPE type, boolean direction, double speed, double yPosition)
	{
		this.type = type;
		this.direction =direction;
		this.speed = speed;
		
		
		
		switch(type)
		{
			case CLOUD_1:
					body = new ImageView(new Image("res/Cloud_1.png"));
					if(Math.random() < 0.5)
						body.relocate(((direction)?1080:0-body.getFitWidth()), yPosition);
					else
						body.relocate(1080*Math.random(), -200);
				break;
				
			case CLOUD_2:
					body = new ImageView(new Image("res/Cloud_2.png"));
					if(Math.random() < 0.5)
						body.relocate(((direction)?1080:0-body.getFitWidth()), yPosition);
					else
						body.relocate(1080*Math.random(), -200);
				break;
			case STAR:
				body = new ImageView(new Image("res/starboi.gif"));
				body.relocate(1080*Math.random(), -200);

			break;
			
			case PLANET_1:
				body = new ImageView(new Image("res/planetboi.gif"));
				body.relocate(1080*Math.random(), -200);

			break;
			
			case PLANET_2:
				body = new ImageView(new Image("res/planetboi_2.gif"));
				body.relocate(1080*Math.random(), -200);

			break;
			
			case PLANE:
				body = new ImageView(new Image("res/planeboi.gif"));
				body.relocate(1180, 720*Math.random());

			break;
		}
		if(type == TYPE.CLOUD_2 || type == TYPE.CLOUD_1)
		{	
			body.setFitHeight(100 * Math.random() + 100);
			body.setFitWidth(100 * Math.random() + 100);
		}
		
		else
		{	
			body.setFitHeight(100);
			body.setFitWidth(100);
		}
	}
	
	public void update(double vel)
	{
		body.setLayoutX(body.getLayoutX() + ((direction)? -speed : speed));
		body.setLayoutY(body.getLayoutY()+vel*0.2);
	}
	
}
