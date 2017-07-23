import java.io.IOException;

import co.edu.uniandes.graficador.GraficadorResultados;
import co.edu.uniandes.manejadores.ManejadorArchivos;

public class Test {
	
	private static final String RUTA_ARCHIVO_MODELO_OWL = "src/recursos/productos_modelo_OWL.owl";
	static int numeroElementos = 10;
	static int numeroIncremento = 10;
	private static final String ERROR = "********** ERROR ************\n";
	private static final String ERROR_NUMERO_REPETICIONES = "Error al ejecutar el componente: \nOpcion no valida, el número de repeticiones debe ser un entero positivo y mayor a 0";


	public static void main(String[] args) throws IOException {	
		StringBuffer mensaje = new StringBuffer();
		mensaje.append(ERROR);
		
		if (numeroIncremento > 0) {
			try {
				ManejadorArchivos.generarNuevoContenidoModeloOWL(RUTA_ARCHIVO_MODELO_OWL, numeroElementos, numeroIncremento);
				GraficadorResultados.generarGraficaResultadosModeloOWL(numeroIncremento);
				ManejadorArchivos.restablecerArchivoModeloOWL(RUTA_ARCHIVO_MODELO_OWL);
			} catch (Exception e) {
				mensaje.append(e.getMessage());
				System.out.println(mensaje);
			}
		} else {
			mensaje.append(ERROR_NUMERO_REPETICIONES);
			System.out.println(mensaje.toString());
		}

	}

}
