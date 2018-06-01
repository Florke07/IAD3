import reader.ReadData;
import reader.ReadFromTXTWindows;

import java.util.ArrayList;

public class MyApp {
    public static void main(String args[]) {
        System.out.println("Hello");

        ReadData reader = new ReadFromTXTWindows();
        ArrayList<Double> list = new ArrayList<>();
        list = reader.readDouble("data/approximation_train_1.txt");
        for (Double d: list) {
            System.out.println(d);
        }
    }
}
