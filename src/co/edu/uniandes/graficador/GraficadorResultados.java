package co.edu.uniandes.graficador;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import co.edu.uniandes.manejadores.ManejadorModelos;

public class GraficadorResultados {

	public static void generarGraficaResultadosModeloRDF(int numeroIncremento) {
		Iterator<Entry<String, List<Long>>> iterador = ManejadorModelos.getEstadisticasModelos().entrySet().iterator();
		Grafica grafica = new Grafica("Evaluación de Rendimiento");

		while (iterador.hasNext()) {
			Map.Entry<String, List<Long>> entrada = (Entry<String, List<Long>>) iterador.next();
			List<Long> valorEntrada = (List<Long>) entrada.getValue();

			switch (entrada.getKey()) {
			case ManejadorModelos.LLAVE_TIEMPOS_CARGA_MODELO_RDF:
				grafica.añadirPanelGrafica("RDF", "Carga de modelo", valorEntrada, numeroIncremento, BorderLayout.WEST);
				break;
			case ManejadorModelos.LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF:
				grafica.añadirPanelGrafica("RDF", "Procesamiento Query", valorEntrada, numeroIncremento, BorderLayout.EAST);
			default:
				break;
			}

		}

		grafica.pack();
		grafica.setVisible(true);
	}

}
