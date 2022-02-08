import java.util.ArrayList;

public class LightStrip {
    private ArrayList<Light> allLights;
    private double totalLumens;
    private int flourescent;
    private double heatOutput;

    public LightStrip() {
        allLights = new ArrayList<>();
        totalLumens = 0.0;
        flourescent = 0;
        heatOutput = 0.0;
    }
    public void display() {
        for (int i = 0; i < this.allLights.size();i++) {
            totalLumens += this.allLights.get(i).getLumens();
            if (this.allLights.get(i).isFlourescent()) {
                flourescent++;
            }
            heatOutput += this.allLights.get(i).heatOutput();
            System.out.println(i + ": " + this.allLights.get(i));
        }
        System.out.println("Total lumens = " + totalLumens + "\n" +
                "Flourescent  = " + flourescent + "\n" +
                "Heat output  = " + heatOutput);
        totalLumens = 0.0;
        flourescent = 0;
        heatOutput = 0.0;
    }

    public void addLight(Light light){
        this.allLights.add(light);
    }

    public void removeLight(int index) {
        try {
            this.allLights.remove(index);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Error: No light at the index");
        }
    }
}
