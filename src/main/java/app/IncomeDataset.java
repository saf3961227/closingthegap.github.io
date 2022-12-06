package app;



public class IncomeDataset {
    
    private String indig_status;
    private String income_bracket;
    private int population_2016;
    private int population_2021;
    private int change;
   


   

   /**
    * Create an LGA and set the fields
    */
   public IncomeDataset(String indig_status, String income_bracket, int population_2016, int population_2021, int change) {
    this.indig_status = indig_status;
    this.income_bracket = income_bracket;
    this.population_2016 = population_2016;
    this.population_2021 = population_2021;
    this.change = change;
   }



   public String getindig_status() {
      return indig_status;
   }
   
   public String getincome_bracket() {
      return income_bracket;
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

