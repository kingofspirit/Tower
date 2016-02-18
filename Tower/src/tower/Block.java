package tower;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Block {
	
	
	boolean isChecked;
	String kindOfBlock = "grass";
	String name;
	Pane basePane = new Pane();
	
	//int a =
	
	Block() {
		this.isChecked = false;
	}
	
	static void createMap(int size[]) {
		
		//MainPage.mapData = new Block[size[0]][size[1]];
		MainPage.mapData = new Block[5][5];
	}
	
	
	static boolean mapCanUse() {
		
		
		int coordinateEntryX = 0;
		int coordinateEntryY = 0;
		int coordinateExitX = 0;
		int coordinateExitY = 0;
		
		int errorAmount;
		
		//check the entry
		
		errorAmount = 0;
		for(int i = 0; i < MainPage.mapData.length; i++)
			for(int j = 0; j < MainPage.mapData[i].length; j++) {
				if(MainPage.mapData[i][j].kindOfBlock == "entry") {
					coordinateEntryX = i;
					coordinateEntryY = j;
					
					
					errorAmount++;
				}
			}
		
		if(errorAmount != 1) {
			if(errorAmount == 0) {
				errorDialog.errorDialogCorrect("Error #1, no Entry!");
				return false;
			}
				
			else if(errorAmount >= 2) {
				errorDialog.errorDialogCorrect("Error #2, more than one Entry!");
				return false;
			}
				
		}
		
		
		//check the exit
		
		errorAmount = 0;
		for(int i = 0; i < MainPage.mapData.length; i++)
			for(int j = 0; j < MainPage.mapData[i].length; j++) {
				if(MainPage.mapData[i][j].kindOfBlock == "exit") {
					coordinateExitX = i;
					coordinateExitY = j;
					
					errorAmount++;
				}
			}
		
		if(errorAmount != 1) {
			if(errorAmount == 0) {
				errorDialog.errorDialogCorrect("Error #3, no Exit!");
				return false;
			}
				
			else if(errorAmount >= 2) {
				errorDialog.errorDialogCorrect("Error #4, more than one Exit!");
				return false;
			}
				
		}
				
		//check whether there is a pass
		
		

		for(int i = 0; i < MainPage.mapData.length; i++)
			for(int j = 0; j < MainPage.mapData[i].length; j++) {
				
				errorAmount = 0;
		
				if(!MainPage.mapData[i][j].kindOfBlock.equals("grass")) {
							
					if((i + 1) < MainPage.mapData.length) {
						if(!MainPage.mapData[i + 1][j].kindOfBlock.equals("grass")) {
							
							errorAmount++;
						}
					}
					
					
					if((i - 1) >= 0) {
						if(!MainPage.mapData[i - 1][j].kindOfBlock.equals("grass")) {
							
							errorAmount++;
						}
					}
					
					
					if((j + 1) < MainPage.mapData[0].length) {
						if(!MainPage.mapData[i][j + 1].kindOfBlock.equals("grass")) {
			
							errorAmount++;
						}
					}	
					
						
					if((j - 1) >= 0) {
						if(!MainPage.mapData[i][j - 1].kindOfBlock.equals("grass")) {

							errorAmount++;
						}
					}
					
					if(MainPage.mapData[i][j].kindOfBlock.equals("entry"))
						errorAmount++;
					else if (MainPage.mapData[i][j].kindOfBlock.equals("exit"))
						errorAmount++;
					
					if(errorAmount != 2) {
						
						if((errorAmount == 1) || (errorAmount == 0)) {
							errorDialog.errorDialogCorrect("Error #5, there is somewhere broken!");
							return false;
						}
							
						else {
							errorDialog.errorDialogCorrect("Error #6, there is somewhere byroad!");
							return false;
						}
							
					}	
				}
		
			}

		return true;
	} 
	
	static void mapSaving() throws FileNotFoundException {
		
		if(mapCanUse() == true) {
			
			JFileChooser savingFile = new JFileChooser();

			if(savingFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = savingFile.getSelectedFile();
			
				PrintWriter saving = new PrintWriter(file);
			
				for(int i = 0; i < MainPage.mapData.length; i++) {
					for(int j = 0; j < MainPage.mapData[i].length; j++) {
						saving.printf("%8s", MainPage.mapData[i][j].kindOfBlock);
					}
				
					saving.println();
				}
				saving.close();
				errorDialog.errorDialogCorrect("the map has saved");
			}
		}
			
		else
			errorDialog.errorDialogCorrect("the map have sth. wrong, not saved");
		
	}
	
	static void mapLoading(Block[][] data) throws FileNotFoundException {
		
		JFileChooser loadingFile = new JFileChooser();
		
		if(loadingFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = loadingFile.getSelectedFile();
		
			Scanner loading = new Scanner(file);
		
			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < data[i].length; j++) {
					data[i][j].kindOfBlock = loading.next();
				}
			}
			
			loading.close();
			
			
			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < data[i].length; j++) {
					if(data[i][j].kindOfBlock.equals("entry"))
						data[i][j].basePane.setStyle("-fx-background-color: red");
					else if(data[i][j].kindOfBlock.equals("exit"))
						data[i][j].basePane.setStyle("-fx-background-color: pink");
					else if(data[i][j].kindOfBlock.equals("road"))
						data[i][j].basePane.setStyle("-fx-background-color: grey");
					else
						data[i][j].basePane.setStyle("-fx-background-color: green");
				}
			}
			
			errorDialog.errorDialogCorrect("the map has loaded");
		}
		
		
	}
		
	
}



