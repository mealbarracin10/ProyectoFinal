package co.edu.uniandes.graficador;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class Grafica extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	private static final String LABEL_EJE_X = "Carga";
	private static final String LABEL_EJE_Y = "Tiempo de respuesta (ms)";
	
	private static final String LABEL_CONVENCION_MODELO_RDF = "RDF";
	private static final String LABEL_CONVENCION_MODELO_OWL = "OWL";

	public Grafica(String tituloAplicacion) {
		super(tituloAplicacion);
	}

	public void adicionarPanelGrafica(String tituloGrafica, List<Long> datosModeloRDF, List<Long> datosModeloOWL,
			int numeroIncremento, String ubicacion) {
		JFreeChart xyLineChart = ChartFactory.createXYLineChart(tituloGrafica, LABEL_EJE_X, LABEL_EJE_Y,
				crearConjuntoDatos(datosModeloRDF, datosModeloOWL, numeroIncremento), PlotOrientation.VERTICAL, true,
				true, false);

		XYPlot xyPlot = xyLineChart.getXYPlot();
		xyPlot.setBackgroundPaint(Color.white);
		xyPlot.setDomainGridlinePaint(Color.lightGray);
		xyPlot.setRangeGridlinePaint(Color.lightGray);

		ChartPanel chartPanel = new ChartPanel(xyLineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		this.getContentPane().add(chartPanel, ubicacion);
	}

	/*
	 * public DefaultCategoryDataset crearConjuntoDatos(String modelo, List<Long>
	 * datos, int numeroIncremento) { DefaultCategoryDataset conjuntoDatos = new
	 * DefaultCategoryDataset();
	 * 
	 * int contador = numeroIncremento; for (Long dato : datos) {
	 * conjuntoDatos.addValue(dato, modelo, String.valueOf(contador)); contador +=
	 * numeroIncremento; }
	 * 
	 * return conjuntoDatos; }
	 */

	/**
	 * 
	 * 
	 * @param datosModeloRDF
	 * @param datosModeloOWL
	 * @param numeroIncremento
	 * @return
	 */
	public XYSeriesCollection crearConjuntoDatos(List<Long> datosModeloRDF, List<Long> datosModeloOWL,
			int numeroIncremento) {
		XYSeries serieRDF = new XYSeries(LABEL_CONVENCION_MODELO_RDF);

		int contador = numeroIncremento;
		for (Long dato : datosModeloRDF) {
			serieRDF.add(contador, dato.doubleValue());
			contador += numeroIncremento;
		}

		XYSeries serieOWL = new XYSeries(LABEL_CONVENCION_MODELO_OWL);
		contador = numeroIncremento;
		for (Long dato : datosModeloOWL) {
			serieOWL.add(contador, dato.doubleValue());
			contador += numeroIncremento;
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		xySeriesCollection.addSeries(serieRDF);
		xySeriesCollection.addSeries(serieOWL);

		return xySeriesCollection;
	}
}
