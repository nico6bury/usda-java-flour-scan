package IJM;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;

import Utils.Result;
import ij.IJ;

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

    /**
     * Constructs the class by reading the jar location and 
     * finding the location of the ijm files.
     * @throws URISyntaxException This exception can occur while interpretting the path to the jar which the program is running from.
     */
    public IJProcess() throws URISyntaxException {
        String jar_location = new File(IJProcess.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().toString();
        String macro_folder = jar_location + File.separator + "macros";
        base_macro_dir = new File(macro_folder);
        String macro_file = macro_folder + File.separator + "NS-FlourScan-Main.ijm";
        base_macro_file = new File(macro_file);
    }//end constructor

    public Result<String> runMacro(String file_to_process) {
        try {
            StringBuilder macro_contents = new StringBuilder();
            for (String line : Files.readAllLines(base_macro_file.toPath())) {
                macro_contents.append(line + "\n");
            }//end adding all lines to string builder
            String main_macro_args = "useBatchMode?0|" + 
            "chosenFilePath?" + file_to_process + "|" +
            "baseThreshold?160|" +
            "szMin?2|" +
            "defSizeLimit?1000|" +
            "splitWidth?2400|" +
            "splitHeight?1200|" +
            "baseMacroDir?" + base_macro_dir.getAbsolutePath() + File.separator;
            IJ.runMacro(macro_contents.toString(), main_macro_args);
        } catch (Exception e) {
            return new Result<>(e);
        }//end if we catch any exceptions

        // if we've readed this point, we must be fine
        return new Result<>("everything was fine ;-)");
    }//end runMacro()
}//end class IJProcess
