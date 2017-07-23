package co.edu.uniandes.manejadores;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class ManejadorModelos {

	public static final String LLAVE_TIEMPOS_CARGA_MODELO_RDF = "TiemposCargaModeloRDF";
	public static final String LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF = "TiemposEjecucionQueryModeloRDF";

	public static final String LLAVE_TIEMPOS_CARGA_MODELO_OWL = "TiemposCargaModeloOWL";
	public static final String LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL = "TiemposEjecucionQueryModeloOWL";

	
	private static HashMap<String, List<Long>> estadisticasModelos = new HashMap<>();

	/**
	 * 
	 * @param rutaArchivoModeloRDF
	 */
	public static void cargarValidarModeloRDF(String rutaArchivoModeloRDF) {

		// Creación del modelo por defecto
		Model modeloRDF = ModelFactory.createDefaultModel();

		// Carga del archivo modificado que contiene el modelo RDF
		InputStream inputStream = FileManager.get().open(rutaArchivoModeloRDF);

		if (inputStream != null) {
			//
			Date inicioLecturaModelo = new Date();

			// Lectura del contenido del archivo en el modelo RDF
			modeloRDF.read(inputStream, null);

			//
			Date finLecturaModelo = new Date();

			//
			Long tiempoLecturaModelo = finLecturaModelo.getTime() - inicioLecturaModelo.getTime();

			if (estadisticasModelos.containsKey(LLAVE_TIEMPOS_CARGA_MODELO_RDF)
					& estadisticasModelos.get(LLAVE_TIEMPOS_CARGA_MODELO_RDF) != null) {
				estadisticasModelos.get(LLAVE_TIEMPOS_CARGA_MODELO_RDF).add(tiempoLecturaModelo);
			} else {
				List<Long> tiemposCargaModeloRDF = new ArrayList<Long>();
				tiemposCargaModeloRDF.add(tiempoLecturaModelo);
				estadisticasModelos.put(LLAVE_TIEMPOS_CARGA_MODELO_RDF, tiemposCargaModeloRDF);

			}

			System.out.println("######### Tiempo de carga del modelo: " + tiempoLecturaModelo + " ms");

			// create query string
			String queryString = "SELECT ?x ?p ?y " + "\n";
			queryString += "WHERE {?x ?p ?y}";

			//
			Date inicioEjecucionQuery = new Date();

			// create ARQ query
			Query query = QueryFactory.create(queryString);
			// execute query
			QueryExecution qe = QueryExecutionFactory.create(query, modeloRDF);
			// collect results
			qe.execSelect();

			//
			Date finEjecucionQuery = new Date();

			//
			Long tiempoEjecucionQuery = finEjecucionQuery.getTime() - inicioEjecucionQuery.getTime();

			if (estadisticasModelos.containsKey(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF)
					& estadisticasModelos.get(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF) != null) {
				estadisticasModelos.get(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF).add(tiempoEjecucionQuery);
			} else {
				List<Long> tiemposEjecucionQueryModeloRDF = new ArrayList<Long>();
				tiemposEjecucionQueryModeloRDF.add(tiempoLecturaModelo);
				estadisticasModelos.put(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_RDF, tiemposEjecucionQueryModeloRDF);
			}
			System.out.println(
					"######### Tiempo de ejecución del query sobre el modelo: " + tiempoEjecucionQuery + " ms");
		}

	}

	public static HashMap<String, List<Long>> getEstadisticasModelos() {
		return estadisticasModelos;
	}

	public static void cargarValidarModeloOWL(String rutaArchivoModeloOWL) {	
		// create ontology model
		OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		// get url path
		InputStream inputStream = FileManager.get().open(rutaArchivoModeloOWL);
		if (inputStream != null) {
		Date tiempoinicioLecturaModelo = new Date();
		// read the ontology
		Model ModeloOWL = ontology.read(inputStream, "RDF/XML");
		Date tiempofinLecturaModelo = new Date();
		Long tiempoLecturaModelo = tiempofinLecturaModelo.getTime() -tiempoinicioLecturaModelo.getTime();
				
		if (estadisticasModelos.containsKey(LLAVE_TIEMPOS_CARGA_MODELO_OWL)
				& estadisticasModelos.get(LLAVE_TIEMPOS_CARGA_MODELO_OWL) != null) {
			estadisticasModelos.get(LLAVE_TIEMPOS_CARGA_MODELO_OWL).add(tiempoLecturaModelo);
		} else {
			List<Long> tiemposCargaModeloOWL = new ArrayList<Long>();
			tiemposCargaModeloOWL.add(tiempoLecturaModelo);
			estadisticasModelos.put(LLAVE_TIEMPOS_CARGA_MODELO_OWL, tiemposCargaModeloOWL);
		}		
		System.out.println("######### Tiempo de carga del modelo: " + tiempoLecturaModelo + " ms");
		// create query string
		String queryString = "SELECT ?x ?p ?y " + "\n";
		queryString += "WHERE {?x ?p ?y}";
		Date inicioEjecucionQuery = new Date();
		
		// create ARQ query
		Query query = QueryFactory.create(queryString);
		
		// execute query
		QueryExecution qe = QueryExecutionFactory.create(query, ModeloOWL);
		
		// collect results
		qe.execSelect();
		Date finEjecucionQuery = new Date();
		Long tiempoEjecucionQuery = finEjecucionQuery.getTime() - inicioEjecucionQuery.getTime();
		if (estadisticasModelos.containsKey(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL)
			& estadisticasModelos.get(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL) != null) {
			estadisticasModelos.get(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL).add(tiempoEjecucionQuery);
		} else {
			List<Long> tiemposEjecucionQueryModeloOWL = new ArrayList<Long>();
			tiemposEjecucionQueryModeloOWL.add(tiempoLecturaModelo);
			estadisticasModelos.put(LLAVE_TIEMPOS_EJECUCION_QUERY_MODELO_OWL, tiemposEjecucionQueryModeloOWL);
			  }		
		System.out.println(
				"######### Tiempo de ejecución del query sobre el modelo: " + tiempoEjecucionQuery + " ms");
		}	
	}
}
