package co.edu.uniandes.graficador;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.ui.RefineryUtilities;

import co.edu.uniandes.cargamodelos.CargaModelos;

public class GraficadorResultados {

	public static void generarGraficaResultadosModeloRDF(int numeroIncremento) {
		Iterator<Entry<String, List<Long>>> iterador = CargaModelos.estadisticasModelos.entrySet().iterator();
		while (iterador.hasNext()) {
			Map.Entry<String, List<Long>> entrada = (Entry<String, List<Long>>) iterador.next();
			List<Long> valorEntrada = (List<Long>) entrada.getValue();
			if (entrada.getKey().toString().equals(CargaModelos.LLAVE_TIEMPOS_CARGA_MODELO_RDF)) {
				Grafica grafica = new Grafica("Evaluación de Rendimiento", "Carga de modelo", valorEntrada, numeroIncremento);
				grafica.pack();
				RefineryUtilities.centerFrameOnScreen(grafica);
				grafica.setVisible(true);
			}
		}
	}

}
