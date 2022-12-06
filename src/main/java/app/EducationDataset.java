package app;



public class EducationDataset {
    
    private String indig_status;
    private String school_year;
    private String gender;
    private int population_2016;
    private int population_2021;
    private int change;
   


   

   /**
    * Create an LGA and set the fields
    */
   public EducationDataset(String indig_status, String school_year, String gender, int population_2016, int population_2021, int change) {
    this.indig_status = indig_status;
    this.school_year = school_year;
    this.gender = gender;
    this.population_2016 = population_2016;
    this.population_2021 = population_2021;
    this.change = change;
   }



   public String getindig_status() {
      return indig_status;
   }
   
   public String getschool_year() {
      return school_year;
   }
   
   public String getgender() {
      return gender;
   }
   
   public int getpopulation_2016() {
      return population_2016;
   }

   public int getpopulation_2021() {
      return population_2021;
   }
 
   public int getchange() {
      return change;
   }

}

