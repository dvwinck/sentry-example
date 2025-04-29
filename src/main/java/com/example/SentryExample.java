package com.example;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentryExample {
    private static final Logger logger = LoggerFactory.getLogger(SentryExample.class);

    public static void main(String[] args) {
        // Initialize Sentry - it will automatically load sentry.properties from
        // classpath
        Sentry.init();

        try {
            logger.info("Starting application");
            
            // Call the processing method
            logger.info("First Number List");
            int[] numbers = {2, 3}; // Note: -1 will cause an exception
            processNumbers(numbers);

            logger.info("Second Number List");
            numbers = new int[] {0}; // Note: -1 will cause an exception
            processNumbers(numbers);


            logger.info("Last Number List");
            numbers = new int[] {5, 6, -1}; // Note: -1 will cause an exception
            processNumbers(numbers);
            
            // Example of capturing a custom event
            Sentry.captureMessage("All calculations completed", SentryLevel.INFO);
            
        } finally {
            // Ensure all events are sent before closing
            Sentry.close();
        }
    }

    private static void processNumbers(int[] numbers) {
        logger.info("Starting number processing");
        
        try {
            // Process different numbers
            
            
            for (int number : numbers) {
                logger.info("Processing number: {}", number);
                long result = calculateFactorial(number);
                logger.info("Factorial of {} is {}", number, result);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Error in processNumbers", e);
            Sentry.captureException(e);
        }
    }

    private static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        
        if (n == 0 || n == 1) {
            return 1;
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}