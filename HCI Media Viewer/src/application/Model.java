package application;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class Model {
	
	boolean loop;
	
	File selectedDirectory;

	File[] selectedFiles;
	
	File selectedFile;
	
	int fileIndex;
	
	int directorySize;
	
	public File getSelectedFile() {
		return selectedFile;
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

