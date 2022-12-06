package app;



public class PopulationProp {
    
    private String lga_name;
    private Float prop_data;

   


   public PopulationProp(String lga_name, Float prop_data) {
    this.lga_name = lga_name;
    this.prop_data = prop_data;
   }



   public String getlga_name() {
      return lga_name;
   }
   
   public Float getprop_data() {
      return prop_data;
   }
   

}

