package co.edu.uniandes.principal;

import co.edu.uniandes.graficador.GraficadorResultados;
import co.edu.uniandes.manejadores.ManejadorArchivos;

public class EvaluadorRendimiento {

	// Ruta del archivo que contiene el modelo RDF
	private static final String RUTA_ARCHIVO_MODELO_RDF = "recursos/productos_modelo_RDF.xml";

	// Ruta del archivo que contiene el modelo OWL
	private static final String RUTA_ARCHIVO_MODELO_OWL = "recursos/productos_modelo_OWL.owl";

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
					// Modelo RDF
					ManejadorArchivos.cargarPoblarArchivoModeloRDF(RUTA_ARCHIVO_MODELO_RDF, numeroRepeticiones,
							numeroIncremento);

					// Ontología
					ManejadorArchivos.generarNuevoContenidoModeloOWL(RUTA_ARCHIVO_MODELO_OWL, numeroRepeticiones,
							numeroIncremento);

					// Generación de la gráfica con los resultados de la evaluación de rendimientos
					// de los modelos
					GraficadorResultados.generarGraficaResultadosModelos(numeroIncremento);

					// Borrado de los archivos de los modelos para utlizar desde 0 en una nueva
					// prueba
					ManejadorArchivos.restablecerArchivoModeloRDF(RUTA_ARCHIVO_MODELO_RDF);
					ManejadorArchivos.restablecerArchivoModeloOWL(RUTA_ARCHIVO_MODELO_OWL);

				} catch (Exception e) {
					System.out.println(mensaje);
					e.printStackTrace();
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
