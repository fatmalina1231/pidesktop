/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import static com.itextpdf.kernel.pdf.PdfName.Height;
import static com.itextpdf.kernel.pdf.PdfName.Width;
import java.io.*;

import java.sql.*;
import java.util.StringTokenizer;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.plaf.nimbus.State;

import static javafx.scene.input.KeyCode.M;

public class Statistics {
    public static void main( String[ ] args )throws Exception {

        String mobilebrands[] = {
                "Male",
                "Female"
        };


       String jdbcURL = "jdbc:mysql://localhost:3306/pssss";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
      String sql = "SELECT count(case when gender='male' then 1 end) as male_count, count(case when gender='female' then 1 end) as female_count FROM `dev`";
        
       Statement statement = connection.createStatement();
 
            ResultSet result = statement.executeQuery(sql);
        // ResultSet resultSet = stmt.executeQuery("SELECT COUNT(gender) as men FROM `member` where gender = 'M'");
        
        DefaultPieDataset dataset = new DefaultPieDataset( );

        while( result.next( ) ) {
            dataset.setValue("Women",  result.getInt( "female_count" ));
            dataset.setValue("Men",  result.getInt( "male_count" ));
        }

        JFreeChart chart = ChartFactory.createPieChart("Gender distribution amongst members",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false );

        int width = 800;      
       int height = 600;   
        File pieChart = new File( "Gender_Distribution.jpeg" );
        ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
    }
}


