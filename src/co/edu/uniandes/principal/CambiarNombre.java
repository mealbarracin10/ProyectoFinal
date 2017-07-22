package co.edu.uniandes.principal;

import co.edu.uniandes.graficador.GraficadorResultados;
import co.edu.uniandes.manejadores.ManejadorArchivos;

public class CambiarNombre {

	// Ruta del archivo que contiene el modelo RDF
	private static final String RUTA_ARCHIVO_MODELO_RDF = "src/recursos/productos_modelo_RDF.xml";

	// Constantes de mensajes de error
	private static final String ERROR = "********** ERROR ************\n";
	private static final String ERROR_EJECUCION_COMPONENTE = "Error al ejecutar el componente: \nOpcion no valida, debe ejecutarse de la siguiente forma: \n java -jar <Nombre>.jar (# de repeticiones del experimento) (# de incremento)";
	private static final String ERROR_NUMERO_REPETICIONES = "Error al ejecutar el componente: \nOpcion no valida, el número de repeticiones debe ser un entero positivo y mayor a 0";

	/**
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		StringBuffer mensaje = new StringBuffer();
		mensaje.append(ERROR);

		if (args.length == 2) {
			int numeroRepeticiones = Integer.valueOf(args[0]);
			int numeroIncremento = Integer.valueOf(args[1]);

			if (numeroRepeticiones > 0) {
				try {
					ManejadorArchivos.cargarPoblarArchivoModeloRDF(RUTA_ARCHIVO_MODELO_RDF, numeroRepeticiones,
							numeroIncremento);
					GraficadorResultados.generarGraficaResultadosModeloRDF(numeroIncremento);
					ManejadorArchivos.restablecerArchivoModelo(RUTA_ARCHIVO_MODELO_RDF);
				} catch (Exception e) {
					mensaje.append(e.getMessage());
					System.out.println(mensaje);
				}
			} else {
				mensaje.append(ERROR_NUMERO_REPETICIONES);
				System.out.println(mensaje.toString());
			}

		} else {
			mensaje.append(ERROR_EJECUCION_COMPONENTE);
			System.out.println(mensaje.toString());
		}

	}

}
