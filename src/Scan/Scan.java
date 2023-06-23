/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scan;

import SK.gnome.twain.TwainSource;
import Utils.Result;

/**
 * This class is meant to be used for scanning images with an EPSON scanner for image processing.
 * @author Nicholas.Sixbury
 */
public class Scan {
    /**
     * Assumedly an object pointing to the scanner we want to access.
     */
    TwainSource tss;

    /**
     * Tries to find a valid TwainSource and save it for later.
     * @return Returns an error if one was thrown, otherwise is Ok.
     */
    public Result<Result.ResultType> initScanner() {
        // make an attempt to initialize the scanner
        try {
            // TODO: Figure out which source to use
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
        }//end trying to run the scanner
        catch (Exception e) {
            return new Result<>(e);
        }//end catching any exceptions

        // if we've reached this point, we must be fine
        return new Result<>();
    }//end runScanner()
}//end class Scan
