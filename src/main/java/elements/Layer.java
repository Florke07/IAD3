package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;

class Layer {
    Neuron[] neurons;
    ArrayList<Double> output = new ArrayList<>();
    private int numbersOfNeuronsInLayer;

    Layer(int numbersOfNeuronsInLayer, int sizeOfPreviusLaver, boolean isBias, ActivationFunction fun) {
        this.numbersOfNeuronsInLayer = numbersOfNeuronsInLayer;
        neurons = new Neuron[numbersOfNeuronsInLayer];

        for (int i = 0; i < numbersOfNeuronsInLayer; i++) {
            neurons[i] = new Neuron(fun, sizeOfPreviusLaver, isBias);
        }

    }

    ArrayList<Double> output(ArrayList<Double> inputs) {
        for (int i = 0; i < numbersOfNeuronsInLayer; i++) {
            output.add(neurons[i].output(inputs));
        }
        return output;
    }
}
