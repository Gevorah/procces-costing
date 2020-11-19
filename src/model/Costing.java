package model;
import java.util.*;
import java.io.*;

public class Costing {
	private ArrayList<Double> tmp;
	HashMap<String, Double> results;



public void equivalentProduction(
			double unitsII, double percentMDII, double percentMODII, double percentCIFII, 
			double unidInvFin, double percentMDIF,	double percentMODIF, double percentCIFIF) {
		results.put("Unidad Equivalente MD II",unitsII*percentMDII);
		
		
		double unidEquivMDInvInic=unidInvInic*porcentajeMDInvInic;
		double unidEquivMDInvFin=unidInvFin*porcentajeMDInvFin;
		double unidEquivMD=unidEquivMDInvInic+unidEquivMDInvFin+unidInicYTer;
		double unidEquivMODInvInic=unidInvInic*porcentajeMODInvInic;
		double unidEquivMODInvFin=unidInvFin*porcentajeMODInvFin;
		double unidEquivMOD=unidEquivMODInvInic+unidEquivMODInvFin+unidInicYTer;
		double unidEquivCIFInvInic=unidInvInic*porcentajeCIFInvInic;
		double unidEquivCIFInvFin=unidInvFin*porcentajeCIFInvFin;
		double unidEquivCIF=unidEquivCIFInvInic+unidEquivCIFInvFin+unidInicYTer;

		//double calcularCostoUnitario();
	}








	public final static String CSVSEPARATOR = ",";
	public static void toCSV(String business, String process, String periodo,
			double MD, double MOD, double CIF, double addedCostP, double InvInic, 
			double InvFin, double costsProdTer)  {
		String result= String.format("%s%n%s%s%n%s%s%n%s%s%n%s%s%n%s%s%n%s%s%n%s%s%n%s%s%n%s%s%n",
				"Estado de costos",
				"Empresa:"+CSVSEPARATOR,business,
				"Periodo:"+CSVSEPARATOR,periodo,
				"MD"+CSVSEPARATOR,MD,
				"MOD"+CSVSEPARATOR,MOD,
				"CIF"+CSVSEPARATOR,CIF,
				"Costos agregados a producción"+CSVSEPARATOR,addedCostP,
				"Inv. inicial PP"+CSVSEPARATOR,InvInic,
				"Inv. final PP"+CSVSEPARATOR,InvFin,
				"Costos producto terminado:"+CSVSEPARATOR,costsProdTer);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("data"+File.separator+business+"-"+process+".csv"));
			bw.write(result);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
