package lesson4_design_patterns.dependency_injection;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Main {
    public static void main(String[] args) {
        // TODO: Update this code to create a new Guice injector from the Guice module you filled in.
        //       Use Guice.createInjector(...) to do this.

        // TODO: Instead of calling the ExpirationChecker constructor here, use the Guice injector to
        //       get an instance of the ExpirationChecker.
        Injector injector = Guice.createInjector(new ExpirationCheckerModule());
        ExpirationChecker checker = injector.getInstance(ExpirationChecker.class);

        List<Path> paths = Arrays.stream(args).map(Path::of).collect(Collectors.toList());

        System.out.println("The following files are expired: "
                + checker.getExpiredFiles(paths, Duration.ofDays(28)));
    }
}

