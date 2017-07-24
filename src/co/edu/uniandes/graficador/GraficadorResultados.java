package co.edu.uniandes.graficador;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.manejadores.ManejadorModelos;

public class GraficadorResultados {

	private static final String TITULO_APLICACION_GRAFICAS = "Evaluación de Rendimiento";

	private static final String TITULO_GRAFICA_TIEMPOS_CARGA_MODELO = "Carga de modelo";
	private static final String TITULO_GRAFICA_TIEMPOS_PROCESAMIENTO_QUERY = "Procesamiento Query";

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
