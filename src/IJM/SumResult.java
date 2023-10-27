package IJM;

public class SumResult {
    public String slice;
    public int count;
    public int total_area;
    public double percent_area;

    public SumResult(String slice, int count, int total_area, double percent_area) {
        this.slice = slice;
        this.count = count;
        this.total_area = total_area;
        this.percent_area = percent_area;
    }
}
