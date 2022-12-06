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
public class PageST21 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3.html";

    @Override
    public void handle(Context context) throws Exception {

            //get the form data
            //  If the form is not filled in, then the form will return null!

            // Look up some information from JDBC
            // First we need to use your JDBCConnection class
            JDBCConnection jdbc = new JDBCConnection();


            String dataset = context.formParam("dataset");
            String datatypes = context.formParam("datatypes");    
            String sortBy = context.formParam("sortBy");
            String sortOrder = context.formParam("sortOrder");  
            String pageNumString = context.formParam("pageNum");
            int pageNum = 1;
            
            if(pageNumString != null && !pageNumString.equals("")) {
                pageNum = Integer.parseInt(pageNumString);
            }

            if(sortBy == null) {
                sortBy = "LGAname";
            }

            if(sortOrder == null) {
                sortOrder = "asc";
            }

        // Create a simple HTML webpage in a String
        String html = "<html>";

        
        // Add some Head information
        html = html + "<head>" + 
               "<title>The Gap</title>";
        html = html + "<link rel='icon' type='image/ico' href='/favicon.ico' />";


        // Add some CSS (external file)
        // BOOTSTRAP
        html = html + "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi' crossorigin='anonymous'>";

        html = html + "<link rel='stylesheet' type='text/css' href='olivia.css' />";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1'>";

    

        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        //JAVASCRIPT FOR BOOTSTRAP
        html = html + "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js' integrity='sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk' crossorigin='anonymous'></script>";


        // Add the topnav
        // This uses a Java v15+ Text Block
        
        html = html + """
                    <nav class='navbar navbar-expand-lg fixed-top navbar-light bs-light'>
                        <a href='/' class='escapeHatch'>
                        <img class='logo' src='logo2.png' alt='Logo'>
                         <strong>Closing the Gap</strong>
                        </a>

                            <button type='button' 
                            data-bs-toggle='collapse' 
                            data-bs-target='#navbarNav' 
                            class='navbar-toggler' 
                            aria-controls='navbarNav' 
                            aria-expanded='false' 
                            aria-label='Toggle navigation'>
                                <span class='navbar-toggler-icon'></span>
                            </button>
                        <div class='collapse navbar-collapse' id='navbarNav'>

                            <ul class = 'navbar-nav'>
                                <li class='nav-item active'>
                                    <a href='/' class= 'nav-link'>Home</a>
                                </li>
                                <li class='nav-item active'>
                                    <a href='mission.html' class= 'nav-link'>Our Mission</a>
                                </li>
                                <li class='nav-item active'>
                                    <a href='page3.html' class= 'nav-link active'>The Gap</a>
                                </li>
                                <li class='nav-item active'>
                                    <a href='page4.html' class= 'nav-link'>The Progress</a>
                                </li>
                                <li class='nav-item active'>
                                    <a href='page5.html' class= 'nav-link'>The Gap Score</a>
                                </li>
                                <li class='nav-item active'>
                                    <a href='page6.html' class= 'nav-link'>Similarities</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                """;

        // Add header content block
        html = html + """
            <header class='GapHeader'>
                <h2 class='headertitle'>THE GAP</h2>
            </header>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";
        html = html + """
            <ul class='breadcrumb-navigation'>
            <li><a href="/">Home</a></li>
            <li>The Gap</li>
            </ul>
                """;

        if(dataset == null){
            html = html + """
        <p class='intro'><br>The Gap page allows you to generate tables displaying relevant census 
            data for our focus outcomes. Select a Dataset of your interest to generate a table and compare the 
            information of the LGAs from the 2021 Census. Within the proportional data, you can view
            'The Gap' calculation which is made by deducting the Indigenous data values from the Non-Indigenous data values.</p>
            """;
        }
        
        html = html + """
        <div class='parentFilterBox'>

        """;
        // BELOW HERE NEW ACCORDIAN

        html = html + """
        <div class='filterBox'> 
        <div class='accordion'><h3 class='tableTitle'>Dataset 1 - Outcome 1</h3>
        <p class='tableDesc'>Compare age demographics between Indigenous and non-Indigenous people.</p>
        </div>

        <div class='panel'>
        <div class='filters'>
        <form action='/page3.html' method='post' id='form1'>
        <input name='dataset' value='one' hidden/>                        
        <input name='pageNum' id='pageNum1' value='1' hidden/>                        
                <select name='datatypes' id='datatypes1'>
                    <option value='rawdata'>Data format</option>
                    <option value='rawdata'>Raw data</option>
                    <option value='propdata'>Percentages</option>
                </select>
                <select name='sortBy' id='sortBy1'>
                <option value='LGAname'>Sort By</option>
                <option value='LGAname'>LGA Name</option>                 
                    <option value='00-04_indig'>0-4 yrs (Indig)</option>
                    <option value='00-04_non-indig'>0-4 yrs (Non-Indig)</option>
                    <option value='05-09_indig'>5-9 yrs (Indig)</option>
                    <option value='05-09_non-indig'>5-9 yrs (Non-Indig)</option>
                    <option value='10-14_indig'>10-14 yrs (Indig)</option>
                    <option value='10-14_non-indig'>10-14 yrs (Non-Indig)</option>
                    <option value='15-19_indig'>15-19 yrs (Indig)</option>
                    <option value='15-19_non-indig'>15-19 yrs (Non-Indig)</option>
                    <option value='20-24_indig'>20-24 yrs (Indig)</option>
                    <option value='20-24_non-indig'>20-24 yrs (Non-Indig)</option>
                    <option value='25-29_indig'>25-29 yrs (Indig)</option>
                    <option value='25-29_non-indig'>25-29 yrs (Non-Indig)</option>
                    <option value='30-34_indig'>30-34 yrs (Indig)</option>
                    <option value='30-34_non-indig'>30-34 yrs (Non-Indig)</option>
                    <option value='35-39_indig'>35-39 yrs (Indig)</option>
                    <option value='35-39_non-indig'>35-39 yrs (Non-Indig)</option>
                    <option value='40-44_indig'>40-44 yrs (Indig)</option>
                    <option value='40-44_non-indig'>40-44 yrs (Non-Indig)</option>
                    <option value='45-49_indig'>45-49 yrs (Indig)</option>
                    <option value='45-49_non-indig'>45-49 yrs (Non-Indig)</option>
                    <option value='50-54_indig'>50-54 yrs (Indig)</option>
                    <option value='50-54_non-indig'>50-54 yrs (Non-Indig)</option>
                    <option value='55-59_indig'>55-59 yrs (Indig)</option>
                    <option value='55-59_non-indig'>55-59 yrs (Non-Indig)</option>
                    <option value='60-64_indig'>60-64 yrs (Indig)</option>
                    <option value='60-64_non-indig'>60-64 yrs (Non-Indig)</option>
                    <option value='65+_indig'>65+ yrs (Indig)</option>
                    <option value='65+_non-indig'>65+ yrs (Non-Indig)</option>
                </select>
            <select name='sortOrder' id='sortOrder1'>
            <option value='asc'>Sort Order</option>
            <option value='asc'>Ascending</option>
            <option value='desc'>Descending</option>
        </select>
        <br />
        <button type='submit'>GENERATE TABLE</button>
        <button type='reset'>CLEAR</button>
        </form>
        </div>
        </div>
        </div>

        """;

        html = html + """
            <div class='filterBox'> 
            <div class='accordion'><h3 class='tableTitle'>Dataset 2 - Outcome 1</h3>
            <p class='tableDesc'>Compare those affected by illness between Indigenous and non-Indigenous people.</p>
            </div>
    
            <div class='panel'>
            <div class='filters'>
            <form action='/page3.html' method='post' id='form2'>
            <input name='dataset' value='two' hidden/>   
            <input name='pageNum' id='pageNum2' value='1' hidden/>                                             
                <select name='datatypes' id='datatypes2'>
                    <option value='rawdata'>Data format</option>
                    <option value='rawdata'>Raw data</option>
                    <option value='propdata'>Percentages</option>
                </select>
                <select name='sortBy' id='sortBy2'>
                    <option value='LGAname'>Sort By</option>
                    <option value='LGAname'>LGA Name</option>
                    <option value='Arthritis_indig'>Arthritis (Indig)</option>
                    <option value='Arthritis_non-indig'>Arthritis (Non-Indig)</option>
                    <option value='Asthma_indig'>Asthma (Indig)</option>
                    <option value='Asthma_non-indig'>Asthma (Non-Indig)</option>
                    <option value='Cancer_indig'>Cancer (Indig)</option>
                    <option value='Cancer_non-indig'>Cancer (Non-Indig)</option>
                    <option value='Dementia_indig'>Dementia (Indig)</option>
                    <option value='Dementia_non-indig'>Dementia (Non-Indig)</option>
                    <option value='Diabetes_indig'>Diabetes (Indig)</option>
                    <option value='Diabetes_non-indig'>Diabetes (Non-Indig)</option>
                    <option value='HeartDisease_indig'>Heart Disease (Indig)</option>
                    <option value='HeartDisease_non-indig'>Heart Disease (Non-Indig)</option>
                    <option value='KidneyDisease_indig'>Kidney Disease (Indig)</option>
                    <option value='KidneyDisease_non-indig'>Kidney Disease (Non-Indig)</option>
                    <option value='LungCondition_indig'>Lung Condition (Indig)</option>
                    <option value='LungCondition_non-indig'>Lung Condition (Non-Indig)</option>
                    <option value='MentalHealth_indig'>Mental Health (Indig)</option>
                    <option value='MentalHealth_non-indig'>Mental Health (Non-Indig)</option>
                    <option value='Stroke_indig'>Stroke (Indig)</option>
                    <option value='Stroke_non-indig'>Stroke (Non-Indig)</option>
                    <option value='Other_indig'>Other (Indig)</option>
                    <option value='Other_non-indig'>Other (Non-Indig)</option>
                </select>
                <select name='sortOrder' id='sortOrder2'>
                <option value='asc'>Sort Order</option>
                <option value='asc'>Ascending</option>
                <option value='desc'>Descending</option>
            </select>
            <br />
            <button type='submit'>GENERATE TABLE</button>
            <button type='reset'>CLEAR</button>
            </form>
            </div>
            </div>
            </div>
       
            """;
         
        html = html + """
            <div class='filterBox'> 
            <div class='accordion'><h3 class='tableTitle'>Dataset 3 - Outcome 5</h3>
            <p class='tableDesc'>Compare relative numbers of Indigenous and non-Indigenous peoples successfully completing (or leaving) secondary education.</p>
            </div>
    
            <div class='panel'>
            <div class='filters'>
                <form action='/page3.html' method='post' id='form3'>
                <input name='dataset' value='three' hidden/> 
                <input name='pageNum' id='pageNum3' value='1' hidden/>                                               
                    <select name='datatypes' id='datatypes3'>
                        <option value='rawdata'>Data format</option>
                        <option value='rawdata'>Raw data</option>
                        <option value='propdata'>Percentages</option>
                    </select>
                    <select name='sortBy' id='sortBy3'>
                        <option value='LGAname'>Sort By</option>
                        <option value='LGAname'>LGA Name</option>
                        <option value='NoSchooling_indig'>No Schooling (Indig)</option>
                        <option value='NoSchooling_non-indig'>No Schooling (Non-Indig)</option>
                        <option value='Year8Below_indig'>Year 8 Below (Indig)</option>
                        <option value='Year8Below_non-indig'>Year 8 Below (Non-Indig)</option>
                        <option value='Year9_indig'>Year 9 (Indig)</option>
                        <option value='Year9_non-indig'>Year 9 (Non-Indig)</option>
                        <option value='Year10_indig'>Year 10 (Indig)</option>
                        <option value='Year10_non-indig'>Year 10 (Non-Indig)</option>
                        <option value='Year11_indig'>Year 11 (Indig)</option>
                        <option value='Year11_non-indig'>Year 11 (Non-Indig)</option>
                        <option value='Year12_indig'>Year 12 (Indig)</option>
                        <option value='Year12_non-indig'>Year 12 (Non-Indig)</option>
                    </select>
                    
                    <select name='sortOrder' id='sortOrder3'>
                        <option value='asc'>Sort Order</option>
                        <option value='asc'>Ascending</option>
                        <option value='desc'>Descending</option>
                    </select>
                    <br />
                    <button type='submit'>GENERATE TABLE</button>
                    <button type='reset'>CLEAR</button>
                    </form>
                    </div>
                    </div>
                    </div>
            """;

            html = html + """
            <div class='filterBox'> 
            <div class='accordion'><h3 class='tableTitle'>Dataset 4 - Outcome 8</h3>
            <p class='tableDesc'>Compare relative demographics of income levels for Indigenous and non-Indigenous
            people.</p>
            </div>
    
            <div class='panel'>
            <div class='filters'>
            <form action='/page3.html' method='post' id='form4'>
            <input name='dataset' value='four' hidden/>   
            <input name='pageNum' id='pageNum4' value='1' hidden/>                                             

                    <select name='datatypes' id='datatypes4'>
                        <option value='rawdata'>Data format</option>
                        <option value='rawdata'>Raw data</option>
                        <option value='propdata'>Percentages</option>
                    </select>
                    <select name='sortBy' id='sortBy4'>
                        <option value='LGAname'>Sort By</option>
                        <option value='LGAname'>LGA Name</option>
                        <option value='1-149_indig'>$1-$149 (Indig)</option>
                        <option value='1-149_non-indig'>$1-$149 (Non-Indig)</option>
                        <option value='150-299_indig'>$150-$299 (Indig)</option>
                        <option value='150-299_non-indig'>$150-$299 (Non-Indig)</option>
                        <option value='300-399_indig'>$300-$399 (Indig)</option>
                        <option value='300-399_non-indig'>$300-$399 (Non-Indig)</option>
                        <option value='400-499_indig'>$400-$499 (Indig)</option>
                        <option value='400-499_non-indig'>$400-$499 (Non-Indig)</option>
                        <option value='500-649_indig'>$500-$649 (Indig)</option>
                        <option value='500-649_non-indig'>$500-$649 (Non-Indig)</option>
                        <option value='650-799_indig'>$650-$799 (Indig)</option>
                        <option value='650-799_non-indig'>$650-$799 (Non-Indig)</option>
                        <option value='800-999_indig'>$800-$999 (Indig)</option>
                        <option value='800-999_non-indig'>$800-$999 (Non-Indig)</option>
                        <option value='1000-1249_indig'>$1000-$1249 (Indig)</option>
                        <option value='1000-1249_non-indig'>$1000-$1249 (Non-Indig)</option>
                        <option value='1250-1499_indig'>$1250-$1499 (Indig)</option>
                        <option value='1250-1499_non-indig'>$1250-$1499 (Non-Indig)</option>
                        <option value='1500-1749_indig'>$1500-$1749 (Indig)</option>
                        <option value='1500-1749_non-indig'>$1500-$1749 (Non-Indig)</option>
                        <option value='1750-1999_indig'>$1750-$1999 (Indig)</option>
                        <option value='1750-1999_non-indig'>$1750-$1999 (Non-Indig)</option>
                        <option value='2000-2499_indig'>$2000-$2499 (Indig)</option>
                        <option value='2000-2499_non-indig'>$2000-$2499 (Non-Indig)</option>
                        <option value='2500-2999_indig'>$2500-$2999 (Indig)</option>
                        <option value='2500-2999_non-indig'>$2500-$2999 (Non-Indig)</option>
                        <option value='3000-3499_indig'>$3000-$3499 (Indig)</option>
                        <option value='3000-3499_non-indig'>$3000-$3499 (Non-Indig)</option>
                        <option value='3500+_indig'>$3500+ (Indig)</option>
                        <option value='3500+_non-indig'>$3500+ (Non-Indig)</option>
                    </select>

                    <select name='sortOrder' id='sortOrder4'>
                        <option value='asc'>Sort Order</option>
                        <option value='asc'>Ascending</option>
                        <option value='desc'>Descending</option>
                    </select>
                    <br />
                    <button type='submit'>GENERATE TABLE</button>
                    <button type='reset'>CLEAR</button>
                    </form>
                    </div>
                    </div>
                    </div>
                    </div>
        """;



        ArrayList<String> dbData = new ArrayList<String>();

        //MAKING THE TABLE

        if (dataset == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h3 class= 't3'><br>Please select from the Datasets above and generate your table.</h3>";
        } else {
            if(dataset.equals("one")) {
                // Next we will ask this *class* for the outcomes
                        //table box 
                html = html +  "<div class='tableBox1'>";
                html = html + "<h2 class='t2'>Dataset 1 - Outcome 1</h2>";
                html = html + """ 
                    <p class='theGapTableHeader'>'The Gap' between Indigenous and non-Indigenous peoples as observed in the
                    latest information available from the 2021 census by Local Government Area (LGA).</p> 
                    """;
                dbData = jdbc.getPopulationData(sortBy, sortOrder, pageNum);
            }
            else if(dataset.equals("two")) {
                // Next we will ask this *class* for the outcomes
                        //table box 
                html = html +  "<div class='tableBox1'>";
                html = html + "<h2 class='t2'>Dataset 2 - Outcome 1</h2>";
                html = html + """ 
                    <p class='theGapTableHeader'>'The Gap' between Indigenous and non-Indigenous peoples as observed in the
                    latest information available from the 2021 census by Local Government Area (LGA).</p> 
                    """;
                dbData = jdbc.getHealthConditionsData(sortBy, sortOrder, pageNum);
            }
            else if(dataset.equals("three")) {
                // Next we will ask this *class* for the outcomes
                        //table box 
                html = html +  "<div class='tableBox1'>";
                html = html + "<h2 class='t2'>Dataset 3 - Outcome 5</h2>";
                html = html + """ 
                    <p class='theGapTableHeader'>'The Gap' between Indigenous and non-Indigenous peoples as observed in the
                    latest information available from the 2021 census by Local Government Area (LGA).</p> 
                    """;
                dbData = jdbc.getEducationData(sortBy, sortOrder, pageNum);
            }
            else if(dataset.equals("four")) {
                // Next we will ask this *class* for the outcomes
                        //table box 
                html = html +  "<div class='tableBox1'>";
                html = html + "<h2 class='t2'>Dataset 4 - Outcome 8</h2>";
                html = html + """ 
                    <p class='theGapTableHeader'>'The Gap' between Indigenous and non-Indigenous peoples as observed in the
                    latest information available from the 2021 census by Local Government Area (LGA).</p> 
                    """;
                dbData = jdbc.getIncomeData(sortBy, sortOrder, pageNum);
            }


            OliviaTableMethods headings = new OliviaTableMethods();
            html = html + headings.makeHeadings(dataset);
            ArrayList<String> headingsList = headings.getHeadings(dataset);
            OliviaTableMethods rows = new OliviaTableMethods();
            html = html + rows.makeRows(dbData, datatypes, headingsList.size());

            html += "<div class='pagination'>";
            if(pageNum > 1) {
                html += "<button id='prev' data-pageNum='" + pageNum + "' data-dataset='" + dataset +"' ";
                html += "data-datatypes='" + datatypes + "' data-sortBy='" + sortBy + "' data-sortOrder='" + sortOrder + "'>Prev</button>";
            }
          
            html += "<button id='next' data-pageNum='" + pageNum + "' data-dataset='" + dataset +"' ";
            html += "data-datatypes='" + datatypes + "' data-sortBy='" + sortBy + "' data-sortOrder='" + sortOrder + "'>Next</button>";
            html += "</div>";

        
            html += "<p>*Some data has been removed for census participants with 'non-stated' Indigenous or Non-Indigenous status.";
            html += " This has been accounted for when performing calculations.";
            html = html + "</div>";

        }


        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <br />
                <br />
                <br />
            </div>
        """;

        html = html + """
                <script>
                    //get the dropdowns into variables
                    var datatypes1 = document.getElementById('datatypes1');
                    var sortBy1 = document.getElementById('sortBy1');
                    var sortOrder1 = document.getElementById('sortOrder1');

                    var datatypes2 = document.getElementById('datatypes2');
                    var sortBy2 = document.getElementById('sortBy2');
                    var sortOrder2 = document.getElementById('sortOrder2');

                    var datatypes3 = document.getElementById('datatypes3');
                    var sortBy3 = document.getElementById('sortBy3');
                    var sortOrder3 = document.getElementById('sortOrder3');

                    var datatypes4 = document.getElementById('datatypes4');
                    var sortBy4 = document.getElementById('sortBy4');
                    var sortOrder4 = document.getElementById('sortOrder4');

                    var changeDropdowns = function(value, sortByElement, sortOrderElement) {
                        if(value === 'propdata') {
                            sortByElement.setAttribute('disabled', 'disabled');
                            sortOrderElement.setAttribute('disabled', 'disabled');
                        } else {
                            //enable them again
                            sortByElement.removeAttribute('disabled');
                            sortOrderElement.removeAttribute('disabled');
                        }
                        
                    };

                    //when the datatype changes to propdata, disable other two dropdowns
                    datatypes1.addEventListener('change', function(e) {
                        changeDropdowns(this.value, sortBy1, sortOrder1);
                    });

                    datatypes2.addEventListener('change', function(e) {
                        changeDropdowns(this.value, sortBy2, sortOrder2);
                    });

                    datatypes3.addEventListener('change', function(e) {
                        changeDropdowns(this.value, sortBy3, sortOrder3);
                    });

                    datatypes4.addEventListener('change', function(e) {
                        changeDropdowns(this.value, sortBy4, sortOrder4);
                    });

                    //next/prev buttons
                    var nextButton = document.getElementById('next');
                    var prevButton = document.getElementById('prev');

                    //set filters to prev values
                    var setFilters = function(element, increment = 0) {

                        //get filters from button
                        var pageNum = parseInt(element.getAttribute('data-pageNum')) + increment;
                        var dataset = element.getAttribute('data-dataset');
                        var datatypes = element.getAttribute('data-datatypes');
                        var sortBy = element.getAttribute('data-sortBy');
                        var sortOrder = element.getAttribute('data-sortOrder');

                        //set form number
                        var dataNum = 1;
                        if(dataset === 'one') {
                            dataNum = 1;
                        } else if(dataset === 'two') {
                            dataNum = 2;
                        } else if(dataset === 'three') {
                            dataNum = 3;
                        } else if(dataset === 'four') {
                            dataNum = 4;
                        }

                        //set filters to previous values
                        document.getElementById('datatypes' + dataNum).value = datatypes;
                        document.getElementById('sortBy' + dataNum).value = sortBy;
                        document.getElementById('sortOrder' + dataNum).value = sortOrder;
                        document.getElementById('pageNum' + dataNum).value = pageNum;

                        return dataNum;
                    }

                    //click function
                    var buttonClick = function(e) {

                        var dataNum = setFilters(this, 1);

                        //submit the form
                        document.getElementById('form' + dataNum).submit();

                    }

                    //click function
                    var prevButtonClick = function(e) {

                        var dataNum = setFilters(this, -1);

                        //submit the form
                        document.getElementById('form' + dataNum).submit();

                    }

                    //set filters to old values on load
                    changeDropdowns(document.getElementById('datatypes1').value, sortBy1, sortOrder1);
                    changeDropdowns(document.getElementById('datatypes2').value, sortBy2, sortOrder2);
                    changeDropdowns(document.getElementById('datatypes3').value, sortBy3, sortOrder3);
                    changeDropdowns(document.getElementById('datatypes4').value, sortBy4, sortOrder4);

                    //on click
                    if(nextButton !== null) {
                        setFilters(nextButton, 0);
                        nextButton.addEventListener('click', buttonClick);
                    }

                    if(prevButton !== null) {
                        prevButton.addEventListener('click', prevButtonClick);
                    }

                </script>
                """;


                //ACCORDIAN JAVASCRIPT

                html = html + """
                <script>        
                var acc = document.getElementsByClassName('accordion');
                var i;
                for (i = 0; i < acc.length; i++) {
                    acc[i].addEventListener('click', function() {

                // toggling between adding/removing active class to highlight button controlling the accordian
                this.classList.toggle('active');

                //toggling between hiding/showing active panel
                var panel = this.nextElementSibling;
                if (panel.style.display === 'block') {
                    panel.style.display = 'none';
                } else {
                    panel.style.display = 'block';
                }
                });
                }
                
                </script>        
                </div>
                """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

  

}
