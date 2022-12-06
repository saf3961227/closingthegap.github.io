package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        // Add some Head information
        html = html + "<head>" + 
               "<title>Our Mission</title>";
        html = html + "<link rel='icon' type='image/ico' href='/favicon.ico' />";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='mission.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
        
            <div class='topnav'>
           
            
            <a href='/' class='escapeHatch'>
            <img class='logo' src='logo2.png' alt='Logo'>
            <p class='navbar-text'> <strong>Closing the Gap</strong></p>
            </a>

            <a href='/'>Home</a>
            <a href='mission.html'><b>Our Mission</b></a>
            <a href='page3.html'>The Gap</a>
            <a href='page4.html'>The Progress</a>
            <a href='page5.html'>The Gap Score</a>
            <a href='page6.html'>Similarities</a>
         </div>
        """;
        html = html + """
            <header class='ScoreHeader'>
                <h2 class='headertitle'>OUR MISSION</h2>
            </header>
        """;
        html = html + """
            <ul class='breadcrumb-navigation'>
            <li><a href="/">Home</a></li>
            <li>Our Mission</li>
            </ul>
                """;

        // Add header content block
        html = html + """
            <div class='header'>
                    <div class = 'align-center'>

                    <h1>Our perspectives</h1>
                    <p>Our website's goal is to educate users by providing data and other information in a professional, understandable, and respectful manner. To acknowledge and learn from Australia's history, it is essential to fulfil this purpose in a way that is open to all users and respectful of those who have been impacted in the past and the present. The intended users of this website would represent a wide range of demographics given the current and historical context. The majority of this website's user groups are adults, including those in government roles, students, and general public users because this is such a complex subject. With the aid of our website, users can carry out tasks like swift website navigation and the ability to focus their web searches for particular research.</p>
              
                    </div>
            </div>
        """;
        html = html + """
            <h1 class='header_table'>Personas that may use our website include:</h1>
        """;


        // Add Div for page Content
        html = html + "<div class='content'>";

        // Next we will ask this *class* for the personas
        ArrayList<Persona> personas = jdbc.getPersonas();

        Persona persona_1 = personas.get(0);
        Persona persona_2 = personas.get(1);
        
        

        html = html + """
        <div class='flex-grid'>
        
        
        <div class='col persona-box'>
            <div class='flex-grid'>
                <div class='col'>
                    <h1>
        """;

        html = html + persona_1.getName();
        
        
        html = html +
        """
                   
        </h1>
                </div>
                <div class='col align-right'>
                    <img src='
        """ ;
            
        html = html + persona_1.getImageFilePath();
    

        html = html +     
        """
            ' class= 'person-image'/> 
        <p><h4>Age:     
        """;
        ArrayList<PersonaAttribute> persona1_age = jdbc.getPersonaAttributesByNameAndType("Sakura Sato", "Age");
        
        
        html = html + persona1_age.get(0).getDescription();    

        html = html + """
        </h4>
        </p>
        <p>
        <h4>Gender: 
        """;
        ArrayList<PersonaAttribute> persona1_gender = jdbc.getPersonaAttributesByNameAndType("Sakura Sato", "Gender"); 
        html = html + persona1_gender.get(0).getDescription();         
        
        html = html + """
        </h4>
        </p>
        """;
       
        
        html = html + 
        """
        </div>  
            </div>
            <div class='flex-grid'>
                <div class='col'>
                <h4>Background</h4>""";

        ArrayList<PersonaAttribute> persona1_background = jdbc.getPersonaAttributesByNameAndType("Sakura Sato", "Background"); 
        html = html + persona1_background.get(0).getDescription();  
        html = html + 
        """
                <h4>Needs</h4>
                <ul>
                """ ;
        
        ArrayList<PersonaAttribute> persona1_needs = jdbc.getPersonaAttributesByNameAndType("Sakura Sato", "Needs");

        for (PersonaAttribute personaAttribute : persona1_needs) {
            html = html + "<li>" + personaAttribute.getDescription() + 
            "</li>";               
        }  

        html = html + """
        </ul> <h4>Goals</h4>
        <ul>
        """;
        ArrayList<PersonaAttribute> persona1_goals = jdbc.getPersonaAttributesByNameAndType("Sakura Sato", "Goals");

        for (PersonaAttribute personaAttribute : persona1_goals) {
            html = html + "<li>" + personaAttribute.getDescription() + 
            "</li>";               
        }  
        
        html = html + """
        </ul> <h4>Skills and Attributes</h4>
        <ul>
        """;
        ArrayList<PersonaAttribute> persona1_skills = jdbc.getPersonaAttributesByNameAndType("Sakura Sato", "Skills");

        for (PersonaAttribute personaAttribute : persona1_skills) {
            html = html + "<li>" + personaAttribute.getDescription() + 
            "</li>";               
        }  
       
       
        html = html +     
        """
        </ul> 
            </div>
            </div>
        </div>


        <div class='col persona-box'>
            <div class='flex-grid'>
                <div class='col'>
                    <h1>""";
        html = html + persona_2.getName();

        html = html + 
        """
            </h1>
                </div>
                <div class='col align-right'>
                    <img src=' 
                    """;
        html = html + persona_2.getImageFilePath();

        html = html + 
        """        
        ' class= 'person-image'/>
                <p><h4>Age:     
        """;
        ArrayList<PersonaAttribute> persona2_age = jdbc.getPersonaAttributesByNameAndType("Dr. Dirk Heim", "Age");
        
        
        html = html + persona2_age.get(0).getDescription();    

        html = html + """
        </h4>
        </p>
        <p>
        <h4>Gender: 
        """;
        ArrayList<PersonaAttribute> persona2_gender = jdbc.getPersonaAttributesByNameAndType("Dr. Dirk Heim", "Gender"); 
        html = html + persona2_gender.get(0).getDescription();         
        
        html = html + """
        </h4>
        </p>
        """;
       
        
        html = html + 
        """
        </div>  
            </div>
            <div class='flex-grid'>
                <div class='col'>
                <h4>Background</h4>""";

        ArrayList<PersonaAttribute> persona2_background = jdbc.getPersonaAttributesByNameAndType("Dr. Dirk Heim", "Background"); 
        html = html + persona2_background.get(0).getDescription();   
        
        html = html + 
        """
                <h4>Needs</h4>
                <ul>
                """ ;
        
        ArrayList<PersonaAttribute> persona2_needs = jdbc.getPersonaAttributesByNameAndType("Dr. Dirk Heim", "Needs");

        for (PersonaAttribute personaAttribute : persona2_needs) {
            html = html + "<li>" + personaAttribute.getDescription() + 
            "</li>";               
        }  

        html = html + """
        </ul> <h4>Goals</h4>
        <ul>
        """;
        ArrayList<PersonaAttribute> persona2_goals = jdbc.getPersonaAttributesByNameAndType("Dr. Dirk Heim", "Goals");

        for (PersonaAttribute personaAttribute : persona2_goals) {
            html = html + "<li>" + personaAttribute.getDescription() + 
            "</li>";               
        }  
        
        html = html + """
        </ul> <h4>Skills and Attributes</h4>
        <ul>
        """;
        ArrayList<PersonaAttribute> persona2_skills = jdbc.getPersonaAttributesByNameAndType("Dr. Dirk Heim", "Skills");

        for (PersonaAttribute personaAttribute : persona2_skills) {
            html = html + "<li>" + personaAttribute.getDescription() + 
            "</li>";               
        }
        html = html + """
        </ul>             
            </div>
            </div>

        </div>
        </div>
        """;
        html = html + """
            <h1 class='header_table'>How to use our website:</h1>
        """;
        html = html + """
        
            <div class='flex-grid persona-box'>
            <div class = 'align-center'>
                <div class = 'description'>
                    <br>
                    <h3>Web Accessibility</h3>
                    <p>Closing The Gap web team is constantly working to improve the accessibility of its website to be as user-friendly as possible.</p>
                    <br>
                    
                    
                    <h3>Search Options</h3>
                    <p>Checkboxes, dropdowns, and textboxes enable you to further refine your search parameters and the data types.
                    </p>
                    <img src='htu3.png' class= 'htu-image'/>
                    <br>
                    <br>
                    <h3>Navigation Bar</h3>
                    <p>The navigation bar navigation system helps you to be aware of your location and also navigate throughout all other pages.
                    </p>
                    <img src='htu4.png' class= 'nav-image'/>
                    <br>
                    <br>
                    <h3>Escape Hatch Navigation</h3>
                    <p>The escape hatch navigation system enables you to get back to the homepage by clicking on tour logo.
                    </p>
                    <img src='htu5.png' class= 'escape-image'/>
                    <br>
                    <br>
                </div>
                </div>
            </div>

                """;

        html = html + """
            <div class='persona-box'>
                <div class='flex-grid align-center'>
                    <div class='col'>
                        <h3>Our Team</h3>
                    </div>
                </div>
                <div class='flex-grid align-center'>
                    <div class='col'>
                        
                        <h4>Name: Ahmed Shams Saif</h4>
                        <p>Student ID: S3961227</p>
                    </div>
                    <div class='col'>
                        
                        <h4>Name: Olivia Girardin</h4>
                        <p>Student ID: S3973137</p>
                    </div>  
                </div>
            </div>
                """;

        // Look up some information from JDBC


        // Next we will ask this *class* for the LGAs
        


        // Close Content div
        html = html + "</div>";
        
        // Footer
        // html = html + """
        //     <div class='footer'>
        //         <p>COSC2803 - Studio Project Starter Code (Sep22)</p>
        //     </div>
        // """;

        

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
