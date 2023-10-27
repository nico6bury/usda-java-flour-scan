/*
 * Author: Nicholas Sixbury (someprocessing code 
 * taken from simpler macro by Sophia Grothe and Daniel Brabec)
 * File: NS-FlourScan-Main.ijm
 * Purpose: To serve as the main coordinator macro for all the
 * processes required for processing of flour scans in imagej.
 * 
 * Explanation of Parameter Passing: Each serialized parameter should be
 * separated by the | character. For each parameter, it should be the name
 * followed by the value, separated by the ? character. When giving multiple
 * files or strings, separate them by the \f character. Parameters not given
 * will simply use the default.
 * 
 * Pre-Execution Contract: This macro assumes that it will be called by a 
 * specific java program with all arguments fulfilled. It also kinda assumes that
 * things will work out the way everything is intended. The input file should be
 * an image file of the right type.
 * 
 * Post-Execution Contract: When this macro exits, the results for the L*a*b*
 * stuff will be in the results window, which has been renamed to the value of
 * resultsName. Each individual image will correspond to three lines in the
 * results table, namely for an image called imgName.tif, the lines will be
 * labelled the following: imgName.tif:L*, imgName.tif:a*, imgName.tif:b*
 * 
 * Post-Execution Contract: When this macro exits, the input file(s) specified 
 * (whether chosen by user or passed as arguments) will be processed, and the 
 * output will be placed in the specified location (assumedly some sort of 
 * file handshake between this and the caller).
 * The output will be in txt format.
 * 
 * Parameters that can be set in headless execution mode:
 * useBatchMode : boolean whether or not to use batch mode
 * chosenFilePath : the path to the file we should process this run
 * baseThreshold : the upper threshold to use for processing
 * szMin : default lower size limit
 * defSizeLimit : default upper size limit
 * splitWidth : width that we'd expect for an image that needs to be split
 * splitHeight : height that we'd expect for an image that needs to be split
 * baseMacroDirectory : the directory that this macro is called from
 * outputPath : the path we should output the summary file to
 */

// upper threshold for processing
var th01 = 160;


// define temporary variables for storing dialog results
// whether or not we'll use batch mode, which really speeds things up
var useBatchMode = true;
// all the valid selection methods we might use
var selectionMethods = newArray("Single File", "Multiple Files", "Directory");
// the path of the file we're processing. Might be a directory
var chosenFilePath = "";
// the selection method we're actually going with
var selectionMethod = selectionMethods[2];
// whether or not we should display a helpful progress bar for the user
var shouldDisplayProgress = false;
// valid operating systems
var validOSs = newArray("Windows 10", "Windows 7");
// chosen operating system
var chosenOS = validOSs[0];
// filenames with these strings will be ignored in directory selection
var forbiddenStrings = newArray("-Skip", "-L", "-R");
// the only file extension we won't ignore in directory selection
var expectedFileExtensions = newArray("bmp","tif","tiff");
// save something before it's overwritten
var baseThreshold = th01;
// whether we should display particle detection to the user
var showParticles = false;
// Warn the user if they want to view the particles of lots of images
var particleShowSoftLim = 10
// whether or not to append threshold to summary
var appendThreshold = false;
// default lower size limit
var szMin=2;
// default upper size limit
var defSizeLimit = 1000;
// whether or not to append size limit to summary
var appendSize = false;
// width that we'd expect for an image that needs to be split
var splitWidth = 2400;
// height that we'd expect for an image that needs to be split
var splitHeight = 1200;
// the directory from which this macro is called from
var baseMacroDir = "initialized value, should not appear";
// the path to output the summary to
var outputPath = "initialized value, should not appear";

argumentStr = getArgument();
if(argumentStr == "") {
	// create dialog from file
	showDialog();
}//end if there were no arguments
else {
	// split the arguments and parse them
	parameters = split(argumentStr, '|');
	for(i = 0; i < lengthOf(parameters); i++) {
		parameterSplit = split(parameters[i], '?');
//		if (lengthOf(parameterSplit) < 2) {
//			waitForUser(parameters[i]);
//			continue;// TODO: Have some sort of error log printout for this case
//		}//end if we can't split stuff for some reason
		parameterName = parameterSplit[0];
		parameterValue = parameterSplit[1];
		// test each parameter we look for
		if(parameterName == "useBatchMode") {
			useBatchMode = parseInt(parameterValue);
		}//end if we have useBatchMode setting
		else if(parameterName == "chosenFilePath") {
			chosenFilePath = parameterValue;
		}//end if we have chosenFilePath setting
		else if(parameterName == "baseThreshold") {
			baseThreshold = parseInt(parameterValue);
		}//end if we have baseThreshold setting
		else if(parameterName == "szMin") {
			szMin = parseFloat(parameterValue);
		}//end if we have szMin setting
		else if(parameterName == "defSizeLimit") {
			defSizeLimit = parseFloat(parameterValue);
		}//end if we have defSizeLimit setting
		else if(parameterName == "splitWidth") {
			splitWidth = parseInt(parameterValue);
		}//end if we have splitWidth setting
		else if(parameterName == "splitHeight") {
			splitHeight = parseInt(parameterValue);
		}//end if we have splitHeight setting
		else if(parameterName == "baseMacroDir") {
			baseMacroDir = parameterValue;
		}//end if we have baseMacroDir setting
		else if(parameterName == "outputPath") {
			baseMacroDir = parameterValue;
		}//end if we have outputPath
	}//end reading from each parameter
}//end else we do have arguments

// debug feature for doing infinite max size
infinitySwitch = false;


// save selections from user
//saveDialogConfig();

// act on selected options from dialog window
if(chosenOS == validOSs[1]){
	showMessageWithCancel("WARNING","You've selected Windows 7 as the operating "+
	"system that you're using. The threshold used by this program was tested for"+
	" Windows 10, and there might be differences in thresholding between operating"+
	" systems. At present, this macro does not do anything to account for that, "+
	"so please proceed with caution.");
}//end if chosenOS was Windows 7

// go ahead and grab the path to helper macros for later

thisMacroDir = baseMacroDir;
//thisMacroDir = thisMacroDir + "usda-flour-scan" + File.separator;
picSplitterPath = thisMacroDir + "NS-PicSplitter.ijm";
labProcessorPath = thisMacroDir + "NS-LabProcessor.ijm";
resultsFormatterPath = thisMacroDir + "NS-ResultsFormatter.ijm";

// start actually processing all the files
// based on the selection method provided by user and 
// a whole bunch of tests and checks, gets valid file names
filesToProcess = newArray(1);//getFilepaths(selectionMethod);
filesToProcess[0] = chosenFilePath;
// batch mode
if (useBatchMode) {
	if (showParticles) {
		showMessageWithCancel("Complication",
		"You selected both that you wanted batch mode and that you wanted to show\n"+
		"particles. This isn't really possible, so if you want to see particles,\n"+
		"keep the option that mentions not showing pictures off. Press cancel to\n"+
		"immediately stop the macro.");
	}//end if user also wants to show particles
	setBatchMode(useBatchMode);
}//end if we should use batch mode
if(showParticles && lengthOf(filesToProcess) > particleShowSoftLim){
	showMessageWithCancel("Quick Warning","Just an FYI, you have " +
	lengthOf(filesToProcess) + " files selected to process. You have also \n"+
	"told this macro that you want to stop and look at the particles for every \n"+
	"image. Are you sure about this? If you want to continue anyways, click OK. \n"+
	"If you want to quit and select fewer images, click cancel. Note that you \n"+
	"can queue up a specific number of images to go through by using the file \n"+
	"selection method of [Multiple Files].");
}//end if user wants to show particles for lots of files
// array of the files we actually processed
filesProcessed = newArray(6);
filesProcessedCount = 0;
for(i = 0; i < lengthOf(filesToProcess); i++){

	// actually actually start processing
	open(filesToProcess[i]);
	// figure out the dimensions of this image
	temp = 0;
	imgWidth = -1;
	imgHeight = -1;
	getDimensions(imgWidth, imgHeight, temp, temp, temp);
	if(imgWidth == splitWidth && imgHeight == splitHeight){
		// close the current image, as we no longer need it in this macro
		close();
		// assemble individual parameters for PicSplitter macro
		filesToSplitParameter = String.join(newArray("filesToProcess", filesToProcess[i]), "?");
		outputDirParameter = String.join(newArray("outputToSeparateDirectory", 0), "?");
		// put the parameters together into one string to send to the macro
		fullParameterString = String.join(newArray(filesToSplitParameter, outputDirParameter), "\r");
		// actually run the macro to split the image
		runMacro(picSplitterPath, fullParameterString);
		// figure out what the paths for the two split images should be
		splitImageDir = File.getDirectory(filesToProcess[i]);
		splitImageDir = splitImageDir + "SplitImages" + substring(splitImageDir, lengthOf(splitImageDir) - 1);
		originalImageName = File.getNameWithoutExtension(filesToProcess[i]);
		leftSplitImage = splitImageDir + originalImageName + "-L" + ".tif";
		rightSplitImage = splitImageDir + originalImageName + "-R" + ".tif";
		// process left image and update array
		open(leftSplitImage);
		processFile();
		close();
		filesProcessed = arrayAppend(filesProcessed, filesProcessedCount, leftSplitImage); filesProcessedCount++;
		// process right image and update array
		open(rightSplitImage);
		processFile();
		close();
		filesProcessed = arrayAppend(filesProcessed, filesProcessedCount, rightSplitImage); filesProcessedCount++;
	}//end if we need to split the file first
	else{
		// process the current image and then close it
		processFile();
		close();
		// update array of processed images
		arrayAppend(filesProcessed, filesProcessedCount, filesToProcess[i]); filesProcessedCount++;
	}//end else we can just process the image like normal
	run("Close All");
}//end looping over each file we need to process

// Add Lab Processing into the mix
// set up parameters to send to LabProcessing
resultsName = "LabResults";
resultsNameParam = String.join(newArray("resultsName",resultsName), "?");
filesToProcessParam = String.join(
	newArray("filesToProcess", String.join(
		Array.slice(filesProcessed,0,filesProcessedCount), "\f")
	), "?");
fullParameterString = String.join(newArray(resultsNameParam, filesToProcessParam), "\r");
// send parameters over to the Lab Processing macro, process all the files at once
runMacro(labProcessorPath, fullParameterString);


if(useBatchMode){
	setBatchMode(false);
}//end if we have been using batch mode

curSummaryTitle = "Summary";

if(appendThreshold){
	if(isOpen(curSummaryTitle)){
		selectWindow(curSummaryTitle);
		curSummaryTitle = getInfo("window.title");
		newSummaryTitle = curSummaryTitle+"TH"+th01;
		Table.rename(curSummaryTitle, newSummaryTitle);
		curSummaryTitle = newSummaryTitle;
	}//end if the summary window is even open
}//end if we should append threshold to name of summary

if(appendSize){
	if(isOpen(curSummaryTitle)){
		selectWindow(curSummaryTitle);
		curSummaryTitle = getInfo("window.title");
		newSummaryTitle = curSummaryTitle+"-SizeLimit"+szMin+"-";
		if(infinitySwitch == false){
			newSummaryTitle = newSummaryTitle + defSizeLimit;
		}//end if we use normal defined size limit
		else{
			newSummaryTitle = newSummaryTitle + "Infinity";
		}//end else we use infinite size
		Table.rename(curSummaryTitle, newSummaryTitle);
		curSummaryTitle = newSummaryTitle;
	}//end if the summary window is even open
}//end if we should appent size limit to name of summary

// build arguments that need to be sent to ResultsFormatter macro
mainSummaryParam = String.join(newArray("mainSummaryName", curSummaryTitle), "?");
labResultsParam = String.join(newArray("labResultsName", resultsName), "?");
filesProccedParam = String.join(newArray("nFilesProcessed", filesProcessedCount), "?");
fullParameterString = String.join(newArray(mainSummaryParam, labResultsParam, filesProccedParam), "\r");
runMacro(resultsFormatterPath, fullParameterString);

///////////// MAIN FUNCTION START ///////////////

/*
 * Performs all the processing for a particular file. Code is all
 * from DB and SG.
 */
function processFile(){	
	run("Duplicate...", " ");
	
	run("Sharpen");
	run("Smooth");
	
	run("8-bit");
	
	setThreshold(0, th01);
	run("Convert to Mask");

	// set the scale so it doesn't measure in mm or something
	run("Set Scale...", "distance=0 known=0 unit=pixel global");
	// specify the measurement data to recieve from analyze particles
	run("Set Measurements...", "area perimeter bounding redirect=None decimal=1");
	
	if(infinitySwitch == true){
		run("Analyze Particles...", "size=szMin-Infinity "+
		"show=[Overlay Masks] clear summarize");
	}//end if we should do infinite max size
	else{
		run("Analyze Particles...", "size=szMin-defSizeLimit "+
		"show=[Overlay Masks] clear summarize");
	}//end else use defined size limit
	
	if(showParticles && !is("Batch Mode")){
		waitForUser("Particle showcase", "Particles should be outlined in blue\n"+
		"Parts of the image within the threshold should be outlined in black");
	}//end if particles can be visible and they should be
}//end processFile(filename)

////////////// MAIN FUNCTION END ////////////////

///////////// START OF SUPPORT FUNCTIONS ///////////////

function contains(array, val){
	foundVal = false;
	for(ijkm = 0; ijkm < lengthOf(array) && foundVal == false; ijkm++){
		if(array[ijkm] == val){
			foundVal = true;
		}//end if we found the value
	}//end looping over array
	return foundVal;
}//end contains

/*
 * Handles appending a value to an array by automatically
 * expanding the array if need be. Doesn't update the count
 * due to the limitations of the imagej macro language.
 */
function arrayAppend(array, count, val){
	if(lengthOf(array) >= count){
		Array.concat(array,newArray(lengthOf(array) / 5));
	}//end if we need to expand array
	array[count] = val;
	return array;
}//end arrayAppend(array, count, val)

// close down the macro
waitForUser("End of Macro", "When this message box is closed, the macro will terminate");
run("Close All");
run("Clear Results");
if(isOpen("Results")){selectWindow("Results"); run("Close");}
if(isOpen("Log")){selectWindow("Log"); run("Close");}
