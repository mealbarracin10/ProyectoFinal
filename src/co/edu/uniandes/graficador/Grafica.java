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

	public Grafica(String tituloAplicacion, String tituloGrafica, List<Long> datos, int numeroIncremento) {
		super(tituloAplicacion);
		JFreeChart lineChart = ChartFactory.createLineChart(tituloGrafica, "Carga", "Tiempo de respuesta (ms)",
				crearConjuntoDatos(datos, numeroIncremento), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
	}

	public static DefaultCategoryDataset crearConjuntoDatos(List<Long> datos, int numeroIncremento) {
		DefaultCategoryDataset conjuntoDatos = new DefaultCategoryDataset();

		int contador = numeroIncremento;
		for (Long dato : datos) {
			conjuntoDatos.addValue(dato, "Tiempo de respuesta", String.valueOf(contador));
			contador += numeroIncremento;
		}

		return conjuntoDatos;
	}
}
