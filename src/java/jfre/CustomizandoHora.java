/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfre;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.SimpleDateFormat;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author javie
 */
public class CustomizandoHora implements JRChartCustomizer {

    @Override
    public void customize(JFreeChart jfchart, JRChart jrchart) {

        //Get the category plot
        CategoryPlot plot = (CategoryPlot) jfchart.getPlot();

        //Crete a date axis
        DateAxis yAxis = new DateAxis();
        //Override the date format
        yAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm a"));
        //Set it to the range axis
 
        plot.setRangeAxis(yAxis);
        
        //adding a customize item label renderer to view the valus on the barchart
        CategoryItemRenderer renderer = ((CategoryPlot) jfchart.getPlot()).getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",new SimpleDateFormat("HH:mm a")));
        renderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
        renderer.setBasePositiveItemLabelPosition(position);
    }

}