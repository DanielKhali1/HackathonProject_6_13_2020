

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class test extends Application
{
	Pane pane = new Pane();
	Scene scene = new Scene(pane, 700, 700);

	ParticleSystem p;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		
		p = new ParticleSystem(500, 500,new Vector(500/2, 500/2), 5, 2, 50, 2,0.3,new Particle(5, Particle.PARTICLE_TYPE.RED), new Particle(5, Particle.PARTICLE_TYPE.YELLOW));
		p.play(pane);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
			p.update(pane);
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Test Environment");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
