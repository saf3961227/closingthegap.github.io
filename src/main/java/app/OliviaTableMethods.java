package app;

import java.util.ArrayList;
import java.util.Arrays;

public class OliviaTableMethods {

    public ArrayList<String> getHeadings(String dataset) {
        if (dataset.equals("one")) {
            return new ArrayList<>(
                Arrays.asList("0-4 Yrs", "5-9 Yrs", "10-14 Yrs", "15-19 Yrs", "20-24 Yrs", "25-29 Yrs", "30-34 Yrs", "35-39 Yrs", "40-44 Yrs", "45-49 Yrs", "50-54 Yrs", "55-59 Yrs", "	60-64 Yrs", "65+ Yrs")
            );
        }
        if (dataset.equals("two")) {
            return new ArrayList<>(
                Arrays.asList("Arthritis", "Asthma", "Cancer", "Dementia", "Diabetes", "Heart Disease", "Kidney Disease", "Lung Condition", "Mental Health", "Stroke", "Other")
            );
        }
        if (dataset.equals("three")) {
            return new ArrayList<>(
                Arrays.asList("No Schooling", "Year 8 Below", "Year 9", "Year 10", "Year 11", "Year 12")
            );
        }
        if (dataset.equals("four")) {
            return new ArrayList<>(
                Arrays.asList("$1 -<br>$149", "$150 -<br>$299", "$300 -<br>$399", "$400 -<br>$499", "$500 -<br>$649", "$650 -<br>$799", "$800 -<br>$999", "$1000 -<br>$1249", "$1250 -<br>$1499", "$1500 -<br>$1749", "$1750 -<br>$1999", "$2000 -<br>$2499", "$2500 -<br>$2999", "$3000 -<br>$3499", "$3500+")
            );
        }
        else {
            return new ArrayList<>(
                Arrays.asList("ERROR")
            );
        }
    }

    public String makeHeadings(String dataset) {
        ArrayList<String> headings = getHeadings(dataset);
        String html = "<div class='tableWrap'><table class= 'theGapTable'> <thead> <tr>";
        html += "<th> LGA </th> <th> Data for </th>";

        for (int i = 0; i < headings.size(); i++) {
            html += "<th>" + headings.get(i) + "</th>";
        }
        html+= "</tr> </thead>";
        return html;
    }

    //making row method

    public String makeRows(ArrayList<String> count, String datatype, int numHeadings) {
        String html = "";
        int rowspan = 2;
        int rowspanCounter = 0;
        numHeadings = numHeadings+2; // LGA and DATA FOR headings are not within my heading methods.
        
        if(datatype.equals("propdata")){        //setting rowspan of 3 for proportional data (will have extra row for the gap)
            rowspan = 3;
        }

        int indigTotal = 0;
        int nonIndigTotal = 0;
        
        for(int i = 0; i < (count.size()/numHeadings); i++) {
            html += "<tr>";
        
            //looping for the total of each row
            for(int index = 2; index < numHeadings; index++){
                if(index == 2){
                    if(i % 2 == 0){
                        indigTotal = 0;
                        nonIndigTotal = 0;
                    }
                }
                if(i % 2 != 0){
                    nonIndigTotal += Integer.parseInt(count.get(index+(i*numHeadings)));
                }
                else{
                    indigTotal += Integer.parseInt(count.get(index+(i*numHeadings))); 
                }
            }
            for (int j = i * numHeadings; j < i*numHeadings + numHeadings; j++){
                if(j == i * numHeadings) {
                    if(rowspanCounter == 0) {
                        //Render rowspan cell
                        html += "<td rowspan = '" + String.valueOf(rowspan) + "' class='LGAname'>" + count.get(j) + "</td>";
                        rowspanCounter++;
                        
                    } else if(rowspanCounter < rowspan) {
                        //Don't render cell, but increment counter
                        rowspanCounter++;
                    } else {
                        rowspanCounter = 1;
                        html += "<td rowspan = '" + String.valueOf(rowspan) + "' class='LGAname'>" + count.get(j) + "</td>";
                    }
                }
                else {
                    //Render normal cell
                    if(datatype.equals("rawdata")){                         //if raw data selected by user:
                        html += "<td>" + count.get(j) + "</td>";
                    }
                    else if(datatype.equals("propdata")){                       //if proportional data selected by user:
                        if(j >= i * numHeadings && j < i * numHeadings+2) {
                            html += "<td>" + count.get(j) + "</td>";
                        }
                        else {
                            Double value = Double.parseDouble(count.get(j));

                            if(i % 2 != 0){
                                html += "<td>" + String.format("%.2f", (value/nonIndigTotal)*100) + "%</td>";  //if odd row, make prop value of non indig 
                            }
                            else{
                                html += "<td>" + String.format("%.2f", (value/indigTotal)*100) + "%</td>";  //if even row, make prop value of indig
                            }
                        }
                    }
                }
            }
            html += "</tr>";

            if(rowspan == 3 && rowspanCounter == 2) { // if rowspan is 3, adding row for the gap
                html += "<tr>";
                
                for (int k = 0; k < numHeadings-1; k++) {
                    if(k == 0) {
                        html += "<td>The Gap</td>";
                    } 
                    else {
                        Double nonIndigValue = Double.parseDouble(count.get((i*numHeadings) + k + 1));  
                        Double indigValue = Double.parseDouble(count.get(((i-1)*numHeadings) + k + 1));
                        Double nonIndigPerc = (nonIndigValue/nonIndigTotal)*100;                        //getting the prop values
                        Double indigPerc = (indigValue/indigTotal)*100;
                        html += "<td>" + String.format("%.2f", (nonIndigPerc - indigPerc)) +"</td>";    //deducting the prop values for the gap 
                    }
                }
                html += "</tr>";
                rowspanCounter = 0;
            }
        }
        
        html += "</table></div>";
        return html;
    }




    // MAKING LEVEL THREE TABLE:
    
    public String makeMainTableLvlThree(ArrayList<String> count) {
        String html = "";
        int numHeadings = 9; 
        
        if(count.size() == 0) {
            html = html + "<h2>No search results. Please select different filters.</h2>";
            return html;
        }

        html += "<div class='tableWrap'><table class= 'theGapScoreTable'> <thead> <tr>";    //HARDCODING THE HEADINGS
        html += "<th>LGA</th>";
        html += "<th>DATASET</th>";
        html += "<th>CATEGORY</th>";
        html += "<th>GAP SCORE 2016</th>";
        html += "<th>GAP SCORE 2021</th>";
        html += "<th>CHANGE IN GAP</th>";
        html += "<th>AVG GAP SCORE 2016</th>";
        html += "<th>AVG GAP SCORE 2021</th>";
        html += "<th>CHANGE IN AVG</th>";
        html+= "</tr> </thead>";
        
        //MAKING THE ROWS

        for (int i = 0; i < (count.size() / numHeadings); i++) {
            html += "<tr>";

            for (int j = i * numHeadings; j < i * numHeadings + numHeadings; j++) {
                if (count.get(j) == null) {
                    html = html + "<td>-</td>";
                } 
                else {
                    if(j % numHeadings == 0) {
                        html = html + "<td class='LGAname'>" + count.get(j) + "</td>";
                    }
                    else {
                    html += "<td>" + count.get(j) + "</td>";
                    }
                }
            }
            html += "</tr>";
        }

        html += "</table></div>";
        return html;
    }

}


   
