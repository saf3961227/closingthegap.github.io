package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Stand-alone Java file for processing the database CSV files.
 * <p>
 * You can run this file using the "Run" or "Debug" options
 * from within VSCode. This won't conflict with the web server.
 * <p>
 * This program opens a CSV file from the Closing-the-Gap data set
 * and uses JDBC to load up data into the database.
 * <p>
 * To use this program you will need to change:
 * 1. The input file location
 * 2. The output file location
 * <p>
 * This assumes that the CSV files are the the **database** folder.
 * <p>
 * WARNING: This code may take quite a while to run as there will be a lot
 * of SQL insert statments!
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au

 */
public class CTGProcessCSVLGA2020 {

   // MODIFY these to load/store to/from the correct locations
   
   private static final String DATABASE = "jdbc:sqlite:database/ctg.db";
   private static final String CSV_FILE = "database/lgas_2020.csv";


   public static void main (String[] args) {
      // The following arrays define the order of columns in the INPUT CSV.
      // The order of each array MUST match the order of the CSV.
      // These are specific to the given file and should be changed for each file.
      // This is a *simple* way to help you get up and running quickly without being confusing
      

      // JDBC Database Object
      Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            

            // Save the lga_code as we need it for the foreign key
            String LGAcode = rowScanner.next();

            // Skip lga_name
            String Longitude;

            String LGAname = rowScanner.next();
            String LGAtype = rowScanner.next();
            String Area = rowScanner.next();
            String Latitude = rowScanner.next();
            if(Latitude.isEmpty()) {
                Latitude = "0";
            }
            if(rowScanner.hasNext()) {
                Longitude = rowScanner.next();
            }
            else {
                Longitude = "0";
            }
           


            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
        

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into LGA VALUES ("
                              + LGAcode + ","
                              + "'" + LGAname + "',"
                              + "'" + LGAtype + "',"
                              + Area + ","
                              + Latitude + ","
                              + Longitude + ","
                              + "2021" + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      
   }
}
