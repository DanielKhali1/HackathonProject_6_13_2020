import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class app extends Application {

	public final int width = 1080;
	public final int height = 720;
	public final int initialPlatforms = 30;
	
	double playerVelocity = 5;
	double spawnChance = 0.2;
	public final double MOVESPEED = 8;
	public final double GRAVITY = .1;
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
	
	int[] colors = { 1, 0, 0 };
	
	
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
		
		System.out.println(player.position.x + ", " + player.position.y);
		player.updatePos();
		
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
			
			platforms.get(i).getRect().setLayoutY(platforms.get(i).getRect().getLayoutY()+playerVelocity);
			if(platforms.get(i).getRect().getLayoutY() > height)
			{
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
		
		pane.getChildren().add(gamePane);
		
		takeKeyInput();
		
		/*a_Red_bt.setDisable(true);
		s_Blue_bt.setDisable(true);
		d_Yellow_bt.setDisable(true);
		*/
		a_Red_bt.relocate(100, 630);
		a_Red_bt.setStyle("-fx-background-color: red; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
		s_Blue_bt.relocate(200, 630);
		s_Blue_bt.setStyle("-fx-background-color: lightblue; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");

		d_Yellow_bt.relocate(300, 630);
		d_Yellow_bt.setStyle("-fx-background-color: yellow; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");

		
		
		Text heighttxt = new Text(playerVelocity+"");
		heighttxt.setStyle("-fx-font-size: 20;");
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

			}
			if(e.getCode() == KeyCode.S)
			{
				//---------- TODO: PUT CHANGE COLOR _BLUE_ CODE HERE ----------//
				s_Blue_bt.setStyle("-fx-background-color: #3366ff; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				colors[0] = 0;
				colors[1] = 1;
				colors[2] = 0;
			}
			if(e.getCode() == KeyCode.D)
			{
				//---------- TODO: PUT CHANGE COLOR _YELLOW_ CODE HERE ----------//
				
				d_Yellow_bt.setStyle("-fx-background-color: #b3b300; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
				colors[0] = 0;
				colors[1] = 0;
				colors[2] = 1;
				
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

				//---------- TODO: PUT CHANGE COLOR _RED_ CODE HERE ----------//
			}
			if(e.getCode() == KeyCode.S)
			{
				//---------- TODO: PUT CHANGE COLOR _BLUE_ CODE HERE ----------//
				s_Blue_bt.setStyle("-fx-background-color: lightblue; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
			}
			if(e.getCode() == KeyCode.D)
			{
				//---------- TODO: PUT CHANGE COLOR _YELLOW_ CODE HERE ----------//
				d_Yellow_bt.setStyle("-fx-background-color: yellow; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
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
