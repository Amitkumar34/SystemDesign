package LLD.RateLimiterV2;

import LLD.RateLimiterV2.enums.LimiterType;
import LLD.RateLimiterV2.model.RateLimiterConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class RateLimiter {
    protected final RateLimiterConfig config;
    protected final LimiterType limiterType;

    public abstract boolean allowRequest(String userId);
}
