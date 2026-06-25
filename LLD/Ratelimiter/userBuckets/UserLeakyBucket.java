package LLD.Ratelimiter.userBuckets;

import LLD.Ratelimiter.UserBuckets;
import LLD.Ratelimiter.algos.LeakyBucket;
import lombok.Getter;

/** Assigns one LeakyBucket limiter per user. */
@Getter
public class UserLeakyBucket extends UserBuckets {
    private final int capacity;

    public UserLeakyBucket(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void addUser(int id) {
        bucket.put(id, new LeakyBucket(capacity));
    }
}
