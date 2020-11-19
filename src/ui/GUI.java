package ui;

import java.util.*;
import java.io.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Costing;

public class GUI {
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private TextField businesstxt;

    @FXML
    private TextField periodotxt;
    
    @FXML
    private TextField unitsIItxt;

    @FXML
    private TextField pMDIItxt;

    @FXML
    private TextField pMODIItxt;

    @FXML
    private TextField pCIFIItxt;

    @FXML
    private TextField pMDIFtxt;

    @FXML
    private TextField pMODIFtxt;

    @FXML
    private TextField pCIFIFtxt;

    @FXML
    private TextField costStartedtxt;

    @FXML
    private TextField unitsFinishedtxt;

    @FXML
    private TextField processtxt;

    @FXML
    private TextField costIItxt;

    @FXML
    private TextField unitsIFtxt;

    @FXML
    private TextField costIFtxt;

    @FXML
    private TextField acMDtxt;

    @FXML
    private TextField acCIFtxt;

    @FXML
    private TextField acMODtxt;

    @FXML
    private Label businesslbl;

    @FXML
    private TextField unitsStartedtxt;
    
    @FXML
    private TextField transferCosttxt;

    @FXML
    private TextField cMDtxt;

    @FXML
    private TextField cMODtxt;

    @FXML
    private TextField cCIFtxt;
    
	private Costing costing;
	public GUI(Costing costing) {
		this.costing=costing;
		extracted = new HashMap<String, Double>();
	}
	@FXML
	public void loadMainWindow(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(setting);
	}
	
    @FXML
    void signIn(ActionEvent event) throws IOException {
    	costing.entry[0] = businesstxt.getText();
    	costing.entry[1] = periodotxt.getText();
    	loadProcessWindow(null);
    	
    }
    
	@FXML
	public void loadProcessWindow(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("process-window.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(setting);
	}
	Stage window;
	@FXML
	public void loadPopUp() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pp-pop-up.fxml"));
		loader.setController(this);
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("PP extra info");
		Parent root = loader.load();
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.show();
	}
	public static HashMap<String, Double> extracted;
	void commonData() {
		costing.entry[2] = processtxt.getText();
		extracted.clear();
		extracted.put("Unidades II", Double.parseDouble(unitsIItxt.getText()));
		extracted.put("Costo II", Double.parseDouble(costIItxt.getText()));
		extracted.put("%MD II", Double.parseDouble(pMDIItxt.getText()));
		extracted.put("%MOD II", Double.parseDouble(pMODIItxt.getText()));
		extracted.put("%CIF II", Double.parseDouble(pCIFIItxt.getText()));
		extracted.put("Unidades IF", Double.parseDouble(unitsIFtxt.getText()));
		extracted.put("Costo IF", Double.parseDouble(costIFtxt.getText()));
		extracted.put("%MD IF", Double.parseDouble(pMDIFtxt.getText()));
		extracted.put("%MOD IF", Double.parseDouble(pMODIFtxt.getText()));
		extracted.put("%CIF IF", Double.parseDouble(pCIFIFtxt.getText()));
		extracted.put("Unidades Comenzadas", Double.parseDouble(unitsStartedtxt.getText()));
		extracted.put("Costo Comenzadas", Double.parseDouble(costStartedtxt.getText()));
		extracted.put("Unidades Terminadas", Double.parseDouble(unitsFinishedtxt.getText()));
		extracted.put("Costo Agregado MD", Double.parseDouble(acMDtxt.getText()));
		extracted.put("Costo Agregado MOD", Double.parseDouble(acMODtxt.getText()));
		extracted.put("Costo Agregado CIF", Double.parseDouble(acCIFtxt.getText()));
	}
	
	@FXML
    void methodPEPS(ActionEvent event) {
		commonData();
		costing.peps();
    }
	
	@FXML
    void methodPP(ActionEvent event) {
		commonData();
		extracted.put("Costo transferido", Double.parseDouble(transferCosttxt.getText()));
		extracted.put("Costo MD",Double.parseDouble(cMDtxt.getText()));
		extracted.put("Costo MOD",Double.parseDouble(cMODtxt.getText()));
		extracted.put("Costo CIF",Double.parseDouble(cCIFtxt.getText()));
		window.close();
		
    }
	
	
	
    @FXML
    void loadConcepts(ActionEvent event) {

    }

    @FXML
    void loadInstructions(ActionEvent event) {

    }

}
