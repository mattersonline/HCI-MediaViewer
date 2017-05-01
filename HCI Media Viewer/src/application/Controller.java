package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller implements Initializable{

	
	
	private Main main;
	
	@FXML // fx:id="shuffleButton"
	private Button shuffleButton;
	
	@FXML
	private Button repeatButton;
	
	@FXML
	private Button previousButton;
	
	@FXML
	private Button playPauseButton;
	
	@FXML
	private Button nextButton;
	
	@FXML
	private Button muteButton;
	
	@FXML
	private Button fullscreenButton;
	
	public void setMain(Main main){
		this.main = main;
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
	}
	
	// FIXME: Handle the next media button
	public void handleNext(){
		System.out.println("handleNext");
	}
	
	// FIXME: Handle the mute button. Remember it works with the volume slider.
	public void handleMute(){
		System.out.println("handleMute");
	}
	
	// FIXME: Handle window expanding
	public void handleExpand(){
		System.out.println("handleExpand");
	}
	
	// FIXME: Handle the file opening system
	public void handleOpen(){
		System.out.println("handleOpen");
	}
	
	public void handleExit(){
		System.exit(0);
	}
	
	// FIXME: Handle the about window
	public void handleAbout(){
		System.out.println("handleAbout");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}


	

}
