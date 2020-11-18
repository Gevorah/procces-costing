package ui;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


public class Main extends Application {
	private GUI gui;
	private Costing costing;

	public Main() {
		costing = new Costing();
		gui = new GUI(costing);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		loader.setController(gui);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Procces Costing");
		gui.loadMainWindow(null);
		stage.show();
	}
}
