package backend.implementations.loaders.CSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileLoader {
    public static BufferedReader loadFile(String url) {
        try {
            return new BufferedReader(new FileReader(url));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
