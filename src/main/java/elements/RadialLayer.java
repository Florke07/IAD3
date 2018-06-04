package elements;

import java.util.ArrayList;
import java.util.Random;

public class RadialLayer {
    public int numberOfNeurons;
    int sizeOfInputVector;
    private double lambda=1;
    public ArrayList<Double> AllInputs;
    public ArrayList<Double> lastOutput = new ArrayList<>();
    public ArrayList<RadialNeuron> radialNeurons = new ArrayList<>();

    public RadialLayer(int numberOfNeurons, ArrayList<Double> AllInputs, int sizeOfInputVector) {
        Random rng = new Random();
        this.sizeOfInputVector = sizeOfInputVector;
        ArrayList<Double> weightsForRadialNeurons = new ArrayList<>();
        this.numberOfNeurons = numberOfNeurons;
        this.AllInputs = AllInputs;
        for(int i = 0;i < numberOfNeurons; i++){
            weightsForRadialNeurons.clear();
            for(int j = 0; j < sizeOfInputVector;j++){
                weightsForRadialNeurons.add(AllInputs.get((rng.nextInt(AllInputs.size()/(sizeOfInputVector+1)))));
            }
            radialNeurons.add(new RadialNeuron(weightsForRadialNeurons));
        }

    }
    private double gaussFunction(double x){
        return Math.exp((-Math.pow(x,2))/(2*Math.pow(lambda,2)));
    }
    public ArrayList<Double> feedForward (ArrayList<Double> in){
        lastOutput.clear();
        for (RadialNeuron i:radialNeurons) {
            lastOutput.add(gaussFunction(i.distanceToInputVector(in)*i.scalingRate));
            //System.out.println(gaussFunction(i.distanceToInputVector(in)*i.scalingRate));
            //System.out.println(i.distanceToInputVector(in)*i.scalingRate);
        }
        return lastOutput;
    }
}
