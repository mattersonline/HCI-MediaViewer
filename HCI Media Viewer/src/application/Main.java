package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private BorderPane rootLayout;
	public Scene scene;
	//testing
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Controller controller = loader.getController();
			controller.setMain(this);
			controller.setStage(primaryStage);
			scene = new Scene(rootLayout, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("HCI Media Viewer");;
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
