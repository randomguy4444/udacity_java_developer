package lesson6_concurrent_programming.Synchronization;

import java.util.HashMap;
import java.util.Map;

public final class VotingAppSynchronizedApp {
    private static final Map<String, Integer> votes = new HashMap<>();

    public static synchronized void castVote(String performer) {
        //synchronized (this) {
            Integer count = votes.get(performer);
            if (count == null) {
                votes.put(performer, 1);
            } else {
                votes.put(performer, count + 1);
            }
        //}
    }

    public static void main(String[] args) {
        String str = "voting test";
        castVote(str);
    }
}
