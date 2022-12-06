package app;

//MAKING OUTCOME CLASS

public class Outcome {
  
   private String outcomeNum;
   private String outcomeName;
   private String outcomeFocus;

   /**
    * Create an Outcome and set the fields
    */
   public Outcome(String outcomeNum, String outcomeName, String outcomeFocus) {
      this.outcomeNum = outcomeNum;
      this.outcomeName = outcomeName;
      this.outcomeFocus = outcomeFocus;
   }

   public String getOutcomeNum() {
      return outcomeNum;
   }

   public String getOutcomeName() {
      return outcomeName;
   }

   public String getOutcomeFocus() {
      return outcomeFocus;
   }
}
