package backend.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SimulationFileReader {
    public static BufferedReader read(String url) {
        try {
            return new BufferedReader(new FileReader(url));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
