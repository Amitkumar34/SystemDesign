package LLD.Ratelimiter.userBuckets;

import LLD.Ratelimiter.UserBuckets;
import LLD.Ratelimiter.algos.SlidingWindow;
import lombok.Getter;

/** Assigns one SlidingWindow limiter per user. */
@Getter
public class UserSlidingWindow extends UserBuckets {
    private final int capacity;
    private final int timeWindowInSeconds;

    public UserSlidingWindow(int capacity, int timeWindowInSeconds) {
        this.capacity = capacity;
        this.timeWindowInSeconds = timeWindowInSeconds;
    }

    @Override
    public void addUser(int id) {
        bucket.put(id, new SlidingWindow(capacity, timeWindowInSeconds));
    }
}
