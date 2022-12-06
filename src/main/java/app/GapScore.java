package app;

//MAKING GAP SCORE CLASS

public class GapScore {
  
   private String LGAname;
   private String dataset;
   private String category;
   private String gap2016;
   private String gap2021;
   private String changeInGap; 
   private String avgGap2016;
   private String avgGap2021; 
   private String avgChange;


   /**
    * Create a GAP SCORE class and set the fields
    */
   public GapScore(String LGAname, String dataset, String category, String gap2016, String gap2021, String changeInGap, String avgGap2016, String avgGap2021, String avgChange) {
      
      this.LGAname = LGAname;
      this.dataset = dataset;
      this.category = category;  
      this.gap2016 = gap2016;
      this.gap2021 = gap2021;
      this.changeInGap = changeInGap;
      this.avgGap2016 = avgGap2016;
      this.avgGap2021 = avgGap2021;
      this.avgChange = avgChange;
    
   }

   public String getLGAname() {
      return LGAname;
   }
   public String getDataset() {
      return dataset;
   }
   public String getCategory() {
      return category;
   }

   public String getGap2016() { 
      return gap2016;
   }
   public String getGap2021() {
      return gap2021;
   }

   public String getChangeInGap() { 
    return changeInGap;
   }

   public String getAvgGap2016() {
      return avgGap2016;
   }

   public String getAvgGap2021() {
      return avgGap2021;
   }

   public String getAvgChange() {
      return avgChange;
   }



}
