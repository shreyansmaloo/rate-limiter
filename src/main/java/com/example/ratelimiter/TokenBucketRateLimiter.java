import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter implements RateLimiter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    private final RateLimitConfig config;

    public TokenBucketRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean isAllowed(String userId, String api) {
        String key = userId + ":" + api;
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(config.getRateLimit(api)));
        return bucket.allowRequest();
    }

    private static class Bucket {
        private long tokens;
        private long lastRefillTimestamp;
        private final long capacity;
        private final long refillRate;

        Bucket(RateLimit rateLimit) {
            this.tokens = rateLimit.getCapacity();
            this.capacity = rateLimit.getCapacity();
            this.refillRate = rateLimit.getRefillRate();
            this.lastRefillTimestamp = System.nanoTime();
        }

        synchronized boolean allowRequest() {
            refillTokens();
            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        }

        private void refillTokens() {
            long now = System.nanoTime();
            long tokensToAdd = (now - lastRefillTimestamp) / refillRate;
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}
