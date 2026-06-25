package LLD.Ratelimiter;

import java.util.HashMap;
import java.util.Map;

/**
 * Per-user rate limiter registry.
 * Maps userId -> RateLimiter algo instance.
 */
public abstract class UserBuckets {
    /** Distributed: use shared store (e.g. Redis) as single source of truth across servers. */
    protected final Map<Integer, RateLimiter> bucket;

    protected UserBuckets() {
        bucket = new HashMap<>();
    }

    public abstract void addUser(int id); // create algo bucket for a user

    public void accessApp(int id) { // simulate request; delegates to user's limiter
        RateLimiter rateLimiter = bucket.get(id);
        System.out.println(Thread.currentThread().getName() +
                " -> Able to access the app = " + (rateLimiter != null && rateLimiter.grantAccess())
        );
    }
}
