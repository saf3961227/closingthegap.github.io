package app;

//MAKING HEALTHCONDITION CLASS

public class HealthConditions {
  
   private String LGAname;
   private String IndigenousStatus;
   private int arthritis;
   private int asthma;
   private int cancer; 
   private int dementia;
   private int diabetes; 
   private int heartDisease;
   private int kidneyDisease;
   private int lungCondition; 
   private int mentalHealth;
   private int stroke;
   private int other;

   /**
    * Create a health condition class and set the fields
    */
   public HealthConditions(String LGAname, String IndigenousStatus, int arthritis, int asthma, int cancer, int dementia, int diabetes, int heartDisease, int kidneyDisease, int lungCondition, int mentalHealth, int stroke, int other) {
      this.LGAname = LGAname;
      this.IndigenousStatus = IndigenousStatus;
      this.arthritis = arthritis;
      this.asthma = asthma;
      this.cancer = cancer;
      this.dementia = dementia;
      this.diabetes = diabetes;
      this.heartDisease = heartDisease;
      this.kidneyDisease = kidneyDisease;
      this.lungCondition = lungCondition;
      this.mentalHealth = mentalHealth;
      this.stroke = stroke;
      this.other = other;
   }

   public String getLGAname() {
      return LGAname;
   }

   public String getIndigenousStatus() {
      return IndigenousStatus;
   }

   public int getArthritis() { 
      return arthritis;
   }

   public int getAsthma() { 
    return asthma;
   }

   public int getCancer() {
      return cancer;
   }

   public int getDementia() {
      return dementia;
   }

   public int getDiabetes() {
      return diabetes;
   }

   public int getHeartDisease() {
      return heartDisease;
   }

   public int getKidneyDisease() {
      return kidneyDisease;
   }

   public int getLungCondition() {
      return lungCondition;
   }

   public int getMentalHealth() {
      return mentalHealth;
   }

   public int getStroke() {
      return stroke;
   }

   public int getOther() {
      return other;
   }

}
