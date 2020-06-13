import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class app extends Application {

	public final int width = 1080;
	public final int height = 720;
	public final int initialPlatforms = 30;
	double playerVelocity = 5;
	double spawnChance = 0.2;
	
	Button a_Red_bt = new Button("A");
	Button s_Blue_bt = new Button("S");
	Button d_Yellow_bt = new Button("D");

	
	Pane pane = new Pane();
	Pane gamePane = new Pane();
	Scene scene = new Scene(pane, width, height);
	
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	/*
	 * RUNS PER FRAME OF THE GAME
	 * 
	 */
	private void update() 
	{
		updatePlatformHeight();
	}	
	
	void updatePlatformHeight()
	{
		//make a random chance of spawning
		
		for(int i = 0; i < platforms.size(); i++)
		{
			platforms.get(i).getRect().setLayoutY(platforms.get(i).getRect().getLayoutY()+playerVelocity);
			if(platforms.get(i).getRect().getLayoutY() > height)
			{
				platforms.remove(i);
				i--;
				
				for(int j = 0; j < 5; j++)
				{
					
					if(Math.random() < spawnChance)
					{
						platforms.add(new Platform((int)((width * Math.random())/100) * 100 ,-20, 100, 20));
						gamePane.getChildren().add(platforms.get(platforms.size()-1).getRect());
						
					}
				}
			}
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
		
		a_Red_bt.relocate(100, 630);
		a_Red_bt.setStyle("-fx-background-color: red; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
		s_Blue_bt.relocate(200, 630);
		d_Yellow_bt.relocate(300, 630);
		
		
		
		Text heighttxt = new Text(playerVelocity+"");
		heighttxt.setStyle("-fx-font-size: 20;");
		heighttxt.relocate(20, 20);
		pane.getChildren().add(heighttxt);
		
		
		for(int i = 0; i < initialPlatforms; i++)
		{
			platforms.add(new Platform((int)((width * Math.random())/100) * 100 ,(int)((height * Math.random())/20) * 20, 100, 20));
			gamePane.getChildren().add(platforms.get(i).getRect());
		}
		
		
		
		pane.getChildren().addAll(a_Red_bt, s_Blue_bt, d_Yellow_bt);
	}
	
	private void takeKeyInput() 
	{
		scene.setOnKeyPressed
		(e->{
			if(e.getCode() == KeyCode.RIGHT)
			{
				//---------- TODO: PUT MOVE RIGHT CODE HERE ----------//
				System.out.println("You hit the right button");
			}
			if(e.getCode() == KeyCode.LEFT)
			{
				//---------- TODO: PUT MOVE LEFT CODE HERE ----------//
				System.out.println("You hit the left button");
			}
			if(e.getCode() == KeyCode.A)
			{
				a_Red_bt.setStyle("-fx-background-color: darkred; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");

				//---------- TODO: PUT CHANGE COLOR _RED_ CODE HERE ----------//
				System.out.println("You hit the A button");
			}
			if(e.getCode() == KeyCode.S)
			{
				//---------- TODO: PUT CHANGE COLOR _BLUE_ CODE HERE ----------//
				System.out.println("You hit the S button");
			}
			if(e.getCode() == KeyCode.D)
			{
				//---------- TODO: PUT CHANGE COLOR _YELLOW_ CODE HERE ----------//
				System.out.println("You hit the D button");
			}
		});
		
		scene.setOnKeyReleased
		(e->{
			if(e.getCode() == KeyCode.RIGHT)
			{
				//---------- TODO: PUT MOVE RIGHT CODE HERE ----------//
				System.out.println("You hit the right button");
			}
			if(e.getCode() == KeyCode.LEFT)
			{
				//---------- TODO: PUT MOVE LEFT CODE HERE ----------//
				System.out.println("You hit the left button");
			}
			if(e.getCode() == KeyCode.A)
			{
				a_Red_bt.setStyle("-fx-background-color: red; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");

				//---------- TODO: PUT CHANGE COLOR _RED_ CODE HERE ----------//
				System.out.println("You hit the A button");
			}
			if(e.getCode() == KeyCode.S)
			{
				//---------- TODO: PUT CHANGE COLOR _BLUE_ CODE HERE ----------//
				System.out.println("You hit the S button");
			}
			if(e.getCode() == KeyCode.D)
			{
				//---------- TODO: PUT CHANGE COLOR _YELLOW_ CODE HERE ----------//
				System.out.println("You hit the D button");
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
