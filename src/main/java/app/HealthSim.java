package app;



public class HealthSim {
    
    private String lgaName;
    private Float dif_sim;

   


   public HealthSim(String lgaName, Float dif_sim) {
    this.lgaName = lgaName;
    this.dif_sim = dif_sim;
   }



   public String getlgaName() {
      return lgaName;
   }
   
   public Float getdif_sim() {
      return dif_sim;
   }
   

}

