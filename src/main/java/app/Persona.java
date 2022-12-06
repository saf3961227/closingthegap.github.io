package app;

public class Persona {
    private String name;
 
    private String image_file_path;
 
    /**
     * Create an LGA and set the fields
     */
    public Persona(String name, String image_file_path) {
       this.name = name;
       this.image_file_path = image_file_path;
    }
 
    public String getName() {
       return name;
    }
 
    public String getImageFilePath(){
       return image_file_path;
    }
}

// test push