package lesson3_working_with_file_io._5_serialization;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class DeserializationExample {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("list.bin");
        try (var out = new ObjectOutputStream(Files.newOutputStream(path))) {
            out.writeObject(List.of("Hello", " ", "World!"));
        }
        try (var in = new ObjectInputStream(Files.newInputStream(path))) {
            List<String> deserialized = (List<String>) in.readObject();
            System.out.println(deserialized);
        }
    }
}
