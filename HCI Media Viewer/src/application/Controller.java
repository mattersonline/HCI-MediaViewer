package application;

public class Controller {

	private Main main;
	
	public void setMain(Main main){
		this.main = main;
	}
	
	// FIXME: Handle the shuffle feature
	public void handleShuffle(){
		System.out.println("handleShuffle");
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

}
