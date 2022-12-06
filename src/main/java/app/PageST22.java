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
public class PageST22 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page4.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        JDBCConnection jdbc = new JDBCConnection();

        // Add some Head information
        html = html + "<head>" + 
               "<title>The Progress</title>";
        html = html + "<link rel='icon' type='image/ico' href='/favicon.ico' />";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='progress.css' />";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1'>";

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
                       <a href='mission.html'>Our Mission</a>
                       <a href='page3.html'>The Gap</a>
                       <a href='page4.html'><b>The Progress</b></a>
                       <a href='page5.html'>The Gap Score</a>
                       <a href='page6.html'>Similarities</a>
                    </div>
                """;
        html = html + """
            <header class='ScoreHeader'>
                <h2 class='headertitle'>THE PROGRESS</h2>
            </header>
        """;
        html = html + """
            <ul class='breadcrumb-navigation'>
            <li><a href="/">Home</a></li>
            <li>The Progress</li>
            </ul>
                """;

        html = html
                + """    
                    <div class = 'align-center'>
                    <div class = 'description'>   
                        <p>The Progress page allows you to generate tables displaying the change between the 2016 and 2021 censuses by LGA. Select a LGA and dataset of your interest to generate a table and compare the changes of the LGAs from 2016 to 2021 based on the categories of the dataset. Within the page, you can also view the ranking of the LGAs in 2021 by proportion based on various categories.</p>
                    </div>
                    </div>
                            <h3 class='header_table'>The change between the 2016 and 2021 censuses by Local Government Area (LGA).</h3>
                        """;
        // Add Div for page Content
        html = html + "<div class='content'>";
        html = html
                + """
                            <div class='filter_box'>
                                <div class='input_form' >
                                    <form action='/page4.html' method='post' autocomplete='on'>
                                        <p>Please select the LGA or State name</p>
                                        <select name='name_textbox' id='name_textbox'> """;
                                ArrayList<LGAname> lga_names = jdbc.getLGAname();

                                        for (LGAname lga_name : lga_names) {
                                            html = html + "<option>" + lga_name.getlgaName() + 
                                            "</option>";               
                                        } 
        html = html + """               
                                        <option>NSW</option>
                                        <option>Victoria</option>
                                        <option>QLD</option>
                                        <option>South Australia</option>
                                        <option>Western Australia</option>
                                        <option>Tasmania</option>
                                        <option>Northern Territory</option>
                                        <option>ACT</option>
                                        <option>Other</option>
                                        </select>
                                        <p>Please select which dataset you would like to view the information on</p>
                                        <select name='dataset' id='dataset'>
                                            <option value='data'>Dataset Number</option>
                                            <option value='data1'>Dataset 1</option>
                                            <option value='data3'>Dataset 3</option>
                                            <option value='data4'>Dataset 4</option>
                                        </select>
                                        <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
                                        <button type='submit'>Apply</button>
                                        <button type='reset'>Clear</button>
                                    </form>
                                </div>
                            </div>
                        """;
        ArrayList<String> state_names = new ArrayList<String>();
        state_names.add("NSW");
        state_names.add("Victoria");
        state_names.add("QLD");
        state_names.add("South Australia");
        state_names.add("Western Australia");
        state_names.add("Tasmania");
        state_names.add("Northern Territory");
        state_names.add("ACT");
        state_names.add("Other");

        String name_textbox = context.formParam("name_textbox");
        System.out.println("TEXT: " + name_textbox);

        Boolean got_matches = false;

        if (state_names.contains(name_textbox)) {
            html = html + "<div class ='align-center'><div class ='state-box'><h4><u>State Name</u></h4><h1>"
                    + name_textbox + "</div></h1></div>";
        }

        if (name_textbox == null || name_textbox == "") {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<div class ='align-center'><h2><i> </i></h2></div>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            // html = html + name_textbox;
            ArrayList<LGA_2021> lgas_chosen = jdbc.getLGA_2021(name_textbox);

            if (lgas_chosen.size() == 0) {
                html = html + "<div class ='align-center'><h4>Your input data is not a LGA.</h4></div>";
            } else {
                got_matches = true;
            }
        }

        html = html + """
                <div style='overflow-x:auto;''>
                <table class='invisible_table'>
                <tr>
                    <thead>
                        <th> Name </th>
                        <th> State </th>
                        <th> Type </th>
                        <th> Area(sq.km) </th>
                    </thead>
                </tr>
                <tr>""";

        if (got_matches = true) {
            ArrayList<LGA_2021> lgas_chosen = jdbc.getLGA_2021(name_textbox);

            for (LGA_2021 lga_2021 : lgas_chosen) {
                html = html + "<td>" + lga_2021.getlga_name() +
                        "</td>";
                html = html + "<td>" + lga_2021.getlga_state() +
                        "</td>";
                html = html + "<td>" + lga_2021.getlga_type() +
                        "</td>";
                html = html + "<td>" + lga_2021.getlga_area() +
                        "</td>";
            }

        }

        html = html + """
                </tr>
                </table>
                </div>
                <div class = 'population_change'>
                <h3>Total Change in Population between 2016 and 2021</h3>""";

        if (got_matches = true) {
            ArrayList<Integer> total_changes = jdbc.getPopulationChange(name_textbox);
            html = html + "<h1>" + total_changes.get(0) + "</h1>";
            html = html + """
                    </div>
                    """;
        }

        String dataset = context.formParam("dataset");

        // getting table headings
        if (dataset == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<div class ='align-center'><h2><i></i></h2></div>";
        } else

        if (dataset.equals("data1")) {

            html = html + """
                        <div style='overflow-x:auto;''>
                    <table class='table_config'>
                    <tr>
                        <thead>
                            <th> Indigenous Status </th>
                            <th> Age Group </th>
                            <th> Gender </th>
                            <th> 2016 </th>
                            <th> 2021 </th>
                            <th> Change </th>
                        </thead>
                    </tr>
                    """;

            ArrayList<PopulationDataset> changesby_lga = jdbc.getPopulationDataset(name_textbox);

            for (PopulationDataset changeby_lga : changesby_lga) {
                html = html + """
                        <tr>
                            <td>"""
                        + changeby_lga.getindig_status() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getage_group() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getgender() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getpopulation_2016() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getpopulation_2021() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getchange() +
                        """
                                </td>
                        </tr>
                                
                        """;
            }
        } else

        if (dataset.equals("data3")) {

            html = html + """

                        <div style='overflow-x:auto;''>
                    <table class='table_config'>
                    <tr>
                        <thead>
                            <th> Indigenous Status </th>
                            <th> Year of Schooling </th>
                            <th> Gender </th>
                            <th> 2016 </th>
                            <th> 2021 </th>
                            <th> Change </th>
                        </thead>
                    </tr>""";
        
            ArrayList<EducationDataset> changesby_lga = jdbc.getEducationDataset(name_textbox);

            for (EducationDataset changeby_lga : changesby_lga) {
                html = html + """
                        <tr>
                            <td>"""
                        + changeby_lga.getindig_status() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getschool_year() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getgender() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getpopulation_2016() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getpopulation_2021() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getchange() +
                        """
                                    </td>
                        </tr>
                        """;
            }

        } else

        if (dataset.equals("data4")) {

            html = html + """
                    </td>
                        <div style='overflow-x:auto;''>
                    <table class='table_config'>
                    <tr>
                        <thead>
                            <th> Indigenous Status </th>
                            <th> Income Bracket </th>
                            <th> 2016 </th>
                            <th> 2021 </th>
                            <th> Change </th>
                        </thead>
                    </tr>""";
            ArrayList<IncomeDataset> changesby_lga = jdbc.getIncomeDataset(name_textbox);

            for (IncomeDataset changeby_lga : changesby_lga) {
                html = html + """
                        <tr>
                            <td>"""
                        + changeby_lga.getindig_status() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getincome_bracket() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getpopulation_2016() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getpopulation_2021() +
                        """
                                </td>
                                <td>
                        """
                        + changeby_lga.getchange() +
                        """
                                    </td>
                        </tr>
                        
                        """;
            }
        }

         html = html + """ 
            </table>
            </div>
            <h3 class='header_table'>LGA ranking based on categories</h3>
            """;


        String indig_status = context.formParam("indig_status");
        String school_year = context.formParam("school_year");
        String gender = context.formParam("gender");
        String data_number = context.formParam("data_number");
        String age_group = context.formParam("age_group");
        String income_bracket = context.formParam("income_bracket");
        // System.out.println(indig_status);
        // System.out.println(school_year);
        // System.out.println(gender);
        // changing filters according to the selected dataset
        if (dataset == null) {

            html = html + "<div class ='align-center'><h2><i> </i></h2></div>";

        } else
        
        if (dataset.equals("data1")) {

            html = html + """
                <div class='filter_box'>

                <form action='/page4.html' method='post'>
                <div class='input_form'>
                    <p>Please select the categories and confirm the dataset number</p>
                <select name='indig_status' id='indig_status'>
                        <option value='data'>Indigenous Status</option>
                        <option value='indig'>Indigenous</option>
                        <option value='non-indig'>Non-Indigenous</option>
                </select>
                <select name='age_group' id='age_group'>
                    <option value='data'>Age Range</option>
                    <option value='00-04'>00-04</option>
                    <option value='05-09'>05-09</option>
                    <option value='10-14'>10-14</option>
                    <option value='15-19'>15-19</option>
                    <option value='20-24'>20-24</option>
                    <option value='25-29'>25-29</option>
                    <option value='30-34'>30-34</option>
                    <option value='35-39'>35-39</option>
                    <option value='40-44'>40-44</option>
                    <option value='45-49'>45-49</option>
                    <option value='50-54'>50-54</option>
                    <option value='55-59'>55-59</option>
                    <option value='60-64'>60-64</option>
                    <option value='65+'>65+</option>
                </select>
            <select name='gender' id='gender'>
                    <option value='data'>Gender</option>
                    <option value='m'>Male</option>
                    <option value='f'>Female</option>
            </select>
            <select name='data_number' id='data_number'>
                <option value='data'>Dataset Number</option>
                <option value='data1'>Dataset 1</option>
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

                    <form action='/page4.html' method='post'>
                    <div class='input_form'>
                        <p>Please select the categories and confirm the dataset number</p>
                    <select name='indig_status' id='indig_status'>
                            <option value='data'>Indigenous Status</option>
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
                <select name='gender' id='gender'>
                        <option value='data'>Gender</option>
                        <option value='m'>Male</option>
                        <option value='f'>Female</option>
                </select>
                <select name='data_number' id='data_number'>
                    <option value='data'>Dataset Number</option>
                    <option value='data1'>Dataset 1</option>
                    <option value='data3'>Dataset 3</option>
                    <option value='data4'>Dataset 4</option>
                </select>
                <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
                <button type='submit'>Apply</button>
                <button type='reset'>Clear</button>
                    </form>
                    </div>
                    </div>
            """;

       
        

        } else if (dataset.equals("data4")) {
            html = html + """
                <div class='filter_box'>

                <form action='/page4.html' method='post'>
                <div class='input_form'>
                    <p>Please select the categories and confirm the dataset number</p>
                <select name='indig_status' id='indig_status'>
                        <option value='data'>Indigenous Status</option>
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
            <select name='data_number' id='data_number'>
                <option value='data'>Dataset Number</option>
                <option value='data1'>Dataset 1</option>
                <option value='data3'>Dataset 3</option>
                <option value='data4'>Dataset 4</option>
            </select>
            <p><br>Use the APPLY button below to activate your filters, or use the CLEAR button to begin again.</p>
            <button type='submit'>Apply</button>
            <button type='reset'>Clear</button>
                </form>
                </div>
                </div>
            """;

        }


  
        if (data_number == null) {
                // If NULL, nothing to show, therefore we make some "no results" HTML
                html = html + "<div class ='align-center'><h2><i></i></h2></div>";
            } else
        
            if(data_number.equals("data3")) {
            html = html + """
                 <br>
                 <br>
                 <div style='overflow-x:auto;''>
                 <table class='table_config'>
                 <tr>
                     <thead>
                         <th> LGA </th>
                        <th> Proportional Data </th>
                        <th> Rank </th>
                   </thead>
                </tr>
                 <tr>""";

                ArrayList<EducationProp> propsby_lga = jdbc.getEducationProp(indig_status, school_year, gender);
                for(int index = 1; index < propsby_lga.size(); index++){
                    EducationProp propby_lga = propsby_lga.get(index);
                    html = html + """
                        <tr>
                            <td>"""
                        + propby_lga.getlga_name() +
                        """
                                </td>
                                <td>
                        """
                        + propby_lga.getprop_data() +
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
                 <br>
                 <br>
                 <div style='overflow-x:auto;''>
                 <table class='table_config'>
                 <tr>
                     <thead>
                         <th> LGA </th>
                        <th> Proportional Data </th>
                        <th> Rank </th>
                   </thead>
                </tr>
                 <tr>""";

                ArrayList<PopulationProp> propsby_lga = jdbc.getPopulationProp(indig_status, age_group, gender);
                for(int index = 1; index < propsby_lga.size(); index++){
                    PopulationProp propby_lga = propsby_lga.get(index);
                    html = html + """
                        <tr>
                            <td>"""
                        + propby_lga.getlga_name() +
                        """
                                </td>
                                <td>
                        """
                        + propby_lga.getprop_data() +
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
        
            if(data_number.equals("data4")) {
            html = html + """
                 <br>
                 <br>
                 <div style='overflow-x:auto;''>
                 <table class='table_config'>
                 <tr>
                     <thead>
                         <th> LGA </th>
                        <th> Proportional Data </th>
                        <th> Rank </th>
                   </thead>
                </tr>
                 <tr>""";

                ArrayList<IncomeProp> propsby_lga = jdbc.getIncomeProp(indig_status, income_bracket);
                for(int index = 1; index < propsby_lga.size(); index++){
                    IncomeProp propby_lga = propsby_lga.get(index);
                    html = html + """
                        <tr>
                            <td>"""
                        + propby_lga.getlga_name() +
                        """
                                </td>
                                <td>
                        """
                        + propby_lga.getprop_data() +
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
        // Close Content div
        html = html + "</div>";

        // Footer
        // html = html + """
        //             <div class='footer'>
        //                 <p></p>
        //             </div>
        //         """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
