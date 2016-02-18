package tower;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;

public class MainPage extends Application {

	static Block[][] mapData = new Block[12][12];
	static Block[][] gameData = new Block[12][12];
	static GridPane ScreenForEdit = new GridPane();
	static GridPane ScreenForGame = new GridPane();
	static Tower[][] tower = new Tower[12][12];
	
	
	public void start(Stage mainStage) {
		
		Money.money.setValue(1000);
		
		Button mapEditor = new Button("Map Editor");
		Button playingGame = new Button("Playing!");
		mapEditor.setMinSize(175, 60);
		playingGame.setMinSize(175, 60);
		
		VBox mainMenuButtons = new VBox(40);
		mainMenuButtons.setPadding(new Insets(100, 15, 15, 40));
		mainMenuButtons.getChildren().addAll(mapEditor, playingGame);
		
		BorderPane mainMenu = new BorderPane();
		mainMenu.setCenter(mainMenuButtons);
		
		
		
		//MapEditor
		
		
		
		Label mapEditorScene = new Label("Map Editor");
		Button newMap = new Button("Create");
		Button saveMap = new Button("Save");
		Button laodMap = new Button("Load");
		Button returnToMain1 = new Button("Return");
		newMap.setMinSize(100, 45);
		saveMap.setMinSize(100, 45);
		laodMap.setMinSize(100, 45);
		returnToMain1.setMinSize(100, 45);
		
		newMap.setOnAction(e -> {
			int[] sizeOfMap = chooseSize();
			//Block.createMap(sizeOfMap);
		});
		
		saveMap.setOnAction(e -> {
			try {
				Block.mapSaving();
			} catch (Exception er) {}
		});
		
		laodMap.setOnAction(e -> {
			try {
				Block.mapLoading(mapData);
			} catch (Exception er) {}
		});
		
		
		VBox mapEditorButtons = new VBox(15);
		mapEditorButtons.getChildren().addAll(newMap, saveMap, laodMap, returnToMain1);
		

		
		ToggleButton setEntry = new ToggleButton("ENTRY");
		ToggleButton setExit = new ToggleButton("EXIT");
		ToggleButton setRoad = new ToggleButton("ROAD");
		ToggleButton unSet = new ToggleButton("eraser");
		
		//setEntry.setStyle("-fx-background-color: red");
		//setExit.setStyle("-fx-background-color: red");
		//setRoad.setStyle("-fx-background-color: gray");
		//unSet.setStyle("-fx-background-color: white");
		
		setEntry.setMinSize(60, 60);
		setExit.setMinSize(60, 60);
		setRoad.setMinSize(60, 60);
		unSet.setMinSize(60, 60);
		
		ToggleGroup group = new ToggleGroup();
		setEntry.setToggleGroup(group);
		setExit.setToggleGroup(group);
		setRoad.setToggleGroup(group);
		unSet.setToggleGroup(group);
		
		HBox mapEditorTool = new HBox(5);
		mapEditorTool.getChildren().addAll(setEntry, setExit, setRoad, unSet);
		
		BorderPane rightPaneForEditor = new BorderPane();
		rightPaneForEditor.setTop(mapEditorTool);
		rightPaneForEditor.setBottom(mapEditorButtons);
		
		
		ScreenForEdit.setHgap(1);
		ScreenForEdit.setVgap(1);
		ScreenForEdit.setGridLinesVisible(false);
		
		
		
	
	
		for(int i = 0; i < MainPage.mapData.length; i++)
			for(int j = 0; j < MainPage.mapData[i].length; j++) {
				
				MainPage.mapData[i][j] = new Block();
				MainPage.mapData[i][j].kindOfBlock = "grass";
				MainPage.mapData[i][j].basePane.setStyle("-fx-background-color: green");
				MainPage.mapData[i][j].basePane.setMinSize(50, 50);
				MainPage.mapData[i][j].basePane.getChildren().add(new Text(""));
				
				MainPage.ScreenForEdit.add(MainPage.mapData[i][j].basePane, j, i);
				
				final int temp1 = i;
				final int temp2 = j;
				
				 mapData[i][j].basePane.setOnMouseClicked(e -> {
					
					if(setEntry.isSelected() == true) {
						mapData[temp1][temp2].basePane.setStyle("-fx-background-color: red");
						mapData[temp1][temp2].kindOfBlock = "entry";
					}
						
					else if(setExit.isSelected() == true) {
						mapData[temp1][temp2].basePane.setStyle("-fx-background-color: pink");
						mapData[temp1][temp2].kindOfBlock = "exit";
					}
						
					else if(setRoad.isSelected() == true) {
						mapData[temp1][temp2].basePane.setStyle("-fx-background-color: grey");
						mapData[temp1][temp2].kindOfBlock = "road";
					}
					
					else if(unSet.isSelected() == true) {
						mapData[temp1][temp2].basePane.setStyle("-fx-background-color: green");
						mapData[temp1][temp2].kindOfBlock = "grass";
					}
					
				});	
				
			}
		
		
		
		BorderPane editorPane = new BorderPane();
		
		editorPane.setTop(mapEditorScene);
		editorPane.setLeft(ScreenForEdit);
		editorPane.setRight(rightPaneForEditor);
		
		BorderPane.setAlignment(mapEditorScene, Pos.TOP_CENTER);
		
		
		
		//Playing Games
		
		
		
		Label gamingScene = new Label("Tower Defense");
		Button loadGame = new Button("Load");
		Button startGame = new Button("Start");
		Button pauseGame = new Button("Pause");
		Button returnToMain2 = new Button("Return");
		loadGame.setMinSize(100, 45);
		startGame.setMinSize(100, 45);
		pauseGame.setMinSize(100, 45);
		returnToMain2.setMinSize(100, 45);
		
		loadGame.setOnAction(e -> {
			try {
				Block.mapLoading(gameData);
			} catch (Exception er) {}
		});
		
		startGame.setOnAction(e -> {
			//nothing for this build
		});
		
		pauseGame.setOnAction(e -> {
			//nothing for this build
		});
		
		VBox gamingButtons = new VBox(15);
		gamingButtons.getChildren().addAll(loadGame, startGame, pauseGame, returnToMain2);
		
		
		Button sellTower = new Button("Sell");
		Button updateTower = new Button("Update");
		
		
		Text showMoney = new Text("1000");
		
		Money.money.addListener(ov -> {
			showMoney.setText("" + Money.money.getValue());
		});
		
		
		
		
		//sellTower.setStyle("-fx-background-color: red");
		//updateTower.setStyle("-fx-background-color: red");
		
		sellTower.setMinSize(100, 40);
		updateTower.setMinSize(100, 40);
		
		
		VBox towerButtons = new VBox(10);
		towerButtons.setPadding(new Insets(40, 15, 15, 15));
		towerButtons.getChildren().addAll(sellTower, updateTower, new Label("Your Money: "), showMoney);
		
		sellTower.setOnAction(e -> {
			
			
			
			
			
			//Money.money.incMoney(tower[temp1][temp2].valueBuild);
		});
		
		updateTower.setOnAction(e -> {
			//wait
		});
		
		Font font = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20);
		
		Text towerA = new Text("A");
		Text towerB = new Text("B");
		towerA.setFont(font);
		towerB.setFont(font);
		
		Circle circleA = new Circle(25);
		Circle circleB = new Circle(25);
		circleA.setStroke(Color.BLACK);
		circleB.setStroke(Color.BLACK);
		circleA.setFill(null);
		circleB.setFill(null);
		
		StackPane paneA = new StackPane();
		StackPane paneB = new StackPane();
		paneA.getChildren().addAll(towerA, circleA);
		paneB.getChildren().addAll(towerB, circleB);
		
		ToggleButton buildTowerA = new ToggleButton("", paneA);
		ToggleButton buildTowerB = new ToggleButton("", paneB);
		
		ToggleGroup towerGroup = new ToggleGroup();
		buildTowerA.setToggleGroup(towerGroup);
		buildTowerB.setToggleGroup(towerGroup);
		

		//towerA.setStyle("-fx-background-color: green");
		//towerB.setStyle("-fx-background-color: green");
		buildTowerA.setMinSize(50, 50);
		buildTowerB.setMinSize(50, 50);
		
		HBox towerBuilding = new HBox(10);
		
		towerBuilding.getChildren().addAll(buildTowerA, buildTowerB);
			
		BorderPane rightPaneForGame = new BorderPane();
		rightPaneForGame.setTop(towerBuilding);
		rightPaneForGame.setBottom(gamingButtons);
		rightPaneForGame.setRight(towerButtons);
		
		ScreenForGame.setHgap(1);
		ScreenForGame.setVgap(1);
		ScreenForGame.setGridLinesVisible(false);
		
		
		for(int i = 0; i < MainPage.gameData.length; i++)
			for(int j = 0; j < MainPage.gameData[i].length; j++) {
				
				MainPage.gameData[i][j] = new Block();
				MainPage.gameData[i][j].kindOfBlock = "grass";
				MainPage.gameData[i][j].basePane.setStyle("-fx-background-color: green");
				MainPage.gameData[i][j].basePane.setMinSize(50, 50);
				MainPage.gameData[i][j].basePane.getChildren().add(new Text(""));
				
				MainPage.ScreenForGame.add(MainPage.gameData[i][j].basePane, j, i);
				
				
				final int temp1 = i;
				final int temp2 = j;
				
				gameData[i][j].basePane.setOnMousePressed(e -> {
					if((e.getClickCount() == 2) && (gameData[temp1][temp2].kindOfBlock.equals("grass"))){
						MainPage.gameData[temp1][temp2].basePane.setStyle("-fx-background-color: yellow");
					}
				});
				gameData[i][j].basePane.setOnMouseClicked(e -> {
					
					if(gameData[temp1][temp2].kindOfBlock.equals("grass")){
								
						if(buildTowerA.isSelected() == true) {
							
							tower[temp1][temp2] = new TowerA();
							tower[temp1][temp2].kindOfTower = "tower_A";
							
							System.out.println(Money.money.getValue());
							
							if(Money.decMoney(tower[temp1][temp2].valueBuild) == true) {
								gameData[temp1][temp2].basePane.getChildren().add(new Text(25, 25, "A"));
								gameData[temp1][temp2].kindOfBlock = "tower";
							}
							
							
							
							
								
							gameData[temp1][temp2].basePane.setOnMouseClicked(f -> {
								
											
								if(gameData[temp1][temp2].kindOfBlock.equals("tower")) {
									
									
									errorDialog.errorDialogCorrect(tower[temp1][temp2].describe);
									
									/*
									Text t = new Text(tower[temp1][temp2].describe);
									Pane pane = new Pane();
									pane.getChildren().add(t);
									t.setOnMouseMoved(g -> {
										t.setX(g.getX());
										t.setY(g.getY());
									});
									
									gameData[temp1][temp2].basePane.getChildren().add(t);
							*/	
								}
									
							});
							
						}
					
						else if(buildTowerB.isSelected() == true) {
							
							tower[temp1][temp2] = new TowerB();
							tower[temp1][temp2].kindOfTower = "tower_B";
							
							if(Money.decMoney(tower[temp1][temp2].valueBuild) == true) {
								gameData[temp1][temp2].basePane.getChildren().add(new Text(25, 25, "B"));
								gameData[temp1][temp2].kindOfBlock = "tower";
							}
							
						
							tower[temp1][temp2] = new TowerB();
							tower[temp1][temp2].kindOfTower = "tower_B";
							
							gameData[temp1][temp2].basePane.setOnMouseEntered(f -> {
								
							/*				
								if(gameData[temp1][temp2].kindOfBlock.equals("tower")) {
	
									Text t = new Text(tower[temp1][temp2].describe);
									Pane pane = new Pane();
									pane.getChildren().add(t);
									t.setOnMouseMoved(g -> {
										t.setX(g.getX());
										t.setY(g.getY());
									});
									
									gameData[temp1][temp2].basePane.getChildren().add(t);
								}*/
									
							});
							
						}	
						
					
					}	
			
				});
				
				

			}
						
						
			
		
		BorderPane playingPane = new BorderPane();
		playingPane.setTop(gamingScene);
		playingPane.setLeft(ScreenForGame);
		playingPane.setRight(rightPaneForGame);
		BorderPane.setAlignment(gamingScene, Pos.TOP_CENTER);
		
		
		Scene sceneMain = new Scene(mainMenu, 250, 400);
		Scene sceneMapEditor = new Scene(editorPane, 1280, 720);
		Scene sceneGaming = new Scene(playingPane, 1280, 720);
		
		returnToMain1.setOnAction(e -> {
			mainStage.setTitle("Tower Defense");
			mainStage.setResizable(false);
			mainStage.setScene(sceneMain);	
		});
		
		returnToMain2.setOnAction(e -> {
			mainStage.setTitle("Tower Defense");
			mainStage.setResizable(false);
			mainStage.setScene(sceneMain);	
		});
		
		mapEditor.setOnAction(e -> {
			mainStage.setTitle("Map Editor");
			mainStage.setResizable(true);
			mainStage.setScene(sceneMapEditor);
		});
		
		playingGame.setOnAction(e -> {
			mainStage.setTitle("Gaming");
			mainStage.setResizable(true);
			mainStage.setScene(sceneGaming);
		});
		
		mainStage.setTitle("Tower Defense");
		mainStage.setResizable(false);
		//mainStage.setScene(sceneMapEditor);
		mainStage.setScene(sceneMain);
		mainStage.show();
		
	}

	
	public static int[] chooseSize() {
		
		Stage newMap = new Stage();
		newMap.initModality(Modality.APPLICATION_MODAL);
		
		int[] returnSize = {50, 50};
		
		ScrollBar scrollBarHorizontal = new ScrollBar();
		scrollBarHorizontal.setMax(12);
		scrollBarHorizontal.setMin(5);
		scrollBarHorizontal.setValue(8);
		
		Text stringOfScrollBarHorizontal = new Text("8");
		scrollBarHorizontal.valueProperty().addListener(ov -> {
			int valueOfScrollBarHorizontal = (int)Math.rint(scrollBarHorizontal.getValue());
			stringOfScrollBarHorizontal.setText(valueOfScrollBarHorizontal + "");
			
			returnSize[0] = valueOfScrollBarHorizontal;
		});
		
		
		
		ScrollBar scrollBarVertical = new ScrollBar();
		scrollBarVertical.setMax(100);
		scrollBarVertical.setMin(20);
		scrollBarVertical.setValue(50);
		
		Text stringOfScrollBarVertical = new Text("8");
		scrollBarVertical.valueProperty().addListener(ov -> {
			int valueOfScrollBarVertical = (int)Math.rint(scrollBarVertical.getValue());
			stringOfScrollBarVertical.setText(valueOfScrollBarVertical + "");
			
			returnSize[1] = valueOfScrollBarVertical;
		});
		
		Button buttonOK = new Button("OK");
		buttonOK.setOnAction(e -> {
			newMap.close();
		});
		
		
		FlowPane paneSetSize = new FlowPane(5, 10);
		paneSetSize.getChildren().addAll(new Label("Horizontal:"), stringOfScrollBarHorizontal,
				new Label("Vertical:"), stringOfScrollBarVertical, scrollBarHorizontal, scrollBarVertical, buttonOK);
		
		Scene sceneSetSize = new Scene(paneSetSize, 200, 75);
		
		newMap.setTitle("Size");
		newMap.setResizable(false);
		newMap.setScene(sceneSetSize);
		newMap.showAndWait();
		
		return returnSize;
	}
	
	static void clickedEvent() {
		
		
		
	}
	
	public static void main(String[] args) {
		Application.launch();
	}

}

class errorDialog {
	
	public static void errorDialogCorrect(String string) {
		
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		
		Pane pane = new Pane(new Label(string));
		Button buttonOK = new Button("OK");
		buttonOK.setOnAction(e -> {
			dialog.close();
		});
		
		Scene sceneError = new Scene(pane, 225, 80);
		
		dialog.setTitle("Error");
		dialog.setResizable(false);
		dialog.setScene(sceneError);
		dialog.showAndWait();
	}
	
}


