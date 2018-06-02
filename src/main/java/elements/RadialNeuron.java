package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;
import java.util.Random;

public class RadialNeuron {
    private double distance = 0;
    public double scalingRate;
    public double weightX;
    public double weightY;
    public ActivationFunction fun;

    public RadialNeuron(double weightX, double weightY, ActivationFunction fun) {
        this.weightX = weightX;
        this.weightY = weightY;
        this.fun = fun;
        Random rng = new Random();
        scalingRate = rng.nextDouble();
    }
    double distanceToInputVector(ArrayList<Double> in) {
        distance = Math.sqrt(Math.pow(weightX-in.get(0),2)+Math.pow(weightY-in.get(1),2));
        return distance;
    }
}
