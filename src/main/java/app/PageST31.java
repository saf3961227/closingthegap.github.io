package app;

import java.util.ArrayList;
import java.util.List;

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
public class PageST31 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page5.html";

    @Override
    public void handle(Context context) throws Exception {


           //get the form data
            //  If the form is not filled in, then the form will return null!

            // Look up some information from JDBC
            // First we need to use your JDBCConnection class
            JDBCConnection jdbc = new JDBCConnection();
   

            List<String> datasetsList = context.formParams("datasets[]");
            ArrayList<String> datasets = new ArrayList<String>(datasetsList);
            String sortBy = context.formParam("sortBy");
            String LGApopRanges = context.formParam("LGApopRanges");
            String LGAareaRanges = context.formParam("LGAareaRanges");
            String sortOrder = context.formParam("sortOrder");  
            String pageNumString = context.formParam("pageNum");
            int pageNum = 1;
            
            //remove empty datasets
            for(int i = 0; i < datasets.size(); i++) {
                if(datasets.get(i) == "") {
                    datasets.remove(i);
                }
            }

            if(pageNumString != null && !pageNumString.equals("")) {
                pageNum = Integer.parseInt(pageNumString);
            }

            if(sortBy == null) {
                sortBy = "LGAname";
            }

            if(sortOrder == null) {
                sortOrder = "asc";
            }

            if(LGAareaRanges == null) {
                LGAareaRanges = "allAreas";
            }

            if(LGApopRanges == null) {
                LGApopRanges = "allPopRanges";
            }
           


        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>The Gap Score</title>";
        html = html + "<link rel='icon' type='image/ico' href='/favicon.ico' />";


        // Add some CSS (external file)
        // BOOTSTRAP
        html = html + "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi' crossorigin='anonymous'>";

        html = html + "<link rel='stylesheet' type='text/css' href='olivia.css' />";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1'>";
        html = html + "<meta charset='UTF-8'>";

        

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
                                    <a href='page3.html' class= 'nav-link'>The Gap</a>
                                </li>
                                <li class='nav-item active'>
                                    <a href='page4.html' class= 'nav-link'>The Progress</a>
                                </li>
                                <li class='nav-item active'>
                                    <a href='page5.html' class= 'nav-link active'>The Gap Score</a>
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
            <header class='ScoreHeader'>
                <h2 class='headertitle'>THE GAP SCORE</h2>
            </header>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";
        html = html + """
            <ul class='breadcrumb-navigation'>
            <li><a href="/">Home</a></li>
            <li>The Gap Score</li>
            </ul>
                """;

        html = html + """
                <p class='intro'><br>The Gap Score is calculated to show how well 
                Local Government Areas are achieving the Socioeconomic Outcomes relevant for 
                corresponding Datasets. The Gap Score generates a value between -10 to 10
                 where a score of 10 represents an extreme gap disadvantaging the Indigenous population.
                 A score of -10 shows an extreme gap disadvantaging the Non-Indigenous population.
                 A score of 0 displays that the LGA has succeeded in closing the gap between the Non-Indigenous and Indigenous 
                 population. The average (AVG) Gap Score is a combined Gap Score for each LGA across all of the categories 
                 selected by the user. <br><br></p>
        
        <div class='filters3'>
            <form action='/page5.html' method='post' class= 'form3' id='formLvl3'>
            <input name='pageNum' id='pageNum' value='1' hidden/>
            <div class='filtersdesc'>
                <div class= 'checks'>
                <div class= innerchecks>
                <p class ='f3'>GENERATE GAP SCORES FOR:</p>
                <div class='checkboxes'>
                <input type='checkbox' id='dataset1' name='datasets[]' value='dataset1'>
                <label for='html'>Living a long life</label><br>
                <input type='checkbox' id='dataset2' name='datasets[]' value='dataset2'>
                <label for='html'>Living a healthy life</label><br>
                <input type='checkbox' id='dataset3' name='datasets[]' value='dataset3'>
                <label for='html'>Full learning potential</label><br>
                <input type='checkbox' id='dataset4' name='datasets[]' value='dataset4'>
                <label for='html'>Economic Contribution</label><br>
                </div>
                </div>   
                </div>


                <div class='descriptions'>
                    <h3 class='lvl3cat'>Living a long life:</h3>
                    <p class='lvl3des'>Dataset 1 - Outcome 1. Living above 65 years old.</p>
                
                    <h3 class='lvl3cat'>Living a healthy life:</h3>
                    <p class='lvl3des'>Dataset 2 - Outcome 1. Low average health conditions.</p>

                    <h3 class='lvl3cat'>Full learning potential:</h3>
                    <p class='lvl3des'>Dataset 3 - Outcome 5. Completing year 12 education.</p>
            
                    <h3 class='lvl3cat'>Economic contribution:</h3>
                    <p class='lvl3des'>Dataset 4 - Outcome 8. $1,000+ weekly household income.</p>
                </div>
                </div>

            """;


        html = html + """
        <div class='filterBox3'> 
        <div class='accordion3'><p class ='f3'>ADVANCED FILTER OPTIONS:</p> 
        </div>
        <div class='panel'>
        <div class='filterslvl3'>

                <select name='LGApopRanges' id='LGApopRanges'>
                    <option value='allPopRanges'>Population Range</option>
                    <option value='0-500'>0-500</option>
                    <option value='500-2000'>500-2,000</option>
                    <option value='2000-10000'>2,000-10,000</option>
                    <option value='10000-50000'>10,000-50,000</option>
                    <option value='50000-200000'>50,000-200,000</option>
                    <option value='200000+'>200,000+</option>
                </select>
                <select name='LGAareaRanges' id='LGAareaRanges'>
                    <option value='allAreas'>LGA Area Range</option>
                    <option value='0-100'>0-100 km²</option>
                    <option value='100-1000'>100-1,000km²</option>
                    <option value='1000-10000'>1,000-10,000km²</option>
                    <option value='10000-50000'>10,000-50,000km²</option>
                    <option value='50000-150000'>50,000-150,000km²</option>
                    <option value='150000+'>150,000km² +</option>
                </select>
                <select name='sortBy' id='sortBy'>
                    <option value='LGAname'>Sort by</option>
                    <option value='LGAname'>LGA Name</option>
                    <option value='PopGapScore2016'>2016 Gap Score (Dataset 1)</option>
                    <option value='PopGapScore2021'>2021 Gap Score (Dataset 1)</option>
                    <option value='PopChangeInGapScore'>Change In Score (Dataset 1)</option>

                    <option value='HcGapScore2016'>2016 Gap Score (Dataset 2)</option>
                    <option value='HcGapScore2021'>2021 Gap Score (Dataset 2)</option>
                    <option value='HcChangeInGapScore'>Change In Score (Dataset 2)</option>

                    <option value='EduGapScore2016'>2016 Gap Score (Dataset 3)</option>
                    <option value='EduGapScore2021'>2021 Gap Score (Dataset 3)</option>
                    <option value='EduChangeInGapScore'>Change In Score (Dataset 3)</option>

                    <option value='IncGapScore2016'>2016 Gap Score (Dataset 4)</option>
                    <option value='IncGapScore2021'>2021 Gap Score (Dataset 4)</option>
                    <option value='IncChangeInGapScore'>Change In Score (Dataset 4)</option>

                    <option value='AvgGapScore2016'>Avg Gap Score 2016</option>
                    <option value='AvgGapScore2021'>Avg Gap Score 2021</option>
                    <option value='TotalGapScoreChange'>Change In Avg Score</option>
                </select>
                <select name='sortOrder' id='sortOrder'>
                    <option value='asc'>Sort Order</option>
                    <option value='asc'>Ascending</option>
                    <option value='desc'>Descending</option>
                </select>
                </div>
                </div>
                </div>

                <button type='submit'>GENERATE TABLE</button>
            </form>
        </div>
        """;

        if (datasets.size() == 0) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h3 class= 't3'>Please select from the categories above and generate your table.</h3>";

        } else {
            //table box 
            html = html +  "<div class='tableBox'>";
            ArrayList<String> dbData = new ArrayList<String>();

            //MAKING THE TABLE

            dbData = jdbc.getGapScoreData(datasets, LGAareaRanges, LGApopRanges, sortBy, sortOrder, pageNum);

                OliviaTableMethods rows = new OliviaTableMethods();
                html = html + rows.makeMainTableLvlThree(dbData);


                // pagination

                html += "<div class='pagination'>";
                if(pageNum > 1) {
                    html += "<button id='prev' data-pageNum='" + pageNum + "' data-datasets='" + datasets + "' ";
                    html += "data-LGApopRanges='" + LGApopRanges + "' data-LGAareaRanges='" + LGAareaRanges + "' data-sortBy='" + sortBy + "' data-sortOrder='" + sortOrder + "'>Prev</button>";
                }
                
                html += "<button id='next' data-pageNum='" + pageNum + "' data-datasets='" + datasets + "' ";
                html += "data-LGApopRanges='" + LGApopRanges + "' data-LGAareaRanges='" + LGAareaRanges + "' data-sortBy='" + sortBy + "' data-sortOrder='" + sortOrder + "'>Next</button>";
                html += "</div>";

                html += "<p>*Some data has been removed for census participants with 'non-stated' Indigenous or Non-Indigenous status.";
                html += " This has been accounted for when performing calculations.";
                html += " Data cells with a - represent instances where there was no appropriate census data.";
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
   
                    //next/prev buttons
                    var nextButton = document.getElementById('next');
                    var prevButton = document.getElementById('prev');

                    //set filters to prev values
                    var setFilters = function(element, increment = 0) {

                        //get filters from button
                        var pageNum = parseInt(element.getAttribute('data-pageNum')) + increment;
                        var datasets = element.getAttribute('data-datasets');
                        var LGApopRanges = element.getAttribute('data-LGApopRanges');
                        var LGAareaRanges = element.getAttribute('data-LGAareaRanges');
                        var sortBy = element.getAttribute('data-sortBy');
                        var sortOrder = element.getAttribute('data-sortOrder');

                        //remove [] and spaces so we just get values
                        datasets = datasets.replace('[', '').replace(']', '').replaceAll(' ', '');
                        var datasetsArray = datasets.split(',');

                        //check the dataset boxes they chose on prev page
                        for(var i = 0; i < datasetsArray.length; i++) {

                            if(datasetsArray[i] === 'dataset1') {
                                document.getElementById('dataset1').setAttribute('checked', 'checked');

                            } else if(datasetsArray[i] === 'dataset2') {
                                document.getElementById('dataset2').setAttribute('checked', 'checked');

                            } else if(datasetsArray[i] === 'dataset3') {
                                document.getElementById('dataset3').setAttribute('checked', 'checked');

                            } else if(datasetsArray[i] === 'dataset4') {
                                document.getElementById('dataset4').setAttribute('checked', 'checked');
                            }
                        }

                        //set filters to previous values
                        document.getElementById('LGApopRanges').value = LGApopRanges;
                        document.getElementById('LGAareaRanges').value = LGAareaRanges;
                        document.getElementById('sortBy').value = sortBy;
                        document.getElementById('sortOrder').value = sortOrder;
                        document.getElementById('pageNum').value = pageNum;

                    }

                    //click function
                    var buttonClick = function(e) {

                        setFilters(this, 1);

                        //submit the form
                        document.getElementById('formLvl3').submit();

                    }

                    //click function
                    var prevButtonClick = function(e) {

                        setFilters(this, -1);

                        //submit the form
                        document.getElementById('formLvl3').submit();

                    }

                    //on click
                    if(nextButton !== null) {

                        //set filters to old values on load
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
                var acc = document.getElementsByClassName('accordion3');
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
