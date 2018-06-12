import elements.RBFNetwork;
import plotter.DrawPlot;
import reader.ReadData;
import reader.ReadFromTXTWindows;

import java.util.ArrayList;

public class MyApp {
    public static void main(String args[]) {
        System.out.println("Hello");

        ReadData reader = new ReadFromTXTWindows();
        ArrayList<Double> list = new ArrayList<>();
        list = reader.readDouble("data/approximation_train_1.txt");
        ArrayList<Double> testData = reader.readDouble("data/approximation_test.txt");
        ArrayList<Double> in = new ArrayList<>();
        ArrayList<Double> exp = new ArrayList<>();
        ArrayList<Double> out = new ArrayList<>();

        double[][] funkcja = new double[1000][2];
        double[][] wynik = new double[1000][2];
        int k = 0;
        for (int i = 0; i < testData.size(); i += 2) {
            funkcja[k][0] = testData.get(i);
            funkcja[k][1] = testData.get(i + 1);
            k++;
        }
        // DrawPlot.draw(funkcja);
        int iloscEpokR = 100;
        int iloscEpokMLP = 1000;
        RBFNetwork RBF = new RBFNetwork(list, 20, 1, 1, iloscEpokR);
        int g;

        for (int e = 0; e < iloscEpokMLP; e++) {
            for (int i = 0; i < list.size(); i += 2) {

                in.clear();
                exp.clear();
                in.add(list.get(i));
                exp.add(list.get(i + 1));
                RBF.learnMLPLayer(in, exp);
            }
        }


        g = 0;
        for (int i = 0; i < testData.size(); i += 2) {
            in.clear();
            in.add(testData.get(i));
            out = RBF.feedForward(in);

            wynik[g][0] = in.get(0);
            wynik[g][1] = out.get(0);
            g++;
        }
        DrawPlot.draw(funkcja, wynik);
    }
}