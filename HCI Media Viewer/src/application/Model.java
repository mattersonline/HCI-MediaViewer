package application;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class Model {
	
	double volumeLevel = 100;
	double previousVolume = 0;
	boolean loop;
	
	File selectedDirectory;
	
	File[] selectedFiles;
	
	File selectedFile;
	
	int fileIndex;
	
	int directorySize;
	public double getPreviousVolume() {
		return previousVolume;
	}

	public void setPreviousVolume(double previousVolume) {
		this.previousVolume = previousVolume;
	}

	boolean mute = false;

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
	
	public void setSelectedDirectory(File selectedDirectory) {
		this.selectedDirectory = selectedDirectory;
		
		selectedFiles = selectedDirectory.listFiles();
		
		fileIndex = 0;
		directorySize = selectedFiles.length;
		
		selectedFile = selectedFiles[0];
		
	}
	
	public void nextFile()
	{
		if(fileIndex < directorySize - 1)
			++fileIndex;
		else
			fileIndex = 0;
		
		selectedFile = selectedFiles[fileIndex];
	}
	
	public void previousFile()
	{
		if(fileIndex > 0)
			--fileIndex;
		else
			fileIndex = directorySize - 1;
		
		selectedFile = selectedFiles[fileIndex];
	}
	
	public void shuffle()
	{
		Collections.shuffle(Arrays.asList(selectedFiles));
		fileIndex = -1;
	}
	
	boolean getLoop()
	{
		return loop;
	}
	
	void setLoop(boolean loop)
	{
		this.loop = loop;
	}
	
}