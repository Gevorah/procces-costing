package model;

import java.util.*;
import ui.GUI;
import java.io.*;

public class Costing {
	public String[] entry;
	private HashMap<String, Double> equiUnit;
	private HashMap<String, Double> costUnit;
	private HashMap<String, Double> results;

	public Costing() {
		entry = new String[3];
		equiUnit = new HashMap<String, Double>();
		costUnit = new HashMap<String, Double>();
		results = new HashMap<String, Double>();
	}
	public void unidadesIF() {
		GUI.extracted.put("Unidades IF", GUI.extracted.get("Unidades II") 
				+GUI.extracted.get("Unidades Comenzadas") - GUI.extracted.get("Unidades Terminadas"));
	}

	public void unidadesTerminadas() {
		GUI.extracted.put("Unidades Terminadas", GUI.extracted.get("Unidades II") 
				+GUI.extracted.get("Unidades Comenzadas") - GUI.extracted.get("Unidades IF"));
	}
	
	public String equivalentProductionPEPS(double unidIyT) {
		equiUnit.clear();
		equiUnit.put("MD II", GUI.extracted.get("Unidades II") * GUI.extracted.get("%MD II"));
		equiUnit.put("MD IF", GUI.extracted.get("Unidades IF") * GUI.extracted.get("%MD IF"));
		equiUnit.put("MD", equiUnit.get("MD II") + equiUnit.get("MD IF") + unidIyT);
		equiUnit.put("MOD II", GUI.extracted.get("Unidades II") * GUI.extracted.get("%MOD II"));
		equiUnit.put("MOD IF", GUI.extracted.get("Unidades IF") * GUI.extracted.get("%MOD IF"));
		equiUnit.put("MOD", equiUnit.get("MOD II") + equiUnit.get("MOD IF") + unidIyT);
		equiUnit.put("CIF II", GUI.extracted.get("Unidades II") * GUI.extracted.get("%CIF II"));
		equiUnit.put("CIF IF", GUI.extracted.get("Unidades IF") * GUI.extracted.get("%CIF IF"));
		equiUnit.put("CIF", equiUnit.get("CIF II") + equiUnit.get("CIF IF") + unidIyT);
		return String.format("%s%s%s%s%s%n"
				+"%s%.1f%s%.1f%s%.1f%s%.1f%n"
				+"%s%.1f%s%.1f%s%.1f%s%.1f%n"
				+"%s%.1f%s%.1f%s%.1f%s%.1f%n"
				+"%s%.1f%s%.1f%s%.1f%s%.1f%n%n%n",
				"Producción Equivalente",CSVCELLESEPARATOR+CSVSEPARATOR,
				"MD"+CSVSEPARATOR,"MOD"+CSVSEPARATOR,"CIF",
				"Inv. inicial PP"+CSVCELLESEPARATOR,GUI.extracted.get("Unidades II"),CSVSEPARATOR,equiUnit.get("MD II"),CSVSEPARATOR,equiUnit.get("MOD II"),CSVSEPARATOR,equiUnit.get("CIF II"),
				"Iniciadas y Terminadas"+CSVCELLESEPARATOR,unidIyT,CSVSEPARATOR,unidIyT,CSVSEPARATOR,unidIyT,CSVSEPARATOR,unidIyT,
				"Inv. final PP"+CSVCELLESEPARATOR,GUI.extracted.get("Unidades IF"),CSVSEPARATOR,equiUnit.get("MD IF"),CSVSEPARATOR,equiUnit.get("MOD IF"),CSVSEPARATOR,equiUnit.get("CIF IF"),
				"Total"+CSVCELLESEPARATOR,GUI.extracted.get("Unidades II") + GUI.extracted.get("Unidades IF") + unidIyT,CSVSEPARATOR,equiUnit.get("MD"),CSVSEPARATOR,equiUnit.get("MOD"),CSVSEPARATOR,equiUnit.get("CIF"));
	}
	public String unitCostPEPS(double unidIyT) {
		costUnit.clear();
		costUnit.put("MD", GUI.extracted.get("Costo Agregado MD") / equiUnit.get("MD"));
		costUnit.put("MOD", GUI.extracted.get("Costo Agregado MOD") / equiUnit.get("MOD"));
		costUnit.put("CIF", GUI.extracted.get("Costo Agregado CIF") / equiUnit.get("CIF"));
		costUnit.put("Costo Unitario", costUnit.get("MD") + costUnit.get("MOD") + costUnit.get("CIF"));
		costUnit.put("Cts Trasnferidos", GUI.extracted.get("Costo Comenzadas") / GUI.extracted.get("Unidades Comenzadas"));
		costUnit.put("Total", costUnit.get("Costo Unitario") + costUnit.get("Cts Trasnferidos"));
		return String.format("%s%n%s%.1f%n%s%.1f%n%s%.1f%n%s%.1f%n%s%.1f%n%s%.1f%n%n%n",
				"Costo por Unidad",
				"MD"+CSVCELLESEPARATOR,costUnit.get("MD"),
				"MOD"+CSVCELLESEPARATOR,costUnit.get("MOD"),
				"CIF"+CSVCELLESEPARATOR,costUnit.get("CIF"),
				"Costo Unitario"+CSVCELLESEPARATOR,costUnit.get("Costo Unitario"),
				"Costo Transferido"+CSVCELLESEPARATOR,costUnit.get("Cts Trasnferidos"),
				"Costo Total Unitario"+CSVCELLESEPARATOR,costUnit.get("Total"));
	}
	public void costAssignPEPS(double unidIyT) {
		results.clear();
		results.put("Agregado II MD", equiUnit.get("MD II") * costUnit.get("MD"));
		results.put("Agregado II MOD", equiUnit.get("MOD II") * costUnit.get("MOD"));
		results.put("Agregado II CIF", equiUnit.get("CIF II") * costUnit.get("CIF"));
		results.put("Costo Total II",
				results.get("Agregado II MD") + results.get("Agregado II MOD")
				+results.get("Agregado II CIF") + GUI.extracted.get("Costo II"));
		results.put("Costo Total Prod Ter",
				unidIyT * costUnit.get("Total") + results.get("Costo Total II"));
		results.put("Costos MD IF", equiUnit.get("MD IF") * costUnit.get("MD"));
		results.put("Costos MOD IF", equiUnit.get("MOD IF") * costUnit.get("MOD"));
		results.put("Costos CIF IF", equiUnit.get("CIF IF") * costUnit.get("CIF"));
		results.put("Costos Transferidos IF", 
				GUI.extracted.get("Unidades IF") * costUnit.get("Cts Trasnferidos"));
		results.put("Costo Total IF", 
				results.get("Costos MD IF") + results.get("Costos MOD IF")
				+results.get("Costos CIF IF") + results.get("Costos Transferidos IF"));
		results.put("Total Costos Asignados", 
				results.get("Costo Total Prod Ter") + results.get("Costo Total IF"));
	}
	public void peps(String path) {
		if(GUI.undsIF==false) unidadesIF();
		else unidadesTerminadas();
		double unidIyT = GUI.extracted.get("Unidades Terminadas") - GUI.extracted.get("Unidades II");
		String equi = equivalentProductionPEPS(unidIyT);
		String unit = unitCostPEPS(unidIyT);
		costAssignPEPS(unidIyT);
		toCSV("PEPS\n",equi,unit,costs(),path);
	}

	public String equivalentProductionPP() {
		equiUnit.clear();
		equiUnit.put("MD IF", GUI.extracted.get("Unidades IF") * GUI.extracted.get("%MD IF"));
		equiUnit.put("MD", GUI.extracted.get("Unidades Terminadas") + equiUnit.get("MD IF"));
		equiUnit.put("MOD IF", GUI.extracted.get("Unidades IF") * GUI.extracted.get("%MOD IF"));
		equiUnit.put("MOD", GUI.extracted.get("Unidades Terminadas") + equiUnit.get("MOD IF"));
		equiUnit.put("CIF IF", GUI.extracted.get("Unidades IF") * GUI.extracted.get("%CIF IF"));
		equiUnit.put("CIF", GUI.extracted.get("Unidades Terminadas") + equiUnit.get("CIF IF"));
		return String.format("%s%s%s%s%s%n"
				+"%s%.1f%s%.1f%s%.1f%s%.1f%n"
				+"%s%.1f%s%.1f%s%.1f%s%.1f%n"
				+"%s%.1f%s%.1f%s%.1f%s%.1f%n%n%n",
				"Producción Equivalente",CSVCELLESEPARATOR+CSVSEPARATOR,
				"MD"+CSVSEPARATOR,"MOD"+CSVSEPARATOR,"CIF",
				"Unidades Terminadas"+CSVCELLESEPARATOR,GUI.extracted.get("Unidades Terminadas"),CSVSEPARATOR,GUI.extracted.get("Unidades Terminadas"),CSVSEPARATOR,GUI.extracted.get("Unidades Terminadas"),CSVSEPARATOR,GUI.extracted.get("Unidades Terminadas"),
				"Inv. final PP"+CSVCELLESEPARATOR,GUI.extracted.get("Unidades IF"),CSVSEPARATOR,equiUnit.get("MD IF"),CSVSEPARATOR,equiUnit.get("MOD IF"),CSVSEPARATOR,equiUnit.get("CIF IF"),
				"Total"+CSVCELLESEPARATOR,GUI.extracted.get("Unidades Terminadas") + GUI.extracted.get("Unidades IF"),CSVSEPARATOR,equiUnit.get("MD"),CSVSEPARATOR,equiUnit.get("MOD"),CSVSEPARATOR,equiUnit.get("CIF"));
	}

	public String unitCostPP() {
		costUnit.clear();
		costUnit.put("MD", (GUI.extra.get("Costo MD") + GUI.extracted.get("Costo Agregado MD")) / equiUnit.get("MD"));
		costUnit.put("MOD", (GUI.extra.get("Costo MOD") + GUI.extracted.get("Costo Agregado MOD")) / equiUnit.get("MOD"));
		costUnit.put("CIF", (GUI.extra.get("Costo CIF") + GUI.extracted.get("Costo Agregado CIF")) / equiUnit.get("CIF"));
		costUnit.put("Costo Unitario", costUnit.get("MD") + costUnit.get("MOD") + costUnit.get("CIF"));
		costUnit.put("Cts Transferidos", (GUI.extra.get("Costo Transferido") + GUI.extracted.get("Costo Comenzadas")) 
				/ (GUI.extracted.get("Unidades Terminadas") + GUI.extracted.get("Unidades IF")));
		costUnit.put("Total", costUnit.get("Costo Unitario") + costUnit.get("Cts Trasnferidos"));
		return String.format("%s%n%s%.1f%n%s%.1f%n%s%.1f%n%s%.1f%n%s%.1f%n%s%.1f%n%n%n",
				"Costo por Unidad",
				"MD"+CSVCELLESEPARATOR,costUnit.get("MD"),
				"MOD"+CSVCELLESEPARATOR,costUnit.get("MOD"),
				"CIF"+CSVCELLESEPARATOR,costUnit.get("CIF"),
				"Costo Unitario"+CSVCELLESEPARATOR,costUnit.get("Costo Unitario"),
				"Costo Transferido"+CSVCELLESEPARATOR,costUnit.get("Cts Trasnferidos"),
				"Costo Total Unitario"+CSVCELLESEPARATOR,costUnit.get("Total"));
	}
	public void costAssignPP() {
		results.clear();
		results.put("Costo Total Prod Ter", GUI.extracted.get("Unidades Terminadas") * costUnit.get("Total"));
		results.put("MD", equiUnit.get("MD IF") * costUnit.get("MD"));
		results.put("MOD", equiUnit.get("MOD IF") * costUnit.get("MOD"));
		results.put("CIF", equiUnit.get("CIF IF") * costUnit.get("CIF"));
		results.put("Costos Transferidos IF", GUI.extracted.get("Unidades IF") * costUnit.get("Cts Transferidos"));
		results.put("Costo Total IF", results.get("MD") + results.get("MOD") + results.get("CIF") + results.get("Costo Transferido"));
		results.put("Total Costos Asignados", results.get("Costo Total Prod Ter") + results.get("Costo Total IF"));
	}
	public void pp(String path) {
		if(GUI.undsIF==false) unidadesIF();
		else unidadesTerminadas();
		String equi = equivalentProductionPP();
		String unit = unitCostPP();
		costAssignPP();
		toCSV("PP\n",equi,unit,costs(),path);
	}
	
	public String costs() {
		results.put("Total Costos Agregados",GUI.extracted.get("Costo Agregado MD")
				+GUI.extracted.get("Costo Agregado MOD") + GUI.extracted.get("Costo Agregado CIF"));
		results.put("Costo Prod Ter", results.get("Total Costos Agregados") 
				+GUI.extracted.get("Costo II") - results.get("Costo Total IF"));
		return String.format("%s%n%s%s%n%s%s%n%s%s%n"
				+"%s%.2f%n%s%.2f%n%s%.2f%n%s%.2f%n%s%.2f%n%s%.2f%n%s%.2f",
				"Estado de costos",
				"Empresa:"+CSVSEPARATOR,entry[0],
				"Periodo:"+CSVSEPARATOR,entry[1],
				"Proceso:"+CSVSEPARATOR,entry[2],
				"MD"+CSVCELLESEPARATOR,GUI.extracted.get("Costo Agregado MD"),
				"MOD"+CSVCELLESEPARATOR,GUI.extracted.get("Costo Agregado MOD"),
				"CIF"+CSVCELLESEPARATOR,GUI.extracted.get("Costo Agregado CIF"),
				"Costos agregados a producción"+CSVCELLESEPARATOR,results.get("Total Costos Agregados"),
				"Inv. inicial PP"+CSVCELLESEPARATOR,GUI.extracted.get("Costo II"),
				"Inv. final PP"+CSVCELLESEPARATOR,results.get("Costo Total IF"),
				"Costos producto terminado:"+CSVCELLESEPARATOR,results.get("Costo Prod Ter"));

	}
	public final static String CSVSEPARATOR = ",";
	public final static String CSVCELLESEPARATOR = ",,";
	public void toCSV(String title,String equi, String unit, String costs, String path)  {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path+File.separator+entry[0]+"-"+entry[1]+".csv"));
			bw.write(title+equi+unit+costs);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
