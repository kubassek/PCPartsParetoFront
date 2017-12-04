import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class EALoop {

    public EALoop(Integer population, Integer evolutions){
        this.populationSize = population;
        this.evolutions = evolutions;
        this.mainLoop();
    }

    public ArrayList<PCParts> possibleCpu = new ArrayList<PCParts>();
    public ArrayList<PCParts> possibleGpu = new ArrayList<PCParts>();
    public ArrayList<PCParts> possibleDrives = new ArrayList<PCParts>();

    private Random random = new Random();
    public int evolutions;
    public int populationSize;
    public ArrayList<PC> population = new ArrayList<PC>();
    public double mutationChance;
    public double mutationRate;
    private ArrayList<Integer> parentLocation = new ArrayList<Integer>();
    private int selectionIndividual=0;

    //MAin ea loop========================
    public void mainLoop(){
        this.inistialisePopulation();
        for(int i=0; i<evolutions;i++){
            ArrayList<PC> parents = new ArrayList<PC>();
            ArrayList<PC> children = new ArrayList<PC>();
            parents = this.selectParents();
            children = this.combineParents(parents);
            //children = this.mutation();
            this.repopulation(children, parents);
        }

        this.getParetoFront();

    }

    public void inistialisePopulation(){
        PullPCParts pull = new PullPCParts();


       // Print all pc parts

        System.out.println("CPU ========================");
        for(int i=0;i< pull.pcPartsCPU.size();i++){
            System.out.println(pull.pcPartsCPU.get(i).getPartName() + " " + pull.pcPartsCPU.get(i).getPerformace() + " " + pull.pcPartsCPU.get(i).getPrice());
        }

        System.out.println("GPU ========================");
        for(int i=0;i< pull.pcPartsGPU.size();i++){
            System.out.println(pull.pcPartsGPU.get(i).getPartName() + " " + pull.pcPartsGPU.get(i).getPerformace() + " " + pull.pcPartsGPU.get(i).getPrice());
        }

        System.out.println("Drives ========================");
        for(int i=0;i< pull.pcPartsDrive.size();i++){
            System.out.println(pull.pcPartsDrive.get(i).getPartName() + " " + pull.pcPartsDrive.get(i).getPerformace() + " " + pull.pcPartsDrive.get(i).getPrice());
        }


        //Generate a new random population and print results

        System.out.println("====================Population=======================");
        for(int i=0;i<populationSize;i++){
            //System.out.println("population member==========================================");
            population.add(new PC(pull.pcPartsCPU.get(random.nextInt(pull.pcPartsCPU.size()-1)), pull.pcPartsGPU.get(random.nextInt(pull.pcPartsGPU.size()-1)), pull.pcPartsDrive.get(random.nextInt(pull.pcPartsDrive.size()-1))));
            //System.out.println(population.get(i).getCpu().getPartName() + " " + population.get(i).getGpu().getPartName() + " " + population.get(i).getDrive().getPartName() + " $" + population.get(i).getPCPrice() + " fitness value: " + population.get(i).getFitness());
            //System.out.println(population.get(i).getFitness() + " $" + population.get(i).getPCPrice());
        }
        //System.out.println("===========================================");
    }

    public ArrayList<PC> selectParents(){
        ArrayList<PC> parents = new ArrayList<PC>();
        parentLocation.clear();

        parents.add(population.get(selectionIndividual));
        parentLocation.add(selectionIndividual);
        parentLocation.add(random.nextInt(populationSize));
        parents.add(population.get(parentLocation.get(1)));

        if(selectionIndividual == populationSize){
            selectionIndividual = 0;
        }else selectionIndividual++;

        return parents;
    }

    public ArrayList<PC> combineParents(ArrayList<PC> parents){
        ArrayList<PC> childrenPC = new ArrayList<PC>();

        int randomCpu = random.nextInt(1);
        int randomGpu = random.nextInt(1);
        int randomDrive = random.nextInt(1);

        childrenPC.add(new PC(parents.get(randomCpu).getCpu(),parents.get(randomGpu).getGpu(),parents.get(randomDrive).getDrive()));
        childrenPC.add(new PC(parents.get(this.switchRandom(randomCpu)).getCpu(),parents.get(this.switchRandom(randomGpu)).getGpu(),parents.get(this.switchRandom(randomDrive)).getDrive()));

        return childrenPC;
    }

    public int switchRandom(int random){
        if(random == 0) return 1;
        else return 0;
    }

    public void repopulation(ArrayList<PC> children, ArrayList<PC> parents){
        //this.sortPopulation();
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++) {
                if ((children.get(i).getFitness() > parents.get(j).getFitness()) && (children.get(i).getPCPrice() < parents.get(j).getPCPrice())) {
                    //System.out.println(population.get(parentLocation.get(j)).getFitness() + " $" + population.get(parentLocation.get(j)).getPCPrice() + " replaced by:"+ children.get(i).getFitness() + " " + children.get(i).getPCPrice());
                    population.remove(parentLocation.get(j));
                    population.add(children.get(i));
                    System.out.println(children.get(i).getFitness() + " $" + children.get(i).getPCPrice());
                }
            }
        }
    }

    public void getParetoFront(){
        ArrayList<PC> paretoFront = new ArrayList<PC>();
        for(int i=0; i<populationSize;i++){
            boolean paretoMember = true;
            for(int j=0; j<populationSize; j++){
                if((population.get(i).getFitness()<population.get(j).getFitness())&&(population.get(i).getPCPrice() > population.get(j).getPCPrice())){
                    paretoMember = false;
                }
            }
            if(paretoMember == true)paretoFront.add(population.get(i));
        }
        System.out.println("=====================Pareto Front========================");
        for(int i=0; i<paretoFront.size();i++){
            System.out.println(paretoFront.get(i).getFitness() + " $" + paretoFront.get(i).getPCPrice());
           // System.out.println(paretoFront.get(i).getCpu().getPartName() + " " + paretoFront.get(i).getGpu().getPartName() + " " + paretoFront.get(i).getDrive().getPartName());
        }

        System.out.println("\n");

        for(int i=0; i<paretoFront.size();i++){
            System.out.println(paretoFront.get(i).getFitness() + " $" + paretoFront.get(i).getPCPrice());
            System.out.println(paretoFront.get(i).getCpu().getPartName() + " " + paretoFront.get(i).getGpu().getPartName() + " " + paretoFront.get(i).getDrive().getPartName());
        }
    }

    public void sortPopulation(){
        Collections.sort(population, new Comparator<PC>() {
            @Override
            public int compare(PC o1, PC o2) {
                if(o1.getFitness() < o2.getFitness()){
                    return 1;
                }
                else if(o2.getFitness() < o1.getFitness()){
                    return -1;
                }
                else return 0;
            }
        });
    }

    public PC mutate(PC childPC){
     PC mutatedPC = childPC;

     return mutatedPC;
    }
}
