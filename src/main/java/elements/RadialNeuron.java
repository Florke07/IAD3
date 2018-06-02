package elements;

import activationFunctions.ActivationFunction;

public class RadialNeuron {
    public double weightX;
    public double weightY;
    public ActivationFunction fun;

    public RadialNeuron(double weightX, double weightY, ActivationFunction fun) {
        this.weightX = weightX;
        this.weightY = weightY;
        this.fun = fun;
    }
}
