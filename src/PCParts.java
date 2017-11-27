public class PCParts {

    public PCParts(String name, double performance, double price){
        this.partName = name;
        this.performace = performance;
        this.price = price;
    }

    // 1 = CPU 2 = GPU 3 = SSD/HDD

    private String partName;
    private double performace;
    private double price;

    public String getPartName() {
        return partName;
    }

    public double getPerformace() {
        return performace;
    }

    public double getPrice() {
        return price;
    }
}
