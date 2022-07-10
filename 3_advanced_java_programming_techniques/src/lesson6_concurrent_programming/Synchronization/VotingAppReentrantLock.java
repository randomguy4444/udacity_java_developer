package lesson6_concurrent_programming.Synchronization;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class VotingAppReentrantLock {
    private static final Map<String, Integer> votes = new HashMap<>();
    private static final Lock lock = new ReentrantLock();
    public static void castVote(String performer) {
        lock.lock();
        try {
            votes.compute(
                    performer, (k, v) -> (v == null) ? 1 : v + 1);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        String str = "voting test";
        castVote(str);
    }
}
