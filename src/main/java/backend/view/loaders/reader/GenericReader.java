package backend.view.loaders.reader;


import java.io.IOException;
import java.util.List;

public interface GenericReader<T> {
    List<T> read(int count) throws IOException;
}
