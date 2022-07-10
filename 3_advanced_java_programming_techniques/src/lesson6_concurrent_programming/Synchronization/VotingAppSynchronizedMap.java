package lesson6_concurrent_programming.Synchronization;

import java.util.*;
import java.util.concurrent.*;

public final class VotingAppSynchronizedMap {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(12);

        Map<String, Integer> votes = Collections.synchronizedMap(new HashMap<>());

        List<Future<?>> futures = new ArrayList<>(10_000);
        for (int i = 0; i < 10_000; i++) {
            futures.add(
                    executor.submit(() -> {
                        // similar to concurrentHashMap, get and put are not safe but compute is
                        /*
                        Integer count = votes.get("Larry");
                        if (count == null) {
                            votes.put("Larry", 1);
                        } else {
                            votes.put("Larry", count + 1);
                        }*/
                        votes.compute("Larry", (k, v) -> (v == null) ? 1 : v + 1);
                    }));
        }
        for (Future<?> future : futures) {
            future.get();
        }
        executor.shutdown();

        System.out.println(votes);
    }
}
