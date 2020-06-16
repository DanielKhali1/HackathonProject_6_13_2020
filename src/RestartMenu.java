import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RestartMenu extends Application
{
	int score = 0;
	
	Pane pane = new Pane();
    Scene scene = new Scene(pane, 1080, 720);
    
    public RestartMenu()
    {
    	
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
    	Button restart_bt = new Button("RESTART");
    	Button mainmenu_bt = new Button("MAIN MENU");
    	Text death_txt = new Text("Looks like you chose the wrong color!\nPlease enter your name for the scoreboard.");
    	Text score_txt = new Text("Your Score: " + score);
    	TextField hiscore_txtfld = new TextField();
    	
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
        
    	death_txt.setTextAlignment(TextAlignment.CENTER);
    	death_txt.setStyle("-fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold");
    	death_txt.relocate(225, 150);
    	
    	score_txt.setStyle("-fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold");
    	score_txt.relocate(390, 300);
    	
    	hiscore_txtfld.relocate(390, 365);
    	hiscore_txtfld.setPrefSize(300, 50);
    	hiscore_txtfld.setStyle("-fx-font-size: 30; -fx-border-color: black; -fx-font-weight: bold");
    	
    	restart_bt.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-font-size: 30; -fx-border-color: gold; -fx-border-width: 5; -fx-font-weight: bold");
    	restart_bt.relocate(310, 480);
    	restart_bt.setOnMouseEntered(e->{
			restart_bt.setStyle("-fx-text-fill: brown; -fx-background-color: yellow; -fx-font-size: 30; -fx-border-color: gold; -fx-border-width: 5; -fx-font-weight: bold;");

		});
		restart_bt.setOnMouseExited(e->{
			restart_bt.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-font-size: 30; -fx-border-color: gold; -fx-border-width: 5; -fx-font-weight: bold");
		});
    	
    	mainmenu_bt.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-font-size: 30; -fx-border-color: blue; -fx-border-width: 5; -fx-font-weight: bold");
    	mainmenu_bt.relocate(520, 480);
    	mainmenu_bt.setOnMouseEntered(e->{
			mainmenu_bt.setStyle("-fx-text-fill: white; -fx-background-color: cornflowerblue; -fx-font-size: 30; -fx-border-color: blue; -fx-border-width: 5; -fx-font-weight: bold;");

		});
		mainmenu_bt.setOnMouseExited(e->{
			mainmenu_bt.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-font-size: 30; -fx-border-color: blue; -fx-border-width: 5; -fx-font-weight: bold");
		});
    	
    	pane.getChildren().addAll(restart_bt, mainmenu_bt, exit, death_txt, score_txt, hiscore_txtfld);
    	
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("YOU DIED");
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
