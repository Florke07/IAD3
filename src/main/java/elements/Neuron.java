package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;
import java.util.Random;

class Neuron {
    private ActivationFunction function;
    double adderResult;
    double neuronResult;
    double biasWeight;
    double prevuisBiasDelta;
    Double[] previusInputDelta;
    //ArrayList<Double> AllInputs;
    Double[] weight;

    private int numbersOfInputs;
    private boolean isBias;


    Neuron(ActivationFunction function, int numbersOfInputs, boolean isBias) {
        this.function = function;
        this.numbersOfInputs = numbersOfInputs;
        this.isBias = isBias;
        //this.AllInputs = AllInputs;
        weight = new Double[numbersOfInputs];
        previusInputDelta = new Double[numbersOfInputs];
        prevuisBiasDelta = 0;

        Random random = new Random();
        for (int i = 0; i < numbersOfInputs; i++) {
            weight[i] = random.nextDouble()-0.5;
            previusInputDelta[i] = 0.0;
        }

        Random random1 = new Random();
        if (isBias) {
            biasWeight = random.nextDouble()-0.5;
        }

    }

    Double output(ArrayList<Double> inputs) {
        calculate(inputs);
        neuronResult = function.Value(adderResult);
        return neuronResult;
    }

    private void calculate(ArrayList<Double> inputs) {
        adderResult = 0;

        for (int i = 0; i < numbersOfInputs; i++) {
            adderResult += inputs.get(i) * weight[i];
        }
        if (isBias) {
            adderResult += (1 * biasWeight);
        }
    }

}
