package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;
import java.util.Random;

public class MLPNeuron {
    ActivationFunction fun;
    //ArrayList<Double> AllInputs;
    ArrayList<Double> weights = new ArrayList<>();//last is bias weight
    //Double biasWeight;
    double output;
    int numberOfInputs;

    public MLPNeuron(ActivationFunction fun,int numberOfInputs) {
        this.fun = fun;
        //this.AllInputs = AllInputs;
        this.numberOfInputs = numberOfInputs;
        Random rng = new Random();
        for (int i = 0;i < numberOfInputs + 1; i++){
            weights.add((rng.nextDouble()-0.5)*2);
        }
    }
    public Double feedForward(ArrayList<Double> in){
        output = 0;
        for (int i = 0; i < in.size(); i++){
            output += (weights.get(i)*in.get(i));
        }
        output += weights.get(weights.size()-1);
        return output;
    }
}
