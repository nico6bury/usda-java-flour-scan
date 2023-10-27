package IJM;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import Utils.Result;
import ij.IJ;
import ij.ImagePlus;

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

    public Result<String> MainMacro(List<File> files_to_process) {
        int baseThreshold = 160;
        int szMin = 2;
        int defSizeLimit = 1000;
        int splitWidth = 2400;
        int splitHeight = 1200;
        String baseMacroDir = base_macro_dir.getAbsolutePath() + File.separator;
        List<File> filesProcessed = new ArrayList<File>();
        for (int i = 0; i < files_to_process.size(); i++) {
            File file = files_to_process.get(i);
            // actually start processing
            ImagePlus this_image = IJ.openImage(file.getAbsolutePath());
            // fiure out the dimensions of this image
            int imgWidth = this_image.getWidth();
            int imgHeight = this_image.getHeight();
            if (imgWidth == splitWidth && imgHeight == splitHeight) {
                // close the current image, as we no longer need it in this macro
                this_image.close();
                // TODO: do all the stuff for splitting this image
            }//end if we need to split the file first
            else {
                // process the current image and then close it
                // contents of processFile() from the macro:
                IJ.run(this_image, "Sharpen", "");
                IJ.run(this_image, "Smooth", "");
                IJ.run(this_image, "8-bit", "");
                IJ.setThreshold(this_image, 0, 160);
                IJ.run(this_image, "Convert to Mask", "");

                // set the scale so it doesn't measure in mm or something
                IJ.run(this_image, "Set Scale...", "distance=0 known=0 unit=pixel global");
                // specify the measure data to recieve from analyze particles
                IJ.run(this_image, "Set Measurements...", "area perimeter bounding redirect=None decimal=1");

                IJ.run(this_image, "Analyze Particles", "size=" + szMin + "-" + defSizeLimit + " show=[Overlay Masks] clear summarize");

                this_image.close();
                // update list of processed images
                filesProcessed.add(file);
            }//end else we can just process the image like normal
        }//end looping over each file we want to process

        // add lab processing into the mix
        // set up parameters to send to LabProcessing
        // TODO: run the Lab processing part

        // TODO: do stuff to figure out eventual file names

        // TODO: run results formatter

        return new Result<>("placeholder value");
    }//end Main Macro converted from ijm
}//end class IJProcess
