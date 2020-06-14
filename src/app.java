import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class app extends Application {

	public final int width = 1080;
	public final int height = 720;
	public final int initialPlatforms = 50;
	
	double playerVelocity = 0;
	double spawnChance = 0.08;
	public final double MOVESPEED = 10;
	public final double GRAVITY = .3;
	public final double BOUNCE_SPEED = 13;
	
	boolean movingLeft = false;
	boolean movingRight = false;
	
	Button a_Red_bt = new Button("A");
	Button s_Blue_bt = new Button("S");
	Button d_Yellow_bt = new Button("D");
	Player player = new Player(new Vector(width/2, height/2), MOVESPEED, GRAVITY);

	
	Pane pane = new Pane();
	Pane gamePane = new Pane();
	Scene scene = new Scene(pane, width, height);
	
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	ArrayList<ParticleSystem> particleSystems = new ArrayList<ParticleSystem>();
	Media m;
    MediaPlayer mp;
	int[] colors = { 1, 0, 0 };
	
	int score = 0;
	Text heighttxt;
	
	/*
	 * RUNS PER FRAME OF THE GAME
	 * 
	 */
	private void update() 
	{
		
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
				
				
				
				break;
				
			}
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
	private void initialize() 
	{
		
		try {
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
		
		
		pane.getChildren().add(gamePane);
		
		takeKeyInput();
		
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
		pane.getChildren().addAll(a_Red_bt, s_Blue_bt, d_Yellow_bt);
	}
	
	private void takeKeyInput() 
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
				player.changeImage("slimeRed.png");

			}
			if(e.getCode() == KeyCode.S)
			{
				//---------- TODO: PUT CHANGE COLOR _BLUE_ CODE HERE ----------//
				s_Blue_bt.setStyle("-fx-background-color: #3366ff; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				colors[0] = 0;
				colors[1] = 1;
				colors[2] = 0;
				player.changeImage("slimeBlue.png");
			}
			if(e.getCode() == KeyCode.D)
			{
				//---------- TODO: PUT CHANGE COLOR _YELLOW_ CODE HERE ----------//
				
				d_Yellow_bt.setStyle("-fx-background-color: #b3b300; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				colors[0] = 0;
				colors[1] = 0;
				colors[2] = 1;
				player.changeImage("slimeYellow.png");
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

	public void Game()
	{
		// initializes the game state
		initialize();
		
		// begins the game loop
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
			update();
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	@Override
	public void start(Stage primaryStage)
	{	
		Game();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hackathon Project");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
