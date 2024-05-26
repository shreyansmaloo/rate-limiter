package com.paperflite;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
    private final Map<String, TokenBucket> buckets;
    private final TokenBucket defaultBucket;

    public RateLimiter(int defaultCapacity, double defaultRefillRate) {
        this.buckets = new HashMap<>();
        this.defaultBucket = new TokenBucket(defaultCapacity, defaultRefillRate);
    }

    public synchronized boolean allowRequest(String key, int tokensRequested) {
        TokenBucket bucket = buckets.getOrDefault(key, defaultBucket);
        boolean allowed = bucket.consume(tokensRequested);
        if (!buckets.containsKey(key)) {
            buckets.put(key, bucket);
        }
        return allowed;
    }
}
