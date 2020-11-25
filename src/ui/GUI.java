package ui;

import java.util.*;

import javax.swing.JFileChooser;

import java.io.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
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
    private Label contentlbl;
    
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
		extra = new HashMap<String, Double>();
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
    	if(!businesstxt.getText().equals("") && !periodotxt.getText().equals("")) {
    		costing.entry[0] = businesstxt.getText();
    		costing.entry[1] = periodotxt.getText();
    		loadProcessWindow(null);
    		businesslbl.setText(costing.entry[0]);
    	} else {
    		alert(AlertType.ERROR,"Error","Por favor llene los espacios.");
    	}
    	
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
		ppBool = commonData();
		if(ppBool) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("pp-pop-up.fxml"));
			loader.setController(this);
			window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("PP extra info");
			Parent root = loader.load();
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.show();
			window.setResizable(false);
		}
		
	} 
	public static HashMap<String, Double> extracted;
	boolean commonData() {
		try {
			if(!processtxt.getText().equals(""))costing.entry[2] = processtxt.getText();
			else throw new NumberFormatException();
			extracted.clear();
			extracted.put("Unidades II", Double.parseDouble(unitsIItxt.getText()));
			extracted.put("Costo II", Double.parseDouble(costIItxt.getText()));
			extracted.put("%MD II", Double.parseDouble(pMDIItxt.getText())/100);
			extracted.put("%MOD II", Double.parseDouble(pMODIItxt.getText())/100);
			extracted.put("%CIF II", Double.parseDouble(pCIFIItxt.getText())/100);
			extracted.put("%MD IF", Double.parseDouble(pMDIFtxt.getText())/100);
			extracted.put("%MOD IF", Double.parseDouble(pMODIFtxt.getText())/100);
			extracted.put("%CIF IF", Double.parseDouble(pCIFIFtxt.getText())/100);
			extracted.put("Unidades Comenzadas", Double.parseDouble(unitsStartedtxt.getText()));
			extracted.put("Costo Comenzadas", Double.parseDouble(costStartedtxt.getText()));
			extracted.put("Costo Agregado MD", Double.parseDouble(acMDtxt.getText()));
			extracted.put("Costo Agregado MOD", Double.parseDouble(acMODtxt.getText()));
			extracted.put("Costo Agregado CIF", Double.parseDouble(acCIFtxt.getText()));
			if(unitsIFtxt.getText().equals("") && unitsFinishedtxt.getText().equals("")) {
				alert(AlertType.WARNING,"Advertencia","Por favor llene uno: Unidades del Inventario Final o Unidades Terminadas");
			}else if(unitsIFtxt.getText().equals("")){
				extracted.put("Unidades Terminadas", Double.parseDouble(unitsFinishedtxt.getText()));
				undsIF = false;
			}else{
				extracted.put("Unidades IF", Double.parseDouble(unitsIFtxt.getText()));
				undsIF = true;
			}
			return true;
		}catch(NumberFormatException nfe) {
			extracted.clear();
			alert(AlertType.ERROR,"Error","Por favor llene todos los espacios y solo utilice números");
			return false;
		}
		
	}
	public static boolean undsIF;
	
	public void alert(AlertType a, String title, String content) {
		Alert alert = new Alert(a);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	@FXML
    void methodPEPS(ActionEvent event) {
		if(commonData()){
			try {
				costing.peps(save());
			} catch (Exception e) {
				e.printStackTrace();
			}
			alert(AlertType.INFORMATION,"Listo!","Ya puede revisar el archivo.");
		}
    }
	public static boolean ppBool;
	public static HashMap<String, Double> extra;
	@FXML
    void methodPP(ActionEvent event) throws IOException {
		if(ppBool){
			try {
				extra.clear();
				extra.put("Costo Transferido", Double.parseDouble(transferCosttxt.getText()));
				extra.put("Costo MD",Double.parseDouble(cMDtxt.getText()));
				extra.put("Costo MOD",Double.parseDouble(cMODtxt.getText()));
				extra.put("Costo CIF",Double.parseDouble(cCIFtxt.getText()));
				window.close();
				costing.pp(save());
			}catch(NumberFormatException nfe) {
				extra.clear();
				alert(AlertType.ERROR,"Error","Por favor llene todos los espacios y solo utilice números");
			}
			alert(AlertType.INFORMATION,"Listo!","Ya puede revisar el archivo.");
		}
		
    }
	
	public String save() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
			return chooser.getSelectedFile().getCanonicalPath();
		else return "data";
		
	}
	void dialog(String title, String content) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog-window.fxml"));
		loader.setController(this);
		DialogPane root = loader.load();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setDialogPane(root);
		dialog.setTitle(title);
    	contentlbl.setText(content);
    	dialog.show();
    	dialog.setResizable(false);
	}
	
    @FXML
    void loadConcepts(ActionEvent event) throws IOException {
    	String content = String.format("%s%n%s%n%s%n%s%n%s%n%s",
    			"PP: Producto en Proceso.",
    			"MD: Material Directo.",
    			"MOD: Mano de Obra Directa.",
    			"CIF: Costos Indirectos de Fabricación.",
    			"PEPS: Método Primeras en Entrar, Primeras en Salir.",
    			"PP: Método Promedio Ponderado.");
    	dialog("Conceptos", content);
    }

    @FXML
    void loadInstructions(ActionEvent event) throws IOException {
    	String content = String.format("%s%n%s%n%s%n%s%n%s%n%s",
    			"1.Ingrese el nombre de la empresa y el periodo",
    			"2.Ingrese los datos correspondientes en las casillas. ",
    			"*Tenga en cuenta que no necesariamente debe ingresar las unidades del "
    			+ "inventario final y unidades terminadas, puede ingresar cualquiera de las dos.",
    			"3.Haga clic en el boton del metodo que desea usar. [PEPS] o [PP].",
    			"*En caso de que sea [PP] ingrese los datos pedidos en la ventana emergente.",
    			"4.Seleccione el directorio en el que desea guardar el archivo.");
    	dialog("Instrucciones", content);
    }

}
