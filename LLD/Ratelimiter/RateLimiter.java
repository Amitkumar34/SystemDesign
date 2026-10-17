package LLD.Ratelimiter;

/** Core contract: return true if request is allowed, false if rate-limited. */
public interface RateLimiter {
    boolean grantAccess();
}
