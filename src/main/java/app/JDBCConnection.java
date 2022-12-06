package app;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/ctg.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<LGA> getLGAs() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int code16     = results.getInt("lga_code16");
                String name16  = results.getString("lga_name16");

                // Create a LGA Object
                LGA lga = new LGA(code16, name16);

                // Add the lga object to the array
                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }



    // TODO: Add your required methods here

    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    //RETRIEVING PERSONA NAME AND IMAGE FILE FROM DB (SAIF)
    public ArrayList<Persona> getPersonas() {
        // Create the ArrayList of LGA objects to return
        ArrayList<Persona> personas = new ArrayList<Persona>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Persona";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name     = results.getString("Name");
                String image_file_path  = results.getString("ImageFileName");

                // Create a LGA Object
                Persona persona = new Persona(name, image_file_path);

                // Add the lga object to the array
                personas.add(persona);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return personas;
    }
     /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<PersonaAttribute> getPersonaAttributes() {
        // Create the ArrayList of LGA objects to return
        ArrayList<PersonaAttribute> personaAttributes = new ArrayList<PersonaAttribute>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM PersonaAttribute";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int id     = results.getInt("ID");
                String persona_name = results.getString("Name");
                String AttributeType = results.getString("AttributeName");
                String Description = results.getString("AttributeDesc");
                // Create a LGA Object
                PersonaAttribute personaAttribute = new PersonaAttribute(id, persona_name, AttributeType, Description);

                // Add the lga object to the array
                personaAttributes.add(personaAttribute);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return personaAttributes;
    }
    //RETRIEVING PERSONA DESCRIPTION FROM DB
    public ArrayList<PersonaAttribute> getPersonaAttributesByNameAndType(String persona_name, String AttributeType) {
        // Create the ArrayList of LGA objects to return
        ArrayList<PersonaAttribute> personaAttributes = new ArrayList<PersonaAttribute>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM PersonaAttribute WHERE Name ='" + persona_name + "' AND AttributeName = '" + AttributeType + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int id     = results.getInt("ID");
                persona_name = results.getString("Name");
                AttributeType = results.getString("AttributeName");
                String Description = results.getString("AttributeDesc");
                // Create a LGA Object
                PersonaAttribute personaAttribute = new PersonaAttribute(id, persona_name, AttributeType, Description);

                // Add the lga object to the array
                personaAttributes.add(personaAttribute);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return personaAttributes;
    }










        // OLIVIA BELOW RETRIEVING OUTCOMES FROM DB

         /**
     * Get all of the OUTCOMESs in the database.
     * @return
     *    Returns an ArrayList of OUTCOME objects
     */
    public ArrayList<Outcome> getOutcomes() {
        // Create the ArrayList of LGA objects to return
        ArrayList<Outcome> outcomes = new ArrayList<Outcome>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Outcome";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String outcomeNum     = results.getString("OutcomeNum");
                String outcomeName  = results.getString("OutcomeName");
                String outcomeFocus = results.getString("IsFocus");

                // Create a outcome Object
                Outcome outcome = new Outcome(outcomeNum, outcomeName, outcomeFocus);

                // Add the outcome object to the array
                outcomes.add(outcome);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the outcomes
        return outcomes;
    }



    // OLIVIA GETTING THE POPULATION DATA FROM DB

    public ArrayList<String> getPopulationData(String sortBy, String sortOrder, int pageNum) {
        ArrayList<String> populations = new ArrayList<String>();
        Connection connection = null;
        int returnPageRows = 10;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);


            String[] arrOfCategory = sortBy.split("_");
            
            String query = "SELECT P1.*, P2.LGAname AS `LGAname_2`, P2.IndigenousStatus AS `IndigenousStatus_2`, ";
            query += "P2.`00-04` AS `00-04_2`, P2.`05-09` AS `05-09_2`, P2.`10-14` AS `10-14_2`, P2.`15-19` AS `15-19_2`, ";
            query += "P2.`20-24` AS `20-24_2`, P2.`25-29` AS `25-29_2`, P2.`30-34` AS `30-34_2`, P2.`35-39` AS `35-39_2`,  ";
            query += "P2.`40-44` AS `40-44_2`, P2.`45-49` AS `45-49_2`, P2.`50-54` AS `50-54_2`, P2.`55-59` AS `55-59_2`, ";
            query += "P2.`60-64` AS `60-64_2`, P2.`65+` AS `65+_2` ";
            query += "FROM PopulationAggregated2021 AS P1 ";
            query += "INNER JOIN PopulationAggregated2021 AS P2 ";
            query += "ON P1.LGAname = P2.LGAname AND P1.IndigenousStatus <> P2.IndigenousStatus ";
            query += "GROUP BY P1.LGAname ";
            query += "ORDER BY ";

            if(arrOfCategory[0].equals("LGAname")){
                query += "P1.LGAname";
            }
            else{
                String columnNo = "";
                if(arrOfCategory[1].equals("non-indig")) {
                    columnNo = "_2";
                }

                query += "`" + arrOfCategory[0] + columnNo + "`";
            }
            query += " " + sortOrder;
            query += " LIMIT "; 
            int offset = (returnPageRows * pageNum) - returnPageRows;
            query += offset + ", " + returnPageRows + ";";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                populations.add(results.getString("LGAname"));
                populations.add(results.getString("IndigenousStatus"));
                populations.add(String.valueOf(results.getInt("00-04")));
                populations.add(String.valueOf(results.getInt("05-09")));
                populations.add(String.valueOf(results.getInt("10-14")));
                populations.add(String.valueOf(results.getInt("15-19")));
                populations.add(String.valueOf(results.getInt("20-24")));
                populations.add(String.valueOf(results.getInt("25-29")));
                populations.add(String.valueOf(results.getInt("30-34")));
                populations.add(String.valueOf(results.getInt("35-39")));
                populations.add(String.valueOf(results.getInt("40-44")));
                populations.add(String.valueOf(results.getInt("45-49")));
                populations.add(String.valueOf(results.getInt("50-54")));
                populations.add(String.valueOf(results.getInt("55-59")));
                populations.add(String.valueOf(results.getInt("60-64")));
                populations.add(String.valueOf(results.getInt("65+")));

                populations.add(results.getString("LGAname_2"));
                populations.add(results.getString("IndigenousStatus_2"));
                populations.add(String.valueOf(results.getInt("00-04_2")));
                populations.add(String.valueOf(results.getInt("05-09_2")));
                populations.add(String.valueOf(results.getInt("10-14_2")));
                populations.add(String.valueOf(results.getInt("15-19_2")));
                populations.add(String.valueOf(results.getInt("20-24_2")));
                populations.add(String.valueOf(results.getInt("25-29_2")));
                populations.add(String.valueOf(results.getInt("30-34_2")));
                populations.add(String.valueOf(results.getInt("35-39_2")));
                populations.add(String.valueOf(results.getInt("40-44_2")));
                populations.add(String.valueOf(results.getInt("45-49_2")));
                populations.add(String.valueOf(results.getInt("50-54_2")));
                populations.add(String.valueOf(results.getInt("55-59_2")));
                populations.add(String.valueOf(results.getInt("60-64_2")));
                populations.add(String.valueOf(results.getInt("65+_2")));

            }

            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return populations;
    }




    // OLIVIA GETTING THE INCOME DATA FROM DB

    public ArrayList<String> getIncomeData(String sortBy, String sortOrder, int pageNum) {
        ArrayList<String> incomes = new ArrayList<String>();
        Connection connection = null;
        int returnPageRows = 10;


        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String[] arrOfCategory = sortBy.split("_");

            String query = "SELECT I1.*, I2.LGAname AS `LGAname_2`, I2.IndigenousStatus AS `IndigenousStatus_2`, ";
            query += "I2.`1-149` AS `1-149_2`, I2.`150-299` AS `150-299_2`, I2.`300-399` AS `300-399_2`, I2.`400-499` AS `400-499_2`, ";
            query += "I2.`500-649` AS `500-649_2`, I2.`650-799` AS `650-799_2`, I2.`800-999` AS `800-999_2`, I2.`1000-1249` AS `1000-1249_2`, ";
            query += "I2.`1250-1499` AS `1250-1499_2`, I2.`1500-1749` AS `1500-1749_2`, I2.`1750-1999` AS `1750-1999_2`, I2.`2000-2499` AS `2000-2499_2`, ";
            query += "I2.`2500-2999` AS `2500-2999_2`, I2.`3000-3499` AS `3000-3499_2`, I2.`3500+` AS `3500+_2` ";
            query += "FROM IncomeAggregated2021 AS I1 ";
            query += "INNER JOIN IncomeAggregated2021 AS I2 ";
            query += "ON I1.LGAname = I2.LGAname AND I1.IndigenousStatus <> I2.IndigenousStatus ";
            query += "GROUP BY I2.LGAname ";
            query += "ORDER BY ";

            if(arrOfCategory[0].equals("LGAname")){
                query += "I1.LGAname";
            }
            else{
                String columnNo = "";
                if(arrOfCategory[1].equals("non-indig")) {
                    columnNo = "_2";
                }

                query += "`" + arrOfCategory[0] + columnNo + "`";
            }
            query += " " + sortOrder;
            query += " LIMIT "; 
            int offset = (returnPageRows * pageNum) - returnPageRows;
            query += offset + ", " + returnPageRows + ";";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                incomes.add(results.getString("LGAname"));
                incomes.add(results.getString("IndigenousStatus"));
                incomes.add(String.valueOf(results.getInt("1-149")));
                incomes.add(String.valueOf(results.getInt("150-299")));
                incomes.add(String.valueOf(results.getInt("300-399")));
                incomes.add(String.valueOf(results.getInt("400-499")));
                incomes.add(String.valueOf(results.getInt("500-649")));
                incomes.add(String.valueOf(results.getInt("650-799")));
                incomes.add(String.valueOf(results.getInt("800-999")));
                incomes.add(String.valueOf(results.getInt("1000-1249")));
                incomes.add(String.valueOf(results.getInt("1250-1499")));
                incomes.add(String.valueOf(results.getInt("1500-1749")));
                incomes.add(String.valueOf(results.getInt("1750-1999")));
                incomes.add(String.valueOf(results.getInt("2000-2499")));
                incomes.add(String.valueOf(results.getInt("2500-2999")));
                incomes.add(String.valueOf(results.getInt("3000-3499")));
                incomes.add(String.valueOf(results.getInt("3500+")));

                incomes.add(results.getString("LGAname_2"));
                incomes.add(results.getString("IndigenousStatus_2"));
                incomes.add(String.valueOf(results.getInt("1-149_2")));
                incomes.add(String.valueOf(results.getInt("150-299_2")));
                incomes.add(String.valueOf(results.getInt("300-399_2")));
                incomes.add(String.valueOf(results.getInt("400-499_2")));
                incomes.add(String.valueOf(results.getInt("500-649_2")));
                incomes.add(String.valueOf(results.getInt("650-799_2")));
                incomes.add(String.valueOf(results.getInt("800-999_2")));
                incomes.add(String.valueOf(results.getInt("1000-1249_2")));
                incomes.add(String.valueOf(results.getInt("1250-1499_2")));
                incomes.add(String.valueOf(results.getInt("1500-1749_2")));
                incomes.add(String.valueOf(results.getInt("1750-1999_2")));
                incomes.add(String.valueOf(results.getInt("2000-2499_2")));
                incomes.add(String.valueOf(results.getInt("2500-2999_2")));
                incomes.add(String.valueOf(results.getInt("3000-3499_2")));
                incomes.add(String.valueOf(results.getInt("3500+_2")));

            }

            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return incomes;
    }



    // OLIVIA GETTING THE HEALTHCONDITION DATA FROM DB

    public ArrayList<String> getHealthConditionsData(String sortBy, String sortOrder, int pageNum) {
        ArrayList<String> conditions = new ArrayList<String>();
        Connection connection = null;
        int returnPageRows = 10;


        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String[] arrOfCategory = sortBy.split("_");

            String query = "SELECT H1.*, H2.LGAname AS `LGAname_2`, H2.IndigenousStatus AS `IndigenousStatus_2`, ";
            query += "H2.`Arthritis` AS `Arthritis_2`, H2.`Asthma` AS `Asthma_2`, H2.`Cancer` AS `Cancer_2`, H2.`Dementia` AS `Dementia_2`, ";
            query += "H2.`Diabetes` AS `Diabetes_2`, H2.`HeartDisease` AS `HeartDisease_2`, H2.`KidneyDisease` AS `KidneyDisease_2`, H2.`LungCondition` AS `LungCondition_2`, ";
            query += "H2.`MentalHealth` AS `MentalHealth_2`, H2.`Stroke` AS `Stroke_2`, H2.`Other` AS `Other_2` ";
            query += "FROM HealthConditionAggregated2021 AS H1 ";
            query += "INNER JOIN HealthConditionAggregated2021 AS H2 ";
            query += "ON H1.LGAname = H2.LGAname AND H1.IndigenousStatus <> H2.IndigenousStatus ";
            query += "GROUP BY H1.LGAname ";
            query += "ORDER BY ";

            if(arrOfCategory[0].equals("LGAname")){
                query += "H1.LGAname";
            }
            else{
                String columnNo = "";
                if(arrOfCategory[1].equals("non-indig")) {
                    columnNo = "_2";
                }

                query += "`" + arrOfCategory[0] + columnNo + "`";
            }
            query += " " + sortOrder;
            query += " LIMIT "; 
            int offset = (returnPageRows * pageNum) - returnPageRows;
            query += offset + ", " + returnPageRows + ";"; 

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                conditions.add(results.getString("LGAname"));
                conditions.add(results.getString("IndigenousStatus"));
                conditions.add(String.valueOf(results.getInt("Arthritis")));
                conditions.add(String.valueOf(results.getInt("Asthma")));
                conditions.add(String.valueOf(results.getInt("Cancer")));
                conditions.add(String.valueOf(results.getInt("Dementia")));
                conditions.add(String.valueOf(results.getInt("Diabetes")));
                conditions.add(String.valueOf(results.getInt("HeartDisease")));
                conditions.add(String.valueOf(results.getInt("KidneyDisease")));
                conditions.add(String.valueOf(results.getInt("LungCondition")));
                conditions.add(String.valueOf(results.getInt("MentalHealth")));
                conditions.add(String.valueOf(results.getInt("Stroke")));
                conditions.add(String.valueOf(results.getInt("Other")));

                conditions.add(results.getString("LGAname_2"));
                conditions.add(results.getString("IndigenousStatus_2"));
                conditions.add(String.valueOf(results.getInt("Arthritis_2")));
                conditions.add(String.valueOf(results.getInt("Asthma_2")));
                conditions.add(String.valueOf(results.getInt("Cancer_2")));
                conditions.add(String.valueOf(results.getInt("Dementia_2")));
                conditions.add(String.valueOf(results.getInt("Diabetes_2")));
                conditions.add(String.valueOf(results.getInt("HeartDisease_2")));
                conditions.add(String.valueOf(results.getInt("KidneyDisease_2")));
                conditions.add(String.valueOf(results.getInt("LungCondition_2")));
                conditions.add(String.valueOf(results.getInt("MentalHealth_2")));
                conditions.add(String.valueOf(results.getInt("Stroke_2")));
                conditions.add(String.valueOf(results.getInt("Other_2")));
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return conditions;
    }

    // OLIVIA GETTING THE EDUCATION DATA FROM DB

    public ArrayList<String> getEducationData(String sortBy, String sortOrder, int pageNum) {
        ArrayList<String> educations = new ArrayList<String>();
        Connection connection = null;
        int returnPageRows = 10;


        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String[] arrOfCategory = sortBy.split("_");

            String query = "SELECT E1.*, E2.LGAname AS `LGAname_2`, E2.IndigenousStatus AS `IndigenousStatus_2`, ";
            query += "E2.`NoSchooling` AS `NoSchooling_2`, E2.`Year8Below` AS `Year8Below_2`, E2.`Year9` AS `Year9_2`, E2.`Year10` AS `Year10_2`, ";
            query += "E2.`Year11` AS `Year11_2`, E2.`Year12` AS `Year12_2` ";
            query += "FROM EducationAggregated2021 AS E1 ";
            query += "INNER JOIN EducationAggregated2021 AS E2 ";
            query += "ON E1.LGAname = E2.LGAname AND E1.IndigenousStatus <> E2.IndigenousStatus ";
            query += "GROUP BY E1.LGAname ";
            query += "ORDER BY ";

            if(arrOfCategory[0].equals("LGAname")){
                query += "E1.LGAname";
            }
            else{
                String columnNo = "";
                if(arrOfCategory[1].equals("non-indig")) {
                    columnNo = "_2";
                }

                query += "`" + arrOfCategory[0] + columnNo + "`";
            }
            query += " " + sortOrder;
            query += " LIMIT "; 
            int offset = (returnPageRows * pageNum) - returnPageRows;
            query += offset + ", " + returnPageRows + ";";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                educations.add(results.getString("LGAname"));
                educations.add(results.getString("IndigenousStatus"));
                educations.add(String.valueOf(results.getInt("NoSchooling")));
                educations.add(String.valueOf(results.getInt("Year8Below")));
                educations.add(String.valueOf(results.getInt("Year9")));
                educations.add(String.valueOf(results.getInt("Year10")));
                educations.add(String.valueOf(results.getInt("Year11")));
                educations.add(String.valueOf(results.getInt("Year12")));

                educations.add(results.getString("LGAname_2"));
                educations.add(results.getString("IndigenousStatus_2"));
                educations.add(String.valueOf(results.getInt("NoSchooling_2")));
                educations.add(String.valueOf(results.getInt("Year8Below_2")));
                educations.add(String.valueOf(results.getInt("Year9_2")));
                educations.add(String.valueOf(results.getInt("Year10_2")));
                educations.add(String.valueOf(results.getInt("Year11_2")));
                educations.add(String.valueOf(results.getInt("Year12_2")));
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return educations;
    }


    
    // OLIVIA GETTING THE GAP SCORE DATA FROM DB

    public ArrayList<String> getGapScoreData(ArrayList<String> datasets, String area, String popRange, String sortBy, String sortOrder, int pageNum) {
        ArrayList<String> gapScores = new ArrayList<String>();
        Connection connection = null;
        int returnPageRows = datasets.size() != 0 ? 20 / datasets.size() : 20;
        int numScores2016 = datasets.contains("dataset2") ? (datasets.size()-1) : datasets.size(); // REMOVING 1 BC NO HEALTHCOND DATA FOR 2016


        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // QUERY GETTING INDIVIDUAL LGA BY YEAR GAP SCORES
            // THEN CALCULATING CHANGE IN GAP SCORES
            // THEN CALCULATING AVG GAP SCORE BY LGA AND YEAR ACROSS ALL DATASETS CHOSEN
            // THEN CALCULATING THE CHANGE IN GAP SCORE FROM THESE AVG
            String query = "SELECT LGAname, ";
            query += "`PopGapScore2016`, `PopGapScore2021`, ";
            query += "(`PopGapScore2016`-`PopGapScore2021`) AS 'PopChangeInGapScore', ";
            query += "`HcGapScore2016`, `HcGapScore2021`, ";
            query += "(`HcGapScore2016`-`HcGapScore2021`) AS 'HcChangeInGapScore', ";
            query += "`EduGapScore2016`, `EduGapScore2021`, ";
            query += "(`EduGapScore2016`-`EduGapScore2021`) AS 'EduChangeInGapScore', ";
            query += "`IncGapScore2016`, `IncGapScore2021`, ";
            query += "(`IncGapScore2016`-`IncGapScore2021`) AS 'IncChangeInGapScore', ";
             // CHANGING THE QUERY  BASED ON USER SELECTIONS
            String avgGap2016 = "((";
            if(datasets.contains("dataset1")) {
                avgGap2016 += "`PopGapScore2016` + ";
            }
            if(datasets.contains("dataset2")) {
            // NO DATA FOR 2016 HC DATASET
            }
            if(datasets.contains("dataset3")) {
                avgGap2016 += "`EduGapScore2016` + ";
            }
            if(datasets.contains("dataset4")) {
                avgGap2016 += "`IncGapScore2016` + ";
            }
            avgGap2016 += "0) / " + numScores2016 + ")";
            query += avgGap2016 + " AS 'AvgGapScore2016', ";

            //REPEATING FOR 2021
            String avgGap2021 = "((";
            if(datasets.contains("dataset1")) {
                avgGap2021 += "`PopGapScore2021` + ";
            }
            if(datasets.contains("dataset2")) {
                avgGap2021 += "`HcGapScore2021` + ";
            }
            if(datasets.contains("dataset3")) {
                avgGap2021 += "`EduGapScore2021` + ";
            }
            if(datasets.contains("dataset4")) {
                avgGap2021 += "`IncGapScore2021` + ";
            }
            avgGap2021 += "0) / " + datasets.size() + ")";
            query += avgGap2021 + " AS 'AvgGapScore2021', ";
            query += "(" + avgGap2016 + " - " + avgGap2021 +") AS 'TotalGapScoreChange'";
            query += "FROM GapScores_View ";

            // CHANGING THE WHERE STATEMENT BASED ON USER FILTER CHOICES FOR AREA OR POPULATION RANGES
            if (area.equals("allAreas")) {
                query += "WHERE TRUE ";
            } 
            else if (area.equals("150000+")) {
                query += "WHERE Area > 150000 ";
            }
            else {
                String[] range = area.split("-");
                query += "WHERE (Area >= " + range[0] + " AND Area < " + range[1] + ") ";
            }
            if (popRange.equals("allPopRanges")) {
            }
            else if (popRange.equals("200000+")) {
                query += "AND (LGAtotalPop2016 > 200000 OR LGAtotalPop2021 > 200000) ";
            }
            else {
                String[] range = popRange.split("-");
                query += "AND ((LGAtotalPop2016 >= " + range[0] + " AND LGAtotalPop2016 < " + range[1] + " ) ";
                query += "OR (LGAtotalPop2021 >= " + range[0] + " AND LGAtotalPop2021 < " + range[1] + " )) ";
            }
            query += "ORDER BY " + sortBy;
            query += " " + sortOrder;
            query += " LIMIT ";
            int offset = (returnPageRows * pageNum) - returnPageRows;
            query += offset + ", " + returnPageRows + ";";
            
            // EXECUTING QUERY
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {

                if(datasets.contains("dataset1")) {
                    gapScores.add(results.getString("LGAname"));
                    gapScores.add("Dataset 1");
                    gapScores.add("Living a long life");
                    gapScores.add(results.getObject("PopGapScore2016") != null ? String.format("%.2f", results.getDouble("PopGapScore2016")) : "-");
                    gapScores.add(results.getObject("PopGapScore2021") != null ? String.format("%.2f", results.getDouble("PopGapScore2021")) : "-");
                    gapScores.add(results.getObject("PopChangeInGapScore") != null ? String.format("%.2f", results.getDouble("PopChangeInGapScore")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2016") != null ? String.format("%.2f", results.getDouble("AvgGapScore2016")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2021") != null ? String.format("%.2f", results.getDouble("AvgGapScore2021")) : "-");
                    gapScores.add(results.getObject("TotalGapScoreChange") != null ? String.format("%.2f", results.getDouble("TotalGapScoreChange")) : "-");
                }

                if(datasets.contains("dataset2")) {
                    gapScores.add(results.getString("LGAname"));
                    gapScores.add("Dataset 2");
                    gapScores.add("Living a healthy life");
                    gapScores.add(results.getObject("HcGapScore2016") != null ? String.format("%.2f", results.getDouble("HcGapScore2016")) : "-");
                    gapScores.add(results.getObject("HcGapScore2021") != null ? String.format("%.2f", results.getDouble("HcGapScore2021")) : "-");
                    gapScores.add(results.getObject("HcChangeInGapScore") != null ? String.format("%.2f", results.getDouble("HcChangeInGapScore")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2016") != null ? String.format("%.2f", results.getDouble("AvgGapScore2016")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2021") != null ? String.format("%.2f", results.getDouble("AvgGapScore2021")) : "-");
                    gapScores.add(results.getObject("TotalGapScoreChange") != null ? String.format("%.2f", results.getDouble("TotalGapScoreChange")) : "-");
                }

                if(datasets.contains("dataset3")) {
                    gapScores.add(results.getString("LGAname"));
                    gapScores.add("Dataset 3");
                    gapScores.add("Full learning potential");
                    gapScores.add(results.getObject("EduGapScore2016") != null ? String.format("%.2f", results.getDouble("EduGapScore2016")) : "-");
                    gapScores.add(results.getObject("EduGapScore2021") != null ? String.format("%.2f", results.getDouble("EduGapScore2021")) : "-");
                    gapScores.add(results.getObject("EduChangeInGapScore") != null ? String.format("%.2f", results.getDouble("EduChangeInGapScore")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2016") != null ? String.format("%.2f", results.getDouble("AvgGapScore2016")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2021") != null ? String.format("%.2f", results.getDouble("AvgGapScore2021")) : "-");
                    gapScores.add(results.getObject("TotalGapScoreChange") != null ? String.format("%.2f", results.getDouble("TotalGapScoreChange")) : "-");
                }

                if(datasets.contains("dataset4")) {
                    gapScores.add(results.getString("LGAname"));
                    gapScores.add("Dataset 4");
                    gapScores.add("Economic Contribution");
                    gapScores.add(results.getObject("IncGapScore2016") != null ? String.format("%.2f", results.getDouble("IncGapScore2016")) : "-");
                    gapScores.add(results.getObject("IncGapScore2021") != null ? String.format("%.2f", results.getDouble("IncGapScore2021")) : "-");
                    gapScores.add(results.getObject("IncChangeInGapScore") != null ? String.format("%.2f", results.getDouble("IncChangeInGapScore")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2016") != null ? String.format("%.2f", results.getDouble("AvgGapScore2016")) : "-");
                    gapScores.add(results.getObject("AvgGapScore2021") != null ? String.format("%.2f", results.getDouble("AvgGapScore2021")) : "-");
                    gapScores.add(results.getObject("TotalGapScoreChange") != null ? String.format("%.2f", results.getDouble("TotalGapScoreChange")) : "-");
                }

                    
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return gapScores;
    }







    //SAIF GETTING LGA 2021 DATA FROM DB
    public ArrayList<LGA_2021> getLGA_2021(String lga_name) {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA_2021> lgas_2021 = new ArrayList<LGA_2021>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                SELECT *,
                CASE
                    When LGAcode LIKE '1%' then 'NSW'
                    When LGAcode LIKE '2%' then 'Victoria'
                    When LGAcode LIKE '3%' then 'QLD'
                    When LGAcode LIKE '4%' then 'South Australia'
                    When LGAcode LIKE '5%' then 'Western Australia'
                    When LGAcode LIKE '6%' then 'Tasmania'
                    When LGAcode LIKE '7%' then 'Northern Territory'
                    When LGAcode LIKE '8%' then 'ACT'
                    When LGAcode LIKE '9%' then 'Other'--other cases
                END as State
                FROM LGA WHERE Year = '2021' AND LGAname = '""" + lga_name + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // System.out.println(query);
            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int lga_code     = results.getInt("LGAcode");
                lga_name = results.getString("LGAname");
                String lga_type = results.getString("LGAtype");
                Double lga_area = results.getDouble("Area");
                Double lga_latitude = results.getDouble("Latitude");
                Double lga_longitude = results.getDouble("Longitude");
                int lga_year = results.getInt("Year");
                String lga_state = results.getString("State");
                // Create a LGA Object
                LGA_2021 lga_2021 = new LGA_2021(lga_code, lga_name, lga_type, lga_area, lga_latitude, lga_longitude, lga_year, lga_state);

                // Add the lga object to the array
                lgas_2021.add(lga_2021);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas_2021;
    }
    //SAIF getting total population change from DB
    public ArrayList<Integer> getPopulationChange(String lganame) {
        // Create the ArrayList of LGA objects to return
        ArrayList<Integer> total_changes = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                select (select 
                sum(count)
            from population
            where year = 2021 and lgacode = (select lgacode from lga where lganame = '""" + lganame + "')" +
            """
            group by
                year, lgacode) - (select 
                sum(count)
            from population
            where year = 2016 and lgacode = (select lgacode from lga where lganame = '""" + lganame + "')" +
            """
            group by
                year, lgacode) AS total_change        
                    """;;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // System.out.println(query);
            // Process all of the results
            int total_change;

            while (results.next()) {
                // Lookup the columns we need
                // String indig_status = results.getString("IndigenousStatus");
                // int age_group = results.getInt("AgeGroup");
                // String gender = results.getString("Gender");
                // int count = results.getInt("Count");
                // int lga_code = results.getInt("LGAcode");
                // int year = results.getInt("Year");
                total_change = results.getInt("total_change");

                // Add the lga object to the array
                total_changes.add(total_change);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return total_changes;
    }
    
    //SAIF getting dataset1 change from DB
    public ArrayList<PopulationDataset> getPopulationDataset(String lga_name) {
        // Create the ArrayList of LGA objects to return
        ArrayList<PopulationDataset> dataset_population_changes = new ArrayList<PopulationDataset>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                select
                -- all columns except count, lgacode, year
                a.IndigenousStatus,
                a.AgeGroup,
                a.Gender,
                sum(case when a.Year = 2016 then a.Count else 0 end) as Population_2016,
                sum(case when a.Year = 2021 then a.Count else 0 end) as Population_2021,
                sum(case when a.Year = 2021 then a.Count else 0 end) - sum(case when a.Year = 2016 then a.Count else 0 end) as Change 
            from
                Population a join LGA b
                on a.lgacode = b.lgacode
            where
                b.LGAName = '""" + lga_name + "'" + """
            group by
                
                a.IndigenousStatus,
                a.AgeGroup,
                a.Gender,
                a.LGAcode;  
                
                """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
          //  System.out.println(query);
            // Process all of the results

            while (results.next()) {
                // Lookup the columns we need
                String indig_status = results.getString("IndigenousStatus");
                String age_group = results.getString("AgeGroup");
                String gender = results.getString("Gender");
                int population_2016 = results.getInt("Population_2016");
                int population_2021 = results.getInt("Population_2021");
                int change = results.getInt("Change");

                // Create a PopulationDataset object
                PopulationDataset dataset_population_change = new PopulationDataset(indig_status, age_group, gender, population_2016, population_2021, change);

                // Add the lga object to the array
                dataset_population_changes.add(dataset_population_change);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_population_changes;
    }

        //SAIF getting dataset3 change from DB
    public ArrayList<EducationDataset> getEducationDataset(String lga_name) {
            // Create the ArrayList of LGA objects to return
            ArrayList<EducationDataset> dataset_education_changes = new ArrayList<EducationDataset>();
    
            // Setup the variable for the JDBC connection
            Connection connection = null;
    
            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);
    
                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                
                // The Query
                String query = """
                    select
                    -- all columns except count, lgacode, year
                    a.IndigenousStatus,
                    a.SchoolYear,
                    a.Gender,
                    sum(case when a.Year = 2016 then a.Count else 0 end) as Population_2016,
                    sum(case when a.Year = 2021 then a.Count else 0 end) as Population_2021,
                    sum(case when a.Year = 2021 then a.Count else 0 end) - sum(case when a.Year = 2016 then a.Count else 0 end) as Change 
                from
                    Education a join LGA b
                    on a.lgacode = b.lgacode
                where
                    b.LGAName = '""" + lga_name + "'" + """
                         -- java variable to be passed here 
                group by
                    -- all columns except count, year
                    a.IndigenousStatus,
                    a.SchoolYear,
                    a.Gender,
                    a.LGAcode;
                    
                    """;
                
                // Get Result
                ResultSet results = statement.executeQuery(query);
              //  System.out.println(query);
                // Process all of the results
    
                while (results.next()) {
                    // Lookup the columns we need
                    String indig_status = results.getString("IndigenousStatus");
                    String school_year = results.getString("SchoolYear");
                    String gender = results.getString("Gender");
                    int population_2016 = results.getInt("Population_2016");
                    int population_2021 = results.getInt("Population_2021");
                    int change = results.getInt("Change");
    
                    // Create a PopulationDataset object
                    EducationDataset dataset_education_change = new EducationDataset(indig_status, school_year, gender, population_2016, population_2021, change);
    
                    // Add the lga object to the array
                    dataset_education_changes.add(dataset_education_change);
                }
    
                // Close the statement because we are done with it
                statement.close();
            } catch (SQLException e) {
                // If there is an error, lets just pring the error
                System.err.println(e.getMessage());
            } finally {
                // Safety code to cleanup
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
            }
    
            // Finally we return all of the lga
            return dataset_education_changes;
        }

    //SAIF getting dataset4 change from DB
    public ArrayList<IncomeDataset> getIncomeDataset(String lga_name) {
        // Create the ArrayList of LGA objects to return
        ArrayList<IncomeDataset> dataset_income_changes = new ArrayList<IncomeDataset>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                select
                -- all columns except count, lgacode, year
                a.IndigenousStatus,
                a.IncomeBracket,
                sum(case when a.Year = 2016 then a.Count else 0 end) as Population_2016,
                sum(case when a.Year = 2021 then a.Count else 0 end) as Population_2021,
                sum(case when a.Year = 2021 then a.Count else 0 end) - sum(case when a.Year = 2016 then a.Count else 0 end) as Change 
            from
                Income a join LGA b
                on a.lgacode = b.lgacode
            where
                b.LGAName = '""" + lga_name + "'" + """ 
            group by
                -- all columns except count, year
                a.IndigenousStatus,
                a.IncomeBracket,
                a.LGAcode;    
                
                """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
          //  System.out.println(query);
            // Process all of the results

            while (results.next()) {
                // Lookup the columns we need
                String indig_status = results.getString("IndigenousStatus");
                String income_bracket = results.getString("IncomeBracket");
                int population_2016 = results.getInt("Population_2016");
                int population_2021 = results.getInt("Population_2021");
                int change = results.getInt("Change");

                // Create a PopulationDataset object
                IncomeDataset dataset_income_change = new IncomeDataset(indig_status, income_bracket, population_2016, population_2021, change);

                // Add the lga object to the array
                dataset_income_changes.add(dataset_income_change);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_income_changes;
    }

    public ArrayList<EducationProp> getEducationProp(String indig_status, String school_year, String gender) {
        // Create the ArrayList of LGA objects to return
        ArrayList<EducationProp> dataset_education_props = new ArrayList<EducationProp>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                with population_table as (select
                lgacode,
                sum(count) as total_population
            from
                education
            where
                year = 2021
            group by
                lgacode,
                year)
                
            select
                a.lgacode,
                c.lganame,
                sum(a.count),
                b.total_population,
                printf('%.2f', sum(cast(a.count as float)) / (cast(b.total_population as float)) * 100) as percentage
                
            from
                education a left join population_table b 
                on a.lgacode = b.lgacode join lga c on a.lgacode = c.lgacode 
            where
                a.year = 2021    
                and a.IndigenousStatus = '""" + indig_status + "'" + """  
                and a.SchoolYear = '""" + school_year + "'" + """
                and gender = '""" + gender + "'" + """
            group by
                a.lgacode,
                a.year,
                a.IndigenousStatus,
                a.SchoolYear,
                a.gender    
            order by percentage desc            
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results

            while (results.next()) {
                // Lookup the columns we need
                String lga_name = results.getString("lganame");
                Float prop_data = results.getFloat("percentage");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                EducationProp dataset_education_prop = new EducationProp(lga_name, prop_data);

                // Add the lga object to the array
                dataset_education_props.add(dataset_education_prop);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_education_props;
    }
    public ArrayList<PopulationProp> getPopulationProp(String indig_status, String age_group, String gender) {
        // Create the ArrayList of LGA objects to return
        ArrayList<PopulationProp> dataset_population_props = new ArrayList<PopulationProp>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                with population_table as (select
                lgacode,
                sum(count) as total_population
            from
                population
            where
                year = 2021
            group by
                lgacode,
                year)
                
            select
                a.lgacode,
                c.lganame,
                sum(a.count),
                b.total_population,
                printf('%.2f', sum(cast(a.count as float)) / (cast(b.total_population as float)) * 100) as percentage
                
            from
                population a left join population_table b 
                on a.lgacode = b.lgacode join lga c on a.lgacode = c.lgacode 
            where
                a.year = 2021    
                and a.IndigenousStatus = '""" + indig_status + "'" + """  
                and a.AgeGroup = '""" + age_group + "'" + """
                and gender = '""" + gender + "'" + """
            group by
                a.lgacode,
                a.year,
                a.IndigenousStatus,
                a.AgeGroup,
                a.gender    
            order by percentage desc            
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results

            while (results.next()) {
                // Lookup the columns we need
                String lga_name = results.getString("lganame");
                Float prop_data = results.getFloat("percentage");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                PopulationProp dataset_population_prop = new PopulationProp(lga_name, prop_data);

                // Add the lga object to the array
                dataset_population_props.add(dataset_population_prop);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_population_props;
    }
    public ArrayList<IncomeProp> getIncomeProp(String indig_status, String income_bracket) {
        // Create the ArrayList of LGA objects to return
        ArrayList<IncomeProp> dataset_income_props = new ArrayList<IncomeProp>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                with population_table as (select
                lgacode,
                sum(count) as total_population
            from
                Income
            where
                year = 2021
            group by
                lgacode,
                year)
                
            select
                a.lgacode,
                c.lganame,
                sum(a.count),
                b.total_population,
                printf('%.2f', sum(cast(a.count as float)) / (cast(b.total_population as float)) * 100) as percentage
                
            from
                Income a left join population_table b 
                on a.lgacode = b.lgacode join lga c on a.lgacode = c.lgacode 
            where
                a.year = 2021    
                and a.IndigenousStatus = '""" + indig_status + "'" + """  
                and a.IncomeBracket = '""" + income_bracket + "'" + """
            group by
                a.lgacode,
                a.year,
                a.IndigenousStatus,
                a.IncomeBracket    
            order by percentage desc            
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results

            while (results.next()) {
                // Lookup the columns we need
                String lga_name = results.getString("lganame");
                Float prop_data = results.getFloat("percentage");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                IncomeProp dataset_income_prop = new IncomeProp(lga_name, prop_data);

                // Add the lga object to the array
                dataset_income_props.add(dataset_income_prop);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_income_props;

    }
    public ArrayList<IncomeSim> getIncomeSim(String indig_status, String income_bracket, String lga_name) {
        // Create the ArrayList of LGA objects to return
        ArrayList<IncomeSim> dataset_income_sims = new ArrayList<IncomeSim>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                with population_table as (
                    select
                        lgacode,
                        sum(count) as total_population
                    from
                        Income -- change table name
                    where
                        year = 2021
                    group by
                        lgacode,
                        year
                    ),
                proportions as (
                    select
                        c.lganame,
                        a.lgacode,
                        sum(a.count),
                        b.total_population,
                        printf("%.4f", sum(cast(a.count as float)) / (cast(b.total_population as float)) * 100) as percentage
                        
                    from
                        Income a left join population_table b -- change table name Income
                        on a.lgacode = b.lgacode join lga c on a.lgacode = c.lgacode 
                    where
                        a.year = 2021    
                        and a.IndigenousStatus = '""" + indig_status + "'" + """  
                        and a.IncomeBracket = '""" + income_bracket + "'" + """
                        
                    group by
                        a.lgacode,
                        a.year,
                        a.IndigenousStatus, --this will change
                        a.IncomeBracket    --this will change
                     
                    order by percentage desc
                    )
                
                select
                    *, ABS((select percentage from proportions where lganame = '""" + lga_name + "'" + """  
                        ) - percentage) as difference  -- lganame should be variable
                from
                    proportions
                where
                    lganame != '""" + lga_name + "'" + """  
                order by
                    difference asc;         
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results

            while (results.next()) {
                // Lookup the columns we need
                String lgaName = results.getString("lganame");
                Float dif_sim = results.getFloat("difference");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                IncomeSim dataset_income_sim = new IncomeSim(lgaName, dif_sim);

                // Add the lga object to the array
                dataset_income_sims.add(dataset_income_sim);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_income_sims;
    }

    public ArrayList<PopulationSim> getPopulationSim(String indig_status, String age_group, String lga_name, String gender) {
        // Create the ArrayList of LGA objects to return
        ArrayList<PopulationSim> dataset_population_sims = new ArrayList<PopulationSim>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                with population_table as (
                    select
                        lgacode,
                        sum(count) as total_population
                    from
                        Population -- change table name
                    where
                        year = 2021
                    group by
                        lgacode,
                        year
                    ),
                proportions as (
                    select
                        c.lganame,
                        a.lgacode,
                        sum(a.count),
                        b.total_population,
                        printf("%.4f", sum(cast(a.count as float)) / (cast(b.total_population as float)) * 100) as percentage
                        
                    from
                        Population a left join population_table b -- change table name Income
                        on a.lgacode = b.lgacode join lga c on a.lgacode = c.lgacode 
                    where
                        a.year = 2021    
                        and a.IndigenousStatus = '""" + indig_status + "'" + """  
                        and a.AgeGroup = '""" + age_group + "'" + """
                        and gender = '""" + gender + "'" + """
                        
                    group by
                        a.lgacode,
                        a.year,
                        a.IndigenousStatus, 
                        a.AgeGroup, --this will change
                        a.gender    
                     
                    order by percentage desc
                    )
                
                select
                    *, ABS((select percentage from proportions where lganame = '""" + lga_name + "'" + """  
                        ) - percentage) as difference  -- lganame should be variable
                from
                    proportions
                where
                    lganame != '""" + lga_name + "'" + """  
                order by
                    difference asc;         
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results
            System.out.println(query);

            while (results.next()) {
                // Lookup the columns we need
                String lgaName = results.getString("lganame");
                Float dif_sim = results.getFloat("difference");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                PopulationSim dataset_population_sim = new PopulationSim(lgaName, dif_sim);

                // Add the lga object to the array
                dataset_population_sims.add(dataset_population_sim);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_population_sims;
    }
    public ArrayList<HealthSim> getHealthSim(String indig_status, String health_condition, String lga_name, String gender) {
        // Create the ArrayList of LGA objects to return
        ArrayList<HealthSim> dataset_health_sims = new ArrayList<HealthSim>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                with population_table as (
                    select
                        lgacode,
                        sum(count) as total_population
                    from
                        HealthCondition -- change table name
                    where
                        year = 2021
                    group by
                        lgacode,
                        year
                    ),
                proportions as (
                    select
                        c.lganame,
                        a.lgacode,
                        sum(a.count),
                        b.total_population,
                        printf("%.4f", sum(cast(a.count as float)) / (cast(b.total_population as float)) * 100) as percentage
                        
                    from
                        HealthCondition a left join population_table b -- change table name Income
                        on a.lgacode = b.lgacode join lga c on a.lgacode = c.lgacode 
                    where
                        a.year = 2021    
                        and a.IndigenousStatus = '""" + indig_status + "'" + """  
                        and a.HealthCondition = '""" + health_condition + "'" + """
                        and gender = '""" + gender + "'" + """
                        
                    group by
                        a.lgacode,
                        a.year,
                        a.IndigenousStatus, 
                        a.HealthCondition, --this will change
                        a.gender    
                     
                    order by percentage desc
                    )
                
                select
                    *, ABS((select percentage from proportions where lganame = '""" + lga_name + "'" + """  
                        ) - percentage) as difference  -- lganame should be variable
                from
                    proportions
                where
                    lganame != '""" + lga_name + "'" + """  
                order by
                    difference asc;         
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results
            System.out.println(query);

            while (results.next()) {
                // Lookup the columns we need
                String lgaName = results.getString("lganame");
                Float dif_sim = results.getFloat("difference");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                HealthSim dataset_health_sim = new HealthSim(lgaName, dif_sim);

                // Add the lga object to the array
                dataset_health_sims.add(dataset_health_sim);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_health_sims;
    }

    public ArrayList<EducationSim> getEducationSim(String indig_status, String school_year, String lga_name, String gender) {
        // Create the ArrayList of LGA objects to return
        ArrayList<EducationSim> dataset_education_sims = new ArrayList<EducationSim>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                with population_table as (
                    select
                        lgacode,
                        sum(count) as total_population
                    from
                        Education -- change table name
                    where
                        year = 2021
                    group by
                        lgacode,
                        year
                    ),
                proportions as (
                    select
                        c.lganame,
                        a.lgacode,
                        sum(a.count),
                        b.total_population,
                        printf("%.4f", sum(cast(a.count as float)) / (cast(b.total_population as float)) * 100) as percentage
                        
                    from
                        Education a left join population_table b -- change table name Income
                        on a.lgacode = b.lgacode join lga c on a.lgacode = c.lgacode 
                    where
                        a.year = 2021    
                        and a.IndigenousStatus = '""" + indig_status + "'" + """  
                        and a.SchoolYear = '""" + school_year + "'" + """
                        and gender = '""" + gender + "'" + """
                        
                    group by
                        a.lgacode,
                        a.year,
                        a.IndigenousStatus, 
                        a.SchoolYear, --this will change
                        a.gender    
                     
                    order by percentage desc
                    )
                
                select
                    *, ABS((select percentage from proportions where lganame = '""" + lga_name + "'" + """  
                        ) - percentage) as difference  -- lganame should be variable
                from
                    proportions
                where
                    lganame != '""" + lga_name + "'" + """  
                order by
                    difference asc;         
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results
            System.out.println(query);

            while (results.next()) {
                // Lookup the columns we need
                String lgaName = results.getString("lganame");
                Float dif_sim = results.getFloat("difference");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                EducationSim dataset_education_sim = new EducationSim(lgaName, dif_sim);

                // Add the lga object to the array
                dataset_education_sims.add(dataset_education_sim);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return dataset_education_sims;
    }

    public ArrayList<LGAname> getLGAname() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGAname> lga_names = new ArrayList<LGAname>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            // The Query
            String query = """
                SELECT distinct lganame FROM LGA        
            """;
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results
            System.out.println(query);

            while (results.next()) {
                // Lookup the columns we need
                String lgaName = results.getString("lganame");
                // Float dif_sim = results.getFloat("difference");
                // String gender = results.getString("Gender");
                // int population_2016 = results.getInt("Population_2016");
                // int population_2021 = results.getInt("Population_2021");
                // int change = results.getInt("Change");

                // Create a PopulationDataset object
                LGAname lga_name = new LGAname(lgaName);

                // Add the lga object to the array
                lga_names.add(lga_name);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lga_names;
    }

    



}
