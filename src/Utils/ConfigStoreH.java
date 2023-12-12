package Utils;

/**
 * This class basically just acts as a struct to hold different labeled properies that should be stored in a human readable config file
 */
public class ConfigStoreH {
    public int proc_threshold = 160;
    public double area_threshold_lower = 0.05;
    public double area_threshold_upper = 0.10;
    public ConfigStoreH() {}
    public ConfigStoreH(int proc_threshold, double area_threshold_lower, double area_threshold_upper) {
        this.proc_threshold = proc_threshold;
        this.area_threshold_lower = area_threshold_lower;
        this.area_threshold_upper = area_threshold_upper;
    }//end full constructor
}//end class ConfigStoreH
