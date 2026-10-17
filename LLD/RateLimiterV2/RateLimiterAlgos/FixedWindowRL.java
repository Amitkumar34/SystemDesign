package LLD.RateLimiterV2.RateLimiterAlgos;

import LLD.RateLimiterV2.RateLimiter;
import LLD.RateLimiterV2.enums.LimiterType;
import LLD.RateLimiterV2.model.RateLimiterConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowRL extends RateLimiter {
    private final Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> windowStartTime = new HashMap<>();

    public FixedWindowRL(RateLimiterConfig config) {
        super(config, LimiterType.FIXED_WINDOW);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allow = new AtomicBoolean(false);
        tokens.compute(userId, (id, availableTokens) -> {
            int currentTokens = refillTokens(id, availableTokens == null ? 0 : availableTokens);
            if (currentTokens > 0) {
                allow.set(true);
                return currentTokens - 1;
            }
            return currentTokens;
        });
        return allow.get();
    }

    private int refillTokens(String userId, int availableTokens) {
        long now = System.currentTimeMillis() / 1000;
        long lastWindowStart = windowStartTime.getOrDefault(userId, -1L);

        long elapsedSeconds = lastWindowStart == -1 ? 0 : now - lastWindowStart;
        if (lastWindowStart == -1 || elapsedSeconds >= config.getWindowTimeInSeconds()) {
            long newWindowStart = now - elapsedSeconds % config.getWindowTimeInSeconds();
            windowStartTime.put(userId, newWindowStart);
            return config.getMaxAllowedRequest();
        }
        return availableTokens;
    }
}
