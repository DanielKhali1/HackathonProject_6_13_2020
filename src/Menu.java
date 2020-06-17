import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Menu extends Application
{
	Pane pane = new Pane();
	Scene scene = new Scene(pane, 1080, 720);
	
	@Override
	public void start(Stage primaryStage)  {
		
		Button highscore = new Button("Highscore");
		Button options = new Button("Options");
		
		Button start = new Button("Play");
		start.relocate(470, 350);
		
		
		Text titletxt = new Text("Jumpy Boi");
		titletxt.relocate(320, 100);
		titletxt.setStyle("-fx-font-size: 80; -fx-font-weight: bold");
		
		highscore.relocate(438, 450);

		
		
		Button exit = new Button("X");
		exit.setStyle("-fx-background-color: red; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
		exit.relocate(1000, 20);
		
		exit.setOnMouseEntered(e->{
			exit.setStyle("-fx-text-fill: red; -fx-background-color: white; -fx-font-size: 30; -fx-border-color: red; -fx-font-weight: bold;");

		});
		exit.setOnMouseExited(e->{
			exit.setStyle("-fx-background-color: red; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
		});
		
		exit.setOnAction(e->{
			primaryStage.close();
		});
		
		
		VBox vb = new VBox();
		vb.setSpacing(40);
		vb.getChildren().addAll(start, highscore, options);
		vb.relocate(200, 250);
		
		int font_size = 40;
		
		start.setStyle("-fx-background-color: white; -fx-font-size: "+font_size+"; -fx-font-weight: bold; -fx-border-color: blue; -fx-border-width: 5");
		highscore.setStyle("-fx-background-color: white; -fx-font-size: "+font_size+"; -fx-font-weight: bold; -fx-border-color: gold; -fx-border-width: 5");
		options.setStyle("-fx-background-color: white; -fx-font-size: "+font_size+"; -fx-border-color: pink; -fx-font-weight: bold;-fx-border-width: 5");

		start.setOnMouseEntered(e->{
			start.setStyle("-fx-text-fill: white; -fx-background-color: cornflowerblue; -fx-font-size: "+font_size+"; -fx-border-color: blue; -fx-font-weight: bold;-fx-border-width: 5");
		});
		start.setOnMouseExited(e->{
			start.setStyle("-fx-background-color: white; -fx-font-size: "+font_size+"; -fx-font-weight: bold; -fx-border-color: blue; -fx-border-width: 5");
		});
		
		highscore.setOnMouseEntered(e->{
			highscore.setStyle("-fx-text-fill: brown; -fx-background-color: gold; -fx-font-size: "+font_size+"; -fx-border-color: brown; -fx-font-weight: bold;-fx-border-width: 5");
		});
		highscore.setOnMouseExited(e->{
			highscore.setStyle("-fx-background-color: white; -fx-font-size: "+font_size+"; -fx-font-weight: bold; -fx-border-color: gold; -fx-border-width: 5");
		});
		
		options.setOnMouseEntered(e->{
			options.setStyle("-fx-text-fill: white; -fx-background-color: pink; -fx-font-size: "+font_size+"; -fx-border-color: pink; -fx-font-weight: bold;-fx-border-width: 5");
		});
		options.setOnMouseExited(e->{
			options.setStyle("-fx-background-color: white; -fx-font-size: "+font_size+"; -fx-border-color: pink; -fx-font-weight: bold;-fx-border-width: 5");
		});
		
		options.setOnAction(e->{
			for(int i = 0; i < 1000; i++)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Fuck off");
				alert.setHeaderText("Fuck off");
				ImageView x = new ImageView(new Image("res/middlefinger.png"));
				x.setFitHeight(50);
				x.setFitWidth(50);
				alert.setContentText("THIS FEATURE WASN'T FINISHED YET..\nGO AWAY NOW");
				alert.setGraphic(x);
				alert.setX(Math.random() * 1000);
				alert.setY(Math.random() * 1000);
				alert.show();
				
			}
		});
		
		
		start.setOnAction(e->{
			new app().start(new Stage());
			primaryStage.close();
		});
		
		highscore.setOnAction(e->{
			new Highscore().start(new Stage());
			primaryStage.close();
		});
		
		
		pane.getChildren().addAll(titletxt, vb, exit);
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TITLE");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}