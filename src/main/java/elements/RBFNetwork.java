package elements;

import activationFunctions.IdenityActivationFunction;
import activationFunctions.RadialActivationFunction;

import java.util.ArrayList;

public class RBFNetwork {
    public RadialLayer radialLayer;
    public MLPLayer mlpLayer;
    public ArrayList<Double> inputs;
    ArrayList<Double> lastOutput;
    public int numberOfNeuronsInRadialLayer;
    int sizeOfInputVectorForRadialLayer;
    int numberOfOutputs;

    public RBFNetwork(ArrayList<Double> inputs, int numberOfNeuronsInRadialLayer,int numberOfOutputs,int sizeOfInputVectorForRadialLayer) {
        this.inputs = inputs;
        this.sizeOfInputVectorForRadialLayer = sizeOfInputVectorForRadialLayer;
        this.numberOfOutputs = numberOfOutputs;
        this.numberOfNeuronsInRadialLayer = numberOfNeuronsInRadialLayer;
        radialLayer = new RadialLayer(numberOfNeuronsInRadialLayer,inputs,sizeOfInputVectorForRadialLayer);
        mlpLayer = new MLPLayer(numberOfNeuronsInRadialLayer,numberOfOutputs,new IdenityActivationFunction(),radialLayer);
    }
    public ArrayList<Double> feedForward(ArrayList<Double> in){
        ArrayList<Double> tmp = radialLayer.feedForward(in);
        lastOutput = mlpLayer.feedForward(tmp);
        return lastOutput;
    }
}
