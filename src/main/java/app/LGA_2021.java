package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 */
public class LGA_2021 {
   // LGA 2021 Code
    private int lga_code;
    private String lga_name;
    private String lga_type;
    private Double lga_area;
    private Double lga_latitude;
    private Double lga_longitude;
    private int lga_year;
    private String lga_state;

   /**
    * Create an LGA and set the fields
    */
   public LGA_2021(int lga_code, String lga_name, String lga_type, Double lga_area, Double lga_latitude, Double lga_longitude, int lga_year, String lga_state) {
    this.lga_code = lga_code;
    this.lga_name = lga_name;
    this.lga_type = lga_type;
    this.lga_area = lga_area;
    this.lga_latitude = lga_latitude;
    this.lga_longitude = lga_longitude;
    this.lga_year = lga_year;
    this.lga_state = lga_state;

   }

   public int getlga_code() {
      return lga_code;
   }

   public String getlga_name() {
      return lga_name;
   }
   
   public String getlga_type() {
      return lga_type;
   }

   public Double getlga_area() {
      return lga_area;
   }
   
   public Double getlga_latitude() {
      return lga_latitude;
   }
   
   public Double getlga_longitude() {
      return lga_longitude;
   }
   
   public int getlga_year() {
      return lga_year;
   }

   public String getlga_state() {
      return lga_state;
   }

}

