
import java.util.HashMap;
import java.util.Map;

public class RateLimitConfig {
    private final RateLimit defaultRateLimit;
    private final Map<String, RateLimit> apiRateLimits = new HashMap<>();

    public RateLimitConfig(RateLimit defaultRateLimit) {
        this.defaultRateLimit = defaultRateLimit;
    }

    public void addRateLimit(String api, RateLimit rateLimit) {
        apiRateLimits.put(api, rateLimit);
    }

    public RateLimit getRateLimit(String api) {
        return apiRateLimits.getOrDefault(api, defaultRateLimit);
    }
}
