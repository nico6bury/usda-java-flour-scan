package Config;

import java.io.File;

/**
 * This class is meant to store various configuration settings related to scanning, meant to be used with the Scan package.
 * @author Nicholas.Sixbury
 */
public class ScanConfig {
    /**
     * Assumedly the light source for the scanner.
     */
    public String lightSource;
    /**
     * The directory where the scanned images should go.
     */
    public String scanDirectory;
    /**
     * The dpi setting to scan with.
     */
    public int dpi;

    /**
     * This method simply sets all properties to their default values.
     * @throws SecurityException if there's a security manager and we don't have access to figure out the "user.home" directory.
     */
    public void setDefault() {
        lightSource = "Back lit";
        scanDirectory = System.getProperty("user.home") + File.separator + "Pictures";
    }//end setDefault()
}//end class ScanConfig
