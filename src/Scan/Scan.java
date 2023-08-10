package Scan;

import SK.gnome.morena.MorenaImage;
import SK.gnome.twain.TwainException;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;
import Utils.Result;
import Utils.Result.ResultType;

/**
 * This class is meant to be used for scanning images with an EPSON scanner for image processing.
 * @author Nicholas.Sixbury
 */
public class Scan {
    /**
     * Assumedly an object pointing to the scanner we want to access.
     */
    TwainSource scanSource;

    /**
     * Tries to find a valid TwainSource and save it for later.
     * @return Returns an error if one was thrown, otherwise is Ok.
     */
    public Result<Result.ResultType> initScanner() {
        // make an attempt to initialize the scanner
        try {
            // Figure out which source to use
            TwainSource tss[] = TwainManager.listSources();
            System.out.println("Twain Sources on following lines.");
            for (TwainSource ts : tss) {
                System.out.println(ts.toString());
            }//end looping over the twain sources we found
            System.out.println();

            // set source to null ???
            scanSource = TwainManager.selectSource(null);
            System.out.println("Selected source is " + scanSource);
            // check that source isn't null ???
            if (scanSource == null) {
                throw new NullPointerException("The twain source was null!");
            }//end if scanSource is null
        }//end trying to figure out the twain source
        catch (Exception e) {
            return new Result<>(e);
        }//end catching any exceptions

        // if we've reached this point, we must be fine
        return new Result<>();
    }//end initScanner()

    /**
     * Tries to set the scan settings of the saved twain source.
     * @return Returns an error if one was thrown, otherwise is Ok.
     */
    public Result<Result.ResultType> setScanSettings() {
        // make an attempt to set settings of twain source
        try {
            // TODO: Set the settings for the scanner
            scanSource.setVisible(false);
            scanSource.setIndicators(false);
            scanSource.setColorMode();
            // TODO: Figure out what Bill's config is
            // statement would be if config.getLightSource().equals(Config.lightSource)
            boolean configIndicator = true;
            if (configIndicator) {
                scanSource.setLightPath(1);
            }//end if statement
            else {
                scanSource.setLightPath(0);
            }//end else statement
        }//end trying to set scan settings
        catch (Exception e) {
            return new Result<>(e);
        }//end catching any exceptions

        // if we've reached this point, we must be fine
        return new Result<>();
    }//end setScanSettings()

    /**
     * Tries to run the scanner of the saved twain source with proper settings.
     * @return Returns an error if one was thrown, otherwise is Ok.
     */
    public Result<String> runScanner() {
        // make an attempt to run the scanner
        try {
            // TODO: run the scanner
            MorenaImage img = new MorenaImage(scanSource);

            System.out.println("Size of acquired image is " + img.getWidth() + " x " + img.getHeight());

            // TODO: Actually save the img somehow
        }//end trying to run the scanner
        catch (Exception e) {
            return new Result<>(e);
        }//end catching any exceptions

        // if we've reached this point, we must be fine
        return new Result<>();
    }//end runScanner()

    /**
     * Closes the twain manager. This operation might fail, for some reason. If it does, an error will be returned with the result type.
     * @return Returns an exception result if an exception returns. Otherwise, nothing useful is returned.
     */
    public Result<ResultType> closeScanner() {
        try {
            // TODO: Not sure what this does, but was present in Bill's version
            TwainManager.close();
        } catch (TwainException e) {
            return new Result<>(e);
        }//end catching and returning exceptions
        return new Result<>();
    }//end closeScanner()
}//end class Scan
