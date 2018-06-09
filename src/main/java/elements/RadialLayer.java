package elements;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.sort;

public class RadialLayer {
    public int numberOfNeurons;
    int sizeOfInputVector;
    public double lambdaMax;
    public double lambdaMin;
    public int wiek = 0;
    private int wiekMax;
    private double promienFunkcjiGaussowskiej =1;
    public ArrayList<Double> AllInputs;
    public ArrayList<Double> lastOutput = new ArrayList<>();
    public ArrayList<RadialNeuron> radialNeurons = new ArrayList<>();

    public RadialLayer(int numberOfNeurons, ArrayList<Double> AllInputs, int sizeOfInputVector,int iloscEpok) {
        Random rng = new Random();
        this.sizeOfInputVector = sizeOfInputVector;
        ArrayList<Double> weightsForRadialNeurons = new ArrayList<>();
        this.numberOfNeurons = numberOfNeurons;
        this.AllInputs = AllInputs;
        wiekMax = iloscEpok;
        for(int i = 0;i < numberOfNeurons; i++){
            weightsForRadialNeurons.clear();
            for(int j = 0; j < sizeOfInputVector;j++){
                weightsForRadialNeurons.add(AllInputs.get((rng.nextInt(AllInputs.size()/(sizeOfInputVector+1)))));
            }
            radialNeurons.add(new RadialNeuron(weightsForRadialNeurons));
        }

    }
    private double gaussFunction(double x){
        return Math.exp((-Math.pow(x,2))/(2*Math.pow(promienFunkcjiGaussowskiej,2)));
    }
    public ArrayList<Double> feedForward (ArrayList<Double> in){
        lastOutput.clear();
        for (RadialNeuron i:radialNeurons) {
            lastOutput.add(gaussFunction(i.distanceToInputVector(in)*i.scalingRate));
            //System.out.println(gaussFunction(i.distanceToInputVector(in)*i.scalingRate));
            //System.out.println(i.distanceToInputVector(in)*i.scalingRate);
        }
        return lastOutput;
    }

    public ArrayList<Double> learnRadialLayer(ArrayList<Double> in) {
        sort(in);
        for (RadialNeuron i : radialNeurons) {
            for (int j = 0; j < sizeOfInputVector; j++) {
                i.weights.set(j, (i.weights.get(j) + learningRateOdWieku(i) * funkcjaSasiedztwa(i) * (in.get(j) - i.weights.get(j))));
            }
        }
        wiek++;
        return feedForward(in);
    }

    private double funkcjaSasiedztwa(RadialNeuron n) {
        double tmp = Math.exp(-radialNeurons.indexOf(n) / lambdaOdWieku());
        //System.out.println(tmp);
        return tmp;
    }

    private double lambdaOdWieku() {
        return lambdaMax * Math.pow((lambdaMin / lambdaMax), ((double) wiek / (double) wiekMax));
    }

    private double learningRateOdWieku(RadialNeuron n) {
        n.learningRate = n.learningRateMax * Math.pow((n.learningRateMin / n.learningRate), ((double) wiek / (double) wiekMax));
        return n.learningRate;
    }


}
