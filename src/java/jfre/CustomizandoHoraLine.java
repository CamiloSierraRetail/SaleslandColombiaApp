/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfre;

import java.text.SimpleDateFormat;
import javafx.scene.chart.LineChart;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;

/**
 *
 * @author javie
 */
public class CustomizandoHoraLine implements JRChartCustomizer {

    @Override
    public void customize(JFreeChart jfchart, JRChart jrc) {
        
           //Get the category plot
        CategoryPlot plot = (CategoryPlot) jfchart.getPlot();

        //Crete a date axis
        DateAxis yAxis = new DateAxis();
        //Override the date format
        yAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm a"));
        //Customize tick unit;
        
        //Set it to the range axis
        plot.setRangeAxis(yAxis);
    }
    
}
