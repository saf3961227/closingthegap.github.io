package app;



public class PopulationDataset {
    
    private String indig_status;
    private String age_group;
    private String gender;
    private int population_2016;
    private int population_2021;
    private int change;
   


   

   /**
    * Create an LGA and set the fields
    */
   public PopulationDataset(String indig_status, String age_group, String gender, int population_2016, int population_2021, int change) {
    this.indig_status = indig_status;
    this.age_group = age_group;
    this.gender = gender;
    this.population_2016 = population_2016;
    this.population_2021 = population_2021;
    this.change = change;
   }



   public String getindig_status() {
      return indig_status;
   }
   
   public String getage_group() {
      return age_group;
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

