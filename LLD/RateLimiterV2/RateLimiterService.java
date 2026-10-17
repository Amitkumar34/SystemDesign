package LLD.RateLimiterV2;

import LLD.RateLimiterV2.enums.LimiterType;
import LLD.RateLimiterV2.factory.RateLimiterFactory;

public class RateLimiterService {
    private final RateLimiter rateLimiter;

    public RateLimiterService(LimiterType rlType, int maxAllowedRequest, int windowTimeInSeconds) {
        rateLimiter = RateLimiterFactory.createRateLimiter(rlType, maxAllowedRequest, windowTimeInSeconds);
    }

    public boolean allowRequest(String id) {
        boolean allowed = rateLimiter.allowRequest(id);
        System.out.println(Thread.currentThread().getName() + " : userId=" + id + ", request is " + allowed);
        return allowed;
    }
}
