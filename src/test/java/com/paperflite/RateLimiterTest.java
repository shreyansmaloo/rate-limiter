package com.paperflite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RateLimiterTest {

    private RateLimiter  rateLimiter;

    @Before
    public void setUp() {
        rateLimiter = new RateLimiter(10, 1.0);
    }

    @Test
    public void testRateLimiting() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            assertTrue(rateLimiter.allowRequest("user1", 1));
        }

        for (int i = 0; i < 5; i++) {
            assertFalse(rateLimiter.allowRequest("user1", 1));
        }

        try {
            Thread.sleep(1000); // Wait for 1 second to refill tokens
        } catch (InterruptedException e) {
            throw e;
        }
        assertTrue(rateLimiter.allowRequest("user1", 1));
    }

    @Test
    public void testMultipleUsers() {
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.allowRequest("user1", 1));
        }

        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.allowRequest("user2", 1));
        }
        assertFalse(rateLimiter.allowRequest("user2", 1));
    }
}
