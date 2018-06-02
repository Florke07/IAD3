package elements;

import activationFunctions.IdenityActivationFunction;
import activationFunctions.RadialActivationFunction;

import java.util.ArrayList;

public class RBFNetwork {
    public RadialLayer radialLayer;
    public Layer layer;
    public ArrayList<Double> inputs;
    public int numberOfNeuronsInRadialLayer;

    public RBFNetwork(ArrayList<Double> inputs, int numberOfNeuronsInRadialLayer) {
        this.inputs = inputs;
        this.numberOfNeuronsInRadialLayer = numberOfNeuronsInRadialLayer;
        radialLayer = new RadialLayer(numberOfNeuronsInRadialLayer,new RadialActivationFunction(),inputs);
        layer = new Layer(2,numberOfNeuronsInRadialLayer,true,new IdenityActivationFunction());
    }
}
