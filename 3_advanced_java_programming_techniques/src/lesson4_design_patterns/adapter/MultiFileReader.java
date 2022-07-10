package lesson4_design_patterns.adapter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MultiFileReader implements Closeable {

    private final List<BufferedReader> readers;

    public MultiFileReader(List<Path> paths) {
        readers = new ArrayList<BufferedReader>(paths.size());
        try {
            // TODO: Build the List of BufferedReaders
            for (Path path : paths) {
                readers.add(Files.newBufferedReader(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public List<BufferedReader> getReaders() {
        return Collections.unmodifiableList(readers);
    }

    @Override
    public void close() {
        // TODO: Close all the readers.
        for (BufferedReader reader: readers) {
            try {
                reader.close();
            } catch (Exception ignored) {
            }
        }
    }
}

