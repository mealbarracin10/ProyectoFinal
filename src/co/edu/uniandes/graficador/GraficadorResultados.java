package co.edu.uniandes.graficador;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.manejadores.ManejadorModelos;

public class GraficadorResultados {

	private static final String TITULO_APLICACION_GRAFICAS = "Evaluación de Rendimiento";

	private static final String TITULO_GRAFICA_TIEMPOS_CARGA_MODELO = "Carga de modelo";
	private static final String TITULO_GRAFICA_TIEMPOS_PROCESAMIENTO_QUERY = "Procesamiento Query";

	/*public static void generarGraficaResultadosModeloRDF(int numeroIncremento) {
		Iterator<Entry<String, List<Long>>> iterador = ManejadorModelos.getEstadisticasModelos().entrySet().iterator();
		Grafica grafica = new Grafica(TITULO_APLICACION_GRAFICAS);

		while (iterador.hasNext()) {
			Map.Entry<String, List<Long>> entrada = (Entry<String, List<Long>>) iterador.next();
			List<Long> valorEntrada = (List<Long>) entrada.getValue();

			switch (entrada.getKey()) {
			case ManejadorModelos.LLAVE_TIEMPOS_CARGA_MODELO_RDF:
				grafica.añadirPanelGrafica(LABEL_CONVENCION_MODELO_RDF, TITULO_GRAFICA_TIEMPOS_CARGA_MODELO,
						valorEntrada, numeroIncremento, BorderLayout.WEST);
				break;
			case ManejadorModelos.LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF:
				grafica.añadirPanelGrafica(LABEL_CONVENCION_MODELO_RDF, TITULO_GRAFICA_TIEMPOS_PROCESAMIENTO_QUERY,
						valorEntrada, numeroIncremento, BorderLayout.EAST);
				break;
			default:
				break;
			}

		}

		grafica.pack();
		grafica.setVisible(true);
	}

	public static void generarGraficaResultadosModeloOWL(int numeroIncremento) {
		Iterator<Entry<String, List<Long>>> iterador = ManejadorModelos.getEstadisticasModelos().entrySet().iterator();

		Grafica grafica = new Grafica(TITULO_APLICACION_GRAFICAS);

		while (iterador.hasNext()) {
			Map.Entry<String, List<Long>> entrada = (Entry<String, List<Long>>) iterador.next();
			List<Long> valorEntrada = (List<Long>) entrada.getValue();

			switch (entrada.getKey()) {
			case ManejadorModelos.LLAVE_TIEMPOS_CARGA_MODELO_OWL:
				grafica.añadirPanelGrafica(LABEL_CONVENCION_MODELO_OWL, TITULO_GRAFICA_TIEMPOS_CARGA_MODELO,
						valorEntrada, numeroIncremento, BorderLayout.WEST);
				break;
			case ManejadorModelos.LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL:
				grafica.añadirPanelGrafica(LABEL_CONVENCION_MODELO_OWL, TITULO_GRAFICA_TIEMPOS_PROCESAMIENTO_QUERY,
						valorEntrada, numeroIncremento, BorderLayout.EAST);
			default:
				break;
			}
		}
		grafica.pack();
		grafica.setVisible(true);
	}*/

	/**
	 * 
	 * @param numeroIncremento
	 */
	public static void generarGraficaResultadosModelos(int numeroIncremento) {
		Grafica grafica = new Grafica(TITULO_APLICACION_GRAFICAS);

		List<Long> tiemposCargaModeloRDF = (ManejadorModelos.getEstadisticasModelos()
				.get(ManejadorModelos.LLAVE_TIEMPOS_CARGA_MODELO_RDF) != null
						? ManejadorModelos.getEstadisticasModelos().get(ManejadorModelos.LLAVE_TIEMPOS_CARGA_MODELO_RDF)
						: new ArrayList<Long>());
		List<Long> tiemposProcesamientoQueryModeloRDF = (ManejadorModelos.getEstadisticasModelos()
				.get(ManejadorModelos.LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF) != null
						? ManejadorModelos.getEstadisticasModelos()
								.get(ManejadorModelos.LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF)
						: new ArrayList<Long>());
		List<Long> tiemposCargaModeloOWL = (ManejadorModelos.getEstadisticasModelos()
				.get(ManejadorModelos.LLAVE_TIEMPOS_CARGA_MODELO_OWL) != null
						? ManejadorModelos.getEstadisticasModelos().get(ManejadorModelos.LLAVE_TIEMPOS_CARGA_MODELO_OWL)
						: new ArrayList<Long>());
		List<Long> tiemposProcesamientoQueryModeloOWL = (ManejadorModelos.getEstadisticasModelos()
				.get(ManejadorModelos.LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL) != null
						? ManejadorModelos.getEstadisticasModelos()
								.get(ManejadorModelos.LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL)
						: new ArrayList<Long>());
		
		grafica.adicionarPanelGrafica(TITULO_GRAFICA_TIEMPOS_CARGA_MODELO, tiemposCargaModeloRDF, tiemposCargaModeloOWL, numeroIncremento, BorderLayout.WEST);
		grafica.adicionarPanelGrafica(TITULO_GRAFICA_TIEMPOS_PROCESAMIENTO_QUERY, tiemposProcesamientoQueryModeloRDF, tiemposProcesamientoQueryModeloOWL, numeroIncremento, BorderLayout.EAST);
		
		grafica.pack();
		grafica.setVisible(true);
	}

}
