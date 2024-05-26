# Rate Limiter Project

## Description
This project implements a simple rate limiter in Java using the token bucket algorithm. The rate limiter monitors the number of requests per second a service allows and blocks excess calls if the limit is exceeded.

## Implementation
The project consists of the following classes:
- `TokenBucket`: Represents the token bucket used by the rate limiter.
- `RateLimiter`: Implements the rate limiting logic, allowing requests based on the token bucket's state.
- `Main`: Contains a simple example demonstrating how to use the rate limiter.

## Usage
To use the rate limiter, create an instance of `RateLimiter` with the desired capacity and refill rate. Then, call the `allowRequest` method to check if a request is allowed.

Example usage:
```java
RateLimiter rateLimiter = new RateLimiter(10, 1.0); // 10 requests per second
if (rateLimiter.allowRequest("user1", 1)) {
    // Process the request
} else {
    // Reject the request
}
```