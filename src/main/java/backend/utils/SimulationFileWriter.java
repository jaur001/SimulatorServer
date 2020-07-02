package backend.utils;

import java.io.FileWriter;
import java.io.IOException;

public class SimulationFileWriter {

    public static void append(String text, String uri){
        write(text, uri, true);
    }

    public static void insert(String text, String uri){
        write(text,uri,false);
    }

    private static void write(String text, String uri, boolean append) {
        try {
            writeContent(text, uri, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeContent(String text, String uri, boolean append) throws IOException {
        FileWriter writer;
        writer = append ?new FileWriter(uri,true):new FileWriter(uri);
        writer.write(text+"\n");
        writer.flush();
        writer.close();
    }
}
