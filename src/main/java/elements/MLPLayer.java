package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;

public class MLPLayer {
    ArrayList<MLPNeuron> mlpNeurons = new ArrayList<>();
    ArrayList<Double> lastOutput = new ArrayList<>();
    int numberOfInputs;
    int numberOfOutputs;
    double learningRate = 0.7;
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
    public void learn(ArrayList<Double> in,ArrayList<Double> expectedOutput){
        feedForward(in);
        ArrayList<Double> error = new ArrayList<>();
        for(int i =0; i < expectedOutput.size();i++){
            error.clear();
            for(int j =0;j < numberOfInputs+1;j++){
                if(j<numberOfInputs) {
                    error.add((lastOutput.get(i) - expectedOutput.get(i)) * in.get(j));
                    mlpNeurons.get(i).weights.set(j, mlpNeurons.get(i).weights.get(j) - (learningRate * error.get(j)));
                }else {
                    error.add((lastOutput.get(i) - expectedOutput.get(i)));
                    mlpNeurons.get(i).weights.set(j, mlpNeurons.get(i).weights.get(j) - (learningRate * error.get(j)));
                }
            }
        }

    }
}