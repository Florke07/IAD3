package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;
import java.util.Random;

public class RadialNeuron implements Comparable<RadialNeuron>{
    private double distance = 0;
    public double scalingRate;
    public double learningRateMax = 0.5;
    public double learningRate = 0.5;
    public double learningRateMin = 0.03;
    public ArrayList<Double> weights;


    public RadialNeuron(ArrayList<Double> weights) {
        this.weights = weights;
        Random rng = new Random();
        scalingRate = 0.03;//rng.nextDouble();
    }
    double distanceToInputVector(ArrayList<Double> in) {
        if(in.size() == weights.size()) {
            distance = 0;
            for (int i = 0; i < weights.size(); i++) {
                distance += Math.pow(weights.get(i) - in.get(i), 2);
            }
            distance = Math.sqrt(distance);
            return distance;
        }else {
            System.out.println("Rozmiar wektora wejsciowego rozny od rozmiaru wektora wag");
            return 0;
        }
    }
    double distanceToOtherNeuron(RadialNeuron n) {
        double tmp = 0;
        for (int i = 0; i < weights.size(); i++) {
            tmp += Math.pow(weights.get(i) - n.weights.get(i), 2);
        }
        tmp = Math.sqrt(tmp);
        return tmp;
    }
    @Override
    public int compareTo(RadialNeuron o) {
        if (this.distance < o.distance)
            return -1;
        else if (o.distance < this.distance)
            return 1;
        return 0;
    }
}
