package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 */
public class PopulationChange {
   // LGA 2021 Code
    private String indig_status;
    private int age_group;
    private String gender;
    private int population_2016;
    private int lga_code;
    private int year;
    private int total_change;


   

   /**
    * Create an LGA and set the fields
    */
   public PopulationChange(int total_change) {
   //  this.indig_status = indig_status;
   //  this.age_group = age_group;
   //  this.gender = gender;
   //  this.count = count;
   //  this.lga_code = lga_code;
   //  this.year = year;
    this.total_change = total_change;
   }



   // public String getindig_status() {
   //    return indig_status;
   // }
   
   // public int getage_group() {
   //    return age_group;
   // }
   
   // public String getgender() {
   //    return gender;
   // }
   
   // public int getcount() {
   //    return count;
   // }
   
   // public int getlga_code() {
   //    return lga_code;
   // }

   // public int getyear() {
   //    return year;
   // }

   public int gettotal_change() {
      return total_change;
   }

}

