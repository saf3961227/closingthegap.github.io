package app;

//MAKING Education CLASS

public class Education {
  
   private String LGAname;
   private String IndigenousStatus;
   private int noSchooling;
   private int year8Below;
   private int year9; 
   private int year10;
   private int year11; 
   private int year12;

   /**
    * Create a education class and set the fields
    */
   public Education(String LGAname, String IndigenousStatus, int noSchooling, int year8Below, int year9, int year10, int year11, int year12) {
      this.LGAname = LGAname;
      this.IndigenousStatus = IndigenousStatus;
      this.noSchooling = noSchooling;
      this.year8Below = year8Below;
      this.year9 = year9;
      this.year10 = year10;
      this.year11 = year11;
      this.year12 = year12;
   }

   public String getLGAname() {
      return LGAname;
   }

   public String getIndigenousStatus() {
      return IndigenousStatus;
   }

   public int getNoSchooling() { 
      return noSchooling;
   }

   public int getYear8Below() { 
    return year8Below;
   }

   public int getYear9() {
      return year9;
   }

   public int getYear10() {
      return year10;
   }

   public int getYear11() {
      return year11;
   }

   public int getYear12() {
      return year12;
   }


}
