package LLD.RateLimiterV2.RateLimiterAlgos;

import LLD.RateLimiterV2.RateLimiter;
import LLD.RateLimiterV2.enums.LimiterType;
import LLD.RateLimiterV2.model.RateLimiterConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRL extends RateLimiter {
    private final Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTime = new HashMap<>();

    public TokenBucketRL(RateLimiterConfig config) {
        super(config, LimiterType.TOKEN_BUCKET);
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
        AtomicBoolean allow = new AtomicBoolean(false);
        tokens.compute(userId, (id, availableTokens) -> {
            int currentTokens = refillTokens(userId, now, availableTokens == null ? 0 : availableTokens);
            if (currentTokens > 0) {
                allow.set(true);
                return currentTokens - 1;
            }
            return currentTokens;
        });
        return allow.get();
    }

    private int refillTokens(String userId, long now, int availableTokens) {
        long lastUpdateTime = lastRefillTime.getOrDefault(userId, now);
        int refillRateTime = config.getWindowTimeInSeconds() / config.getMaxAllowedRequest();
        int addToken = now == lastUpdateTime ? 1 : ((int) ((now - lastUpdateTime) / 1000) / refillRateTime);
        int currentTokens = Math.min(availableTokens + addToken, config.getMaxAllowedRequest());
        if (currentTokens > 0) lastRefillTime.put(userId, now);
        return currentTokens;
    }
}
