package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromTXTWindows implements ReadData {

    @Override
    public ArrayList<Double> readDouble(String pathToFile) {
        ArrayList<Double> ret = new ArrayList<>();
        File file = new File(pathToFile);
        Scanner in;
        String tmp = "";
        try {
            in = new Scanner(file);
            while (in.hasNextLine()) {
                tmp = in.next();
                ret.add(Double.parseDouble(tmp));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return ret;
    }
}
