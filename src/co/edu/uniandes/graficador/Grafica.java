package co.edu.uniandes.graficador;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Grafica extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	private static final String LABEL_EJE_X = "Carga";
	private static final String LABEL_EJE_Y = "Tiempo de respuesta (ms)";

	public Grafica(String tituloAplicacion) {
		super(tituloAplicacion);
	}

	public void añadirPanelGrafica(String modelo, String tituloGrafica, List<Long> datos, int numeroIncremento,
			String ubicacion) {
		JFreeChart lineChart = ChartFactory.createLineChart(tituloGrafica, LABEL_EJE_X, LABEL_EJE_Y,
				crearConjuntoDatos(modelo, datos, numeroIncremento), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		this.getContentPane().add(chartPanel, ubicacion);
	}

	public DefaultCategoryDataset crearConjuntoDatos(String modelo, List<Long> datos, int numeroIncremento) {
		DefaultCategoryDataset conjuntoDatos = new DefaultCategoryDataset();

		int contador = numeroIncremento;
		for (Long dato : datos) {
			conjuntoDatos.addValue(dato, modelo, String.valueOf(contador));
			contador += numeroIncremento;
		}

		return conjuntoDatos;
	}
}
