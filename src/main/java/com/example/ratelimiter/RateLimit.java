public class RateLimit {
    private final long capacity;
    private final long refillRate;

    public RateLimit(long capacity, long refillRate, TimeUnit refillUnit) {
        this.capacity = capacity;
        this.refillRate = refillUnit.toNanos(refillRate);
    }

    public long getCapacity() {
        return capacity;
    }

    public long getRefillRate() {
        return refillRate;
    }
}
