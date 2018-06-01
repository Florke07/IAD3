package reader;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ReadFromTXTLinux implements ReadData {

    @Override
    public ArrayList<Double> readDouble(final String pathToFile) {
        ArrayList<Double> list = new ArrayList<>();
        Scanner scan;
        File file = new File(pathToFile);
        try {
            scan = new Scanner(file);
            scan.useDelimiter("[,\n]").useLocale(Locale.US);

            while (scan.hasNextDouble()) {
                list.add(scan.nextDouble());
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return list;
    }

}
