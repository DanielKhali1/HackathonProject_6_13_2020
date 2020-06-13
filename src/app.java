import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class app extends Application{

	public final int width = 1080;
	public final int height = 720;
	
	
	
	Pane pane = new Pane();
	Scene scene = new Scene(pane, width, height);
	
	
	@Override
	public void start(Stage primaryStage)
	{
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hackathon Project");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
