package IJ;

import java.io.File;
import java.net.URISyntaxException;

/**
 * This class keeps track of everything related to running files through the imagej flour macros.
 * It also handles all the calls to imagej.
 * @author Nicholas Sixbury
 */
public class IJProcess {
    /**
     * Should be the directory where the jar is located
     */
    File base_macro_dir;
    File base_macro_file;

    public IJProcess() throws URISyntaxException {
        String jar_location = new File(IJProcess.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().toString();
        String macro_folder = jar_location + File.separator + "macros";
        base_macro_dir = new File(macro_folder);
        String macro_file = macro_folder + File.separator + "NS-FlourScan-Main.ijm";
        base_macro_file = new File(macro_file);
    }//end constructor
}//end class IJProcess
