package ui;
import java.io.*;
public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;

	public static void main(String args[]) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		solve();

		br.close();
		bw.close();
	}
    
	public static void solve()throws IOException{
		bw.write("\n¿Cuál es el nombre de la empresa?");
		String nombreEmpresa=br.readLine();
		bw.write("\n¿Qué periodo se va a costear?");
		String periodoContable=br.readLine();
		bw.write("\nQué método desea usar para hacer el costeo: (1)PEPS, (2)PP");
		int choice = Integer.parseInt(br.readLine());
		bw.write("\nIngrese las unidades del inventario inicial:");
		double unidInvInic= Double.parseDouble(br.readLine());
		bw.write("\nIngrese el portentaje de materiales directos en el inventario inicial:");
		double porcentajeMDInvInic = Double.parseDouble(br.readLine());
		bw.write("\nIngrese el portentaje de mano de obra directa en el inventario inicial:");
		double porcentajeMODInvInic = Double.parseDouble(br.readLine());
		bw.write("\nIngrese el portentaje de costos indirectos de fabricación en el inventario inicial:");
		double porcentajeCIFInvInic = Double.parseDouble(br.readLine());
		
		switch(choice){
		    case 1:
		        bw.write("\nIngrese el costo del inventario inicial");
		        double costoInvInic = Double.parseDouble(br.readLine());
		        bw.write("\nIngrese los costos agregados:");
		        bw.write("\nMaterial directo:");
		        double costoMDPn = Double.parseDouble(br.readLine());
		        bw.write("\nMano de obra directa:");
		        double costoMODPn = Double.parseDouble(br.readLine());
		        bw.write("\nCostos indirectos de fabricación:");
		        double costoCIFPn = Double.parseDouble(br.readLine());
		        
		        break;
		    case 2:
		       
		        break;
		}
		bw.write("\nIngrese las unidades del inventario final:");
		double unidInvFin= Double.parseDouble(br.readLine());
		bw.write("\nIngrese el portentaje de materiales directos en el inventario final:");
		double porcentajeMDInvFin = Double.parseDouble(br.readLine());
		bw.write("\nIngrese el portentaje de mano de obra directa en el inventario final:");
		double porcentajeMODInvFin = Double.parseDouble(br.readLine());
		bw.write("\nIngrese el portentaje de costos indirectos de fabricación en el inventario final:");
		double porcentajeCIFInvFin = Double.parseDouble(br.readLine());
	}
}
