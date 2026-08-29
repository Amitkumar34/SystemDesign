package LLD.Ratelimiter.userBuckets;

import LLD.Ratelimiter.UserBuckets;
import LLD.Ratelimiter.algos.TokenBucket;
import lombok.Getter;

/** Assigns one TokenBucket limiter per user. */
@Getter
public class UserTokenBucket extends UserBuckets {
    private final int maxBucketCap;
    private final int tokenAdditionRatePerSeconds;

    public UserTokenBucket(int maxBucketCap, int tokenAdditionRatePerSeconds) {
        this.maxBucketCap = maxBucketCap;
        this.tokenAdditionRatePerSeconds = tokenAdditionRatePerSeconds;
    }

    @Override
    public void addUser(int id) {
        bucket.put(id, new TokenBucket(maxBucketCap, tokenAdditionRatePerSeconds));
    }
}
