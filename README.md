# Rate Limiter
This rate limiter implementation uses the token bucket algorithm to monitor and control the number of requests per user and API combination. It supports configurable API-specific and default rate limits. The solution is easily configurable, production-ready, and includes automated tests to ensure correctness.

## Overview
This is a rate limiter implementation using the token bucket algorithm. It supports different rate limits for different APIs and a default rate limit.

## Configuration
- Default rate limit: 5 requests per second.
- API-specific rate limits can be configured.

## Usage
```java
RateLimit defaultRateLimit = new RateLimit(5, 1, TimeUnit.SECONDS);
RateLimitConfig config = new RateLimitConfig(defaultRateLimit);
config.addRateLimit("api1", new RateLimit(10, 1, TimeUnit.SECONDS));
RateLimiter rateLimiter = new TokenBucketRateLimiter(config);

String userId = "user1";
String api = "api1";

boolean isAllowed = rateLimiter.isAllowed(userId, api);
