import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PullPCParts {

    public PullPCParts(){
        this.readPartsData();
    }

    public ArrayList<PCParts> pcPartsCPU = new ArrayList<PCParts>();
    public ArrayList<PCParts> pcPartsGPU = new ArrayList<PCParts>();
    public ArrayList<PCParts> pcPartsDrive = new ArrayList<PCParts>();

    private ArrayList<String> fileLocations = new ArrayList<String>();

    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    public void readPartsData(){
        try {

            fileLocations.add("/Users/jakubsobczak/IdeaProjects/PCPartsEA/CPU4.csv");
            fileLocations.add("/Users/jakubsobczak/IdeaProjects/PCPartsEA/GPU2.csv");
            fileLocations.add("/Users/jakubsobczak/IdeaProjects/PCPartsEA/HDD.csv");

            for(int i=0;i<3;i++) {

                br = new BufferedReader(new FileReader(fileLocations.get(i)));
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] partsCPU = line.split(cvsSplitBy);
                    String[] partsGPU = line.split(cvsSplitBy);
                    String[] partsDrive = line.split(cvsSplitBy);
                    if(i==0) pcPartsCPU.add(new PCParts(partsCPU[0], Double.parseDouble(partsCPU[1]), Double.parseDouble(partsCPU[2])));
                    if(i==1)pcPartsGPU.add(new PCParts(partsGPU[0], Double.parseDouble(partsGPU[1]), Double.parseDouble(partsGPU[2])));
                    if(i==2)pcPartsDrive.add(new PCParts(partsDrive[0], Double.parseDouble(partsDrive[1]), Double.parseDouble(partsDrive[2])));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
