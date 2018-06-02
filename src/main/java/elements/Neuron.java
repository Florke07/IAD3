package elements;

import activationFunctions.ActivationFunction;
import java.util.Random;

class Neuron {
    private ActivationFunction function;
    double adderResult;
    double neuronResult;
    double biasWeight;
    double prevuisBiasDelta;
    Double[] previusInputDelta;
    Double[] input;
    Double[] weight;

    private int numbersOfInputs;
    private boolean isBias;


    Neuron(ActivationFunction function, int numbersOfInputs, boolean isBias) {
        this.function = function;
        this.numbersOfInputs = numbersOfInputs;
        this.isBias = isBias;
        input = new Double[numbersOfInputs];
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

    void output() {
        calculate();
        neuronResult = function.Value(adderResult);
    }

    private void calculate() {
        adderResult = 0;

        for (int i = 0; i < numbersOfInputs; i++) {
            adderResult += input[i] * weight[i];
        }
        if (isBias) {
            adderResult += (1 * biasWeight);
        }
    }

}
