package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable{

	// Window variables
	private Stage primaryStage;
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

	public void setMain(Main main){
		this.main = main;
	}
	
	public void setStage(Stage primaryStage){
		this.primaryStage = primaryStage;
	}
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public void setPlayer(MediaPlayer player){
		this.player = player;
		media.setMediaPlayer(player);
	}
	
	// FIXME: Handle the shuffle feature
	public void handleShuffle(){
		System.out.println("handleShuffle");
	}
	
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
	
	// FIXME: Handle the repeat feature
	public void handleRepeat(){
		System.out.println("handleRepeat");
	}
	
	// FIXME: Handle the previous media button
	public void handleBack(){
		System.out.println("handleBack");
	}
	
	// FIXME: Handle the play button
	public void handlePlay(){
		System.out.println("handlePlay");
		System.out.println(player.getMedia().getSource());
		if(player.getStatus() == MediaPlayer.Status.READY || player.getStatus() == MediaPlayer.Status.PAUSED || player.getStatus() == MediaPlayer.Status.STOPPED)
			player.play();
		else
			player.pause();
		System.out.println(player.getStatus());
	}
	
	// FIXME: Handle the next media button
	public void handleNext(){
		System.out.println("handleNext");
	}
	
	// FIXME: Handle the mute button. Remember it works with the volume slider.
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
	
	// TODO: Window will expand properly, however we need to find a way to hide certain controls on full-screen
	public void handleExpand(){
		if(primaryStage.isFullScreen()){
			primaryStage.setFullScreen(false);
		} else {
			primaryStage.setFullScreen(true);
		}
	}
	
	// FIXME: This opens one single file right now, needs to be expanded
	public void handleOpen(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		model.setSelectedFile(fileChooser.showOpenDialog(primaryStage));
		player = new MediaPlayer(new Media(model.getSelectedFile().toURI().toString()));
	}
	
	public void handleExit(){
		System.exit(0);
	}
	
	// FIXME: Put your names in the about window!!
	/**
	 * @Source StackOverflow (http://stackoverflow.com/questions/22166610/how-to-create-a-popup-windows-in-javafx)
	 */
	public void handleAbout(){
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Team Members:"
        		+ "\n - Jackson Blankenship"
        		+ "\n - PUT YOUR NAMES HERE"
        		+ "\n\nShortcuts:"
        		+ "\n - CTRL-O : Open file"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		player = new MediaPlayer(new Media(Main.class.getResource("/image/default.mp4").toExternalForm()));
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
	    		model.setVolumeLevel(volumeSlider.getValue());
		    }
		});
	}


	

}
