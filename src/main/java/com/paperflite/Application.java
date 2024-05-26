package com.paperflite;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(10, 1.0); // Default rate limit: 10 requests per second

        for (int i = 0; i < 20; i++) {
            String key = "user1";
            boolean allowed = rateLimiter.allowRequest(key, 1);
            System.out.println("Request " + (i + 1) + " allowed: " + allowed);
            try {
                Thread.sleep(100); // Sleep for 100 milliseconds
            } catch (InterruptedException e) {
                throw e;
            }
        }
    }
}
