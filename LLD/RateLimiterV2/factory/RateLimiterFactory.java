package LLD.RateLimiterV2.factory;

import LLD.RateLimiterV2.RateLimiter;
import LLD.RateLimiterV2.RateLimiterAlgos.FixedWindowRL;
import LLD.RateLimiterV2.RateLimiterAlgos.SlidingWindowRL;
import LLD.RateLimiterV2.RateLimiterAlgos.TokenBucketRL;
import LLD.RateLimiterV2.enums.LimiterType;
import LLD.RateLimiterV2.model.RateLimiterConfig;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(LimiterType type, int maxAllowedRequest, int windowTimeInSeconds){
        RateLimiterConfig config = new RateLimiterConfig(maxAllowedRequest, windowTimeInSeconds);
        return switch (type) {
            case FIXED_WINDOW -> new FixedWindowRL(config);
            case SLIDING_WINDOW -> new SlidingWindowRL(config);
            case TOKEN_BUCKET -> new TokenBucketRL(config);
        };
    }
}
