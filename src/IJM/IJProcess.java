package IJM;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import Utils.Result;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Roi;
import ij.measure.ResultsTable;

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
            // StringBuilder macro_contents = new StringBuilder();
            // for (String line : Files.readAllLines(base_macro_file.toPath())) {
            //     macro_contents.append(line + "\n");
            // }//end adding all lines to string builder
            // String main_macro_args = "useBatchMode?0|" + 
            // "chosenFilePath?" + file_to_process + "|" +
            // "baseThreshold?160|" +
            // "szMin?2|" +
            // "defSizeLimit?1000|" +
            // "splitWidth?2400|" +
            // "splitHeight?1200|" +
            // "baseMacroDir?" + base_macro_dir.getAbsolutePath() + File.separator;
            // IJ.runMacro(macro_contents.toString(), main_macro_args);
            List<String> files_to_process = new ArrayList<String>();
            files_to_process.add(file_to_process);
            MainMacro(files_to_process);
        } catch (Exception e) {
            return new Result<>(e);
        }//end if we catch any exceptions

        // if we've readed this point, we must be fine
        return new Result<>("everything was fine ;-)");
    }//end runMacro()

    public Result<String> MainMacro(List<String> files_to_process) {
        // int baseThreshold = 160;
        List<SumResult> runningSum = new ArrayList<SumResult>();
        int splitWidth = 2400;
        int splitHeight = 1200;
        // String baseMacroDir = base_macro_dir.getAbsolutePath() + File.separator;
        List<String> filesProcessed = new ArrayList<String>();
        for (int i = 0; i < files_to_process.size(); i++) {
            String file = files_to_process.get(i);
            // actually start processing
            ImagePlus this_image = IJ.openImage(file);
            // fiure out the dimensions of this image
            int imgWidth = this_image.getWidth();
            int imgHeight = this_image.getHeight();
            if (imgWidth == splitWidth && imgHeight == splitHeight) {
                // split the current image in two and process both halves
                List<ImagePlus> imagesToSplit = new ArrayList<>();
                imagesToSplit.add(this_image);
                List<ImagePlus> splitImages = PicSplitter(imagesToSplit);

                // left image
                SumResult leftResult = ijmProcessFile(splitImages.get(0));
                String sliceBase = leftResult.slice.substring(0, leftResult.slice.length() - 4);
                leftResult.slice = sliceBase + "-L.tif";
                runningSum.add(leftResult);

                // right image
                SumResult rightResult = ijmProcessFile(splitImages.get(1));
                rightResult.slice = sliceBase + "-R.tif";
                runningSum.add(rightResult);
            }//end if we need to split the file first
            else {
                // process the current image and then close it
                SumResult this_result = ijmProcessFile(this_image);
                runningSum.add(this_result);

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

    public SumResult ijmProcessFile(ImagePlus img) {
        // global variables from the ijm
        int szMin = 2;
        int defSizeLimit = 1000;
        
        // contents of processFile() from the macro:
        IJ.run(img, "Sharpen", "");
        IJ.run(img, "Smooth", "");
        IJ.run(img, "8-bit", "");
        IJ.setThreshold(img, 0, 160);
        IJ.run(img, "Convert to Mask", "");

        // set the scale so it doesn't measure in mm or something
        IJ.run(img, "Set Scale...", "distance=0 known=0 unit=pixel global");

        // reset results table
        ResultsTable curTable = ResultsTable.getResultsTable("Summary");
        if (curTable != null) {curTable.reset();}

        // specify the measure data to recieve from analyze particles
        IJ.run(img, "Set Measurements...", "area perimeter bounding redirect=None decimal=1");

        // analyze particles to get summary of specks
        IJ.run(img, "Analyze Particles...", "size=" + szMin + "-" + defSizeLimit + " show=[Overlay Masks] clear summarize");

        // collect recent analyze particles run from summary table
        ResultsTable sumTable = ResultsTable.getResultsTable("Summary");
        // this should contain "Slice", "Count", "Total Area", "Average Size", "%Area", "Perim."
        // String[] headings = sumTable.getColumnHeadings().split("\t");
        String slice = sumTable.getStringValue("Slice", 0);
        int count = (int)sumTable.getValue("Count", 0);
        int total_area = (int)sumTable.getValue("Total Area", 0);
        double prcnt_area = sumTable.getValue("%Area", 0);
        SumResult this_result = new SumResult(slice, count, total_area, prcnt_area);
        
        return this_result;
    }//end ijmProcessFile()

    public List<ImagePlus> PicSplitter(List<ImagePlus> images_to_process) {
        List<ImagePlus> splitImages = new ArrayList<ImagePlus>();

        for (int i = 0; i < images_to_process.size(); i++) {
            ImagePlus this_image1 = images_to_process.get(i);
            ImagePlus this_image2 = this_image1.duplicate();
            // get dimensions so we know where to split
            int imgWidth = this_image1.getWidth();
            int imgHeight = this_image1.getHeight();
            
            Roi[] leftRois = new Roi[1];
            // define the left split
            leftRois[0] = new Roi(0, 0, imgWidth / 2, imgHeight);
            
            Roi[] rightRois = new Roi[1];
            // define the right split
            rightRois[0] = new Roi(imgWidth / 2, 0, imgWidth / 2, imgHeight);

            // get the left and right splits
            ImagePlus[] results1 = this_image1.crop(leftRois);
            ImagePlus[] results2 = this_image2.crop(rightRois);
            splitImages.add(results1[0]);
            splitImages.add(results2[0]);
        }//end looping over images to process
        
        return splitImages;
    }//end Pic Splitter macro converted from ijm
}//end class IJProcess
