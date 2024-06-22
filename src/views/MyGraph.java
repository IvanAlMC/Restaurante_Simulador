package views;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class MyGraph extends JFrame{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int value1;
	private int value2;
	private int value3;
	
	public MyGraph(int v1, int v2, int v3) {
		
		this.value1 = v1;
		this.value2 = v2;
		this.value3 = v3;
	        
	        // Propiedades necesarias para la grafica
	        CategoryDataset dataset = createDataset();
	        
	        JFreeChart chart = ChartFactory.createBarChart("Rendimiento en cocina", 
	                "Cocina", 
	                "Cantidad de platos hechos", 
	                dataset,
	                PlotOrientation.VERTICAL,
	                true, true, false
	        );
	        
	        ChartPanel panel = new ChartPanel(chart);
	        setContentPane(panel);
	    
	    }

	    // Datos a mostrar en la grafica
	    private CategoryDataset createDataset() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        
	        dataset.addValue(this.value1, "C-1", "Cocinero 1");
	        dataset.addValue(this.value2, "C-2", "Cocinero 2");
	        dataset.addValue(this.value3, "C-3", "Cocinero 3");
	        
	        return dataset;
	    }
	    
	    // Comportamiento de la ventana con la grafica
	    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
	        SwingUtilities.invokeAndWait(()->{
	            MyGraph ejemplo = new MyGraph(1, 2, 3);
	            ejemplo.setSize(800, 400);
	            ejemplo.setLocationRelativeTo(null);
	            ejemplo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	            ejemplo.setVisible(true);
	        });
	    }

}
