package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {
    // header info
    public static String LOCATION = "USDA-ARS Manhattan, KS";
    public static String DATE() {
        DateTimeFormatter month_year = DateTimeFormatter.ofPattern("MMM/yyyy");
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(month_year);
    }//end DATE()
    public static String PEOPLE = "Sixbury/Rust/Brabec";
    public static String PROGRAM_NAME = "Flour Scan & Analysis";
    public static String VERSION = "v1.0.0";
}//end class Constants
