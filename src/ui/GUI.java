package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
    private TextField processlbl;

    @FXML
    private TextField costIItxt;

    @FXML
    private TextField unitsIFtxt;

    @FXML
    private TextField costIFtxt;

    @FXML
    private TextField acpMDtxt;

    @FXML
    private TextField acpCIFtxt;

    @FXML
    private TextField acpMODtxt;

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
	DialogPane dp;
	@FXML
	public void loadPopUp() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pp-pop-up.fxml"));
		loader.setController(this);
		DialogPane root = loader.load();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setDialogPane(root);
		dialog.setTitle("PP extra info");
		dialog.show();
	}
	
	@FXML
    void methodPEPS(ActionEvent event) {

    }
	
	@FXML
    void methodPP(ActionEvent event) {
		
    }
	
	
	
    @FXML
    void loadConcepts(ActionEvent event) {

    }

    @FXML
    void loadInstructions(ActionEvent event) {

    }

}
