import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 
 * Returns the low tides that accord during daylight for the list of locations found in the main method.<br>
 * Data is taken from https://www.tide-forecast.com/<br> 
 * Assumptions:  Webpage contains only 1 table<br>
 *               Last element contains indicator for type of row
 * @author John Mitchell (jrmmba@outlook.com)
 * @version June 29, 2018
 */
public class DaylightLowTides
{
    public static void main(String[] args) throws IOException 
    {
        // location must be in the form of city-state. Any spaces in city or state are replaced with -
        // possible future enhancement to read location from some other location (keyboard, file)

        System.out.println("Data taken from https://www.tide-forecast.com/");
        System.out.println();
        listLowTides("Half-Moon-Bay-California");
        listLowTides("Huntington-Beach"); // yes - Huntington Beach is special
        listLowTides("Providence-Rhode-Island");
        listLowTides("Wrightsville-Beach-North-Carolina");

    } // end of main method
    
    
    /** 
     * 
     * Parses the data for the given location. Displays to console low tides that accord during daylight for given location.
     * 
     * @param location - A string. location the form of city-state. Any spaces in city or state are replaced with -
     * @author John Mitchell
     * @version June 29, 2018
    */
    public static void listLowTides (String location) 
    {
        //assuming a static url. Possible future enhancement
        String url = "https://www.tide-forecast.com/locations/" + location + "/tides/latest";
        
        System.out.println("*** Printing Daylight Low Tides for " + location + " ***");
        
        Document doc = null; // doc must be declared outside of the try catch block
        try
        {
            doc = Jsoup.connect(url).get();
        }
        catch (IOException e) 
        {
            System.out.println ("********** ERROR **********");
            System.out.println ("URL Not found: " + url);
            System.out.println ("System Error Message: " + e.getMessage());
            System.out.println ();
            return;
        }
        
        // print table headers
        System.out.printf("%-25s", "Date");
        System.out.printf("%-10s", "Time");
        System.out.printf("%-12s", "Time Zone");
        System.out.print("Low Tide Level");
        System.out.println();
        
        boolean  daylight = false; // we only want rows between sunrise and sunset
        Elements calendarDate = new Elements(); // date only appears once per day. A day has multiple rows. So need to keep date around.
        
        //process the single table on the page
        Elements table = doc.select("table");
        
        // loop through rows in table
        for (Element row : table.select("tr"))
        {
            // if row contains date, capture it
            if (row.select("td.date").size() > 0)
            {
                // we have a new day
                calendarDate = row.select("td.date");
            }
            else
            {
                // same date
            };
               
            Elements tds = row.select("td");
                
            // is it sunrise or sunset?
            if (tds.last().text().equals("Sunrise"))
            {
                daylight = true;
            }
            else if (tds.last().text().equals("Sunset"))
            {
                daylight = false;
            }
            else
            {
                if (daylight && tds.last().text().equals("Low Tide"))
                {
                    System.out.printf("%-25s", calendarDate.text());
                    
                    // the website uses both of these classes for time depending on the row. So only one will turn a value
                    if (row.select("td.time time").size() > 0)
                    {
                        System.out.printf("%-10s", tds.select("td.time tide").text());
                    }
                    else
                    {
                        System.out.printf("%-10s", tds.select("td.time").text());
                    };
                    
                    System.out.printf("%-12s", tds.select("td.time-zone").text());
                    System.out.print(tds.select("td.level metric").text() + " ");
                    System.out.print(tds.select("td.level").text());
                    System.out.println();
                }
            }
        } // loop through rows
        
        System.out.println();
    }  // method listLowTides
} // class
