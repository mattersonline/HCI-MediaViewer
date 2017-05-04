/*	Project: HCI Media Viewer
 * 	File: Controller.java
 * 	Date: 5/3/17
 * 	Creators: Jackson Blankenship, Mathew Borum, Christoph Kinzel, Zachary Connor
 * 	Purpose: To contain the listeners that drive the program
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable{

	// Window variables
	private Stage primaryStage;
	@SuppressWarnings("unused")
	private Main main;
	private Model model;
	private MediaPlayer player;
	
	// FXML Objects
	@FXML private Button shuffleButton; // fx:id="shuffleButton"
	@FXML private Button repeatButton;
	@FXML private Button previousButton;
	@FXML private Button playPauseButton;
	@FXML private Button nextButton;
	@FXML private Button muteButton;
	@FXML private Button fullscreenButton;

	@FXML private MediaView media;
	
	@FXML private Slider volumeSlider;
	@FXML private Slider seekSlider;
	@FXML private Slider faderSlider;
	
	@FXML private Label timeLabel;
	@FXML private Label durationLabel;
	
	@FXML private ImageView loopIcon;
	@FXML private ImageView playPause;

	//Sets the parent class
	public void setMain(Main main){
		this.main = main;
	}
	
	//Sets the stage
	public void setStage(Stage primaryStage){
		this.primaryStage = primaryStage;
	}
	
	//Sets the model
	public void setModel(Model model){
		this.model = model;
	}
	
	//Sets the player
	public void setPlayer(MediaPlayer player){
		this.player = player;
		media.setMediaPlayer(player);
	}
	
	//Handles the shuffle button
	public void handleShuffle(){
		model.shuffle();
	}
	
	//Implements keyboard shortcuts
	public void handleKeyboard(KeyEvent e){
		
		if(e.getCode() == KeyCode.S){
			shuffleButton.fire();
		}
		if(e.getCode() == KeyCode.R){
			repeatButton.fire();
		}
		if(e.getCode() == KeyCode.LEFT){
		previousButton.fire();
		}
		if(e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE){
			playPauseButton.fire();
		}
		if(e.getCode() == KeyCode.RIGHT){
			nextButton.fire();
		}
		if(e.getCode() == KeyCode.M){
			muteButton.fire();
		}
		if(e.getCode() == KeyCode.F11){
			fullscreenButton.fire();
		}
	}
	
	//Handles the repeat button
	public void handleRepeat(){
		if(model.getLoop()){
			model.setLoop(false);
			loopIcon.setImage(new Image(Main.class.getResourceAsStream("/image/repeat.png")));
		} else {
			model.setLoop(true);
			loopIcon.setImage(new Image(Main.class.getResourceAsStream("/image/repeaton.png")));
		}
	}
	
	//Handles the back button
	public void handleBack(){
		model.previousFile();
		handleChange();
	}
	
	//Handles the play button
	public void handlePlay(){
		double temp = seekSlider.getValue();
		if(player.getStatus() == MediaPlayer.Status.READY || player.getStatus() == MediaPlayer.Status.PAUSED || player.getStatus() == MediaPlayer.Status.STOPPED){
			player.play();
			durationLabel.setText(durationString(player.getStopTime()));
			playPause.setImage(new Image(Main.class.getResourceAsStream("/image/pause.png")));
		}
		else{
			player.pause();
			seekSlider.setValue(temp);
			playPause.setImage(new Image(Main.class.getResourceAsStream("/image/play.png")));
		}
	}
	
	//Handles the next button
	public void handleNext(){
		model.nextFile();
		handleChange();
	}
	
	//Handles the mute button
	public void handleMute(){
		if(!model.isMute()){
			model.setMute(true);
			volumeSlider.setDisable(true);
			model.setPreviousVolume(volumeSlider.getValue());
			volumeSlider.setValue(0);
		} else {
			model.setMute(false);
			volumeSlider.setDisable(false);
			volumeSlider.setValue(model.getPreviousVolume());
		}
	}
	
	//Handles the expand button
	public void handleExpand(){
		if(primaryStage.isFullScreen()){
			primaryStage.setFullScreen(false);
			((BorderPane) (media.getParent())).setPrefSize(primaryStage.getWidth(), primaryStage.getHeight());
		} else {
			primaryStage.setFullScreen(true);
		}
	}
	
	//Handles the open function
	public void handleOpen(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Media File");
		model.setSelectedFile(fileChooser.showOpenDialog(primaryStage));
		handleChange();
	}
	
	
	//Handles the open folder function
	public void handleOpenFolder(){
		DirectoryChooser folderChooser = new DirectoryChooser();
		folderChooser.setTitle("Open Media Folder");
		model.setSelectedDirectory(folderChooser.showDialog(primaryStage));
		handleChange();
	}
	
	//Exits the program
	public void handleExit(){
		System.exit(0);
	}
	
	//Implements the about window
	public void handleAbout(){
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help");
		alert.setHeaderText(null);
		alert.setContentText("Team Members:"
        		+ "\n - Jackson Blankenship"
        		+ "\n - Mathew Borum"
        		+ "\n - Christoph Kinzel"
        		+ "\n - Zachary Connor"
        		+ "\n\nShortcuts:"
        		+ "\n - R : Repeat"
        		+ "\n - S : Shuffle"
        		+ "\n - Space/Enter : Play/Pause"
        		+ "\n - Left : Previous"
        		+ "\n - Right : Next"
        		+ "\n - M : Mute"
        		+ "\n - F11 : Fullscreen");

		alert.showAndWait();
	}

	//Updates everything on launch
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		faderSlider.setValue(50);
		player = new MediaPlayer(new Media(Main.class.getResource("/image/default.mp3").toExternalForm()));
		media.setMediaPlayer(player);
		
		media.setOnMouseClicked((MouseEvent event) -> {
			handlePlay();
		});
		volumeSlider.setValue(100);

		player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
		    @Override
	        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
	            seekSlider.setValue(newValue.toMillis()/player.getStopTime().toMillis()*100);
		    }
		});
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
	    		model.setVolumeLevel(volumeSlider.getValue());
	    		player.setVolume(model.getVolumeLevel()/100.0);
		    }
		});
		
		faderSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
	    		model.setBalance(faderSlider.getValue());
	    		player.setBalance((model.getBalance()/50.0)-1.0);
		    }
		});
		
		seekSlider.setOnMouseReleased((MouseEvent event) -> {
			player.seek(new Duration(player.getStopTime().toMillis() * seekSlider.getValue() / 100.0));
		});
		
		seekSlider.setOnMousePressed((MouseEvent event) -> {
			player.seek(new Duration(player.getStopTime().toMillis() * seekSlider.getValue() / 100.0));
		});
	}
	
	//Handles the change in files
	public void handleChange(){
		player.stop();
		player = new MediaPlayer(new Media(model.getSelectedFile().toURI().toString()));
		primaryStage.setTitle(model.getSelectedFile().getName());
		media.setMediaPlayer(player);
		media.fitWidthProperty().bind(((BorderPane) (media.getParent())).widthProperty());
		media.fitHeightProperty().bind(((BorderPane) (media.getParent())).heightProperty());
		media.setPreserveRatio(true);
		
		player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
		    @Override
	        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
	            seekSlider.setValue(newValue.toMillis()/player.getStopTime().toMillis()*100);
	            timeLabel.setText(durationString(newValue));
	            durationLabel.setText(durationString(player.getStopTime()));
	            
	            if(player.getStopTime().toMillis() - newValue.toMillis() < 300){
	            	player.stop();
	            	if(model.getLoop()){
	            		playPause.setImage(new Image(Main.class.getResourceAsStream("/image/pause.png")));
	            		handleNext();
	            	} else
	            		playPause.setImage(new Image(Main.class.getResourceAsStream("/image/play.png")));
	            }
		    }
		});
		
		playPause.setImage(new Image(Main.class.getResourceAsStream("/image/play.png")));
		
		if(model.getLoop()){
    		player.play();
    		durationLabel.setText(durationString(player.getStopTime()));
    		playPause.setImage(new Image(Main.class.getResourceAsStream("/image/pause.png")));
    	}
	}
	
	//Checks the duration and formats the duration
	private static String durationString(Duration dur) {
		int time = (int)Math.floor(dur.toSeconds());
		int hours = time / (60 * 60);
		if (hours > 0)
			time -= hours * 60 * 60;
		int min = time / 60;
		int sec = time - hours * 60 * 60 - min * 60;
		if (hours > 0) {
			return String.format("%d:%02d:%02d", hours, min, sec);
		} else {
			return String.format("%02d:%02d",min, sec);
		}
	}
	

}





