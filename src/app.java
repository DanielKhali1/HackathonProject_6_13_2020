import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class app extends Application{

	public final int width = 1080;
	public final int height = 720;
	public final int initialPlatforms = 20;
	double playerHeight = 0;
	
	
	
	Pane pane = new Pane();
	Scene scene = new Scene(pane, width, height);
	
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	
	
	/*
	 * RUNS PER FRAME OF THE GAME
	 * 
	 */
	private void update() 
	{
		
	}

	
	/*
	 * RUNS BEFORE THE GAME LOOP STARTS
	 * 
	 */
	private void initialize() 
	{
		
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
