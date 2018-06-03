package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;

public class MLPLayer {
    ArrayList<MLPNeuron> mlpNeurons = new ArrayList<>();
    ArrayList<Double> lastOutput = new ArrayList<>();
    int numberOfInputs;
    int numberOfOutputs;
    RadialLayer radialLayer;
    ActivationFunction fun;

    public MLPLayer(int numberOfInputs, int numberOfOutputs, ActivationFunction fun,RadialLayer radialLayer) {
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.radialLayer = radialLayer;
        this.fun = fun;
        for (int i = 0;i < numberOfOutputs;i++){
            mlpNeurons.add(new MLPNeuron(fun,numberOfInputs));
        }
    }
    public ArrayList<Double> feedForward(ArrayList<Double> in){
        lastOutput.clear();
        for (MLPNeuron i: mlpNeurons) {
            lastOutput.add(i.feedForward(in));
        }
        return lastOutput;
    }
    public void calculatingWeightsBasedOnTrainingData (ArrayList<Double> AllIn){
        Double[][] vektorOmega = new Double[numberOfInputs][AllIn.size()/2];
        ArrayList<Double> singleIn = new ArrayList<>();
        ArrayList<Double> outForSingleIn;
        int k = 0;
        for(int i = 0; i < AllIn.size(); i+=2){
            singleIn.clear();
            singleIn.add(AllIn.get(i));
            singleIn.add(AllIn.get(i+1));
            outForSingleIn = radialLayer.feedForward(singleIn);
            for(int j = 0; j < outForSingleIn.size();j++){
                vektorOmega[k][j] = outForSingleIn.get(j);
            }
            k++;
        }

    }
}
