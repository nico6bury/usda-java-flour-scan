package Config;

import java.util.Arrays;
import java.util.List;

/**
 * This class is used for storing the properties useful for configuring image processing through imagej. It should be used with ConfigConfig for actually storing and writing and reading config files.
 * @author Nicholas Sixbury
 */
public class ImageJConfig {
    /**
     * Lower bound of threshold for detecting kernels
     */
    public int lowThresholdKernel;
    /**
     * Upper bound of threshold for detecting kernels
     */
    public int hiThresholdKernel;
    
    /**
     * Lower bound of threshold for detecting chalk
     */
    public int lowThresholdChalk;
    /**
     * Upper bound of threshold for detecting chalk
     */
    public int hiThresholdChalk;

    /**
     * Base parameters for the "analyze particles" call to imagej, describing the columns to include in the output.
     */
    public List<String> measureParamsBase;
    /**
     * Parameters for the "analyze particles" call to imagej, describing the columns to include in the output.
     * Used when detecting kernels.
     */
    public List<String> measureParamsKernel;
    /**
     * Parameters for the "analyze particles" call to imagej, describing the columns to include in the output.
     * Used when detecting chalk.
     */
    public List<String> measureParamsChalk;

    /**
     * "Base" parameters for the "analyze particles" call to imagej, describing certain limits on what particles can be included in detection.
     */
    public List<String> analyzeBase;
    /**
     * Parameters for the "analyze particles" call to imagej, describing certain limits on what particles can be included in detection.
     * Used when detecting kernel.
     */
    public List<String> analyzeKernel;
    /**
     * Parameters for the "analyze particles" call to imagej, describing certain limits on what particles can be included in detection.
     * Used when detecting chalk.
     */
    public List<String> analyzeChalk;

    /**
     * This method simply sets all properties to their default values.
     */
    public void setDefault() {
        lowThresholdKernel = 80;
        hiThresholdKernel = 255;
        lowThresholdChalk = 185;
        hiThresholdChalk = 255;

        String[] measureParamsBaseDefault = new String[] {
            "area",
            "centroid",
            "perimeter",
            "fit",
            "shape",
            "redirect=None",
            "decimal=2",
            "bounding",
            "rectangle",
        };
        measureParamsBase = Arrays.asList(measureParamsBaseDefault);

        String[] measureParamsBaseKernel = new String[] {
            "area",
            "centroid",
            "perimeter",
            "fit",
            "shape",
            "redirect=None",
            "decimal=2",
        };
        measureParamsKernel = Arrays.asList(measureParamsBaseKernel);
        
        String[] measureParamsBaseChalk = new String[] {
            "area",
            "centroid",
            "perimeter",
            "fit",
            "shape",
            "redirect=None",
            "decimal=2",
        };
        measureParamsChalk = Arrays.asList(measureParamsBaseChalk);

        String[] analyzeBaseDefault = new String[] {
            "size=100-10000",
            "circularity=0.1-1.00",
            "bounding rectangle",
        };
        analyzeBase = Arrays.asList(analyzeBaseDefault);
        
        String[] analyzeKernelDefault = new String[] {
            "size=10-30000",
            "circularity=0.1-1.00",
        };
        analyzeKernel = Arrays.asList(analyzeKernelDefault);
        
        String[] analyzeChalkDefault = new String[] {
            "size=10-30000",
            "circularity=0.1-1.00",
        };
        analyzeChalk = Arrays.asList(analyzeChalkDefault);
    }//end setDefault()
}//end class ImageJConfig
