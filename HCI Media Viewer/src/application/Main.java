package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
			Model model = new Model();
			Controller controller = loader.getController();
			controller.setMain(this);
			controller.setStage(primaryStage);
			controller.setModel(model);
			scene = new Scene(rootLayout, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("HCI Media Player 2.0");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/image/logo.png")));
			primaryStage.show();
			primaryStage.setMinWidth(primaryStage.getWidth());
			primaryStage.setMinHeight(primaryStage.getHeight());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
