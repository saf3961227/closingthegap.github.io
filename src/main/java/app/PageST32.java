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
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST32 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page6.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        JDBCConnection jdbc = new JDBCConnection();

        // Add some Head information
        html = html + "<head>" + 
               "<title>Similarities</title>";
        html = html + "<link rel='icon' type='image/ico' href='/favicon.ico' />";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='similarities.css' />";
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
           <a href='/'>Homepage</a>
           <a href='mission.html'>Our Mission</a>
           <a href='page3.html'>The Gap</a>
           <a href='page4.html'>The Progress</a>
           <a href='page5.html'>The Gap Score</a>
           <a href='page6.html'><b>Similarities</b></a>
        </div>
    """;
    html = html + """
        <header class='ScoreHeader'>
            <h2 class='headertitle'>SIMILARITIES</h2>
        </header>
    """;
    html = html + """
        <ul class='breadcrumb-navigation'>
        <li><a href="/">Home</a></li>
        <li>Similarities</li>
        </ul>
            """;
   
        String dataset = context.formParam("dataset");
        String name_textbox = context.formParam("name_textbox");
        String data_number = context.formParam("data_number");
        String indig_status = context.formParam("indig_status");
        String school_year = context.formParam("school_year");
        String gender = context.formParam("gender");
        String age_group = context.formParam("age_group");
        String income_bracket = context.formParam("income_bracket");
        String health_condition = context.formParam("health_condition");
        




        html = html + """
        <div class = 'align-center'>
        <div class = 'description'>   
            <p>The Progress page allows you to generate tables displaying the change between the 2016 and 2021 censuses by LGA. Select a LGA and dataset of your interest to generate a table and compare the changes of the LGAs from 2016 to 2021 based on the categories of the dataset. Within the page, you can also view the ranking of the LGAs in 2021 by proportion based on various categories.</p>
        </div>
            <h3 class='header_table'>Finding LGAs with similar characteristics using Similarity Score</h3>
            <p>By subtracting the proportionate values of the chosen properties of the LGAs from the datasets with the user selected LGA, a similarity score is produced, with the lowest score being the most similar.</p>
        </div>
        """;
        // Add Div for page Content
        html = html + "<div class='content'>";
        html = html + """
            <div class='filter_box'>
                <div class='input_form'>
                    <form action='/page6.html' method='post'>
                        
                        <p>Please select which dataset you would like to view the information on</p>
                        <select name='dataset' id='dataset'>
                            <option value='data'>Dataset Number</option>
                            <option value='data1'>Dataset 1</option>
                            <option value='data2'>Dataset 2</option>
                            <option value='data3'>Dataset 3</option>
                            <option value='data4'>Dataset 4</option>
                        </select>
                        <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
                        <button type='submit'>Apply</button>
                        <button type='reset'>Clear</button>
                    </form>
                </div>
            </div>  """;      
        
        if (dataset == null) {

            html = html + "<div class ='align-center'><h2>Please select your required dataset</h2></div>";
    
            } else
            
            if (dataset.equals("data1")) {
            
            html = html + """
            <div class='filter_box'>
                <div class='input_form'>
                    <form action='/page6.html' method='post'>

                    <p>Please select the LGA name for finding similarities</p>
                    <select name='name_textbox' id='name_textbox'> """;

                    ArrayList<LGAname> lga_names = jdbc.getLGAname();

                            for (LGAname lga_name : lga_names) {
                                html = html + "<option>" + lga_name.getlgaName() + 
                                "</option>";               
                            } 
        html = html + """               
                        </select>    
                        <p>Please use these filters if you want THE SIMILARITIES from Dataset 1:</p>
                        <select name='indig_status' id='indig_status'>
                            <option value='indigstatus'>Indigenous Status</option>
                            <option value='indig'>Indigenous</option>
                            <option value='non-indig'>Non-Indigenous</option>
                        </select>
                        <select name='age_group' id='age_group'>
                            <option value='age'>Age Range</option>
                            <option value='00-04'>0-4 yrs</option>
                            <option value='05-09'>5-9 yrs</option>
                            <option value='10-14'>10-14 yrs</option>
                            <option value='15-19'>15-19 yrs</option>
                            <option value='20-24'>20-24 yrs</option>
                            <option value='25-29'>25-29 yrs</option>
                            <option value='30-34'>30-34 yrs</option>
                            <option value='35-39'>35-39 yrs</option>
                            <option value='40-44'>40-44 yrs</option>
                            <option value='45-49'>45-49 yrs</option>
                            <option value='50-54'>50-54 yrs</option>
                            <option value='55-59'>55-59 yrs</option>
                            <option value='60-64'>60-64 yrs</option>
                            <option value='65+'>65+ yrs</option>
                        </select>
                <select name='gender' id='gender'>
                    <option value='gender'>Gender</option>
                    <option value='m'>Male</option>
                    <option value='f'>Female</option>
                </select>
                <p>Please confirm the dataset number</p>
                        <select name='data_number' id='data_number'>
                            <option value='data'>Dataset Number</option>
                            <option value='data1'>Dataset 1</option>
                            <option value='data2'>Dataset 2</option>
                            <option value='data3'>Dataset 3</option>
                            <option value='data4'>Dataset 4</option>
                        </select>
                <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
                <button type='submit'>Apply</button>
                <button type='reset'>Clear</button>
                </form>
            </div>
            </div>"""; 

        } else if (dataset.equals("data2")) {
            
            html = html + """
                <div class='filter_box'>
                <div class='input_form'>
                    <form action='/page6.html' method='post'>
                    <p>Please select the LGA name for finding similarities</p>
                    <select name='name_textbox' id='name_textbox'> """;

                    ArrayList<LGAname> lga_names = jdbc.getLGAname();

                            for (LGAname lga_name : lga_names) {
                                html = html + "<option>" + lga_name.getlgaName() + 
                                "</option>";               
                            } 
        html = html + """   
                     </select>       
                     <p>Please use these filters if you want THE SIMILARITIES from Dataset 2:</p>
                <select name='indig_status' id='indig_status'>
                    <option value='indigstatus'>Indigenous Status</option>
                    <option value='indig'>Indigenous</option>
                    <option value='non-indig'>Non-Indigenous</option>
                </select>
                <select name='health_condition' id='health_condition'>
                    <option value='health'>Health Condition</option>
                    <option value='Arthritis'>Arthritis</option>
                    <option value='Asthma'>Asthma</option>
                    <option value='Cancer'>Cancer</option>
                    <option value='Dementia'>Dementia</option>
                    <option value='Diabetes'>Diabetes</option>
                    <option value='HeartDisease'>Heart Disease</option>
                    <option value='KidneyDisease'>Kidney Disease</option>
                    <option value='LungCondition'>Lung Condition</option>
                    <option value='MentalHealth'>Mental Health</option>
                    <option value='Other'>Other</option>
                    <option value='Stroke'>Stroke</option>
                </select>

        <select name='gender' id='gender'>
            <option value='gender'>Gender</option>
            <option value='m'>Male</option>
            <option value='f'>Female</option>
        </select>
        <p>Please confirm the dataset number</p>
        <select name='data_number' id='data_number'>
            <option value='data'>Dataset Number</option>
            <option value='data1'>Dataset 1</option>
            <option value='data2'>Dataset 2</option>
            <option value='data3'>Dataset 3</option>
            <option value='data4'>Dataset 4</option>
        </select>           
                <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
                <button type='submit'>Apply</button>
                <button type='reset'>Clear</button>
                </form>
            </div>
            </div>""";
        } else if (dataset.equals("data3")) {
            
            html = html + """
                <div class='filter_box'>
                <div class='input_form'>
                    <form action='/page6.html' method='post'>
                    <p>Please select the LGA name for finding similarities</p>
                    <select name='name_textbox' id='name_textbox'> """;

                    ArrayList<LGAname> lga_names = jdbc.getLGAname();

                            for (LGAname lga_name : lga_names) {
                                html = html + "<option>" + lga_name.getlgaName() + 
                                "</option>";               
                            } 
        html = html + """   
                     </select>  

                    <p>Please use these filters if you want THE SIMILARITIES from Dataset 3:</p>
                    <select name='indig_status' id='indig_status'>
                        <option value='indigstatus'>Indigenous Status</option>
                        <option value='indig'>Indigenous</option>
                        <option value='non-indig'>Non-Indigenous</option>
                    </select>
            
                <select name='school_year' id='school_year'>
                    <option value='data'>Year of Schooling</option>
                    <option value='No Schooling'>No Schooling</option>
                    <option value='Year 8 Below'>Year 8 Below</option>
                    <option value='Year 9'>Year 9</option>
                    <option value='Year 10'>Year 10</option>
                    <option value='Year 11'>Year 11</option>
                    <option value='Year 12'>Year 12</option>
                </select>
                    </select>
            <select name='gender' id='gender'>
                <option value='gender'>Gender</option>
                <option value='m'>Male</option>
                <option value='f'>Female</option>
            </select>
            <p>Please confirm the dataset number</p>
            <select name='data_number' id='data_number'>
                <option value='data'>Dataset Number</option>
                <option value='data1'>Dataset 1</option>
                <option value='data2'>Dataset 2</option>
                <option value='data3'>Dataset 3</option>
                <option value='data4'>Dataset 4</option>
            </select>                                  
                <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
                <button type='submit'>Apply</button>
                <button type='reset'>Clear</button>
                </form>
            </div>
            </div>""";

        } else if (dataset.equals("data4")) {
            
            html = html + """
                <div class='filter_box'>
                <div class='input_form'>
                    <form action='/page6.html' method='post'>
                    <p>Please select the LGA name for finding similarities</p>
                    <select name='name_textbox' id='name_textbox'> """;

                    ArrayList<LGAname> lga_names = jdbc.getLGAname();

                            for (LGAname lga_name : lga_names) {
                                html = html + "<option>" + lga_name.getlgaName() + 
                                "</option>";               
                            } 
        html = html + """   
                     </select>  
                    <p>Please use these filters if you want THE SIMILARITIES from Dataset 4:</p>
                    <select name='indig_status' id='indig_status'>
                        <option value='indigstatus'>Indigenous Status</option>
                        <option value='indig'>Indigenous</option>
                        <option value='non-indig'>Non-Indigenous</option>
                    </select>
                    <select name='income_bracket' id='income_bracket'>
                    <option value='data'>Income Bracket</option>
                        <option value='1-149'>$1-$149</option>
                        <option value='150-299'>$150-$299</option>
                        <option value='300-399'>$300-$399</option>
                        <option value='400-499'>$400-$499</option>
                        <option value='500-649'>$500-$649</option>
                        <option value='650-799'>$650-$799</option>
                        <option value='800-999'>$800-$999</option>
                        <option value='1000-1249'>$1000-$1249</option>
                        <option value='1250-1499'>$1250-$1499</option>
                        <option value='1500-1749'>$1500-$1749</option>
                        <option value='1750-1999'>$1750-$1999</option>
                        <option value='2000-2499'>$2000-$2499</option>
                        <option value='2500-2999'>$2500-$2999</option>
                        <option value='3000-3499'>$3000-$3499</option>
                        <option value='3500+'>$3500+</option>
                    </select>

                    <p>Please confirm the dataset number</p>
                    <select name='data_number' id='data_number'>
                        <option value='data'>Dataset Number</option>
                        <option value='data1'>Dataset 1</option>
                        <option value='data2'>Dataset 2</option>
                        <option value='data3'>Dataset 3</option>
                        <option value='data4'>Dataset 4</option>
                    </select>                                         
                <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
                <button type='submit'>Apply</button>
                <button type='reset'>Clear</button>
                </form>
            </div>
            </div>""";
        }




if (data_number == null) {
    // If NULL, nothing to show, therefore we make some "no results" HTML
    html = html + "<div class ='align-center'><h2><i></i></h2></div>";
} else

if(data_number.equals("data4")) {
html = html + """
     <div style='overflow-x:auto;''>
     <table class='table_config'>
     <tr>
         <thead>
             <th>LGA</th>
             <th>Similarity Score</th>
             <th>Similarity Rank</th>
       </thead>
    </tr>
     <tr>""";

     ArrayList<IncomeSim> simsby_lga = jdbc.getIncomeSim(indig_status, income_bracket, name_textbox);
                for(int index = 1; index < simsby_lga.size(); index++){
                    IncomeSim simby_lga = simsby_lga.get(index);
                    html = html + """
                        <tr>
                            <td>"""
                        + simby_lga.getlgaName() +
                        """
                                </td>
                                <td>
                        """
                        + simby_lga.getdif_sim() +
                        """
                                </td>
                                <td>
                        """
                            + index +
                        """
                            </td> 
                        </tr>

                        """;
                    if (index == 15){
                        break;
                    }
                }
} else

if(data_number.equals("data1")) {
html = html + """
     <div style='overflow-x:auto;''>
     <table class='table_config'>
     <tr>
         <thead>
             <th>LGA</th>
             <th>Similarity Score</th>
             <th>Similarity Rank</th>
       </thead>
    </tr>
     <tr>""";

     ArrayList<PopulationSim> simsby_lga = jdbc.getPopulationSim(indig_status, age_group, name_textbox, gender);
                for(int index = 1; index < simsby_lga.size(); index++){
                    PopulationSim simby_lga = simsby_lga.get(index);
                    html = html + """
                        <tr>
                            <td>"""
                        + simby_lga.getlgaName() +
                        """
                                </td>
                                <td>
                        """
                        + simby_lga.getdif_sim() +
                        """
                                </td>
                                <td>
                        """
                            + index +
                        """
                            </td> 
                        </tr>

                        """;
                    if (index == 15){
                        break;
                    }
                }
            } else

            if(data_number.equals("data2")) {
            html = html + """
                 <div style='overflow-x:auto;''>
                 <table class='table_config'>
                 <tr>
                     <thead>
                         <th>LGA</th>
                         <th>Similarity Score</th>
                         <th>Similarity Rank</th>
                   </thead>
                </tr>
                 <tr>""";
            
                 ArrayList<HealthSim> simsby_lga = jdbc.getHealthSim(indig_status, health_condition, name_textbox, gender);
                            for(int index = 1; index < simsby_lga.size(); index++){
                                HealthSim simby_lga = simsby_lga.get(index);
                                html = html + """
                                    <tr>
                                        <td>"""
                                    + simby_lga.getlgaName() +
                                    """
                                            </td>
                                            <td>
                                    """
                                    + simby_lga.getdif_sim() +
                                    """
                                            </td>
                                            <td>
                                    """
                                        + index +
                                    """
                                        </td> 
                                    </tr>
            
                                    """;
                                if (index == 15){
                                    break;
                                }
                            }
                        } else

                        if(data_number.equals("data3")) {
                        html = html + """
                             <div style='overflow-x:auto;''>
                             <table class='table_config'>
                             <tr>
                                 <thead>
                                     <th>LGA</th>
                                     <th>Similarity Score</th>
                                     <th>Similarity Rank</th>
                               </thead>
                            </tr>
                             <tr>""";
                        
                             ArrayList<EducationSim> simsby_lga = jdbc.getEducationSim(indig_status, school_year, name_textbox, gender);
                                        for(int index = 1; index < simsby_lga.size(); index++){
                                            EducationSim simby_lga = simsby_lga.get(index);
                                            html = html + """
                                                <tr>
                                                    <td>"""
                                                + simby_lga.getlgaName() +
                                                """
                                                        </td>
                                                        <td>
                                                """
                                                + simby_lga.getdif_sim() +
                                                """
                                                        </td>
                                                        <td>
                                                """
                                                    + index +
                                                """
                                                    </td> 
                                                </tr>
                        
                                                """;
                                            if (index == 15){
                                                break;
                                            }
                                        }
                                    }
    html = html + """
    </table>
    </div>
    """;    

  
    

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
