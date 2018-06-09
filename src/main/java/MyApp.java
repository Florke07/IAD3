import elements.RBFNetwork;
import plotter.DrawPlot;
import reader.ReadData;
import reader.ReadFromTXTWindows;

import java.util.ArrayList;

public class MyApp {
    public static void main(String args[]) {
        System.out.println("Hello");

        ReadData reader = new ReadFromTXTWindows();
        ArrayList<Double> learData = new ArrayList<>();
        learData = reader.readDouble("data/classification_train.txt");
        ArrayList<Double> testData = reader.readDouble("data/classification_test.txt");

        for (Double d: learData) {
            System.out.println(d);
        }
    }
}