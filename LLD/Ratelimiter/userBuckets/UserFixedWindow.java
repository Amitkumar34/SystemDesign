package LLD.Ratelimiter.userBuckets;

import LLD.Ratelimiter.UserBuckets;
import LLD.Ratelimiter.algos.FixedWindow;
import lombok.Getter;

/** Assigns one FixedWindow limiter per user. */
@Getter
public class UserFixedWindow extends UserBuckets {
    private final int maxBucketCapacity, timeWindowInSeconds;

    public UserFixedWindow(int maxBucketCapacity, int timeWindowInSeconds) {
        this.maxBucketCapacity = maxBucketCapacity;
        this.timeWindowInSeconds = timeWindowInSeconds;
    }

    @Override
    public void addUser(int id) {
        bucket.put(id, new FixedWindow(maxBucketCapacity, timeWindowInSeconds));
    }
}
