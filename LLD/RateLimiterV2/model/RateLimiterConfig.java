package LLD.RateLimiterV2.model;

import lombok.Data;

@Data
public class RateLimiterConfig {
    private final int maxAllowedRequest;
    private final int windowTimeInSeconds;
}
