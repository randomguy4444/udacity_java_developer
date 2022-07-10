package lesson6_concurrent_programming.Synchronization;

import java.util.*;
import java.util.concurrent.*;

public final class VotingApp {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Map<String, Integer> votes = new HashMap<>();

        List<Future<?>> futures = new ArrayList<>(10_000);
        for (int i = 0; i < 10_000; i++) {
            futures.add(
                    executor.submit(() -> {
                        Integer count = votes.get("Larry");
                        if (count == null) {
                            votes.put("Larry", 1);
                        } else {
                            votes.put("Larry", count + 1);
                        }
                        // lambda equivalent style of programming
                        //votes.compute("Larry", (k, v) -> (v == null) ? 1 : v + 1);
                    }));
        }
        for (Future<?> future : futures) {
            future.get();
        }
        executor.shutdown();

        System.out.println(votes);
    }
}
