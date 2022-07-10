package lesson2_functional_programming_in_java.lambda.limitations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public final class ReadFilesMain {
    // You can handle checked exceptions with a try-catch inside the lambda:
    public static void main(String[] args) throws IOException {
        List<String> fileNames = Arrays.asList("file-a.txt", "file-b.txt", "file-c.txt");

        fileNames.stream()
                .map(Path::of)
                .map(p -> {
                    try {
                        return Files.readAllLines(p, StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        return List.of();
                    }
                })
                .flatMap(List::stream)
                .forEach(System.out::println);
    }
    // or with a for loop
    /*
        public static void main(String[] args) throws IOException {
        List<String> fileNames = Arrays.asList("file-a.txt", "file-b.txt", "file-c.txt");

        for (String fileName : fileNames) {
            for (String line : Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)) {
                System.out.println(line);
            }
        }
    }
    */
}