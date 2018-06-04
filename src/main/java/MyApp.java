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
        ArrayList<Double> in = new ArrayList<>();
        ArrayList<Double> exp = new ArrayList<>();
        ArrayList<Double> out = new ArrayList<>();

        double[][] funkcja = new double[81][2];
        double[][] wynik = new double[81][2];
        int k=0;
        for (int i =0;i<list.size();i+=2) {
            funkcja[k][0] = list.get(i);
            funkcja[k][1] = list.get(i+1);
            k++;
        }
       // DrawPlot.draw(funkcja);
        RBFNetwork RBF = new RBFNetwork(list,50,1,1);
        for (int o=0;o<10;o++) {
            for (int i = 0; i < list.size(); i += 2) {

                in.clear();
                exp.clear();
                in.add(list.get(i));
                exp.add(list.get(i + 1));

                out = RBF.feedForward(in);

                System.out.println("Przed learnem");
                for (Double j : out) {
                    System.out.println(j);
                }

                System.out.println("Oczekiwana");
                for (Double j : exp) {
                    System.out.println(j);
                }

                RBF.learn(in, exp);

                out = RBF.feedForward(in);

                System.out.println("po learnie");
                for (Double j : out) {
                    System.out.println(j);
                }
            }
        }
    }
}