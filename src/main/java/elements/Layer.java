package elements;

import activationFunctions.ActivationFunction;

class Layer {
    Neuron[] neuron;
    private int numbersOfNeuronsInLayer;

    Layer(int numbersOfNeuronsInLayer, int sizeOfPreviusLaver, boolean isBias, ActivationFunction fun) {
        this.numbersOfNeuronsInLayer = numbersOfNeuronsInLayer;
        neuron = new Neuron[numbersOfNeuronsInLayer];

        for (int i = 0; i < numbersOfNeuronsInLayer; i++) {
            neuron[i] = new Neuron(fun, sizeOfPreviusLaver, isBias);
        }

    }

    void output() {
        for (int i = 0; i < numbersOfNeuronsInLayer; i++) {
            neuron[i].output();
        }
    }
}
