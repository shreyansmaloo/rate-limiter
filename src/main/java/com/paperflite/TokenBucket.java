package com.paperflite;

public class TokenBucket {
    private final int capacity;
    private final double refillRate;
    private double tokens;
    private long lastRefillTimestamp;

    public TokenBucket(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean consume(int tokensRequested) {
        refill();
        if (tokens >= tokensRequested) {
            tokens -= tokensRequested;
            return true;
        }
        return false;
    }

    private synchronized void refill() {
        long currentTime = System.currentTimeMillis();
        double elapsedTime = (currentTime - lastRefillTimestamp) / 1000.0;
        double tokensToAdd = elapsedTime * refillRate;
        tokens = Math.min(capacity, tokens + tokensToAdd);
        lastRefillTimestamp = currentTime;
    }
}
