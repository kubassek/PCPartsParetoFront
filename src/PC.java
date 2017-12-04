public class PC{

    public PC(PCParts cpu, PCParts gpu, PCParts drive){

        this.cpu = cpu;
        this.gpu = gpu;
        this.drive = drive;
        this.PCPrice = cpu.getPrice() + gpu.getPrice() + drive.getPrice();
        this.fitness = this.fitnessCalculator();
    }

    private double cpuWeight = 0.5;
    private double gpuWeight = 1;
    private double driveWeight = 0.5;
    private PCParts cpu;
    private PCParts gpu;
    private PCParts drive;
    private double PCPrice = 0;
    private double fitness;

    public double fitnessCalculator(){
        double calculatedFitness = 0;

        calculatedFitness = (((cpu.getPerformace() * 100)/27000)*cpuWeight + ((gpu.getPerformace() * 100)/14000)*gpuWeight + ((drive.getPerformace() * 100)/17000)*driveWeight);

        return calculatedFitness;
    }

    public double getFitness() {
        return fitness;
    }

    public double getPCPrice() {
        return PCPrice;
    }

    public PCParts getCpu() {
        return cpu;
    }

    public void setCpu(PCParts cpu) {
        this.cpu = cpu;
    }

    public PCParts getGpu() {
        return gpu;
    }

    public void setGpu(PCParts gpu) {
        this.gpu = gpu;
    }

    public PCParts getDrive() {
        return drive;
    }

    public void setDrive(PCParts drive) {
        this.drive = drive;
    }
}
