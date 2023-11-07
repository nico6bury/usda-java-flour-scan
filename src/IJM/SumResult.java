package IJM;

import java.io.File;

public class SumResult {
    public File file;
    public String slice;
    public int count;
    public int total_area;
    public double percent_area;
    public double l_mean;
    public double l_stdv;
    public LeftOrRight leftOrRight = LeftOrRight.Unknown;

    public SumResult() {}

    public SumResult(String slice, int count, int total_area, double percent_area) {
        this.slice = slice;
        this.count = count;
        this.total_area = total_area;
        this.percent_area = percent_area;
    }//end constructor

    public SumResult(File file, String slice, int count, int total_area, double percent_area) {
        this.file = file;
        this.slice = slice;
        this.count = count;
        this.total_area = total_area;
        this.percent_area = percent_area;
    }//end constructor

    public SumResult(String slice, int count, int total_area, double percent_area, double l_mean, double l_stdv) {
        this.slice = slice;
        this.count = count;
        this.total_area = total_area;
        this.percent_area = percent_area;
        this.l_mean = l_mean;
        this.l_stdv = l_stdv;
    }//end constructor
    
    public enum LeftOrRight {
        Left,
        Right,
        Unknown,
    }//end enum LeftOrRight
}//end struct SumResult

