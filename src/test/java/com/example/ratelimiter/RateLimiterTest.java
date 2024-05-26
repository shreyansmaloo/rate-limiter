import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateLimiterTest {
    @Test
    public void testRateLimiter() {
        RateLimit defaultRateLimit = new RateLimit(5, 1, TimeUnit.SECONDS);
        RateLimitConfig config = new RateLimitConfig(defaultRateLimit);
        RateLimiter rateLimiter = new TokenBucketRateLimiter(config);

        String userId = "user1";
        String api = "api1";

        // Allow 5 requests initially
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.isAllowed(userId, api));
        }

        // 6th request should be blocked
        assertFalse(rateLimiter.isAllowed(userId, api));
    }
}
