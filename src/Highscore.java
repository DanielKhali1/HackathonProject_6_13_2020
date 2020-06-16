

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Highscore extends Application
{
	Pane pane = new Pane();
	Scene scene = new Scene(pane, 1080, 720);
	 ArrayList<Record> arlist = new ArrayList<Record>();

	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			arlist = HighscoreFileOperations.grabHighScores();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		Button highscore = new Button("Highscore");
		
		Button start = new Button("Play");
		start.relocate(470, 350);
		
		
		Text titletxt = new Text("Highscore");
		titletxt.relocate(350, 100);
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
		
		Button back = new Button("<");
		back.setStyle("-fx-text-fill: black; -fx-background-color: gold; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
		back.relocate(20, 20);
		
		back.setOnMouseEntered(e->{
			back.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-font-size: 30; -fx-border-color: gold; -fx-font-weight: bold;");

		});
		back.setOnMouseExited(e->{
			back.setStyle("-fx-text-fill: black; -fx-background-color: gold; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
		});
		
		back.setOnAction(e->{
			new Menu().start(new Stage());
			primaryStage.close();
		});
		
		
		VBox v = new VBox();
		v.relocate(100, 200);
		
		
		
		
		for(int i = 0 ; i < arlist.size(); i++)
		{
			
			
			
			if(i <= 5)
			{
				HBox h = new HBox();
				Button rank = new Button(i+1 + "");
				Button name = new Button(arlist.get(i).name);
				Button score = new Button(arlist.get(i).score +"");
				name.setPrefWidth(400);
				score.setPrefWidth(400);
				
				rank.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
				
				if(rank.getText().equals("1"))
					rank.setStyle("-fx-text-fill: black; -fx-background-color: gold; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
				else if(rank.getText().equals("2"))
					rank.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
				else if(rank.getText().equals("3"))
					rank.setStyle("-fx-text-fill: white; -fx-background-color: brown; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");

				
				if(i % 2 == 0)
				{
					name.setStyle("-fx-text-fill: black; -fx-background-color: #b3d9ff; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
					score.setStyle("-fx-text-fill: black; -fx-background-color: #b3d9ff; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
				}
				else
				{
					name.setStyle("-fx-text-fill: black; -fx-background-color: #cce6ff; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
					score.setStyle("-fx-text-fill: black; -fx-background-color: #cce6ff; -fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold;");
				}

				h.getChildren().addAll(rank, name, score);
				v.getChildren().add(h);
			}
		}
		
		
		
		pane.getChildren().addAll(titletxt, exit, back, v);
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TITLE");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}