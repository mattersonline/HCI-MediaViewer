package application;

import java.io.File;

public class Model {
	File selectedFile;
	double volumeLevel = 100;
	double previousVolume = 0;
	boolean mute = false;
	
	public double getPreviousVolume() {
		return previousVolume;
	}

	public void setPreviousVolume(double previousVolume) {
		this.previousVolume = previousVolume;
	}
	
	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public double getVolumeLevel() {
		return volumeLevel;
	}

	public void setVolumeLevel(double volumeLevel) {
		this.volumeLevel = volumeLevel;
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}
}