/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Mega-PC
 */

 
 import java.io.*;
import java.sql.*;
import java.util.*;
 import java.util.Date;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
 
/**
 * Sample Java program that imports data from an Excel file to MySQL database.
 *
 * @author Nam Ha Minh - https://www.codejava.net
 *
 */
public class exceltobase {
 
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/pssss";
        String username = "root";
        String password = "";
 
        String excelFilePath = "uwu.xlsx";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
            long start = System.currentTimeMillis();
             
            FileInputStream inputStream = new FileInputStream(excelFilePath);
 
            PreparedStatement statement;
            try (Workbook workbook = new XSSFWorkbook(inputStream)) {
                Sheet firstSheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = firstSheet.iterator();
                connection = DriverManager.getConnection(jdbcURL, username, password);
                connection.setAutoCommit(false);
                String sql = "INSERT INTO dev (grpname, fullname, mail, gender) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                int count = 0;
                rowIterator.next(); // skip the header row
                while (rowIterator.hasNext()) {
                    Row nextRow = rowIterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    
                    while (cellIterator.hasNext()) {
                        Cell nextCell = cellIterator.next();
                        
                        int columnIndex = nextCell.getColumnIndex();
                        
                        switch (columnIndex) {
                            case 0:
                                String grpname = nextCell.getStringCellValue();
                                statement.setString(1, grpname);
                                break;
                            case 1:
                                String fullname = nextCell.getStringCellValue();
                                statement.setString(2, fullname);
                            case 2:
                                String mail = nextCell.getStringCellValue();
                                statement.setString(3, mail);
                              case 3:
                                String gender = nextCell.getStringCellValue();
                                statement.setString(4, gender);  
                                
                        }
                        
                    }
 
                    statement.addBatch();
                    
                    if (count % batchSize == 0) {
                        statement.executeBatch();
                    }
                    
                }
            }
             
            // execute the remaining queries
            statement.executeBatch();
  
            connection.commit();
            connection.close();
             
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
             
        } catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            System.out.println("Database error");
            ex2.printStackTrace();
        }
 
    }
}