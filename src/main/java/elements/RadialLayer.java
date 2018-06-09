package elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RadialLayer {
    public int numberOfNeurons;
    int sizeOfInputVector;
    public double lambdaMax;
    public double lambdaMin;
    public int wiek = 0;
    private int wiekMax;
    private double scalingRate=0.1;
    private double promienFunkcjiGaussowskiej =0.3;
    public ArrayList<Double> AllInputs;
    public ArrayList<Double> lastOutput = new ArrayList<>();
    public ArrayList<RadialNeuron> radialNeurons = new ArrayList<>();

    public RadialLayer(int numberOfNeurons, ArrayList<Double> AllInputs, int sizeOfInputVector,int iloscEpok) {
        Random rng = new Random();

        this.sizeOfInputVector = sizeOfInputVector;
        ArrayList<Double> weightsForRadialNeurons;
        this.numberOfNeurons = numberOfNeurons;
        this.AllInputs = AllInputs;
        wiekMax = iloscEpok;
        for(int i = 0;i < numberOfNeurons; i++){
            weightsForRadialNeurons = new ArrayList<Double>();
            for(int j = 0; j < sizeOfInputVector;j++){
                weightsForRadialNeurons.add(AllInputs.get((rng.nextInt(AllInputs.size()/(sizeOfInputVector+1)))));
            }
            radialNeurons.add(new RadialNeuron(weightsForRadialNeurons));
        }
        lambdaMax = numberOfNeurons / 2;
        lambdaMin = 0.01;

    }
    private double gaussFunction(double x){
        return Math.exp((-Math.pow(x,2))/(2*Math.pow(promienFunkcjiGaussowskiej,2)));
    }
    public ArrayList<Double> feedForward (ArrayList<Double> in){
        lastOutput.clear();
        for (RadialNeuron i:radialNeurons) {
            lastOutput.add(gaussFunction(i.distanceToInputVector(in)*scalingRate));
            //System.out.println(gaussFunction(i.distanceToInputVector(in)*i.scalingRate));
            //System.out.println(i.distanceToInputVector(in)*i.scalingRate);
        }
        return lastOutput;
    }
    private void sort(ArrayList<Double> in) {
        for (RadialNeuron i : radialNeurons) {
            i.distanceToInputVector(in);
        }
        Collections.sort(radialNeurons);
    }

    public void learnRadialLayer(ArrayList<Double> in) {
        sort(in);
        for (RadialNeuron i : radialNeurons) {
            for (int j = 0; j < sizeOfInputVector; j++) {
                i.weights.set(j, (i.weights.get(j) + learningRateOdWieku(i) * funkcjaSasiedztwa(i) * (in.get(j) - i.weights.get(j))));
            }
        }
    }

    private double funkcjaSasiedztwa(RadialNeuron n) {
        double tmp = Math.exp(-radialNeurons.indexOf(n) / lambdaOdWieku());
        //System.out.println("Som:"+tmp);
        return tmp;
    }

    private double lambdaOdWieku() {
        //System.out.println("lambda:"+(lambdaMax * Math.pow((lambdaMin / lambdaMax), ((double) wiek / (double) wiekMax))));
        return lambdaMax * Math.pow((lambdaMin / lambdaMax), ((double) wiek / (double) wiekMax));
    }

    private double learningRateOdWieku(RadialNeuron n) {
        n.learningRate = n.learningRateMax * Math.pow((n.learningRateMin / n.learningRate), ((double) wiek / (double) wiekMax));
        //System.out.println("LearningRAte:"+n.learningRate);
        return n.learningRate;
    }


}
