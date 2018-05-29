package elements;

import activationFunctions.ActivationFunction;

public class Network {
    private Layer outputLayer;
    private Layer hiddenLayer;

    private int numberOfInputs;
    private int numberOfOutputs;
    private int numberOfNeuronsOnHiddenLayers;
    private ActivationFunction fun;
    private double learningRate;
    private double momentumRate;
    private boolean isBias;

    public Network(boolean isBias, final ActivationFunction fun, int numberOfInputs, int numberOfOutputs, int numberOfNeuronsOnHiddenLayers, double learningRate, double momentumRate) {
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;

        this.numberOfNeuronsOnHiddenLayers = numberOfNeuronsOnHiddenLayers;
        this.fun=fun;
        this.isBias=isBias;
        this.learningRate=learningRate;
        this.momentumRate=momentumRate;


        hiddenLayer = new Layer(numberOfNeuronsOnHiddenLayers,numberOfInputs,isBias,fun);
        outputLayer = new Layer(numberOfOutputs,numberOfNeuronsOnHiddenLayers,isBias,fun);
    }

    public double[] Calculate(double[] inputs) {
        double[] out = new double[numberOfOutputs];

        for (int i=0;i<numberOfNeuronsOnHiddenLayers;i++){
            for (int j=0;j<numberOfInputs;j++){
                hiddenLayer.neuron[i].input[j] = inputs[j];
            }
        }
        hiddenLayer.output();


        for (int i=0;i<numberOfOutputs;i++){
            for (int j=0;j<numberOfNeuronsOnHiddenLayers;j++){
                outputLayer.neuron[i].input[j] = hiddenLayer.neuron[j].neuronResult;
            }
        }
        outputLayer.output();


        for (int i=0;i<numberOfOutputs;i++){
            out[i] = outputLayer.neuron[i].neuronResult;
        }

        return out;

    }

    public double Learn(double[] inputs, double[] expectedOutput){
        double[] currentOutputForNetwork = Calculate(inputs);
        double[] outputLayerError = new double[numberOfOutputs];
        double[] hiddenLayerError = new double[numberOfNeuronsOnHiddenLayers];


        for (int i=0;i<numberOfOutputs;i++){
            outputLayerError[i] = fun.Derivative(outputLayer.neuron[i].adderResult) * (expectedOutput[i] - currentOutputForNetwork[i]);
        }

        double temp=0;
        for (int i=0;i<numberOfNeuronsOnHiddenLayers;i++){
            temp=0;
            for (int j=0;j<numberOfOutputs;j++){
                temp += outputLayerError[j]*outputLayer.neuron[j].weight[i];

            }
            //if (isBias) temp += outputLayerError[i] * outputLayer.neuron[i].biasWeight;
            hiddenLayerError[i] = fun.Derivative(hiddenLayer.neuron[i].adderResult) * temp;
        }

        double squaredError=0;
        for (int i=0;i<numberOfOutputs;i++){
            squaredError += Math.pow((expectedOutput[i] - currentOutputForNetwork[i]),2);
        }
        squaredError = squaredError/numberOfOutputs;


        double delta=0;

        for (int i=0;i<numberOfOutputs;i++){
            for (int j=0;j<numberOfNeuronsOnHiddenLayers;j++){
                delta = (learningRate * outputLayerError[i] * outputLayer.neuron[i].input[j]) + (momentumRate * outputLayer.neuron[i].previusInputDelta[j]);
                outputLayer.neuron[i].weight[j] += delta;
                outputLayer.neuron[i].previusInputDelta[j] = delta;

            }

            if (isBias) {
                delta = ((learningRate * outputLayerError[i]) + (momentumRate * outputLayer.neuron[i].prevuisBiasDelta));
                outputLayer.neuron[i].biasWeight += delta;
                outputLayer.neuron[i].prevuisBiasDelta = delta;
            }
        }

        delta=0;

        for (int i=0;i<numberOfNeuronsOnHiddenLayers;i++){
            for (int j=0;j<numberOfInputs;j++){
                delta = (learningRate * hiddenLayerError[i] * hiddenLayer.neuron[i].input[j]) + (momentumRate * hiddenLayer.neuron[i].previusInputDelta[j]);
                hiddenLayer.neuron[i].weight[j] += delta;
                hiddenLayer.neuron[i].previusInputDelta[j] = delta;
            }

            if (isBias) {
                delta = ((learningRate * hiddenLayerError[i]) + (momentumRate * hiddenLayer.neuron[i].prevuisBiasDelta));
                hiddenLayer.neuron[i].biasWeight += delta;

                hiddenLayer.neuron[i].prevuisBiasDelta = delta;
            } else {
                //
            }
        }

        return squaredError;
    }
}
