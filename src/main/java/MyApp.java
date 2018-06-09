import elements.RBFNetwork;
import plotter.DrawPlot;
import reader.ReadData;
import reader.ReadFromTXTWindows;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyApp {
    public static void main(String args[]) {
        System.out.println("Hello");

        ReadData reader = new ReadFromTXTWindows();
        ArrayList<Double> list = new ArrayList<>();
        list = reader.readDouble("data/classification_train.txt");
        ArrayList<Double> testData = reader.readDouble("data/classification_test.txt");
        ArrayList<Double> in = new ArrayList<>();
        ArrayList<Double> exp = new ArrayList<>();
        ArrayList<Double> out = new ArrayList<>();

        double[][] oczekiwana = new double[93][2];
        double[][] otrzymana = new double[93][2];

        int iloscEpokR=100;
        int iloscEpokMLP = 100;
        RBFNetwork RBF = new RBFNetwork(list,5,1,4,iloscEpokR);
        for (int o=0;o<iloscEpokR;o++) {
            for (int i = 0; i < list.size(); i += 5) {

                in.clear();
                exp.clear();
                in.add(list.get(i));
                in.add(list.get(i+1));
                in.add(list.get(i+2));
                in.add(list.get(i+3));
                exp.add(list.get(i + 4));

                //out = RBF.feedForward(in);

                /*System.out.println("Przed learnem");
                for (Double j : out) {
                    System.out.println(j);
                }*/

                /*System.out.println("Oczekiwana");
                for (Double j : exp) {
                    System.out.println(j);
                }*/

                RBF.learnRadiallayer(in);

                //out = RBF.feedForward(in);

                /*System.out.println("po learnie");
                for (Double j : out) {
                    System.out.println(j);
                }*/
                //wynik[g][0] = in.get(0);
                //wynik[g][1] = out.get(0);
                //g++;
            }
            RBF.radialLayer.wiek++;
        }
        for(int e =0;e<iloscEpokMLP;e++){
            for (int i = 0; i < list.size(); i += 5) {

                in.clear();
                exp.clear();
                in.add(list.get(i));
                in.add(list.get(i+1));
                in.add(list.get(i+2));
                in.add(list.get(i+3));
                exp.add(list.get(i + 4));
                RBF.learnMLPLayer(in,exp);
            }
        }


        int k=0;
        DecimalFormat df = new DecimalFormat("0.0000");
        DecimalFormat df2 = new DecimalFormat("0.00000000");
        for (int i=0;i<testData.size();i+=5) {
            in.clear();
            in.add(testData.get(i));
            in.add(testData.get(i+1));
            in.add(testData.get(i+2));
            in.add(testData.get(i+3));
            out = RBF.feedForward(in);
            double d1 = testData.get(i+4);
            double d2 = out.get(0);
            double d3 = Math.abs(testData.get(i+4)-out.get(0));
            double d4 = (d3/d2)*100;
            System.out.print("Oczekiwano: "+d1+"   Otrzymano: "+df2.format(d2)+ "     Błąd: "+df.format(d3)+"   "+df.format(d4)+"%\n");
            //System.out.println("Otrzymano: "+out.get(0));
            oczekiwana[k][0]=k;
            oczekiwana[k][1]=testData.get(i+4);
            otrzymana[k][1]=out.get(0);
            otrzymana[k][0]=k;
            k++;
        }
        DrawPlot.draw(oczekiwana,otrzymana);
    }
}