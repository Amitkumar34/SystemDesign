package LLD.RateLimiterV2.RateLimiterAlgos;

import LLD.RateLimiterV2.RateLimiter;
import LLD.RateLimiterV2.enums.LimiterType;
import LLD.RateLimiterV2.model.RateLimiterConfig;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowRL extends RateLimiter {
    private final Map<String, Queue<Long>> requestLog = new ConcurrentHashMap<>();

    public SlidingWindowRL(RateLimiterConfig config) {
        super(config, LimiterType.SLIDING_WINDOW);
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
        System.out.println(now);
        AtomicBoolean allowed = new AtomicBoolean(false);
        requestLog.compute(userId, (id, log) -> {
            if(log == null) log = new ArrayDeque<>();
            while(!log.isEmpty() && (now - log.peek())/1000 >= config.getWindowTimeInSeconds())
                log.poll();
            if(log.size() < config.getMaxAllowedRequest()){
                log.offer(now);
                allowed.set(true);
            }
            return log;
        });
        return allowed.get();
    }
}
