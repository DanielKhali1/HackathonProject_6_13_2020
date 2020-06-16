import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    	Text hiscore_txt = new Text("LooksPlease enter your name for the scoreboard.");
    	TextField hiscore_txtfld = new TextField("Danny");
        
    	restart_bt.setStyle("-fx-background-color: white; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
    	restart_bt.relocate(200, 450);
    	mainmenu_bt.setStyle("-fx-background-color: white; -fx-font-size: 30; -fx-border-color: black; -fx-border-width: 5");
    	mainmenu_bt.relocate(400, 450);
    	
    	pane.getChildren().addAll(restart_bt, mainmenu_bt, hiscore_txt, hiscore_txtfld);
    	
        primaryStage.setScene(scene);
        primaryStage.setTitle("YOU DIED");
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
