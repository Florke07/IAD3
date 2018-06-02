package elements;

import activationFunctions.ActivationFunction;

import java.util.ArrayList;
import java.util.Random;

public class RadialLayer {
    public int numberOfNeurons;
    public ActivationFunction fun;
    public ArrayList<Double> inputs;
    public ArrayList<RadialNeuron> radialNeurons;

    public RadialLayer(int numberOfNeurons, ActivationFunction fun, ArrayList<Double> inputs) {
        Random rng = new Random();
        int posX;
        int posY;
        this.numberOfNeurons = numberOfNeurons;
        this.fun = fun;
        this.inputs = inputs;
        for(int i = 0;i < numberOfNeurons; i++){
            posX = (rng.nextInt(inputs.size()/2))*2;
            posY = posX+1;
            radialNeurons.add(new RadialNeuron(inputs.get(posX),inputs.get(posY),fun));
        }




    }
}
