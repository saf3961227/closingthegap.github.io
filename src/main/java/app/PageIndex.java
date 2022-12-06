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
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" +
                "<title>Closing The Gap</title>";
        html = html + "<link rel='icon' type='image/ico' href='/favicon.ico' />";

        // Add some CSS (external file)
        // BOOTSTRAP
        html = html
                + "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi' crossorigin='anonymous'>";

        html = html + "<link rel='stylesheet' type='text/css' href='olivia.css' />";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1'>";

        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // JAVASCRIPT FOR BOOTSTRAP
        html = html
                + "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js' integrity='sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk' crossorigin='anonymous'></script>";

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
                                    <a href='/' class= 'nav-link active'>Home</a>
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
                    <header class='HomeHeader'>
                        <h2 class='headertitle'>CLOSING THE GAP</h2>
                    </header>
                """;

        // SCAFFOLD CODE
        // Add Div for page Content
        // html = html + "<div class='content'>";

        // Add HTML for the list of pages (as well as topnav)
        // html = html + """
        // <p>Homepage information</p>
        // """;

        // Close Content div
        // html = html + "</div>";

        // Add page Content
        html = html + "<div class='content'>";
        html = html
                + """

                            <div class='introHome'>
                                <h1 class='title1'>Intro to Our Focus</h1>
                                <p class='intro'>Closing the Gap refers to the Australian Government <em>Close the Gap</em> campaign which was
                                    originally launched in 2007.
                                    The campaign aims to close key gaps and achieve equality for Aboriginal and Torres Strait Islander people in
                                    health, education and wellbeing.
                                    Initially the campaign only had six targets, which has now grown to the current set of 17 Socioeconomic Outcomes
                                    of the National
                                    Agreement on Closing the Gap as listed below. Our focus is to provide extensive data on Outcome 1, Outcome 5 and Outcome 8.
                                    <br>
                                </p>
                                <div class='herobuttons'>
                                <a href= 'page3.html'><button class='herobutton'>SEE THE GAP</button></a>
                                <a href= 'page4.html'><button class='herobutton'>SEE THE PROGRESS</button></a>
                                </div>
                            </div>


                            """;

        JDBCConnection jdbc = new JDBCConnection();

        ArrayList<Outcome> outcomes = jdbc.getOutcomes();
        Outcome outcome1 = outcomes.get(0);
        Outcome outcome5 = outcomes.get(4);
        Outcome outcome8 = outcomes.get(7);

        html = html
                + """
                            <h3 class='outcometitle'><br>The three Socioeconomic Outcomes our website is focusing on.</h3>
                        """;

        html = html + """
                    <div class='outcomesBox'>
                        <div class='outcomesFocus'>
                            <img class='imageicon' src='logo2.png' alt='icon of logo'>
                                <h3 class='outcomeNum'>
                """;
        html = html + outcome1.getOutcomeNum();
        html = html + outcome1.getOutcomeFocus();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome1.getOutcomeName();
        html = html + """
                        </p>
                </div>
                """;

        html = html + """
                        <div class='outcomesFocus'>
                            <img class='imageicon' src='logo2.png' alt='icon of logo'>
                                <h3 class='outcomeNum'>
                """;
        html = html + outcome5.getOutcomeNum();
        html = html + outcome5.getOutcomeFocus();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome5.getOutcomeName();
        html = html + """
                        </p>
                </div>
                """;

        html = html + """
                        <div class='outcomesFocus'>
                            <img class='imageicon' src='logo2.png' alt='icon of logo'>
                                <h3 class='outcomeNum'>
                """;
        html = html + outcome8.getOutcomeNum();
        html = html + outcome8.getOutcomeFocus();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome8.getOutcomeName();
        html = html + """
                        </p>
                </div>
                </div>


                """;

        html = html
                + """
                            <h3 class='outcometitle'><br>All 17 Socioeconomic Outcomes of the National Agreement on Closing the Gap.</h3>
                        """;

        // trying to retrieve outcomes from db
        html = html + "<div class='content'>";

        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        // JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the outcomes
        // ArrayList<Outcome> outcomes = jdbc.getOutcomes();

        // Outcome outcome1 = outcomes.get(0);
        Outcome outcome2 = outcomes.get(1);
        Outcome outcome3 = outcomes.get(2);
        Outcome outcome4 = outcomes.get(3);
        // Outcome outcome5 = outcomes.get(4);
        Outcome outcome6 = outcomes.get(5);
        Outcome outcome7 = outcomes.get(6);
        // Outcome outcome8 = outcomes.get(7);
        Outcome outcome9 = outcomes.get(8);
        Outcome outcome10 = outcomes.get(9);
        Outcome outcome11 = outcomes.get(10);
        Outcome outcome12 = outcomes.get(11);
        Outcome outcome13 = outcomes.get(12);
        Outcome outcome14 = outcomes.get(13);
        Outcome outcome15 = outcomes.get(14);
        Outcome outcome16 = outcomes.get(15);
        Outcome outcome17 = outcomes.get(16);

        html = html + """
                    <div class='outcomesBox'>
                        <div class='outcomesReg'>
                            <img class='imageicon' src='logo2.png' alt='icon of logo'>
                                <h3 class='outcomeNum'>
                """;
        html = html + outcome1.getOutcomeNum();
        html = html + outcome1.getOutcomeFocus();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome1.getOutcomeName();
        html = html + """
                        </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome2.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome2.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome3.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome3.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome4.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome4.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome5.getOutcomeNum();
        html = html + outcome5.getOutcomeFocus();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome5.getOutcomeName();
        html = html + """
                        </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome6.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome6.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome7.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome7.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome8.getOutcomeNum();
        html = html + outcome8.getOutcomeFocus();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome8.getOutcomeName();
        html = html + """
                        </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome9.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome9.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome10.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome10.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome11.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome11.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome12.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome12.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome13.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome13.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome14.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome14.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome15.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome15.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome16.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome16.getOutcomeName();
        html = html + """
                    </p>
                </div>

                <div class='outcomesReg'>
                    <img class='imageicon' src='logo2.png' alt='icon of logo'>
                        <h3 class='outcomeNum'>
                """;
        html = html + outcome17.getOutcomeNum();
        html = html + """
                    </h3>
                        <p class='outcomeName'>
                """;
        html = html + outcome17.getOutcomeName();
        html = html + """
                    </p>
                </div>

                </div>
                """;

        // Footer
        html = html + """
                    <div class='footer'>
                        <br />
                        <br />
                        <br />
                    </div>
                    </div>
                """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
