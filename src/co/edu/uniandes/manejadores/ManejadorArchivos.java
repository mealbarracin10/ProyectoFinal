package co.edu.uniandes.manejadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class ManejadorArchivos {

	private static final String TAG_INICIO_ARCHIVO_MODELO_RDF = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
			+ "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n"
			+ "xmlns:prop=\"http://www.elfuturoeshoy.mipropia.com#\">\n";
	private static final String TAG_FIN_ARCHIVO_MODELO_RDF = "</rdf:RDF>";

	private static final String[] DISPONIBILIDAD_PPRODUCTOS = { "True", "False" };
	private static final String[] CATEGORIAS_PRODUCTOS = { "Clothes", "Electronics", "Music", "Home", "Office" };
	private static final String[] PROVEEDORES_PRODUCTOS = { "Adidas", "Nike", "Puma", "Sony", "Samsung", "Apple" };
	private static final String[] COLORES_PRODUCTOS = { "Yellow", "Blue", "Red" };

	private static final String FORMATO_FECHA_YYYY_MM_DD_HH_MI_SS = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 
	 * @param rutaArchivoModeloRDF
	 * @param numeroProductos
	 * @throws IOException
	 */
	public static void cargarPoblarArchivoModeloRDF(String rutaArchivoModeloRDF, int numeroElementos,
			int numeroIncrmento) throws IOException {

		// Generación de nuevos productos
		generarNuevoContenidoModeloRDF(rutaArchivoModeloRDF, numeroElementos, numeroIncrmento);
	}

	/**
	 * 
	 * 
	 * @param numeroProductos
	 * @return
	 * @throws IOException
	 */
	public static void generarNuevoContenidoModeloRDF(String rutaArchivoModeloRDF, int numeroElementos,
			int numeroIncremento) throws IOException {
		// Tags comunes del modelo RDF
		String primeraParteTagDescription = "<rdf:Description rdf:about=\"http://elfuturoeshoy.mipropia.com/#P00";
		String segundaParteTagDescription = "\">\n";
		String terceraParteTagDescription = "</rdf:Description>\n";

		// Propiedades de un producto
		int identificadorProducto = 1;
		int indice = 0;
		String disponibilidad = null;
		String categoria = null;
		String proveedor = null;
		String color = null;
		Date fechaCreacion;
		String fechaCreacionCadena = null;
		Date fechaModificacion;
		String fechaModificacionCadena = null;
		int inventario = 0;
		int peso = 0;
		int costo = 0;
		int precio = 0;

		// Formato de fecha
		DateFormat formatoFecha = new SimpleDateFormat(FORMATO_FECHA_YYYY_MM_DD_HH_MI_SS);

		// Generación de nuevos productos
		StringBuffer nuevosProductos = new StringBuffer();
		nuevosProductos.append("\n<!-- Productos -->\n");

		for (int i = 0; i < (numeroElementos * numeroIncremento); i++) {

			// Disponibilidad producto
			indice = ThreadLocalRandom.current().nextInt(DISPONIBILIDAD_PPRODUCTOS.length);
			disponibilidad = DISPONIBILIDAD_PPRODUCTOS[indice];

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:isAvailable rdf:resource=\"http://elfuturoeshoy.mipropia.com#")
					.append(disponibilidad).append("\"/>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Categoria producto
			indice = ThreadLocalRandom.current().nextInt(CATEGORIAS_PRODUCTOS.length);
			categoria = CATEGORIAS_PRODUCTOS[indice];

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasCategory rdf:resource=\"http://elfuturoeshoy.mipropia.com#")
					.append(categoria).append("\"/>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Proveedor producto
			indice = ThreadLocalRandom.current().nextInt(PROVEEDORES_PRODUCTOS.length);
			proveedor = PROVEEDORES_PRODUCTOS[indice];

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasProvider rdf:resource=\"http://elfuturoeshoy.mipropia.com#")
					.append(proveedor).append("\"/>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Unidad de medida (Peso) producto
			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasUnitMeasure rdf:resource=\"http://elfuturoeshoy.mipropia.com#Grams\"/>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Color producto
			indice = ThreadLocalRandom.current().nextInt(COLORES_PRODUCTOS.length);
			color = COLORES_PRODUCTOS[indice];

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasColor rdf:resource=\"http://elfuturoeshoy.mipropia.com#").append(color)
					.append("\"/>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Fecha creación registro producto
			fechaCreacion = new Date();
			fechaCreacionCadena = formatoFecha.format(fechaCreacion);

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasDateCreated>").append(fechaCreacionCadena)
					.append("</prop:hasDateCreated>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Fecha modificación registro producto
			fechaModificacion = new Date();
			fechaModificacionCadena = formatoFecha.format(fechaModificacion);

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasDateModified>").append(fechaModificacionCadena)
					.append("</prop:hasDateModified>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Nombre Producto
			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasName>").append("Name_").append(identificadorProducto)
					.append("</prop:hasName>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Descripción Producto
			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasDescription>").append("Description_").append(identificadorProducto)
					.append("</prop:hasDescription>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Inventario producto
			inventario = ThreadLocalRandom.current().nextInt(10000);

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasInventory>").append(inventario).append("</prop:hasInventory>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Peso producto
			peso = ThreadLocalRandom.current().nextInt(100000000);

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasWeight>").append(peso).append("</prop:hasWeight>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Costo producto
			costo = ThreadLocalRandom.current().nextInt(100000000);

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasCost>").append(costo).append("</prop:hasCost>\n");
			nuevosProductos.append(terceraParteTagDescription);

			// Precio producto
			precio = ThreadLocalRandom.current().nextInt((costo + 100), 1000000000);

			nuevosProductos.append(primeraParteTagDescription).append(identificadorProducto)
					.append(segundaParteTagDescription);
			nuevosProductos.append("<prop:hasPrice>").append(precio).append("</prop:hasPrice>\n");
			nuevosProductos.append(terceraParteTagDescription);

			nuevosProductos.append("\n");

			// Si la cantidad de nuevos elementos del modelo RDF es multiplo del número de
			// incremento (cantidad de elementos para genrar estadisticas) se generan las
			// estadisticas del modelo
			if (i > 1 & (i % numeroIncremento == 0)) {
				generarEstadisticasModeloRDF(rutaArchivoModeloRDF, nuevosProductos.toString());

				// Se limpia la cantidad de elementos anterior
				nuevosProductos.delete(0, nuevosProductos.length());
			}

			identificadorProducto++;
		}
	}

	/**
	 * 
	 * @param rutaArchivoModeloRDF
	 * @param nuevoContenidoModeloRDF
	 * @throws IOException
	 */
	private static void generarEstadisticasModeloRDF(String rutaArchivoModeloRDF, String nuevoContenidoModeloRDF)
			throws IOException {
		// Carga del archivo que contiene el modelo RDF desde la ruta especificada
		Path ruta = Paths.get(rutaArchivoModeloRDF);
		Charset codificacionCaracteres = StandardCharsets.UTF_8;

		// Adición de nuevos productos al modelo RDF y sobreescritura del archivo que lo
		// contiene
		if (ruta != null) {
			String contenido = new String(Files.readAllBytes(ruta), codificacionCaracteres);
			contenido = contenido.replace(TAG_FIN_ARCHIVO_MODELO_RDF,
					nuevoContenidoModeloRDF + TAG_FIN_ARCHIVO_MODELO_RDF);
			Files.write(ruta, contenido.getBytes(codificacionCaracteres));
		}

		// Cargar modelo RDF y calcular tiempos con el nuevo contenido
		ManejadorModelos.cargarValidarModeloRDF(rutaArchivoModeloRDF);
	}

	/**
	 * 
	 * @param rutaArchivoModelo
	 * @throws IOException
	 */
	public static void restablecerArchivoModelo(String rutaArchivoModelo) throws IOException {
		File archivoModelo = new File(rutaArchivoModelo);
		FileWriter escritorArchivo = new FileWriter(archivoModelo);
		BufferedWriter bufferEscritura = new BufferedWriter(escritorArchivo);
		bufferEscritura.write(TAG_INICIO_ARCHIVO_MODELO_RDF);
		bufferEscritura.write(TAG_FIN_ARCHIVO_MODELO_RDF);
		bufferEscritura.close();
	}

}
