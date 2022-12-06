package app;

import javassist.runtime.Desc;

public class PersonaAttribute {
   private int id;
   private String persona_name;
   private String AttributeType;
   private String Description;

 
    /**
     * Create a persona attribute and set the fields
     */
    public PersonaAttribute(int id, String persona_name, String AttributeType, String Description) {
       this.id = id;
       this.persona_name = persona_name;
       this.AttributeType = AttributeType;
       this.Description = Description; 
    }
 
    public int getid() {
       return id;
    }
 
    public String getpersona_name(){
       return persona_name;
    }

    public String getAttributeType(){
      return AttributeType;
    }

    public String getDescription(){
      return Description;
    }
   
}

