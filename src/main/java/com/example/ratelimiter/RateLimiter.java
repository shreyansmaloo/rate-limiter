public interface RateLimiter {
    boolean isAllowed(String userId, String api);
}
