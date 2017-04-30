package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private BorderPane rootLayout;
	//testing
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout, 800, 800);
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
