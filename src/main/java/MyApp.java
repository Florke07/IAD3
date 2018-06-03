import elements.RBFNetwork;
import reader.ReadData;
import reader.ReadFromTXTWindows;

import java.util.ArrayList;

public class MyApp {
    public static void main(String args[]) {
        System.out.println("Hello");

        ReadData reader = new ReadFromTXTWindows();
        ArrayList<Double> list = new ArrayList<>();
        list = reader.readDouble("data/approximation_train_1.txt");
        ArrayList<Double> in = new ArrayList<>();
        ArrayList<Double> out = new ArrayList<>();
        in.add(0.5);
        RBFNetwork RBF = new RBFNetwork(list,4,1,1);
        out = RBF.feedForward(in);
        for(Double i: out){
            System.out.println(i);
        }
    }
}