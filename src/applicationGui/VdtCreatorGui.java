package applicationGui;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import java.util.*;

import application.vdt_Creator;


public class VdtCreatorGui extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LinkedList<String> filePathList = new LinkedList<String>();
		
		primaryStage.setTitle("Vdt Creator");
		
		String css = this.getClass().getResource("app.css").toExternalForm();
		
		GridPane gridPane = createPane();
		gridPane.setStyle("-fx-background-color:#FF7043;");
		Scene scene = new Scene(gridPane,600,500);
		scene.getStylesheets().add(css);
		
		Label dropboxLabel = new Label("Drop Here!");
		dropboxLabel.setFont(Font.font("Arial",FontWeight.BOLD,23));
		gridPane.add(dropboxLabel,0,0,2,1);
		GridPane.setHalignment(dropboxLabel, HPos.CENTER);
		GridPane.setMargin(dropboxLabel,new Insets(0,90,260,90));
		
		ListView<String> listview = new ListView();
		gridPane.add(listview,0,0,2,2);
		GridPane.setHalignment(listview, HPos.CENTER);
		GridPane.setMargin(listview,new Insets(40,100,0,100));
		
		Button executeButton  = new Button("Get VDTs");
		executeButton.setFont(Font.font("Arial",23));
		gridPane.add(executeButton,0,2,2,1);
		GridPane.setHalignment(executeButton, HPos.CENTER);
		GridPane.setMargin(executeButton,new Insets(0,100,90,100));
		
		executeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				if(listview.getItems().isEmpty()) {
					showAlert(Alert.AlertType.ERROR,gridPane.getScene().getWindow(),
							"Form Error!","Please drop some files");
					return;
				}
				else {
					
					vdt_Creator creator = new vdt_Creator();
					
					int size1 = listview.getItems().size();
					int size2 = filePathList.size();
					
					String files[] = new String[size1];
					String filePaths[] = new String[size2];
					
					filePathList.toArray(filePaths);
					listview.getItems().toArray(files);
					
					try {
						creator.createVdt(size1, files, filePaths);
					}
					catch(Exception e) { System.out.println(e); }
					
					primaryStage.close();
					
				}
			}
		});
		
		
        gridPane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        
        // Dropping over surface
        gridPane.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        listview.getItems().add(file.getName());
                        filePathList.add(file.getAbsolutePath());
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
		
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	    Alert alert = new Alert(alertType);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.initOwner(owner);
	    alert.show();
	}

	
	private GridPane createPane() {
		GridPane gridPane = new GridPane();
		
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.setPadding(new Insets(40,40,40,40));
		
		gridPane.setHgap(10);
		
		gridPane.setVgap(10);
		
	    ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
	    columnOneConstraints.setHalignment(HPos.RIGHT);

	    // columnTwoConstraints will be applied to all the nodes placed in column two.
	    ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
	    columnTwoConstrains.setHgrow(Priority.ALWAYS);

	    gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
	    
	    return gridPane;
		
	}
	
	public static void main(String[]args) {
		launch(args);
	}
}
